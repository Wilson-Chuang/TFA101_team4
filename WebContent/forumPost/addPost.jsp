<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_post.model.*" %>
<%@ page import="com.search.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member.model.*"%>

<%
	ForumPostVO forumPost = (ForumPostVO)request.getAttribute("forumPost");
%>

<%
	MemberVO member = (MemberVO)session.getAttribute("login");
	pageContext.setAttribute("member", member);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>發起討論</title>

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

</head>
<body>
	<%@include file="/pages/header.file"%>
<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td><h3>ForumPost: addPost.jsp</h3> -->
<!-- 				<h4>( MVC )</h4></td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

	<%-- 	錯誤列表 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
<!-- 	<ul> -->
<!-- 		<li><a href="allPost.jsp">討論區</a><br></li> -->
<!-- 	</ul> -->
	<br>
	<br>
	<div class="container">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<FORM METHOD="post" ACTION="forumPost.do">
		<!-- 		<label for="memberID">會員</label> -->
		<!-- 		<input type="text" name="memberID" id="memberID"> -->	
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1">會員</span>
						<input type="text" class="form-control" name="member_email" disabled="disabled" value="${member.member_email}" aria-label="Username" aria-describedby="basic-addon1">
  						<input type="hidden" class="form-control" name="memberID" id="memberID" value="${member.member_id}" aria-label="Username" aria-describedby="basic-addon1">
					</div>
		<!-- 		<label for="title">主題</label> -->
		<!-- 		<input type="text" name="title" id="title"> -->
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1">主題</span>
  						<input type="text" class="form-control" name="title" id="title" aria-label="Username" aria-describedby="basic-addon1">
					</div>
		<!-- 		<textarea id="summernote" name="content"></textarea> -->
					<textarea class="ckeditor" id="myContent" name="content"></textarea>
					<br>
					<input type="hidden" name="action" value="insert">
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
</html>