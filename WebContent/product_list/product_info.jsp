<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.* , shopping.product"%>
<%@ page import="com.member.model.*"%>

<%
  ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<%
// 	MemberService memberSvc = new MemberService();
// 	MemberVO memberVO = memberSvc.GET_ONE_BY_ID(1);
// 	pageContext.setAttribute("memberVO", memberVO);
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

<html>
<head>
 <title>${productVO.product_name}</title>
 <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
 <script src="${pageContext.request.contextPath}/product_list/js/product_info.js"></script>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/product_info.css">
 <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/sidebar.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/product_list/css/cart.css">
 <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
 <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
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
    
   
     <div id="content" class="content" style="background-color:white">
     
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
<!---------------------------------------商品介紹------------------------------------------------>
     <form name="shoppingForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
            			method="POST" target="nm_iframe">
        <div class="product_detail_img">
            <img src="/upload/${productVO.product_img_name}" alt="">
        </div>

        <div class="product_detail_info">
            <div class="pd_title">${productVO.product_name}</div>
            <div class="pd_info">${productVO.product_intro}</div>
            <div class="pd_quantity">剩餘庫存:&nbsp;${productVO.product_stock_quantity}</div>
<!--             ------------------------------優惠為1時顯示------------------------------ -->
            <c:if test="${productVO.product_discount_no == 1}">
	            <div class="pd_price" style="font-size:20px; text-decoration: line-through; color:gray;">
	            	<font style="font-size:10px;">$&nbsp;</font>${productVO.product_point}
	 			</div>
	 			<div class="pd_price" style="left:90px; width:200px;">
	            	<font style="font-size:25px;">$</font>
	            	<fmt:formatNumber type="number" maxFractionDigits="0" 
                    		value="${productVO.product_point*productVO.product_discount_detail_rate/10}" />                    
	 			</div>
	 			<div class="pd_price" style="left:280px; font-size:20px; 
	 			text-align:center;padding:10px;background-color:rgb(255, 147, 165);
	 			color:white; width:320px; font-weight:bold;box-shadow:0px 0px 12px gray; letter-sapcing:1px;">
	 			【限時${productVO.product_discount_detail_rate}折優惠，欲購從速!】</div>
            </c:if>
            
<!--             ------------------------------優惠為2時顯示------------------------------ -->        
            <c:if test="${productVO.product_discount_no == 2}">
	            <div class="pd_price">
	            	<font style="font-size:25px;">$&nbsp;</font>${productVO.product_point}
	 			</div>

	 			<div class="pd_price" style="left:280px; font-size:16px; 
	 			text-align:center;padding:10px;background-color:rgb(255, 152, 112);
	 			color:white; width:320px; font-weight:bold;box-shadow:0px 0px 12px gray; letter-sapcing:1px;">
	 			優惠折價輸入:【${productVO.product_discount_detail_coupon}】</div>
            </c:if>            
            
            
<!--             ------------------------------優惠為3時顯示------------------------------ -->        
            <c:if test="${productVO.product_discount_no == 3}">
	            <div class="pd_price">
	            	<font style="font-size:25px;">$&nbsp;</font>${productVO.product_point}
	 			</div>

	 			<div class="pd_price" style="left:280px; font-size:20px; 
	 			text-align:center;padding:10px;background-color:cornflowerblue;
	 			color:white; width:320px; font-weight:bold;box-shadow:0px 0px 12px gray; letter-sapcing:1px;">
	 			【活動買${productVO.product_discount_detail_buy_count}送${productVO.product_discount_detail_get_count}，數量有限!】</div>
            </c:if>
            
            
<!--             ------------------------------優惠為4時顯示------------------------------ -->            
            <c:if test="${productVO.product_discount_no == 4}">
	            <div class="pd_price">
	            	<font style="font-size:25px;">$&nbsp;</font>${productVO.product_point}
	 			</div>
 			</c:if>
            
            <div class="pd_QTY">
                <div class="input-group">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default btn-number" disabled="disabled" data-type="minus" data-field="quantity">
                            <span class="glyphicon glyphicon-minus"></span>
                        </button>
                    </span>
                    <input type="text" name="quantity" class="form-control input-number" value="1" min="1" max="${productVO.product_stock_quantity}">
                    <span class="input-group-btn">
                        <button type="button" class="btn btn-default btn-number" data-type="plus" data-field="quantity">
                            <span class="glyphicon glyphicon-plus"></span>
                        </button>
                    </span>
                    
                </div>
			
            </div>
            
            <div class="pd_cart">
                <input type="submit" value="加入購物車" class="pd_cart_button">
               				<input type="hidden" name="img_name" value="${productVO.product_img_name}">
                            <input type="hidden" name="name" value="${productVO.product_name}">
                            
                            
                            
                             <c:if test="${productVO.product_discount_no == 1}">
                            	<input type="hidden" name="price" value="${productVO.product_point*productVO.product_discount_detail_rate/10}">
                             </c:if>
                            
                            <c:if test="${productVO.product_discount_no != 1}">
                            	<input type="hidden" name="price" value="${productVO.product_point}">
                            </c:if>
                            
                            <input type="hidden" name="product_no" value="${productVO.product_no}">
                            <input type="hidden" name="product_discount_detail_buy_count" value="${productVO.product_discount_detail_buy_count}">
                            <input type="hidden" name="product_discount_detail_get_count" value="${productVO.product_discount_detail_get_count}">
                            <input type="hidden" name="product_discount_no" value="${productVO.product_discount_no}">
                            <input type="hidden" name="action" value="ADD">      
            </div>
        </div>
        </form>
    	</div>

<iframe id="nm_iframe" name="nm_iframe" style="display:none;" ></iframe>


<script>
      window.onload = function() {
      	  document.getElementById("nm_iframe").onload = function() {
       		  setTimeout(function(){
      		  	location.reload(true);  
       		  },500);
      	  }
      };
      
      $(".pd_cart_button").each(function () {
          $(".pd_cart_button").click(function () {
              swal("已完成", "成功將此商品加入購物車!", "success");

          });
      });  
 </script>
 <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/sidebar.js"></script>
</body>
</html>