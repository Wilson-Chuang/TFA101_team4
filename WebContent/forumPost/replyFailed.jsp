<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum_post.model.*"%>
<%@ page import="com.forum_reply.model.*"%>
<%@ page import="com.member.model.*"%>



<%
	Integer forum_post_id = (Integer) request.getAttribute("postid");

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
</head>
<body>
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

	<FORM METHOD="post" ACTION="forumReply.do">
		<label for="memberID">會員ID</label> 
		<br> 
		<input type="text" name="memberID"> 
		<br> 
		<label for="reply">回覆</label> 
		<br>
		<textarea name="reply" rows="6" cols="40"></textarea>
		<br> <input type="hidden" name="action" value="reply"> 
		<input type="hidden" name="postid" value="<%=forum_post_id %>">
		<input type="submit" value="送出"> 
		<input type="reset" value="清除">
	</FORM>

</body>