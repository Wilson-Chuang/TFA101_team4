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
	MemberVO MemberVO = (MemberVO)session.getAttribute("login");
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
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">

</head>
<body>
<div class="container">
        <div class="row">
            <div class="col-2">
                <img src="/upload/<%=MemberVO.getMember_pic() %>" width="150px" alt="" class="member_pic" id="showimg">
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
        <ul class="shopping_record" >
            <li><span class="date">2021/05/13</span><span class="time">12:25</span><span class="item">使用1000積分購買「LADY M草莓千層蛋糕」</span></li>
            <li><span class="date">2021/05/11</span><span class="time">11:05</span><span class="item">使用5000積分購買「星巴克不鏽鋼保溫瓶」</span></li>
        </ul>
    </div>
</div>
</div>

</body>
<script type="text/javascript">
		function show(f) {
			var reader = new FileReader();//建立檔案讀取物件
			var files = f.files[0];//獲取file元件中的檔案
			reader.readAsDataURL(files);//檔案讀取裝換為base64型別
			reader.onloadend = function(e) {
				//載入完畢之後獲取結果賦值給img
				document.getElementById("showimg").src = this.result;
			}
		}
	</script>
</html>