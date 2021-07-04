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
</style>

</head>
<body bgcolor='white'>


<header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>

<table>
	<tr>
		<th>揪團編號</th>
		<th>會員編號</th>
		<th>餐廳編號</th>
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