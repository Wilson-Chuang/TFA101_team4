<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , shopping.product"%>
<%@ page import="com.orders.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
%>

<%
	OrdersService ordersSvc = new OrdersService();
	List<OrdersVO> list = ordersSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
 <title>結帳</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/product_list/css/ShoppingCart.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/product_list/css/check_out3.css">
 <script src="<%=request.getContextPath()%>/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
 <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
 </head>
<body>

<form name="checkoutForm" action="<%=request.getContextPath()%>/product_list/shopping.html" method="POST">

<div class="check_out">
		<div class="process_1">
			<p>確認購買明細</p>
			<i class="far fa-check-circle"></i>
		</div>
		<div class="process_3">
			<p>配送與付款方式</p>
			<i class="fas fa-truck-moving"></i>
		</div>
    
		<div class="process_3">
			<p>填寫收件資料</p>
			<i class="far fa-edit"></i>
		</div>
		<div class="process_2">
			<p>完成訂購</p>
			<i class="far fa-handshake"></i>
		</div>
		

		
		<div class="check_order">
			<div class="check_order_title">
				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;DELIVERY INFORMATION 請填寫收件資料</h1>
			</div>
	
			<div class="form">
				收件姓名:<input type="TEXT" name="orders_shipping_name" size="20"
							value="<%=(ordersVO == null) ? "" : ordersVO.getOrders_shipping_name()%>" />
			</div>

			<div class="form">
				手機號碼:<input type="TEXT" name="orders_shipping_phone" size="20"
							value="<%=(ordersVO == null) ? "" : ordersVO.getOrders_shipping_phone()%>" />
			</div>

			<div class="form">
				郵遞區號:<input type="TEXT" name="orders_shipping_zip" size="6"
							value="<%=(ordersVO == null) ? "" : ordersVO.getOrders_shipping_zip()%>" />
			</div>

			<div class="form">
				收件地址:<input type="TEXT" name="orders_shipping_address" size="40"
							value="<%=(ordersVO == null) ? "" : ordersVO.getOrders_shipping_address()%>" />
			</div>

			<div class="form">
				備註: <textarea name="orders_shipping_note" rows="3" cols="60"></textarea>
			</div>
			
			
		</div>

<!-- 		<div class="check_order"> -->
<!-- 			<div class="check_order_title"> -->
<!-- 				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;RECEIPT 請選擇發票格式</h1> -->
<!-- 			</div> -->
			<div style="height: 200px;"></div>
<!-- 		</div>	 -->
		
		
					
	</div>	
	
	
		<div class="finish_button">
			<input type="button" value="上一步" class="back" 
					onclick="location.href='<%=request.getContextPath()%>/product_list/Checkout2.jsp'">
					
			<input type="submit" value="確定付款" class="next" style="background-color:black; color:white;">		
			
			<input type="hidden" name="action"  value="CHECKOUT4">
		</div>



</form>

	<script src="<%=request.getContextPath()%>/product_list/js/check_out.js"></script>
</body>
</html>