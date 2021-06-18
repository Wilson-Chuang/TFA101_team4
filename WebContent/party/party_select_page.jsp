<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>揪團查詢</title>


<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white' >



<table id="table-1">
   <tr><td><h3>PARTY</h3><h4>( MVC )</h4></td></tr>
</table>


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
         <c:forEach var="partyVO" items="${partySvc.all}" > 
          <option value="${partyVO.party_id}">${partyVO.party_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>發起揪團</h3>

<ul>
  <li><a href='addparty.jsp'>發起揪團</a></li>
</ul>

</body>
</html>