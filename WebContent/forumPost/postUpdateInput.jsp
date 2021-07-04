<%@page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum_post.model.*"%>
<%@ page import="com.forum_reply.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.search.model.*"%>
<%@ page import="com.shop.model.*"%>


<%
	ForumPostVO forumPost = (ForumPostVO) request.getAttribute("forumPost");
%>

<%
	MemberVO member = (MemberVO)session.getAttribute("login");
	pageContext.setAttribute("member", member);
%>

<html>
<head>
	<title>文章修改</title>

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
	<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet">

	
	 	<!-- include libraries(jQuery, bootstrap) -->
<!--     <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet"> -->
<!--     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<!--     <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

<!--     include summernote css/js -->
<!--     <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script> -->
</head>
<body>
	<%@include file="/pages/header.file"%>
<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td><h3>文章 ForumPost: postUpdate.jsp</h3> -->
<!-- 				<h4>( MVC )</h4></td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<FORM METHOD="post" ACTION="forumPost.do">
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1">會員</span>
						<input type="text" class="form-control" name="member_email" disabled="disabled" value="${member.member_email}" aria-label="Username" aria-describedby="basic-addon1">
					</div>
					<div class="input-group mb-3">
  						<span class="input-group-text" id="basic-addon1">主題</span>
  						<input type="text" class="form-control" name="title" id="title" value="${forumPost.forum_post_title}" aria-label="Username" aria-describedby="basic-addon1">
					</div>
<!-- 					<label for="title">標題</label> -->
<%-- 					<input type="text" name="title" id="title" value="${forumPost.forum_post_title}"> --%>
		<%-- 		<textarea id="summernote" name="content">${forumPost.forum_post_content}</textarea> --%>
					<textarea class="ckeditor" id="myContent" name="content">${forumPost.forum_post_content}</textarea>
					<br>
					<input type="hidden" name="action" value="post_Update">
					<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
					<input type="hidden" name="memberID" id="memberID" value="${forumPost.member_id}">
					<button type="submit" class="btn btn-primary">送出</button>
				</FORM>
			</div>
			<div class="col-3"></div>
		</div>
	</div>
	
	<!-- Header -->
	<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/header.js"></script>
</body>