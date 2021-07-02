<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>



<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商品管理(後台)</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/product/css/addProduct.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/product/vendors/jquery/jquery-3.6.0.min.js"></script>
</head>


<body>
		<header>
			<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
		</header>
	
		<div>
			<jsp:include page="/cms/header_asideMenu/cmsAsideMenu.jsp" flush="true" />
	    </div>
	<div class="main">
		<FORM METHOD="post" ACTION="product.do" name="form1" enctype="multipart/form-data">
			<div class="product_info" style="margin-right:130px;">
				<div class="product_title">
					<h1>${productVO.product_name}								
					<span class="product_no">產品編號:${productVO.product_no}</span>
					</h1>
					<input type="hidden" name="product_no" value="${productVO.product_no}">
				</div>
				<div class="product_info_form">
					<div class="form1">商品封面照</div>
					<div class="product_pic_upload">
						<ul class="picture_list"></ul>
						<a href="javascript:void(0)"> 
							<img src="${pageContext.request.contextPath}/product/img/新增圖片.png" id="my-img"> 
							<input type="file" name="product_img" id="img-upload" value="<%=(productVO == null) ? "" : productVO.getProduct_img_name()%>">
						</a>
					</div>
					
					<div class="form1">名稱</div>
					<div class="form2">
						<input type="TEXT" name="product_name" size="40" class="input_css" value="<%=(productVO == null) ? "" : productVO.getProduct_name()%>" />
					</div>
					<div class="form1">價格</div>
					<div class="form2">
						<input type="TEXT" name="product_point" size="15" value="<%=(productVO == null) ? "" : productVO.getProduct_point()%>" />
					</div>
					<div class="form1">庫存數量</div>
					<div class="form2">
						<input type="TEXT" name="product_stock_quantity" size="15" value="<%=(productVO == null) ? "" : productVO.getProduct_stock_quantity()%>" />
					</div>
					<div class="form1">商品介紹</div>
					<div class="form2">
						<textarea name="product_intro" rows="15" cols="80" class="ckeditor">${productVO.product_intro}</textarea>
					</div>
					
					<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

					<div class="form1">系列</div>
					<div class="form2">
						<select size="1" name="product_category_no" class="select">
							<c:forEach var="product_categoryVO"
								items="${product_categorySvc.all}">
								<option value="${product_categoryVO.product_category_no}"
									${(productVO.product_category_no==product_categoryVO.product_category_no)? 'selected':'' }>${product_categoryVO.product_category_name}
							</c:forEach>
						</select>
					</div>
					<div class="form1">上架狀態</div>
					<div class="form2">
						<input type="RADIO" name="product_status" size="45" value="0" checked />下架 
						<input type="RADIO" name="product_status" size="45" value="1" />上架

					</div>

				</div>
			</div>
		<!-------------------------------------------商品折扣----------------------------------------- -->
			<div class="product_info" style="height:500px; margin-right:130px;">
				<div class="product_title">
					<h1>優惠設定</h1>
				</div>
				<div class="product_info_form">
					
					
						<div class="form2">
							  <label>
							    <input type="radio" value="1" name="product_discount_no" <c:if test="${productVO.product_discount_no == 1}">checked</c:if> />
							     	折扣優惠
							  </label>							  
						</div>
						
						<div class="discount_1">
							優惠折數: <input type="number"style="width:70px;" step="1" min="0" max="10" name="product_discount_detail_rate" size="2">&nbsp;折
						</div>
						
						<div class="form2">
							  <label>
							    <input type="radio" value="2" name="product_discount_no" <c:if test="${productVO.product_discount_no == 2}">checked</c:if>/>
							     	Coupon
							  </label>							  
						</div>
						
						<div class="discount_2">
							<div>設定Coupon代碼: <input type="TEXT" id="coupon" name="product_discount_detail_coupon" size="25"></div>
							
							<div class="random_code"><input id="autofill" type="button" onclick="RandomAutoFunction()" value="自動產生隨機代碼" size="3"></div>
							<div>減價: <input type="number" style="width:120px;" step="1" min="0" name="product_discount_detail_minus" size="3">&nbsp;元</div>
						</div>
						
						<div class="form2">
							  <label>
							    <input type="radio" value="3" name="product_discount_no" <c:if test="${productVO.product_discount_no == 3}">checked</c:if>/>
							     	買X送x
							  </label>							  
						</div>
						
						<div class="discount_3">
							買<input type="number"style="width:70px;" step="1" min="1" name="product_discount_detail_buy_count" size="1">
							送<input type="number"style="width:70px;" step="1" min="0" name="product_discount_detail_get_count" size="1">
						</div>
						
						<div class="form2">
							  <label>
							    <input type="radio" value="4" class="ckbutton_checkbox" name="product_discount_no" <c:if test="${productVO.product_discount_no == 4}">checked</c:if>/>
							     	不須設定優惠
							  </label>							  
						</div>


					<div class="product_save">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="product_no" value="${productVO.product_no}">
						<input type="submit" value="儲存" class="product_save_submit">
						<a href='listProd_back.jsp'><input type="button" value="取消" class="product_save_submit" style="background-color:rgb(161, 161, 161);"></a>
					</div>
					
				</div>
			</div>
		</FORM>
	</div>
	<script src="${pageContext.request.contextPath}/product/js/addProduct.js"></script>
	<script>
	// 優惠選擇填單顯示 
	$(document).ready(function(){ 
		$(".discount_1").hide();
        $(".discount_2").hide();
        $(".discount_3").hide();
	
	   $('input[name="product_discount_no"]').click(function(){
		        if($(this).attr("value")=="1"){
		            $(".discount_1").show();
		            $(".discount_2").hide();
		            $(".discount_3").hide();
		        }
		        if($(this).attr("value")=="2"){
		            $(".discount_1").hide();
		            $(".discount_2").show();
		            $(".discount_3").hide();
		        }
		        if($(this).attr("value")=="3"){
		            $(".discount_1").hide();
		            $(".discount_2").hide();
		            $(".discount_3").show();
		        }
		        if($(this).attr("value")=="4"){
		            $(".discount_1").hide();
		            $(".discount_2").hide();
		            $(".discount_3").hide();
		        }
		    });	   
		 });
	</script>
	
	
	<script>
	var characters="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	var passwordlength=8

		function generatepassword() {
		var password = ""
		var n = 0
		var randomnumber = 0
		while( n < passwordlength ) {
		n ++
		randomnumber = Math.floor(characters.length*Math.random());
		password += characters.substring(randomnumber,randomnumber + 1)
		}

		return password
		}
	
	function RandomAutoFunction() {
// 	    if (document.getElementById('autofill').click()) {
	        var randomNum = generatepassword();
	     
// 			alert(generatepassword());
	        document.getElementById('coupon').value = randomNum;
	   
// 	    } 
	}
	</script>
</body>

</html>