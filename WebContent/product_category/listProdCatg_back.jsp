<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.product.model.*"%>


<%
	Product_categoryService product_categorySvc = new Product_categoryService();
	List<Product_categoryVO> list = product_categorySvc.getAll();
	pageContext.setAttribute("list", list);
%>



<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<title>系列管理(後台)</title>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/product_category/css/listProdCatg_back.css">
<script src="${pageContext.request.contextPath}/product_category/vendors/jquery/jquery-3.6.0.min.js"></script>

</head>

<body>
	<div class="main">
		<div class="listProd_content">
			<div class="product_title">
				<h1>系列</h1>
			</div>
			<div class="product_navbar">
				<a href='addProduct_category.jsp'>
				<button>
					<i class="fas fa-plus"></i>&nbsp;&nbsp;新系列
				</button>
				</a>
				<div class="datacount">
				<%@ include file="page1.file"%>
				</div>
			</div>
			<table>
				<tr>
					<th width="100px"></th>
					<th width="600px">系列</th>					
					<th width="150px"></th>
					<th width="150px"></th>
					<th width="150px"></th>
				</tr>
				<c:forEach var="product_categoryVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td width="100px">&nbsp;</td>
						<td width="600px">${product_categoryVO.product_category_name}</td>
						<td width="150px">
							<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/product_category/product_category.do">
									<input type="submit" value="檢" class="view">
									<input type="hidden" name="product_category_no" value="${product_categoryVO.product_category_no}"> 
									<input type="hidden" name="action" value="view">
							</FORM>						
						</td>
						<td width="150px">
							<i class="far fa-edit"></i>
							<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/product_category/product_category.do">
									<input type="submit" value="編" class="edit">
									<input type="hidden" name="product_category_no" value="${product_categoryVO.product_category_no}"> 
									<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>						
						</td>
						<td width="150px">
							<i class="far fa-trash-alt"></i>
			  				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product_category/product_category.do" style="margin-bottom: 0px;">
			    					<input type="submit" value="移" class="delete">
			     					<input type="hidden" name="product_category_no"  value="${product_categoryVO.product_category_no}">
			     					<input type="hidden" name="action" value="delete"></FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="include_file">
			<%@ include file="page2.file"%>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/product_category/js/listProdCatg_back.js"></script>
</body>

</html>