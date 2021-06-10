<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	MemberVO MemberVO = (MemberVO) request.getAttribute("MemberVO");
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|我的錢包</title>
<link href="css/bootstrap.min.css" rel="stylesheet" />
<link href="css/bootstrap-icons.css" rel="stylesheet" />
<link href="css/materialdesignicons.min.css" rel="stylesheet" />
<link href="css/wrunner-default-theme.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">

</head>
<body>
 <nav class="fixed-top bg-body">
        <div class="container-fluid">
            <div class="row g-0">
                <div class="col-lg-2 d-flex justify-content-center align-items-center">
                    <a class="navbar-brand" href="#">
                        <img style="min-height:70px;max-height:70px;max-width:150px" src="./img/header/logo.png" />
                    </a>
                </div>
                <form class="col-lg-6" method="post" action="">
                    <div class="row">
                        <div class="col-md-5 pt-2">
                            <div class="row g-0 d-flex justify-content-center">
                                <div class="btn-group btn-group-sm" role="group">
                                    <input type="radio" class="btn-check" name="btn-route" id="motorbike" value="motorbike" autocomplete="on" checked />
                                    <label class="btn btn-outline-primary" for="motorbike">
                                        <i class="mdi mdi-motorbike" aria-hidden="true"></i>
                                    </label>
                                    <input type="radio" class="btn-check" name="btn-route" id="bike" value="bike" autocomplete="on" />
                                    <label class="btn btn-outline-primary" for="bike">
                                        <i class="mdi mdi-bike" aria-hidden="true"></i>
                                    </label>
                                    <input type="radio" class="btn-check" name="btn-route" id="walk" value="walk" autocomplete="on" />
                                    <label class="btn btn-outline-primary" for="walk">
                                        <i class="mdi mdi-walk" aria-hidden="true"></i>
                                    </label>
                                    <input type="radio" class="btn-check" name="btn-route" id="car" value="car" autocomplete="on" />
                                    <label class="btn btn-outline-primary" for="car">
                                        <i class="mdi mdi-car" aria-hidden="true"></i>
                                    </label>
                                    <input type="radio" class="btn-check" name="btn-route" id="train" value="train" autocomplete="on" />
                                    <label class="btn btn-outline-primary" for="train">
                                        <i class="mdi mdi-train" aria-hidden="true"></i>
                                    </label>
                                </div>
                            </div>
                            <div class="text-center">
                                <label class="pt-2">搜尋範圍: <label id="reachtime">5</label>分鐘內可抵達（<label id="route">機車</label>）</label>
                                <div class="row g-0 d-flex justify-content-center align-items-center">
                                    <div class="col-sm-9 px-3">
                                        <div id="range-slider"></div>
                                        <input type="hidden" name="reachtime" id="btn-reachtime" value="5" />
                                    </div>
                                    <div class="col-sm-3">
                                        <button type="button" class="btn btn-primary text-nowrap">確定</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7 pt-1">
                            <div class="input-group d-flex justify-content-center">
                                <select name="search-type" id="search-type" class="form-select-sm">
                                    <option value="shop" selected>商家</option>
                                    <option value="article">專欄</option>
                                    <option value="product">商品</option>
                                    <option value="party">揪團</option>
                                </select>
                                <input type="text" name="place-bar" id="place-bar" class="form-control w-25 search-bar" placeholder="地點" />
                                <input type="text" name="shop-keyword-bar" id="shop-keyword-bar" class="form-control w-25 search-bar" placeholder="關鍵字" />
                                <button type="button" class="btn btn-primary btn-sm" id="btn-submit"><i class="bi bi-search" aria-hidden="true"></i></button>
                            </div>
                            <div class="row g-2 d-flex justify-content-evenly align-items-center pt-4" id="popular-key">
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">火鍋</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">早午餐</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">約會餐廳</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">牛排</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">居酒屋</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">餐酒館</a>								
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">日式料理</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">吃到飽</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">甜點</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">燒肉</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">韓式料理</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">小吃</a>
                                <a href="#" class="badge bg-primary link-light col-sm-auto text-decoration-none mx-1">泰式料理</a>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="col-lg-4 pt-2">
                    <div class="text-end" id="topnav">
                        <a href="#" class="text-decoration-none text-nowrap fw-bold badge bg-warning link-dark fs-5 align-middle me-4" id="shop-join">
                            <i class="bi bi-shop pe-2" aria-hidden="true"></i>商家加入
                        </a>
                        <button type="button" class="btn btn-outline-primary me-3 guest">登入</button>
                        <button type="button" class="btn btn-primary me-4 guest">註冊</button>						
                    </div>
                    <nav class="navbar-expand-lg pt-4">
                        <ul class="nav justify-content-evenly">
                            <li class="nav-item">
                                <a class="nav-link h5" href="#">首頁</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle h5" href="#" data-bs-toggle="dropdown">專欄</a>
                                <ul class="dropdown-menu text-center">
                                    <li><a class="dropdown-item" href="#">專欄 item 1</a></li>
                                    <li><a class="dropdown-item" href="#">專欄 item 2</a></li>
                                    <li><a class="dropdown-item" href="#">專欄 item 3</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle h5" href="#" data-bs-toggle="dropdown">討論區</a>
                                <ul class="dropdown-menu text-center">
                                    <li><a class="dropdown-item" href="#">討論區 item 1</a></li>
                                    <li><a class="dropdown-item" href="#">討論區 item 2</a></li>
                                    <li><a class="dropdown-item" href="#">討論區 item 3</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle h5" href="#" data-bs-toggle="dropdown">揪團</a>
                                <ul class="dropdown-menu text-center">
                                    <li><a class="dropdown-item" href="#">揪團 item 1</a></li>
                                    <li><a class="dropdown-item" href="#">揪團 item 2</a></li>
                                    <li><a class="dropdown-item" href="#">揪團 item 3</a></li>
                                </ul>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </nav>
