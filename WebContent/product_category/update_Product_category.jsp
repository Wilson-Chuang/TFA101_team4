<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_category.model.*"%>

<%
	Product_categoryVO product_categoryVO = (Product_categoryVO) request.getAttribute("product_categoryVO");
%>
<%-- <%= empVO==null %>--${empVO.deptno}-- --%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>系列管理(後台)</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/product_category/css/updateProdCatg.css">
<script src="./vendors/jquery/jquery-3.6.0.min.js"></script>
</head>


<body>
	<div class="main">
		<FORM METHOD="post" ACTION="product_category.do" name="form1">
			<div class="product_info">
				<div class="product_title">
					<h1>系列編輯</h1>
				</div>
				<div class="product_info_form">
					<div class="form1">名稱</div>
					<div class="form2">
						<input type="TEXT" name="product_category_name" size="40" class="input_css"
							value="<%=(product_categoryVO == null) ? "" : product_categoryVO.getProduct_category_name()%>" />
					</div>
					

					<div class="product_save">
						<input type="hidden" name="action" value="update"> 
						<input type="hidden" name="product_category_no" value="<%=product_categoryVO.getProduct_category_no()%>">
						<input type="submit" value="儲存" class="product_save_submit">
						<a href='listProdCatg_back.jsp'><input type="button" value="取消" class="product_save_submit" style="background-color:rgb(161, 161, 161);"></a>
					</div>

				</div>
			</div>
		</FORM>
	</div>
	<script src="./js/addProdCatg.js"></script>
</body>

</html>