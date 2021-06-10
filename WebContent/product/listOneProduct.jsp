<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>商品資料</title>
<h4><a href="select_page.jsp">回首頁</a></h4>
<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>商品資料 - ListOneProduct.jsp</h3>
<!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
	</td></tr>
</table>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品介紹</th>
		<th>商品價格</th>
		<th>庫存數量</th>
		<th>商品圖片</th>
		<th>上架狀態</th>
		<th>商品類別</th>
	</tr>
	<tr>
		<td>${productVO.product_no}</td>
		<td>${productVO.product_name}</td>
		<td>${productVO.product_intro}</td>
		<td>${productVO.product_point}</td>
		<td>${productVO.product_stock_quantity}</td>
		<td>${productVO.product_img}</td>
		<td>${productVO.product_status eq 1?"上架":"下架"}</td> 
		<c:if test="${productVO.product_category_no == 1}"><td>生活</td></c:if>
		<c:if test="${productVO.product_category_no == 2}"><td>飯店住宿</td></c:if>
		<c:if test="${productVO.product_category_no == 3}"><td>泡湯</td></c:if>
		<c:if test="${productVO.product_category_no == 4}"><td>獨享食光</td></c:if>
	</tr>
</table>

</body>
</html>