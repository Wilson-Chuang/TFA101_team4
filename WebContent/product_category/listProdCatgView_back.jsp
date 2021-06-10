<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.product.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

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
<title>�ӫ~�޲z(��x)</title>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/product_category/css/listProdCatgView_back.css">
<script src="${pageContext.request.contextPath}/product_category/vendors/jquery/jquery-3.6.0.min.js"></script>

</head>

<body>
	<div class="main">
		<div class="listProd_content">
			<div class="product_title">
		
				<h1>${product_categoryVO.product_category_name} �t�C</h1>

			</div>
			<div class="product_navbar">
				<a href='listProdCatg_back.jsp'>
					<button style="letter-spacing:5px; font-size:16px;">
						�W�@��
					</button>
				</a>
				<div class="datacount">�@��<font color=red> ${count}</font> ���ӫ~</div>
			</div>
			<table>
				<tr>		
					<th width="120px"></th>
					<th width="320px">�W��</th>
					<th width="100px">����</th>
					<th width="100px">�w�s</th>
					<th>���A</th>
				</tr>
				<c:forEach var="productVO" items="${set}">
					<tr>
						<td><div class="img"><img src="/upload/${productVO.product_img_name}"></div></td>
						<td>${productVO.product_name}</td>
						<td>${productVO.product_point}</td>
						<td>${productVO.product_stock_quantity}</td>		
						<td>${productVO.product_status eq 1?"�W�[":"�U�["}</td>
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