<%@page import="com.party_participants.model.PartyParticipantsVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.MemberVO"%>
<%@ page import="com.party.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />


<%
  PartyVO partyVO = (PartyVO) request.getAttribute("partyVO"); 
  
  MemberVO member = (MemberVO) session.getAttribute("login");
  pageContext.setAttribute("member", member);
  
  PartyParticipantsVO participants = (PartyParticipantsVO) session.getAttribute("partyparticipantsVO");
  pageContext.setAttribute("participants", participants);
  
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>揪團</title>


<style>


div.party_img{
	 float:left;
	 width:500px;
	 padding:20px;
	 margin: 50px 20px;
	 border:2px #ccc solid;
	 border-radius: 10px;
	 box-shadow:3px 3px 5px 6px #cccccc;
}

img{
	width:460px; 
	height:360px;
}

div.party_all{
	width:100%; 
	height:550px;
}



div.party_t{
	width:500px;
    padding:20px;
	float:left;
	margin: 50px 20px;
	size: 2em;
	
}



h5{
 	color: #4166F8;
  	letter-spacing: 5px;
  	font-style: italic;
  	font-size: 1.5rem;
  	text-align:center;
  	margin: 0;
}

p{
  	font-size: 20px;
  	margin: 0;
}

hr {
    box-sizing: content-box;
    height: 0;
    overflow: visible;
}

div.party_intro{
	float: left;
	width:500px; 
	height:400px;
	margin: 50px;
	
}


div.paert_tag{
	float: right;
	width:500px; 
	height:400px;
	margin: 50px;
}


p.p1{
	color: #4166F8;
  	letter-spacing: 5px;
  	font-style: italic;
  	font-size: 1.5rem;
  	text-align:center;
  	margin: 0;
  	
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

 	input[type="submit"]{padding:15px 70px; background:#4166F8; border:0 none;
	cursor:pointer;
	-webkit-border-radius: 5px;
	border-radius: 50px; 
	color: white;
	font-size: 20px;
	margin: 50px 30%;
	
	
	}

</style>


<style>
 li {list-style-type:none;}
</style>



</head>
<body>

	<div class="party_all">
	
	    <div class="party_img">
	       <ul>
              <li><img src="./image/揪團封面.jpg"></li>
           </ul>
	   </div>
	
	
		
	<FORM METHOD="post" ACTION="party.do" name="form1">
		<div class="party_t">
			<h5>${partyVO.party_title}</h5>
			<p>
				<c:forEach var="memberVO" items="${memberSvc.all}">
	            	<c:if test="${partyVO.member_id==memberVO.member_id}">${memberVO.member_name}
	                </c:if>
               	</c:forEach> 
			</p>
			<hr>
			<p>結束時間: <fmt:formatDate value="${partyVO.party_end_time}"
						pattern="yyyy-MM-dd hh:mm" /></p>
			<p>最高人數: ${partyVO.party_participants_max}</p>
			<p>最低人數: ${partyVO.party_participants_min}</p>
			<p>餐廳名稱:
				<c:forEach var="shopVO" items="${shopSvc.all}">
 					<c:if test="${partyVO.shop_id==shopVO.shop_id}">
  						<a href="http://guidefood.myftp.org:8081/public/Shop.jsp?shop_id=${shopVO.shop_id}">                
       						${shopVO.shop_name}         
  						</a>
        			</c:if>
 				</c:forEach>
			</p>
			<p>備註: ${partyVO.party_remarks}</p>
			
	<% if(!(member==null)){
		%>
			<input type="hidden" name="action" value="join">
			<input type="hidden" name="member_id" value="${member.member_id}">
			<input type="hidden" name="party_id" value="${partyVO.party_id}">
			<input type="hidden" name="party_participants_id" value="${participants.party_participants_id}">
		    <input type="submit" value="加入揪團">
		<%} %>
		</div>
		</FORM>
	</div>
		
		
		<div class="party_intro">
			<p class="p1">揪團簡介</p>
			<hr>
			<p>${partyVO.party_intro}</p>
		</div>
	
		<div class="paert_tag">
			<p class="p1">餐廳評論</p>
			<hr>
			<c:forEach var="shopVO" items="${shopSvc.all}">
	           <c:if test="${partyVO.shop_id==shopVO.shop_id}">${shopVO.shop_tag}
	          </c:if>
         	</c:forEach> 
		</div>

	


</body>
</html>