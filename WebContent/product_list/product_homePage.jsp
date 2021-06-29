<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.product.model.*"%>


<%
	Product_categoryService product_categorySvc3 = new Product_categoryService();
	Set<ProductVO> set1 = product_categorySvc3.getProductsByproduct_category_no(1);
	pageContext.setAttribute("set1", set1);
%>


<%
	Product_categoryService product_categorySvc4 = new Product_categoryService();
	Set<ProductVO> set2 = product_categorySvc4.getProductsByproduct_category_no(2);
	pageContext.setAttribute("set2", set2);
%>


<jsp:useBean id="product_categorySvc" scope="page" class="com.product_category.model.Product_categoryService" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
    <title>Guide好食積分商城</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/product_homePage.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/flip-clock.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/cart.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/header.css">
    <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/flip-clock.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<body>
    <button id="sidebar-btn" class="sidebar-btn"><i class="fas fa-shopping-cart"></i></button>
    <section id="sidebar" class="sidebar">
	<jsp:include page="/product_list/Cart.jsp" flush="true" />
    </section>
    <div id="content" class="content" style="background-color:white">
<!---------------------------------------輪播------------------------------------------------>

        <div class="slideshow-container">
            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/product_list//img/slider5.png">
                <div class="slider_info2">
                    <h1>Better Things In a Better Way</h1>
                    <h2>Guide food</h2>
                    <h3>【新上市】</h3>
                </div>
            </div>

            <div class="mySlides fade">
                <img src="${pageContext.request.contextPath}/product_list//img/slider6.png">
                <div class="slider_info3">
                    <h2>Write more , buy more</h2>
                </div>
            </div>

            <a class="prev1" onclick="plusSlides(-1)">❮</a>
            <a class="next1" onclick="plusSlides(1)">❯</a>
        </div>
     
        
<!--------------------------------------熱銷商品------------------------------------------------->
        
        
		<div class="title">HOT SALES</div>
		
        <div class="slider_body">
            <a href="javascript:;" class="dIcon next"></a>
            <a href="javascript:;" class="dIcon prev"></a>
            <div class="slider">
                <ul class="list">
   
         <c:forEach var="productVO" items="${set2}">

                    <li>    
                        <div class="hot_sales_product">              
                        <a href="javascript:void();">
                        <img src="/upload/${productVO.product_img_name}">
                        </a>
                        
                        
                       	<form METHOD="get" ACTION="<%=request.getContextPath()%>/product/product.do">                      
                        <input type="submit" value="連結到商品資訊" class="product_info1">
                        <input type="hidden" name="product_no" value="${productVO.product_no}">
                        <input type="hidden" name="action" value="getOne_For_Display"> 
                         </form>
          		
                        <div class="hot_sales_product_info">
                                <h2>${productVO.product_name}</h2>
                            <div class="hot_sales_content">
                                <p>${productVO.product_point} GP</p>
                            <form name="shoppingForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
            			method="POST" target="nm_iframe2">
         					<input type="submit" value=" &nbsp加入購物車 &nbsp" class="cart_submit2">
                            <input type="hidden" name="img_name" value="${productVO.product_img_name}">
                            <input type="hidden" name="name" value="${productVO.product_name}">
                            <input type="hidden" name="price" value="${productVO.product_point}">
                            <input type="hidden" name="product_no" value="${productVO.product_no}">
                            <input type="hidden" name="quantity" value="1">
                            <input type="hidden" name="action" value="ADD">
                             </form>
                            </div>
                        </div>
                        </div>                      
                    </li>                            
         </c:forEach>
                </ul>
            </div>
        </div>
        <iframe id="nm_iframe2" name="nm_iframe2" style="display:none;" ></iframe>
		     
<!--------------------------------------計時器------------------------------------------------->
 
        <div class="timecounter">
            <div class="time">
                <ul class="flip-clock-container flip-example">
                    <li class="flip-item-seconds">2</li>
                    <li class="flip-item-minutes">32</li>
                    <li class="flip-item-hours">1</li>
                </ul>
            </div>
            <div class="timecounter_title">
                <h1>限時活動</h1>
                <div class="timecounter_more"><a href="http://127.0.0.1:5500/index.html">>></a></div>
            </div>        
        </div>
        
