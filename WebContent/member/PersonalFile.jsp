<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
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
	String email = ((MemberVO) session.getAttribute("login")).getMember_email();
	MemberService memSvc=new MemberService();
	MemberVO MemberVO = memSvc.getOneMem(email);
	
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
	
        <div class="row">
            <div class="col-2">
                <img src="/upload/<%=MemberVO.getMember_pic()%>" width="150px" alt="" class="member_pic" id="showimg">
            </div>
            <div class="col-10">
                <span class="member_name"><%=MemberVO.getMember_name()%></span>
                <span class="member_status">一般會員</span><br>
                <%CommentService comSvc= new CommentService(); 
                	int countByMember = comSvc.countByMember(MemberVO.getMember_id());
                	%>
                <span class="comments_count">發表<%=countByMember %>則評論|</span>
                <%Member_FollowerService folSvc=new Member_FollowerService(); 
                	int count_fans = folSvc.count_fans(MemberVO.getMember_id());%>
                <span class="followers_count"><%=count_fans%>個粉絲|</span>
                <span class="followers_count">會員ID:<%=MemberVO.getMember_id()%></span>
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
            <div class="col-10" >
                <form action="member.html" class="personal_form" method="post"  enctype="multipart/form-data">
                <table class="table table-striped">
                <tr><th>信箱:</th><td><%=MemberVO.getMember_email()%></td></tr>
                <tr><th>性別:</th><td><%if(MemberVO.getMember_gender()==1)%>男<%else%>女</td></tr>
                <tr><th>生日:</th><td><%=MemberVO.getMember_birth()%></td></tr>
                <tr><th>年齡:</th><td><%=MemberVO.getMember_age()%></td></tr>
                <tr><th>地址:</th><td><%=MemberVO.getMember_address()%></td></tr>
                <tr><th>電話:</th><td><%=MemberVO.getMember_phone()%></td></tr>
                <tr><th>註冊日期:</th><td><fmt:formatDate value="<%=MemberVO.getMember_create_time()%>" pattern="yyyy/MM/dd"/></td></tr>
                <tr><th>更新日期:</th><td><fmt:formatDate value="<%=MemberVO.getMember_update_time()%>" pattern="yyyy/MM/dd"/></td></tr>
                
                </table>
                    <input type=hidden name= "MEMBER_EMAIL" value="<%=MemberVO.getMember_email()%>">
                    <input type=hidden name= "MEMBER_NAME" value="<%=MemberVO.getMember_name()%>">
                    <input type=hidden name= "MEMBER_GENDER" value="<%=MemberVO.getMember_gender()%>">
                    <input type=hidden name= "MEMBER_BIRTH" value="<%=MemberVO.getMember_birth()%>">
                    <input type=hidden name= "MEMBER_AGE" value="<%=MemberVO.getMember_age()%>">
                    <input type=hidden name= "MEMBER_ADDRESS" value="<%=MemberVO.getMember_address()%>">
                    <input type=hidden name= "MEMBER_PHONE" value="<%=MemberVO.getMember_phone()%>">
                    <input type=hidden name= "MEMBER_PIC" value="<%=MemberVO.getMember_pic()%>">
                    <input type=hidden name="action" value="change">
                    <input type="submit" value="修改" class="save_btn" style="width:150px">
                </form>
                <form action="member.html">
                    <input type=hidden name="action" value="log_out">
                    <input type="submit" value="登出" class="save_btn" style="width:150px">
                </form>
 
            </div>
            <div id="chatRoom">
<%--             <jsp:include page="/chat.jsp" flush="true" /> --%>
        	</div>
    	</div>

</body>
</html>