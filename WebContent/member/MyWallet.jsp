<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.member_follower.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@ page import="com.orders.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.search.model.*"%>
<%@ page import="java.io.*"%>

<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	MemberVO MemberVO = (MemberVO)session.getAttribute("login");
String picpath=request.getContextPath()+ File.separator+"UPLOAD" + File.separator + "member"+ File.separator + "pic"+ File.separator+MemberVO.getMember_id()+ File.separator;
String uploadFilePath = picpath +MemberVO.getMember_pic();
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|我的錢包</title>
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
                <img src="<%=(MemberVO.getMember_pic())==null?"./public/img/noimage.jpg":uploadFilePath%>" width="150px" alt="" class="member_pic" id="showimg">
            </div>
            <div class="col-10">
                <span class="member_name"><%=MemberVO.getMember_name() %></span>
                
                <span class="member_status">一般會員</span><br>
                <%CommentService comSvc= new CommentService(); 
                	int countByMember = comSvc.countByMember(MemberVO.getMember_id());%>
                <span class="comments_count">發表<%=countByMember %>則評論|</span>
                <%Member_FollowerService folSvc=new Member_FollowerService(); 
                	int count_fans = folSvc.count_fans(MemberVO.getMember_id());%>
                	  <span class="followers_count"><%=count_fans%>個粉絲|</span>
                <span class="followers_count">會員ID:<%=MemberVO.getMember_id() %></span>
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
        <div class="row">
            <span class="point">目前積分餘額:<br><%=MemberVO.getMember_point() %></span><br>
        </div>
        <h3 class="wallet" style="text-align:center;">消費紀錄</h3>  
        <%MemberService memSvc=new MemberService();
		List<OrdersVO> oders=memSvc.getOdersByMember(MemberVO.getMember_id());
        if(!oders.isEmpty()){%>
        <ul class="shopping_record" >
							<%for(OrdersVO order:oders){						
						%>
            <li><%=order.getOrders_date() %>使用了<%=order.getOrders_total_point() %>積分!</li>
        <%}%>
        </ul>
							<%}else{%>
        <img src="./public/img/empty.jpg" style="width:100%">
        	<% }%>
    </div>
</div>
</div>

</body>
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/header.js"></script>
</html>