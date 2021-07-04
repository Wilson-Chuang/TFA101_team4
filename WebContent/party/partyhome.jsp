<%@page import="com.member.model.MemberVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.party.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<%
    PartyService partySvc = new PartyService();
    List<PartyVO> list = partySvc.getAll();
    pageContext.setAttribute("list",list);
    MemberVO myMemberVO = (MemberVO) session.getAttribute("login");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>揪團首頁</title>


<style>
* {
	margin: 0;
	padding:0;
	background-color: white;
}

div.Party_top {
	background-image: url(./image/揪團封面.jpg);
	width: 100%;
	height: 700px;
	background-size: cover;
	background-repeat: no-repeat;
	margin: 0;
}

div.Party_middle {
	width: 400px;
	height: 200px;
	
}



div.Party_btm {
	background-image: url(./image/揪團背景2.jpg);
	width: 100%;
	height: 800px;
	background-size: cover;
	background-repeat: no-repeat;
	background-color: #f1f1f1;
	position: relative;
	display: flex;
	justify-content: space-around;
	overflow: hidden; 
	margin: 0;
	
}

div.party_data{
    width: 250px !important;
    height: 50% !important;
	display:inline-block;
	margin: 300px 0;
	border:2px #ccc solid;
	border-radius: 10px;
	box-shadow:3px 3px 5px 6px #cccccc;
}




div{
	font-family:Bold;
	margin: 10px 25px;
}


div.party_title{
	font-family:Bold;
	color: #4166F8;
	font-size: 20px;
	font-style: italic;
	letter-spacing: 0.3em;
	text-align:center;
}


div.button{

 margin: 50px 94px;
}



</style>


<style>

 	input[type="submit"]{padding:5px 20px; background:#4166F8; border:0 none;
	cursor:pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px; 
	color: white;
	}

</style>

<style>
 li {list-style-type:none;}
</style>

</head>


<body>
	<div class="Party_top"></div>
	
	<div class="Party_middle">
	
	<%
	if(!(myMemberVO==null)){
	%>
      <ul>
         <li><a href='${pageContext.request.contextPath}/party/addparty.jsp'><img src="./image/發起揪團.png"  width="400px" height="200px"></a></li>
      </ul>
	<%} %>
	
    </div>
    
    <div>
    <%
	if(!(myMemberVO==null)){
	%>
   		 <a href='${pageContext.request.contextPath}/party/myparty.jsp'> ssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss</a>
    <%} %>
    </div>

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

<%@ include file="page3.file" %> 
	<c:forEach var="partyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">


	<div class="party_data">
	
		<div>
		<c:forEach var="memberVO" items="${memberSvc.all}">
	                    		<c:if test="${partyVO.member_id==memberVO.member_id}">
		                   			${memberVO.member_pic}
	                    		</c:if>
                			 </c:forEach> 
		</div>
		<div>
		<c:forEach var="memberVO" items="${memberSvc.all}">
	                    		<c:if test="${partyVO.member_id==memberVO.member_id}">
		                   			${memberVO.member_name}
	                    		</c:if>
                			 </c:forEach> 
		</div>
		<div>結束時間: <fmt:formatDate value="${partyVO.party_end_time}"
						pattern="yyyy-MM-dd hh:mm" /></div>
		<div>最高人數: ${partyVO.party_participants_max}</div>
		<div>最低人數: ${partyVO.party_participants_min}</div>
	      
	<div class="button">
	
	
	
	<FORM METHOD="post" ACTION="party.do" >
       
       <input type="hidden" name="action" value="getOne_For_party">
       <input type="hidden" name="party_id" value="${partyVO.party_id}">
       <input type="submit" value="查看">
    </FORM>
	</div>
	
	</div>
	</c:forEach>
	</div>
<%@ include file="page4.file" %>


</body>



</html>