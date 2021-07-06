<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.party.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%
    PartyService partySvc1 = new PartyService();
    List<PartyVO> list = partySvc1.getAll();
    pageContext.setAttribute("list",list);
    MemberVO myMemberVO = (MemberVO) session.getAttribute("login");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>後台所有揪團資料</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/bootstrap/css/bootstrap.min.css">


<style>

 ul {
    list-style: none;
  }
  
  main.add{
  	width: 80%;
    float: left;
  }

	 div.add button:focus {
     outline: none;
  }
   
  .add button{
  	 /* 圓角 */
     border-radius: 10px;
    /* 輸入文字色彩設定 */
     color: rgb(41, 41, 41);
     padding: 5px 10px;
  	 width:100px;
  	 height:50px;
  	 margin:20px 10px;
  	 border-radius:20%;
  	 background-color:#e9e9e9;
  }  
  .add button a{
	text-decoration: none; 
	font-size:15px;
	color: rgb(41, 41, 41);
  }
  
  .add ul{
  	float:right;
  	padding: 30px 70px 30px 0;
  }
  .add li{
  	float:left;
  	margin-right:20px;
  }
  
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

  div.main_content {
    width: 100%;
    float: left;
  }

  .breadcrumb{
     background-color: white;
     margin:20px 0 0 0;
     font-size:15px;
  }
</style>


<style>
 div.pa_fi{
  margin: 50px 250px;
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

</head>
<body bgcolor='white' >



<header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>

<div class="main_content">

<aside>

    <div>
	<jsp:include page="/cms/header_asideMenu/cmsAsideMenu.jsp" flush="true" />
    </div>
    
    <!--麵包屑，請大家對應側邊欄幫忙修改一下以下名稱，因為是bootstrap，所以需載入script-->
    <div>
	<nav aria-label="breadcrumb">
	    <ol class="breadcrumb">
		<li class="breadcrumb-item">用戶管理</li>
		<li class="breadcrumb-item active" aria-current="page">管理員管理</li>
	    </ol>
	</nav>
    </div>
    
</aside>


 
 <main class="add">

<h3>揪團查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllParty.jsp'>查詢全部揪團</a> <br><br></li>
  
  

   
  <li>
  <jsp:useBean id="partySvc" scope="page" class="com.party.model.PartyService" />
     <FORM METHOD="post" ACTION="party.do" >
       <b>揪團編號:</b>
       <select size="1" name="party_id">
         <c:forEach var="partyVO" items="${partySvc.all}"> 
          <option value="${partyVO.party_id}">${partyVO.party_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  
  <li>
  <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
  
	<FORM METHOD="post" ACTION="party.do" >
	     <b>選擇姓名查詢:</b>
			  <select size="1" name="member_id">
			     <c:forEach var="memberVO" items="${memberSvc.all}" > 
			        <option value="${memberVO.member_id}">${memberVO.member_name}
			      </c:forEach>   
			  </select>
			    <input type="hidden" name="action" value="getOne_For_member">
			    <input type="submit" value="送出" style="border-radius: 5px; border: 1px solid gray;">
	</FORM>
 </li>
  
</ul>
</main>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<article>

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
	
	
	<%@ include file="page1.file" %> 
	<c:forEach var="partyVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr class="table_0">
			<td class="table_1">${partyVO.party_id}</td>
			<td class="table_1">${partyVO.member_id}</td>
			<td class="table_1">${partyVO.shop_id}</td>
			<td class="table_1">${partyVO.party_title}</td>
			<td class="table_1"><fmt:formatDate value="${partyVO.party_start_time}"
						pattern="yyyy-MM-dd hh:mm" /></td> 
			<td class="table_1"><fmt:formatDate value="${partyVO.party_end_time}"
						pattern="yyyy-MM-dd hh:mm" /></td>   
			<td class="table_1">${partyVO.party_intro}</td>
			<td class="table_1">${partyVO.party_participants_max}</td>
			<td class="table_1">${partyVO.party_participants_min}</td>
			<td class="table_1">${partyVO.party_remarks}</td>
			<td class="table_1">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/party/party.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="party_id"  value="${partyVO.party_id}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<div class="pa_fi">
	<%@ include file="page2.file" %>
</div>
</article>


</div>


<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>