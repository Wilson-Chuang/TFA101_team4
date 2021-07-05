<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>文章預覽</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/article/css/viewArticle.css">

<script src="${pageContext.request.contextPath}/article/vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>

</head>


<body>

<div class="article_content">

<!-- 標題 -->
    <div class="maintop_header">
        <div class="category">
            <h2>
               	預覽模式
            </h2>
        </div>
    </div>
    
<!-- 側邊功能列 -->
	<div class="nav_bar">
		<input type="button" value="結束預覽" onclick="location.href='${pageContext.request.contextPath}/article/article.do?member_no=1&action=post'">
	</div>

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
<script>
function sub() {
	var msg = "確定要送審了嗎？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}
</script>

<script>
function cancel() {
	var msg = "確定要取消審核嗎？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}
</script>


<script src="${pageContext.request.contextPath}/article/js/listArticle.js"></script>
</body>
</html>
