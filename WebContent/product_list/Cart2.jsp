<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , shopping.product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
 <title>我的購物車</title>
 <script src="${pageContext.request.contextPath}/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
</head>
<body>
	 <div class="shopping_cart_title">
            <h1>Your Cart</h1>
        </div>

    <% @SuppressWarnings("unchecked") 
    Vector<product> buylist = (Vector<product>) session.getAttribute("shoppingcart");
    Integer quantity = 0;
	Integer price = 0;
	Integer subtotal = 0;
    %>
	<%if (buylist != null && (buylist.size() > 0)) {%>
  
		<div class="shopping_cart_product">
            <ul>
            		
            	<% for (int index = 0;index < buylist.size(); index++) {
					product order = buylist.get(index); 
					String name = order.getName();
					price += order.getPrice();
					quantity += order.getQuantity();
					subtotal += order.getQuantity()*order.getPrice();
					String img_name = order.getImg_name();
					Integer product_no = order.getProduct_no();
					
					%>
					
                <li id="cart_for_delete<%=index%>">
                    <img src="/upload/<%=order.getImg_name()%>" alt="">
                    <div class="shopping_cart_detail">
                        <h2><%=order.getName()%></h2>
                        <h3>$ <%=order.getPrice()%></h3>
                        
                        <c:if test="<%=order.getProduct_discount_no()!=3%>">
                        	<h4 style="font-weight:bold;">x&nbsp;&nbsp;<%=order.getQuantity()%></h4>
                        </c:if>
                        
                        <c:if test="<%=order.getProduct_discount_no()==3%>">
	                       <h4 style="font-weight:bold;">x&nbsp;&nbsp;<%=order.getQuantity()%>
	                       	&nbsp;(贈送<fmt:formatNumber type="number" maxFractionDigits="0" 
	                   		value="<%=(order.getQuantity()-order.getQuantity()%order.getProduct_discount_detail_buy_count())*order.getProduct_discount_detail_buy_times_get()%>" />件)</h4> 
                     	</c:if>
                     	
                        <form name="deleteForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
                        			method="POST" target="nm_iframe3">
                            <input type="hidden" name="action"  value="DELETE">
                            <input type="hidden" name="del" value="<%= index %>">
                            <input type="submit" value="X    Remove" class="delete_button" id="delete_button">
                        </form>
                         <iframe id="nm_iframe3" name="nm_iframe3" style="display:none;" ></iframe>
                    </div>
                </li>
                <%}%>
            </ul>
            <form name="checkoutForm" action="<%=request.getContextPath()%>/product_list/shopping.html" method="POST">
            <div class="discount_coupon" style="background-color:white;">
            	<p>輸入優惠代碼: </p><input name="product_discount_detail_coupon" type="text" class="input_coupon">
            </div>
            <div class="subtotal">
            	<div class="subtotal_title">Subtotal</div>
            	<div class="subtotal_price">$<%=subtotal%></div>
            </div>
            <div class="check_out">
                    <input type="hidden" name="action"  value="CHECKOUT"> 
                    <input type="submit" value="CHECK OUT" class="check_out_button">
                
<%}%>
                    <a href="javascript:void(0)" id="back_a"><input type="button" value="Back" class="back_button"></a>           
            </div>
            </form>	
        </div>



<script>
	$(document).ready(function () {
	  $('#back_a').click(function () {
	      $('#sidebar-btn').click();
	  });
	});
</script>

<script>
	$(document).ready(function(){
	  $(".delete_button").click(function(){	  
		  setTimeout(function(){
			  location.reload(false);
		},0);	   
      });
    });
</script>			


</body>
</html>