<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_post.model.*"%>
<%@ page import="com.member.model.*"%>

<%
    ForumPostVO forumPost = (ForumPostVO)request.getAttribute("forumPost");

	ForumPostService forumPostSvc = new ForumPostService();
	List<ForumPostVO> list = forumPostSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<html>
<head>
<title>Guide好食|討論區</title>

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
			<td><h3>全部文章 ForumPost: allPost.jsp</h3>
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

	<ul>
		<li><a href="addPost.jsp">發起討論</a><br></li>
	</ul>

	<table>
		<tr>
			<th>主題</th>
			<th>回覆數量</th>
			<th>用戶名</th>
			<th>按讚</th>
			<th>發表時間</th>
		</tr>
		<c:forEach var="forumPost" items="${list}">
			<tr>
				<FORM METHOD="post" ACTION="forumPost.do">
					<td><input type="submit" value="${forumPost.forum_post_title}"></td>
					<td>${forumPost.forum_post_reply_total}</td>
					<td>${memberSvc.GET_ONE_BY_ID(forumPost.member_id).member_email}</td>
					<td>${forumPost.forum_post_like}</td>
					<td><fmt:formatDate value="${forumPost.forum_post_time}"
							pattern="yyyy/MM/dd HH:mm" /></td>
					<input type="hidden" name="action" value="getOne_For_Display"> 
					<input type="hidden" name="postid"value="${forumPost.forum_post_id}">
				</FORM>
			</tr>
		</c:forEach>
	</table>
</body>
</html>