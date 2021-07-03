<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop_favorites.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member_follower.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.article_favorite.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.search.model.*"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.io.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	MemberVO MemberVO = (MemberVO) session.getAttribute("login");


	

		MemberService memSvc=new MemberService();
		int Member_id=MemberVO.getMember_id();
			
			
			List<Shop_FavoritesVO> Shop_FavoritesVO= (List<Shop_FavoritesVO>) memSvc.getAllByMember(Member_id);
			List<ShopVO> list_myFavShop = new ArrayList<ShopVO>();
			for(Shop_FavoritesVO i:Shop_FavoritesVO) {
				int shop_id= i.getSHOP_ID();
				ShopService shopSvc = new ShopService();
				ShopVO ShopVO = shopSvc.getOneShop(shop_id);
				list_myFavShop.add(ShopVO);
			}
							
			Member_FollowerService memfolSvc=new Member_FollowerService();
			List<Integer> following = memfolSvc.GET_ALL_FOLLOWING(Member_id);
			List<MemberVO> list_following= new ArrayList<MemberVO>();
			 for(int i:following){
		            MemberVO Member_folVO = memSvc.GET_ONE_BY_ID(i);
		            list_following.add(Member_folVO);
		        }
			List<Integer> fan = memfolSvc.GET_ALL_FANS(Member_id);
			List<MemberVO> list_fans= new ArrayList<MemberVO>();
			for(int i:fan){
	            MemberVO Member_folVO = memSvc.GET_ONE_BY_ID(i);
	            list_fans.add(Member_folVO);
	           
	        }
	List<Article_FavoriteVO> list_article_fol=memSvc.getAllArticleFavByMem(Member_id);
