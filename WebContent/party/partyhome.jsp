<%@page import="com.member.model.MemberVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.party.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />

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

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.7.1/swiper-bundle.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/6.7.1/swiper-bundle.min.js"></script>


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
	 float:left;
	 width:500px;
	 padding:20px;
	 margin: 50px 20px;
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
    width: 280px !important;
    height: 60% !important;
	display:inline-block;
	margin: 300px 0;
	border:5px #ccc solid;
	border-radius: 10px;
	box-shadow:5px 3px 5px 6px #cccccc;
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

h5{
 	color: #4166F8;
  	letter-spacing: 5px;
  	font-style: italic;
  	font-size: 1.5rem;
  	text-align:center;
  	margin: 0;
}

div.myparty{
	width:500px;
    padding:70px 130px;
	margin: 50px 20px;
	size: 2em;
	
}



</style>


<style>
/* 去掉a標籤底線 */
a{ text-decoration:none} 
a:hover{ text-decoration:none}

/* 設定還沒有瀏覽過的連結 */
a:link{
color:blue;
}

/* 設定已經瀏覽過的連結 */
a:visited {
color:blue;
}

/* 設定滑鼠移經的連結 */
a:hover {
color:red;
}

/* 設定正在點選的連結 */
a:active {
color:#fafafa;
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

<style>
 li {list-style-type:none;}
</style>

<style>
div.membername{
		display: inline-block;
    	padding: 0 25px;
    	height: 50px;
    	font-size: 16px;
    	line-height: 50px;
    	border-radius: 25px;
    	background-color: #f3f5ee;
    	color: #4166F8;
	}	
</style>

<style>
.swiper-container {
    width: 500px;
    height:300px;
}  

</style>

<style>

.swiper-button-prev{
	color: #5E5F61;

}

.swiper-button-next{
	color: #5E5F61;
}

</style>


<style type="text/css">
a:link{
//設定還沒有瀏覽過的連結
text-decoration:none;
background-color:#ffffff;
}
a:visited {
//設定已經瀏覽過的連結
}
a:hover {
//設定滑鼠移經的連結
text-decoration:underline;
background-color:#fafafa;
color:gray;
}
a:active {
//設定正在點選的連結
text-decoration:none;
background-color:gray;
color:#fafafa;
}
</style>

</head>


<body>

	<div class="Party_top" style="cursor: pointer;" onclick="window.location='${pageContext.request.contextPath}/member/PersonalFile.jsp';"></div>
	
	<%
	if(!(myMemberVO==null)){
	%>
	
	<div class="Party_middle">
	
	<%
	if(!(myMemberVO==null)){
	%>
      <ul>
         <li><a href='${pageContext.request.contextPath}/party/addparty.jsp'><img src="./image/發起揪團.png"  width="400px" height="200px"></a></li>
      </ul>
	<%} %>
	
    </div>
    
   
    
    <!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <div class="swiper-slide">
            	<div class="myparty">
    <%
	if(!(myMemberVO==null)){
	%>
   		 <a href='${pageContext.request.contextPath}/party/myparty.jsp'>我的參加的揪團</a>
    <%} %>
    </div>
            </div>
            <div class="swiper-slide">
            	 <div class="myparty">
    <%
	if(!(myMemberVO==null)){
	%>
   		 <a href='${pageContext.request.contextPath}/party/listMYParty.jsp'>我發起的揪團</a>
    <%} %>
    </div>
            </div>
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
        <div class="swiper-button-prev"></div>
   		<div class="swiper-button-next"></div>
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
		<div class="membername">
			<c:forEach var="memberVO" items="${memberSvc.all}">
	                <c:if test="${partyVO.member_id==memberVO.member_id}">
		                   ${memberVO.member_name}
	                </c:if>
            </c:forEach> 
		<h5>${partyVO.party_title}</h5>
		</div>
		<div>結束時間: <fmt:formatDate value="${partyVO.party_end_time}"
						pattern="yyyy-MM-dd hh:mm" /></div>
		<div>最高人數: ${partyVO.party_participants_max}</div>
		<div>最低人數: ${partyVO.party_participants_min}</div>
		
		<div>
		<c:forEach var="shopVO" items="${shopSvc.all}">
	                    		<c:if test="${partyVO.shop_id==shopVO.shop_id}">
		                   			${shopVO.shop_address}
	                    		</c:if>
                			 </c:forEach> 
		</div>
		
	      
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


<!-- Initialize Swiper -->
    <script>
    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
          },
        
    });
    
    </script>


</body>



</html>