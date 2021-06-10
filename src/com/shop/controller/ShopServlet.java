package com.shop.controller;

import java.io.*;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.shop.model.*;

@MultipartConfig(fileSizeThreshold=1024*1024*10, 
maxFileSize=1024*1024*50,
maxRequestSize=1024*1024*100)   
public class ShopServlet extends HttpServlet {

	private static final long serialVersionUID = 8523979247180883296L;
	private static final String UPLOAD_DIR = "uploads";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("shop_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商家編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer shop_id = null;
				try {
					shop_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("商家編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(shop_id);
				if (shopVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopVO", shopVO); // 資料庫取出的shopVO物件,存入req
				String url = "/shop/listOneShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneShop.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllShop.jsp 或  /dept/listShops_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer shop_id = new Integer(req.getParameter("shop_id"));
				
				/***************************2.開始查詢資料****************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(shop_id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("shopVO", shopVO); // 資料庫取出的shopVO物件,存入req
				String url = "/shop/update_shop_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_shop_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_shop_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer shop_id = new Integer(req.getParameter("shop_id").trim());
												
				String shop_tax_id = req.getParameter("shop_tax_id").trim();
				String shop_tax_idReg = "^[0-9]{8}$";
				if (shop_tax_id == null || shop_tax_id.trim().length() == 0) {
					errorMsgs.add("統一編號請勿空白");
				} else if(!shop_tax_id.trim().matches(shop_tax_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("統一編號: 統一編號格式錯誤，須為八個數字");
	            }
				
				String shop_name = req.getParameter("shop_name");
				if (shop_name == null || shop_name.trim().length() == 0) {
					errorMsgs.add("商家名稱: 請勿空白");
				}
				
				String shop_zip_code = req.getParameter("shop_zip_code");
				if (shop_zip_code == null || shop_zip_code.trim().length() == 0) {
					errorMsgs.add("郵遞區號: 請勿空白");
				}
				
				String shop_city = req.getParameter("shop_city");
				if (shop_city == null || shop_city.trim().length() == 0) {
					errorMsgs.add("縣市: 請勿空白");
				}
				
				String shop_address = req.getParameter("shop_address");
				if (shop_address == null || shop_address.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				}
				
				Double shop_latitude = null;
				try {
					shop_latitude = new Double(req.getParameter("shop_latitude").trim());
				} catch (NumberFormatException e) {
					shop_latitude = 0.0;
					errorMsgs.add("請填正確格式的緯度");
				}

				Double shop_longitude = null;
				try {
					shop_longitude = new Double(req.getParameter("shop_longitude").trim());
				} catch (NumberFormatException e) {
					shop_longitude = 0.0;
					errorMsgs.add("請填正確格式的經度");
				}
				
				String shop_description = req.getParameter("shop_description");
				String shop_tag = req.getParameter("shop_tag");
				
				Double shop_rating = null;
				try {
					shop_rating = new Double(req.getParameter("shop_rating").trim());
				} catch (NumberFormatException e) {
					shop_rating = 0.0;
					errorMsgs.add("請填正確格式的評價");
				}
				
				Integer shop_rating_count = new Integer(req.getParameter("shop_rating_count").trim());
				Integer shop_rating_total = new Integer(req.getParameter("shop_rating_total").trim());
						
				String shop_emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				String shop_email = req.getParameter("shop_email");
				if (shop_email == null || shop_email.trim().length() == 0) {
					errorMsgs.add("Email: 請勿空白");
				} else if(!shop_email.trim().matches(shop_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("Email: 信箱格式錯誤");
	            }				
				
				String shop_phoneReg = "^[0-9]{10}$";
				String shop_phone = req.getParameter("shop_phone");
				if (shop_phone == null || shop_phone.trim().length() == 0) {
					errorMsgs.add("手機號碼: 請勿空白");
				} else if(!shop_phone.trim().matches(shop_phoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機號碼: 手機號碼格式錯誤");
	            }				

				Integer shop_price_level = new Integer(req.getParameter("shop_price_level").trim());
				
				
				String shop_opening_time = req.getParameter("shop_opening_time");
				String shop_website = req.getParameter("shop_website");
				
				// 主圖 
				String appPath = req.getServletContext().getRealPath("");
				String uploadFilePath = appPath + File.separator + 
						UPLOAD_DIR + File.separator + shop_tax_id + File.separator + "images";
		        File fileSaveDir = new File(uploadFilePath);
		        if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdirs();
		        }
		        System.out.println("上傳到資料夾到:" + fileSaveDir.getAbsolutePath());
		        
		        File[] listOfFiles = fileSaveDir.listFiles();
		        for (File file : listOfFiles) {
			        if (file.isFile()) {
			        	file.delete();
			        }
			    }
		        
		        Part imgPart = req.getPart("shop_main_img");
			    String shop_main_img = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
			    imgPart.write(uploadFilePath + File.separator + shop_main_img);
				// 圖片庫
				uploadFilePath = appPath + File.separator + 
						UPLOAD_DIR + File.separator + shop_tax_id + File.separator + "gallery";
		        fileSaveDir = new File(uploadFilePath);
		        if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdirs();
		        }		       
		        System.out.println("上傳到資料夾到:" + fileSaveDir.getAbsolutePath());		        
				List<Part> fileParts = req.getParts().stream().filter(part -> 
						"shop_gallery".equals(part.getName()) 
						&& part.getSize() > 0).collect(Collectors.toList());
				HashSet<String> hs = new HashSet<String>();
			    for (Part galleryPart : fileParts) {
			        String fileName = Paths.get(galleryPart.getSubmittedFileName()).getFileName().toString();
			        galleryPart.write(uploadFilePath + File.separator + fileName);
			    }
			    listOfFiles = fileSaveDir.listFiles();
			    for (File file : listOfFiles) {
			        if (file.isFile()) {
			        	hs.add(file.getName());
			        }
			    }
			    
			    String shop_gallery = hs.toString();
			    
				Date date = new Date();
				Timestamp shop_update_time = new Timestamp(date.getTime());
				Integer shop_total_view = new Integer(req.getParameter("shop_total_view").trim());
				Integer shop_reserv_status = new Integer(req.getParameter("shop_reserv_status").trim());				
				Integer member_id = new Integer(req.getParameter("member_id").trim());
				ShopVO shopVO = new ShopVO();
				shopVO.setShop_id(shop_id);
				shopVO.setMember_id(member_id);
				shopVO.setShop_tax_id(shop_tax_id);
				shopVO.setShop_name(shop_name);
				shopVO.setShop_zip_code(shop_zip_code);
				shopVO.setShop_city(shop_city);
				shopVO.setShop_address(shop_address);
				shopVO.setShop_latitude(shop_latitude);
				shopVO.setShop_longitude(shop_longitude);
				shopVO.setShop_description(shop_description);
				shopVO.setShop_tag(shop_tag);
				shopVO.setShop_rating(shop_rating);
				shopVO.setShop_rating_count(shop_rating_count);
				shopVO.setShop_rating_total(shop_rating_total);
				shopVO.setShop_email(shop_email);
				shopVO.setShop_phone(shop_phone);
				shopVO.setShop_price_level(shop_price_level);
				shopVO.setShop_opening_time(shop_opening_time);
				shopVO.setShop_website(shop_website);
				shopVO.setShop_main_img(shop_main_img);
				shopVO.setShop_gallery(shop_gallery);
				shopVO.setShop_update_time(shop_update_time);
				shopVO.setShop_total_view(shop_total_view);
				shopVO.setShop_reserv_status(shop_reserv_status);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shopVO", shopVO); // 含有輸入格式錯誤的shopVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/update_shop_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ShopService shopSvc = new ShopService();
				shopVO = shopSvc.updateShop(shop_id,member_id,shop_tax_id,
						shop_name,shop_zip_code,shop_city,shop_address,
						shop_latitude,shop_longitude,shop_description,
						shop_tag,shop_rating,shop_rating_count,shop_rating_total,
						shop_email,shop_phone,shop_price_level,shop_opening_time,
						shop_website,shop_main_img,shop_gallery,
						shop_update_time,shop_total_view,shop_reserv_status);
				shopVO = shopSvc.getOneShop(shop_id);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("shopVO", shopVO); // 資料庫update成功後,正確的的shopVO物件,存入req
				String url = "/shop/listOneShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneShop.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/shop/update_shop_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addShop.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String shop_tax_id = req.getParameter("shop_tax_id").trim();
				String shop_tax_idReg = "^[0-9]{8}$";
				if (shop_tax_id == null || shop_tax_id.trim().length() == 0) {
					errorMsgs.add("統一編號請勿空白");
				} else if(!shop_tax_id.trim().matches(shop_tax_idReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("統一編號: 統一編號格式錯誤，須為八個數字");
	            }
				
				String shop_name = req.getParameter("shop_name");
				if (shop_name == null || shop_name.trim().length() == 0) {
					errorMsgs.add("商家名稱: 請勿空白");
				}
				
				String shop_zip_code = req.getParameter("shop_zip_code");
				if (shop_zip_code == null || shop_zip_code.trim().length() == 0) {
					errorMsgs.add("郵遞區號: 請勿空白");
				}
				
				String shop_city = req.getParameter("shop_city");
				if (shop_city == null || shop_city.trim().length() == 0) {
					errorMsgs.add("縣市: 請勿空白");
				}
				
				String shop_address = req.getParameter("shop_address");
				if (shop_address == null || shop_address.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				}
				
				Double shop_latitude = null;
				try {
					shop_latitude = new Double(req.getParameter("shop_latitude").trim());
				} catch (NumberFormatException e) {
					shop_latitude = 0.0;
					errorMsgs.add("請填正確格式的緯度");
				}

				Double shop_longitude = null;
				try {
					shop_longitude = new Double(req.getParameter("shop_longitude").trim());
				} catch (NumberFormatException e) {
					shop_longitude = 0.0;
					errorMsgs.add("請填正確格式的經度");
				}
				
				String shop_description = req.getParameter("shop_description");
				String shop_tag = req.getParameter("shop_tag");
				
				Double shop_rating = null;
				try {
					shop_rating = new Double(req.getParameter("shop_rating").trim());
				} catch (NumberFormatException e) {
					shop_rating = 0.0;
					errorMsgs.add("請填正確格式的評價");
				}
				
				Integer shop_rating_count = new Integer(req.getParameter("shop_rating_count").trim());
				Integer shop_rating_total = new Integer(req.getParameter("shop_rating_total").trim());
						
				String shop_emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				String shop_email = req.getParameter("shop_email");
				if (shop_email == null || shop_email.trim().length() == 0) {
					errorMsgs.add("Email: 請勿空白");
				} else if(!shop_email.trim().matches(shop_emailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("Email: 信箱格式錯誤");
	            }				
				
				String shop_phoneReg = "^[0-9]{10}$";
				String shop_phone = req.getParameter("shop_phone");
				if (shop_phone == null || shop_phone.trim().length() == 0) {
					errorMsgs.add("手機號碼: 請勿空白");
				} else if(!shop_phone.trim().matches(shop_phoneReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("手機號碼: 手機號碼格式錯誤");
	            }				

				Integer shop_price_level =  new Integer(req.getParameter("shop_price_level").trim());
								
				String shop_opening_time = req.getParameter("shop_opening_time");
				String shop_website = req.getParameter("shop_website");
				
				// 主圖 
				String appPath = req.getServletContext().getRealPath("");
				String uploadFilePath = appPath + File.separator + 
						UPLOAD_DIR + File.separator + shop_tax_id + File.separator + "images";
		        File fileSaveDir = new File(uploadFilePath);
		        if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdirs();
		        }
		        System.out.println("上傳到資料夾到:" + fileSaveDir.getAbsolutePath());
		        
		        Part imgPart = req.getPart("shop_main_img");
			    String shop_main_img = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
			    imgPart.write(uploadFilePath + File.separator + shop_main_img);
				// 圖片庫
				uploadFilePath = appPath + File.separator + 
						UPLOAD_DIR + File.separator + shop_tax_id + File.separator + "gallery";
		        fileSaveDir = new File(uploadFilePath);
		        if (!fileSaveDir.exists()) {
		            fileSaveDir.mkdirs();
		        } 
		        System.out.println("上傳到資料夾到:" + fileSaveDir.getAbsolutePath());		        
				List<Part> fileParts = req.getParts().stream().filter(part -> 
						"shop_gallery".equals(part.getName()) 
						&& part.getSize() > 0).collect(Collectors.toList());
				HashSet<String> hs = new HashSet<String>();
				
			    for (Part galleryPart : fileParts) {
			        String fileName = Paths.get(galleryPart.getSubmittedFileName()).getFileName().toString();
			        galleryPart.write(uploadFilePath + File.separator + fileName);
			        hs.add(fileName);
			    }
			    String shop_gallery = hs.toString();
			    
				Date date = new Date();
				Timestamp shop_create_time = new Timestamp(date.getTime());
				Timestamp shop_update_time = new Timestamp(date.getTime());
				Integer shop_total_view = new Integer(req.getParameter("shop_total_view").trim());
				Integer shop_reserv_status = new Integer(req.getParameter("shop_reserv_status").trim());
				Integer member_id = new Integer(req.getParameter("member_id").trim());
				
				ShopVO shopVO = new ShopVO();
				shopVO.setMember_id(member_id);
				shopVO.setShop_tax_id(shop_tax_id);
				shopVO.setShop_name(shop_name);
				shopVO.setShop_zip_code(shop_zip_code);
				shopVO.setShop_city(shop_city);
				shopVO.setShop_address(shop_address);
				shopVO.setShop_latitude(shop_latitude);
				shopVO.setShop_longitude(shop_longitude);
				shopVO.setShop_description(shop_description);
				shopVO.setShop_tag(shop_tag);
				shopVO.setShop_rating(shop_rating);
				shopVO.setShop_rating_count(shop_rating_count);
				shopVO.setShop_rating_total(shop_rating_total);
				shopVO.setShop_email(shop_email);
				shopVO.setShop_phone(shop_phone);
				shopVO.setShop_price_level(shop_price_level);
				shopVO.setShop_opening_time(shop_opening_time);
				shopVO.setShop_website(shop_website);
				shopVO.setShop_main_img(shop_main_img);
				shopVO.setShop_gallery(shop_gallery);
				shopVO.setShop_create_time(shop_create_time);
				shopVO.setShop_update_time(shop_update_time);
				shopVO.setShop_total_view(shop_total_view);
				shopVO.setShop_reserv_status(shop_reserv_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("shopVO", shopVO); // 含有輸入格式錯誤的shopVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/shop/addShop.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ShopService shopSvc = new ShopService();
				shopVO = shopSvc.addShop(member_id,shop_tax_id,
						shop_name,shop_zip_code,shop_city,shop_address,
						shop_latitude,shop_longitude,shop_description,
						shop_tag,shop_rating,shop_rating_count,shop_rating_total,
						shop_email,shop_phone,shop_price_level,shop_opening_time,
						shop_website,shop_main_img,shop_gallery,shop_create_time,
						shop_update_time,shop_total_view,shop_reserv_status);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/shop/listAllShop.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllShop.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/shop/addShop.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // 來自listAllShop.jsp 或  /member/listShops_ByMember_id.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/shop/listAllShop.jsp】 或  【/member/listShops_ByMember_id.jsp】 或 【 /member/listAllMember.jsp】
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer shop_id = new Integer(req.getParameter("shop_id"));
				
				/***************************2.開始刪除資料***************************************/
				ShopService shopSvc = new ShopService();
				ShopVO shopVO = shopSvc.getOneShop(shop_id);
				shopSvc.deleteShop(shop_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
				MemberService memberSvc = new MemberService();
				if(requestURL.equals("/member/listShops_ByMember_id.jsp") || requestURL.equals("/member/listAllMember.jsp"))
					req.setAttribute("listShops_ByMember_id",memberSvc.GET_ONE_BY_MEMBER(shopVO.getMember_id())); // 資料庫取出的list物件,存入request
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}