<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member_follower.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.search.model.*"%>

<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	MemberVO MemberVO = (MemberVO)session.getAttribute("login");
	MemberService memSvc=new MemberService();
	ShopVO ShopVO= memSvc.GET_ONE_BY_MEMBER(MemberVO.getMember_id());
	CommentService comSvc= new CommentService();
	int countByMember = comSvc.countByMember(MemberVO.getMember_id());
	
	Member_FollowerService folSvc=new Member_FollowerService(); 
	int count_fans = folSvc.count_fans(MemberVO.getMember_id());
	
	String picpath=request.getContextPath()+ File.separator+"UPLOAD" + File.separator + "member"+ File.separator + "pic"+ File.separator;
	String uploadFilePath = picpath +MemberVO.getMember_pic();
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|商家專區</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/bootstrap-icons.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wrunner-default-theme.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/header.js"></script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">
<%@ include file="/pages/header.file" %>

</head>
<body>


<div class="container">
        <div class="row">
            <div class="col-2">
                <img src="<%=(MemberVO.getMember_pic()).equals("noimage.jpg")?"./public/img/noimage.jpg":uploadFilePath%>" width="150px" alt="" class="member_pic" id="showimg">
            </div>
            <div class="col-10">
                <span class="member_name"><%=((MemberVO) (session.getAttribute("login"))).getMember_name() %></span>
                <span class="member_status">一般會員</span><br>
                <span class="comments_count">發表<%=countByMember %>則評論|</span>
                <span class="followers_count"><%=count_fans%>個粉絲|</span>
                <span class="followers_count">會員ID:<%=((MemberVO) (session.getAttribute("login"))).getMember_id() %></span>
            </div>

        </div>

        <div class="row">

            <div class="col-2">
                <ul class="sidebar_ul">
                    <li class="sidebar"><form action="member.html" class="personal_form"><input type=hidden name="action" value="toPersonal">
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
            <%if(ShopVO==null){
            
            %>
          		  <a href="<%=request.getContextPath()+"/member/join.jsp" %>">
	   				<img src="./public/img/joinus.jpg" style="width:80%;"></a>
	
			<%}else{ 
				String imgPath = request.getContextPath()+ File.separator+
						"uploads" + File.separator + "shop"+ File.separator +  ShopVO.getShop_tax_id()+ 
						File.separator +"images"+ File.separator ;
				String galleryPath = request.getContextPath()+ File.separator  + 
						"uploads" + File.separator + "shop"+ File.separator +  
						ShopVO.getShop_tax_id()+ File.separator +"gallery"+ File.separator;
			double shopRating=comSvc.countRatings(ShopVO.getShop_id());
			if(Double.isNaN(shopRating)){
				shopRating =0.0;
			}
			int countByShop = comSvc.countByShop(ShopVO.getShop_id());%>
                <div class="row">
                    <div class="col-md-4">
                    
                        <a href="<%=request.getContextPath()+"/public/Shop.jsp?shop_id="+ShopVO.getShop_id() %>">
                        <%String main_img=imgPath+ShopVO.getShop_main_img();%>
                        <img src="<%=main_img %>" class="card-img" alt="..."></a>
                    </div>
                    <div class="col-md-8 shop_zone">
                        <h1 class="shop_title"><%=ShopVO.getShop_name() %></h1>
                        <span class="ratins"><%=shopRating%><i class="fas fa-star"></i></span>
                        <span class="coms"><%=countByShop %>則評論|</span>
                        <span class="avg_prices">均消:<%=ShopVO.getShop_price_level() %>|</span>
                        <span class="tags"><%=ShopVO.getShop_tag() %></span><br>
                        <span class="open_time">營業時間:<%=ShopVO.getShop_opening_time() %></span><br>
                        <span class="address">地址:<%=ShopVO.getShop_address() %></span><br>
                        <span class="web	s"><a>粉絲專頁:<%=ShopVO.getShop_website() %></a></span><br>
                        <span class="phone">連絡電話:<%=ShopVO.getShop_phone() %></span><br>
                        <span class="descriptin">介紹	:<%=ShopVO.getShop_description() %></span><br>
                   		<form action="member.html"  method="post" >
                   		<input type=hidden name= "SHOP_ID" value="<%= ShopVO.getShop_id()%>">
                   		<input type=hidden name= "SHOP_MAIN_IMG" value="<%= ShopVO.getShop_main_img()%>">
                   		<input type=hidden name= "MEMBER_EMAIL" value="<%= MemberVO.getMember_email()%>">
					<input type=hidden name="action" value="change_shop">
                    <input type="submit" value="修改" class="save_btn" style="width:150px">
					</form>
                    </div>
					
                </div>
                <h1>餐廳照片</h1>
                <div class="row">
					<%
					String pre_gallery=ShopVO.getShop_gallery();
					
					if(pre_gallery.length()==0){%>
						<div class="shop_pic">
                        <img src="./public/img/noimage.jpg" class="shop_img" style="width:150px;height:150px;">
                    </div>	
					<%}else{
					String gallery=pre_gallery.substring(1,pre_gallery.length()-1);
					String[] shop_gallery;
					shop_gallery=gallery.split(", ");
					String shop_name=(ShopVO.getShop_name()).toString();
					
					
					for(int i =0;i<shop_gallery.length;i++){
						String gallery_img=galleryPath+shop_gallery[i];
					%>
					
                    <div class="shop_pic">
                        <img src="<%=gallery_img %>" class="shop_img" style="width:150px;height:150px;">
                    </div>
					<%}} %>

                </div>
         
                </div>

            </div>
        </div>

<%} %>
</body>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</html>