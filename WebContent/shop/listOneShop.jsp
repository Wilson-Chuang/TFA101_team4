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

<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/bootstrap/css/bootstrap.min.css">


<style>
  div.main_content {
    width: 100%;
    float: left;
  }
    div.rightcontent {
    width: 80%;
	margin-left:20%;
	padding-right:10px;
  }

  .breadcrumb{
     background-color: white;
     margin:20px 0 0 0;
     font-size:15px;
  }
 
  .back button:focus {
     outline: none;
  }
  .back button{
  	 /* 圓角 */
     border-radius: 5%;
    /* 輸入文字色彩設定 */
     color: rgb(41, 41, 41);
     padding: 5px 10px;
  	 width:150px;
  	 height:50px;
  	 margin:20px 10px;
  	 border-radius:20%;
  	 background-color:#e9e9e9;
  	 border: none;
  }  
  .back button a{
	text-decoration: none; 
	font-size:15px;
	color: rgb(41, 41, 41);
  }
  a{
	text-decoration: none; 
	}
  table {
/* 	width: 950px; */
	background-color: white;
	margin: 5px 5px 5px 0;
  }
  th, td {
    border: 0;
    border-bottom: 1px solid #ccc;
    padding: 5px;
    margin: 0 5px;
    text-align: center;
    height: 80px;
    vertical-align: middle;
  }
  tr{
   padding-top: 15px;
   vertical-align: middle;
   }
#preview {
      border: 1px solid lightgray;
      display: table;
      width: 100%;
/*       min-height: 150px; */
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
      width: 100%;
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

<header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>

<div class="main_content">

    <div>
	<jsp:include page="/cms/header_asideMenu/cmsAsideMenu.jsp" flush="true" />
    </div>


	<div class="rightcontent">    
    <!--麵包屑，請大家對應側邊欄幫忙修改一下以下名稱，因為是bootstrap，所以需載入script-->
    <div>
	<nav aria-label="breadcrumb">
	    <ol class="breadcrumb">
		<li class="breadcrumb-item active" aria-current="page">商家管理</li>
	    </ol>
	</nav>
    </div>


		<table style="width:980px; table-layout:fixed; word-wrap:break-word; word-break ; break-all;">
			<tr>
				<th>商家<br>編號</th>
				<th>會員<br>編號</th>
				<th>統一<br>編號</th>
				<th>商家<br>名稱</th>
<!-- 				<th>郵遞區號</th> -->
				<th>縣市</th>
<!-- 				<th>地址</th> -->
<!-- 				<th>緯度</th> -->
<!-- 				<th>經度</th> -->
<!-- 				<th>商家介紹</th> -->
		<!-- 		<th>標籤</th> -->
				<th>評價</th>
				<th>評價<br>總數</th>
<!-- 				<th>評價總和</th> -->
				<th>Email</th>
				<th>聯絡<br>電話</th>
<!-- 				<th>均消</th> -->
<!-- 				<th>營業時間</th> -->
				<th>網站</th>
<!-- 				<th>圖片</th> -->
				<th>圖片庫</th>
				<th>建立<br>時間</th>
				<th>更新<br>時間</th>
				<th>總瀏<br>覽數</th>
<!-- 				<th>預約功能</th> -->
			</tr>
			<tr>
				<td><%=shopVO.getShop_id()%></td>
				<td><%=shopVO.getMember_id()%></td>
				<td><%=shopVO.getShop_tax_id()%></td>
				<td><%=shopVO.getShop_name()%></td>
<%-- 				<td><%=shopVO.getShop_zip_code()%></td> --%>
				<td><%=shopVO.getShop_city()%></td>
<%-- 				<td><%=shopVO.getShop_address()%></td> --%>
<%-- 				<td><%=shopVO.getShop_latitude()%></td>	 --%>
<%-- 				<td><%=shopVO.getShop_longitude()%></td>	 --%>
<%-- 				<td><%=shopVO.getShop_description()%></td> --%>
		<%-- 		<td><%=shopVO.getShop_tag()%></td> --%>
				<td><%=shopVO.getShop_rating()%></td>
				<td><%=shopVO.getShop_rating_count()%></td>
<%-- 				<td><%=shopVO.getShop_rating_total()%></td> --%>
				<td><%=shopVO.getShop_email()%></td>
				<td><%=shopVO.getShop_phone()%></td>
<%-- 				<td><%=shopVO.getShop_price_level()%></td> --%>
		<!-- 	<td> -->	
<%-- 					<% if(shopVO.getShop_opening_time().length()<1){ %>   --%>
<!-- 					<span class="text">無資料</span> -->
<%-- 					<% }else{ %>      --%>
<%-- 					<% --%>
<!-- 				    	String opening_time = shopVO.getShop_opening_time().replaceAll("[(\\[\")(\"\\])]", "").replaceAll("\\\\u2013", "-"); -->
<!-- 				     	String[] week = opening_time.split(","); -->
<!-- 					    for (int i=0; i<week.length; i++) { -->
<!-- 				    %> -->
<%-- 				    	<% out.print(week[i]); %><br><br>	 --%>
<%-- 					<% }} %>		     --%>
<!-- 				</td> -->
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
<!-- 				<td> -->
<!-- 					<div id="preview_m"> -->
<%-- 					<% if(shopVO.getShop_gallery().length()<1){ %> --%>
<!-- 					<span class="text">查無圖片</span> -->
<%-- 					<% }else{ %>      	 --%>
<!-- 				   	<div> -->
<%-- 				   	<% --%>
<!-- 				    	String filename = shopVO.getShop_gallery().replaceAll("[\\[\\]\"]", ""); -->
<!-- 				     	String[] values = filename.split(", "); -->
<!-- 					    for (int i=0; i<values.length; i++) { -->
<!-- 				    %> -->
<%-- 				      	<img class='preview_img' src='<%=request.getContextPath()%>/uploads/shop/<% out.print(shopVO.getShop_tax_id()); %>/gallery/<% out.print(values[i]); %>'  --%>
<%-- 				      	title='<% out.print(values[i]); %>'/>		 --%>
<%-- 					<% } %> --%>
<!-- 				   	</div> -->
<%-- 			      	<% } %> --%>
<!-- 		   			</div> -->
<!-- 				</td> -->
<%-- 				<td><%=shopVO.getShop_create_time()%></td> --%>
				<td><%=shopVO.getShop_update_time()%></td>
				<td><%=shopVO.getShop_total_view()%></td>
				<td><%=shopVO.getShop_reserv_status()%></td>
			</tr>
		</table>
		
		<div class="back">
			<button>
				<a href="${pageContext.request.contextPath}/shop/listAllShop.jsp">返回商家列表</a>
			</button>
		</div>
	</div>
</div>

<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>