<%@page import="com.member.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.party.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*" %>

<%
	PartyVO party = (PartyVO) request.getAttribute("PartyVO");
    pageContext.setAttribute("party", party);
	
    MemberVO member = (MemberVO) session.getAttribute("login");
    pageContext.setAttribute("member", member);
%>

<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>揪團發起</title>




<style>
 	input[type="submit"]{padding:10px 25px; background:#4166F8; border:0 none;
	cursor:pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px; 
	color: white;
	float:right;
	margin: 20px 20px;
	}
</style>



    <script src="<%=request.getContextPath()%>/party/ckeditor/ckeditor.js"></script>


    <link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/bootstrap-icons.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/materialdesignicons.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/wrunner-default-theme.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet">

</head>
<body>

<%@include file="/pages/header.file"%>
	

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
	
	<div>
		會員名稱:
		<input type="text" disabled="disabled" name="member_name" size="20" value="${member.member_name}"/>
		<input type="hidden" name="member_id" size="45" value="${member.member_id}"/>
	</div>
	
	
	<div>
		餐廳名稱:<input list="shop_no" name="shop_id"/>  
  				<datalist id="shop_no">
  					<c:forEach var="shopVO" items="${shopSvc.all}">
      					<option value="${shopVO.shop_id}"
   							${(partyVO.shop_id==shopVO.shop_id)? 'selected':'' }>${shopVO.shop_name}
  					</c:forEach>    
  				</datalist>
 		</div>
	
	
	
	<div>
		揪團標題:
		<input type="TEXT" name="party_title" size="45"
				value="<%=(party == null) ? "" : party.getParty_title()%>" />
	</div>
	
	<div>
		開始日期時間:
		<input name="party_start_time" id="party_start_time" type="text" >
	</div>
	
	<div>
		結束日期時間:
		<input name="party_end_time" id="party_end_time" type="text">
	</div>
	
	<div>
		最高人數:
		<input type="number" min="1" max="15" name="party_participants_max" size="45"
				value="<%= (party == null) ? "1": party.getParty_participants_max()%>" />
	</div>
	
	<div>
		最低人數:
		<input type="number" min="1" max="15" name="party_participants_min" size="45"
				value="<%=(party == null) ? "1" : party.getParty_participants_min()%>" />
	</div>
	
	<div>
		備註:
		<select name="party_remarks" style="width: 160">
			<option value="<%=(party == null) ? "無菸環境，不喝酒" : party.getParty_remarks()%>" <c:if test="${partyVO.party_remarks}">selected</c:if>>無菸環境，不喝酒</option>
		    <option value="<%=(party == null) ? "無菸環境，喝酒" : party.getParty_remarks()%>" <c:if test="${partyVO.party_remarks}">selected</c:if>>無菸環境，喝酒</option>
		    <option value="<%=(party == null) ? "抽菸環境，不喝酒" : party.getParty_remarks()%>" <c:if test="${partyVO.party_remarks}">selected</c:if>>抽菸環境，不喝酒</option>
		    <option value="<%=(party == null) ? "抽菸環境，喝酒" : party.getParty_remarks()%>" <c:if test="${partyVO.party_remarks}">selected</c:if>>抽菸環境，喝酒</option>
	    </select>
	</div>
	
	<div>
		揪團介紹:
		<textarea class="ckeditor" id="mContent" name="party_intro" ></textarea>
	</div>
	
	
		<br> 
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="建立">
	</FORM>
	
	
	
    <script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/header.js"></script>


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
<% 
java.sql.Timestamp party_start_time = null;
try {
	party_start_time = party.getParty_start_time();
 } catch (Exception e) {
	 party_start_time = new java.sql.Timestamp(System.currentTimeMillis());
 }
%>

$.datetimepicker.setLocale('zh');
$('#party_start_time').datetimepicker({
   theme: 'white',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=party_start_time%>'
   // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});


<% 
java.sql.Timestamp party_end_time = null;
try {
	party_end_time = party.getParty_end_time();
 } catch (Exception e) {
	 party_end_time = new java.sql.Timestamp(System.currentTimeMillis());
 }
%>

$.datetimepicker.setLocale('zh');
$('#party_end_time').datetimepicker({
   theme: 'white',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=party_end_time%>'
   //value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});




	$.datetimepicker.setLocale('zh'); // kr ko ja en
	$(function() {
		$('#party_start_time').datetimepicker(
				{
					format : 'Y-m-d H:i',
					onShow : function() {
						this.setOptions({
							maxDate : $('#party_end_time').val() ? $(
									'#party_end_time').val() : false
						})
					},
					timepicker : true,
					step : 1
				});

		$('#party_end_time').datetimepicker(
				{
					format : 'Y-m-d H:i',
					onShow : function() {
						this.setOptions({
							minDate : $('#party_start_time').val() ? $(
									'#party_start_time').val() : false
						})
					},
					timepicker : true,
					step : 1
				});
	});
</script>

</html>