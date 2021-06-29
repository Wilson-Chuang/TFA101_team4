<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.* , shopping.product"%>


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


<%
	MemberService memberSvc = new MemberService();
	MemberVO memberVO = memberSvc.GET_ONE_BY_ID(1);
	pageContext.setAttribute("memberVO", memberVO);
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
    <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/flip-clock.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<body>
    <button id="sidebar-btn" class="sidebar-btn"><i class="fas fa-shopping-cart"></i>
	<% 
	Integer quantity = 0;
	Integer price = 0;
	Integer total = 0;
	Integer subtotal = 0;
	@SuppressWarnings("unchecked") 
    	Vector<product> buylist = (Vector<product>) session.getAttribute("shoppingcart");%>
			<%if (buylist != null && (buylist.size() > 0)) {
				for (int i = 0; i < buylist.size(); i++) {			
					product order = buylist.get(i);
					String name = order.getName();
					price += order.getPrice();
					quantity += order.getQuantity();
					
					subtotal += order.getQuantity()*order.getPrice();
					String img_name = order.getImg_name();
					Integer product_no = order.getProduct_no();
				}
			%>				
					<div class="buylist_count"><%=quantity%></div>		
			<%}%>
	</button>
    <section id="sidebar" class="sidebar">
	<jsp:include page="/product_list/Cart2.jsp" flush="true" />
    </section>
    <div id="content" class="content" style="background-color:white">
       <header>
            <div class="header_top">
                <h6><a href="">BACK TO GUIDE FOOD</a></h6>
            </div>
            <div class="header_bottom">
                <div class="logo"><a href="${pageContext.request.contextPath}/product_list/product_homePage.jsp">
                        <h1>GF.SHOP</h1>
                    </a></div>
                <div class="search">
                
                	<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/product/product.do" id="form">
	                    <input class="search_bar" type="text" name="product_name" id="search" placeholder="Search for items">
	                    <input type="hidden" name="action" value="search_front">
	                    <input type="submit" value="送出" class="search_submit">
	                    <button class="search_button"><i class="fas fa-search"></i></button>
                    </FORM>
                </div>
                <a href="">
                    <div class="money">
                        <p>GP:</p>
                    </div>
                    <div class="money_number">${memberVO.member_point}</div>
                </a>
                <button class="user"><i class="far fa-user"></i></button>
                <button class="cart" id="sidebar-btn"><i class="far fa-clipboard"></i></button>
            </div>
        </header>
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
		 <c:if test="${productVO.product_status == 1}">
                    <li>    
                        <div class="hot_sales_product">              
	                        <a href="${pageContext.request.contextPath}/product/product.do?product_no=${productVO.product_no}&action=getOne_For_Display">
	                        	<img src="/upload/${productVO.product_img_name}">
	                        </a>
                       
          		
                        <div class="hot_sales_product_info">
                        <a href="${pageContext.request.contextPath}/product/product.do?product_no=${productVO.product_no}&action=getOne_For_Display">
                                <h2>${productVO.product_name}</h2>
                        </a>
                            <div class="hot_sales_content">
                            
                            
<!--                             -------------------------------優惠為1時顯示------------------------------- -->
                            <c:if test="${productVO.product_discount_no == 1}">
                            	<p style="color:red; bottom:60px; font-weight:bold;">$<fmt:formatNumber type="number" maxFractionDigits="0" 
                    		value="${productVO.product_point*productVO.product_discount_detail_rate/10}" /></p>
                    			<p style="color:white; bottom:65px; left:117px;font-weight:bold;background-color:rgb(255, 147, 165);
                    				width:80px;font-size:16px; padding:5px;text-align:center;">
		                        	${productVO.product_discount_detail_rate}折優惠
		                        </p>
                    			<p style="text-decoration: line-through;">$${productVO.product_point}</p>
                            </c:if>
                            
<!--                             -------------------------------優惠為2時顯示------------------------------- -->                            
                             <c:if test="${productVO.product_discount_no == 2}">
		                        <p style="color:white; bottom:65px; left:117px;font-weight:bold;background-color:rgb(255, 152, 112);width:80px;font-size:16px; padding:5px;text-align:center;">
		                        	折價券
		                        </p>
		                        <p>$${productVO.product_point}</p>
                     		</c:if>                            
                            
                            
                            
<!--                             -------------------------------優惠為3時顯示------------------------------- -->                            
                             <c:if test="${productVO.product_discount_no == 3}">
		                        <p style="color:white; bottom:65px; left:117px;font-weight:bold;background-color:cornflowerblue;width:80px;font-size:16px; padding:5px;text-align:center;">
		                        	買${productVO.product_discount_detail_buy_count}送${productVO.product_discount_detail_get_count}
		                        </p>
		                        <p>$${productVO.product_point}</p>
                     		</c:if>
<!--                             -------------------------------優惠為4時顯示------------------------------- -->                            
<%--                             <c:if test="${productVO.product_discount_no == 4}"> --%>
                                <p>$${productVO.product_point}</p>
<%--                             </c:if> --%>
                                
                                
                                
                                
                                
                            <form name="shoppingForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
            			method="POST" target="nm_iframe2">
         					<input type="submit" value=" &nbsp加入購物車 &nbsp" class="cart_submit2">
                            <input type="hidden" name="img_name" value="${productVO.product_img_name}">
                            <input type="hidden" name="name" value="${productVO.product_name}">
