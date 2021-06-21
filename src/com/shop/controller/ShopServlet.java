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
	private static final String UPLOAD_DIR = "uploads" + File.separator + "shop";
	private ShopService shopSvc;
	
	@Override
	public void init() throws ServletException {
		shopSvc = new ShopService();
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
// 接收資料
		String tmp_shop_id = "";
		if(req.getParameter("shop_id") != null) {
			tmp_shop_id = req.getParameter("shop_id").trim();
		}
		String tmp_member_id = "";
		if(req.getParameter("member_id") != null) {
			tmp_member_id = req.getParameter("member_id").trim();
		}
		String shop_tax_id = "";
		if(req.getParameter("shop_tax_id") != null) {
			shop_tax_id = req.getParameter("shop_tax_id").trim();
		}
		String shop_name = "";
		if(req.getParameter("shop_name") != null) {
			shop_name = req.getParameter("shop_name").trim();
		}
		String shop_zip_code = "";
		if(req.getParameter("shop_zip_code") != null) {
			shop_zip_code = req.getParameter("shop_zip_code").trim();
		}
		String shop_city = "";
		if(req.getParameter("shop_city") != null) {
			shop_city = req.getParameter("shop_city").trim();
		}
		String shop_address = "";
		if(req.getParameter("shop_address") != null) {
			shop_address = req.getParameter("shop_address").trim();
		}
		String tmp_shop_latitude = "";
		if(req.getParameter("shop_latitude") != null) {
			tmp_shop_latitude = req.getParameter("shop_latitude").trim();
		}
		String tmp_shop_longitude = "";
		if(req.getParameter("shop_longitude") != null) {
			tmp_shop_longitude = req.getParameter("shop_longitude").trim();
		}
		String shop_description = "";
		if(req.getParameter("shop_description") != null) {
			shop_description = req.getParameter("shop_description").trim();
		}
		String shop_tag = "";
		if(req.getParameter("shop_tag") != null) {
			shop_tag = req.getParameter("shop_tag").trim();
		}
		String tmp_shop_rating = "";
		if(req.getParameter("shop_rating") != null) {
			tmp_shop_rating = req.getParameter("shop_rating").trim();
		}
		String tmp_shop_rating_count = "";
		if(req.getParameter("shop_rating_count") != null) {
			tmp_shop_rating_count = req.getParameter("shop_rating_count").trim();
		}
		String tmp_shop_rating_total = "";
		if(req.getParameter("shop_rating_total") != null) {
			tmp_shop_rating_total = req.getParameter("shop_rating_total").trim();
		}
		String shop_email = "";
		if(req.getParameter("shop_email") != null) {
			shop_email = req.getParameter("shop_email").trim();
		}
		String shop_phone = "";
		if(req.getParameter("shop_phone") != null) {
			shop_phone = req.getParameter("shop_phone").trim();
		}
		String tmp_shop_price_level = "";
		if(req.getParameter("shop_price_level") != null) {
			tmp_shop_price_level = req.getParameter("shop_price_level").trim();
		}
		String shop_opening_time = "";
		if(req.getParameter("shop_opening_time") != null) {
			shop_opening_time = req.getParameter("shop_opening_time").trim();
		}
		String shop_website = "";
		if(req.getParameter("shop_website") != null) {
			shop_website = req.getParameter("shop_website").trim();
		}
		String tmp_shop_total_view = "";
		if(req.getParameter("shop_total_view") != null) {
			tmp_shop_total_view = req.getParameter("shop_total_view").trim();
		}
		String tmp_shop_reserv_status = "";
		if(req.getParameter("shop_reserv_status") != null) {
			tmp_shop_reserv_status = req.getParameter("shop_reserv_status").trim();
		}
		String action = "";
		if(req.getParameter("action") != null) {
			action = req.getParameter("action").trim();
		}
		
// 驗證資料
		Map<String, String> errorMsgs = new HashMap<String, String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		if(action != null) {
			if(action.equals("getOne_For_Display") || 
				action.equals("getOne_For_Update") || 
				action.equals("update") ||
				action.equals("insert") || 
				action.equals("delete")) {
				if(tmp_shop_id == null || tmp_shop_id.length() == 0) {
					errorMsgs.put("shop_id", "商家編號請勿空白");
				}
			}
			if(action.equals("update") ||
				action.equals("insert")) {
				if(shop_tax_id == null || shop_tax_id.length() == 0) {
					errorMsgs.put("shop_tax_id", "統一編號請勿空白");
				}
				if (shop_name == null || shop_name.length() == 0) {
					errorMsgs.put("shop_name", "商家名稱請勿空白");
				}
				if (shop_zip_code == null || shop_zip_code.length() == 0) {
					errorMsgs.put("shop_zip_code", "郵遞區號請勿空白");
				}
				if (shop_address == null || shop_address.length() == 0) {
					errorMsgs.put("shop_address", "地址請勿空白");
				}
				if (tmp_shop_latitude == null || tmp_shop_latitude.length() == 0) {
					errorMsgs.put("shop_latitude", "緯度請勿空白");
				}
				if (tmp_shop_longitude == null || tmp_shop_longitude.length() == 0) {
					errorMsgs.put("shop_longitude", "經度請勿空白");
				}
				if (shop_email == null || shop_email.length() == 0) {
					errorMsgs.put("shop_email", "信箱請勿空白");
				}				
			}
		}
		
		Integer shop_id = 0;
		if(tmp_shop_id != null && tmp_shop_id.length() != 0) {
			try {
				shop_id = Integer.parseInt(tmp_shop_id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgs.put("shop_id", "商家編號格式不正確");
			}
		}		
				
		if(shop_tax_id != null && shop_tax_id.length() != 0) {
			String shop_tax_idReg = "^[0-9]{8}$";
			if(!shop_tax_id.matches(shop_tax_idReg)) {
				errorMsgs.put("shop_tax_id", "統一編號格式錯誤，須為八個數字");
	        }
			ShopVO target_tax = shopSvc.findShop_tax_id(shop_tax_id);
			if(shop_id != target_tax.getShop_id() && 
					shop_tax_id == target_tax.getShop_tax_id()) {
				errorMsgs.put("shop_tax_id", "統一編號已被使用");
			}
		}
		
		Double shop_latitude = 0.0;
		if(tmp_shop_latitude!=null && tmp_shop_latitude.length()!=0) {
			try {
				shop_latitude = Double.parseDouble(tmp_shop_latitude);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgs.put("shop_latitude", "請填正確格式的緯度");
			}
		}		

		Double shop_longitude = 0.0;
		if(tmp_shop_longitude!=null && tmp_shop_longitude.length()!=0) {
			try {
				shop_longitude = Double.parseDouble(tmp_shop_longitude);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgs.put("shop_longitude", "請填正確格式的經度");
			}			
		}		
		
		Double shop_rating = 0.0;
		if(tmp_shop_longitude!=null && tmp_shop_longitude.length()!=0) {
			try {
				shop_rating = Double.parseDouble(tmp_shop_rating);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgs.put("shop_rating", "請填正確格式的評價");
			}
		}
		
		Integer shop_rating_count = 0;
		if(tmp_shop_rating_count!=null && tmp_shop_rating_count.length()!=0) {
			try {
				shop_rating_count = Integer.parseInt(tmp_shop_rating_count);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgs.put("shop_rating_count", "請填正確格式的評價總數");
			}
		}
		
		Integer shop_rating_total = 0;
		if(tmp_shop_rating_total!=null && tmp_shop_rating_total.length()!=0) {
			try {
				shop_rating_total = Integer.parseInt(tmp_shop_rating_total);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgs.put("shop_rating_total", "請填正確格式的評價總和");
			}
		}
		
		if(shop_email != null && shop_email.length() != 0) {
			String shop_emailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
			if(!shop_email.matches(shop_emailReg)) {
				errorMsgs.put("shop_email", "信箱格式錯誤");
	        }
		}
		
		if(shop_phone != null && shop_phone.length() != 0) {
			String shop_phoneReg = "^[0-9]{10}$";
			if(!shop_phone.trim().matches(shop_phoneReg)) {
				errorMsgs.put("shop_phone", "手機號碼格式錯誤");
	        }
		}

		Integer shop_price_level = 0;
		if(tmp_shop_price_level!=null && tmp_shop_price_level.length()!=0) {
			try {
				shop_price_level = Integer.parseInt(tmp_shop_price_level);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgs.put("shop_price_level", "請填正確格式的均銷等級");
			}
		}
		
		String shop_main_img = "";
		String shop_gallery = "";
		Date date = new Date();
		Timestamp shop_create_time = new Timestamp(date.getTime());
		Timestamp shop_update_time = new Timestamp(date.getTime());
		
	    Integer shop_total_view = 0;
		if(tmp_shop_total_view!=null && tmp_shop_total_view.length()!=0) {
			try {
				shop_total_view = Integer.parseInt(tmp_shop_total_view);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgs.put("shop_total_view", "請填正確格式的累計瀏覽數");
			}
		}
	    
		Integer shop_reserv_status = 0;
		if(tmp_shop_reserv_status!=null && tmp_shop_reserv_status.length()!=0) {
			try {
				shop_reserv_status = Integer.parseInt(tmp_shop_reserv_status);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgs.put("shop_reserv_status", "請填正確格式的累計瀏覽數");
			}
		}
		
		Integer member_id = 0;
		if(tmp_member_id!=null && tmp_member_id.length()!=0) {
			try {
				member_id = Integer.parseInt(tmp_member_id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				errorMsgs.put("member_id", "請填正確格式的會員編號");
			}
		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			try {
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ShopVO shopVO = shopSvc.getOneShop(shop_id);
				if (shopVO == null) {
					errorMsgs.put("shopVO", "查無資料");
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
				errorMsgs.put("shopVO", "無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllShop.jsp 或  /dept/listShops_ByDeptno.jsp 的請求

			try {
				/***************************2.開始查詢資料****************************************/
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
		
		
		if ("update".equals(action)) {
			try {
				// 預設值
				ShopVO oldVO = shopSvc.getOneShop(shop_id);
				shop_main_img = oldVO.getShop_main_img();
				shop_gallery = oldVO.getShop_gallery();
				shop_create_time = oldVO.getShop_create_time();
				shop_update_time = oldVO.getShop_update_time();
				
				// 統一編號更動檢查				
				if(shop_tax_id != oldVO.getShop_tax_id()) {
					String appPath = req.getServletContext().getRealPath("");
					String uploadFilePath = appPath + File.separator + 
							UPLOAD_DIR + File.separator;
					File sourceFilename = new File(uploadFilePath + oldVO.getShop_tax_id());
					File destFilename = new File(uploadFilePath + shop_tax_id);
					if (sourceFilename.renameTo(destFilename)) {
					    System.out.println(oldVO.getShop_tax_id() +"資料夾更名到" + shop_tax_id);
					} else {
					    System.out.println("資料夾名稱更名失敗");
					}
				}
				
				// 主圖更新
				Part imgPart = req.getPart("shop_main_img");	
				
				if(imgPart.getSize() > 0) {
					String appPath = req.getServletContext().getRealPath("");
					String uploadFilePath = appPath + File.separator + 
							UPLOAD_DIR + File.separator + shop_tax_id + File.separator + "images";
					File fileSaveDir = new File(uploadFilePath);
					File[] listOfFiles = fileSaveDir.listFiles();
					for (File file : listOfFiles) {
				        if (file.isFile()) {
				        	file.delete();
				        }
				    }
			        if (!fileSaveDir.exists()) {
			            fileSaveDir.mkdirs();
			        }
			        System.out.println("上傳到資料夾:" + fileSaveDir.getAbsolutePath());			        
				    shop_main_img = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
				    imgPart.write(uploadFilePath + File.separator + shop_main_img);
				}
				
				// 圖片庫更新
				List<Part> fileParts = req.getParts().stream().filter(part -> 
										"shop_gallery".equals(part.getName()) 
										&& part.getSize() > 0).collect(Collectors.toList());
			    
			    if(!fileParts.isEmpty()) {
			    	String appPath = req.getServletContext().getRealPath("");
			    	String uploadFilePath = appPath + File.separator + 
							UPLOAD_DIR + File.separator + shop_tax_id + File.separator + "gallery";
			    	File fileSaveDir = new File(uploadFilePath);
			        if (!fileSaveDir.exists()) {
			            fileSaveDir.mkdirs();
			        }
			        System.out.println("上傳到資料夾:" + fileSaveDir.getAbsolutePath());		
					HashSet<String> hs = new HashSet<String>();
				    for (Part galleryPart : fileParts) {
				        String fileName = Paths.get(galleryPart.getSubmittedFileName()).getFileName().toString();
				        galleryPart.write(uploadFilePath + File.separator + fileName);
				    }
				    File[] listOfFiles = fileSaveDir.listFiles();
				    for (File file : listOfFiles) {
				        if (file.isFile()) {
				        	hs.add(file.getName());
				        }
				    }
				    shop_gallery = hs.toString();
			    }
				
				// 更新時間
				shop_update_time = new Timestamp(date.getTime());
				
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
				errorMsgs.put("shopVO", "修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/shop/update_shop_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addShop.jsp的請求  
			try {
				// 主圖更新
				Part imgPart = req.getPart("shop_main_img");	
				
				if(imgPart.getSize() > 0) {
					String appPath = req.getServletContext().getRealPath("");
					String uploadFilePath = appPath + File.separator + 
							UPLOAD_DIR + File.separator + shop_tax_id + File.separator + "images";
					File fileSaveDir = new File(uploadFilePath);
					File[] listOfFiles = fileSaveDir.listFiles();
					for (File file : listOfFiles) {
				        if (file.isFile()) {
				        	file.delete();
				        }
				    }
			        if (!fileSaveDir.exists()) {
			            fileSaveDir.mkdirs();
			        }
			        System.out.println("上傳到資料夾:" + fileSaveDir.getAbsolutePath());			        
				    shop_main_img = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
				    imgPart.write(uploadFilePath + File.separator + shop_main_img);
				}
				
				// 圖片庫更新
				List<Part> fileParts = req.getParts().stream().filter(part -> 
										"shop_gallery".equals(part.getName()) 
										&& part.getSize() > 0).collect(Collectors.toList());
			    
			    if(!fileParts.isEmpty()) {
			    	String appPath = req.getServletContext().getRealPath("");
			    	String uploadFilePath = appPath + File.separator + 
							UPLOAD_DIR + File.separator + shop_tax_id + File.separator + "gallery";
			    	File fileSaveDir = new File(uploadFilePath);
			        if (!fileSaveDir.exists()) {
			            fileSaveDir.mkdirs();
			        }
			        System.out.println("上傳到資料夾:" + fileSaveDir.getAbsolutePath());		
					HashSet<String> hs = new HashSet<String>();
				    for (Part galleryPart : fileParts) {
				        String fileName = Paths.get(galleryPart.getSubmittedFileName()).getFileName().toString();
				        galleryPart.write(uploadFilePath + File.separator + fileName);
				    }
				    File[] listOfFiles = fileSaveDir.listFiles();
				    for (File file : listOfFiles) {
				        if (file.isFile()) {
				        	hs.add(file.getName());
				        }
				    }
				    shop_gallery = hs.toString();
			    }
				
				// 建立時間與更新時間
				shop_create_time = new Timestamp(date.getTime());
				shop_update_time = new Timestamp(date.getTime());
				
				
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
				errorMsgs.put("ShopVO", e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/shop/addShop.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // 來自listAllShop.jsp 或  /member/listShops_ByMember_id.jsp的請求
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/shop/listAllShop.jsp】 或  【/member/listShops_ByMember_id.jsp】 或 【 /member/listAllMember.jsp】
	
			try {
				/***************************2.開始刪除資料***************************************/
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
				errorMsgs.put("shopVO", "刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}