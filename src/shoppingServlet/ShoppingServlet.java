package shoppingServlet;
import java.util.*;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.http.*;

import com.credit_card.model.Credit_cardService;
import com.order_item.model.Order_itemService;
import com.order_item.model.Order_itemVO;
import com.orders.model.OrdersDAO_interface;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.product.model.ProductDAO_interface;
import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.product_category.model.Product_categoryService;
import com.product_category.model.Product_categoryVO;
import com.credit_card.model.Credit_cardVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import shopping.product;
import testdatasource.MailService;;

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
		
		//來自Checkout.jsp的請求
		if ("CHECKOUT".equals(action)) {
			
			String product_discount_detail_coupon = req.getParameter("product_discount_detail_coupon");
			System.out.println("輸入的酷碰券號碼:" + product_discount_detail_coupon);
			
			ProductService productSvc = new ProductService();
			
			int total = 0;
			int subquantity = 0;
			int minus = 0;
			
			for (int i = 0; i < buylist.size(); i++) {
				product order = buylist.get(i);
				Integer price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
				subquantity += (quantity+(order.getQuantity()-order.getQuantity()%order.getProduct_discount_detail_buy_count())*order.getProduct_discount_detail_buy_times_get());
				
				System.out.println("購物車產品編號:"+order.getProduct_no());
				
				ProductVO productVO = productSvc.getOneProduct(order.getProduct_no());
				System.out.println("購物車產品酷碰號碼:" + productVO.getProduct_discount_detail_coupon());
				System.out.println("購物車產品設定酷碰折價:" + productVO.getProduct_discount_detail_minus());
				
				if(product_discount_detail_coupon.equals(productVO.getProduct_discount_detail_coupon())){
					System.out.println("進來equals");
					minus = productVO.getProduct_discount_detail_minus();
				}else if(!product_discount_detail_coupon.equals(productVO.getProduct_discount_detail_coupon())){
					System.out.println("進來unequals");
					continue;
				}
			}
			
			System.out.println(minus);
			session.setAttribute("discount_minus", minus);
			
			
			String subQuantity2 = String.valueOf(subquantity);
			String amount1 = String.valueOf(total);
			String amount2 = String.valueOf(total-minus);
			req.setAttribute("subQuantity2", subQuantity2);
			req.setAttribute("amount1", amount1);
			req.setAttribute("amount2", amount2);
			req.setAttribute("minus",minus);
			String url2 = "/product_list/Checkout2.jsp";
			
			RequestDispatcher rd2 = req.getRequestDispatcher(url2);
			rd2.forward(req, res);
			
		}
		
		
		//來自Checkout.jsp的請求
		if ("CHECKOUT3".equals(action)) {
//			int minus = Integer.parseInt(req.getParameter("minus"));
			int discount_minus = (int)session.getAttribute("discount_minus");
			System.out.println("checkout3的減價是否有傳到" + discount_minus);
			int total = 0;
			int subquantity = 0;
			for (int i = 0; i < buylist.size(); i++) {
				product order = buylist.get(i);
				Integer price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
				subquantity += (quantity+(order.getQuantity()-order.getQuantity()%order.getProduct_discount_detail_buy_count())*order.getProduct_discount_detail_buy_times_get());
			}
					System.out.println(subquantity);
			String subQuantity2 = String.valueOf(subquantity);
			String amount2 = String.valueOf(total);
			
			
			req.setAttribute("minus", discount_minus);
			req.setAttribute("subQuantity2", subQuantity2);
			System.out.println(subQuantity2);
			req.setAttribute("amount2", amount2);
			System.out.println(amount2);
			String url2 = "/product_list/Checkout3.jsp";
			RequestDispatcher rd2 = req.getRequestDispatcher(url2);
			rd2.forward(req, res);
		}
			
		if("CHECKOUT4".equals(action)) {
//			int minus = Integer.parseInt(req.getParameter("minus"));
			int discount_minus = (int)session.getAttribute("discount_minus");
			System.out.println("checkout4的減價是否有傳到" + discount_minus);
			
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			List<String> name_errorMsgs = new LinkedList<String>();
		
			req.setAttribute("name_errorMsgs", name_errorMsgs);
			
			List<String> phone_errorMsgs = new LinkedList<String>();
			
			req.setAttribute("phone_errorMsgs", phone_errorMsgs);
			
			List<String> address_errorMsgs = new LinkedList<String>();
			
			req.setAttribute("address_errorMsgs", address_errorMsgs);
			
			List<String> invoice_tax_number_errorMsgs = new LinkedList<String>();
			
			req.setAttribute("invoice_tax_number_errorMsgs", invoice_tax_number_errorMsgs);
			
			
			
			List<String> credit_card_number_errorMsgs = new LinkedList<String>();
			
			req.setAttribute("credit_card_number_errorMsgs", credit_card_number_errorMsgs);
			
			List<String> credit_card_cvv_errorMsgs = new LinkedList<String>();
			
			req.setAttribute("credit_card_cvv_errorMsgs", credit_card_cvv_errorMsgs);
			
			List<String> credit_card_name_errorMsgs = new LinkedList<String>();
			
			req.setAttribute("credit_card_name_errorMsgs", credit_card_name_errorMsgs);
			
			List<String> credit_card_phone_errorMsgs = new LinkedList<String>();
			
			req.setAttribute("credit_card_phone_errorMsgs", credit_card_phone_errorMsgs);
			
			List<String> credit_card_address_errorMsgs = new LinkedList<String>();
			
			req.setAttribute("credit_card_address_errorMsgs", credit_card_address_errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				
				
				
				String orders_shipping_name = req.getParameter("orders_shipping_name").trim();
				if (orders_shipping_name == null || orders_shipping_name.trim().length() == 0) {
					name_errorMsgs.add("* 收件人姓名: 請勿空白");
				} 
				
				String orders_shipping_phone = req.getParameter("orders_shipping_phone").trim();
				if (orders_shipping_phone == null || orders_shipping_phone.trim().length() == 0) {
					phone_errorMsgs.add("* 手機號碼請勿空白");
				}	
			
				String orders_shipping_address = 
						req.getParameter("city") + req.getParameter("county") + req.getParameter("address");
	
			
				String orders_note = req.getParameter("orders_shipping_note").trim();	
				
				Integer payment_no = new Integer(req.getParameter("payment_no").trim());
			
				Integer invoice_no = new Integer(req.getParameter("invoice_no").trim());			
				
				Integer orders_invoice_tax_number = 0;
				
				if(invoice_no == 1) {
					orders_invoice_tax_number =  0;
				}else {
					try {

					orders_invoice_tax_number =  new Integer(req.getParameter("orders_invoice_tax_number").trim());
					
					} catch (NumberFormatException e) {
						orders_invoice_tax_number = 0;
						invoice_tax_number_errorMsgs.add("* 請填正確的統一編號格式");
					}
					
				}
				
//				----------------------------------------------------------------------------------------
				
				Integer credit_card_number = 0;
				Integer credit_card_month =0;
				Integer credit_card_year =0;
				Integer credit_card_cvv =0;
				String credit_card_name = null;
				java.sql.Date credit_card_birthday = null;
				String credit_card_phone = null;
				String credit_card_address = null;
				
				
				if(payment_no == 3) {
					
				try {
					credit_card_number = new Integer(req.getParameter("credit_card_number").trim());
				} catch (NumberFormatException e) {
					credit_card_number = 0;
					credit_card_number_errorMsgs.add("* 請填入正確卡號");
				}
				
				
				credit_card_month =new Integer(req.getParameter("credit_card_month").trim());
		
				
				credit_card_year = new Integer(req.getParameter("credit_card_year").trim());
			
				try {
					credit_card_cvv = new Integer(req.getParameter("credit_card_cvv").trim());
				} catch (NumberFormatException e) {
					credit_card_cvv = 0;
					credit_card_cvv_errorMsgs.add("* 請填入正確末三碼");
				}
				
				credit_card_name = req.getParameter("credit_card_name").trim();
				if (credit_card_name == null || credit_card_name.trim().length() == 0) {
					credit_card_name_errorMsgs.add("* 卡片持有人姓名請勿空白");
				}	
				
				credit_card_birthday = java.sql.Date.valueOf(req.getParameter("credit_card_birthday").trim());	
		
				
				credit_card_phone = req.getParameter("credit_card_phone").trim();
				if (credit_card_phone == null || credit_card_phone.trim().length() == 0) {
					credit_card_phone_errorMsgs.add("* 手機號碼請勿空白");
				}	
				
				credit_card_address = req.getParameter("city1") + req.getParameter("county1") + req.getParameter("address1");
			
				
				
				}
				
				int total = 0;
				int subquantity = 0;
				String product_name = null;
				int order_item_price = 0;
				int order_item_QTY = 0;
				int getbuy = 0;
				
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
					getbuy =(int)((order.getQuantity()-order.getQuantity()%order.getProduct_discount_detail_buy_count())*order.getProduct_discount_detail_buy_times_get());
					
					
					Order_itemVO order_item = new Order_itemVO();
					order_item.setProduct_name(order.getName());
					order_item.setOrder_item_amount(order.getQuantity());
					order_item.setOrder_item_point(order.getPrice());
					order_item.setProduct_no(order.getProduct_no());
					testList.add(order_item);
					
					
				}
				
				int amount2 = total - discount_minus;
				System.out.println("折價:" + discount_minus);
				System.out.println("總金額等於:" + amount2);
				OrdersVO ordersVO = new OrdersVO();

				ordersVO.setMember_no(1);//member_no
				ordersVO.setOrders_date(new java.sql.Timestamp(new java.util.Date().getTime()));
				ordersVO.setOrders_total_point(amount2);
				ordersVO.setOrders_shipping_name(orders_shipping_name);
				ordersVO.setOrders_shipping_phone(orders_shipping_phone);
				ordersVO.setOrders_shipping_address(orders_shipping_address);
				ordersVO.setOrders_note(orders_note);
				ordersVO.setPayment_no(payment_no);
				ordersVO.setInvoice_no(invoice_no);
				ordersVO.setOrders_invoice_tax_number(orders_invoice_tax_number);
				
				
				Credit_cardVO credit_cardVO = new Credit_cardVO();
				
				credit_cardVO.setCredit_card_number(credit_card_number);
				credit_cardVO.setCredit_card_month(credit_card_month);
				credit_cardVO.setCredit_card_year(credit_card_year);
				credit_cardVO.setCredit_card_cvv(credit_card_cvv);
				credit_cardVO.setCredit_card_name(credit_card_name);
				credit_cardVO.setCredit_card_bithday(credit_card_birthday);
				credit_cardVO.setCredit_card_phone(credit_card_phone);
				credit_cardVO.setCredit_card_address(credit_card_address);
				
				
				
				
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("ordersVO", ordersVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product_list/Checkout3.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				if (!name_errorMsgs.isEmpty()) {
					req.setAttribute("ordersVO", ordersVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product_list/Checkout3.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!phone_errorMsgs.isEmpty()) {
					req.setAttribute("ordersVO", ordersVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product_list/Checkout3.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!address_errorMsgs.isEmpty()) {
					req.setAttribute("ordersVO", ordersVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product_list/Checkout3.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!invoice_tax_number_errorMsgs.isEmpty()) {
					req.setAttribute("ordersVO", ordersVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product_list/Checkout3.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!credit_card_cvv_errorMsgs.isEmpty()) {
					req.setAttribute("ordersVO", ordersVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product_list/Checkout3.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!credit_card_name_errorMsgs.isEmpty()) {
					req.setAttribute("ordersVO", ordersVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product_list/Checkout3.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				if (!credit_card_phone_errorMsgs.isEmpty()) {
					req.setAttribute("ordersVO", ordersVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product_list/Checkout3.jsp");
					failureView.forward(req, res);
					return;
				}
				
				if (!credit_card_address_errorMsgs.isEmpty()) {
					req.setAttribute("ordersVO", ordersVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/product_list/Checkout3.jsp");
					failureView.forward(req, res);
					return;
				}
				

				
				/***************************2.開始新增資料***************************************/
				OrdersService ordersSvc = new OrdersService();
				
				Credit_cardService credit_cardSvc = new Credit_cardService();
				
				MemberService memberSvc = new MemberService();
				
				ordersSvc.insertWithOrder_item(ordersVO,testList);	
				credit_cardVO = credit_cardSvc.addCredit_card(credit_card_number, credit_card_cvv, credit_card_month, credit_card_year, credit_card_name, 
						credit_card_birthday, credit_card_phone, credit_card_address);

				Integer orders_no = ordersVO.getOrders_no();
				
				OrdersVO ordersVO2 = ordersSvc.getOneOrders(orders_no);		
				
				MemberVO memberVO = memberSvc.GET_ONE_BY_ID(1);
				
				Integer member_point = memberVO.getMember_point();
						
				MemberVO memberVO2 = memberSvc.point_update(1,(member_point - amount2));
				
				Set<Order_itemVO> set = ordersSvc.getOrder_Item_ByOrders_no(orders_no);	
				
//				訂單完成寄信
				String to = "guidefooood@gmail.com";
			      
			    String subject = "Guide 好食 - 訂單確認";
			      
			    String ch_name = memberVO.getMember_name();
			    
			    Integer order_id = orders_no;
			    
			    String messageText = 
			    		
			    "親愛的" + ch_name + " 您好！ " + "\n" + "\n"
			    
			   	+ "感謝您在Guide好食上的購物，以下是您的訂單編號:" 
			    
			    + "\n" + "\n" +
			    
			    order_id
			    
			    + "\n" + "\n"
			    
			    + "若對商品有任何問題，請使用此編號向客服詢問!" + "\n" + "\n"+ "再次感謝您的光臨！"; 
			      //信件mail
			    MailService mailService = new MailService();
			    mailService.sendMail(to, subject, messageText);
				
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
			    req.setAttribute("discount_minus", discount_minus);
			    req.setAttribute("getbuy", getbuy);
				req.setAttribute("set", set); 
				req.setAttribute("ordersVO", ordersVO2);
				req.setAttribute("memberVO", memberVO2);
				String url = "/product_list/Checkout4.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				session.removeAttribute("shoppingcart");
				System.out.println("session remove成功");
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product_list/product_homePage.jsp");
				failureView.forward(req, res);
			
			}
		}
		
		
		
		//來自Checkout.jsp的請求
		if ("screen_shot".equals(action)) {
			
			 try {
		        	
		    		Date currentdate = new Date();
		    		
		    		String screenshotfilename = currentdate.toString().replace(" ","-").replace(":","-");
		        	
		            Robot robot=new Robot();
		            //根據指定的區域抓取屏幕的指定區域，1300是長度，800是寬度。
		            BufferedImage bi=robot.createScreenCapture(new Rectangle(1920,1080));
		            //把抓取到的內容寫到一個jpg文件中
//		            ImageIO.write(bi, "jpg", new File("C:\\Users\\Tibame_T14\\Desktop\\screenshot\\" + screenshotfilename+".png"));
		            ByteArrayOutputStream out =new ByteArrayOutputStream();
		            ImageIO.write(bi,"png",out);//png 为要保存的图片格式
		            byte[] barray = out.toByteArray();
		            out.close();
		            
		            res.setHeader("Content-Type","application/octet-stream");

		            res.setHeader("Content-Disposition","attachment;filename=screen-shot.png");

		            res.getOutputStream().write(barray);
		       
	
		        } catch (AWTException e) {
		            e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			 		
				} 
			}
		

		
	private product getProduct(HttpServletRequest req) {
		
		String product_no = req.getParameter("product_no");
		String product_discount_no = req.getParameter("product_discount_no");
		String quantity = req.getParameter("quantity");
		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String img_name = req.getParameter("img_name");
		String product_discount_detail_buy_count = req.getParameter("product_discount_detail_buy_count");
		String product_discount_detail_get_count = req.getParameter("product_discount_detail_get_count");
		
		
		
		System.out.println("買"+product_discount_detail_buy_count);
		System.out.println("送"+product_discount_detail_get_count);
		
		
		
		product product = new product();

		product.setName(name);
		product.setProduct_no(new Integer(product_no));
		product.setProduct_discount_no(new Integer(product_discount_no));
		product.setPrice(new Integer((int) Math.round((new Double(price)))));
		product.setQuantity((new Integer(quantity)).intValue());
		product.setImg_name(img_name);
		product.setProduct_discount_detail_buy_count(new Integer(product_discount_detail_buy_count));
		product.setProduct_discount_detail_get_count(new Integer(product_discount_detail_get_count));
		product.setProduct_discount_detail_buy_times_get(new Double(product_discount_detail_get_count)/new Integer(product_discount_detail_buy_count));
		return product;
	}
}