<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum_post.model.*"%>
<%@ page import="com.forum_reply.model.*"%>
<%@ page import="com.member.model.*"%>
<%@include file="../../pages/header.file"%>

<%
	ForumPostVO forumPost = (ForumPostVO) request.getAttribute("forumPost");
%>


<html>
<head>
<title>回覆修改</title>

<style>
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

</style>

	<!-- ckeditor -->
	<script src="<%=request.getContextPath()%>/forumPost/resources/ckeditor/ckeditor.js"></script>
	<script src="<%=request.getContextPath()%>/forumPost/resources/ckeditor/config.js"></script>
	
	<!-- Header -->
	<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/bootstrap-icons.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/materialdesignicons.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/wrunner-default-theme.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/style_header.css" rel="stylesheet">
	<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/header.js"></script>
</head>
<body>
	<table id="table-1">
		<tr>
			<td><h3>文章 ForumReply: replyUpdate.jsp</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<div class="container">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<FORM METHOD="post" ACTION="forumReply.do">
		<%-- 		<textarea name="content" rows="6" cols="40" >${forumReply.forum_reply_content}</textarea> --%>
					<textarea class="ckeditor" id="myContent" name="content">${forumReply.forum_reply_content}</textarea>
					<br>
					<input type="hidden" name="action" value="reply_Update">
					<input type="hidden" name="postid" value="${forumReply.forum_post_id}">
					<input type="hidden" name="replyid" value="${forumReply.forum_reply_id}">
					<input type="hidden" name="memberID" id="memberID" value="${forumReply.member_id}">
					<button type="submit" class="btn btn-primary">送出</button>
				</FORM>
			</div>
			<div class="col-3"></div>
		</div>
	</div>
	

</body>