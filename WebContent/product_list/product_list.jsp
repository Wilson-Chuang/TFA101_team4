<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.* , shopping.product"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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


<%

	MemberVO MemberVO2 = (MemberVO) session.getAttribute("login");
	pageContext.setAttribute("member_login", MemberVO2);
	if( MemberVO2 !=null){
		MemberService memberSvc = new MemberService();
		MemberVO memberVO = memberSvc.GET_ONE_BY_ID(MemberVO2.getMember_id());
		pageContext.setAttribute("memberVO", memberVO);
	}

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/cart.css">
	<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
   
</head>

<body>
  	<button id="sidebar-btn" class="sidebar-btn"><i class="fas fa-shopping-cart"></i>
	<% 
	Integer quantity = 0;
	Integer price = 0;
	@SuppressWarnings("unchecked") 
    	Vector<product> buylist = (Vector<product>) session.getAttribute("shoppingcart");%>
			<%if (buylist != null && (buylist.size() > 0)) {
				for (int i = 0; i < buylist.size(); i++) {			
					product order = buylist.get(i);
					String name = order.getName();
					price += order.getPrice();
					quantity += order.getQuantity();
					Integer subtotal = order.getQuantity()*order.getPrice();
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
    <div id="content" class="content">
    
     <header>
            <div class="header_top">
                <h6><a href="${pageContext.request.contextPath}/article/article_homepage.jsp">BACK TO GUIDE FOOD</a></h6>
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
                <c:if test="${member_login == null}">
	                <a href="">               
	                    <div class="money">
	                        <p></p>
	                    </div>
	                    <div class="money_number"></div>
	                </a>
	            </c:if>
                
                
                
                <c:if test="${member_login != null}">
	                <a href="">               
	                    <div class="money">
	                        <p>GP:</p>
	                    </div>
	                    <div class="money_number">${memberVO.member_point}</div>
	                </a>
	            </c:if>
                <button class="user"><i class="far fa-user"></i></button>
                <button class="cart" id="sidebar-btn"><i class="far fa-clipboard"></i></button>
            </div>
        </header>
<!---------------------------------------側邊導航------------------------------------------------>
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
	
<!---------------------------------------產品列表------------------------------------------------>	
            <div class="product_list">
                  
            <c:forEach var="productVO" items="${set}">
         	<c:if test="${productVO.product_status == 1}">
                <div class="product">
                    <a href="${pageContext.request.contextPath}/product/product.do?product_no=${productVO.product_no}&action=getOne_For_Display">
                    
                    <img src="/upload/${productVO.product_img_name}">
                    
                    </a>
                        
                    <div class="product_info">
                        <p>${product_categoryVO.product_category_name}</p>
                        <a href="${pageContext.request.contextPath}/product/product.do?product_no=${productVO.product_no}&action=getOne_For_Display">
                            <h2>${productVO.product_name}</h2>
                        </a>
                        <div class="product_price">
                        
<!--             ------------------------------優惠為1時顯示------------------------------ -->
                            
		                    <c:if test="${productVO.product_discount_no == 1}">
		                    	<p style="color:red; bottom:36px; font-weight:bold; font-size:24px;">
		                    	$<fmt:formatNumber type="number" maxFractionDigits="0" 
		                    		value="${productVO.product_point*productVO.product_discount_detail_rate/10}" />		                    		
		                    	</p>
		                    	
		                    	<p style="color:white; bottom:42px; left:148px; font-weight:bold;background-color:rgb(255, 147, 165);
		                        		width:80px;font-size:16px; padding:5px;text-align:center;">
		                        	${productVO.product_discount_detail_rate}折優惠
		                        </p>
		                     	<p style="text-decoration: line-through;">$${productVO.product_point}</p>
		                    </c:if>
 <!--             ------------------------------優惠為2時顯示------------------------------ -->
 		                    <c:if test="${productVO.product_discount_no == 2}">
		                    	
	                        <p style="color:white; bottom:42px; left:148px; font-weight:bold;background-color:rgb(255, 152, 112);width:80px;font-size:16px; padding:5px;text-align:center;">
                        		折價券
                       		 </p>
		                     	<p>$${productVO.product_point}</p>
		                    </c:if>		                    
		                    
		                    
		                    
 <!--             ------------------------------優惠為3時顯示------------------------------ -->
 		                    <c:if test="${productVO.product_discount_no == 3}">
		                    	
	                        <p style="color:white; bottom:42px; left:148px; font-weight:bold;background-color:cornflowerblue;width:80px;font-size:16px; padding:5px;text-align:center;">
                        	買${productVO.product_discount_detail_buy_count}送${productVO.product_discount_detail_get_count}
                       		 </p>
		                     	<p>$${productVO.product_point}</p>
		                    </c:if>
 
 
                                                        
 <!--             ------------------------------優惠為4時顯示------------------------------ -->                           
                             <c:if test="${productVO.product_discount_no == 4}">
                            	<p>$${productVO.product_point}</p>
                             </c:if>
                            
                            
                            
                            
                            
                            
  							<form name="shoppingForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
            									method="POST" target="nm_iframe">
                            <input type="submit" value="加入購物車" class="cart_submit">
                            <input type="hidden" name="img_name" value="${productVO.product_img_name}">
                            <input type="hidden" name="name" value="${productVO.product_name}">
                            
 <!--             ------------------------------優惠為1時的價格------------------------------ -->                           
                             <c:if test="${productVO.product_discount_no == 1}">
                            	<input type="hidden" name="price" value="${productVO.product_point*productVO.product_discount_detail_rate/10}">
                             </c:if>
 <!--             ------------------------------優惠不為1時的價格------------------------------ -->                           
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