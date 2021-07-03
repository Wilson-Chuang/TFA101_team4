<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.search.model.*"%>
<%
	MemberVO MemberVO = (MemberVO) request.getAttribute("MemberVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|註冊頁面</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/bootstrap-icons.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wrunner-default-theme.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">


</head>
<body class="sign_right" style="padding-top:50px;background-image: url('./loginBg.jpg');">
   
	<div class="container">
		<div class="row">
			<div class="col-4">
				<div class="sign_in_form">
					<h3 class="sign_title">Sign Up</h3>
						<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<form name="signup" action="member.html" method="post">
					<label for="">EMAIL ADDRESS<br></label> 
					<input type="email" name="EMAIL_ADDRESS"class="sign_form" placeholder="name@address.com">
					<label	for="" class="ps_zone">PASSWORD</label>
					<input type="password" name="PASSWORD"class="sign_form" minlength="8" placeholder="Password"> 
					<label for="" class="ps_zone">CONFIRM YOUR PASSWORD</label>
					<input type="password" name="CON_PASSWORD"class="sign_form" placeholder="Password">
				    <input type="hidden" name="action" value="insert">
                    <input type="submit" value="會員註冊">
					</form>

				</div>
				<div style="text-align: center;">
					<hr>
					<span class="sign_up">註冊則代表您同意以下<a href="">隱私條款</a></span>
				</div>

			</div>
			<div class="col-8 "></div>
		</div>
	</div>

</body>




<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/wrunner-jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/scripts.js"></script>

</html>