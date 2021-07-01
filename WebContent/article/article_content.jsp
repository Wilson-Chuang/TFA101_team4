<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
		<p>Hank</p>
	</div>
	<div class="title">${articleVO.article_title}</div>
	<div class="article_info">
		<span>推薦美食</span>
		<span><fmt:formatDate value="${articleVO.article_create_time}" pattern="yyyy/MM/dd hh:mm:ss"/></span>
		<span>人氣:199</span>
	</div>
	<div class="content">
		${articleVO.article_content}
	</div>

</div>


<script src="${pageContext.request.contextPath}/article/js/article_content.js"></script>
</body>
</html>
