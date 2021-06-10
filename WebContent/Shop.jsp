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
<title>Giude好食|商店頁面</title>
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
                <ul class="sidebar_ul">
                    <li class="sidebar"><form method="post" action="member.html" class="personal_form"><input type=hidden name="action" value="signin">
                    <input type="submit" value="個人資料" class="save_btn" style="width:150px;background:none;color:black"></form>
                        </li>
                    <hr>
                    <li class="sidebar"><form method="post" action="member.html" class="personal_form"><input type=hidden name="action" value="toWallet">
                    <input type="submit" value="我的錢包" class="save_btn" style="width:150px;background:none;color:black"></form>
                        </li>
                    <hr>
                    <li class="sidebar"><form method="post" action="member.html" class="personal_form"><input type=hidden name="action" value="toFavorites">
                    <input type="submit" value="我的收藏" class="save_btn" style="width:150px;background:none;color:black"></form>
                        </li>
                    <hr>
                    <li class="sidebar"><form method="post" action="member.html" class="personal_form"><input type=hidden name="action" value="toActive">
                    <input type="submit" value="活動紀錄" class="save_btn" style="width:150px;background:none;color:black"></form>
                        </li>
                    <hr>
                    <li class="sidebar  lock"><form method="post" action="member.html" class="personal_form"><input type=hidden name="action" value="toShop">
                    <input type="submit" value="商家專區" class="save_btn" style="width:150px;background:none;color:black"></form>
                        </li>
                    <hr>
                </ul>
            </div>
                    <div class="col-10">
                <div class="row">
                    <div class="col-md-4">
                        <img src="./wilson/loginBg.jpg" class="card-img" alt="...">
                    </div>
                    <div class="col-md-8 shop_zone">
                        <h1 class="shop_title">老二樓串燒居酒屋</h1>
                        <span class="ratins">4.1<i class="fas fa-star"></i></span><span class="coms">125則評論</span>
                        <span class="avg_prices">均消$350</span> <span class="tags">清酒 串燒 燒烤</span><br>
                        <span class="open_time">營業時間:00:00~23:00</span><br>
                        <span class="address">地址:長安東路二段172號</span><br>
                        <span class="webs"><a>粉絲專頁:facebook.com</a></span><br>
                        <span class="phone">連絡電話:0987654321</span><br>
                    </div>
                </div>
                <h1>餐廳照片</h1>
                <div class="row">

                    <div class="shop_pic">
                        <img src="./wilson/1.jpg" class="shop_img">
                    </div>
                    <div class="shop_pic">
                        <img src="./wilson/2.jpg" class="shop_img">
                    </div>
                    <div class="shop_pic">
                        <img src="./wilson/3.jpg" class="shop_img">
                    </div>
                    <div class="shop_pic">
                        <img src="./wilson/4.jpg" class="shop_img">
                    </div>

                </div>
                <h1>餐廳評論</h1>
                <div class="row">
                    <div class="comment_table">
                    	<form>
                        <a href="#" class="text-decoration-none commentStar star-count-1">
    <i class="fa fa-star text-light p-1 bg-secondary" aria-hidden="true"></i>
  </a>
  <a href="#" class="text-decoration-none commentStar star-count-2">
    <i class="fa fa-star text-light p-1 bg-secondary" aria-hidden="true"></i>
  </a>
  <a href="#" class="text-decoration-none commentStar star-count-3">
    <i class="fa fa-star text-light p-1 bg-secondary" aria-hidden="true"></i>
  </a>
  <a href="#" class="text-decoration-none commentStar star-count-4">
    <i class="fa fa-star text-light p-1 bg-secondary" aria-hidden="true"></i>
  </a>
  <a href="#" class="text-decoration-none commentStar star-count-5">
    <i class="fa fa-star text-light p-1 bg-secondary" aria-hidden="true"></i>
  </a>
                        <br>
                        <textarea class="comment_text" placeholder="想說什麼呢?"></textarea><br>
                        <button class="photo_update">上傳照片</button><br>
                        <input type="submit" value="送出" class="comment_btn"><br><hr>
                    </div>
                    <div class="comment_zone">
                        <h3 class="user">用戶A</h3>
                        <p>2021/05/05 11:24:32</p>
                        <span class="ratins">4<i class="fas fa-star"></i></span><br>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vitae voluptas assumenda sequi
                            dignissimos possimus asperiores laboriosam, dolorum odit commodi vel aperiam voluptates
                            numquam voluptatibus quia iure, porro, quo beatae veniam mollitia. Dolores officia officiis,
                            quidem, tempore reiciendis est assumenda doloribus voluptate optio eos, sit maxime
                            consequatur dolorum iure itaque natus.</p>
                            <img src="./wilson/1.jpg" alt="" style="width:25%">
                            <hr>
                    </div>
                    <div class="comment_zone">
                        <h3 class="user">用戶B</h3>
                        <p>2021/05/04 10:04:22</p>
                        <span class="ratins">4<i class="fas fa-star"></i></span><br>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vitae voluptas assumenda sequi
                            dignissimos possimus asperiores laboriosam, dolorum odit commodi vel aperiam voluptates
                            numquam voluptatibus quia iure, porro, quo beatae veniam mollitia. Dolores officia officiis,
                            quidem, tempore reiciendis est assumenda doloribus voluptate optio eos, sit maxime
                            consequatur dolorum iure itaque natus.</p>
                            <img src="./wilson/2.jpg" alt="" style="width:25%">
                            <hr>
                    </div>
                </div>

            </div>
        </div>
    </div>


</body>
   <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/wrunner-jquery.js"></script>
    <script src="js/scripts.js"></script>
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
	<script type="text/javascript">
	let starIcon = document.querySelectorAll('.commentStar i');
	for (let i = 0; i < starIcon.length; i++) {
	  starIcon[i].addEventListener(
	    'click',
	    function(event) {
	      event.preventDefault();
	      console.log('preventDefault will stop you from checking this checkbox!');
	      console.log(this);
	      for (let j = 0; j <= i; j++) {
	        starIcon[j].classList.add('bg-danger');
	        console.log('第 ' + j + ' 號星 加上紅色');
	      }
	      for (let k = 4; k > i; k--) {
	        starIcon[k].classList.remove('bg-danger');
	        console.log('第 ' + k + ' 號星 去除紅色');
	      }
	      var starNumber = i + 1; // starNumber = 傳到資料庫的星數
	      console.log('評論星數為 ' + starNumber);
	    },
	    false
	  );
	}
	</script>
</html>