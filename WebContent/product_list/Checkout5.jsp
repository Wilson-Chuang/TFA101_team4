<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , shopping.product"%>
<html>
<head>
 <title>結帳</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/product_list/css/ShoppingCart.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/product_list/css/check_out5.css">
 <script src="<%=request.getContextPath()%>/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
 <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
 </head>
<body>


<div class="check_out">
		<div class="process_1">
			<p>確認購買明細</p>
			<i class="far fa-check-circle"></i>
		</div>
		<div class="process_3">
			<p>填寫收件資料與付款方式</p>
			<i class="far fa-edit"></i>
		</div>
		<div class="process_3">
			<p>完成訂購</p>
			<i class="far fa-handshake"></i>
		</div>
		
		<div class="check_order">
			<h6>親愛的Hank Wu您好，感謝您的訂購!<br>
				訂單資訊已寄送至您的信箱</h6>
			<i class="far fa-check-circle" id="complete"></i>			
		</div>

		<div class="finish_button">
			<input type="submit" value="完成" class="back" 
					onclick="location.href='<%=request.getContextPath()%>/product_category/product_category.do?product_category_no=1&action=see_category_product'">
			<input type="submit" value="擷取螢幕" class="back" 
					onclick="location.href='<%=request.getContextPath()%>/product_category/product_category.do?product_category_no=1&action=see_category_product'">
		</div>





	<script src="<%=request.getContextPath()%>/product_list/js/check_out.js"></script>
</body>
</html>