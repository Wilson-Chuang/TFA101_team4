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
  	margin-top:30px;
    padding: 1px;
  }
  tr, td {
 	height:50px;
  }
   h4{
 	margin:20px 0;
 	font-size:22px;
  	color: gray;
  }
  
  div.main_content {
    width: 100%;
    float: left;
  }

  .breadcrumb{
     background-color: white;
     margin:20px 0 0 0;
     font-size:15px;
  }
  
  .inputtext{
  	border:0;
  	border-bottom: 1px solid gray;
  }
  
  push:focus {
     outline: none;
  }
   
  push{
  	 /* 圓角 */
     border-radius: 30px;
    /* 輸入文字色彩設定 */
     color: rgb(41, 41, 41);
     padding: 5px 10px;
  	 width:100px;
  	 height:50px;
  	 margin:20px 10px;
  	 background-color:#e9e9e9;
  }  

</style>

</head>
<body bgcolor='white'>

<header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>
 
 <div class="main_content">
 	
 	<div>
		<jsp:include page="/cms/header_asideMenu/cmsAsideMenu.jsp" flush="true" />
    </div>
    
    <!--麵包屑，請大家對應側邊欄幫忙修改一下以下名稱，因為是bootstrap，所以需載入script-->
    <div>
		<nav aria-label="breadcrumb">
		    <ol class="breadcrumb">
				<li class="breadcrumb-item">用戶管理</li>
				<li class="breadcrumb-item active" aria-current="page">管理員管理</li>
		    </ol>
		</nav>
    </div>
 
		<h4>管理員資料修改</h4>
		
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
				<td>編號&emsp;<font color=red><b>*</b></font></td>
				<td><%=(managerVO==null)?"":managerVO.getManager_id()%></td>
			</tr>
			<tr>
				<td>帳號&emsp;</td>
				<td>
					<input type="TEXT" class="inputtext" name="manager_account" size="45" 
						value="<%=(managerVO==null)?"":managerVO.getManager_account()%>" />
				</td>
			</tr>
			<tr>
				<td>姓名&emsp;</td>
				<td>
					<input type="TEXT" class="inputtext" name="manager_name" size="45" 
						value="<%=(managerVO==null)?"":managerVO.getManager_name()%>" />
				</td>
			</tr>
			<tr>
				<td>大頭貼&emsp;</td>
				<td>
					<input type="file" name="manager_pic" 
						value="<%=(managerVO==null)?"":managerVO.getManager_pic()%>" />
				</td>
			</tr>
			<tr>
				<td>信箱&emsp;</td>
				<td>
					<input type="TEXT" class="inputtext" size="45" disabled="disabled"
						value="<%=(managerVO==null)?"":managerVO.getManager_email()%>" />
					<input type="hidden" class="inputtext" name="manager_email" size="45" 
						value="<%=(managerVO==null)?"":managerVO.getManager_email()%>" />
				</td>
			</tr>
			<tr>
				<td>密碼&emsp;</td>
				<td>
					<input type="password" class="inputtext" name="manager_password" size="45" 
						value="<%=(managerVO==null)?"":managerVO.getManager_password()%>" />
				</td>
			</tr>
			<tr>
				<td>電話&emsp;</td>
				<td>
					<input type="TEXT" class="inputtext" name="manager_phone" size="45" 
						value="<%=(managerVO==null)?"":managerVO.getManager_phone()%>" />
				</td>
			</tr>
		
		</table>
		<br>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="manager_id" value="<%=(managerVO==null)?"":managerVO.getManager_id()%>">
		<input type="submit" class="push" value="送出修改"></FORM>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>

</body>

</html>