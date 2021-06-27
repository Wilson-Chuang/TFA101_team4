<%@page import="com.forum_reply_report.model.ForumReplyReportVO"%>
<%@page import="com.forum_reply.model.ForumReplyVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_reply.model.*"%>
<%@ page import="com.forum_reply_report.model.*"%>

<%
	ForumReplyVO forumReply = (ForumReplyVO) request.getAttribute("forumReply");
	ForumReplyReportVO forumReplyReport = (ForumReplyReportVO) request.getAttribute("forumReplyReport");
%>
<html>
<head>
<title>Guide好食|檢舉回覆</title>

<link rel="stylesheet" type="text/css"
	href="vendors/bootstrap-4.6.0-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="css/report.css">


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
			<td><h3>ForumReply: replyReport.jsp</h3>
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

	<div class="container">
		<div class="report">
			<div class="row">
				<div class="col title">檢舉</div>
			</div>
			<div class="row">
				<div class="col selects">
					<FORM METHOD="post" ACTION="forumReply.do">
						<div class="form-check">
							<input class="form-check-input" type="radio" name="reportRadios" id="reason1" value="不當言論" checked> 
							<label class="form-check-label" for="reason1"> 不當言論 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="reportRadios" id="reason2" value="內含情色"> 
							<label class="form-check-label" for="reason2"> 內含情色 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="reportRadios" id="reason3" value="其他"> 
							<label class="form-check-label" for="reason3">其他</label> 
							<input type="text" name="report_reason">
						</div>
						<div>
							<label for="report_memberID">會員</label>
							<input type="text" id="report_memberID" name="report_memberID">
						</div>
						
						<button class="btn btn-primary" type="submit">送出</button>
						<input type="hidden" name="action" value="reply_report">
						<c:if test="${forumReply != null}">
							<input type="hidden" name="replyid" value="${forumReply.forum_reply_id}">
						</c:if>
						<c:if test="${forumReplyReport != null}">
							<input type="hidden" name="replyid" value="${forumReplyReport.forum_reply_id}">
						</c:if>
					</FORM>
				</div>
			</div>
		</div>
		
		<!--載入jquery-->
        <script src="vendors/jquery/jquery-3.6.0.min.js"></script>
        <!--Bootstrap JS-->
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
        <script src="vendors/bootstrap-4.6.0-dist/js/bootstrap.min.js"></script>
</body>
</html>