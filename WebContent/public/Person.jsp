<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member_follower.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.forum_post.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ include file="/pages/header.file" %> --%>
<%@ page import="java.util.*"%>
<%@ page import="com.search.model.*"%>

<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
		int member_id= Integer.valueOf(request.getParameter("member_id"));
		MemberService memSvc=new MemberService();
		MemberVO MemberVO= memSvc.GET_ONE_BY_ID(member_id);
		MemberVO myMemberVO = (MemberVO)(session.getAttribute("login"));
		Member_FollowerService memfolSvc=new Member_FollowerService();
		List<ArticleVO> list_myArticle=memSvc.getMyArticle(member_id);
		List<ForumPostVO> list_myForum=memSvc.getMyForum(member_id);
		

		
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|個人頁面</title>
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
            <div class="col-2">
                <img src="/upload/<%= MemberVO.getMember_pic()%>" width="150px" alt="" class="member_pic" id="showimg">
            </div>
            <div class="col-10">
                <span class="member_name"><%= MemberVO.getMember_name()%></span>
                <%if(MemberVO.getMember_status()==1){ %>                
                <span class="member_status">一般會員</span><br>
                <%}else{ %>
                <span class="member_status">商家管理會員</span><br>
                <%} %>
                <%CommentService comSvc= new CommentService(); 
                	int countByMember = comSvc.countByMember(MemberVO.getMember_id());%>
                <span class="comments_count">發表<%=countByMember %>則評論|</span>
                <%Member_FollowerService folSvc=new Member_FollowerService(); 
                	int count_fans = folSvc.count_fans(MemberVO.getMember_id());%>
                	  <span class="followers_count"><%=count_fans%>個粉絲|</span>
                <span class="followers_count">會員ID:<%= MemberVO.getMember_id()%></span><br>
                <%
                if(!(myMemberVO==null)){%>
                	<form  action="<%=request.getContextPath() %>/chat.do" method="POST" target="_blank">
                	<%-- <input type=hidden name="userName" value=<%=myMemberVO.getMember_name() %>  >  --%>
                		 <input type=hidden name="recieverName" value=<%=MemberVO.getMember_id()+":"+MemberVO.getMember_name() %>> 
                		 <input type=hidden name="action" value="chat">
                		 <input type="submit" value="聊天" class="save_btn" style="width: 150px;">
                	</form>
               <% boolean followed =memfolSvc.check_follow(member_id, myMemberVO.getMember_id());
                if(followed){%>
                	<form action="member.html" method="post">
									<input type=hidden name= "MEMBER_ID_FOL" value="<%=MemberVO.getMember_id()%>">
									<input type=hidden name= "MEMBER_ID" value="<%= myMemberVO.getMember_id()%>">
									<input type=hidden name="action" value="delete_fol">
									<input type="submit" value="取消追蹤" class="save_btn" style="width:150px">
					</form>
                <%	
                }else{
                %>
                
 				 <form action="member.html"method="post">
 				 	<input type=hidden name="member_id" value="<%= MemberVO.getMember_id()%>">
 				 	<input type=hidden name="myMember_id" value="<%= myMemberVO.getMember_id()%>">

 				    <input type=hidden name="action" value="follow">
                    <input type="submit" value="追蹤" class="save_btn" style="width:150px">
 				 </form>
 				 <%}} %>
            </div>

        </div>
				<ul class="nav nav-tabs">
					<li class="favorite_page_li"><a class="favorite_page"
						href="#myArticle" rel="external nofollow" data-toggle="tab"><%=MemberVO.getMember_name() %>的文章</a></li>
					<li class="favorite_page_li"><a class="favorite_page"
						href="#myforum" rel="external nofollow" data-toggle="tab"><%=MemberVO.getMember_name() %>的討論</a></li>
				</ul>
<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="myArticle">
					<%if(!list_myArticle.isEmpty()){
							for(ArticleVO article:list_myArticle){						
						%>
						<div class="card mb-3" style="max-width: 540px;">
							<a href=#><div class="row no-gutters">
								<div class="col-md-4">
									<img src="/upload/<%=article.getArticle_img_name() %>" class="card-img" alt="...">
								</div>
								<div class="col-md-8">
									<p class="card-text"><%=MemberVO.getMember_name() %>發表了「<%=article.getArticle_title() %>」這篇文章!</p>
								</div>
							</div></a>
						</div>
						<%
 							}
					}else{
						%> 
					<img src="/upload/empty.jpg" style="width:100%">
					<%} %>
					</div>
					<div class="tab-pane fade" id="myforum">
					
					
					<%if(!list_myForum.isEmpty()){%>
						
							<ul class="list-group list-group-flush">
  						<%for (ForumPostVO post : list_myForum) {%>  
								<li class="list-group-item"><a href=#><%=MemberVO.getMember_name() %>發表了「<%=post.getForum_post_title() %>」這則討論!</a></li>
					<%}%>
							</ul>
 					<%}else{%> 
							<img src="/upload/empty.jpg" style="width:100%">
					<%} %>
						
					</div>

            </div>
        </div>
    </div>

</body>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/wrunner-jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
</html>