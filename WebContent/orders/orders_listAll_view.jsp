<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	Set set = new LinkedHashSet();
	set.add(request.getAttribute("set"));
%>

<jsp:useBean id="paymentSvc" scope="page" class="com.payment.model.PaymentService" />
<jsp:useBean id="invoiceSvc" scope="page" class="com.invoice.model.InvoiceService" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<title>訂單管理(後台)</title>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/orders/css/orders_listAll_view.css">
<script src="${pageContext.request.contextPath}/orders/vendors/jquery/jquery-3.6.0.min.js"></script>

</head>

<body>
	<div class="main">
		<div class="listProd_content">
			<div class="product_title">
				<h1>訂單編號: ${ordersVO.orders_no}</h1>
				<button class="button1" onclick="location.href='${pageContext.request.contextPath}/orders/orders_listAll.jsp'">
					返回
				</button>
				<button class="button2">
					<i class="far fa-trash-alt"></i>&nbsp;&nbsp;刪除訂單
				</button>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/orders/orders.do">
			    	<input type="submit" value="移除" onclick="javascript:return del();">
			     	<input type="hidden" name="orders_no"  value="${ordersVO.orders_no}">
			     	<input type="hidden" name="action" value="delete">
			    </FORM>
			</div>
		
			<table>
					<tr>
						<th colspan="5" style="text-align:center;">訂單資料</th>
					</tr>	
					<tr>
						<th width="30px;" style="background-color:white;"></th>
						<th width="120px;" style="background-color:white;">訂單編號</th>
						<th width="120px" style="background-color:white;">訂單總金額</th>				
						<th width="250px;" style="background-color:white;">下單時間</th>
						<th width="120px" style="background-color:white;">付款方式</th>
					</tr>
							
					<tr>
						<td width="30px;"></td>
						<td width="120px;">${ordersVO.orders_no}</td>					
						<td width="120px">$ ${ordersVO.orders_total_point}</td>
						<td width="250px;">
							<fmt:formatDate value="${ordersVO.orders_date}" pattern="yyyy/MM/dd hh:mm:ss"/>
						</td>
						
						<td width="120px">
							<c:forEach var="paymentVO" items="${paymentSvc.all}">
	                    		<c:if test="${ordersVO.payment_no==paymentVO.payment_no}">
		                   			${paymentVO.payment_name}
	                    		</c:if>
	                		</c:forEach>   
						</td>				
					</tr>
					
					<tr>
						<th width="30px;" style="background-color:white;"></th>
						
						<th width="150px" style="background-color:white;">發票類型</th>				
						<th width="200px;" style="background-color:white;">統一編號</th>
						<th width="" style="background-color:white;">備註</th>
						
					</tr>
					
					<tr>
						<td width="30px;"></td>
										
						<td width="150px">
							<c:forEach var="invoiceVO" items="${invoiceSvc.all}">
	                    		<c:if test="${ordersVO.invoice_no==invoiceVO.invoice_no}">
		                   			${invoiceVO.invoice_name}
	                    		</c:if>
	                		</c:forEach>   
						</td>
						<td width="200px;">${ordersVO.orders_invoice_tax_number}</td>
						<td width="">${ordersVO.orders_note}</td>		
						
					</tr>
			</table>
			<table>
					<tr>
						<th colspan="5" style="text-align:center;">訂單明細</th>
					</tr>	
					<tr>
						<th width="120px" style="background-color:white;"></th>
				
						<th width="375px;" style="background-color:white;">訂購產品</th>
						<th width="80px;" style="background-color:white;">訂購數量</th>
						<th width="" style="background-color:white;">小計</th>
	
					</tr>
					<c:forEach var="order_itemVO" items="${set}">
						<tr>
							<td width="120px">
								<c:forEach var="productVO" items="${productSvc.all}">
	                    			<c:if test="${productVO.product_no==order_itemVO.product_no}">
		                   				<img src="/upload/${productVO.product_img_name}">
	                    			</c:if>
	                			</c:forEach>  
							</td>
				
							<td width="375px;">${order_itemVO.product_name}</td>
							<td width="80px;">${order_itemVO.order_item_amount}</td>
							<td width="">$ ${order_itemVO.order_item_point}</td>
						</tr>
					</c:forEach>
			</table>
			<table>
					<tr>
						<th colspan="5" style="text-align:center;">訂購會員資料</th>
					</tr>	
					<tr>
						<th width="30px;" style="background-color:white;"></th>
						<th width="120px;" style="background-color:white;">會員編號</th>	
						<th width="120px;" style="background-color:white;">會員姓名</th>
						<th width="200px;" style="background-color:white;">會員信箱</th>
						<th width="" style="background-color:white;">會員手機</th>
						
					</tr>		
					<tr>
						<td width="30px;"></td>
						<td width="120px;">${ordersVO.member_no}</td>
						<td width="120px;">
					    <c:forEach var="memberVO" items="${memberSvc.all}">
                    		<c:if test="${ordersVO.member_no==memberVO.member_id}">
	                   			${memberVO.member_name}
                    		</c:if>
                		</c:forEach>  
						</td>
						<td width="250px;">
						<c:forEach var="memberVO" items="${memberSvc.all}">
                    		<c:if test="${ordersVO.member_no==memberVO.member_id}">
	                   			${memberVO.member_email}
                    		</c:if>
                		</c:forEach>  
						</td>
						<td width="">
						<c:forEach var="memberVO" items="${memberSvc.all}">
                    		<c:if test="${ordersVO.member_no==memberVO.member_id}">
	                   			${memberVO.member_phone}
                    		</c:if>
                		</c:forEach>  
						</td>
								
					</tr>
			</table>
			<table>
					<tr>
						<th colspan="5" style="text-align:center;">收件人資料</th>
					</tr>
					<tr>
						<th width="30px;" style="background-color:white;"></th>
						<th width="120px;" style="background-color:white;">收件人姓名</th>
						<th width="120px;" style="background-color:white;">收件人電話</th>	
						<th width="" style="background-color:white;">收件地址</th>
					</tr>		
					<tr>
						<td width="30px;"></td>
						<td width="120px;">${ordersVO.orders_shipping_name}</td>
						<td width="120px;">${ordersVO.orders_shipping_phone}</td>
						<td width="">${ordersVO.orders_shipping_address}</td>
						
					</tr>
			</table>
		
			<div class="include_file">
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/orders/js/orders_listAll.js"></script>
	<script>
	function del() {
		var msg = "您真的確定要刪除嗎？\n\n請確認！";
		if (confirm(msg)==true){
		return true;
		}else{
		return false;
		}
	}
	</script>
</body>

</html>