<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.manager.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO"); //ManagerServlet.java(Concroller), 存入req的managerVO物件
%>

<html>
<head>
<title>員工資料 - listOneManager.jsp</title>

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

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneManager.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>編號</th>
		<th>帳號</th>
		<th>姓名</th>
		<th>大頭貼</th>
		<th>信箱</th>
		<th>密碼</th>
		<th>電話</th>
	</tr>
	<tr>
		<td><%=managerVO.getManager_id()%></td>
		<td><%=managerVO.getManager_account()%></td>
		<td><%=managerVO.getManager_name()%></td>
		<td><%=managerVO.getManager_pic()%></td>
		<td><%=managerVO.getManager_email()%></td>
		<td><%=managerVO.getManager_password()%></td>
		<td><%=managerVO.getManager_phone()%></td>
	</tr>
</table>

</body>
</html>