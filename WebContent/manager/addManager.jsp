<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>

<%
  ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>資料新增 - addManager.jsp</title>

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>資料新增 - addManager.jsp</h3></td><td>
		 <h4><a href="select_page.jsp"><img src="images/logo.png" width="200" height="64" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="manager.do" name="form1">
<table>
	<jsp:useBean id="managerSvc" scope="page" class="com.manager.model.ManagerService" />
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="Manager_account" size="45" 
			 value="<%= (managerVO==null)? "testaccount1" : managerVO.getManager_account()%>" /></td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="Manager_name" size="45"
			 value="<%= (managerVO==null)? "測試名" : managerVO.getManager_name()%>" /></td>
	</tr>
	<tr>
		<td>大頭貼:</td>
		<td><input type="file" name="Manager_pic()"
			 value="<%= (managerVO==null)? "請放圖" : managerVO.getManager_pic()%>" /></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="Manager_email" size="45"
			 value="<%= (managerVO==null)? "testemail@gmail.com" : managerVO.getManager_email()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="Manager_password" size="45"
			 value="<%= (managerVO==null)? "123456" : managerVO.getManager_password()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="Manager_phone" size="45"
			 value="<%= (managerVO==null)? "0900111222" : managerVO.getManager_phone()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"></FORM>
</body>

</html>