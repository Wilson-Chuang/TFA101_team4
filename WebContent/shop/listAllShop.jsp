<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ShopService shopSvc = new ShopService();
	List<ShopVO> list = shopSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<html>
<head>
<title>商家資料 - listAllShop.jsp</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/bootstrap/css/bootstrap.min.css">


<style>
  ul {
    list-style: none;
  }
  div.main_content {
    width: 100%;
    float: left;
  }
  .firstline{
  	margin:20px 0;
  }

  .rightcontent{
  	margin-left:20%;
  	width: 1000px; 
  }

  table {
 	width: 950px; 
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  tr{
    padding-top: 15px;
   vertical-align: middle;
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
 	#preview { 
       border: 1px solid lightgray; 
       display: table; 
       width: 100%; 
/*        min-height: 150px;  */
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
/*      #preview_m { */
/*       border: 1px solid lightgray; */
/*       display: table; */
/*       width: 100%; */
/*       min-height: 150px; */
/*       position: relative; */
/*     } */
/*     #preview_m span.text { */
/*       position: absolute; */
/*       display: inline-block; */
/*       left: 50%; */
/*       top: 50%; */
/*       transform: translate(-50%, -50%); */
/*       z-index: 1; */
/*       color: lightgray; */
/*     } */
/*     #preview_m img.preview_img { */
/*       width: 100%; */
/*     } */
    button{
  	border: none;
  	background-color: white;	
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
    
    <div class="firstline">
		<nav aria-label="breadcrumb">
		  <h3>商家管理</h3>
		</nav>
		<div>
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs1}">
				<font style="color:red">請修正以下錯誤:</font>
				<div>
				    <c:forEach var="message" items="${errorMsgs1}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</div>
			</c:if>
		</div>
    </div>

	<div class="rightcontent" width="1000px;">
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<table style="width:980px; table-layout:fixed; word-wrap:break-word; word-break ; break-all;">
			<tr>
				<th>商家<br>編號</th>
				<th>會員<br>編號</th>
				<th>統一<br>編號</th>
				<th>商家<br>名稱</th>
		<!-- 		<th>郵遞區號</th>  -->
				<th>縣市</th>
		<!--		<th>地址</th> -->
		<!-- 		<th>緯度</th> -->
		<!--		<th>經度</th> -->
		<!-- 		<th>商家介紹</th>-->
		<!-- 		<th>標籤</th> -->
				<th>評價</th>
				<th>評價<br>總數</th>
		<!--		<th>評價總和</th> -->
				<th>Email</th>
				<th>聯絡<br>電話</th>
		<!--		<th>均消</th> -->
		<!--		<th>營業時間</th> -->
				<th width="100px">網站</th>
				<th>圖片</th> 
			<!--	<th>圖片庫</th> -->
			<!--	<th>建立<br>時間</th> -->
				<th>更新<br>時間</th>
				<th>總瀏<br>覽數</th>
		<!-- 		<th>預約功能</th>-->
				<th>修改</th>
		<!--		<th>刪除</th>-->
			</tr>
			<%@ include file="pages/page1.file" %> 
			<c:forEach var="shopVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<tr>
					<td>${shopVO.shop_id}</td>
					<td><c:forEach var="memberVO" items="${memberSvc.all}">
		                    <c:if test="${shopVO.member_id==memberVO.member_id}">
			                    ${memberVO.member_id}
		                    </c:if>
		                </c:forEach>
					</td>
					<td>${shopVO.shop_tax_id}</td>
					<td>${shopVO.shop_name}</td>
		<!-- 		<td>${shopVO.shop_zip_code}</td> -->
					<td>${shopVO.shop_city}</td>
		<!--		<td>${shopVO.shop_address}</td>  -->
		<!--		<td>${shopVO.shop_latitude}</td>  -->	
		<!--		<td>${shopVO.shop_longitude}</td>  -->
		<!--		<td>${shopVO.shop_description}</td> -->
		<!--		<td>${shopVO.shop_tag}</td>  -->
					<td>${shopVO.shop_rating}</td>
					<td>${shopVO.shop_rating_count}</td>
		<!--		<td>${shopVO.shop_rating_total}</td>  -->
					<td>${shopVO.shop_email}</td>
					<td>${shopVO.shop_phone}</td>
		<!--			<td>${shopVO.shop_price_level}</td>  -->
		<!--			<td>
						<c:set var="shop_opening_time" value="${shopVO.shop_opening_time}"/>	
						<c:set var="remove01" value="${fn:replace(shop_opening_time, '[\"', '')}"/>
						<c:set var="remove02" value="${fn:replace(remove01, '\"]', '')}"/>
						<c:set var="remove03" value="${fn:replace(remove02, '\\\\u2013', '–')}"/>
						<c:forTokens items="${remove03}" delims='\",\"' var="value01">
								${value01}<br><br>
						</c:forTokens>
					</td>		-->
					<td width="100px">${shopVO.shop_website}</td>
					<td>
					<div id="preview">
					<c:set var="shop_main_img" value="${shopVO.shop_main_img}"/>
						<c:choose>					
							<c:when test="${fn:length(shop_main_img) lt 1}">
								<span class="text">查無圖片</span>
							</c:when>
							 <c:otherwise>
								<div><img class='preview_img' src='<%=request.getContextPath()%>/uploads/shop/${shopVO.shop_tax_id}/images/${shopVO.shop_main_img}'
								 title='${shopVO.shop_main_img}'/></div>				
							 </c:otherwise>
						</c:choose>
					</div>
					</td> 
				<!--	<td>
						<div id="preview_m">
						<c:set var="shop_gallery" value="${shopVO.shop_gallery}"/>
						<c:choose>
							<c:when test="${fn:length(shop_gallery) lt 1}">
								<span class="text">查無圖片</span>
							</c:when>
							 <c:otherwise>
								<div>						
									<c:set var="remove1" value="${fn:replace(shop_gallery, '[', '')}"/>
									<c:set var="remove2" value="${fn:replace(remove1, ']', '')}"/>
									<c:set var="remove3" value="${fn:replace(remove2, '\"', '')}"/>
									<c:set var="remove4" value="${fn:replace(remove3, ' ', '')}"/>
									<c:forTokens items="${remove4}" delims="," var="value">
									   <img class='preview_img' src='<%=request.getContextPath()%>/uploads/shop/${shopVO.shop_tax_id}/gallery/${value}' title='${value}'/>
									</c:forTokens>
				      			</div>
							 </c:otherwise>
						</c:choose>
			   			</div></td>  -->
				<!--	<td><fmt:formatDate value="${shopVO.shop_create_time}" pattern="yyyy/MM/dd HH:mm"/></td> -->
					<td><fmt:formatDate value="${shopVO.shop_update_time}" pattern="yyyy/MM/dd HH:mm"/></td>
					<td>${shopVO.shop_total_view}</td>
				<!--	<td>${shopVO.shop_reserv_status}</td>	-->	
					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop/shop.do" style="margin-bottom: 0px;">
					     <button type="submit"><i class="fas fa-edit fa-2x"></i></button>
					     <input type="hidden" name="shop_id"      value="${shopVO.shop_id}">
					     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--><!-- 目前尚未用到  -->
					     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
					</td>
					
				</tr>
			</c:forEach>
		</table>
		<%@ include file="pages/page2.file" %>
		
		
		<!--  
		<br>本網頁的路徑:<br><b>
		   <font color=blue>request.getServletPath():</font> <%=request.getServletPath()%><br>
		   <font color=blue>request.getRequestURI(): </font> <%=request.getRequestURI()%> </b>
		-->
	</div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="./vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>


</body>
</html>