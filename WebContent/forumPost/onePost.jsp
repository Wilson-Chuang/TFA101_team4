<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum_post.model.*"%>
<%@ page import="com.forum_reply.model.*"%>
<%@ page import="com.member.model.*"%>

<%=(request.getAttribute("forumPost") == null)%>

<%
	ForumPostVO forumPost = (ForumPostVO) request.getAttribute("forumPost");
	Integer forum_post_id = null;
	if (forumPost.getForum_post_id() == null) {
		forum_post_id = (Integer) request.getAttribute("postid");
	} else {
		forum_post_id = forumPost.getForum_post_id();
	}

	ForumReplyService forumReplySvc = new ForumReplyService();
	List<ForumReplyVO> list = forumReplySvc.getByFoumPostID(forum_post_id);
	pageContext.setAttribute("list", list);
%>

<%=forumPost.getForum_post_id()%>

<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService" />

<html>
<head>
<title>Guide好食|討論文章</title>

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

FORM {
	display: inline-block;
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

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div>發文</div>
	<div id="post">
		<div>用戶名:${memberSvc.GET_ONE_BY_ID(forumPost.member_id).member_email}</div>
		<div>
			發文時間:
			<fmt:formatDate value="<%=forumPost.getForum_post_time()%>"
				pattern="yyyy/MM/dd HH:mm" />
		</div>
		<div>
			最後更新時間:
			<fmt:formatDate value="<%=forumPost.getForum_update_time()%>"
				pattern="yyyy/MM/dd HH:mm" />
		</div>
		<div>
			標題:<%=forumPost.getForum_post_title()%></div>
		<div>
			內容:<%=forumPost.getForum_post_content()%></div>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumPost/forumPost.do">
			<input type="submit" value="編輯"> 
			<input type="hidden" name="action" value="getOne_Post_For_update"> 
			<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
			<input type="hidden" name="memberID" value="${forumPost.member_id}">
			<input type="hidden" name="post_title" value="${forumPost.forum_post_title}"> 
			<input type="hidden" name="post_content" value="${forumPost.forum_post_content}">
		</FORM>
		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/forumPost/forumPost.do">
			<input type="submit" value="刪除"> 
			<input type="hidden" name="action" value="post_delete"> 
			<input type="hidden" name="postid" value="${forumPost.forum_post_id}"> 
			<input type="hidden" name="memberID" value="${forumPost.member_id}">
		</FORM>
		<FORM METHOD="post" ACTION="forumPost.do">
			<input type="submit" value="檢舉"> 
			<input type="hidden" name="action" value="getOne_Post_For_report"> 
			<input type="hidden" name="postid" value="${forumPost.forum_post_id}"> 
			<input type="hidden" name="memberID" value="${forumPost.member_id}">
			<input type="hidden" name="post_content" value="${forumPost.forum_post_content}">
		</FORM>
	</div>

	<div>回覆</div>
	<c:forEach var="forumReply" items="${list}">
		<div id="reply">
			<div>用戶名:${memberSvc.GET_ONE_BY_ID(forumReply.member_id).member_email}</div>
			<div>
				回覆時間:
				<fmt:formatDate value="${forumReply.forum_reply_time}"
					pattern="yyyy/MM/dd HH:mm" />
			</div>
			<div>
				最後更新時間:
				<fmt:formatDate value="${forumReply.forum_reply_update_time}"
					pattern="yyyy/MM/dd HH:mm" />
			</div>
			<div>回復內容:${forumReply.forum_reply_content}</div>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumPost/forumReply.do">
				<input type="submit" value="編輯"> 
				<input type="hidden" name="action" value="getOne_Reply_For_update">
				<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
				<input type="hidden" name="replyid" value="${forumReply.forum_reply_id}">
				<input type="hidden" name="memberID" value="${forumReply.member_id}"> 
				<input type="hidden" name="reply_content" value="${forumReply.forum_reply_content}">
			</FORM>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumPost/forumReply.do">
				<input type="submit" value="刪除"> 
				<input type="hidden" name="action" value="reply_delete"> 
				<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
				<input type="hidden" name="replyid" value="${forumReply.forum_reply_id}"> 
				<input type="hidden" name="memberID" value="${forumReply.member_id}">
			</FORM>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumPost/forumReply.do">
				<input type="submit" value="檢舉"> 
				<input type="hidden" name="action" value="reply_report"> 
				<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
				<input type="hidden" name="replyid" value="${forumReply.forum_reply_id}"> 
				<input type="hidden" name="memberID" value="${forumReply.member_id}">
			</FORM>
		</div>
	</c:forEach>

	<FORM METHOD="post" ACTION="forumReply.do">
		<label for="memberID">會員ID</label> 
		<br> 
		<input type="text" name="memberID"> 
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