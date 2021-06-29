<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.product.model.*"%>

<%
	ProductService productSvc = new ProductService();
	List<ProductVO> list2 = productSvc.getAll();
	pageContext.setAttribute("list2", list2);
%>

<%
	Product_categoryService product_categorySvc2 = new Product_categoryService();
	List<Product_categoryVO> list = product_categorySvc2.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	Set set = new LinkedHashSet();
	set.add(request.getAttribute("set"));
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/product_list.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/sidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/header.css">
    <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   
</head>

<body>
    <button id="sidebar-btn" class="sidebar-btn"><i class="fas fa-shopping-cart"></i></button>
    <section id="sidebar" class="sidebar">
	<jsp:include page="/product_list/Cart.jsp" flush="true" />
    </section>
    <div id="content" class="content">
        <header>
            <div class="header_top">
                <h6><a href="">BACK TO GUIDE FOOD</a></h6>
            </div>
            <div class="header_bottom">
                <div class="logo"><a href="${pageContext.request.contextPath}/product_list/product_homePage.jsp">
                        <h1>GF.SHOP</h1>
                    </a></div>
                <div class="search">
                    <input class="search_bar" type="text" name="search" id="search" placeholder="Search for items">
                    <button class="search_button"><i class="fas fa-search"></i></button>
                </div>
                <a href="">
                    <div class="money">
                        <p>GP:</p>
                    </div>
                    <div class="money_number">5,000</div>
                </a>
                <button class="user"><i class="far fa-user"></i></button>
                <button class="cart" id="sidebar-btn"><i class="fas fa-shopping-cart"></i></button>
            </div>
        </header>




        <div class="main">
            <div class="side_nav">
                <ul>
                <c:forEach var="product_categoryVO" items="${list}">
                    <li>
                    		<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/product_category/product_category.do">
									<input type="submit" value="檢視">
									<input type="hidden" name="product_category_no" value="${product_categoryVO.product_category_no}"> 
									<input type="hidden" name="action" value="see_category_product">
							</FORM>
							<a href="#">${product_categoryVO.product_category_name}</a>
					</li>
                </c:forEach>
                </ul>                
            </div>
	
		
		
            <div class="product_list">
                  
            <c:forEach var="productVO" items="${set}">
         
                <div class="product">
                    <a href="">
                    
                    <img src="/upload/${productVO.product_img_name}">
                    
                    </a>
                    
                     	<form METHOD="get" ACTION="<%=request.getContextPath()%>/product/product.do">                      
                        <input type="submit" value="連結到商品資訊" class="product_info1">
                        <input type="hidden" name="product_no" value="${productVO.product_no}">
                        <input type="hidden" name="action" value="getOne_For_Display"> 
                        </form>
                        
                    <div class="product_info">
                        <p>${product_categoryVO.product_category_name}</p>
                        <a href="">
                            <h2>${productVO.product_name}</h2>
                        </a>
                        <div class="product_price">
                            <p>${productVO.product_point} GP</p>
  							<form name="shoppingForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
            									method="POST" target="nm_iframe">
                            <input type="submit" value="加入購物車" class="cart_submit">
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

            
        </div>
    </div>
    

 
    <script src="${pageContext.request.contextPath}/product_list/js/product_list.js"></script>
    <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/sidebar.js"></script>
    <script>
    $(".cart_submit").each(function () {
        $(".cart_submit").click(function () {
            swal("已完成", "成功將此商品加入購物車!", "success");
// 			   alert("已完成", "成功將此商品加入購物車!", "success");
        });
    });  
    
    window.onload = function() {
    	  document.getElementById("nm_iframe").onload = function() {
     		  setTimeout(function(){
    		  	location.reload(true);  
     		  },500);
    	  }
    };
    
    </script>
</body>

</html>