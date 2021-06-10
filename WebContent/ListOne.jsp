<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.member.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	MemberVO MemberVO = (MemberVO) request.getAttribute("MemberVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>員工資料 - listOneEmp.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>


	<table>
		<tr>
			<th>會員信箱</th>
			<th>會員名稱</th>
			<th>會員頭貼</th>
			<th>會員粉絲數</th>
			<th>會員年齡</th>
			<th>會員性別</th>
			<th>會員生日</th>
			<th>會員電話</th>
			<th>會員地址</th>
			<th>會員積分</th>
			<th>創建時間</th>
			<th>更新時間</th>

		</tr>

		<tr>
			<td>${MemberVO.member_email()}</td>
<%-- 			<td>${MemberVO.member_name}</td> --%>
<%-- 			<td><img src="/upload/${MemberVO.member_pic}" width="150px"></td> --%>
<%-- 			<td>${MemberVO.member_fans}</td> --%>
<%-- 			<td>${MemberVO.member_age}</td> --%>
<%-- 			<td>${MemberVO.member_gender}</td> --%>
<%-- 			<td>${MemberVO.member_birth}</td> --%>
<%-- 			<td>${MemberVO.member_phone}</td> --%>
<%-- 			<td>${MemberVO.member_address}</td> --%>
<%-- 			<td>${MemberVO.member_point}</td> --%>
			<td>${MemberVO.member_create_time}</td>
			<td>${MemberVO.member_update_time}</td>
		</tr>
	</table>

</body>
</html>