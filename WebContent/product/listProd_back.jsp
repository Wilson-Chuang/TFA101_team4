<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%
	ProductService productSvc = new ProductService();
	List<ProductVO> list = productSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<title>商品管理(後台)</title>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/product/css/listProd_back.css">
<script src="${pageContext.request.contextPath}/product/vendors/jquery/jquery-3.6.0.min.js"></script>

</head>

<body>
	<div class="main">
		<div class="listProd_content">
			<div class="product_title">
				<h1>產品</h1>
			</div>
			<div class="product_navbar">
				<a href='addProduct.jsp'>
				<button>
					<i class="fas fa-plus"></i>&nbsp;&nbsp;新產品
				</button>
				</a>
				<div class="datacount">
				<%@ include file="page3.file"%>
				</div>
			</div>
			<table>
				<tr>
					<th width="100px;"></th>
					<th width="200px;">名稱</th>
					<th width="80px;">價格</th>
					<th width="80px;">庫存</th>
					<th width="80px;">狀態</th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td width="100px;"><div class="img"><img src="/upload/${productVO.product_img_name}"></div></td>
						<td width="200px;">${productVO.product_name}</td>
						<td width="80px;">${productVO.product_point}</td>
						<td width="80px;">${productVO.product_stock_quantity}</td>
						<td width="80px;">${productVO.product_status eq 1?"上架":"下架"}</td>			
						<td>
							<i class="far fa-edit"></i>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
									<input type="submit" value="邊" class="edit">
									<input type="hidden" name="product_no" value="${productVO.product_no}"> 
									<input type="hidden" name="action" value="getOne_For_Update">
							</FORM>						
						</td>
						<td>
							<i class="far fa-trash-alt"></i>
			  				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" style="margin-bottom: 0px;">
			    					<input type="submit" value="移">
			     					<input type="hidden" name="product_no"  value="${productVO.product_no}">
			     					<input type="hidden" name="action" value="delete">
			     			</FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div class="include_file">
			<%@ include file="page4.file"%>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/product/js/listProd_back.js"></script>
</body>

</html>