<!--                             -------------------------------優惠為1時的價錢------------------------------- -->                   
                             <c:if test="${productVO.product_discount_no == 1}">
                            	<input type="hidden" name="price" value="${productVO.product_point*productVO.product_discount_detail_rate/10}">
                             </c:if>
<!--                             -------------------------------優惠不為1時的價錢------------------------------- -->                           
                            <c:if test="${productVO.product_discount_no != 1}">
                            	<input type="hidden" name="price" value="${productVO.product_point}">
                            </c:if>
                                
                            <input type="hidden" name="product_discount_detail_buy_count" value="${productVO.product_discount_detail_buy_count}">
                            <input type="hidden" name="product_discount_detail_get_count" value="${productVO.product_discount_detail_get_count}">
                            <input type="hidden" name="product_discount_no" value="${productVO.product_discount_no}">
                            
                            <input type="hidden" name="product_no" value="${productVO.product_no}">
                            <input type="hidden" name="quantity" value="1">
                            <input type="hidden" name="action" value="ADD">
                             </form>
                            </div>
                        </div>
                        </div>                      
                    </li>
         </c:if>                           
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
         <c:if test="${productVO.product_status == 1}">
            <div class="new_arrival_product">          
                <a href="${pageContext.request.contextPath}/product/product.do?product_no=${productVO.product_no}&action=getOne_For_Display">
                	<img src="/upload/${productVO.product_img_name}">
                </a>       

                <div class="new_arrival_desc">
                    <p>
                       <c:forEach var="product_categoryVO" items="${product_categorySvc.all}">
                    		<c:if test="${productVO.product_category_no==product_categoryVO.product_category_no}">
	                   			${product_categoryVO.product_category_name}
                    		</c:if>
                		</c:forEach>                                      
                    </p>
                    <a href="${pageContext.request.contextPath}/product/product.do?product_no=${productVO.product_no}&action=getOne_For_Display">
                        <h2>${productVO.product_name}</h2>
                   	</a>
                    <div class="new_arrival_content">
<!--                             -------------------------------優惠為1時顯示------------------------------- -->                           

                    <c:if test="${productVO.product_discount_no == 1}">
                    	<p style="color:red; bottom:28px; font-weight:bold; font-size:24px;">
                    	$<fmt:formatNumber type="number" maxFractionDigits="0" 
                    		value="${productVO.product_point*productVO.product_discount_detail_rate/10}" />                 		
                    	</p>
                        <p style="color:white; bottom:32px; left:120px; font-weight:bold;background-color:rgb(255, 147, 165);
                        width:80px;font-size:16px; padding:5px;text-align:center;">
                        	${productVO.product_discount_detail_rate}折優惠
                        </p>
                     	<p style="text-decoration: line-through;">$${productVO.product_point}</p>
                    </c:if>
                    
                    
<!--                             -------------------------------優惠為2時顯示------------------------------- -->                            
                             <c:if test="${productVO.product_discount_no == 2}">
		                        <p style="color:white; bottom:32px; left:120px; font-weight:bold;background-color:rgb(255, 152, 112);
                        width:80px;font-size:16px; padding:5px;text-align:center;">
		                        	折價券
		                        </p>
		                        <p>$${productVO.product_point}</p>
                     		</c:if>                            
                                                
<!--                             -------------------------------優惠為3時顯示------------------------------- -->                           
                   
                      <c:if test="${productVO.product_discount_no == 3}">
                        <p style="color:white; bottom:32px; left:120px; font-weight:bold;background-color:cornflowerblue;width:80px;font-size:16px; padding:5px;text-align:center;">
                        	買${productVO.product_discount_detail_buy_count}送${productVO.product_discount_detail_get_count}
                        </p>
                        <p>$${productVO.product_point}</p>
                     </c:if>
                     
<!--                             -------------------------------優惠為4時顯示------------------------------- -->                           
                    
                    <c:if test="${productVO.product_discount_no == 4}">
                        <p>$${productVO.product_point}</p>
                    </c:if>     
                        
                        
 					<form name="shoppingForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
            			method="POST" target="nm_iframe">
                            <input type="submit" value=" &nbsp加入購物車 &nbsp" class="cart_submit">
                            <input type="hidden" name="img_name" value="${productVO.product_img_name}">
                            <input type="hidden" name="name" value="${productVO.product_name}">
                            
                            
<!--                             -------------------------------優惠為1時的價錢------------------------------- -->                           
                            
                             <c:if test="${productVO.product_discount_no == 1}">
                            	<input type="hidden" name="price" value="${productVO.product_point*productVO.product_discount_detail_rate/10}">
                             </c:if>
<!--                             -------------------------------優惠不為1時的價錢------------------------------- -->                           
                            
                            <c:if test="${productVO.product_discount_no != 1}">
                            	<input type="hidden" name="price" value="${productVO.product_point}">
                            </c:if>
                                
                            
                            <input type="hidden" name="product_no" value="${productVO.product_no}">
                            <input type="hidden" name="product_discount_detail_buy_count" value="${productVO.product_discount_detail_buy_count}">
                            <input type="hidden" name="product_discount_detail_get_count" value="${productVO.product_discount_detail_get_count}">
                            <input type="hidden" name="product_discount_no" value="${productVO.product_discount_no}">
                            
                            <input type="hidden" name="quantity" value="1">
                            <input type="hidden" name="action" value="ADD">
                    </form>
                    </div>
                </div>
            </div>
         </c:if>
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