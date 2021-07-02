<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , shopping.product"%>
<%@ page import="com.orders.model.*"%>
<%@ page import="com.member.model.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
  OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");

%>

<%
  int getbuy = (int) request.getAttribute("getbuy");
  pageContext.setAttribute("getbuy", getbuy);
  
  int discount_minus = (int) request.getAttribute("discount_minus");
  pageContext.setAttribute("discount_minus", discount_minus);
%>

<%
	Set set = new LinkedHashSet();
	set.add(request.getAttribute("set"));
%>


<%
// 	MemberService memberSvc = new MemberService();
// 	MemberVO memberVO = memberSvc.GET_ONE_BY_ID(1);
// 	pageContext.setAttribute("memberVO", memberVO);

	MemberVO MemberVO2 = (MemberVO) session.getAttribute("login");
	MemberService memberSvc = new MemberService();
	MemberVO memberVO = memberSvc.GET_ONE_BY_ID(MemberVO2.getMember_id());
	pageContext.setAttribute("memberVO", memberVO);
%>

<jsp:useBean id="invoiceSvc" scope="page" class="com.invoice.model.InvoiceService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="paymentSvc" scope="page" class="com.payment.model.PaymentService" />

<html>
<head>
 <title>結帳</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/product_list/css/ShoppingCart.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/product_list/css/check_out4.css">
 <script src="<%=request.getContextPath()%>/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
 <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
 </head>
 
<body>
<%-- <form name="checkoutForm" action="<%=request.getContextPath()%>/product_list/shopping.html" method="POST"> --%>
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
			<h6>親愛的${ordersVO.orders_shipping_name}您好，感謝您的訂購，您的訂單編號為：${ordersVO.orders_no}</h6>
			<h6>訂單確認信已為您寄出，請至信箱確認!</h6>
			<div class="check_order_title">
				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;ORDER DETAIL 訂單明細</h1>
			</div>
			<table>
				<tr>
					<th></th>
					<th>商品名稱</th>
					<th>數量</th>
					<th>價格</th>
					<th>小計</th>			
				</tr>
				<c:forEach var="order_itemVO" items="${set}">
				<tr>
					<td width="200px">
					<c:forEach var="productVO" items="${productSvc.all}">
	                    	<c:if test="${productVO.product_no==order_itemVO.product_no}">
		                   	<a href="http://localhost:8081/Team4_MVC_Hank/product/product.do?product_no=${productVO.product_no}&action=getOne_For_Display">
		                   	<img src="/upload/${productVO.product_img_name}"></a>
	                    	</c:if>
	               </c:forEach>  			
					</td>
					<td width="520px">
					<c:forEach var="productVO" items="${productSvc.all}">
	                    	<c:if test="${productVO.product_no==order_itemVO.product_no}">
					<a href="http://localhost:8081/Team4_MVC_Hank/product/product.do?product_no=${productVO.product_no}&action=getOne_For_Display">
					${order_itemVO.product_name}
					</a>
							</c:if>
	               </c:forEach>  	
					</td>
					<td width="150px">${order_itemVO.order_item_amount}</td>
					<td width="150px">${order_itemVO.order_item_point}</td>
					<td width="150px">${order_itemVO.order_item_point*order_itemVO.order_item_amount}</td>
				</tr>
				</c:forEach>
			</table>
			<div class="order_cost_amount">
				<table>
					<tr>
						<th width="150px">總金額</th>
						<th width="150px">${ordersVO.orders_total_point+discount_minus}</th>
					</tr>
					<tr>
						<th width="150px">優惠碼折價</th>
						<th width="150px">-${discount_minus}</th>
					</tr>
					<tr>
						<td width="150px" style="font-weight:bold; color:rgb(185, 1, 1);">訂單金額</td>
						<td width="150px" style="font-weight:bold; color:rgb(185, 1, 1);">${ordersVO.orders_total_point}</td>
					</tr>
				</table>
			</div>			
		</div>
		
		<div class="check_order">
			<div class="check_order_title">
				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;Payment and recipient information 付款方式及收件人資料</h1>
			</div>
			
			<table>
				<tr>
					<th width="150px">訂單編號</th>
					<th>${ordersVO.orders_no}</th>			
				</tr>
				<tr>
					<th width="150px">日期</th>
					<th>
					<fmt:formatDate value="${ordersVO.orders_date}" pattern="yyyy/MM/dd hh:mm:ss"/>
					</th>			
				</tr>
				<tr>
					<th width="150px">付款方式</th>
					<th>
					<c:forEach var="paymentVO" items="${paymentSvc.all}">
	                    <c:if test="${ordersVO.payment_no==paymentVO.payment_no}">
		                   	${paymentVO.payment_name}
	                    </c:if>
	                </c:forEach>
	                
	                	<c:if test="${ordersVO.payment_no==1}">
		                   	【目前剩餘積分: ${memberVO.member_point} GP】
	                    </c:if>	
					</th>			
				</tr>
				<tr>
					<th width="150px">收件人</th>
					<th>${ordersVO.orders_shipping_name}</th>			
				</tr>
				<tr>
					<th width="150px">收件地址</th>
					<th>${ordersVO.orders_shipping_address}</th>			
				</tr>
				<tr>
					<th width="150px">發票類型</th>		
						<c:forEach var="invoiceVO" items="${invoiceSvc.all}">
		                    		<c:if test="${ordersVO.invoice_no==invoiceVO.invoice_no}">
			                   			<th>${invoiceVO.invoice_name}</th>
		                    		</c:if>
		                </c:forEach>   		
				</tr>
				<tr>
					<th width="150px">統一編號</th>
						<c:if test="${ordersVO.invoice_no==2}">
							<th>${ordersVO.orders_invoice_tax_number}</th>
						</c:if>
					<th>-</th>
				</tr>
				<tr>
					<th width="150px">備註</th>
					<th>${ordersVO.orders_note}</th>			
				</tr>
			</table>
		
		<div class="finish_button">
			<input type="button" value="完成" class="back" 
					onclick="location.href='<%=request.getContextPath()%>/product_list/product_homePage.jsp'">
		<form name="checkoutForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
			method="POST">	
			<input type="hidden" name="action"  value="screen_shot">
			<input type="submit" value="擷取螢幕" class="next">
		</form>
		</div>
		</div>					
</div>
<!-- </form> -->



	<script src="<%=request.getContextPath()%>/product_list/js/check_out.js"></script>
</body>
</html>