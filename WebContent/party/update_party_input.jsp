<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.party.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	PartyVO partyVO = (PartyVO) request.getAttribute("partyVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>揪團資料修改</title>



<script src="<%=request.getContextPath()%>/party/ckeditor/ckeditor.js"></script>

</head>
<body bgcolor='white'>


	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="party.do" name="form1">
		<table>
			<tr>
				<td>揪團編號:<font color=red><b>*</b></font></td><td><%=partyVO.getParty_id()%></td>
			</tr>
			
			<tr>
				<td>會員名稱:</td>
				<td><input type="TEXT" disabled="disabled" min="1" max="15" name="member_id" size="45"value="<%=partyVO.getMember_id()%>" /></td>
			</tr>
			
			<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
			
			<tr>
				<td>餐廳地址:<input list="shop_no" name="shop_id"/>  
  				<datalist id="shop_no">
  					<c:forEach var="shopVO" items="${shopSvc.all}">
      					<option value="${shopVO.shop_id}"
   							${(partyVO.shop_id==shopVO.shop_id)? 'selected':'' }>${shopVO.shop_name}
  					</c:forEach>    
  				</datalist>
					</td>
			</tr>
			
			
			
			
			<tr>
				<td>揪團標題:</td>
				<td><input type="TEXT" name="party_title" size="45"value="<%=partyVO.getParty_title()%>" /></td>
			</tr>
			

			<tr>
				<td>開始日期時間:</td>
				<td><input name="party_start_time" id="party_start_time"type="text"></td>
			</tr>
			
			<tr>
				<td>結束日期時間:</td>
				<td><input name="party_end_time" id="party_end_time"type="text"></td>
			</tr>
			
			
			<tr>
				<td>成團人數最多限制:</td>
				<td><input type="number" min="1" max="15" name="party_participants_max" size="45"value="<%=partyVO.getParty_participants_max()%>" /></td>
			</tr>
			<tr>
				<td>成團人數最少限制:</td>
				<td><input type="number" min="1" max="15" name="party_participants_min" size="45"value="<%=partyVO.getParty_participants_min()%>" /></td>
			</tr>
			
			<tr>
				<td>備註:</td>
				<td>		<select name="party_remarks" style="width: 160">
		                    <option value="<%=(partyVO == null) ? "無菸環境，不喝酒" : partyVO.getParty_remarks()%>" <c:if test="${partyVO.party_remarks}">selected</c:if>>無菸環境，不喝酒</option>
		                    <option value="<%=(partyVO == null) ? "無菸環境，喝酒" : partyVO.getParty_remarks()%>" <c:if test="${partyVO.party_remarks}">selected</c:if>>無菸環境，喝酒</option>
		                    <option value="<%=(partyVO == null) ? "抽菸環境，不喝酒" : partyVO.getParty_remarks()%>" <c:if test="${partyVO.party_remarks}">selected</c:if>>抽菸環境，不喝酒</option>
		                    <option value="<%=(partyVO == null) ? "抽菸環境，喝酒" : partyVO.getParty_remarks()%>" <c:if test="${partyVO.party_remarks}">selected</c:if>>抽菸環境，喝酒</option>
	                        </select>
				
					      </td>
			</tr>
			
			

			<tr>
				<td>揪團介紹:</td>
				<td><textarea class="ckeditor" id="mContent" name="party_intro"></textarea></td>
			</tr>
		</table>
		<br> 
			 <input type="hidden" name="action" value="update"> 
			 <input type="hidden" name="member_id" value="<%=partyVO.getMember_id()%>">
		     <input type="hidden" name="party_id" value="<%=partyVO.getParty_id()%>">
		     <input type="submit" value="送出修改">
		</FORM>

</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
<link rel="stylesheet" type="text/css"
	href="datetimepicker/jquery.datetimepicker.css" />
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>





$.datetimepicker.setLocale('zh');
$('#party_start_time').datetimepicker({
   theme: 'white',              //theme: 'dark',
   timepicker:true,        //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
   value: '<%=partyVO.getParty_start_time()%>', //value:   new Date(),
    //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});

$.datetimepicker.setLocale('zh');
$('#party_end_time').datetimepicker({
   theme: 'white',              //theme: 'dark',
   timepicker:true,        //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d H:i',         //format:'Y-m-d H:i:s',
   value: '<%=partyVO.getParty_end_time()%>', //value:   new Date(),
    //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});


</script>
</html>