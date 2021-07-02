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
<header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>
<div>
	<jsp:include page="/cms/header_asideMenu/cmsAsideMenu.jsp" flush="true" />
</div>

	<div class="main" style=" margin-right:130px;">
		<div class="listProd_content">
			<div class="product_title">
				<h1>產品管理</h1>
			</div>
			<div class="product_navbar">
			
				<div class="search">
                <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/product/product.do" id="form">
                    <input class="search_bar" type="text" name="product_name" id="search" placeholder="商品名稱..">
                    <input type="hidden" name="action" value="search">
        			<input type="submit" value="送出" class="search_submit">
                    <button class="search_button"><i class="fas fa-search"></i></button>
                </FORM>
                </div>
                
				<a href='addProduct.jsp'>
				<button class="add_product">
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
					<th width="120px;" style="text-align:center;">狀態</th>
					<th></th>
				</tr>
				<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr>
						<td width="100px;">
							<div class="img">
								<a href="${pageContext.request.contextPath}/product/product.do?product_no=${productVO.product_no}&action=view">								
									<img src="/upload/${productVO.product_img_name}">
								</a>
							</div>
						</td>
						
						<td width="200px;">
							<a href="${pageContext.request.contextPath}/product/product.do?product_no=${productVO.product_no}&action=view">
								${productVO.product_name}
							</a>
						</td>
						<td width="80px;">${productVO.product_point}</td>
						<td width="80px;">${productVO.product_stock_quantity}</td>

						<td width="120px;" style="text-align:center;">
						<c:if test="${productVO.product_status == 1}">
							<i class="far fa-circle" style="color:greenyellow; font-size:26px;"></i>					
						</c:if>
						
						<c:if test="${productVO.product_status == 0}">
							<i class="fas fa-times" style="color:red; font-size:26px;"></i>				
						</c:if>
						
					            
					            <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/product/product.do">
					                <input class="input_off" type="submit" id="radio-one" name="switch-one" value="上架"/>
					                <input type="hidden" name="product_no" value="${productVO.product_no}">
					                <input type="hidden" name="action" value="update_on_status">
					                
					            </FORM>
					       
					            
					            <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/product/product.do" >
					                <input class="input_on" type="submit" id="radio-two" name="switch-one" value="下架"/>
					                <input type="hidden" name="product_no" value="${productVO.product_no}">
					                <input type="hidden" name="action" value="update_off_status">					         
					            </FORM>
					   
					
       						
						</td>					

						<td style="text-align:center;">
							<i class="far fa-edit"></i>
							<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/product/product.do">
									<input type="submit" value="邊" class="edit">
									<input type="hidden" name="product_no" value="${productVO.product_no}"> 
									<input type="hidden" name="action" value="getOne_For_Update">
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
	<script>
	$("#form").keydown(function(event) {
	    if(event.keyCode == 13){
	    	on 'submit';
	    };
	});
	</script>
	
</body>

</html>