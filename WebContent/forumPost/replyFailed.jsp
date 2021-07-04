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
	Integer forum_post_id = (Integer) request.getAttribute("postid");

%>

<%
	MemberVO member = (MemberVO)session.getAttribute("login");
	pageContext.setAttribute("member", member);
%>

<%=forum_post_id %>

<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService" />

<html>
<head>
<title>討論文章</title>

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

div#post, #reply {
	border: solid 1px black;
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
	<table id="table-1">
		<tr>
			<td><h3>文章 ForumReply: replyFailed.jsp</h3>
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
    				<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1">會員</span>
						<input type="text" class="form-control" name="member_email" disabled="disabled" value="${member.member_email}" aria-label="Username" aria-describedby="basic-addon1">
  						<input type="hidden" class="form-control" name="memberID" value="${member.member_id}" aria-label="Username" aria-describedby="basic-addon1">
					</div> 
        			<textarea class="ckeditor" id="myContent" name="reply"></textarea>
        			<br> 
        			<input type="hidden" name="action" value="reply"> 
        			<input type="hidden" name="postid" value="<%=forum_post_id %>">
        			<button class="btn btn-primary" type="submit">送出</button> 
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