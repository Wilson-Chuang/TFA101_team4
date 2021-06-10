package com.product.controller;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.product.model.*;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ProductServlet extends HttpServlet {

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
				String str = req.getParameter("product_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入產品編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer product_no = null;
				try {
					product_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("產品編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
				String url = "/product/listOneProduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer product_no = new Integer(req.getParameter("product_no"));
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);         // 資料庫取出的empVO物件,存入req
				String url = "/product/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/listOneProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer product_no = new Integer(req.getParameter("product_no").trim());
				String product_name = req.getParameter("product_name").trim();
//				String product_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
//				if (product_name == null || product_name.trim().length() == 0) {
//					errorMsgs.add("商品名稱: 請勿空白");
//				} else if(!product_name.trim().matches(product_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
//	            }
//				
				String product_intro = req.getParameter("product_intro").trim();
//				if (product_intro == null || product_intro.trim().length() == 0) {
//					errorMsgs.add("商品介紹請勿空白");
//				}	

				Integer product_point = null;
				try {
					product_point = new Integer(req.getParameter("product_point").trim());
				} catch (NumberFormatException e) {
					product_point = 0;
					errorMsgs.add("商品價格請填數字.");
				}

				Integer product_stock_quantity = null;
				try {
					product_stock_quantity = new Integer(req.getParameter("product_stock_quantity").trim());
				} catch (NumberFormatException e) {
					product_stock_quantity = 0;
					errorMsgs.add("商品庫存請填數字.");
				}
				
				//圖片還沒寫
				 Part file = req.getPart("product_img");
				
			     String imageFileName = file.getSubmittedFileName();
			   
    
			     String uploadPath = "C:/Users/Tibame_T14/Desktop/upload/"+imageFileName;
			    
			     
			     FileOutputStream fos = new FileOutputStream(uploadPath);
			     InputStream is = file.getInputStream();     
			     
			     byte[] data = new byte[is.available()];
			     is.read(data);
			     fos.write(data);
			     fos.close();    	
				
				Integer product_status = new Integer(req.getParameter("product_status").trim());
				
				Integer product_category_no = new Integer(req.getParameter("product_category_no").trim());
				
				String product_img_name = file.getSubmittedFileName().trim();
			

				ProductVO productVO = new ProductVO();
				
				productVO.setProduct_no(product_no);
				productVO.setProduct_name(product_name);
				productVO.setProduct_intro(product_intro);
				productVO.setProduct_point(product_point);
				productVO.setProduct_stock_quantity(product_stock_quantity);
				productVO.setProduct_img(data);
				productVO.setProduct_status(product_status);
				productVO.setProduct_category_no(product_category_no);
				productVO.setProduct_img_name(product_img_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.updateProduct(product_no, product_name, product_intro, product_point, product_stock_quantity,data,product_status, product_category_no, product_img_name);
		
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/product/listProd_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/update_product_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		
		
        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String product_name = req.getParameter("product_name").trim();
				String product_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (product_name == null || product_name.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if(!product_name.trim().matches(product_nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
	            }
				System.out.println("檢查完參數了");
				
				String product_intro = req.getParameter("product_intro").trim();
				if (product_intro == null || product_intro.trim().length() == 0) {
					errorMsgs.add("商品介紹請勿空白");
				}	

				Integer product_point = null;
				try {
					product_point = new Integer(req.getParameter("product_point").trim());
				} catch (NumberFormatException e) {
					product_point = 0;
					errorMsgs.add("商品價格請填數字.");
				}

				Integer product_stock_quantity = null;
				try {
					product_stock_quantity = new Integer(req.getParameter("product_stock_quantity").trim());
				} catch (NumberFormatException e) {
					product_stock_quantity = 0;
					errorMsgs.add("商品庫存請填數字.");
				}
				
				//圖片還沒完成	
			     
				 Part file = req.getPart("product_img");
				
			     String imageFileName = file.getSubmittedFileName();
			
     
			     String uploadPath = "C:/Users/Tibame_T14/Desktop/upload/"+imageFileName;
			    
			     
			     FileOutputStream fos = new FileOutputStream(uploadPath);
			     InputStream is = file.getInputStream();     
			     
			     byte[] data = new byte[is.available()];
			     is.read(data);
			     fos.write(data);
			     fos.close();    		  
					
			     
			     
				Integer product_status = null;
				try {
					product_status = new Integer(req.getParameter("product_status").trim());
				} catch (NumberFormatException e) {
					product_status = 0;
					errorMsgs.add("商品狀態請填數字.");
				}
				
				Integer product_category_no = null;
				try {
					product_category_no = new Integer(req.getParameter("product_category_no").trim());
				} catch (NumberFormatException e) {
					product_category_no = 0;
					errorMsgs.add("商品類別請填數字.");
				}
				
				String product_img_name = imageFileName.trim();;
			
				ProductVO productVO = new ProductVO();
				productVO.setProduct_name(product_name);
				productVO.setProduct_intro(product_intro);
				productVO.setProduct_point(product_point);
				productVO.setProduct_stock_quantity(product_stock_quantity);
				productVO.setProduct_img(data);
				productVO.setProduct_status(product_status);
				productVO.setProduct_category_no(product_category_no);
				productVO.setProduct_img_name(product_img_name);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.addProduct(product_name, product_intro, product_point, product_stock_quantity,data,product_status, product_category_no,product_img_name);
			
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/product/listProd_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer product_no = new Integer(req.getParameter("product_no"));
				
				/***************************2.開始刪除資料***************************************/
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(product_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/product/listProd_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/listProd_back.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
