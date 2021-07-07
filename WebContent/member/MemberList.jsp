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
    
    MemberVO MemberVO = (MemberVO) session.getAttribute("MemberVO");
    pageContext.setAttribute("MemberVO",MemberVO);
    String picpath=request.getContextPath()+ File.separator+"UPLOAD" + File.separator + "member"+ File.separator + "pic"+ File.separator;

%>


<html>
<head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/cms/vendors/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/cms/vendors/bootstrap/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/bootstrap-icons.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wrunner-default-theme.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">

<title>會員清單 - MemberList</title>


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
  .rightcontent{
  	margin-left:20%;
  	width: 1000px; 
  }
  
<%-- <%@ include file="/pages/header.file" %> --%>
</style>

</head>
<div class="main_content">

    
    <div class="firstline">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item">用戶管理</li>
		    <li class="breadcrumb-item active" aria-current="page">會員管理</li>
		  </ol>
		</nav>
    </div>

	
	<div class="rightcontent" width="1000px;">
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
				<th  class="table1" width="100px">大頭貼</th>
			</tr>
			
		
			<%@ include file="page1.file" %> 
			<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				<%System.out.println(picpath); %>
				
				
				<tr style="align: center;">
					<td class="table1">${memberVO.member_id}</td>
					<td class="table1">${memberVO.member_name}</td>
					<td class="table1">
					<a href="<%=request.getContextPath()+"/public/Person.jsp?member_id="%>${memberVO.member_id} ">
					<img src="<%=picpath%>${memberVO.member_id}<%=File.separator%>${memberVO.member_pic}" onerror="this.src='${pageContext.request.contextPath}/public/img/noimage.jpg'">
					</a>
					</td>
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
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/header.js"></script>

</body>
