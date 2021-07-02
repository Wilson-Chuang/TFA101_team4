<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , shopping.product"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
 <title>結帳</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/product_list/css/ShoppingCart.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/product_list/css/check_out2.css">
 <script src="<%=request.getContextPath()%>/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
 <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
 </head>
<body>


<div class="check_out">
		<div class="process_1">
			<p>確認購買明細</p>
			<i class="far fa-check-circle"></i>
		</div>
		<div class="process_2">
			<p>填寫收件資料與付款方式</p>
			<i class="far fa-edit"></i>
		</div>
		<div class="process_2">
			<p>完成訂購</p>
			<i class="far fa-handshake"></i>
		</div>
		
<!-- 		此處做額外現金付款方式(尚未處理) -->
		<div class="check_order">
			

		<div class="check_order2">
			<div class="check_order_title">
				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;CHECK YOUR ORDER 確認購買明細</h1>
			</div>
			<table>	
				<tr>
					<th width="200px"></th>
					<th width="520px">商品名稱</th>
					<th width="80px">數量</th>
					<th width="150px">價格</th>
					<th width="150px">小計</th>
					<th></th>
				</tr>
				
	<%  @SuppressWarnings("unchecked")
		Vector<product> buylist = (Vector<product>) session.getAttribute("shoppingcart");
		Integer minus =  (Integer) request.getAttribute("minus");
		String amount1 =  (String) request.getAttribute("amount1");
		String amount2 =  (String) request.getAttribute("amount2");
		String subQuantity2 =  (String) request.getAttribute("subQuantity2");
	%>
	<%	for (int i = 0; i < buylist.size(); i++) {
			product order = buylist.get(i);
			String name = order.getName();
			Integer price = order.getPrice();
			Integer quantity = order.getQuantity();
			Integer subtotal = order.getQuantity()*order.getPrice();
			String img_name = order.getImg_name();
			Integer product_no = order.getProduct_no();
	%>
				<tr>
					
					<td width="200px">
						<a href="${pageContext.request.contextPath}/product/product.do?product_no=<%=product_no%>&action=getOne_For_Display">
							<img src="/upload/<%=img_name%>">
						</a>
					</td>
					<td width="520px">
						<a 	style="color:black"
							href="${pageContext.request.contextPath}/product/product.do?product_no=<%=product_no%>&action=getOne_For_Display">
							<%=name%>
							<c:if test="<%=order.getProduct_discount_no()==3%>">
							<font style="color:red;font-weight:bold;">
							【搭配買<%=order.getProduct_discount_detail_buy_count()%>送<%=order.getProduct_discount_detail_get_count()%>
							活動額外贈送<fmt:formatNumber type="number" maxFractionDigits="0" 
		                   		value="<%=(order.getQuantity()-order.getQuantity()%order.getProduct_discount_detail_buy_count())*order.getProduct_discount_detail_buy_times_get()%>" />
		                   		件】</font></c:if>
						</a>					
					</td>
					
					<td width="80px"><%=quantity%></td>	
					<td width="150px"><%=price%></td>
					<td width="150px"><%=subtotal%></td>
					<td>
						 <i class="far fa-trash-alt"></i>
					     <form name="deleteForm" action="<%=request.getContextPath()%>/product_list/shopping.html" 
                        			method="POST" target="nm_iframe4">
                            <input type="hidden" name="action"  value="DELETE">
                            <input type="hidden" name="del" value="<%= i %>">
                            <input type="submit" value="X" class="delete_button" id="delete_button">
                        </form>
						<iframe id="nm_iframe4" name="nm_iframe4" style="display:none;" ></iframe>
					
					</td>
				</tr>
	<%
		}
	%>
		</table>
		</div>
		<div class="discount_minus" style="color:black; border-top:0.5px solid rgb(214, 214, 214); padding-top:20px;">
			<p>總金額: <%=amount1%>元</p>
		</div>
		
		<c:if test="${minus == 0}">
			<div class="discount_minus">
				<p>無折價優惠</p>
			</div>
		</c:if>
		
		
		
		 <c:if test="${minus != 0}">
			<div class="discount_minus">
				<p>優惠碼折價: <%=minus%>元</p>
			</div>
		</c:if>
		
		
		<div class="total_cost">
			<p>本筆訂單金額(共<%=subQuantity2%>件)</p>

	
			<div class="total_cost_box">$  <%=amount2%></div>
		</div>
	</div>
		<div class="finish_button">
