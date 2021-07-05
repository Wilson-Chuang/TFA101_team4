<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Guide好食${articleVO.article_title}</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/article/css/article_content.css">

<script src="${pageContext.request.contextPath}/article/vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>

</head>


<body>
<!-- 導航 -->
<!-- <div class="article_header"></div> -->



<div class="article_content">

<!-- 標題 -->
    <div class="maintop_header">
        <div class="category">
            <h2>
               	Article
            </h2>
        </div>
    </div>
	<table class="nav">
		<tr>
			<th style="border-left:0;">
				<a href="${pageContext.request.contextPath}/article/article_list2.jsp">推薦美食</a>
			</th>
			<th>
				<a href="${pageContext.request.contextPath}/article/article_list1.jsp">美食開箱</a>
			</th>
			<th>
				<a href="${pageContext.request.contextPath}/article/article_homepage.jsp">專欄首頁</a>
			</th>
			<th>
				<a href="${pageContext.request.contextPath}/article/article_list3.jsp">餐廳介紹</a>
			</th>
			<th>
				<a href="${pageContext.request.contextPath}/article/article_list4.jsp">新店快報</a>
			</th>
		</tr>
	</table>    


<!-- 您的文章 -->	
	<div class="author">
		<img src="${pageContext.request.contextPath}/article/img/model8.jpeg">
		<p>
		 <c:forEach var="memberVO" items="${memberSvc.all}">
       		<c:if test="${articleVO.member_no==memberVO.member_id}">
       			${memberVO.member_name}
       		</c:if>
	     </c:forEach> 	
		</p>
		
		
		   <c:forEach var="shopVO" items="${shopSvc.all}">
              <c:if test="${articleVO.shop_no==shopVO.shop_id}">
	   			<div class="shopname">
	               <a href="http://guidefood.myftp.org:8081/public/Shop.jsp?shop_id=${shopVO.shop_id}">                
	                 <p style="color:gray; font-size:10px; position:absolute; bottom:78px; left:155px; font-weight:bold;" > 
	       			${shopVO.shop_name}			       
	               </a>
	          	</div>
              </c:if>
           </c:forEach>
	</div>
	<div class="title">${articleVO.article_title}</div>
	<div class="article_info">
		<span>推薦美食</span>
		<span><fmt:formatDate value="${articleVO.article_create_time}" pattern="yyyy/MM/dd hh:mm:ss"/></span>
		<span>人氣:0</span>
	</div>
	
	<div class="content">
		${articleVO.article_content}
	</div>

</div>


<script src="${pageContext.request.contextPath}/article/js/article_content.js"></script>
</body>
</html>
