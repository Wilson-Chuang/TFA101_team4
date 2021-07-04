<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.party_participants.model.*"%>
<%@ page import="com.party.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
    
    
<%
//     PartyParticipantsVO participants = (PartyParticipantsVO) request.getAttribute("PartyParticipantsVO");
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
  table {
	width: 950px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  
  .table_1 {
  	border: 0;
    padding: 5px;
    margin: 0 5px;
    text-align: center;
    height: 80px;
    vertical-align: middle;
  }
  .table_0{
   padding-top: 15px;
   vertical-align: middle;
   }
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

<table>
	<tr class="table_0">
		<th class="table_1">揪團編號</th>
		<th class="table_1">會員編號</th>
		<th class="table_1">餐廳編號</th>
		<th class="table_1">揪團標題</th>
		<th class="table_1">揪團開始時間</th>
		<th class="table_1">揪團結束時間</th>
		<th class="table_1">揪團簡介</th>
		<th class="table_1">成團人數最多限制</th>
		<th class="table_1">成團人數最少限制</th>
		<th class="table_1">備註</th>
	</tr>
	
	
<%-- 	<%@ include file="page5.file" %>  --%>
	<c:forEach var="partyVO1" items="${list}">
	
		<tr class="table_0">
			<td class="table_1">${partyVO1.party_id}</td>
			<td class="table_1">${partyVO1.member_id}</td>
			<td class="table_1">${partyVO1.shop_id}</td>
			<td class="table_1">${partyVO1.party_title}</td>
			<td class="table_1"><fmt:formatDate value="${partyVO1.party_start_time}"
						pattern="yyyy-MM-dd hh:mm" /></td> 
			<td class="table_1"><fmt:formatDate value="${partyVO1.party_end_time}"
						pattern="yyyy-MM-dd hh:mm" /></td>   
			<td class="table_1">${partyVO1.party_intro}</td>
			<td class="table_1">${partyVO1.party_participants_max}</td>
			<td class="table_1">${partyVO1.party_participants_min}</td>
			<td class="table_1">${partyVO1.party_remarks}</td>
			
			
			<td class="table_1">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/party/party.do" style="margin-bottom: 0px;">
			     <input type="submit" value="取消">
			     <input type="hidden" name="party_id"  value="${partyVO1.party_id}">
			     <input type="hidden" name="action" value="delete2"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page7.file" %> --%>



    <script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/header.js"></script>


</body>
</html>