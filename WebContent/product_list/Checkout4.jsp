<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , shopping.product"%>
<%@ page import="com.orders.model.*"%>

<%
  OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
%>
<html>
<head>
 <title>結帳</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/product_list/css/ShoppingCart.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/product_list/css/check_out4.css">
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
		<div class="process_3">
			<p>完成訂購</p>
			<i class="far fa-handshake"></i>
		</div>
		
<!-- 		<div class="check_order"> -->
<!-- 			<h6>親愛的Hank Wu您好，感謝您的訂購，您的訂單編號為：12</h6> -->
<!-- 			<div class="check_order_title"> -->
<!-- 				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;ORDER DETAIL 訂單明細</h1> -->
<!-- 			</div> -->
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					<th>商品編號</th> -->
<!-- 					<th>商品名稱</th> -->
<!-- 					<th>數量</th> -->
<!-- 					<th>價格</th> -->
<!-- 					<th>小計</th>			 -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td width="200px">12</td> -->
<!-- 					<td width="520px">我是商品名字</td> -->
<!-- 					<td width="150px">309</td> -->
<!-- 					<td width="150px">100</td> -->
<!-- 					<td width="150px">100</td> -->
<!-- 				</tr> -->
<!-- 			</table> -->
<!-- 			<div class="order_cost_amount"> -->
<!-- 				<table> -->
<!-- 					<tr> -->
<!-- 						<th width="150px">總金額</th> -->
<!-- 						<th width="150px">390</th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th width="150px">運費</th> -->
<!-- 						<th width="150px">80</th> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td width="150px">應付金額</td> -->
<!-- 						<td width="150px">390</td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</div>			 -->
<!-- 		</div> -->
		
		<div class="check_order">
		<h6>親愛的${ordersVO.orders_shipping_name}您好，感謝您的訂購，您的訂單編號為：${ordersVO.orders_no}</h6>
			<div class="check_order_title">
				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;Payment and recipient information 付款方式及收件人資料</h1>
			</div>
			
			<table>
				<tr>
					<th width="150px">訂單編號</th>
					<th>${ordersVO.orders_no}</th>			
				</tr>
				<tr>
					<th width="150px">付款方式</th>
					<th>使用積分付款 目前剩餘積分: 5000 GP</th>			
				</tr>
				<tr>
					<th width="150px">收件人</th>
					<th>${ordersVO.orders_shipping_name}</th>			
				</tr>
				<tr>
					<th width="150px">取貨地址</th>
					<th>${ordersVO.orders_shipping_address}</th>			
				</tr>
				<tr>
					<th width="150px">備註</th>
					<th>${ordersVO.orders_note}</th>			
				</tr>
			</table>
		
		<div class="finish_button">
			<input type="submit" value="完成" class="back" 
					onclick="location.href='<%=request.getContextPath()%>/product_category/product_category.do?product_category_no=1&action=see_category_product'">
			<input type="submit" value="擷取螢幕" class="back" 
					onclick="location.href='<%=request.getContextPath()%>/product_category/product_category.do?product_category_no=1&action=see_category_product'">
		</div>
		</div>					
</div>
</form>





	<script src="<%=request.getContextPath()%>/product_list/js/check_out.js"></script>
</body>
</html>