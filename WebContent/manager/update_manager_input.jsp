<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>

<%
  ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO"); //EmpServlet.java (Concroller) 存入req的managerVO物件 (包括幫忙取出的managerVO, 也包括輸入資料錯誤時的managerVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>管理員資料修改 - update_manager_input.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/bootstrap/css/bootstrap.min.css">


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

<!-- 
<table id="table-1">
	<tr><td>
		 <h3>管理員資料修改 - update_manager_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/logo.png" width="200" height="64" border="0">回首頁</a></h4>
	</td></tr>
</table>
 -->
 
<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="manager.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>編號:<font color=red><b>*</b></font></td>
		<td><%=(managerVO==null)?"":managerVO.getManager_id()%></td>
	</tr>
	<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="manager_account" size="45" 
		value="<%=(managerVO==null)?"":managerVO.getManager_account()%>" /></td>
	</tr>
	<tr>
		<td>姓名:</td>
		<td><input type="TEXT" name="manager_name" size="45" value="<%=(managerVO==null)?"":managerVO.getManager_name()%>" /></td>
	</tr>
	<tr>
		<td>大頭貼:</td>
		<td><input type="file" name="manager_pic" value="<%=(managerVO==null)?"":managerVO.getManager_pic()%>" /></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="manager_email" size="45" value="<%=(managerVO==null)?"":managerVO.getManager_email()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="password" name="manager_password" size="45" value="<%=(managerVO==null)?"":managerVO.getManager_password()%>" /></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="manager_phone" size="45" value="<%=(managerVO==null)?"":managerVO.getManager_phone()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="manager_id" value="<%=(managerVO==null)?"":managerVO.getManager_id()%>">
<input type="submit" value="送出修改"></FORM>


<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>