package shoppingServlet;
import java.util.*;

import java.io.*;
import java.sql.Timestamp;

import javax.servlet.*;
import javax.servlet.http.*;

import com.order_item.model.Order_itemService;
import com.order_item.model.Order_itemVO;
import com.orders.model.OrdersDAO_interface;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductVO;
import com.product_category.model.Product_categoryService;
import com.product_category.model.Product_categoryVO;

import shopping.product;

public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		
		@SuppressWarnings("unchecked")
		List<product> buylist = (Vector<product>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");


			// 刪除購物車中的書籍
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);
				
				session.setAttribute("shoppingcart", buylist);
				String url = "/product_list/product_list.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
			// 新增書籍至購物車中
			else if (action.equals("ADD")) {
				// 取得後來新增的書籍
				product aproduct = getProduct(req);
		
				if (buylist == null) {
					buylist = new Vector<product>();
					buylist.add(aproduct);
				} else {
					if (buylist.contains(aproduct)) {
						product innerproduct = buylist.get(buylist.indexOf(aproduct));
						innerproduct.setQuantity(innerproduct.getQuantity() + aproduct.getQuantity());
					} else {
						buylist.add(aproduct);
					}
				}
				session.setAttribute("shoppingcart", buylist);
				String url = "/product_list/product_list.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}

	

		// 結帳，計算購物車書籍價錢總數
			if (action.equals("CHECKOUT")) {
			
			int total = 0;
			int subquantity = 0;
			for (int i = 0; i < buylist.size(); i++) {
				product order = buylist.get(i);
				Integer price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
				subquantity += quantity;
			}
			
			String subQuantity = String.valueOf(subquantity);
			String amount = String.valueOf(total);
			req.setAttribute("subQuantity", subQuantity);
			req.setAttribute("amount", amount);
			
			String url = "/product_list/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
		
		
		//來自Checkout.jsp的請求
		if ("CHECKOUT2".equals(action)) {
		
			int total = 0;
			int subquantity = 0;
			for (int i = 0; i < buylist.size(); i++) {
				product order = buylist.get(i);
				Integer price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
				subquantity += quantity;
			}
			
			String subQuantity2 = String.valueOf(subquantity);
			String amount2 = String.valueOf(total);
			req.setAttribute("subQuantity2", subQuantity2);
			req.setAttribute("amount2", amount2);
			String url2 = "/product_list/Checkout2.jsp";
			
			RequestDispatcher rd2 = req.getRequestDispatcher(url2);
			rd2.forward(req, res);
			
		}
		
		
		//來自Checkout.jsp的請求
		if ("CHECKOUT3".equals(action)) {
			
			int total = 0;
			int subquantity = 0;
			for (int i = 0; i < buylist.size(); i++) {
				product order = buylist.get(i);
				Integer price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
				subquantity += quantity;
			}
					
			String subQuantity2 = String.valueOf(subquantity);
			String amount2 = String.valueOf(total);
			
			req.setAttribute("subQuantity2", subQuantity2);
			req.setAttribute("amount2", amount2);
			String url2 = "/product_list/Checkout3.jsp";
			RequestDispatcher rd2 = req.getRequestDispatcher(url2);
			rd2.forward(req, res);
		}
			
		if("CHECKOUT4".equals(action)) {
			System.out.println("進來checkout4");
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				Integer orders_no = new Integer(req.getParameter("orders_no"));
				
				String orders_shipping_name = req.getParameter("orders_shipping_name").trim();
				
//				String orders_shipping_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
//				if (orders_shipping_name == null || orders_shipping_name.trim().length() == 0) {
//					errorMsgs.add("收件人姓名: 請勿空白");
//				} else if(!orders_shipping_name.trim().matches(orders_shipping_nameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("收件人姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
//	            }
				
				String orders_shipping_phone = req.getParameter("orders_shipping_phone").trim();
				if (orders_shipping_phone == null || orders_shipping_phone.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				}	
		
				Integer orders_shipping_zip = null;
				try {
					orders_shipping_zip = new Integer(req.getParameter("orders_shipping_zip").trim());
				} catch (NumberFormatException e) {
					orders_shipping_zip = 0;
					errorMsgs.add("郵遞區號請填數字.");
				}
			
				String orders_shipping_address = req.getParameter("orders_shipping_address").trim();
				if (orders_shipping_address == null || orders_shipping_address.trim().length() == 0) {
					errorMsgs.add("收件地址請勿空白");
				}	
			
				String orders_note = req.getParameter("orders_shipping_note").trim();
				
				int total = 0;
				int subquantity = 0;
				String product_name = null;
				int order_item_price = 0;
				int order_item_QTY = 0;
				
				List<Order_itemVO> testList = new ArrayList<Order_itemVO>();
				
				
				for (int i = 0; i < buylist.size(); i++) {
					
					product order = buylist.get(i);
					Integer price = order.getPrice();
					Integer quantity = order.getQuantity();
					String product_name1 = order.getName();
					total += (price * quantity);
					subquantity += quantity;
					product_name = product_name1;
					order_item_price = price;
					order_item_QTY = quantity;	
					System.out.println(order);
					
					Order_itemVO order_item = new Order_itemVO();
					order_item.setProduct_name(order.getName());
					order_item.setOrder_item_amount(order.getQuantity());
					order_item.setOrder_item_point(order.getPrice());
					
					testList.add(order_item);
					System.out.println(order_item);
					
				}
				
				int amount2 = total;
				
				OrdersVO ordersVO = new OrdersVO();
				
				ordersVO.setMember_no(1);//member_no
				ordersVO.setOrders_date(new java.sql.Timestamp(new java.util.Date().getTime()));
				ordersVO.setOrders_total_point(amount2);
				ordersVO.setOrders_shipping_name(orders_shipping_name);
				ordersVO.setOrders_shipping_phone(orders_shipping_phone);
				ordersVO.setOrders_shipping_zip(orders_shipping_zip);
				ordersVO.setOrders_shipping_address(orders_shipping_address);
				ordersVO.setOrders_note(orders_note);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordersVO", ordersVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/product_list/Checkout3.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				OrdersService ordersSvc = new OrdersService();
				ordersSvc.insertWithOrder_item(ordersVO,testList);
				OrdersVO ordersVO2 = ordersSvc.getOneOrders(orders_no);
				System.out.println(ordersVO2);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				req.setAttribute("ordersVO", ordersVO2);
				String url = "/product_list/Checkout4.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);		
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_category/product_category.do?product_category_no=1&action=see_category_product");
				failureView.forward(req, res);
			}
		}
	}
		

		
		
	private product getProduct(HttpServletRequest req) {

		String quantity = req.getParameter("quantity");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String img_name = req.getParameter("img_name");
	
		product product = new product();

		product.setName(name);
		product.setPrice(new Integer(price));
		product.setQuantity((new Integer(quantity)).intValue());
		product.setImg_name(img_name);
		return product;
	}
}