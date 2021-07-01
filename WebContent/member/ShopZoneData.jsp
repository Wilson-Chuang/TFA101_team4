<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member_follower.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ include file="/pages/header.file" %>


<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	MemberVO MemberVO = (MemberVO) session.getAttribute("login");
	MemberService memSvc = new MemberService();
	ShopVO ShopVO = memSvc.GET_ONE_BY_MEMBER(MemberVO.getMember_id());
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
                <form action="member.html"  method="post"  enctype="multipart/form-data">
                	<label for="">店家編號<br><input type=text value="<%=ShopVO.getShop_id()%>" name="SHOP_ID"></label><br>
                	<input type=hidden value="<%=MemberVO.getMember_email() %>" name="MEMBER_EMAIL">
                	<input type=hidden value="<%=ShopVO.getShop_main_img() %>" name="filename">
                    <label for="">店名<br><input type="text" name="SHOP_NAME" value="<%=ShopVO.getShop_name() %>"></label><br>
                    <label for="">均消<br><input type="text" name="SHOP_PRICE_LEVEL"  value="<%=ShopVO.getShop_price_level() %>"></label><br>
                    <label for="">營業時間<br><input type="text" name="SHOP_OPENING_TIME"  value="<%=ShopVO.getShop_opening_time() %>"></label><br>
                    <label for="">地址<br><input type="text" name="address" value="<%=ShopVO.getShop_address()%>"></label><br>
                    <label for="">縣市<br><input type="text" name="SHOP_CITY" value="<%=ShopVO.getShop_city() %>"></label><br>
                    <label for="">粉絲專頁<br><input type="text" name="SHOP_WEBSITE" value="<%=ShopVO.getShop_website() %>"></label><br>
                    <label for="">電話<br><input type="text" name="SHOP_PHONE" value="<%=ShopVO.getShop_phone() %>"></label><br>
                    <label for="">信箱<br><input type="email" name="SHOP_EMAIL" value="<%=ShopVO.getShop_email() %>"></label><br>
                    <label for="">介紹<br><input type="text" name="SHOP_DESC" value="<%=ShopVO.getShop_description() %>"></label><br>
                    <label for="">標籤<br><input type="text" name="SHOP_TAG" value="<%=ShopVO.getShop_tag() %>"></label><br>
                    <label style="border:1px solid black;border-radius:5px;">上傳封面<input style="display:none" type="file" name="SHOP_MAIN_IMG" value="/upload/<%=ShopVO.getShop_main_img() %>"></label><br>                
                    <label style="border:1px solid black;border-radius:5px;">上傳圖片庫<input style="display:none" type="file" name="SHOP_GALLERY" value="/upload/<%=ShopVO.getShop_gallery() %>" multiple></label><br>                
                    <input type="hidden" name="action" value="update_shop">
                    <input type="submit" value="儲存" class="save_btn" style="width:150px">
                </form>
            </div>
        </div>
    </div>

</body>
</html>