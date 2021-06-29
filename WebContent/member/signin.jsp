<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.search.model.*"%>
<%@ include file="/pages/header.file" %>

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
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">


</head>
<body class="sign_right">
	
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
					<form name="signin" action="member.html" method="post">
						<label for="">EMAIL ADDRESS<br></label>
						 <input type="text"	name="MEMBER_EMAIL" class="sign_form"placeholder="name@address.com"> 
						 <label for=""class="ps_zone">PASSWORD <a href="#">忘記密碼?</a></label> 
						 <input	type="password" name="MEMBER_PASSWORD" class="sign_form"placeholder="Password">
						  <input type="hidden"name="action" value="signin"> <input type="submit"
							value="會員登入">
					</form>
				</div>
				<div class="sign_in_other">
					<h3 class="title">
						<span>OR</span>
					</h3>
<!-- 					<button class="fb_sign"> -->
<!-- 						<i class="fab fa-facebook-f" style="color: #4166F8"></i><span -->
<!-- 							class="fb_text">透過FACEBOOK登入</span> -->
<!-- 					</button> -->
<!-- 					<br> -->
<!-- 					<button class="google_sign"> -->
<!-- 						<i class="fab fa-google" style="color: #707070"></i><span -->
<!-- 							class="g_text">透過GOOGLE登入</span> -->
<!-- 					</button> -->
<!-- 					<h3 class="title"> -->
<!-- 						<span>OR</span> -->
<!-- 					</h3> -->
					<span class="sign_up">還沒有帳號?<a
						href="http://localhost:8081/TFA101_team4/sign/signup.jsp">點我註冊</a></span>
				</div>
			</div>
			<div class="col-8 "></div>
		</div>
	</div>
	</div>










	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/wrunner-jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/scripts.js"></script>

</body>

</html>