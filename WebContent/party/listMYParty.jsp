<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.party.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%
    MemberVO member = (MemberVO) session.getAttribute("login");
    PartyService partySvc = new PartyService();
    List<PartyVO> list = partySvc.getAllmyparty(member.getMember_id());
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>所有揪團資料</title>

<style>
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
<body bgcolor='white' >


<table id="table-1">
	<tr><td>
		 <h3>所有揪團資料</h3>
		 <h4><a href="party_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
		<th>揪團編號</th>
		<th>會員編號</th>
		<th>餐廳編號</th>
		<th>揪團標題</th>
		<th>揪團開始時間</th>
		<th>揪團結束時間</th>
		<th>揪團簡介</th>
		<th>成團人數最多限制</th>
		<th>成團人數最少限制</th>
		<th>備註</th>
	</tr>
	
	
	<%@ include file="page5.file" %> 
	<c:forEach var="partyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<td>${partyVO.party_id}</td>
			<td>${partyVO.member_id}</td>
			<td>${partyVO.shop_id}</td>
			<td>${partyVO.party_title}</td>
			<td><fmt:formatDate value="${partyVO.party_start_time}"
						pattern="yyyy-MM-dd hh:mm" /></td> 
			<td><fmt:formatDate value="${partyVO.party_end_time}"
						pattern="yyyy-MM-dd hh:mm" /></td>   
			<td>${partyVO.party_intro}</td>
			<td>${partyVO.party_participants_max}</td>
			<td>${partyVO.party_participants_min}</td>
			<td>${partyVO.party_remarks}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/party/party.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="party_id"  value="${partyVO.party_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/party/party.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="party_id"  value="${partyVO.party_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>