package com.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
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


import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.member_follower.model.Member_FollowerService;
import com.member_follower.model.Member_FollowerVO;
import com.shop.model.ShopService;
import com.shop.model.ShopVO;
import com.shop_favorites.model.Shop_FavoritesService;
import com.shop_favorites.model.Shop_FavoritesVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("insert".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//				String email = req.getParameter("EMAIL_ADDRESS");
//				MemberDAO dao = new MemberDAO();
//				
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String email = req.getParameter("EMAIL_ADDRESS");
				String password = req.getParameter("PASSWORD");
				String con_password = req.getParameter("CON_PASSWORD");
				if (email == null || (email.trim()).length() == 0) {
					errorMsgs.add("請輸入註冊信箱");
				} else if (password == null || (password.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				} 
				else if (!(password.equals(con_password))) {
					errorMsgs.add("請確認密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signup.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

//				Integer MEMBER_ID = null;
//				try {
//					MEMBER_ID = new Integer(email);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/ListOne.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}

				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				MemberService memSvc= new MemberService();
//				List<String> list=dao.accountCheck();
				List<String> list=memSvc.check();
				     if(list.contains(email)) {
				    	 errorMsgs.add("信箱已被註冊");
				     }
				     if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/member/signup.jsp");
							failureView.forward(req, res);
							return;//程式中斷
						}
				MemberVO MemberVO = new MemberVO();
				MemberVO.setMember_email(email);
				MemberVO.setMember_password(password);
				MemberVO.setMember_gender(1);
				memSvc.insert(email,password);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signup.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				HttpSession httpSession = req.getSession();
				httpSession.setAttribute("Login_email", email);
//				System.out.println((String) httpSession.getAttribute("Login_email"));
				String url = "/member/PersonalFile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signup.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("follow".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//				String email = req.getParameter("EMAIL_ADDRESS");
//				MemberDAO dao = new MemberDAO();
//				
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				int member_id= Integer.valueOf(req.getParameter("member_id"));
				int myMember_id= Integer.valueOf(req.getParameter("myMember_id"));
				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				Member_FollowerService memFolSvc= new Member_FollowerService();
//				List<String> list=dao.accountCheck();
				System.out.println("a");
//				Member_FollowerVO Member_FollowerVO=memFolSvc.insert(member_id,myMember_id);
				     
				Member_FollowerVO Member_FollowerVO = new Member_FollowerVO();
				Member_FollowerVO.setMEMBER_ID(member_id);
				Member_FollowerVO.setMEMBER_ID_FOLLOWER(myMember_id);
				memFolSvc.insert(member_id,myMember_id);
				System.out.println("a");
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signup.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("Member_FollowerVO", Member_FollowerVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/Person.jsp?member_id="+req.getParameter("member_id");
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signup.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		

		if ("update".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String name = req.getParameter("MEMBER_NAME");
				String gender = req.getParameter("MEMBER_GENDER");

				java.sql.Date MEMBER_BIRTH = null;
//				try {
					MEMBER_BIRTH = java.sql.Date.valueOf(req.getParameter("MEMBER_BIRTH").trim());
//				} catch (IllegalArgumentException e) {
//					MEMBER_BIRTH = new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				 Calendar cal = Calendar.getInstance();
				 Calendar now = Calendar.getInstance();
				 cal.setTime(MEMBER_BIRTH);
				 int born_year=cal.get(Calendar.YEAR);
				 int now_year=now.get(Calendar.YEAR);
				 int age = now_year - born_year;
				String address = req.getParameter("MEMBER_ADDRESS");
				String phone = req.getParameter("MEMBER_PHONE");
				String email = req.getParameter("MEMBER_EMAIL");
				
				
				Part part = req.getPart("MEMBER_PIC");
				String filename = getFileNameFromPart(part);
				String path = "C:/upload/"+filename;
				FileOutputStream fos = new FileOutputStream(path);
				InputStream in = part.getInputStream();
				byte[] data = new byte[in.available()];
				in.read(data);
				fos.write(data);
				fos.close();
				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				MemberService memSvc= new MemberService();
				MemberVO MemberVO = new MemberVO();
				MemberVO.setMember_name(name);
				Integer Member_gender;
				if (gender.equals("男")) {
					Member_gender=1;
				} else {
					Member_gender=2;
				}
				MemberVO.setMember_gender(Member_gender);
				MemberVO.setMember_birth(MEMBER_BIRTH);
	
				MemberVO.setMember_address(address);
				MemberVO.setMember_phone(phone);
				Timestamp ts = new java.sql.Timestamp(new java.util.Date().getTime());
				MemberVO.setMember_update_time(ts);
				MemberVO.setMember_email(email);
				MemberVO.setMember_age(age);
				MemberVO.setMember_pic(filename);
				memSvc.update(name,Member_gender,MEMBER_BIRTH,phone,address,filename,ts,age,email);
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/PersonalFile.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/PersonalFile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/PersonalData.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		if ("signin".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						
				HttpSession session=req.getSession();
				String login_email;
				String login_pswd;
				if((session.getAttribute("login"))==null) {
				login_email=req.getParameter("MEMBER_EMAIL");
				login_pswd=req.getParameter("MEMBER_PASSWORD");
				}else {
					login_email=((MemberVO) (session.getAttribute("login"))).getMember_email();
					login_pswd=((MemberVO) (session.getAttribute("login"))).getMember_password();
					
				}
				if (login_email == null || (login_email.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				if (login_pswd == null || (login_pswd.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				String email=null;
//				try {
//					email = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("帳號格式不正確");
//				}
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				MemberService memSvc=new MemberService();
//				List<String> list=dao.accountCheck();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/member/signin.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				session.setAttribute("login",MemberVO);
//				System.out.println(((MemberVO) (session.getAttribute("login"))).getMember_email());
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				String url ="/member/PersonalFile.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		if ("log_out".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						
				HttpSession session=req.getSession();
				String login_email;
				String login_pswd;
				if((session.getAttribute("login"))==null) {
				login_email=req.getParameter("MEMBER_EMAIL");
				login_pswd=req.getParameter("MEMBER_PASSWORD");
				}else {
					login_email=((MemberVO) (session.getAttribute("login"))).getMember_email();
					login_pswd=((MemberVO) (session.getAttribute("login"))).getMember_password();
					
				}
				if (login_email == null || (login_email.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				if (login_pswd == null || (login_pswd.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				String email=null;
//				try {
//					email = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("帳號格式不正確");
//				}
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				MemberService memSvc=new MemberService();
//				List<String> list=dao.accountCheck();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/member/signin.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				session.setAttribute("login",MemberVO);
//				System.out.println(((MemberVO) (session.getAttribute("login"))).getMember_email());
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			session.removeAttribute("login");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member//signin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		if ("toWallet".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						
				HttpSession session=req.getSession();
				String login_email;
				String login_pswd;
				if((session.getAttribute("login"))==null) {
				login_email=req.getParameter("MEMBER_EMAIL");
				login_pswd=req.getParameter("MEMBER_PASSWORD");
				}else {
					login_email=((MemberVO) (session.getAttribute("login"))).getMember_email();
					login_pswd=((MemberVO) (session.getAttribute("login"))).getMember_password();
					
				}
				if (login_email == null || (login_email.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				if (login_pswd == null || (login_pswd.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				String email=null;
//				try {
//					email = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("帳號格式不正確");
//				}
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				MemberService memSvc=new MemberService();
//				List<String> list=dao.accountCheck();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/member/signin.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				session.setAttribute("login",MemberVO);
//				System.out.println(((MemberVO) (session.getAttribute("login"))).getMember_email());
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/MyWallet.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		
		
		
		
		
		if ("toFavorites".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						
				HttpSession session=req.getSession();
				String login_email;
				String login_pswd;
				if((session.getAttribute("login"))==null) {
				login_email=req.getParameter("MEMBER_EMAIL");
				login_pswd=req.getParameter("MEMBER_PASSWORD");
				}else {
					login_email=((MemberVO) (session.getAttribute("login"))).getMember_email();
					login_pswd=((MemberVO) (session.getAttribute("login"))).getMember_password();
					
				}
				if (login_email == null || (login_email.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				if (login_pswd == null || (login_pswd.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				MemberService memSvc=new MemberService();
//				List<String> list=dao.accountCheck();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/member/signin.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			int Member_id=MemberVO.getMember_id();
			
			
			List<Shop_FavoritesVO> Shop_FavoritesVO= (List<Shop_FavoritesVO>) memSvc.getAllByMember(Member_id);
			List<ShopVO> list_myFavShop = new ArrayList<ShopVO>();
			for(Shop_FavoritesVO i:Shop_FavoritesVO) {
				int shop_id= i.getSHOP_ID();
				ShopService shopSvc = new ShopService();
				ShopVO ShopVO = shopSvc.getOneShop(shop_id);
				list_myFavShop.add(ShopVO);
			}
							
			Member_FollowerService memfolSvc=new Member_FollowerService();
			List<Integer> following = memfolSvc.GET_ALL_FOLLOWING(Member_id);
			List<MemberVO> list_following= new ArrayList<MemberVO>();
			 for(int i:following){
		            MemberVO Member_folVO = memSvc.GET_ONE_BY_ID(i);
		            list_following.add(Member_folVO);
		        }
			List<Integer> fans = memfolSvc.GET_ALL_FANS(Member_id);
			List<MemberVO> list_fans= new ArrayList<MemberVO>();
			for(int i:fans){
	            MemberVO Member_folVO = memSvc.GET_ONE_BY_ID(i);
	            list_fans.add(Member_folVO);
	           
	        }
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("list_following", list_following); // 資料庫取出的empVO物件,存入req
				req.setAttribute("list_fans", list_fans); // 資料庫取出的empVO物件,存入req
				req.setAttribute("list_myFavShop", list_myFavShop); // 資料庫取出的empVO物件,存入req
				
				String url = "/member/MyFavorites.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		if ("toActive".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						
				HttpSession session=req.getSession();
				String login_email;
				String login_pswd;
				if((session.getAttribute("login"))==null) {
				login_email=req.getParameter("MEMBER_EMAIL");
				login_pswd=req.getParameter("MEMBER_PASSWORD");
				}else {
					login_email=((MemberVO) (session.getAttribute("login"))).getMember_email();
					login_pswd=((MemberVO) (session.getAttribute("login"))).getMember_password();
					
				}
				if (login_email == null || (login_email.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				if (login_pswd == null || (login_pswd.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				String email=null;
//				try {
//					email = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("帳號格式不正確");
//				}
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				MemberService memSvc=new MemberService();
//				List<String> list=dao.accountCheck();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/member/signin.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				session.setAttribute("login",MemberVO);
//				System.out.println(((MemberVO) (session.getAttribute("login"))).getMember_email());
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/MyActivities.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		if ("toShop".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						
				HttpSession session=req.getSession();
				String login_email;
				String login_pswd;
				if((session.getAttribute("login"))==null) {
				login_email=req.getParameter("MEMBER_EMAIL");
				login_pswd=req.getParameter("MEMBER_PASSWORD");
				}else {
					login_email=((MemberVO) (session.getAttribute("login"))).getMember_email();
					login_pswd=((MemberVO) (session.getAttribute("login"))).getMember_password();
					
				}
				if (login_email == null || (login_email.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				if (login_pswd == null || (login_pswd.trim()).length() == 0) {
					errorMsgs.add("請輸入密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
//				String email=null;
//				try {
//					email = new String(str);
//				} catch (Exception e) {
//					errorMsgs.add("帳號格式不正確");
//				}
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				MemberService memSvc=new MemberService();
//				List<String> list=dao.accountCheck();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/member/signin.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				session.setAttribute("login",MemberVO);
//				System.out.println(((MemberVO) (session.getAttribute("login"))).getMember_email());
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				int member_id= MemberVO.getMember_id();
				ShopVO ShopVO= memSvc.GET_ONE_BY_MEMBER(member_id);
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("ShopVO", ShopVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/ShopZone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		
		
		
		
		
		if ("change".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				
				String str=req.getParameter("MEMBER_EMAIL");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String email=null;
				try {
					email = new String(str);
				} catch (Exception e) {
					errorMsgs.add("帳號格式不正確");
				}
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				MemberService memSvc = new MemberService();
//				MemberVO MemberVO =dao.getOneStmt(email);
				MemberVO MemberVO =memSvc.getOneMem(email);
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member//PersonalData.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("change_shop".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				
				String str=req.getParameter("MEMBER_EMAIL");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入帳號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String email=null;
				try {
					email = new String(str);
				} catch (Exception e) {
					errorMsgs.add("帳號格式不正確");
				}
				// Send the use back to the form, if there were errors
				
				/*************************** 2.開始查詢資料 *****************************************/
//				MemberDAO dao = new MemberDAO();
				MemberService memSvc = new MemberService();
//				MemberVO MemberVO =dao.getOneStmt(email);
				MemberVO MemberVO =memSvc.getOneMem(email);
				int member_id= MemberVO.getMember_id();
				ShopVO ShopVO= memSvc.GET_ONE_BY_MEMBER(member_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("ShopVO", ShopVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/ShopZoneData.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("update_shop".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String name = req.getParameter("SHOP_NAME");
				String price_level_s = req.getParameter("SHOP_PRICE_LEVEL");
				int price_level=Integer.valueOf(price_level_s);
				String opening_time = req.getParameter("SHOP_OPENING_TIME");
				String address = req.getParameter("SHOP_ADDRESS");
				String web = req.getParameter("SHOP_WEBSITE");
				String phone = req.getParameter("SHOP_PHONE");
				String id_s = req.getParameter("SHOP_ID");
				int id = Integer.valueOf(id_s);
				
				Part part = req.getPart("SHOP_MAIN_IMG");
				String filename = getFileNameFromPart(part);
				String path = "C:/upload/"+filename;
				FileOutputStream fos = new FileOutputStream(path);
				InputStream in = part.getInputStream();
				byte[] data = new byte[in.available()];
				in.read(data);
				fos.write(data);
				fos.close();
				/*************************** 2.開始查詢資料 *****************************************/
				String Member_email= req.getParameter("MEMBER_EMAIL"); 
				MemberService memSvc= new MemberService();
				MemberVO MemberVO = memSvc.getOneMem(Member_email);
				ShopVO ShopVO = new ShopVO();
				ShopVO.setShop_name(name);
				ShopVO.setShop_price_level(price_level);
				ShopVO.setShop_opening_time(opening_time);
				ShopVO.setShop_address(address);
				ShopVO.setShop_website(web);
				ShopVO.setShop_phone(phone);
				Timestamp ts = new java.sql.Timestamp(new java.util.Date().getTime());
				ShopVO.setShop_update_time(ts);
				ShopVO.setShop_main_img(filename);
				ShopVO.setShop_id(id);
				memSvc.update_shop(name,price_level,opening_time,address,web,phone,ts,filename,id);
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/ShopZoneData.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("ShopVO", ShopVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/ShopZone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/ShopZoneData.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		if ("delete_fol".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				int Member_ID= Integer.parseInt(req.getParameter("MEMBER_ID"));
				int Member_ID_Follwer= Integer.parseInt(req.getParameter("MEMBER_ID_FOL"));
				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc = new MemberService();
				memSvc.delete_fol(Member_ID,Member_ID_Follwer);
//				HttpSession session=req.getSession();
				MemberVO MemberVO= memSvc.GET_ONE_BY_ID(Member_ID);
				int Member_id=MemberVO.getMember_id();
				List<Shop_FavoritesVO> Shop_FavoritesVO= (List<Shop_FavoritesVO>) memSvc.getAllByMember(Member_id);
				List<ShopVO> list_myFavShop = new ArrayList<ShopVO>();
				for(Shop_FavoritesVO i:Shop_FavoritesVO) {
					int shop_id= i.getSHOP_ID();
					ShopService shopSvc = new ShopService();
					ShopVO ShopVO = shopSvc.getOneShop(shop_id);
					list_myFavShop.add(ShopVO);
				}
				Member_FollowerService memfolSvc=new Member_FollowerService();
				List<Integer> following = memfolSvc.GET_ALL_FOLLOWING(Member_id);
				List<MemberVO> list_following= new ArrayList<MemberVO>();
				 for(int i:following){
			            MemberVO Member_fing = memSvc.GET_ONE_BY_ID(i);
			            list_following.add(Member_fing);
			        }
				List<Integer> fans = memfolSvc.GET_ALL_FANS(Member_id);
				List<MemberVO> list_fans= new ArrayList<MemberVO>();
				for(int i:fans){
		            MemberVO Member_fans = memSvc.GET_ONE_BY_ID(i);
		            list_fans.add(Member_fans);
		        }
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			  	req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("list_following", list_following); // 資料庫取出的empVO物件,存入req
				req.setAttribute("list_fans", list_fans); // 資料庫取出的empVO物件,存入req
				req.setAttribute("list_myFavShop", list_myFavShop); // 資料庫取出的empVO物件,存入req
				System.out.println("a");
				String url = "/member/MyFavorites.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete_sf".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				int Member_ID= Integer.parseInt(req.getParameter("MEMBER_ID"));
				int Shop_ID= Integer.parseInt(req.getParameter("SHOP_ID"));

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc = new MemberService();
				memSvc.delete_sf(Member_ID,Shop_ID);
				MemberVO MemberVO= memSvc.GET_ONE_BY_ID(Member_ID);
				int Member_id=MemberVO.getMember_id();
				List<Shop_FavoritesVO> Shop_FavoritesVO= (List<Shop_FavoritesVO>) memSvc.getAllByMember(Member_id);
				List<ShopVO> list_myFavShop = new ArrayList<ShopVO>();
				for(Shop_FavoritesVO i:Shop_FavoritesVO) {
					int shop_id= i.getSHOP_ID();
					ShopService shopSvc = new ShopService();
					ShopVO ShopVO = shopSvc.getOneShop(shop_id);
					list_myFavShop.add(ShopVO);
				}
								
				Member_FollowerService memfolSvc=new Member_FollowerService();
				List<Integer> following = memfolSvc.GET_ALL_FOLLOWING(Member_id);
				List<MemberVO> list_following= new ArrayList<MemberVO>();
				 for(int i:following){
			            MemberVO Member_folVO = memSvc.GET_ONE_BY_ID(i);
			            list_following.add(Member_folVO);
			        }
				List<Integer> fans = memfolSvc.GET_ALL_FANS(Member_id);
				List<MemberVO> list_fans= new ArrayList<MemberVO>();
				for(int i:fans){
		            MemberVO Member_folVO = memSvc.GET_ONE_BY_ID(i);
		            list_fans.add(Member_folVO);
		           
		        }
					      							
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			  	req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("list_following", list_following); // 資料庫取出的empVO物件,存入req
				req.setAttribute("list_fans", list_fans); // 資料庫取出的empVO物件,存入req
				req.setAttribute("list_myFavShop", list_myFavShop); // 資料庫取出的empVO物件,存入req
				
				String url = "/member/MyFavorites.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/signin.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
	
	

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
