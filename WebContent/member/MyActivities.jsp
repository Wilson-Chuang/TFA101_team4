<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member_follower.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.forum_post.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.search.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	MemberVO MemberVO = (MemberVO) session.getAttribute("login");
	MemberService memSvc=new MemberService();
	int Member_id=MemberVO.getMember_id();
	List<ArticleVO> list_myArticle=memSvc.getMyArticle(Member_id);
	List<ForumPostVO> list_myForum=memSvc.getMyForum(Member_id);
	String picpath=request.getContextPath()+ File.separator+"UPLOAD" + File.separator + "member"+ File.separator + "pic"+ File.separator;
	String uploadFilePath = picpath +MemberVO.getMember_pic();
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|活動紀錄</title>
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
                <%CommentService comSvc= new CommentService(); 
                	int countByMember = comSvc.countByMember(MemberVO.getMember_id());%>
                <span class="comments_count">發表<%=countByMember %>則評論|</span>
                <%Member_FollowerService folSvc=new Member_FollowerService(); 
                	int count_fans = folSvc.count_fans(MemberVO.getMember_id());%>
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
				<ul class="nav nav-tabs">
					<li class="favorite_page_li"><a class="favorite_page"
						href="#myArticle" rel="external nofollow" data-toggle="tab">我的文章</a></li>
					<li class="favorite_page_li"><a class="favorite_page"
						href="#myforum" rel="external nofollow" data-toggle="tab">我的討論</a></li>
				</ul>
				<!--標籤的內容-->
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="myArticle">
					<%if(!list_myArticle.isEmpty()){
							for(ArticleVO article:list_myArticle){						
						%>
						<div class="card mb-3" >
							<a href="<%=request.getContextPath()+"/article/article.do?article_no="+article.getArticle_no()+"&action=see_article" %>"><div class="row no-gutters">
								<div class="col-md-2">
								</div>
								<div class="col-md-4">
									<img src="/upload/<%=article.getArticle_img_name() %>" class="card-img" alt="...">
								</div>
								<div class="col-md-4">
									<p class="card-text">您成功發表了「<%=article.getArticle_title() %>」這篇文章!</p>
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
					<div class="tab-pane fade" id="myforum">
					
					
					<%
					
					if(!list_myForum.isEmpty()){%>
						
							<ul class="list-group list-group-flush shopping_record">
  						<%for (ForumPostVO post : list_myForum) {%>  
								<li class="list-group-item"><a href=#>您成功發表了「<%=post.getForum_post_title() %>」這則討論!</a></li>
					<%}%>
							</ul>
 					<%}else{%> 
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