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
			String uploadFilePath = picpath +MemberVO.getMember_id()+ File.separator+MemberVO.getMember_pic();

%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude??????|????????????</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/bootstrap-icons.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wrunner-default-theme.css" rel="stylesheet" />
<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">
</head>
<body>
<%@ include file="/pages/header.file" %>
	<div class="container">
		<div class="row">
			<div class="col-2">
				<img
					src="<%=(MemberVO.getMember_pic())!=null?uploadFilePath:"./public/img/noimage.jpg"%>"
					width="150px" alt="" class="member_pic" id="showimg">
			</div>
			<div class="col-10">
				<span class="member_name"><%=((MemberVO) (session.getAttribute("login"))).getMember_name()%></span>
                <span class="member_status">????????????</span><br>
                 <%CommentService comSvc= new CommentService(); 
                	int countByMember = comSvc.countByMember(MemberVO.getMember_id());%>
                <span class="comments_count">??????<%=countByMember %>?????????|</span>
                <%Member_FollowerService folSvc=new Member_FollowerService(); 
                	int count_fans = folSvc.count_fans(MemberVO.getMember_id());%>
                	  <span class="followers_count"><%=count_fans%>?????????|</span>
				<span class="followers_count">??????ID:<%=((MemberVO) (session.getAttribute("login"))).getMember_id()%></span>
			</div>

		</div>

		<div class="row">

			<div class="col-2">
				<ul class="sidebar_ul">
					<li class="sidebar"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="toPersonal"> <input
								type="submit" value="????????????" class="save_btn"
								style="width: 150px; background: none; color: black">
						</form></li>
					<hr>
					<li class="sidebar"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="toWallet"> <input
								type="submit" value="????????????" class="save_btn"
								style="width: 150px; background: none; color: black">
						</form></li>
					<hr>
					<li class="sidebar"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="toFavorites"> <input
								type="submit" value="????????????" class="save_btn"
								style="width: 150px; background: none; color: black">
						</form></li>
					<hr>
					<li class="sidebar"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="toActive"> <input
								type="submit" value="????????????" class="save_btn"
								style="width: 150px; background: none; color: black">
						</form></li>
					<hr>
					<li class="sidebar  lock"><form action="member.html"
							class="personal_form">
							<input type=hidden name="action" value="toShop"> <input
								type="submit" value="????????????" class="save_btn"
								style="width: 150px; background: none; color: black">
						</form></li>
					<hr>
				</ul>
			</div>
			<div class="col-10">
				<ul class="nav nav-tabs">
					<li class="favorite_page_li"><a class="favorite_page"
						href="#shop_favorite" rel="external nofollow" data-toggle="tab">????????????</a></li>
					<li class="favorite_page_li"><a class="favorite_page"
						href="#member_follower_fans" rel="external nofollow"
						data-toggle="tab">????????????</a></li>
					<li class="favorite_page_li"><a class="favorite_page"
						href="#member_follower_follow" rel="external nofollow"
						data-toggle="tab">????????????</a></li>

				</ul>
				<!--???????????????-->
				<div id="myTabContent" class="tab-content">
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
							<img class="card-img-top" src="<%=main_img%>" alt="???????????????"style="width:18rem;height:18rem;">
							</a>
							<div class="card-body">
							
								<h5 class="card-title"><%=shop.getShop_name() %></h5>
								<form action="member.html" method="post">
								<input type=hidden name= "MEMBER_ID" value="<%=MemberVO.getMember_id()%>">
								<input type=hidden name= "SHOP_ID" value="<%=shop.getShop_id()%>">
								<input type=hidden name="action" value="delete_sf">
								<label ><i class="fas fa-trash-alt"></i>??????<input style="display:none" type="submit" value="??????"></input></label>
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
							<img class="card-img-top" src="<%=fans.getMember_pic()!=null?picpath+fans.getMember_id()+ File.separator+fans.getMember_pic():"./public/img/noimage.jpg"%>"
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
							<img class="card-img-top" src="<%=fing.getMember_pic()!=null?picpath+fing.getMember_id()+ File.separator+fing.getMember_pic():"./public/img/noimage.jpg"%>" alt="Card image cap"style="width:18rem;height:18rem;"></a>
								<div class="card-body">
								<h5 class="card-title"><%=fing.getMember_name()%></h5>
							</div>
							<ul class="list-group list-group-flush">
										
								<li class="list-group-item">
									<form action="member.html" method="post">
									<input type=hidden name= "MEMBER_ID" value="<%=MemberVO.getMember_id()%>">
									<input type=hidden name= "MEMBER_ID_FOL" value="<%= fing.getMember_id()%>">
									<input type=hidden name="action" value="delete_fol">
									<label ><i class="fas fa-trash-alt"></i>??????<input style="display:none" type="submit" value="??????"></input></label>
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
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/header.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
</html>