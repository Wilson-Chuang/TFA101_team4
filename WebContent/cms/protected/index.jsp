<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*"%>

<%
  ManagerVO managerVO = (ManagerVO) request.getAttribute("managerVO"); //EmpServlet.java (Concroller) 存入req的managerVO物件 (包括幫忙取出的managerVO, 也包括輸入資料錯誤時的managerVO物件)
%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gudie好食 | 後台系統</title>

<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/manager/css/managerIndex.css">
</head>

<body>

	<header class="header">

		<div class="logo">
			<img src="${pageContext.request.contextPath}/manager/images/logo(white).png">
		</div>
		<div class="Breadcrumbs">
			<form>
				<!-- placeholder輸入欄裡的預設字 -->
				<input type="search" placeholder="搜尋">
				<button>
					<i class="fas fa-search"></i>
				</button>
			</form>
		</div>
		 <div class="user">
            <span class="guidefoodindex">
                <a href="#">前台網頁</a>
            </span>
            <span>
                &emsp; &emsp; ${manager_account}，您好&emsp;
            </span>
            <span>
                <a href="#">登出</a>
            </span>
        </div>

	</header>



	<div class="main_content">

		<div class="aside-menu">

			<!--             
            <form>
                placeholder輸入欄裡的預設字
                <input type="search" placeholder="搜尋">
                <button><i class="fas fa-search"></i></button>
            </form> -->

			<ul class="menu-name">
				<li><a href="#"> <i class="fas fa-home"></i> 首頁
				</a></li>
				<li><a href="#" onclick="toggleMenu(1);">用戶管理</a>
					<ul class="hide" id="menu-1">
						<li><a href="#">會員管理</a></li>
						<li><a href="${request.getContextPath}/cms/protected/manager_listAll_include.jsp">管理員管理</a></li>
					</ul></li>
				<li><a href="#" onclick="toggleMenu(2);">商家管理</a>
					<ul class="hide" id="menu-2">
						<li><a href="#">商家認領審核</a></li>
						<li><a href="#">商家圖文管理</a></li>
						<li><a href="#">商家標籤管理</a></li>
					</ul></li>
				<li><a href="#" onclick="toggleMenu(3);">社群管理</a>
					<ul class="hide" id="menu-3">
						<li><a href="#">評論管理</a></li>
						<li><a href="#">討論區文章管理</a></li>
						<li><a href="#">專欄文章管理</a></li>
						<li><a href="#">揪團管理</a></li>
					</ul></li>
				<li><a href="#" onclick="toggleMenu(4);">商品管理</a>
					<ul class="hide" id="menu-4">
						<li><a href="#">產品管理</a></li>
						<li><a href="#">系列管理</a></li>
						<li><a href="#">訂單管理</a></li>
					</ul></li>
				<li><a href="#">意見回饋</a></li>
				<li><a href="#">網站管理</a></li>
			</ul>
		</div>

		<div class="main">
			${manager_email}
			${managerVO.manager_account}
		</div>



	</div>

	<script>
		// 設定側邊欄，點擊展開/縮合
		function toggleMenu(number) {
			var menu = document.getElementById("menu-" + number);

			//display.none 代表隱藏。 
			if (menu.style.display == "none") {
				// 點擊後，若現在狀態為隱藏，則展開。
				menu.style.display = "block";
			} else {
				// 點擊後，若現在狀態為展開，則隱藏。
				menu.style.display = "none";
			}
		}
	</script>


	<!-- body 結束標籤之前，載入Bootstrap 的 JS 及其相依性安裝(jQuery、Popper) -->
	<script src="./vendors/jquery/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script src="./vendors/popper/popper.min.js"></script>
	<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>