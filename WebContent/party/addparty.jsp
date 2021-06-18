<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.party.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	PartyVO partyVO = (PartyVO) request.getAttribute("PartyVO");
%>
<%= partyVO==null %>--${partyVO.party_id}--


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>揪團發起</title>


<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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
<script src="<%=request.getContextPath()%>/resources/ckeditor/ckeditor.js"></script>

</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>揪團發起</h3>
			</td>
			<td>
				<h4>
					<a href="party_select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

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
				<td>揪團標題:</td>
				<td><input type="TEXT" name="party_title" size="45"
					value="<%=(partyVO == null) ? "標題" : partyVO.getParty_title()%>" /></td>
			</tr>
			
			<tr>
				<td>開始日期時間:</td>
				<td><input name="party_start_time" id="party_start_time" type="text" ></td>
			</tr>
			<tr>
			<fmt:formatDate type="party_start_time" value="${party_start_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
			
				<td>結束日期時間:</td>
				<td><input name="party_end_time" id="party_end_time" type="text"><fmt:formatDate type="party_end_time" value="${party_end_time }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
			</tr>
			

			<tr>
				<td>最高人數:</td>
				<td><input type="TEXT" name="party_participants_max" size="45"
							value="<%= (partyVO == null) ? "4" : partyVO.getParty_participants_max()%>" /></td>
			</tr>
			<tr>
				<td>最低人數:</td>
				<td><input type="TEXT" name="party_participants_min" size="45"
							value="<%=(partyVO == null) ? "1" : partyVO.getParty_participants_min()%>" /></td>
			</tr>
			<tr>
				<td>揪團介紹:</td>
				<td>
					 <textarea class="ckeditor" id="mContent" name="party_intro" ></textarea></td>
			</tr>


		</table>
		<br> 
		<input type="hidden" name="action" value="insert">
		<input type="submit" value="送出新增">
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
<% 
java.sql.Timestamp party_start_time = null;
try {
	party_start_time = partyVO.getParty_start_time();
 } catch (Exception e) {
	 party_start_time = new java.sql.Timestamp(System.currentTimeMillis());
 }
%>

$.datetimepicker.setLocale('zh');
$('#party_start_time').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=party_start_time%>', 
   // value:   new Date(),
   //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
   //startDate:	            '2017/07/10',  // 起始日
   //minDate:               '-1970-01-01', // 去除今日(不含)之前
   //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
});


<% 
java.sql.Timestamp party_end_time = null;
try {
	party_end_time = partyVO.getParty_end_time();
 } catch (Exception e) {
	 party_end_time = new java.sql.Timestamp(System.currentTimeMillis());
 }
%>

$.datetimepicker.setLocale('zh');
$('#party_end_time').datetimepicker({
   theme: '',              //theme: 'dark',
   timepicker:false,       //timepicker:true,
   step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
   format:'Y-m-d',         //format:'Y-m-d H:i:s',
   value: '<%=party_end_time%>', 
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