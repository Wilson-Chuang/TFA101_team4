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
  ul {
    list-style: none;
  }
  div{
	height: 30px;	
  }
  a{
	text-decoration: none; 
  }
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

<header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>

<table>
	<tr>
		<th>編號</th>
		<th>帳號</th>
		<th>姓名</th>
		<th width="100px">大頭貼</th>
		<th>信箱</th>
		<th>密碼</th>
		<th>電話</th>
	</tr>
	<tr>
		<td><%=managerVO.getManager_id()%></td>
		<td><%=managerVO.getManager_account()%></td>
		<td><%=managerVO.getManager_name()%></td>
		<td><img src="<%=request.getContextPath()%>/manager/GetPic.do?manager_id=${managerVO.manager_id}"></td>
		<td><%=managerVO.getManager_email()%></td>
		<td><%=managerVO.getManager_password()%></td>
		<td><%=managerVO.getManager_phone()%></td>
	</tr>
</table>

</body>
</html>