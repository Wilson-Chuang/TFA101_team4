<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.*"%>

<%
	OrdersService ordersSvc = new OrdersService();
	List<OrdersVO> list = ordersSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
  OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="order_itemSvc" scope="page" class="com.order_item.model.Order_itemService" />
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<title>訂單管理(後台)</title>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/orders/css/orders_listAll.css">
<script src="${pageContext.request.contextPath}/orders/vendors/jquery/jquery-3.6.0.min.js"></script>

</head>

<body>
	<div class="main">
		<div class="listProd_content">
			<div class="product_title">
				<h1>訂單管理</h1>
				<button class="button1" onclick="location.href='${pageContext.request.contextPath}/orders/orders_listAll.jsp'">
					返回
				</button>
			</div>
			<div class="product_navbar">
                <div class="search">
                <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/orders/orders.do" id="form">
                    <input class="search_bar" type="text" name="orders_no" id="search" placeholder="訂單編號..">
                    <input type="hidden" name="action" value="getOne_For_Display">
        			<input type="submit" value="送出" class="search_submit">
                    <button class="search_button"><i class="fas fa-search"></i></button>
                </FORM>
                </div>
			</div>
			<table>
				<tr>
					<th width="30px;"></th>
					<th width="120px;">訂單編號</th>
					
					<th width="120px;">會員</th>				
					<th width="120px">消費金額</th>
					<th width="250px;">訂購時間</th>
					<th></th>
					<th></th>
				</tr>
			
					<tr>
						<td width="30px;"></td>
						<td width="120px;">${ordersVO.orders_no}</td>
						<td width="120px;">
						 <c:forEach var="memberVO" items="${memberSvc.all}">
                    		<c:if test="${ordersVO.member_no==memberVO.member_id}">
	                   			${memberVO.member_name}
                    		</c:if>
                		 </c:forEach>  
						</td>
						
						<td width="120px;">$${ordersVO.orders_total_point}</td>	
						<td width="240px;">${ordersVO.orders_date}</td>		
						<td>									
						<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/orders/orders.do">
								<input type="submit" value="邊" class="view">
								<input type="hidden" name="orders_no" value="${ordersVO.orders_no}">
								<input type="hidden" name="action" value="view">
						</FORM>					
						</td>
						<td>
						<i class="far fa-trash-alt">
						<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/orders/orders.do">
				    			<input type="submit" value="移除" class="delete" onclick="javascript:return del();">
						     	<input type="hidden" name="orders_no"  value="${ordersVO.orders_no}">
						     	<input type="hidden" name="action" value="delete">
			    		</FORM>
						</td>
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
	
	$("#form").keydown(function(event) {
	    if(event.keyCode == 13){
	    	on 'submit';
	    };
	});
	
	</script>
</body>

</html>