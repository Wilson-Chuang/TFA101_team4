<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , shopping.product"%>
<html>
<head>
 <title>結帳</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/product_list/css/ShoppingCart.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/product_list/css/check_out2.css">
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
			<p>配送與付款方式</p>
			<i class="fas fa-truck-moving"></i>
		</div>
		<div class="process_2">
			<p>填寫收件資料</p>
			<i class="far fa-edit"></i>
		</div>
		<div class="process_2">
			<p>完成訂購</p>
			<i class="far fa-handshake"></i>
		</div>
		
<!-- 		此處做額外現金付款方式(尚未處理) -->
		<div class="check_order">
			<div class="check_order_title">
				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;SHIPPING & PAYMENT 配送與付款方式</h1>
			</div>
			
			<div class="ckbutton">
			  <label>
			    <input type="radio" class="ckbutton_checkbox" name="example" checked />
			     	使用積分付款 目前剩餘積分: 5000 GP
			  </label>
			</div>
			
		</div>

		<div class="check_order">
			<div class="check_order_title">
				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;CHECK YOUR ORDER 確認購買明細</h1>
			</div>
			<table>	
				<tr>
					<th width="200px"></th>
					<th width="520px">商品名稱</th>
					<th width="80px">數量</th>
					<th width="150px">價格</th>
					<th width="150px">小計</th>
					<th></th>
				</tr>
				
	<%  @SuppressWarnings("unchecked")
		Vector<product> buylist = (Vector<product>) session.getAttribute("shoppingcart");
		String amount2 =  (String) request.getAttribute("amount2");
		String subQuantity2 =  (String) request.getAttribute("subQuantity2");
	%>
	<%	for (int i = 0; i < buylist.size(); i++) {
			product order = buylist.get(i);
			String name = order.getName();
			Integer price = order.getPrice();
			Integer quantity = order.getQuantity();
			Integer subtotal = order.getQuantity()*order.getPrice();
			String img_name = order.getImg_name();
	%>
				<tr>
					<td width="200px"><img src="/upload/<%=img_name%>"></td>
					<td width="520px"><%=name%></td>
					<td width="80px"><%=quantity%></td>
					<td width="150px"><%=price%></td>
					<td width="150px"><%=subtotal%></td>
					<td>
						 <i class="far fa-trash-alt"></i>
					     <form name="deleteForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
                        			method="POST" target="nm_iframe4">
                            <input type="hidden" name="action"  value="DELETE">
                            <input type="hidden" name="del" value="<%= i %>">
                            <input type="submit" value="X" class="delete_button" id="delete_button">
                        </form>
						<iframe id="nm_iframe4" name="nm_iframe4" style="display:none;" ></iframe>
					
					</td>
				</tr>
	<%
		}
	%>
		</table>
		</div>
		<div class="total_cost">
			<p>小計金額(共<%=subQuantity2%>件)</p>

	
			<div class="total_cost_box">GP  <%=amount2%></div>
		</div>
	</div>
		<div class="finish_button">
<!-- 			<input type="submit" value="下一步" class="next"  -->
<%-- 					onclick="location.href='<%=request.getContextPath()%>/product_list/Checkout3.jsp'"> --%>
<!-- 			<input type="button" value="上一步" class="back"  -->
<%-- 					onclick="location.href='<%=request.getContextPath()%>/product_list/Checkout.jsp'"> --%>
			<form name="checkoutForm" action="<%=request.getContextPath()%>/product_list/shopping.html" method="POST">
			<input type="hidden" name="action"  value="CHECKOUT3">
			<input type="submit" value="下一步" class="next">
			</form>
		</div>




<script>
	$(document).ready(function(){
	  $(".delete_button").click(function(){	  
		  
		  setTimeout(function(){
		  location.reload(false);
		  },500);
		  
		  
      });
    });
	
    window.onload = function() {
  	  document.getElementById("nm_iframe4").onload = function() {
   		  setTimeout(function(){
  		  	location.reload(true);  
   		  },500);
  	  }
  };
</script>	

	<script src="<%=request.getContextPath()%>/product_list/js/check_out.js"></script>
</body>
</html>