// 	List<ArticleVO> list_myArticle=memSvc.getMyArticle(Member_id);
			String picpath=request.getContextPath()+ File.separator+"UPLOAD" + File.separator + "member"+ File.separator + "pic"+ File.separator;
			String uploadFilePath = picpath +MemberVO.getMember_pic();

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
				<img
					src="<%=(MemberVO.getMember_pic()).equals("noimage.jpg")?"./public/img/noimage.jpg":uploadFilePath%>"
					width="150px" alt="" class="member_pic" id="showimg">
			</div>
			<div class="col-10">
				<span class="member_name"><%=((MemberVO) (session.getAttribute("login"))).getMember_name()%></span>
                <span class="member_status">一般會員</span><br>
                 <%CommentService comSvc= new CommentService(); 
                	int countByMember = comSvc.countByMember(MemberVO.getMember_id());%>
                <span class="comments_count">發表<%=countByMember %>則評論|</span>
                <%Member_FollowerService folSvc=new Member_FollowerService(); 
                	int count_fans = folSvc.count_fans(MemberVO.getMember_id());%>
                	  <span class="followers_count"><%=count_fans%>個粉絲|</span>
				<span class="followers_count">會員ID:<%=((MemberVO) (session.getAttribute("login"))).getMember_id()%></span>
			</div>

		</div>

		<div class="row">

			<div class="col-2">
				<ul class="sidebar_ul">
					<li class="sidebar"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="toPersonal"> <input
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
						data-toggle="tab">追蹤會員</a></li>

				</ul>
				<!--標籤的內容-->
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="article_follower">
					<%
					if(!list_article_fol.isEmpty()){
							for(Article_FavoriteVO article_fav:list_article_fol){
								int article_no=article_fav.getArticle_no();
								ArticleVO ArticleVO=memSvc.getArticleFollowed(article_no);
						%>
						<div class="card mb-3" style="max-width: 540px;">
							<a href="<%=request.getContextPath()+"/article/article.do?article_no="+ArticleVO.getArticle_no()+"&action=see_article" %>"><div class="row no-gutters">
								<div class="col-md-4">
									<img src="/upload/<%=ArticleVO.getArticle_img_name()%>" class="card-img" alt="...">
								</div>
								<div class="col-md-8">
								<p class="card-text">您追蹤了「<%=ArticleVO.getArticle_title() %>」這篇文章</p>
								</div>
							</div></a>
						</div>
						<%
 							}
					}else{
						%> 
					<img src="./public/img/empty.jpg" style="width:100%">
					<%} %>
					</div>
					<div class="tab-pane fade" id="shop_favorite">
					
					
					<%if(!list_myFavShop.isEmpty()){
 							for (ShopVO shop : list_myFavShop) {
 						%> 
							
						<div class="card shop_card" style="width: 18rem;">
							<a href="<%=request.getContextPath()+"/public/Shop.jsp?shop_id="+shop.getShop_id() %>">
							<%String imgPath = request.getContextPath()+ File.separator+
									"uploads" + File.separator + "shop"+ File.separator +  shop.getShop_tax_id()+ 
									File.separator +"images"+ File.separator ;
												String main_img=imgPath+shop.getShop_main_img();
												%>
							<img class="card-img-top" src="<%=main_img%>" alt="商家封面圖"style="width:18rem;height:18rem;">
							</a>
							<div class="card-body">
							
								<h5 class="card-title"><%=shop.getShop_name() %></h5>
								<form action="member.html" method="post">
								<input type=hidden name= "MEMBER_ID" value="<%=MemberVO.getMember_id()%>">
								<input type=hidden name= "SHOP_ID" value="<%=shop.getShop_id()%>">
								<input type=hidden name="action" value="delete_sf">
								<label ><i class="fas fa-trash-alt"></i>移除<input style="display:none" type="submit" value="移除"></input></label>
								</form>
							</div>
						</div>
					<%
 							}
					}else{
						%> 
					<img src="./public/img/empty.jpg" style="width:100%">
					<%} %>
						
					</div>
					<div class="tab-pane fade" id="member_follower_fans">
					<%if(!list_fans.isEmpty()){
							for (MemberVO fans : list_fans) {
						%>
						<div class="card follow_card" style="width: 18rem;">
						<a href="<%=request.getContextPath()+"/public/Person.jsp?member_id="+fans.getMember_id() %>">
							<img class="card-img-top" src="<%=picpath+fans.getMember_pic()%>"
								alt="Card image cap" style="width:18rem;height:18rem;"></a>
							<div class="card-body">
								<h5 class="card-title"><%=fans.getMember_name()%></h5>
							</div>
						</div>
						<%
 							}
					}else{
						%> 
					<img src="./public/img/empty.jpg" style="width:100%">
					<%} %>
					</div>
						
					<div class="tab-pane fade" id="member_follower_follow">
						<%if(!list_following.isEmpty()){
							for (MemberVO fing : list_following) {
						%>
						<div class="card fans_card" style="width: 18rem;">
						<a href="<%=request.getContextPath()+"/public/Person.jsp?member_id="+fing.getMember_id() %>">
							<img class="card-img-top"
								src="<%=picpath+fing.getMember_pic()%>" alt="Card image cap"style="width:18rem;height:18rem;"></a>
							<div class="card-body">
								<h5 class="card-title"><%=fing.getMember_name()%></h5>
							</div>
							<ul class="list-group list-group-flush">
										
								<li class="list-group-item">
									<form action="member.html" method="post">
									<input type=hidden name= "MEMBER_ID" value="<%=MemberVO.getMember_id()%>">
									<input type=hidden name= "MEMBER_ID_FOL" value="<%= fing.getMember_id()%>">
									<input type=hidden name="action" value="delete_fol">
									<label ><i class="fas fa-trash-alt"></i>移除<input style="display:none" type="submit" value="移除"></input></label>
								</form>

							</ul>
						</div>

						<%
 							}
					}else{
						%> 
					<img src="./public/img/empty.jpg" style="width:100%">
					<%} %>


					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
</html>