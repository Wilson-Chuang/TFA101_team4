package com.manager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.comment.model.CommentService;
import com.comment_report.model.*;
import com.manager.model.MailService;
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


public class CMSMemberServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("insert_shop".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				
				System.out.println("有進來");
				
				Integer member_id = new Integer(req.getParameter("member_id"));
				String shop_name = req.getParameter("Shop_name");
				String shop_tax_id = req.getParameter("Shop_tax_id");
				String shop_zip_code =req.getParameter("Shop_zip_code");
				
				System.out.println(shop_zip_code);
				
				String city=req.getParameter("SHOP_CITY");
				
				String shop_address=req.getParameter("address");
				
				System.out.println(shop_address);
				
				Double shop_latitude = new Double(req.getParameter("Shop_latitude"));
				Double shop_longitude = new Double(req.getParameter("Shop_longitude"));
				
				System.out.println(shop_latitude);
				
				String shop_phone=req.getParameter("Shop_phone");
				String shop_email=req.getParameter("Shop_email");
				String shop_description=req.getParameter("shop_description");
				String shop_tag=req.getParameter("Shop_tag");
				Integer Shop_price_level=new Integer(req.getParameter("Shop_price_level"));
				String shop_opening_time=req.getParameter("Shop_opening_time");
				String shop_website=req.getParameter("Shop_website");
				
				System.out.println(shop_opening_time);
				
				String shop_main_img= "";
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
				 
				 
				 System.out.println("===========================");
				 
				 Integer shop_reserv_status=new Integer(req.getParameter("Shop_reservation_status"));
				// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/shop/addShop.jsp");
						failureView.forward(req, res);
						return;//程式中斷
					}
				/*************************** 2.開始查詢資料 *****************************************/
				ShopService shopSvc=new ShopService();
				ShopVO ShopVO=shopSvc.insertShop(member_id, shop_tax_id, shop_name, shop_zip_code, city, shop_address, shop_latitude, shop_longitude, shop_description, shop_tag, shop_email, shop_phone, Shop_price_level, shop_opening_time, shop_website, shop_main_img, shop_gallery, shop_reserv_status);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/addShop.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("ShopVO", ShopVO); // 資料庫取出的empVO物件,存入req
				String url = "/shop/listAllShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/shop/addShop.jsp");
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
						"UPLOAD" + File.separator + "member"+ File.separator + "pic"+File.separator+MemberVO.getMember_id()+File.separator;
				File fileSaveDir = new File(uploadFilePath);
				if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdirs();
		        }
				part.write(uploadFilePath+filename);
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
				MemberVO=memSvc.getOneMem(email);
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
		
		
		
		if ("toShop".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
						
				HttpSession session=req.getSession();
				MemberVO MemberVO=(MemberVO)(session.getAttribute("login"));
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/sign/signin.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				

				/*************************** 2.開始查詢資料 *****************************************/
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
				String price_level_s = req.getParameter("Shop_price_level");
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
				System.out.println(price_level);
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
		

		
		if ("getOne_For_Update".equals(action)) { // 來自listAllMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer member_ID = new Integer(req.getParameter("member_ID"));

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.GET_ONE_BY_ID(member_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件,存入req
				String url = "/cms/protected/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_manager_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/cms/protected/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("changeOne_Status".equals(action)) { // 來自listAllMember.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer member_ID = new Integer(req.getParameter("member_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService memberSvc = new MemberService();
				memberSvc.change_status(member_ID);
				MemberVO memberVO = memberSvc.GET_ONE_BY_ID(member_ID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的memberVO物件,存入req
				String url = "/cms/protected/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_manager_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/cms/protected/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
	
	
	
	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i ++) {
		sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
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
