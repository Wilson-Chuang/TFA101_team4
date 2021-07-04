<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
    
    MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
    pageContext.setAttribute("memberVO",memberVO);
    String picpath=request.getContextPath()+ File.separator+"UPLOAD" + File.separator + "member"+ File.separator + "pic"+ File.separator;

%>


<html>
<head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/cms/vendors/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/cms/vendors/bootstrap/css/bootstrap.min.css">


<title>會員資料 - listAllMember.jsp</title>


<style>
  ul {
    list-style: none;
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
  
  div. firstline{
  	float:left;
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
    
    <div class="firstline">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item">用戶管理</li>
		    <li class="breadcrumb-item active" aria-current="page">會員管理</li>
		  </ol>
		</nav>
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
				<th class="table1">姓名</th>
				<th class="table1">生日</th>
				<th class="table1">電話</th>
				<th class="table1">地址</th>
				<th  class="table1" width="100px">大頭貼</th>
				<th class="table1">信箱</th>
				<th class="table1">狀態</th>
				<th class="table1">停權/<br>可使用</th>
			</tr>
			
		
			<%@ include file="page1.file" %> 
			<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<%System.out.println(picpath); %>
				
				
				<tr style="align: center;">
					<td class="table1">${memberVO.member_id}</td>
					<td class="table1">${memberVO.member_name}</td>
					<td class="table1">${memberVO.member_birth}</td>
					<td class="table1">${memberVO.member_phone}</td>
					<td class="table1">${memberVO.member_address}</td>
					<td class="table1"><img src="<%=picpath%>${memberVO.member_id}<%=File.separator%>${memberVO.member_pic}"></td>
					<td class="table1">${memberVO.member_email}</td>
<%-- 					<td class="table1"><%= (memberVO.getMember_status() == 1) ? "可使用" : "停權" %></td> --%>
					<td class="table1">
						<c:if test="${memberVO.member_status == 1}">可使用</c:if>
						<c:if test="${memberVO.member_status != 1}">停權</c:if>
					</td>
 				<!--改成停權狀態及按鈕 status==0停權 <i class="fas fa-user-alt-slash"></i>  
 					==1開放  <i class="fas fa-user"></i> 
 					-->
					<td class="table1">
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member.html" style="margin-bottom: 0px;">
					     <button type="submit">
						     <c:if test="${memberVO.member_status == 1}"><i class="fas fa-user-alt-slash"></i><br>停權</c:if>
							 <c:if test="${memberVO.member_status != 1}"><i class="fas fa-user"></i><br>開放使用</c:if>
					     </button>
					     <input type="hidden" name="member_id"  value="${memberVO.member_id}">
					     <input type="hidden" name="action"	value="changeOne_Status">
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