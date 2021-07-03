<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_reply_report.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.forum_reply.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ForumReplyReportService forumReplyReportSvc = new ForumReplyReportService();
	List<ForumReplyReportVO> list = forumReplyReportSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="forumReplySvc" scope="page" class="com.forum_reply.model.ForumReplyService" />


<html>
<head>
	<title>回覆檢舉列表</title>
	
	
	<style>
	  table {
	 width: 900px;
	 background-color: white;
	 margin-top: 5px;
	 margin-bottom: 5px;
	  }
	  th, td {
	   border: 0;
	    border-bottom: 1px solid gray;
	    padding: 5px;
	    text-align: center;
	    height: 50px;
	  }
	  tr{
	   align: center;
	   }
	  img {
	  width: 80%;
	  }
	  button{
	   border: none;
	   background-color: none; 
	  }
	  
	  div.main_content {
	    width: 100%;
	    float: left;
	  }
	
	  .breadcrumb{
	     background-color: white !​important;
	     margin:20px 0 0 0;
	     font-size:15px;
	  }
	</style>

<!-- bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/bootstrap/css/bootstrap.min.css">

</head>
<body bgcolor='white'>
<header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>
<div class="main_content">

    <div>
	<jsp:include page="/cms/header_asideMenu/cmsAsideMenu.jsp" flush="true" />
	</div>
	<br>
	<br>

<table id="table-1">
	<tr><td>
		 <h3>回覆檢舉列表 </h3>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>回覆編號</th>
		<th>檢舉會員</th>
		<th>檢舉原因</th>
		<th>檢舉時間</th>
		<th>通過</th>
		<th>不通過</th>
	</tr>

	<c:forEach var="forumReplyReportVO" items="${list}">
<%-- 		<c:if test="${forumReplyReportVO.forum_reply_report_status == 2}"> --%>
			<tr>
				<td>${forumReplyReportVO.forum_reply_id}</td>
				<td>${memberSvc.GET_ONE_BY_ID(forumReplyReportVO.member_id).member_email}</td>
				<td>${forumReplyReportVO.forum_reply_report_reason}</td>
				<td><fmt:formatDate value="${forumReplyReportVO.forum_reply_report_time}" pattern="yyyy-MM-dd HH:mm"/></td>			
				<FORM METHOD="post" ACTION="forumReply.do">
					<td><input type="submit" value="通過"></td>
					<input type="hidden" name="action" value="check_reply_report">
					<input type="hidden" name="replyid" value="${forumReplyReportVO.forum_reply_id}">
					<input type="hidden" name="forum_reply_report_id" value="${forumReplyReportVO.forum_reply_report_id}">
					<input type="hidden" name="forum_reply_report_status" value="0">
				</FORM>
				<FORM METHOD="post" ACTION="forumReply.do">
					<td><input type="submit" value="不通過"></td>
					<input type="hidden" name="action" value="check_reply_report">
					<input type="hidden" name="replyid" value="${forumReplyReportVO.forum_reply_id}">
					<input type="hidden" name="forum_reply_report_id" value="${forumReplyReportVO.forum_reply_report_id}">
					<input type="hidden" name="forum_reply_report_status" value="1">
				</FORM>
			</tr>
<%-- 		</c:if>	 --%>
	</c:forEach>
</table>
</div>

<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>