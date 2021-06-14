<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.party.model.*"%>

<%
	PartyVO partyVO = (PartyVO) request.getAttribute("partyVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%=partyVO == null%>--${partyVO.party_id}--

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>揪團資料修改</title>


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

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>








</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>揪團資料修改</h3>
				<h4>
					<a href="party_select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

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
				<td>揪團編號:<font color=red><b>*</b></font></td>
				<td><%=partyVO.getParty_id()%></td>
			</tr>
			<tr>
				<td>揪團標題:</td>
				<td><input type="TEXT" name="party_title" size="45"
					value="<%=partyVO.getParty_title()%>" /></td>
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
				<td>揪團介紹:</td>
				<td><input type="TEXT" name="party_intro" size="45"
					value="<%=partyVO.getParty_intro()%>" /></td>
			</tr>
			<tr>
				<td>成團人數最多限制:</td>
				<td><input type="TEXT" name="party_participants_max" size="45"
					value="<%=partyVO.getParty_participants_max()%>" /></td>
			</tr>
			<tr>
				<td>成團人數最少限制:</td>
				<td><input type="TEXT" name="party_participants_min" size="45"
					value="<%=partyVO.getParty_participants_min()%>" /></td>
			</tr>


			<jsp:useBean id="partytSvc" scope="page"
				class="com.party.model.PartyService" />


		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="empno" value="<%=partyVO.getParty_id()%>">
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
   value: '<%=party_start_time%>', // value:   new Date(),
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
   value: '<%=party_end_time%>', // value:   new Date(),
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