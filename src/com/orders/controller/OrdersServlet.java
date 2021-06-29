package com.orders.controller;

import java.io.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.order_item.model.Order_itemService;
import com.order_item.model.Order_itemVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_category.model.Product_categoryService;
import com.product_category.model.Product_categoryVO;



public class OrdersServlet extends HttpServlet {

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
				String str = req.getParameter("orders_no");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入系列編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/orders/orders_listAll.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer orders_no = null;
				try {
					orders_no = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/orders/orders_listAll.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				OrdersService ordersSvc = new OrdersService();
				OrdersVO ordersVO = ordersSvc.getOneOrders(orders_no);
				if (ordersVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/orders/orders_listAll.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("ordersVO", ordersVO); // 資料庫取出的empVO物件,存入req
				String url = "/orders/orders_listOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/orders/orders_listAll.jsp");
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
				Integer orders_no = new Integer(req.getParameter("orders_no"));
				
				/***************************2.開始刪除資料***************************************/
				OrdersService ordersSvc = new OrdersService();
				ordersSvc.deleteOrders(orders_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/orders/orders_listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/orders/orders_listAll.jsp");
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
				Integer orders_no = new Integer(req.getParameter("orders_no"));
				
				/***************************2.開始查詢資料****************************************/
				OrdersService ordersSvc = new OrdersService();
				
				Set<Order_itemVO> set = ordersSvc.getOrder_Item_ByOrders_no(orders_no);
			
				OrdersVO ordersVO = ordersSvc.getOneOrders(orders_no);

				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("set", set);         // 資料庫取出的empVO物件,存入req
				req.setAttribute("ordersVO", ordersVO);

				String url = "/orders/orders_listAll_view.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/orders/orders_listAll.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}