<!------------------------------------新到貨--------------------------------------------------->
        
        <div class="title">NEW ARRIVALS</div>
		<div class="new_arrival">    
         <c:forEach var="productVO" items="${set1}"  begin="0" end ="7"> 
            <div class="new_arrival_product">          
                <img src="/upload/${productVO.product_img_name}">            
                <form METHOD="get" ACTION="<%=request.getContextPath()%>/product/product.do">                      
                        <input type="submit" value="連結到商品資訊" class="product_info2">
                        <input type="hidden" name="product_no" value="${productVO.product_no}">
                        <input type="hidden" name="action" value="getOne_For_Display"> 
                </form>
                <div class="new_arrival_desc">
                    <p>
                       <c:forEach var="product_categoryVO" items="${product_categorySvc.all}">
                    		<c:if test="${productVO.product_category_no==product_categoryVO.product_category_no}">
	                   			${product_categoryVO.product_category_name}
                    		</c:if>
                		</c:forEach>                                      
                    </p>
                  
                        <h2>${productVO.product_name}</h2>
                   
                    <div class="new_arrival_content">
                        <p>${productVO.product_point} GP</p>
 					<form name="shoppingForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
            			method="POST" target="nm_iframe">
                            <input type="submit" value=" &nbsp加入購物車 &nbsp" class="cart_submit">
                            <input type="hidden" name="img_name" value="${productVO.product_img_name}">
                            <input type="hidden" name="name" value="${productVO.product_name}">
                            <input type="hidden" name="price" value="${productVO.product_point}">
                            <input type="hidden" name="product_no" value="${productVO.product_no}">
                            <input type="hidden" name="quantity" value="1">
                            <input type="hidden" name="action" value="ADD">
                    </form>
                    </div>
                </div>
            </div>
         </c:forEach>        
        </div>
		<iframe id="nm_iframe" name="nm_iframe" style="display:none;" ></iframe>
        
<!-------------------------------------商品類別-------------------------------------------------->

        <div class="see_more"><a href="${pageContext.request.contextPath}/product_category/product_category.do?product_category_no=1&action=see_category_product">Discover more</a></div>
        <div class="Best_Picks"></div>
        <div class="title1">NEW COLLECTION</div>
        <div class="title2">Best Picks 2021</div>
        
        <div class="category">
            <a href="${pageContext.request.contextPath}/product_category/product_category.do?product_category_no=3&action=see_category_product"><div class="category1">美食餐券</div></a>
            <a href="${pageContext.request.contextPath}/product_category/product_category.do?product_category_no=5&action=see_category_product"><div class="category2">旅遊住宿</div></a>
            <a href="${pageContext.request.contextPath}/product_category/product_category.do?product_category_no=6&action=see_category_product"><div class="category3">居家生活</div></a>
            <a href="${pageContext.request.contextPath}/product_category/product_category.do?product_category_no=4&action=see_category_product"><div class="category4">泡湯溫泉</div></a>
        </div>
  
</div>
       
        
<!-----------------------------------------js補充---------------------------------------------->


    <script src="${pageContext.request.contextPath}/product_list/js/product_homePage.js"></script>
    <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/sidebar.js"></script>
    
<script>
    //加入購物車alert
    $(".cart_submit").each(function () {
        $(".cart_submit").click(function () {
            swal("已完成", "成功將此商品加入購物車!", "success");

        });
    });  
    
    $(".cart_submit2").each(function () {
        $(".cart_submit2").click(function () {
            swal("已完成", "成功將此商品加入購物車!", "success");

        });
    });  
        
        
    //重新整理頁面
    window.onload = function() {
      	  document.getElementById("nm_iframe").onload = function() {
       		  setTimeout(function(){
      		  	location.reload(true);  
       		  },500);
      	  }
      	  document.getElementById("nm_iframe2").onload = function() {
       		  setTimeout(function(){
      		  	location.reload(true);  
       		  },500);
      	  }
      };
      
</script>

</body>


</html>