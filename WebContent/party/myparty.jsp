<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.party_participants.model.*"%>
<%@ page import="com.party.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
    
    
<%
	PartyParticipantsService partySvc = new PartyParticipantsService();
	MemberVO MemberVO2 = (MemberVO) session.getAttribute("login");
	int Member = MemberVO2.getMember_id();
    List<PartyParticipantsVO> pslist = partySvc.getMemberAll(Member);
    List<PartyVO> list = new ArrayList();
    PartyService partySvc2 = new PartyService();
    for(PartyParticipantsVO participant:pslist){
    	PartyVO partyVO = partySvc2.getOneParty(participant.getParty_id());
    	list.add(partyVO);
    }
    pageContext.setAttribute("list",list);
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我參加的揪團</title>


<style>

div.Party_btm {
	height:800px;
	background-size: cover;
	background-repeat: no-repeat;
	position: relative;
	display: flex;
	justify-content: space-around;
	overflow: hidden; 
	margin: 0;
	
}


 div.party_data{
    width: 280px !important;
    height: 70% !important;
	display:inline-block;
	border:5px #ccc solid;
	border-radius: 10px;
	box-shadow:5px 3px 5px 6px #cccccc;
	
	
}

  div.t1{
	font-family:Bold;
	margin: 10px 25px;
}
</style>

<style>
a{ text-decoration:none} 
a:hover{ text-decoration:none}
</style>


<style>

 	input[type="submit"]{padding:5px 25px; background:#4166F8; border:0 none;
	cursor:pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px; 
	color: white;
	}

</style>

	<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/bootstrap-icons.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/materialdesignicons.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/wrunner-default-theme.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet">



</head>

<body bgcolor='white' >

<%@include file="/pages/header.file"%>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="Party_btm">
	
<%-- 	<%@ include file="page5.file" %>  --%>
	<c:forEach var="partyVO" items="${list}">
	
		<div class="party_data">
		<div class="t1">揪團編號 ${partyVO.party_id}</div>
		<div class="t1">會員: 
			<c:forEach var="memberVO" items="${memberSvc.all}">
	               <c:if test="${partyVO.member_id==memberVO.member_id}">
		                   	${memberVO.member_name}
	               </c:if>
            </c:forEach> </div>
		<div class="t1">餐廳:
			<c:forEach var="shopVO" items="${shopSvc.all}">
 					<c:if test="${partyVO.shop_id==shopVO.shop_id}">
  						<a href="http://guidefood.myftp.org:8081/public/Shop.jsp?shop_id=${shopVO.shop_id}">                
       						${shopVO.shop_name}         
  						</a>
        			</c:if>
 				</c:forEach> </div>
		<div class="t1">標題: ${partyVO.party_title}</div>
		<div class="t1">開始時間: <fmt:formatDate value="${partyVO.party_start_time}"
						pattern="yyyy-MM-dd hh:mm" /></div>
		<div class="t1">結束時間: <fmt:formatDate value="${partyVO.party_end_time}"
						pattern="yyyy-MM-dd hh:mm" /></div>
		<div class="t1">簡介: ${partyVO.party_intro}</div>
		<div class="t1">最多人數: ${partyVO.party_participants_max}</div>
		<div class="t1">最低人數: ${partyVO.party_participants_min}</div>
		<div class="t1">備註: ${partyVO.party_remarks}</div>
	
		<div class="t1">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/party/party.do" style="margin-bottom: 0px;">
			 	<input type="submit" value="取消">
			     <input type="hidden" name="party_id"  value="${partyVO.party_id}">
			     <input type="hidden" name="action" value="delete2">
			</FORM>
		</div>
	</div>
		</c:forEach>
		
	</div>	
		
    <script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/header.js"></script>


</body>
</html>