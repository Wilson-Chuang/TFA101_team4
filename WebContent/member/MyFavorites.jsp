<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop_favorites.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	MemberVO MemberVO = (MemberVO) request.getAttribute("MemberVO");


	List<ShopVO> list_myFavShop = (List<ShopVO>) request.getAttribute("list_myFavShop");
	List<MemberVO> list_following = (List<MemberVO>) request.getAttribute("list_following");

	List<MemberVO> list_fans = (List<MemberVO>) request.getAttribute("list_fans");
	Shop_FavoritesService shopfavSvc= new Shop_FavoritesService();
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|我的收藏</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/bootstrap-icons.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wrunner-default-theme.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">

</head>
<body>
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
			<div class="col-2">
				<img
					src="/upload/<%=((MemberVO) (session.getAttribute("login"))).getMember_pic()%>"
					width="150px" alt="" class="member_pic" id="showimg">
			</div>
			<div class="col-10">
				<span class="member_name"><%=((MemberVO) (session.getAttribute("login"))).getMember_name()%></span>
				<span class="member_status">一般會員</span><br> <span
					class="comments_count">0則評論</span> <span class="followers_count"><%=((MemberVO) (session.getAttribute("login"))).getMember_fans()%>個粉絲</span>
				<span class="followers_count">會員ID:<%=((MemberVO) (session.getAttribute("login"))).getMember_id()%></span>
			</div>

		</div>

		<div class="row">

			<div class="col-2">
				<ul class="sidebar_ul">
					<li class="sidebar"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="signin"> <input
								type="submit" value="個人資料" class="save_btn"
								style="width: 150px; background: none; color: black">
						</form></li>
					<hr>
					<li class="sidebar"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="toWallet"> <input
								type="submit" value="我的錢包" class="save_btn"
								style="width: 150px; background: none; color: black">
						</form></li>
					<hr>
					<li class="sidebar"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="toFavorites"> <input
								type="submit" value="我的收藏" class="save_btn"
								style="width: 150px; background: none; color: black">
						</form></li>
					<hr>
					<li class="sidebar"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="toActive"> <input
								type="submit" value="活動紀錄" class="save_btn"
								style="width: 150px; background: none; color: black">
						</form></li>
					<hr>
					<li class="sidebar  lock"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="toShop"> <input
								type="submit" value="商家專區" class="save_btn"
								style="width: 150px; background: none; color: black">
						</form></li>
					<hr>
				</ul>
			</div>
			<div class="col-10">
				<ul class="nav nav-tabs">
					<li class="favorite_page_li"><a class="favorite_page"
						href="#article_follower" rel="external nofollow" data-toggle="tab">文章追蹤</a></li>
					<li class="favorite_page_li"><a class="favorite_page"
						href="#shop_favorite" rel="external nofollow" data-toggle="tab">餐廳收藏</a></li>
					<li class="favorite_page_li"><a class="favorite_page"
						href="#member_follower_fans" rel="external nofollow"
						data-toggle="tab">我的粉絲</a></li>
					<li class="favorite_page_li"><a class="favorite_page"
						href="#member_follower_follow" rel="external nofollow"
						data-toggle="tab">追蹤清單</a></li>

				</ul>
				<!--標籤的內容-->
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="article_follower">
						<div class="card mb-3" style="max-width: 540px;">
							<div class="row no-gutters">
								<div class="col-md-4">
									<img src="./wilson/loginBg.jpg" class="card-img" alt="...">
								</div>
								<div class="col-md-8">
									<p class="card-text">您追蹤了「甜點界女王 Lady M x 潮流酒吧 WAT
										推出「粉黛荔香瓶裝雞尾酒」搭配「玫瑰千層」讓今年來點不一樣的母親節下午茶！」這篇文章</p>
								</div>
							</div>
						</div>
						<div class="card mb-3" style="max-width: 540px;">
							<div class="row no-gutters">
								<div class="col-md-4">
									<img src="./wilson/1.jpg" class="card-img" alt="...">
								</div>
								<div class="col-md-8">
									<p class="card-text">您追蹤了「5/8~5/9 母親節帶媽咪逛市集！40攤美食+手作市集在圓山花博
										搞市生活節等你，還有塔羅、創作活動，待一整天都沒問題！」這篇文章</p>
								</div>
							</div>
						</div>
					</div>
					<div class="tab-pane fade" id="shop_favorite">
					
					
					<%
 							for (ShopVO shop : list_myFavShop) {
 						%> 
							
						<div class="card" style="width: 18rem;">
							<a href="<%=request.getContextPath()+"/member/Shop.jsp?shop_id="+shop.getShop_id() %>">
							<img class="card-img-top" src="/upload/<%=shop.getShop_main_img() %>" alt="">
							</a>
							<div class="card-body">
							
								<h5 class="card-title"><%=shop.getShop_name() %></h5>
								<form action="member.html" method="post">
								<i class="fas fa-trash-alt"></i>
								<input type=hidden name= "MEMBER_ID" value="${MemberVO.member_id}">
								<input type=hidden name= "SHOP_ID" value="<%=shop.getShop_id()%>">
								<input type=hidden name="action" value="delete_sf">
								<input type="submit" value="移除"></input>
								</form>
							</div>
						</div>
					<%
 							}
						%> 
					
						
					</div>
					<div class="tab-pane fade" id="member_follower_fans">
					<%
							for (MemberVO fans : list_fans) {
						%>
						<div class="card follow_card" style="width: 18rem;">
						<a href="<%=request.getContextPath()+"/member/Person.jsp?member_id="+fans.getMember_id() %>">
							<img class="card-img-top" src="/upload/<%=fans.getMember_pic()%>"
								alt="Card image cap"></a>
							<div class="card-body">
								<h5 class="card-title"><%=fans.getMember_name()%></h5>
							</div>
							<ul class="list-group list-group-flush">
							<li class="list-group-item"><a href="#" class="card-link"><i
										class="fas fa-comments">聊天</i></a></li>
								</ul>

						</div>
						<%
							}
						%>
					</div>
						
					<div class="tab-pane fade" id="member_follower_follow">
						<%
							for (MemberVO fing : list_following) {
// 								System.out.println(fing.getMember_id());
// 								System.out.println(MemberVO.getMember_id());
						%>
						<div class="card fans_card" style="width: 18rem;">
						<a href="<%=request.getContextPath()+"/member/Person.jsp?member_id="+fing.getMember_id() %>">
							<img class="card-img-top"
								src="/upload/<%=fing.getMember_pic()%>" alt="Card image cap"></a>
							<div class="card-body">
								<h5 class="card-title"><%=fing.getMember_name()%></h5>
							</div>
							<ul class="list-group list-group-flush">
							<li class="list-group-item"><a href="#" class="card-link"><i
										class="fas fa-comments">聊天</i></a></li>
								<li class="list-group-item">
								<form action="member.html" method="post">
								<i class="fas fa-user-times">
								<input type=hidden name= "MEMBER_ID" value="${MemberVO.member_id}">
								<input type=hidden name= "MEMBER_ID_FOL" value="<%= fing.getMember_id()%>">
								<input type=hidden name="action" value="delete_fol">
								<input type="submit" value="移除"></input></i>
								</form>
							</ul>
						</div>

						<%
							}
						%>


					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/wrunner-jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<script type="text/javascript">
	function show(f) {
		var reader = new FileReader();//建立檔案讀取物件
		var files = f.files[0];//獲取file元件中的檔案
		reader.readAsDataURL(files);//檔案讀取裝換為base64型別
		reader.onloadend = function(e) {
			//載入完畢之後獲取結果賦值給img
			document.getElementById("showimg").src = this.result;
		}
	}
</script>
</html>