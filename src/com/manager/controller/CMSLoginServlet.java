package com.manager.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;

@WebServlet("/cms/login.do")
public class CMSLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("login".equals(action)) { // 來自login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			System.out.println("=================");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String email = req.getParameter("manager_email");
				String password = req.getParameter("manager_password");

				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("管理員信箱: 請勿空白");
				}

				if (password == null || password.trim().length() == 0) {
					errorMsgs.add("管理員密碼: 請勿空白");
				}

				System.out.println(email);
				System.out.println(password);
				System.out.println("帳密非空白");

				// 取資料庫取值比對
				ManagerService managerSvc = new ManagerService();

				int manager_id = managerSvc.GETID(email);
				ManagerVO loginmanagerVO = managerSvc.getOneManager(manager_id);
				
				System.out.println(manager_id);

				if (email.trim().length() != 0) {
					if (manager_id == 0) {
						errorMsgs.add("信箱有誤，請重新輸入");
					}
				}

				// 當信箱無誤後，取出資料庫中的帳密
				String checkEmail = managerSvc.getOneManager(manager_id).getManager_email();
				String checkPsw = managerSvc.getOneManager(manager_id).getManager_password();

				System.out.println(checkEmail);
				System.out.println(checkPsw);

				if (!(password.equals(checkPsw))) {
					errorMsgs.add("密碼有誤，請重新輸入");
				}
				System.out.println("111111111111111");
				// Send the use back, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/cms/cmsLogin.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				System.out.println("222222222222222222222");
				// 成功登入後
				if ((email.equals(checkEmail)) && (password.equals(checkPsw))) {

					/*********************** 登入成功，帳密有效，才做以下動作 ************************************/

					HttpSession session = req.getSession();
					session.setAttribute("loginmanagerVO", loginmanagerVO); // *工作1: 才在session內做已經登入過的標識
					
//					req.setAttribute("loginmanagerVO", loginmanagerVO); // 資料庫取出的managerVO物件,存入req
					
					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
							res.sendRedirect(location);
							return;
						}
					} catch (Exception ignored) {
						System.out.println("666666666666");
					}
//						這個方法無法抓到登入的id的圖片
//						res.sendRedirect(req.getContextPath() + "/cms/protected/cmsIndex.jsp"); // *工作3: (-->如無來源網頁:則重導至cmsIndex.jsp)

//					      	準備轉交(Send the Success view)
					String url = "/cms/protected/cmsIndex.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交index.jsp
					successView.forward(req, res);
					System.out.println("55555");
//						
				}

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				System.out.println("333333333333333333");
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/cms/cmsLogin.jsp");
				failureView.forward(req, res);
			}
		}

		if ("logout".equals(action)) {
			
			HttpSession session = req.getSession(false);//如果當前沒有Session就為null，不要建立新session
			
			if(session == null){
				
				String url = "/cms/cmsLogin.jsp";
				RequestDispatcher protectView = req.getRequestDispatcher(url);
				protectView.forward(req, res);
//				res.sendRedirect("/cms/cmsLogin.jsp");
				return;	
				
			} else {
				session.removeAttribute("manager_id");
//				res.sendRedirect("/cms/cmsLogin.jsp");
				
				String url = "/cms/cmsLogin.jsp";
				RequestDispatcher logoutView = req.getRequestDispatcher(url); // 新增成功後轉交index.jsp
				logoutView.forward(req, res);
				
				return;	
			}			
		}

	}

}
