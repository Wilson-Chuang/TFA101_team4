<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.manager.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO"); //ManagerServlet.java(Concroller), 存入req的managerVO物件
%>

<html>
<head>
<title>管理員資料 - listOneManager</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/bootstrap/css/bootstrap.min.css">


<style>
   ul {
    list-style: none;
  }
  .back{
  margin-left: 780px;
  }  
  .back button:focus {
     outline: none;
  }
   
  .back button{
  	 /* 圓角 */
     border-radius: 20%;
    /* 輸入文字色彩設定 */
     color: rgb(41, 41, 41);
     padding: 5px 10px;
  	 width:150px;
  	 height:50px;
  	 margin:20px 10px;
  	 background-color:#e9e9e9;
  }  
  .back button a{
	text-decoration: none; 
	font-size:15px;
	color: rgb(41, 41, 41);
  }
  a{
	text-decoration: none; 
  }
  table {
	width: 950px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  th, td {
 	border: 0;
    border-bottom: 1px solid #ccc;
    padding: 5px;
    margin: 0 5px;
    text-align: center;
    height: 80px;
    vertical-align: middle;
  }
  tr{
   padding-top: 15px;
   vertical-align: middle;
   }
  img {
  width: 80%;
  }
  button{
  	border: none;
  	background-color: none;	
  }
  
   div.main_content {
    width: 100%;
    float: left;
  }
  div.right_content {
    width: 80%;
    float: right;
  }

  .breadcrumb{
     background-color: white;
     margin-top:20px;
     font-size:15px;
     width:200px;
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
    
    <div class="right_content">
    <!--麵包屑，請大家對應側邊欄幫忙修改一下以下名稱，因為是bootstrap，所以需載入script-->
	    
		<nav aria-label="breadcrumb">
		    <ol class="breadcrumb">
				<li class="breadcrumb-item">用戶管理</li>
				<li class="breadcrumb-item active" aria-current="page">
					管理員管理
				</li>
			</ol>
		</nav>

    	<table>
			<tr>
				<th>編號</th>
				<th>帳號</th>
				<th>姓名</th>
				<th width="100px">大頭貼</th>
				<th>信箱</th>
				<th>電話</th>
				<th class="table1">修改</th>
				<th class="table1">刪除</th>
			</tr>
			<tr>
				<td><%=managerVO.getManager_id()%></td>
				<td><%=managerVO.getManager_account()%></td>
				<td><%=managerVO.getManager_name()%></td>
				<td><img src="<%=request.getContextPath()%>/manager/GetPic.do?manager_id=${managerVO.manager_id}"></td>
				<td><%=managerVO.getManager_email()%></td>
				<td><%=managerVO.getManager_phone()%></td>
				<td class="table1">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/manager/manager.do" style="margin-bottom: 0px;">
					     <button type="submit" style="background-color:white;"><i class="fas fa-edit fa-2x"></i></button>
					     <input type="hidden" name="manager_id"  value="${managerVO.manager_id}">
					     <input type="hidden" name="action"	value="getOne_For_Update">
					</FORM>
				</td>
				<td class="table1">
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/manager/manager.do" style="margin-bottom: 0px;">
					     <button type="submit" style="background-color:white;"><i class="fas fa-trash-alt fa-2x"></i></button>
					     <input type="hidden" name="manager_id"  value="${managerVO.manager_id}">
					     <input type="hidden" name="action" value="delete">
					  </FORM>
				</td>
			</tr>
		</table>
		
		<div class="back">
			<button>
				<a href="${pageContext.request.contextPath}/manager/listAllManager.jsp">返回管理員列表</a>
			</button>
		</div>
		
	</div>
</div>

<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>