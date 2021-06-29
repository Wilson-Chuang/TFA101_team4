<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_post_report.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.forum_post.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	ForumPostReportService forumPostReportSvc = new ForumPostReportService();
	List<ForumPostReportVO> list = forumPostReportSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="forumPostSvc" scope="page" class="com.forum_post.model.ForumPostService" />

<html>
<head>
<title>發文檢舉列表 - allPostReport.jsp</title>

<!--  <style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin: 0 auto;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style> -->

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
</style>

</head>
<body bgcolor='white'>


<table id="table-1">
	<tr><td>
		 <h3>發文檢舉列表 - allPostReport.jsp</h3>
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
		<th>發文編號</th>
		<th>發文標題</th>
		<th>檢舉會員</th>
		<th>檢舉原因</th>
		<th>檢舉時間</th>
		<th>通過</th>
		<th>不通過</th>
	</tr>

	<c:forEach var="forumPostReportVO" items="${list}">
		<c:if test="${forumPostReportVO.forum_post_report_status == 2}">
			<tr>
				<td>${forumPostReportVO.forum_post_id}</td>
				<td>${forumPostSvc.getOneForumPost(forumPostReportVO.forum_post_id).forum_post_title}</td>
				<td>${memberSvc.GET_ONE_BY_ID(forumPostReportVO.member_id).member_email}</td>
				<td>${forumPostReportVO.forum_post_report_reason}</td>
				<td><fmt:formatDate value="${forumPostReportVO.forum_post_report_time}" pattern="yyyy-MM-dd HH:mm"/></td>			
				<FORM METHOD="post" ACTION="forumPost.do">
					<td><input type="submit" value="通過"></td>
					<input type="hidden" name="action" value="check_post_report">
					<input type="hidden" name="postid" value="${forumPostReportVO.forum_post_id}">
					<input type="hidden" name="forum_post_report_id" value="${forumPostReportVO.forum_post_report_id}">
					<input type="hidden" name="forum_post_report_status" value="0">
				</FORM>
				<FORM METHOD="post" ACTION="forumPost.do">
					<td><input type="submit" value="不通過"></td>
					<input type="hidden" name="action" value="check_post_report">
					<input type="hidden" name="postid" value="${forumPostReportVO.forum_post_id}">
					<input type="hidden" name="forum_post_report_id" value="${forumPostReportVO.forum_post_report_id}">
					<input type="hidden" name="forum_post_report_status" value="1">
				</FORM>
			</tr>
		</c:if>	
	</c:forEach>
</table>


</body>
</html>