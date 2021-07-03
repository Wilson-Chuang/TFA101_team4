<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller ShopServlet.java已存入request的ShopVO物件--%>
<%
	ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
%>

<%-- 取出 對應的MemberVO物件--%>
<%
  MemberService memberSvc = new MemberService();
  MemberVO memberVO = memberSvc.GET_ONE_BY_ID(shopVO.getMember_id());
%>

<html>
<head>
<title>商家資料 - listOneShop.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
#preview {
      border: 1px solid lightgray;
      display: table;
      width: 100px;
      min-height: 150px;
      position: relative;
    }
    #preview span.text {
      position: absolute;
      display: inline-block;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      color: lightgray;
    }
    #preview img.preview_img {
      width: 100%;
    }
     #preview_m {
      border: 1px solid lightgray;
      display: table;
      width: 100px;
      min-height: 150px;
      position: relative;
    }
    #preview_m span.text {
      position: absolute;
      display: inline-block;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      z-index: 1;
      color: lightgray;
    }
    #preview_m img.preview_img {
      width: 100%;
    }

</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>商家資料 - listOneShop.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商家編號</th>
		<th>會員編號</th>
		<th>統一編號</th>
		<th>商家名稱</th>
		<th>郵遞區號</th>
		<th>縣市</th>
		<th>地址</th>
		<th>緯度</th>
		<th>經度</th>
		<th>商家介紹</th>
<!-- 		<th>標籤</th> -->
		<th>評價</th>
		<th>評價總數</th>
		<th>評價總和</th>
		<th>Email</th>
		<th>聯絡電話</th>
		<th>均消</th>
		<th>營業時間</th>
		<th>網站</th>
		<th>圖片</th>
		<th>圖片庫</th>
		<th>建立時間</th>
		<th>更新時間</th>
		<th>總瀏覽數</th>
		<th>預約功能</th>
	</tr>
	<tr>
		<td><%=shopVO.getShop_id()%></td>
		<td><%=shopVO.getMember_id()%></td>
		<td><%=shopVO.getShop_tax_id()%></td>
		<td><%=shopVO.getShop_name()%></td>
		<td><%=shopVO.getShop_zip_code()%></td>
		<td><%=shopVO.getShop_city()%></td>
		<td><%=shopVO.getShop_address()%></td>
		<td><%=shopVO.getShop_latitude()%></td>	
		<td><%=shopVO.getShop_longitude()%></td>	
		<td><%=shopVO.getShop_description()%></td>
<%-- 		<td><%=shopVO.getShop_tag()%></td> --%>
		<td><%=shopVO.getShop_rating()%></td>
		<td><%=shopVO.getShop_rating_count()%></td>
		<td><%=shopVO.getShop_rating_total()%></td>
		<td><%=shopVO.getShop_email()%></td>
		<td><%=shopVO.getShop_phone()%></td>
		<td><%=shopVO.getShop_price_level()%></td>
		<td>
			<% if(shopVO.getShop_opening_time().length()<1){ %>
			<span class="text">無資料</span>
			<% }else{ %>     
			<%
		    	String opening_time = shopVO.getShop_opening_time().replaceAll("[(\\[\")(\"\\])]", "").replaceAll("\\\\u2013", "-");
		     	String[] week = opening_time.split(",");
			    for (int i=0; i<week.length; i++) {
		    %>
		    	<% out.print(week[i]); %><br><br>	
			<% }} %>		    
		</td>
		<td><%=shopVO.getShop_website()%></td>
		<td>
		<div id="preview">
			<% if(shopVO.getShop_main_img().length()<1){ %>
			<span class="text">查無圖片</span>
			<% }else{ %>
			<div><img class='preview_img' src='<%=request.getContextPath()%>/uploads/shop/<% out.print(shopVO.getShop_tax_id()); %>/images/<%=shopVO.getShop_main_img()%>'
			 title='<%=shopVO.getShop_main_img()%>'/></div>
			<% } %>
		</div>
		</td>
		<td>
			<div id="preview_m">
			<% if(shopVO.getShop_gallery().length()<1){ %>
			<span class="text">查無圖片</span>
			<% }else{ %>      	
		   	<div>
		   	<%
		    	String filename = shopVO.getShop_gallery().replaceAll("[\\[\\]\"]", "");
		     	String[] values = filename.split(", ");
			    for (int i=0; i<values.length; i++) {
		    %>
		      	<img class='preview_img' src='<%=request.getContextPath()%>/uploads/shop/<% out.print(shopVO.getShop_tax_id()); %>/gallery/<% out.print(values[i]); %>' 
		      	title='<% out.print(values[i]); %>'/>		
			<% } %>
		   	</div>
	      	<% } %>
   			</div>
		</td>
		<td><%=shopVO.getShop_create_time()%></td>
		<td><%=shopVO.getShop_update_time()%></td>
		<td><%=shopVO.getShop_total_view()%></td>
		<td><%=shopVO.getShop_reserv_status()%></td>
	</tr>
</table>

</body>
</html>