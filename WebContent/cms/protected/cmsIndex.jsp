<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.orders.model.*"%>

<%	
	ManagerVO loginmanagerVO = (ManagerVO) session.getAttribute("loginmanagerVO");

	MemberService memberSvc = new MemberService();
	Integer countmember = memberSvc.countMember();
	
	ShopService shopSvc = new ShopService();
	Integer countshop = shopSvc.countShop();
	
	OrdersService ordersSvc = new OrdersService();
	Integer countorders = ordersSvc.countOrders();
%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gudie好食 | 後台系統</title>

<link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">

</head>

<style>
	div.main_content {
	    width: 100%;
	    float: left;
	
	}
	.main{
	    height: 100%;
	    width: 80%;
	    float: left;
	    background-color: #ffffff;
	    padding-top: 30px;
	}
	.main ul{
	list-style: none;
	/*background-color: red;*/
	overflow: hidden;
	width: 1086px;
	padding: 50px 0;
	margin: 0 auto;
	}
	.main li{
		width: 200px;
		float: left;
		margin: 0 10px;
	    
	}
	.main h3{
	text-align: center;
	color: white;
    font-weight: bold;
    font-size: 25px;
    padding-top: 25px;
	}
	.box1{
	    background-color: rgb(126, 185, 205);
	    border-top-left-radius: 40px;
	    border-top-right-radius: 40px;
	    height:70px;
	}
	.box2{
	    background-color: rgb(255, 147, 179);
	    border-top-left-radius: 40px;
	    border-top-right-radius: 40px;
	    height:70px;
	}
	.box3{
	    background-color: rgb(240, 185, 140);
	    border-top-left-radius: 40px;
	    border-top-right-radius: 40px;
	    height:70px;
	}
/* 	.box4{ */
/* 	    background-color: rgb(0, 133, 174); */
/* 	    border-top-left-radius:40px; */
/* 	    border-top-right-radius: 40px; */
/* 	    height:70px; */
/* 	} */
	.bottom{
	    box-shadow:3px 3px 12px rgb(196, 196, 196);
	    height: 100px;
	}
	.bottom h3{
	    text-align: center;
		color: rgb(119, 119, 119);
	    font-weight: bold;
	    font-size: 25px;
	    padding-top: 40px;
	}


</style>

<body>

	
<header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>

	<div class="main_content">
		
		<div>
			<jsp:include page="/cms/header_asideMenu/cmsAsideMenu.jsp" flush="true" />
    	</div>


		<div class="main">
			
			<ul>
                <li>
                    <div class="box1">
                        <h3>會員人數</h3>
                    </div>
                    <div class="bottom">
                        <h3><%=countmember%></h3>
                    </div>
                </li>
                <li>
                    <div class="box2">
                        <h3>商家數量</h3>
                    </div>
                    <div class="bottom">
                        <h3><%=countshop%></h3>
                    </div>
                </li>
                <li>
                    <div class="box3">
                        <h3>訂單數量</h3>
                    </div>
                    <div class="bottom">
                        <h3><%=countorders%></h3>
                    </div>
                </li>
          <!--      <li>
                    <div class="box4">
                        <h3>活動數量</h3>
                    </div>
                    <div class="bottom">
                        <h3>0000</h3>
                    </div>
                </li> --> 
            </ul>
			
		</div>

	</div>

	

	<!-- body 結束標籤之前，載入Bootstrap 的 JS 及其相依性安裝(jQuery、Popper) -->
	<script src="./vendors/jquery/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
	<script src="./vendors/popper/popper.min.js"></script>
	<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>