<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.search.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.search.model.*"%>

<%
	MemberVO MemberVO = (MemberVO) request.getAttribute("MemberVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|登入頁面</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/bootstrap-icons.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wrunner-default-theme.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">


</head>
<body class="sign_right" style="padding-top:50px;background-image: url('./loginBg.jpg');">
<%@ include file="/pages/header.file" %>
	
	<div class="container">
		<div class="row">
			<div class="col-4">
				<div class="sign_in_form">
					<h3 class="sign_title">Welcome Back</h3>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<form name="signin" id="signin" action="member.html" method="post">
						<label for="">EMAIL ADDRESS<br></label>
						 <input type="text"	id="MEMBER_EMAIL" name="MEMBER_EMAIL" class="sign_form"placeholder="name@address.com"> 
						 <label for=""class="ps_zone">PASSWORD
						 <input	type="password" name="MEMBER_PASSWORD" class="sign_form"placeholder="Password">
						  <input type="hidden"name="action" value="signin"> 
						  <input type="submit" value="會員登入">
					</form>
						   <input type=button id="fp" value="忘記密碼?"  style="width:100%; border:none;background-color: #4166F8;color:white;" > 
				</div>
				<div class="sign_in_other">
					<h3 class="title">
						<span>OR</span>
					</h3>
					<span class="sign_up">還沒有帳號?<a
						href="${pageContext.request.contextPath}/sign/signup.jsp">點我註冊</a></span>
						
						
				<form name="frmApp" action="member.html" id="frmAppId" mothed="post">
  					 <input id="test" type="hidden" name="test">
  					 <input type="hidden"name="action" value="forget_password"> 
				</form>
				</div>
			</div>
			<div class="col-8 "></div>
		</div>
	</div>
</body>

	<script>

	var obt=document.getElementById("fp");
  	obt.onclick=function(){
  	 var infor = window.prompt("若您真的忘記了當初註冊之密碼，請輸入註冊信箱，我們會傳一組新密碼，請稍後至信箱查看。收信時間不定，請耐心等候，並盡快更改密碼，謝謝!");
  	document.getElementById("test").value = infor;
  	var frm = document.getElementById("frmAppId"); 
    frm.submit(); 
  	
  	}
</script>
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/header.js"></script>
</html>