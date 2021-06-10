<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<%-- <%= empVO==null %>--${empVO.deptno}-- --%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品管理(後台)</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/product/css/addProduct.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/product/vendors/jquery/jquery-3.6.0.min.js"></script>
</head>


<body>
		<FORM METHOD="post" ACTION="product.do" name="form1" enctype="multipart/form-data">
			<div class="product_info">
				<div class="product_title">
					<h1>新增產品</h1>
				</div>
				<div class="product_info_form">
					<div class="form1">商品封面照</div>
					<div class="product_pic_upload">
						<ul class="picture_list"></ul>
						<a href="javascript:void(0)"> 
							<img src="${pageContext.request.contextPath}/product/img/新增圖片.png" id="my-img"> 
							<input type="file" name="product_img" id="img-upload">
						</a>
					</div>
					<div class="form1">名稱</div>
					<div class="form2">
						<input type="TEXT" name="product_name" size="40" class="input_css"
							value="<%=(productVO == null) ? "" : productVO.getProduct_name()%>" />
					</div>
					<div class="form1">價格</div>
					<div class="form2">
						<input type="TEXT" name="product_point" size="15"
							value="<%=(productVO == null) ? "" : productVO.getProduct_point()%>" />
					</div>
					<div class="form1">庫存數量</div>
					<div class="form2">
						<input type="TEXT" name="product_stock_quantity" size="15"
							value="<%=(productVO == null) ? "" : productVO.getProduct_stock_quantity()%>" />
					</div>
					<div class="form1">商品介紹</div>
					<div class="form2">
						<textarea name="product_intro" rows="15" cols="80" class="ckeditor"></textarea>
					</div>
					<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />
					<div class="form1">系列</div>
					<div class="form2">
						<select size="1" name="product_category_no" class="select">
							<c:forEach var="product_categoryVO" items="${product_categorySvc.all}">
								<option value="${product_categoryVO.product_category_no}"
									${(productVO.product_category_no==product_categoryVO.product_category_no)? 'selected':'' }>${product_categoryVO.product_category_name}
							</c:forEach>
						</select>
					</div>
					<div class="form1">上架狀態</div>
					<div class="form2">
						<input type="RADIO" name="product_status" size="45" value="0" checked />下架 
							<input type="RADIO" name="product_status" size="45" value="1" />上架
						</td>
					</div>
					<div class="product_save">
						<input type="hidden" name="action" value="insert"> 
						<input type="submit" value="儲存" class="product_save_submit">
						<a href='listProd_back.jsp'><input type="button" value="取消" class="product_save_submit" style="background-color:rgb(161, 161, 161);"></a>
					</div>

				</div>
			</div>
		</FORM>
	<script src="${pageContext.request.contextPath}/product/js/addProduct.js"></script>
</body>

</html>