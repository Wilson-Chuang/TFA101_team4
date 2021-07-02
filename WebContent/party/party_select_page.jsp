<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>揪團查詢</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/bootstrap/css/bootstrap.min.css">


</head>
<body bgcolor='white' >


<header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>

<aside>

<div class="main_content">

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

    <!-- 原先的code --> 

</div>

</aside>

<main>

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
  
  

  <jsp:useBean id="partySvc" scope="page" class="com.party.model.PartyService" />
   
  <li>
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
  
</ul>
</main>


<section>


</section>




<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>