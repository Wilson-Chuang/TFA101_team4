<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.orders.model.*"%>



<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<title>商品管理(後台)</title>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/product/css/product_listAll_view.css">
<script src="${pageContext.request.contextPath}/product/vendors/jquery/jquery-3.6.0.min.js"></script>

</head>

<body>
	<div class="main">
		<div class="listProd_content">
			<div class="product_title">
				<h1>商品編號: ${productVO.product_no}</h1>
				<button class="button1" onclick="location.href='${pageContext.request.contextPath}/product/listProd_back.jsp'">
					返回
				</button>
				<button class="button2">
					<i class="far fa-trash-alt"></i>&nbsp;&nbsp;刪除產品
				</button>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do">
			    	<input type="submit" value="移除" onclick="javascript:return del();">
			     	<input type="hidden" name="product_no"  value="${productVO.product_no}">
			     	<input type="hidden" name="action" value="delete">
			    </FORM>
			</div>
		
			<table>
					<tr>
						<th colspan="5" style="text-align:center;">商品資料</th>
					</tr>	
					<tr>
						<th width="30px;" style="background-color:white;"></th>
						<th width="120px;" style="background-color:white;">商品編號</th>
						<th width="120px" style="background-color:white;">訂價</th>				
						<th width="250px;" style="background-color:white;">商品名稱</th>
						<th width="" style="background-color:white;">庫存</th>
					</tr>
							
					<tr>
						<td width="30px;"></td>
						<td width="120px;">${productVO.product_no}</td>					
						<td width="120px">${productVO.product_point} GP</td>
						<td width="250px;">${productVO.product_name}</td>
						<td width="">${productVO.product_stock_quantity}</td>		
					</tr>
			</table>
			<table>

					<tr>
						<th width="294px" style="background-color:white;"></th>				
						<th width="249px;" style="background-color:white;">上架狀態</th>
						<th width="" style="background-color:white;">系列</th>
	
					</tr>
					<tr>
						<td width="294px">
		                   	<img src="/upload/${productVO.product_img_name}">	            
						</td>
				
						
						<td width="249px;">${productVO.product_status eq 1?"上架":"下架"}</td>
						<td width="">
								<c:forEach var="product_categoryVO" items="${product_categorySvc.all}">
	                    			<c:if test="${product_categoryVO.product_category_no==productVO.product_category_no}">
		                   				${product_categoryVO.product_category_name}
	                    			</c:if>
	                			</c:forEach>
	                	</td>
						</tr>
			</table>
			<table>
					<tr>
						<th colspan="2" style="text-align:center;">商品介紹</th>
					</tr>
					<tr>
						<td width="30px;"></td>
						<td style=" height:300px;vertical-align: unset;padding-top:40px;">${productVO.product_intro}</td>
					</tr>
			</table>
		
		
			<div class="include_file">
			</div>
		</div>
	</div>
<%-- 	<script src="${pageContext.request.contextPath}/product/js/orders_listAll.js"></script> --%>
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