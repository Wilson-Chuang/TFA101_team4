package com.member.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.comment.model.CommentService;
import com.comment_report.model.*;
import com.comment.model.CommentVO;
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
							.getRequestDispatcher("/sign/signup.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}


				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc= new MemberService();
				List<String> list=memSvc.check();
				     if(list.contains(email)) {
				    	 errorMsgs.add("信箱已被註冊");
				     }
				     if (!errorMsgs.isEmpty()) {
							RequestDispatcher failureView = req
									.getRequestDispatcher("/sign/signup.jsp");
							failureView.forward(req, res);
							return;//程式中斷
						}
				MemberVO newMemberVO = new MemberVO();
				newMemberVO.setMember_email(email);
				newMemberVO.setMember_password(password);
				memSvc.insert(email,password);
				MemberVO MemberVO=memSvc.getOneMem(email);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sign/signup.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				HttpSession httpSession = req.getSession();
				httpSession.setAttribute("login", MemberVO);
				String url = req.getContextPath()+"/member/PersonalFile.jsp";
				res.sendRedirect(url);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signup.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("insert_shop".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//				
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				HttpSession session=req.getSession();
				MemberVO MemberVO=(MemberVO)session.getAttribute("login");
				int Member_id=MemberVO.getMember_id();
				String shop_name = req.getParameter("Shop_name");
				String shop_tax_id = req.getParameter("Shop_tax_id");
				String shop_zip_code =req.getParameter("Shop_zip_code");
				String city=req.getParameter("SHOP_CITY");
				String shop_address=req.getParameter("address");
				Double shop_latitude = new Double(req.getParameter("Shop_latitude"));
				Double shop_longitude = new Double(req.getParameter("Shop_longitude"));
				String shop_phone=req.getParameter("Shop_phone");
				String shop_email=req.getParameter("Shop_email");
				String shop_description=req.getParameter("shop_description");
				String shop_tag=req.getParameter("Shop_tag");
				Integer shop_price_level=new Integer(req.getParameter("Shop_price_level"));
				String shop_opening_time=req.getParameter("Shop_opening_time");
				String shop_website=req.getParameter("Shop_website");
				String shop_main_img= "noimage.jpg";
				if(!((req.getPart("SHOP_MAIN_IMG")).getSize()==0)) {
					Part part = req.getPart("SHOP_MAIN_IMG");
					shop_main_img = getFileNameFromPart(part);
					String appPath = req.getServletContext().getRealPath("");
					String uploadFilePath = appPath  + 
							"uploads" + File.separator + "shop"+ File.separator +  shop_tax_id+ 
							File.separator +"images";
					File fileSaveDir = new File(uploadFilePath);
					if (!fileSaveDir.exists()) {
				        fileSaveDir.mkdirs();
				      }
						part.write(uploadFilePath+File.separator +shop_main_img);
				}
				
				// 圖片庫
				List<Part> fileParts = req.getParts().stream().filter(part -> 
				"SHOP_GALLERY".equals(part.getName()) 
				&& part.getSize() > 0).collect(Collectors.toList());
				String shop_gallery ="";
				 if(!fileParts.isEmpty()) {
						String appPath = req.getServletContext().getRealPath("");
						String uploadFilePath = appPath  + 
								"uploads" + File.separator + "shop"+ File.separator +  
								shop_tax_id+ File.separator +"gallery";
				File fileSaveDir = new File(uploadFilePath);
		        if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdirs();
		        }		       
				HashSet<String> hs = new HashSet<String>();
			    for (Part galleryPart : fileParts) {
			    	
			        String fileName = Paths.get(galleryPart.getSubmittedFileName()).getFileName().toString();
			        galleryPart.write(uploadFilePath + File.separator + fileName);
			    }
			    File[] listOfFiles = fileSaveDir.listFiles();
			    listOfFiles = fileSaveDir.listFiles();
			    for (File file : listOfFiles) {
			        if (file.isFile()) {
			        	hs.add(file.getName());
			        }
			    }
			    shop_gallery = hs.toString();
				 }
				 
				 Integer shop_reserv_status=new Integer(req.getParameter("Shop_reservation_status"));
				// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/member/ShopZone.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				/*************************** 2.開始查詢資料 *****************************************/
				ShopService shopSvc=new ShopService();
				ShopVO ShopVO=shopSvc.insertShop(Member_id, shop_tax_id, shop_name, shop_zip_code, city, shop_address, shop_latitude, shop_longitude, shop_description, shop_tag, shop_email, shop_phone, shop_price_level, shop_opening_time, shop_website, shop_main_img, shop_gallery, shop_reserv_status);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/ShopZone.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("ShopVO", ShopVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/ShopZone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/member/join.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		if ("add_comment".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			int member_id =Integer.valueOf(req.getParameter("member_id"));
			int shop_id = Integer.valueOf(req.getParameter("shop_id"));
			
//				
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				
				BigDecimal comment_rating = new BigDecimal(req.getParameter("comment_rating"));
				String comment_text = req.getParameter("comment_text");
				Part part = req.getPart("comment_pic");
				String filename = getFileNameFromPart(part);
				String path = "C:/upload/"+filename;
				FileOutputStream fos = new FileOutputStream(path);
				InputStream in = part.getInputStream();
				byte[] data = new byte[in.available()];
				in.read(data);
				fos.write(data);
				fos.close();
				
				if(req.getParameter("comment_rating").equals("0")) {
					errorMsgs.add("請給個評價吧!");
				}
				if(comment_text.length()==0) {
					errorMsgs.add("說點什麼吧!");
				}
				if(req.getPart("comment_pic").getSize()==0) {
					errorMsgs.add("請選擇一張圖片!");
				}
				
//				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String url="/public/Shop.jsp?Shopid="+shop_id;
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/*************************** 2.開始查詢資料 *****************************************/
				CommentService comSvc= new CommentService();
//				
				if (!errorMsgs.isEmpty()) {
					String url="/public/Shop.jsp?Shopid="+shop_id;
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				CommentVO CommentVO=comSvc.insert(member_id,shop_id,comment_text,comment_rating,filename);
				ShopService shopSvc=new ShopService();
				double rating=comSvc.countRatings(shop_id);
				shopSvc.updateShopRaing(shop_id, rating);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String url="/public/Shop.jsp?Shopid="+shop_id;
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("CommentVO", CommentVO); // 資料庫取出的empVO物件,存入req
				String url = "/public/Shop.jsp?shop_id="+shop_id;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				String url="/public/Shop.jsp?Shopid="+shop_id;
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if ("comment_report".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String shop_id=req.getParameter("shop_id");
			String url="/public/Shop.jsp?Shopid="+shop_id;
//				
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				int member_id =Integer.valueOf(req.getParameter("MEMBER_ID"));
				int comment_id = Integer.valueOf(req.getParameter("COMMENT_ID"));
				String report_reason=req.getParameter("REASON");
				
//				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/*************************** 2.開始查詢資料 *****************************************/
				Comment_ReportService comSvc= new Comment_ReportService();
//				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Comment_ReportVO Comment_ReportVO=comSvc.insert(comment_id,member_id,report_reason);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("Comment_ReportVO", Comment_ReportVO); // 資料庫取出的empVO物件,存入req
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(url);
				failureView.forward(req, res);
			}
		}
		
		
		if ("follow".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//				
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				int member_id= Integer.valueOf(req.getParameter("member_id"));
				int myMember_id= Integer.valueOf(req.getParameter("myMember_id"));
				/*************************** 2.開始查詢資料 *****************************************/
				Member_FollowerService memFolSvc= new Member_FollowerService();
				     
				Member_FollowerVO Member_FollowerVO = new Member_FollowerVO();
				Member_FollowerVO.setMEMBER_ID(member_id);
				Member_FollowerVO.setMEMBER_ID_FOLLOWER(myMember_id);
				memFolSvc.insert(member_id,myMember_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sign/signup.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("Member_FollowerVO", Member_FollowerVO); // 資料庫取出的empVO物件,存入req
				String url = "/public/Person.jsp?member_id="+member_id;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signup.jsp");
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
				HttpSession session=req.getSession();
				MemberVO MemberVO=(MemberVO)(session.getAttribute("login"));
				String email =MemberVO.getMember_email();
				String filename=MemberVO.getMember_pic();
				
				
				
				String name = req.getParameter("MEMBER_NAME");
				String gender = req.getParameter("MEMBER_GENDER");

				java.sql.Date MEMBER_BIRTH = null;
					MEMBER_BIRTH = java.sql.Date.valueOf(req.getParameter("MEMBER_BIRTH").trim());
				 Calendar cal = Calendar.getInstance();
				 Calendar now = Calendar.getInstance();
				 cal.setTime(MEMBER_BIRTH);
				 int born_year=cal.get(Calendar.YEAR);
				 int now_year=now.get(Calendar.YEAR);
				 int age = now_year - born_year;
				 
				 String city = req.getParameter("city");
				 String county = req.getParameter("county");
				 String add =req.getParameter("address");
				String address = city+ county+add;
				String phone = req.getParameter("MEMBER_PHONE");
				
				
				if(!((req.getPart("MEMBER_PIC")).getSize()==0)) {
				Part part = req.getPart("MEMBER_PIC");
				filename = getFileNameFromPart(part);
				String appPath = req.getServletContext().getRealPath("");
				String uploadFilePath = appPath  + 
						"UPLOAD" + File.separator + "member"+ File.separator + "pic";
				File fileSaveDir = new File(uploadFilePath);
				if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdirs();
		        }
				part.write(uploadFilePath+File.separator +filename);
				}
				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc= new MemberService();
				MemberVO = new MemberVO();
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
							.getRequestDispatcher("/sign/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc=new MemberService();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/sign/signin.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				session.setAttribute("login",MemberVO);
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/sign/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				String location=(String) session.getAttribute("location");
				if(location==null) {
				String url ="/member/PersonalFile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				}else {
					session.removeAttribute("location");
					res.sendRedirect(location);
				}

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
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
			session.removeAttribute("login");

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				String url = "/sign/signin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		
		if ("toPersonal".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				
				HttpSession session=req.getSession();
				String appPath = req.getServletContext().getRealPath("");
				String uploadFilePath = appPath  + 
						"UPLOAD" + File.separator + "member"+ File.separator + "pic";
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
							.getRequestDispatcher("/sign/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				
				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc=new MemberService();
				List<String> list=memSvc.check();
				if(!list.contains(login_email)) {
					errorMsgs.add("信箱不存在");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sign/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				session.setAttribute("login",MemberVO);
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sign/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("uploadFilePath", uploadFilePath); // 資料庫取出的empVO物件,存入req
				String url = "/member/PersonalFile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
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
							.getRequestDispatcher("/sign/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc=new MemberService();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/sign/signin.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				session.setAttribute("login",MemberVO);
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/sign/signin.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
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
							.getRequestDispatcher("/sign/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc=new MemberService();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/sign/signin.jsp");
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
						.getRequestDispatcher("/sign/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/MyFavorites.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
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
							.getRequestDispatcher("/sign/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc=new MemberService();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/sign/signin.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				session.setAttribute("login",MemberVO);
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/sign/signin.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
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
							.getRequestDispatcher("/sign/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc=new MemberService();
				List<String> list=memSvc.check();
			     if(!list.contains(login_email)) {
			    	 errorMsgs.add("信箱不存在");
			     }
			     if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/sign/signin.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				MemberVO MemberVO =memSvc.getOneMem(login_email);
				session.setAttribute("login",MemberVO);
				String password = MemberVO.getMember_password();
				if(!login_pswd.equals(password) ) {
					errorMsgs.add("密碼錯誤");
				}
				int member_id= MemberVO.getMember_id();
				
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/sign/signin.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("MemberVO", MemberVO); // 資料庫取出的empVO物件,存入req
				String url = "/member/ShopZone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
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
							.getRequestDispatcher("/sign/signin.jsp");
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
				MemberService memSvc = new MemberService();
				MemberVO MemberVO =memSvc.getOneMem(email);
				// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/sign/signin.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
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
							.getRequestDispatcher("/sign/signin.jsp");
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
				MemberService memSvc = new MemberService();
				MemberVO MemberVO =memSvc.getOneMem(email);
				int member_id= MemberVO.getMember_id();
				ShopVO ShopVO= memSvc.GET_ONE_BY_MEMBER(member_id);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sign/signin.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
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
				HttpSession session=req.getSession();
				MemberVO myMemberVO = (MemberVO)session.getAttribute("login");
				int Member_id=myMemberVO.getMember_id();
				String name = req.getParameter("SHOP_NAME");
				String price_level_s = req.getParameter("SHOP_PRICE_LEVEL");
				int price_level=Integer.valueOf(price_level_s);
				String opening_time = req.getParameter("SHOP_OPENING_TIME");
				String city = req.getParameter("SHOP_CITY");
				String address = req.getParameter("address");
				String web = req.getParameter("SHOP_WEBSITE");
				String phone = req.getParameter("SHOP_PHONE");
				String email = req.getParameter("SHOP_EMAIL");
				String desc = req.getParameter("SHOP_DESC");
				String tag = req.getParameter("SHOP_TAG");
				String id_s = req.getParameter("SHOP_ID");
				int id = Integer.valueOf(id_s);
				String filename=req.getParameter("filename");
				if(!((req.getPart("SHOP_MAIN_IMG")).getSize()==0)) {
					Part part = req.getPart("SHOP_MAIN_IMG");
					filename = getFileNameFromPart(part);
					ShopService memSvc = new ShopService();
					ShopVO ShopVO = memSvc.getOneShop(new Integer(req.getParameter("SHOP_ID")));
					String appPath = req.getServletContext().getRealPath("");
					String uploadFilePath = appPath  + 
							"uploads" + File.separator + "shop"+ File.separator +  ShopVO.getShop_tax_id()+ 
							File.separator +"images";
					File fileSaveDir = new File(uploadFilePath);
					if (!fileSaveDir.exists()) {
				        fileSaveDir.mkdirs();
				      }
						part.write(uploadFilePath+File.separator +filename);
				}
				
				
				// 圖片庫
				List<Part> fileParts = req.getParts().stream().filter(part -> 
				"SHOP_GALLERY".equals(part.getName()) 
				&& part.getSize() > 0).collect(Collectors.toList());
				String shop_gallery ="";
				 if(!fileParts.isEmpty()) {
					 ShopService memSvc = new ShopService();
						ShopVO ShopVO = memSvc.getOneShop(new Integer(req.getParameter("SHOP_ID")));
						String appPath = req.getServletContext().getRealPath("");
						String uploadFilePath = appPath  + 
								"uploads" + File.separator + "shop"+ File.separator +  
								ShopVO.getShop_tax_id()+ File.separator +"gallery";
				File fileSaveDir = new File(uploadFilePath);
		        if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdirs();
		        }		       
				HashSet<String> hs = new HashSet<String>();
			    for (Part galleryPart : fileParts) {
			    	
			        String fileName = Paths.get(galleryPart.getSubmittedFileName()).getFileName().toString();
			        galleryPart.write(uploadFilePath + File.separator + fileName);
			    }
			    File[] listOfFiles = fileSaveDir.listFiles();
			    listOfFiles = fileSaveDir.listFiles();
			    for (File file : listOfFiles) {
			        if (file.isFile()) {
			        	hs.add(file.getName());
			        }
			    }
			    shop_gallery = hs.toString();
				 }
				/*************************** 2.開始查詢資料 *****************************************/
				String Member_email= req.getParameter("MEMBER_EMAIL"); 
				MemberService memSvc= new MemberService();
				MemberVO MemberVO = memSvc.getOneMem(Member_email);
				ShopVO ShopVO=new ShopVO();
				ShopVO.setShop_name(name);
				ShopVO.setShop_price_level(price_level);
				ShopVO.setShop_opening_time(opening_time);
				ShopVO.setShop_address(address);
				ShopVO.setShop_city(city);
				ShopVO.setShop_website(web);
				ShopVO.setShop_phone(phone);
				ShopVO.setShop_email(email);
				ShopVO.setShop_description(desc);
				ShopVO.setShop_tag(tag);
				Timestamp ts = new java.sql.Timestamp(new java.util.Date().getTime());
				ShopVO.setShop_update_time(ts);
				ShopVO.setShop_main_img(filename);
				ShopVO.setShop_gallery(shop_gallery);
				ShopVO.setShop_id(id);
				memSvc.update_shop(name,price_level,opening_time,address,city,web,phone,email,desc,tag,ts,filename,shop_gallery,id);
				ShopVO = memSvc.GET_ONE_BY_SHOP(id);
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
						.getRequestDispatcher("/sign/signin.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete_comment_rt".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String url = "/public/comment_report_list.jsp";
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				int comment_ID= Integer.parseInt(req.getParameter("comment_id"));
				int comment_status= Integer.parseInt(req.getParameter("comment_status"));
				int comment_report_status= Integer.parseInt(req.getParameter("comment_report_status"));
				int comment_report_ID= Integer.parseInt(req.getParameter("comment_report_id"));
				/*************************** 2.開始查詢資料 *****************************************/
				Comment_ReportService rtSvc=new Comment_ReportService();
				rtSvc.update_status(comment_report_status,comment_report_ID);
				CommentService comSvc = new CommentService();
				comSvc.update_status(comment_status,comment_ID);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("comment_ID", comment_ID); // 資料庫取出的empVO物件,存入req
				
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(url);
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
						.getRequestDispatcher("/sign/signin.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert_sf".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				int Member_ID= Integer.parseInt(req.getParameter("MEMBER_ID"));
				int Shop_ID= Integer.parseInt(req.getParameter("SHOP_ID"));
				
				/*************************** 2.開始查詢資料 *****************************************/
				Shop_FavoritesService sfSvc=new Shop_FavoritesService();
				MemberService memSvc = new MemberService();
				Shop_FavoritesVO Ahop_FavoritesVO=sfSvc.insert(Member_ID,Shop_ID);
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
							.getRequestDispatcher("/sign/signin.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/sign/signin.jsp");
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
