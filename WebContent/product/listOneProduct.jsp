<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
 <title>${productVO.product_name}</title>
 <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
 <script src="${pageContext.request.contextPath}/product_list/js/popup.js"></script>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/popup.css">
 <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/sidebar.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/cart.css">
 <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
 <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
 
</head>
<body>
 <button id="sidebar-btn" class="sidebar-btn"><i class="fas fa-shopping-cart"></i></button>
    <section id="sidebar" class="sidebar">
	<jsp:include page="/product_list/Cart.jsp" flush="true" />
    </section>
    
    
    <form name="shoppingForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
            			method="POST" target="nm_iframe">
    <div id="content" class="content" style="background-color:white">
        <div class="product_detail_img">
            <img src="/upload/${productVO.product_img_name}" alt="">
        </div>

        <div class="product_detail_info">
            <div class="pd_title">${productVO.product_name}</div>
            <div class="pd_info">${productVO.product_intro}</div>
            <div class="pd_price">${productVO.product_point}</div>
            <div class="pd_QTY">

                <div class="input-group">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quant[1]">
                            <span class="glyphicon glyphicon-minus"></span>
                        </button>
                    </span>
                    <input type="text" name="quant[1]" class="form-control input-number" value="1" min="1" max="${productVO.product_stock_quantity}">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quant[1]">
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>
                    </span>
                </div>

            </div>
            <div class="pd_cart">
                <input type="submit" value="加入購物車" class="pd_cart_button">
                <input type="hidden" name="img_name" value="${productVO.product_img_name}">
                <input type="hidden" name="name" value="${productVO.product_name}">
                <input type="hidden" name="price" value="${productVO.product_point}">
                <input type="hidden" name="quantity" value="${productVO.product_point}">
                <input type="hidden" name="action" value="ADD">        
            </div>
        </div>
    
</div>
</form>
<iframe id="nm_iframe" name="nm_iframe" style="display:none;" ></iframe>


<script>
      window.onload = function() {
      	  document.getElementById("nm_iframe").onload = function() {
       		  setTimeout(function(){
      		  	location.reload(true);  
       		  },500);
      	  }
      };
 </script>
 <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/sidebar.js"></script>
</body>
</html>