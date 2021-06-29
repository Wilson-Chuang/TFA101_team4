package com.product.controller;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.article.model.ArticleService;
import com.order_item.model.Order_itemVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.product.model.*;
import com.product_category.model.Product_categoryService;
import com.product_category.model.Product_categoryVO;

import shopping.product;
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

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("product_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入產品編號");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_list/product_homePage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer product_no = null;
				try {
					product_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("產品編號格式不正確");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_list/product_homePage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_list/product_homePage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO); 
				String url = "/product_list/product_info.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_list/product_homePage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer product_no = new Integer(req.getParameter("product_no"));
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("productVO", productVO);
				String url = "/product/update_product_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/listOneProduct.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("update".equals(action)) { 
			System.out.println("進來update");
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer product_no = new Integer(req.getParameter("product_no").trim());
				
				String product_name = req.getParameter("product_name").trim();
	
				String product_intro = req.getParameter("product_intro").trim();
				System.out.println(product_intro);
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

				
				Integer product_status = new Integer(req.getParameter("product_status").trim());
				
				Integer product_category_no = new Integer(req.getParameter("product_category_no").trim());
				
				
				ProductService productSvc = new ProductService();
				
				ProductVO productVO2 = productSvc.getOneProduct(product_no);
				
				String filename = productVO2.getProduct_img_name();
				
				
				byte[] data = null;
				
				if(!((req.getPart("product_img")).getSize()==0)) {
					//這邊如果有選擇檔案就會進入一般上傳,"filename"會被取代
					 Part file = req.getPart("product_img");
						
				     filename = file.getSubmittedFileName();		   
	    
				     String uploadPath = "C:/Users/Tibame_T14/Desktop/upload/"+filename;	     
				     
				     FileOutputStream fos = new FileOutputStream(uploadPath);
				     InputStream is = file.getInputStream();     
				     
				     data = new byte[is.available()];
				     is.read(data);
				     fos.write(data);
				     fos.close();    	
				}
				
				Integer product_discount_no = new Integer(req.getParameter("product_discount_no").trim());
				
				
				Integer product_discount_detail_rate = null;
				try {
					if(product_discount_no != 1) {
						product_discount_detail_rate = 0;
					}else {
						product_discount_detail_rate = new Integer(req.getParameter("product_discount_detail_rate").trim());
					}
				} catch (NumberFormatException e) {
					product_discount_detail_rate = 0;
					errorMsgs.add("商品折扣請填數字.");
				}
				
				String product_discount_detail_coupon = null;
				
				if(product_discount_no != 2) {
					product_discount_detail_coupon = "";
				}else {
					product_discount_detail_coupon = req.getParameter("product_discount_detail_coupon").trim();
				}
						
				
				Integer product_discount_detail_minus = null;
				try {
					
					if(product_discount_no != 2) {
						product_discount_detail_minus = 0;
					}else {
						product_discount_detail_minus = new Integer(req.getParameter("product_discount_detail_minus").trim());
					}
				} catch (NumberFormatException e) {
					product_discount_detail_minus = 0;
					errorMsgs.add("商品折價請填數字.");
				}
				
				Integer product_discount_detail_buy_count = null;
				try {
					if(product_discount_no != 3) {
						product_discount_detail_buy_count = 1;
					}else {
						product_discount_detail_buy_count = new Integer(req.getParameter("product_discount_detail_buy_count").trim());
					}				
				} catch (NumberFormatException e) {
					product_discount_detail_buy_count = 0;
					errorMsgs.add("需購數量請填數字.");
				}
				
				Integer product_discount_detail_get_count = null;
				try {
					if(product_discount_no != 3) {
						product_discount_detail_get_count = 0;
					}else {
						product_discount_detail_get_count = new Integer(req.getParameter("product_discount_detail_get_count").trim());
					}	
				} catch (NumberFormatException e) {
					product_discount_detail_get_count = 0;
					errorMsgs.add("贈送數量請填數字.");
				}
				
				
				
				
				ProductVO productVO = new ProductVO();
				
				productVO.setProduct_no(product_no);
				productVO.setProduct_name(product_name);
				productVO.setProduct_intro(product_intro);
				productVO.setProduct_point(product_point);
				productVO.setProduct_stock_quantity(product_stock_quantity);
				productVO.setProduct_img(data);
				productVO.setProduct_status(product_status);
				productVO.setProduct_category_no(product_category_no);
				productVO.setProduct_img_name(filename);
				productVO.setProduct_discount_no(product_discount_no);
				productVO.setProduct_discount_detail_rate(product_discount_detail_rate);
				productVO.setProduct_discount_detail_coupon(product_discount_detail_coupon);
				productVO.setProduct_discount_detail_minus(product_discount_detail_minus);
				productVO.setProduct_discount_detail_buy_count(product_discount_detail_buy_count);
				productVO.setProduct_discount_detail_get_count(product_discount_detail_get_count);
				

				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				/***************************2.開始修改資料*****************************************/
				productVO = productSvc.updateProduct(product_no, product_name, product_intro, product_point, product_stock_quantity,
										data,product_status, product_category_no, filename,product_discount_no,
										product_discount_detail_rate,product_discount_detail_coupon,
										product_discount_detail_minus, product_discount_detail_buy_count,
										product_discount_detail_get_count);
				
			
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("productVO", productVO);
				String url = "/product/listProd_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/update_product_input.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		
		
        if ("insert".equals(action)) {
			
			
			List<String> name_errorMsgs = new LinkedList<String>();
			req.setAttribute("name_errorMsgs", name_errorMsgs);
			
			List<String> point_errorMsgs = new LinkedList<String>();
			req.setAttribute("point_errorMsgs", point_errorMsgs);
			
			
			List<String> stock_quantity_errorMsgs = new LinkedList<String>();
			req.setAttribute("stock_quantity_errorMsgs", stock_quantity_errorMsgs);
			
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			


			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String product_name = req.getParameter("product_name").trim();
				if (product_name == null || product_name.trim().length() == 0) {
					name_errorMsgs.add("* 務必填寫商品名稱");
					}
			
				
				String product_intro = req.getParameter("product_intro").trim();

				Integer product_point = null;
				try {
					product_point = new Integer(req.getParameter("product_point").trim());
				} catch (NumberFormatException e) {
					product_point = 0;
					point_errorMsgs.add("* 價格請填數字");
				}

				Integer product_stock_quantity = null;
				try {
					product_stock_quantity = new Integer(req.getParameter("product_stock_quantity").trim());
				} catch (NumberFormatException e) {
					product_stock_quantity = 0;
					stock_quantity_errorMsgs.add("* 庫存請填數字");
				}
				
			     
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
				
				String product_img_name = imageFileName.trim();
				
				Integer product_discount_no = null;
				try {
					product_discount_no = new Integer(req.getParameter("product_discount_no").trim());
				} catch (NumberFormatException e) {
					product_discount_no = 0;
					errorMsgs.add("優惠類別請填數字.");
				}
				
				Integer product_discount_detail_rate = null;
				try {
					if(product_discount_no != 1) {
						product_discount_detail_rate = 0; //小心這裡是打0折哦,會變成送的
						
					}else {
						product_discount_detail_rate = new Integer(req.getParameter("product_discount_detail_rate").trim());
						System.out.println(product_discount_detail_rate);
					
					}
				} catch (NumberFormatException e) {
					product_discount_detail_rate = 0;
					errorMsgs.add("商品折扣請填數字.");
				}
				
				String product_discount_detail_coupon = null;
				
				if(product_discount_no != 2) {
					product_discount_detail_coupon = "";
				}else {
					product_discount_detail_coupon = req.getParameter("product_discount_detail_coupon").trim();
				}
						
				
				Integer product_discount_detail_minus = null;
				try {
					
					if(product_discount_no != 2) {
						product_discount_detail_minus = 0;
					}else {
						product_discount_detail_minus = new Integer(req.getParameter("product_discount_detail_minus").trim());
					}
				} catch (NumberFormatException e) {
					product_discount_detail_minus = 0;
					errorMsgs.add("商品折價請填數字.");
				}
				
				Integer product_discount_detail_buy_count = null;
				try {
					if(product_discount_no != 3) {
						product_discount_detail_buy_count = 1;
					}else {
						product_discount_detail_buy_count = new Integer(req.getParameter("product_discount_detail_buy_count").trim());
					}				
				} catch (NumberFormatException e) {
					product_discount_detail_buy_count = 0;
					errorMsgs.add("需購數量請填數字.");
				}
				
				Integer product_discount_detail_get_count = null;
				try {
					if(product_discount_no != 3) {
						product_discount_detail_get_count = 0;
					}else {
						product_discount_detail_get_count = new Integer(req.getParameter("product_discount_detail_get_count").trim());
					}	
				} catch (NumberFormatException e) {
					product_discount_detail_get_count = 0;
					errorMsgs.add("贈送數量請填數字.");
				}
				
				

				ProductVO productVO = new ProductVO();
				productVO.setProduct_name(product_name);
				productVO.setProduct_intro(product_intro);
				productVO.setProduct_point(product_point);
				productVO.setProduct_stock_quantity(product_stock_quantity);
				productVO.setProduct_img(data);
				productVO.setProduct_status(product_status);
				productVO.setProduct_category_no(product_category_no);
				productVO.setProduct_img_name(product_img_name);
				productVO.setProduct_discount_no(product_discount_no);
				productVO.setProduct_discount_detail_rate(product_discount_detail_rate);
				productVO.setProduct_discount_detail_coupon(product_discount_detail_coupon);
				productVO.setProduct_discount_detail_minus(product_discount_detail_minus);
				productVO.setProduct_discount_detail_buy_count(product_discount_detail_buy_count);
				productVO.setProduct_discount_detail_get_count(product_discount_detail_get_count);
				
				System.out.println("優惠編號: "+ product_discount_no + "折扣數值: " + product_discount_detail_rate +
						"庫碰: " + product_discount_detail_coupon
						+ "折價: " + product_discount_detail_minus + "買x" + product_discount_detail_buy_count 
						+ "送x" + product_discount_detail_get_count);
		
				
//				-----------------------------------------錯誤驗證------------------------------------------				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!name_errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!point_errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!stock_quantity_errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				
		
				
				/***************************2.開始新增資料***************************************/
				ProductService productSvc = new ProductService();
				productVO = productSvc.addProduct(product_name, product_intro, product_point, product_stock_quantity,
						data,product_status, product_category_no,product_img_name,product_discount_no,
						product_discount_detail_rate,product_discount_detail_coupon,
						product_discount_detail_minus,product_discount_detail_buy_count,
						product_discount_detail_get_count);
			
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/product/listProd_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}
        
        
        if ("view".equals(action)) { 
			System.out.println("進來product的view");
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer product_no = new Integer(req.getParameter("product_no"));
			
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				ProductVO productVO = productSvc.getOneProduct(product_no);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
		
				req.setAttribute("productVO", productVO);

				String url = "/product/product_listAll_view.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/listProd_back.jsp");
				failureView.forward(req, res);
			}
		}
		
        
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer product_no = new Integer(req.getParameter("product_no"));
				
				/***************************2.開始刪除資料***************************************/
				ProductService productSvc = new ProductService();
				productSvc.deleteProduct(product_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/product/listProd_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/listProd_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("search".equals(action)) { 
	
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String product_name = new String(req.getParameter("product_name"));
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				
				Set<ProductVO> set = productSvc.findByProductName(product_name);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("set", set);      

				String url = "/product/product_searchAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product/listProd_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		if ("search_front".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
		
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				String product_name = new String(req.getParameter("product_name"));
				/***************************2.開始查詢資料****************************************/
				ProductService productSvc = new ProductService();
				
				Set<ProductVO> set = productSvc.findByProductName(product_name);
				if (set.isEmpty()) {
					errorMsgs.add("查無符合條件的商品,請在試一次");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_list/product_search_list.jsp");
					failureView.forward(req, res);
					return;
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("set", set);      
				
				String url = "/product_list/product_search_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_list/product_homePage.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		  if ("update_on_status".equals(action)) { 
			
				List<String> errorMsgs = new LinkedList<String>();
		
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer product_no = new Integer(req.getParameter("product_no"));
					
					/***************************2.開始刪除資料***************************************/
					ProductService productSvc = new ProductService();
					productSvc.update_statusProduct(product_no);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/product/listProd_back.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
				
					errorMsgs.add("更新資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/listProd_back.jsp");
					failureView.forward(req, res);
				}
			}
		  
		  
		  if ("update_off_status".equals(action)) { 
		
				List<String> errorMsgs = new LinkedList<String>();
		
				req.setAttribute("errorMsgs", errorMsgs);
		
				try {
					/***************************1.接收請求參數***************************************/
					Integer product_no = new Integer(req.getParameter("product_no"));
					
					/***************************2.開始刪除資料***************************************/
					ProductService productSvc = new ProductService();
					productSvc.update_statusProduct2(product_no);
					
					/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
					String url = "/product/listProd_back.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
					successView.forward(req, res);
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
				
					errorMsgs.add("更新資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product/listProd_back.jsp");
					failureView.forward(req, res);
				}
			}
	}
}
