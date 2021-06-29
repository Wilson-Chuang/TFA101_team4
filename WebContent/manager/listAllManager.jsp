<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    ManagerService managerSvc = new ManagerService();
    List<ManagerVO> list = managerSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>管理員資料 - listAllManager.jsp</title>

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
  img {
  width: 100%
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>管理員資料 - listAllManager.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/logo.png" width="200" height="62" border="0">回首頁</a></h4>
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
		<th>編號</th>
		<th>帳號</th>
		<th>姓名</th>
		<th>大頭貼</th>
		<th>信箱</th>
		<th>密碼</th>
		<th>電話</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	

	<%@ include file="page1.file" %> 
	<c:forEach var="managerVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${managerVO.manager_id}</td>
			<td>${managerVO.manager_account}</td>
			<td>${managerVO.manager_name}</td>
			<td><img src="/uploadpic/${managerVO.manager_picname}"></td>
			<td>${managerVO.manager_email}</td>
			<td>${managerVO.manager_password}</td> 
			<td>${managerVO.manager_phone}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/manager/manager.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="manager_id"  value="${managerVO.manager_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/manager/manager.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="manager_id"  value="${managerVO.manager_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>