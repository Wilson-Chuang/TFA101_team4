<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Guide好食 |後台登入</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/cms/css/cmsLogin.css">

</head>
<body>

	<div class="main">
		<div class="pic">
			<img src="${pageContext.request.contextPath}/manager/images/login.jpg">
		</div>

		<div class="login">
			<img src="${pageContext.request.contextPath}/manager/images/logo.png">
			<h3>後台管理系統登入</h3>
			
			
			<form action="<%=request.getContextPath()%>/cms/login.do" method="post" >
				<p>管理員信箱</p>
				<input type="text" name="manager_email" placeholder="請輸入信箱" /><br>
				<p>管理員密碼</p>
				<input type="password" name="manager_password" placeholder="請輸入密碼" /><br>

					<%-- 錯誤表列 --%>
                    <c:if test="${not empty errorMsgs}">
                        <font style="color: red">請修正以下錯誤:</font>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color: red">${message}</li>
                            </c:forEach>
                        </ul>
                    </c:if> 

				<button type="submit">登入</button>
				<input type="hidden" name="manager_id"  value="${managerVO.manager_id}">
				<input type="hidden" name="action" value="login">
			</form>
		</div>
	</div>

</body>
</html>