<!-- 			<input type="submit" value="下一步" class="next"  -->
<%-- 					onclick="location.href='<%=request.getContextPath()%>/product_list/Checkout3.jsp'"> --%>
			<input type="button" style="background-color:black; color:white;" value="繼續購物" class="next2" 
					onclick="location.href='<%=request.getContextPath()%>/product_list/product_homePage.jsp'">
			<form name="checkoutForm" action="<%=request.getContextPath()%>/product_list/shopping.html" method="POST">
			
<%-- 			<input type="hidden" name="minus" value="<%=minus%>"> --%>
			<input type="hidden" name="action"  value="CHECKOUT3">
			<input type="submit" value="付款去" class="next">
			</form>
		</div>




<script>
	$(document).ready(function(){
	  $(".delete_button").click(function(){	  
		  
		  setTimeout(function(){
		  location.reload(false);
		  },500);
		  
		  
      });
	  
	  
	   $('input[type="radio"]').click(function(){
           if($(this).attr("value")=="1"){
               $(".credit_card_info").hide();
           }
           if($(this).attr("value")=="2"){
               $(".credit_card_info").hide();
           }
           if($(this).attr("value")=="3"){
               $(".credit_card_info").show();
           }


       });
    });
	
    window.onload = function() {
  	  document.getElementById("nm_iframe4").onload = function() {
   		  setTimeout(function(){
  		  	location.reload(true);  
   		  },500);
  	  }
  };
  
	  var isShow = false;
	  function show22() {
	      if(!isShow) {
	          isShow = true;
	         document.getElementById('hide22').style.display='';
	         document.getElementById('show22').innerText = "【如何取得積分?】";
	     }
	     else {
	         isShow = false;
	         document.getElementById('hide22').style.display='none';
	         document.getElementById('show22').innerText = "【如何取得積分?】";
	     }
	  }
	  
	
	  
	
	    onload = function (){
	    
	       var year=new Date().getFullYear(); //獲取當前年份
	     
	       var sel = document.getElementById ('sel');//獲取select下拉列表
	       for ( var i = year; i < year+10; i++)//迴圈新增2006到當前年份加3年的每個年份依次新增到下拉列表
	       {
	           var option = document.createElement ('option');
	           option.value = i;
	           var txt = document.createTextNode (i);
	           option.appendChild (txt);
	           sel.appendChild (option);
	       }
	        
	       var sel = document.getElementById ('sel2');//獲取select下拉列表
	       for ( var i = 1970; i < year+1; i++)//迴圈新增2006到當前年份加3年的每個年份依次新增到下拉列表
	       {
	           var option = document.createElement ('option');
	           option.value = i;
	           var txt = document.createTextNode (i);
	           option.appendChild (txt);
	           sel.appendChild (option);
	       }
	       
	       var sel = document.getElementById ('sel3');//獲取select下拉列表
	       for ( var i = 1; i <= 31; i++)//迴圈新增2006到當前年份加3年的每個年份依次新增到下拉列表
	       {
	           var option = document.createElement ('option');
	           option.value = i;
	           var txt = document.createTextNode (i);
	           option.appendChild (txt);
	           sel.appendChild (option);
	       }     
	    }
	    
	    function next(obj) { 
	    	if (obj.value.length == obj.maxLength) { 
	    		do { 
	    			obj = obj.nextSibling; 
	    		} while (obj.nodeName != "INPUT"); 
	    		obj.focus(); 
	    	}		 
	    } 

	    document.forms[0].N1.focus(); 
	       


</script>	

	<script src="<%=request.getContextPath()%>/product_list/js/check_out.js"></script>
</body>
</html>