<div class="container">
        <div class="row">
            <div class="col-2">
                <img src="/upload/<%=((MemberVO) (session.getAttribute("login"))).getMember_pic() %>" width="150px" alt="" class="member_pic" id="showimg">
            </div>
            <div class="col-10">
                <span class="member_name"><%=((MemberVO) (session.getAttribute("login"))).getMember_name() %></span>
                <span class="member_status">一般會員</span><br>
                <span class="comments_count">0則評論</span>
                <span class="followers_count"><%=((MemberVO) (session.getAttribute("login"))).getMember_fans() %>個粉絲</span>
                <span class="followers_count">會員ID:<%=((MemberVO) (session.getAttribute("login"))).getMember_id() %></span>
            </div>

        </div>

        <div class="row">

            <div class="col-2">
                <ul class="sidebar_ul">
                    <li class="sidebar"><form action="member.html" class="personal_form"><input type=hidden name="action" value="signin">
                    <input type="submit" value="個人資料" class="save_btn" style="width:150px;background:none;color:black"></form>
                        </li>
                    <hr>
                    <li class="sidebar"><form action="member.html" class="personal_form"><input type=hidden name="action" value="toWallet">
                    <input type="submit" value="我的錢包" class="save_btn" style="width:150px;background:none;color:black"></form>
                        </li>
                    <hr>
                    <li class="sidebar"><form action="member.html" class="personal_form"><input type=hidden name="action" value="toFavorites">
                    <input type="submit" value="我的收藏" class="save_btn" style="width:150px;background:none;color:black"></form>
                        </li>
                    <hr>
                    <li class="sidebar"><form action="member.html" class="personal_form"><input type=hidden name="action" value="toActive">
                    <input type="submit" value="活動紀錄" class="save_btn" style="width:150px;background:none;color:black"></form>
                        </li>
                    <hr>
                    <li class="sidebar  lock"><form action="member.html" class="personal_form"><input type=hidden name="action" value="toShop">
                    <input type="submit" value="商家專區" class="save_btn" style="width:150px;background:none;color:black"></form>
                        </li>
                    <hr>
                </ul>
            </div>
            <div class="col-10">
        <div class="row">
            <span class="point">目前積分餘額:<br><%=((MemberVO) (session.getAttribute("login"))).getMember_point() %></span><br>
        </div>
        <h3 class="wallet" style="text-align:center;">消費紀錄</h3>  
        <ul class="shopping_record" >
            <li><span class="date">2021/05/13</span><span class="time">12:25</span><span class="item">使用1000積分購買「LADY M草莓千層蛋糕」</span></li>
            <li><span class="date">2021/05/12</span><span class="time">16:25</span><span class="item">使用優惠券「胡同燒肉」</span></li>
            <li><span class="date">2021/05/11</span><span class="time">11:05</span><span class="item">使用5000積分購買「星巴克不鏽鋼保溫瓶」</span></li>
            <li><span class="date">2021/05/10</span><span class="time">19:55</span><span class="item">使用優惠券「王品牛排」</span></li>
        </ul>
    </div>
</div>
</div>

</body>
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