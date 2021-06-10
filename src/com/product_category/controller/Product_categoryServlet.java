package com.product_category.controller;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_category.model.Product_categoryService;
import com.product_category.model.Product_categoryVO;



public class Product_categoryServlet extends HttpServlet {

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
				String str = req.getParameter("product_category_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入系列編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_category/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer product_category_no = null;
				try {
					product_category_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("系列編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_category/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				Product_categoryVO product_categoryVO = product_categorySvc.getOneProduct_category(product_category_no);
				if (product_categoryVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/product_category/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_categoryVO", product_categoryVO); // 資料庫取出的empVO物件,存入req
				String url = "/product_category/listOneProduct_category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_category/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listProdCatg.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer product_category_no = new Integer(req.getParameter("product_category_no"));
				
				/***************************2.開始查詢資料****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				Product_categoryVO product_categoryVO = product_categorySvc.getOneProduct_category(product_category_no);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("product_categoryVO", product_categoryVO);         // 資料庫取出的empVO物件,存入req
				String url = "/product_category/update_Product_category.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_category/listProdCatg_back.jsp");
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
				Integer product_category_no = new Integer(req.getParameter("product_category_no").trim());
				
				String product_category_name = req.getParameter("product_category_name").trim();
//				String product_category_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
//				if (product_category_name == null || product_category_name.trim().length() == 0) {
//					errorMsgs.add("系列名稱: 請勿空白");
//				} else if(!product_category_name.trim().matches(product_category_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
//	            }
				
				Product_categoryVO product_categoryVO = new Product_categoryVO();
				
				product_categoryVO.setProduct_category_no(product_category_no);
				product_categoryVO.setProduct_category_name(product_category_name);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_categoryVO", product_categoryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/product_category/update_Product_category.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				/***************************2.開始修改資料*****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				product_categoryVO = product_categorySvc.updateProduct_category(product_category_no, product_category_name);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("product_categoryVO", product_categoryVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/product_category/listProdCatg_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_category/update_Product_category.jsp");
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
				String product_category_name = req.getParameter("product_category_name");
//				String product_category_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
//				if (product_category_name == null || product_category_name.trim().length() == 0) {
//					errorMsgs.add("系列名稱: 請勿空白");
//				} else if(!product_category_name.trim().matches(product_category_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
//	            }
						
				Product_categoryVO product_categoryVO = new Product_categoryVO();
				product_categoryVO.setProduct_category_name(product_category_name);

				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("product_categoryVO", product_categoryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/product_category/addProduct_category.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				product_categoryVO = product_categorySvc.addProduct_category(product_category_name);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/product_category/listProdCatg_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_category/addProduct_category.jsp");
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
				Integer product_category_no = new Integer(req.getParameter("product_category_no"));
				
				/***************************2.開始刪除資料***************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				product_categorySvc.deleteProduct_category(product_category_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/product_category/listProdCatg_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_category/listProdCatg_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("view".equals(action)) { // 來自listProdCatg.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer product_category_no = new Integer(req.getParameter("product_category_no"));
				/***************************2.開始查詢資料****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				
				ProductService productSvc = new ProductService();
				
				Set<ProductVO> set = product_categorySvc.getProductsByproduct_category_no(product_category_no);
				
				Product_categoryVO product_categoryVO = product_categorySvc.getOneProduct_category(product_category_no);
					
				Integer count = productSvc.getCountBycategory(product_category_no);


				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("set", set);         // 資料庫取出的empVO物件,存入req
				req.setAttribute("product_categoryVO", product_categoryVO);
				req.setAttribute("count", count);
				String url = "/product_category/listProdCatgView_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_category/listProdCatg_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("see_category_product".equals(action)) { // 來自listProdCatg.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer product_category_no = new Integer(req.getParameter("product_category_no"));
				/***************************2.開始查詢資料****************************************/
				Product_categoryService product_categorySvc = new Product_categoryService();
				
				ProductService productSvc = new ProductService();
				
				Set<ProductVO> set = product_categorySvc.getProductsByproduct_category_no(product_category_no);
				
				Product_categoryVO product_categoryVO = product_categorySvc.getOneProduct_category(product_category_no);
					
				Integer count = productSvc.getCountBycategory(product_category_no);


				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("set", set);         // 資料庫取出的empVO物件,存入req
				req.setAttribute("product_categoryVO", product_categoryVO);
				String url = "/product_list/product_list_bak.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/product_list/product_list_bak.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
