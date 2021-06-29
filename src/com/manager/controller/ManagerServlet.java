package com.manager.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.manager.model.MailService;
import com.manager.model.ManagerService;
import com.manager.model.ManagerVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ManagerServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("manager_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/manager/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer manager_id = null;
				try {
					manager_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/manager/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				ManagerService managerSvc = new ManagerService();
				ManagerVO managerVO = managerSvc.getOneManager(manager_id);
				if (managerVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/manager/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("managerVO", managerVO); // 資料庫取出的managerVO物件,存入req
				String url = "/manager/listOneManager.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllManager.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer manager_id = new Integer(req.getParameter("manager_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				ManagerService managerSvc = new ManagerService();
				ManagerVO managerVO = managerSvc.getOneManager(manager_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("managerVO", managerVO); // 資料庫取出的managerVO物件,存入req
				String url = "/manager/update_manager_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_manager_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/listAllManager.jsp");
				failureView.forward(req, res);
			}
		}


if("update".equals(action))

	{ // 來自update_manager_input.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer manager_id = new Integer(req.getParameter("manager_id").trim());
			System.out.println(manager_id);

			String manager_account = req.getParameter("manager_account");
			String manager_accountReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (manager_account == null || manager_account.trim().length() == 0) {
				errorMsgs.add("管理員帳號: 請勿空白");
			} else if (!manager_account.trim().matches(manager_accountReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
			}

			System.out.println(manager_account);

			String manager_name = req.getParameter("manager_name");
			if (manager_name == null || manager_name.trim().length() == 0) {
				errorMsgs.add("管理員姓名: 請勿空白");
			}

			String manager_email = req.getParameter("manager_email");
			String manager_emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
			if (manager_email == null || manager_email.trim().length() == 0) {
				errorMsgs.add("管理員信箱: 請勿空白");
			} else if (!manager_email.trim().matches(manager_emailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員信箱格式有誤，請再次確認");
			}

			String manager_password = req.getParameter("manager_password");
			String manager_passwordReg = "^[(a-zA-Z0-9)]{6,10}$";
			if (manager_password == null || manager_password.trim().length() == 0) {
				errorMsgs.add("管理員密碼: 請勿空白");
			} else if (!manager_password.trim().matches(manager_passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員密碼: 只能是英文字母和數字 , 且長度必需在6到10之間");
			}

			String manager_phone = req.getParameter("manager_phone");
			String manager_phoneReg = "^09[0-9]{8}$$";
			if (manager_phone == null || manager_phone.trim().length() == 0) {
				errorMsgs.add("管理員電話: 請勿空白");
			} else if (!manager_phone.trim().matches(manager_phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員電話有誤，請再次確認");
			}

			// 圖片，用getpart()

			Part file = req.getPart("manager_pic");
			String fileName = file.getSubmittedFileName();

			String path = "C:/uploadpic/" + fileName;

			FileOutputStream fos = new FileOutputStream(path);
			InputStream is = file.getInputStream();

			byte[] manager_pic = new byte[is.available()];
			is.read(manager_pic);
			fos.write(manager_pic);
			fos.close();

			String manager_picname = file.getSubmittedFileName();

			ManagerVO managerVO = new ManagerVO();
			managerVO.setManager_id(manager_id);
			managerVO.setManager_account(manager_account);
			managerVO.setManager_name(manager_name);
			managerVO.setManager_pic(manager_pic);
			managerVO.setManager_email(manager_email);
			managerVO.setManager_password(manager_password);
			managerVO.setManager_phone(manager_phone);
			managerVO.setManager_picname(manager_picname);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("managerVO", managerVO); // 含有輸入格式錯誤的managerVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/update_manager_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ManagerService managerSvc = new ManagerService();
			managerVO = managerSvc.updateManager(manager_id, manager_account, manager_name, manager_pic, manager_email,
					manager_password, manager_phone, manager_picname);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("managerVO", managerVO); // 資料庫update成功後,正確的的managerVO物件,存入req
			String url = "/manager/listOneManager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneManager.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
		} catch (Exception e) {
			errorMsgs.add("修改資料失敗:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/manager/update_manager_input.jsp");
			failureView.forward(req, res);
		}
	}

if("insert".equals(action))
	{ // 來自addmanager.jsp的請求

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String manager_account = req.getParameter("manager_account");
			System.out.println(manager_account);
			String manager_accountReg = "^[(a-zA-Z0-9_)]{2,10}$";
			if (manager_account == null || manager_account.trim().length() == 0) {
				errorMsgs.add("管理員帳號: 請勿空白");
			} else if (!manager_account.trim().matches(manager_accountReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String manager_name = req.getParameter("manager_name");
			if (manager_name == null || manager_name.trim().length() == 0) {
				errorMsgs.add("管理員姓名: 請勿空白");
			}

			String manager_email = req.getParameter("manager_email");
			String manager_emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
			if (manager_email == null || manager_email.trim().length() == 0) {
				errorMsgs.add("管理員信箱: 請勿空白");
			} else if (!manager_email.trim().matches(manager_emailReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員信箱格式有誤，請再次確認");
			}
			
			
//			亂數產生密碼
			int j;
		    StringBuilder sb = new StringBuilder();
		    int i;
		    for (i = 0; i < 8; i++) {
		      j = (int)((Math.random() * 3) + 1);
		 
		      if (j == 1) { // 放數字,unicdoe 48~57
		        sb.append((int) ((Math.random() * 10) + 48));
		      } else if (j == 2) { // 放大寫英文,unicdoe 65~90
		        sb.append((char) (((Math.random() * 26) + 65)));
		      } else {// 放小寫英文,unicode 97~122
		        sb.append(((char) ((Math.random() * 26) + 97)));
		      }
		    }
		    String manager_password = sb.toString();
		    System.out.println(manager_password);
			
			
//			String manager_password = req.getParameter("manager_password");
//			String manager_passwordReg = "^[(a-zA-Z0-9)]{6,10}$";
//			if (manager_password == null || manager_password.trim().length() == 0) {
//				errorMsgs.add("管理員密碼: 請勿空白");
//			} else if (!manager_password.trim().matches(manager_passwordReg)) { // 以下練習正則(規)表示式(regular-expression)
//				errorMsgs.add("管理員密碼: 只能是英文字母和數字 , 且長度必需在6到10之間");
//			}

			String manager_phone = req.getParameter("manager_phone");
			String manager_phoneReg = "^09[0-9]{8}$";
			if (manager_phone == null || manager_phone.trim().length() == 0) {
				errorMsgs.add("管理員電話: 請勿空白");
			} else if (!manager_phone.trim().matches(manager_phoneReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員電話有誤，請再次確認");
			}

			// 圖片，用getpart()
			Part file = req.getPart("manager_pic");
			String fileName = file.getSubmittedFileName();

			String path = "C:/uploadpic/" + fileName;

			FileOutputStream fos = new FileOutputStream(path);
			InputStream is = file.getInputStream();

			byte[] manager_pic = new byte[is.available()];
			is.read(manager_pic);
			fos.write(manager_pic);
			fos.close();

			String manager_picname = file.getSubmittedFileName();

			ManagerVO managerVO = new ManagerVO();
			managerVO.setManager_account(manager_account);
			managerVO.setManager_name(manager_name);
			managerVO.setManager_pic(manager_pic);
			managerVO.setManager_email(manager_email);
			managerVO.setManager_password(manager_password);
			managerVO.setManager_phone(manager_phone);
			managerVO.setManager_picname(manager_picname);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("managerVO", managerVO); // 含有輸入格式錯誤的managerVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/manager/addManager.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ManagerService managerSvc = new ManagerService();
			managerVO = managerSvc.addManager(manager_account, manager_name, manager_pic, manager_email,
					manager_password, manager_phone, manager_picname);
			System.out.println("新增完成");
			

			

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/cms/protected/manager_listAll_include.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllManager.jsp
			successView.forward(req, res);
			
//			新增成功後，寄認證信			
			String to = manager_email;
			String subject = "Guide好食-管理員密碼通知";
			String ch_name = manager_name;
			String passRandom = manager_password;
			String messageText = ch_name + "，您好!" + "\n" + "恭喜您加入Guide好食並成為管理員!" + "\n" +
								"請謹記此密碼: " + passRandom + "\n" +" (帳號已啟用，密碼可於登入後更新)"+ 
								"\n" + "\n" +"Guide好食團隊        敬上"; 
	       
			MailService mailService = new MailService();
			mailService.sendMail(to, subject, messageText);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/manager/addManager.jsp");
			failureView.forward(req, res);
		}
	}

	if("delete".equals(action))
	{ // 來自listAllManager.jsp

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		try {
			/*************************** 1.接收請求參數 ***************************************/
			Integer manager_id = new Integer(req.getParameter("manager_id"));

			/*************************** 2.開始刪除資料 ***************************************/
			ManagerService managerSvc = new ManagerService();
			managerSvc.deleteManager(manager_id);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/cms/protected/manager_listAll_include.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		} catch (Exception e) {
			errorMsgs.add("刪除資料失敗:" + e.getMessage());
			RequestDispatcher failureView = req.getRequestDispatcher("/manager/listAllmanager.jsp");
			failureView.forward(req, res);
		}
	}
}

}
