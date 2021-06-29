<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Set set = new LinkedHashSet();
	set.add(request.getAttribute("set"));
%>

<%
	Integer count = (Integer)request.getAttribute("count");
%>

<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<title>商品管理(後台)</title>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/product_category/css/listProdCatgView_back.css">
<script src="${pageContext.request.contextPath}/product_category/vendors/jquery/jquery-3.6.0.min.js"></script>

</head>

<body>
	<div class="main">
		<div class="listProd_content">
			<div class="product_title">
		
				<h1>${product_categoryVO.product_category_name} 系列</h1>

			</div>
			<div class="product_navbar">
				<a href='listProdCatg_back.jsp'>
					<button style="letter-spacing:5px; font-size:16px;">
						上一頁
					</button>
				</a>
				<div class="datacount">共有<font color=red> ${count}</font> 筆商品</div>
			</div>
			<table>
				<tr>		
					<th width="120px"></th>
					<th width="320px">名稱</th>
					<th width="100px">價格</th>
					<th width="100px">庫存</th>
					<th>狀態</th>
				</tr>
				<c:forEach var="productVO" items="${set}">
					<tr>
						<td><div class="img"><img src="/upload/${productVO.product_img_name}"></div></td>
						<td>${productVO.product_name}</td>
						<td>${productVO.product_point}</td>
						<td>${productVO.product_stock_quantity}</td>		
						<td>${productVO.product_status eq 1?"上架":"下架"}</td>
					</tr>
				</c:forEach>
			</table>
			<div class="include_file">
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/product_category/js/listProdCatgView_back.js"></script>

</body>

</html>