<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_post.model.*"%>
<%@ page import="com.search.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member.model.*"%>

<%@include file="../../pages/header.file"%>
<%
	MemberVO member = (MemberVO)session.getAttribute("login");
%>

<%-- <%=member.getMember_id()%> --%>
<%-- <%=member.getMember_email()%> --%>

<%
    ForumPostVO forumPost = (ForumPostVO)request.getAttribute("forumPost");

	ForumPostService forumPostSvc = new ForumPostService();
	List<ForumPostVO> list = forumPostSvc.getAll();
	pageContext.setAttribute("list", list);
	
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="forumPostLikeSvc" scope="page" class="com.forum_post_like.model.ForumPostLikeService"></jsp:useBean>

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

#table-1 {
	border: 1px solid #CCCCFF;
}

tr, td {
	padding: 5px;
	text-align: center;
}
</style>

<!-- fontawesome -->
<script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
<!--Bootstrap CSS-->
<link rel="stylesheet" type="text/css"
	href="vendors/bootstrap-4.6.0-dist/css/bootstrap.min.css">
<!--dataTables CSS-->
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/jquery.dataTables.min.css">

<!-- Header -->
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/bootstrap-icons.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/materialdesignicons.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/wrunner-default-theme.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/header.js"></script>
</head>
<body>
	
	<table id="table-1">
		<tr id="table-1-tr">
			<td id="table-1-td"><h3>全部文章 ForumPost: allPost.jsp</h3>
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

	<table id="myTable" class="display" style="width:100%">
		<thead>
			<tr>
				<th>主題</th>
				<th>回覆</th>
				<th>用戶名</th>
				<th>按讚</th>
				<th><i class="far fa-clock"></i></th>
			</tr>
		</thead>
		
		<c:forEach var="forumPost" items="${list}">
			<tr>
				<FORM METHOD="post" ACTION="forumPost.do">				
					<td><button type="submit" class="btn btn-outline-primary">${forumPost.forum_post_title}</button></td>
					<td>${forumPost.forum_post_reply_total}</td>
					<td>${memberSvc.GET_ONE_BY_ID(forumPost.member_id).member_email}</td>
					<td>${forumPostLikeSvc.countByPostID(forumPost.forum_post_id)}</td>
					<td><fmt:formatDate value="${forumPost.forum_post_time}"
							pattern="yyyy/MM/dd HH:mm" /></td>
					<input type="hidden" name="action" value="getOne_For_Display"> 
					<input type="hidden" name="postid"value="${forumPost.forum_post_id}">
				</FORM>
			</tr>
		</c:forEach>
	</table>
	
	
	<!--載入jquery-->
    <script src="vendors/jquery/jquery-3.6.0.min.js"></script>
    <!--Bootstrap JS-->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="vendors/bootstrap-4.6.0-dist/js/bootstrap.min.js"></script>
    <!--DataTables JS-->
    <script src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js"></script>
    <script src="js/allPost.js"></script>
</body>
</html>