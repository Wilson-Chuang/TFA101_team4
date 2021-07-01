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

<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/bootstrap/css/bootstrap.min.css">


<title>管理員資料 - listAllManager.jsp</title>


<style>
  ul {
    list-style: none;
  }
  div.add{
  	 width: 80%;
     float: left;
  }
	
  div.add button:focus {
     outline: none;
  }
  
  .add button{
  	 /* 圓角 */
     border-radius: 10px;
    /* 輸入文字色彩設定 */
     color: rgb(41, 41, 41);
     padding: 5px 10px;
  	 width:100px;
  	 height:50px;
  	 margin:20px 10px;
  	 border-radius:20%;
  	 background-color:#e9e9e9;
  }  
  .add button a{
	text-decoration: none; 
	font-size:15px;
	color: rgb(41, 41, 41);
  }
  table {
	width: 950px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  .table1 {
  	border: 0;
    border-bottom: 1px solid #ccc;
    padding: 5px;
    margin: 0 5px;
    text-align: center;
    height: 80px;
    vertical-align: middle;
  }
  .table0{
   padding-top: 15px;
   vertical-align: middle;
   }
  img {
  width: 90%;
  }
  button{
  	border: none;
  	background-color: white;	
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
    
    <div>
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item">用戶管理</li>
		    <li class="breadcrumb-item active" aria-current="page">管理員管理</li>
		  </ol>
		</nav>
    </div>


	<div class="add">
		<button>
			<a href="${pageContext.request.contextPath}/manager/addManager.jsp">新增管理員</a>
		</button>
		<button>
			<a href="${pageContext.request.contextPath}/manager/select_page.jsp">查詢管理員</a>
		</button>
	</div>
	
	
	<div>
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
			<tr class="table0">
				<th class="table1">編號</th>
				<th class="table1">帳號</th>
				<th class="table1">姓名</th>
				<th  class="table1" width="100px">大頭貼</th>
				<th class="table1">信箱</th>
			<!-- 	<th>密碼</th>  -->
				<th class="table1">電話</th>
				<th class="table1">修改</th>
				<th class="table1">刪除</th>
			</tr>
			
		
			<%@ include file="page1.file" %> 
			<c:forEach var="managerVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
				<tr style="align: center;">
					<td class="table1">${managerVO.manager_id}</td>
					<td class="table1">${managerVO.manager_account}</td>
					<td class="table1">${managerVO.manager_name}</td>
					<td class="table1">
						<img src="<%=request.getContextPath()%>/manager/GetPic.do?manager_id=${managerVO.manager_id}">
					</td>
					<td class="table1">${managerVO.manager_email}</td>
				<!--	<td class="table1">${managerVO.manager_password}</td> -->
					<td class="table1">${managerVO.manager_phone}</td>
					<td class="table1">
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/manager/manager.do" style="margin-bottom: 0px;">
					     <button type="submit"><i class="fas fa-edit fa-2x"></i></button>
					     <input type="hidden" name="manager_id"  value="${managerVO.manager_id}">
					     <input type="hidden" name="action"	value="getOne_For_Update">
					  </FORM>
					</td>
					<td class="table1">
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/manager/manager.do" style="margin-bottom: 0px;">
					     <button type="submit"><i class="fas fa-trash-alt fa-2x"></i></button>
					     <input type="hidden" name="manager_id"  value="${managerVO.manager_id}">
					     <input type="hidden" name="action" value="delete">
					  </FORM>
					</td>
				</tr>
			</c:forEach>
			
		</table>
		<br>
		<%@ include file="page2.file" %>
	</div>

</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>