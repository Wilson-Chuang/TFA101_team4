<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum_post.model.*"%>
<%@ page import="com.forum_reply.model.*"%>

<%
	ForumPostVO forumPost = (ForumPostVO) request.getAttribute("forumPost");
	Integer forum_post_id = forumPost.getForum_post_id();

	ForumReplyService forumReplySvc = new ForumReplyService();
	List<ForumReplyVO> list = forumReplySvc.getByFoumPostID(forum_post_id);
	pageContext.setAttribute("list", list);
%>

<%=forumPost.getForum_post_id() %>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

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
			<td><h3>文章 ForumPost: onePost.jsp</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>
	<div>發文</div>
	<div id="post">
		<div>用戶名:${memberSvc.getOneMember(forumPost.member_id).member_account}</div>
		<div>發文時間:<fmt:formatDate value="<%=forumPost.getForum_post_time()%>"
							pattern="yyyy/MM/dd HH:mm" /></div>
		<div>最後更新時間:<fmt:formatDate value="<%=forumPost.getForum_update_time()%>"
							pattern="yyyy/MM/dd HH:mm" /></div>
		<div>標題:<%=forumPost.getForum_post_title()%></div>
		<div>內容:<%=forumPost.getForum_post_content()%></div>
	</div>

	<div>回復</div>
	<c:forEach var="forumReply" items="${list}">
		<div id="reply">
		<div>用戶名:${memberSvc.getOneMember(forumReply.member_id).member_account}</div>
		<div>回復時間:<fmt:formatDate value="${forumReply.forum_reply_time}" 
							pattern="yyyy/MM/dd HH:mm" /></div>
		<div>最後更新時間:<fmt:formatDate value="${forumReply.forum_reply_update_time}" 
							pattern="yyyy/MM/dd HH:mm" /></div>
		<div>回復內容:${forumReply.forum_reply_content}</div>
	</div>
	</c:forEach>
	
	<FORM METHOD="post" ACTION="forumReply.do">
		<label for="memberID">會員ID</label>
		<br>
		<input type="text" name="memberid">
		<br>
		<label for="reply">回覆</label>
		<br>
		<textarea name="reply" rows="6" cols="40"></textarea>
		<br>
		<input type="hidden" name="action" value="reply">
		<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
		<input type="submit" value="送出">
		<input type="reset" value="清除">
	</FORM>

</body>