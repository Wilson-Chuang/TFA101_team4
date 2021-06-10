<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , shopping.product" %>
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
    Vector<product> buylist = (Vector<product>) session.getAttribute("shoppingcart");%>
	<%if (buylist != null && (buylist.size() > 0)) {%>
  
		<div class="shopping_cart_product">
            <ul>
            		
            	<% for (int index = 0;index < buylist.size(); index++) {
					product order = buylist.get(index); %>
					
                <li id="cart_for_delete<%=index%>">
                    <img src="/upload/<%=order.getImg_name()%>" alt="">
                    <div class="shopping_cart_detail">
                        <h2><%=order.getName()%></h2>
                        <h3><%=order.getPrice()%>  GP</h3>
                        <h4>數量:<%=order.getQuantity()%></h4>

<!-- 						<input type="number" min="1" max="100"> -->
                     
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
            
            <div class="check_out">
                <form name="checkoutForm" action="<%=request.getContextPath()%>/product_list/shopping.html" method="POST">
                    <input type="hidden" name="action"  value="CHECKOUT"> 
                    <input type="submit" value="CHECK OUT" class="check_out_button">
                </form>	
<%}%>
                    <a href="javascript:void(0)" id="back_a"><input type="button" value="Back" class="back_button"></a>
            </div>
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