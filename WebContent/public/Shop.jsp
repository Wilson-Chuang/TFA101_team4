<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.shop_favorites.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@ page import="com.comment_report.model.*"%>
<%@ page import="com.member_follower.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ include file="/pages/header.file" %>

<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	
	MemberVO myMemberVO = (MemberVO) session.getAttribute("login");
	int shop_id= Integer.valueOf(request.getParameter("shop_id"));
	ShopService shopSvc=new ShopService();
	shopSvc.add_total_view(shop_id);
	ShopVO ShopVO= shopSvc.getOneShop(shop_id);
	CommentService comSvc = new CommentService();
	double shopRating=comSvc.countRatings(shop_id);
	if(Double.isNaN(shopRating)){
		shopRating =0.0;
	}
	int countByShop = comSvc.countByShop(ShopVO.getShop_id());
	List<CommentVO> list_Com =(List<CommentVO>)comSvc.getAllByShop(shop_id);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|商店頁面</title>
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
                <ul class="sidebar_ul">
                    <li class="sidebar"><form method="post" action="member.html" class="personal_form"><input type=hidden name="action" value="toPersonal">
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
                <div class="row">
                    <div class="col-md-4">
                        <img src="/upload/<%=ShopVO.getShop_main_img() %>" class="card-img" alt="...">
                    </div>
                    <div class="col-md-8 shop_zone">
                        <h1 class="shop_title"><%=ShopVO.getShop_name() %></h1>
                        <span class="ratins"><%=shopRating %><i class="fas fa-star"></i></span><span class="coms"><%=countByShop %>則評論</span>
                        <span class="avg_prices">均消$<%=ShopVO.getShop_price_level() %></span> <span class="tags"><%=ShopVO.getShop_tag() %></span><br>
                        <span class="open_time">營業時間:<%=ShopVO.getShop_opening_time() %></span><br>
                        <span class="address">地址:<%=ShopVO.getShop_address() %></span><br>
                        <span class="webs"><a>粉絲專頁:<%=ShopVO.getShop_website() %></a></span><br>
                        <span class="phone">連絡電話:<%=ShopVO.getShop_phone() %></span><br>
                   
                   
                   <%if(!(myMemberVO==null)){ 
                   		Shop_FavoritesService sfSvc=new Shop_FavoritesService();
                   		boolean track=sfSvc.check_track(myMemberVO.getMember_id(), shop_id);
                   	if(track){
                   %>
                   
                   <form action="member.html" method="post">
						<input type=hidden name= "MEMBER_ID" value="<%=myMemberVO.getMember_id()%>">
						<input type=hidden name= "SHOP_ID" value="<%=ShopVO.getShop_id()%>">
						<input type=hidden name="action" value="delete_sf">
						<input type="submit" value="取消追蹤" class="save_btn" style="width:150px"></input>
				  	</form>
                   <%}else{ %>
                   
                   <form action="member.html" method="post">
						<input type=hidden name= "MEMBER_ID" value="<%=myMemberVO.getMember_id()%>">
						<input type=hidden name= "SHOP_ID" value="<%=ShopVO.getShop_id()%>">
						<input type=hidden name="action" value="insert_sf">
						<input type="submit" value="追蹤商家" class="save_btn" style="width:150px"></input>
				  	</form>
                   
                   
                   <%}}%>
                   
                   
                    </div>
                </div>
                <h1>餐廳照片</h1>
                <div class="row">

                    <%
					String pre_gallery=ShopVO.getShop_gallery();
                    if(pre_gallery.length()==0){%>
                    <div class="shop_pic">
                        <img src="/upload/noimage.jpg" class="shop_img" style="width:150px;height:150px;">
                    </div>	
                    <%}else{
					String gallery=pre_gallery.substring(1,pre_gallery.length()-1);
					String[] shop_gallery;
					shop_gallery=gallery.split(", ");
					String shop_name=ShopVO.getShop_name();
					
					for(int i =0;i<shop_gallery.length;i++){
						String filepath=shop_name+"gallery/"+shop_gallery[i];
					%>
					
                    <div class="shop_pic">
                        <img src="/upload/<%=filepath %>" class="shop_img" style="width:150px;height:150px;">
                    </div>
					<%}} %>
                </div>
               
                
                <div class="row">
                <c:if test="${not empty errorMsgs}">
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
                <h1>餐廳評論</h1>
                 <%
                 if(!(myMemberVO==null)){
                 boolean comment_judge=false;
                 int i =0;
                 for (CommentVO com : list_Com){
                	if((com.getMEMBER_ID().equals(myMemberVO.getMember_id()))){
                		comment_judge=true;}
                	}
                	if(!comment_judge){
                	%>
                    <div class="comment_table">
                    	<form action="member.html"  method="post"  enctype="multipart/form-data">
                        <input name="comment_rating" type="range" min="0" max="5" value="0" step="1"  id="range"oninput="change()" onchange="change()">
						評價:<span id="value">0</span><br>
                        <textarea name="comment_text"class="comment_text" placeholder="想說什麼呢?"></textarea><br>
                        <label style="border-radius:5px;background-color:blue;color:white;">放一張照片吧<input name="comment_pic" style="display:none" type="file" ></input></label><br>
						<input type="hidden" name="member_id" value=<%=myMemberVO.getMember_id() %>>
						<input type="hidden" name="shop_id" value=<%=shop_id%>>
						<input type="hidden" name="action" value="add_comment">
						<input type="submit" value="送出" class="comment_btn" style="width:150px;"><br><hr>
						</form>
                    </div>
                    <%}}%>
                    
                    <%
 							for (CommentVO com : list_Com) {
 							MemberService memSvc = new MemberService();
 							MemberVO MemberVO=memSvc.GET_ONE_BY_ID(com.getMEMBER_ID());
 							if(com.getCOMMENT_STATUS()==0){
 						%>
                    
                    <div class="comment_zone">
                        <h3 class="user"><%=MemberVO.getMember_name() %></h3>
                        <p><%=com.getCOMMENT_TIME() %></p>
                        <span class="ratins"><%=com.getCOMMENT_RATING()%><i class="fas fa-star"></i></span><br>
                        <p><%=com.getCOMMENT_CONTENT() %></p>
                            <img src="/upload/<%=com.getCOMMENT_PIC()%>" alt="" style="width:25%"><br>
                             <%
                 			if(!(myMemberVO==null)){
                 			Comment_ReportService cmSvc=new Comment_ReportService();
                 			if(!cmSvc.reported(com.getCOMMENT_ID(),myMemberVO.getMember_id())){
                 				

                 			%>
                            <form action="member.html" method="post">
							<input type=hidden name= "MEMBER_ID" value="<%=myMemberVO.getMember_id()%>">
							<input type=hidden name= "COMMENT_ID" value="<%=com.getCOMMENT_ID()%>">
							<input type=hidden name= "shop_id" value="<%=shop_id%>">
							<input type=hidden name="action" value="comment_report">
							<input type=text name= "REASON" placeholder="檢舉原因">
							<input type="submit"  value="檢舉該評論" class="save_btn" style="width:150px"></input>
				  			</form>
				  			<%}
                 			%>
                 				
                 				
                 				
                 			<%
                 			} }else{%>
                 			<p>某評論已被遮蔽</p>
                 			<%} %>
                            <hr>
                    </div>
                    <%
 							}
						%> 
                </div>

            </div>
        </div>
    </div>


</body>
   <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/wrunner-jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
    <script type='text/javascript'>
			function change() {
  				var value = document.getElementById('range').value ;
  				document.getElementById('value').innerHTML = value;
			}
			
		</script>
</html>