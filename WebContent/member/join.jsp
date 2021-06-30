<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/header.file"%>


<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	MemberVO MemberVO = (MemberVO) session.getAttribute("login");
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
<div class="container">
        
            

        <div class="row">

            <div class="col-2">
                
            </div>
            <div class="col-10">
                <form action="member.html" class="personal_form" method="post"  enctype="multipart/form-data">
                    <label for="">商家名稱:	<input type="text" name="Shop_name" required></label><br><br>
                    <label for="">統一編號:	<input type="text" name="Shop_tax_id" maxlength="8" minlength="8" onkeyup="value=value.replace(/[^(\d)]/g,'')" required></label><br><br>
                    <label for="">郵遞區號:	<input type="text" name="Shop_zip_code" onkeyup="value=value.replace(/[^(\d)]/g,'')" required></label><br><br>
                    <label for="">地址:		<input type="text" name="address" required></label><br>
                    <label for="">縣市:		<input type="text" name="SHOP_CITY" ></label><br>
                    <label for="">商家所在地-經度:	<input type="text" name="Shop_latitude" maxlength="10" onkeyup="value=value.replace(/[^(\d)]/g,'.')" required></label><br><br>
                    <label for="">商家所在地-緯度:	<input type="text" name="Shop_longitude" maxlength="10" onkeyup="value=value.replace(/[^(\d)]/g,'.')" required></label><br><br>
                    <label for="">商家電話:	<input type="text" name="Shop_phone" maxlength="10" onkeyup="value=value.replace(/[^(\d)]/g,'')" required></label><br><br>
                    <label for="">商家信箱:	<input type="email" name="Shop_email"  ></label><br><br>
                    <label for="">商家介紹:	<textarea name="shop_description" required></textarea></label><br><br>
                    <label for="">商家標籤:	<textarea name="Shop_tag" placeholder="多個標籤請用分號分隔"></textarea></label><br><br>
                    <label for="">商家均消:	<input type="text" name="Shop_price_level" onkeyup="value=value.replace(/[^(\d)]/g,'')"></label><br><br>
                    <label for="">營業時間:	<input type="text" name="Shop_opening_time" ></label><br><br>
                    <label for="">商家網頁:	<input type="text" name="Shop_website" ></label><br><br>
					<label style="border:1px solid black;border-radius:5px;">上傳封面<input style="display:none" type="file" name="SHOP_MAIN_IMG" ></label><br><br>              
                    <label style="border:1px solid black;border-radius:5px;">上傳圖片庫<input style="display:none" type="file" name="SHOP_GALLERY" multiple></label><br><br>            
                    <label for="">訂位開放狀態:	
                    <select name="Shop_reservation_status">
                            <option value=1 selected=selected >開放</option>
                            <option value=2 >未開放</option>
                   </select></label><br><br>
                    <input type="hidden" name="action" value="insert_shop">
                    <input type="submit" value="儲存" class="save_btn" style="width:150px">
                </form>
            </div>
        </div>
    </div>

</body>
</html>