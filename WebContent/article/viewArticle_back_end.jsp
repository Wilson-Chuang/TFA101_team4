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
<title>文章預覽(後台)</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/article/css/viewArticle_back_end.css">

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
		<input type="button" value="結束預覽" onclick="location.href='${pageContext.request.contextPath}/article/listArticle_back_end.jsp'">
		
		<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do">
			<input type="submit" value="未過/下架" onclick="javascript:return cancel();">
			<input type="hidden" name="article_no"  value="${articleVO.article_no}">
			<input type="hidden" name="action" value="cancel_status">
		</FORM>	

		<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do">
			<input type="submit" value="批准/上架" 
				style="margin-top:9px;" class="submit" onclick="javascript:return sub();">
			<input type="hidden" name="article_no"  value="${articleVO.article_no}">
			<input type="hidden" name="action" value="update_status">
		</FORM>		
	</div>
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
<script>
function sub() {
	var msg = "確定批准上架嗎？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}
</script>

<script>
function cancel() {
	var msg = "確認審核未過並下架嗎？";
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
