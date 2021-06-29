<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

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
	<nav class="fixed-top bg-body">
		<div class="container-fluid">
			<div class="row g-0">
				<div
					class="col-lg-2 d-flex justify-content-center align-items-center">
					<a class="navbar-brand" href="#"> <img
						style="min-height: 70px; max-height: 70px; max-width: 150px"
						src="./img/header/logo.png" />
					</a>
				</div>
				<form class="col-lg-6" method="post" action="">
					<div class="row">
						<div class="col-md-5 pt-2">
							<div class="row g-0 d-flex justify-content-center">
								<div class="btn-group btn-group-sm" role="group">
									<input type="radio" class="btn-check" name="btn-route"
										id="motorbike" value="motorbike" autocomplete="on" checked />
									<label class="btn btn-outline-primary" for="motorbike">
										<i class="mdi mdi-motorbike" aria-hidden="true"></i>
									</label> <input type="radio" class="btn-check" name="btn-route"
										id="bike" value="bike" autocomplete="on" /> <label
										class="btn btn-outline-primary" for="bike"> <i
										class="mdi mdi-bike" aria-hidden="true"></i>
									</label> <input type="radio" class="btn-check" name="btn-route"
										id="walk" value="walk" autocomplete="on" /> <label
										class="btn btn-outline-primary" for="walk"> <i
										class="mdi mdi-walk" aria-hidden="true"></i>
									</label> <input type="radio" class="btn-check" name="btn-route"
										id="car" value="car" autocomplete="on" /> <label
										class="btn btn-outline-primary" for="car"> <i
										class="mdi mdi-car" aria-hidden="true"></i>
									</label> <input type="radio" class="btn-check" name="btn-route"
										id="train" value="train" autocomplete="on" /> <label
										class="btn btn-outline-primary" for="train"> <i
										class="mdi mdi-train" aria-hidden="true"></i>
									</label>
								</div>
							</div>
							<div class="text-center">
								<label class="pt-2">搜尋範圍: <label id="reachtime">5</label>分鐘內可抵達（<label
									id="route">機車</label>）
								</label>
								<div
									class="row g-0 d-flex justify-content-center align-items-center">
									<div class="col-sm-9 px-3">
										<div id="range-slider"></div>
										<input type="hidden" name="reachtime" id="btn-reachtime"
											value="5" />
									</div>
									<div class="col-sm-3">
										<button type="button" class="btn btn-primary text-nowrap">確定</button>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-7 pt-1">
							<div class="input-group d-flex justify-content-center">
								<select name="search-type" id="search-type"
									class="form-select-sm">
									<option value="shop" selected>商家</option>
									<option value="article">專欄</option>
									<option value="product">商品</option>
									<option value="party">揪團</option>
								</select> <input type="text" name="place-bar" id="place-bar"
									class="form-control w-25 search-bar" placeholder="地點" /> <input
									type="text" name="shop-keyword-bar" id="shop-keyword-bar"
									class="form-control w-25 search-bar" placeholder="關鍵字" />
								<button type="button" class="btn btn-primary btn-sm"
									id="btn-submit">
									<i class="bi bi-search" aria-hidden="true"></i>
								</button>
							</div>
							<div
								class="row g-2 d-flex justify-content-evenly align-items-center pt-4"
								id="popular-key">
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">火鍋</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">早午餐</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">約會餐廳</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">牛排</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">居酒屋</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">餐酒館</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">日式料理</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">吃到飽</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">甜點</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">燒肉</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">韓式料理</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">小吃</a>
								<a href="#"
									class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">泰式料理</a>
							</div>
						</div>
					</div>
				</form>
				<div class="col-lg-4 pt-2">
					<div class="text-end" id="topnav">
						<a href="#"
							class="text-decoration-none text-nowrap fw-bold badge bg-warning link-dark fs-5 align-middle me-4"
							id="shop-join"> <i class="bi bi-shop pe-2" aria-hidden="true"></i>商家加入
						</a>
						<button type="button" class="btn btn-outline-primary me-3 guest">登入</button>
						<button type="button" class="btn btn-primary me-4 guest">註冊</button>
					</div>
					<nav class="navbar-expand-lg pt-4">
						<ul class="nav justify-content-evenly">
							<li class="nav-item"><a class="nav-link h5" href="#">首頁</a>
							</li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle h5" href="#"
								data-bs-toggle="dropdown">專欄</a>
								<ul class="dropdown-menu text-center">
									<li><a class="dropdown-item" href="#">專欄 item 1</a></li>
									<li><a class="dropdown-item" href="#">專欄 item 2</a></li>
									<li><a class="dropdown-item" href="#">專欄 item 3</a></li>
								</ul></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle h5" href="#"
								data-bs-toggle="dropdown">討論區</a>
								<ul class="dropdown-menu text-center">
									<li><a class="dropdown-item" href="#">討論區 item 1</a></li>
									<li><a class="dropdown-item" href="#">討論區 item 2</a></li>
									<li><a class="dropdown-item" href="#">討論區 item 3</a></li>
								</ul></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle h5" href="#"
								data-bs-toggle="dropdown">揪團</a>
								<ul class="dropdown-menu text-center">
									<li><a class="dropdown-item" href="#">揪團 item 1</a></li>
									<li><a class="dropdown-item" href="#">揪團 item 2</a></li>
									<li><a class="dropdown-item" href="#">揪團 item 3</a></li>
								</ul></li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</nav>
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
						 <input	type="text" name="MEMBER_PASSWORD" class="sign_form"placeholder="Password">
						  <input type="hidden"name="action" value="signin"> <input type="submit"
							value="會員登入">
					</form>
				</div>
				<div class="sign_in_other">
					<h3 class="title">
						<span>OR</span>
					</h3>
					<button class="fb_sign">
						<i class="fab fa-facebook-f" style="color: #4166F8"></i><span
							class="fb_text">透過FACEBOOK登入</span>
					</button>
					<br>
					<button class="google_sign">
						<i class="fab fa-google" style="color: #707070"></i><span
							class="g_text">透過GOOGLE登入</span>
					</button>
					<h3 class="title">
						<span>OR</span>
					</h3>
					<span class="sign_up">還沒有帳號?<a
						href="http://localhost:8081/TFA101_team4/member/signup.jsp">點我註冊</a></span>
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