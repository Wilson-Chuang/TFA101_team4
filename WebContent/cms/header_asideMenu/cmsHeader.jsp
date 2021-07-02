<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*"%>

<%	
	ManagerVO loginmanagerVO = (ManagerVO) session.getAttribute("loginmanagerVO");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後台Header</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/cms/vendors/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/cms/css/cmsHeader.css">
</head>

<body>

	<header class="header">

		<div class="logo">
			<a href="${pageContext.request.contextPath}/cms/protected/cmsIndex.jsp">
				<img src="${pageContext.request.contextPath}/manager/images/logo(white).png">
			</a>
		</div>
		<div class="Breadcrumbs">
			<!--
			<form>
				 placeholder輸入欄裡的預設字 
				<input type="search" placeholder="搜尋">
				<button>
					<i class="fas fa-search"></i>
				</button>
			</form>
			-->
		</div>
		<div>
            <ul class="user">
                <li class="guidefoodindex">
                    <a href="<%=request.getContextPath()%>/ ">前台網頁</a>
                </li>
                <li>
                    <img src="<%=request.getContextPath()%>/manager/GetPic.do?manager_id=${loginmanagerVO.manager_id}">
                </li>
                <li>
                    <form action="<%=request.getContextPath()%>/cms/login.do" method="post" >
                        <button>登出</button>
                        <input type="hidden" name="action" value="logout">
                    </form>
                </li>
            </ul>
        </div>
	</header>
	
	<div class="box"></div>


	<!-- body 結束標籤之前，載入Bootstrap 的 JS 及其相依性安裝(jQuery、Popper) -->
	<script src="./vendors/jquery/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script src="./vendors/popper/popper.min.js"></script>
	<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>