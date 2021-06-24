<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	MemberVO MemberVO = (MemberVO) request.getAttribute("MemberVO");

	ShopVO ShopVO = (ShopVO) request.getAttribute("ShopVO");
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|修改個人資料</title>
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
                
            </div>
            <div class="col-10">
                <form action="member.html"  method="post"  enctype="multipart/form-data">
                	<input type=hidden value="${ShopVO.getShop_id()}" name="SHOP_ID">
                	<input type=hidden value="${MemberVO.getMember_email()}" name="MEMBER_EMAIL">
                    <label for="">店名<br><input type="text" name="SHOP_NAME" value="${ShopVO.getShop_name()}"></label><br>
                    <label for="">均消<br><input type="text" name="SHOP_PRICE_LEVEL"  value="${ShopVO.getShop_price_level()}"></label><br>
                    <label for="">營業時間<br><input type="text" name="SHOP_OPENING_TIME"  value="${ShopVO.getShop_opening_time()}"></label><br>
                    <label for="">地址<br><input type="text" name="SHOP_ADDRESS" value="${ShopVO.getShop_address()}"></label><br>
                    <label for="">縣市<br><input type="text" name="SHOP_CITY" value="${ShopVO.getShop_city()}"></label><br>
                    <label for="">粉絲專頁<br><input type="text" name="SHOP_WEBSITE" value="${ShopVO.getShop_website()}"></label><br>
                    <label for="">電話<br><input type="text" name="SHOP_PHONE" value="${ShopVO.getShop_phone()}"></label><br>
					<input type="file" name="SHOP_MAIN_IMG" value="/upload/${ShopVO.getShop_main_img()}">                    
                    <input type="hidden" name="action" value="update_shop">
                    <input type="submit" value="儲存" class="save_btn" style="width:150px">
                </form>
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