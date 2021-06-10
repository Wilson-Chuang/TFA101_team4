<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_post.model.*" %>

<%
	ForumPostVO forumPost = (ForumPostVO)request.getAttribute("forumPost");
%>
<html>
<head>
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
</head>
<body>
	<table id="table-1">
		<tr>
			<td><h3>ForumPost: addPost.jsp</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<%-- 	錯誤列表 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="forumPost.do">
		<label for="memberID">會員</label>
		<input type="text" name="memberID" id="memberID">
		<br>
		<label for="title">標題</label>
		<input type="text" name="title" id="title">
		<br>
		<br>
		<textarea name="content" rows="6" cols="40"></textarea>
		<br>
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出">
		<input type="reset" value="清除">
	</FORM>
</body>
</html>