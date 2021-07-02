<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.party.model.*"%>
<!DOCTYPE html>

<%
  PartyVO partyVO = (PartyVO) request.getAttribute("partyVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<title>揪團資料</title>

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
	width: 600px;
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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>揪團資料</h3>
		 <h4><a href="party_select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>揪團編號</th>
		<th>揪團標題</th>
		<th>揪團開始時間</th>
		<th>揪團結束時間</th>
		<th>揪團介紹</th>
		<th>成團人數最多限制</th>
		<th>成團人數最少限制</th>
		<th>備註</th>
	</tr>
	<tr>
		<td><%=partyVO.getParty_id()%></td>
		<td><%=partyVO.getMember_id()%></td>
		<td><%=partyVO.getShop_id()%></td>
		<td><%=partyVO.getParty_title()%></td>
		<td><%=partyVO.getParty_start_time()%></td>
		<td><%=partyVO.getParty_end_time()%></td>
		<td><%=partyVO.getParty_intro()%></td>
		<td><%=partyVO.getParty_participants_max()%></td>
		<td><%=partyVO.getParty_participants_min()%></td>
		<td><%=partyVO.getParty_remarks()%></td>
	</tr>
</table>




</body>
</html>