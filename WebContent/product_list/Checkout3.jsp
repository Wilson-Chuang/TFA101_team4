<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , shopping.product"%>
<%@ page import="com.orders.model.*"%>
<%@ page import="com.credit_card.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
%>


<%
	Credit_cardVO credit_cardVO = (Credit_cardVO) request.getAttribute("credit_cardVO");
%>



<%
	OrdersService ordersSvc = new OrdersService();
	List<OrdersVO> list = ordersSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	MemberService memberSvc = new MemberService();
	MemberVO memberVO = memberSvc.GET_ONE_BY_ID(1);
	pageContext.setAttribute("memberVO", memberVO);
%>

				
<%  @SuppressWarnings("unchecked")
	Vector<product> buylist = (Vector<product>) session.getAttribute("shoppingcart");
	Integer minus =  (Integer) request.getAttribute("minus");
	String amount1 =  (String) request.getAttribute("amount1");
	String amount2 =  (String) request.getAttribute("amount2");
	String subQuantity2 =  (String) request.getAttribute("subQuantity2");
%>
<html>
<head>
 <title>結帳</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/product_list/css/ShoppingCart.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/product_list/css/check_out3.css">
 <script src="<%=request.getContextPath()%>/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
 <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
 </head>
<body>

<form name="checkoutForm" action="<%=request.getContextPath()%>/product_list/shopping.html" method="POST">

<div class="check_out">
		<div class="process_1">
			<p>確認購買明細</p>
			<i class="far fa-check-circle"></i>
		</div>
		<div class="process_3">
			<p>填寫收件資料與付款方式</p>
			<i class="far fa-edit"></i>
		</div>
		<div class="process_2">
			<p>完成訂購</p>
			<i class="far fa-handshake"></i>
		</div>
		
		
		
		<div class="check_order">
			<div class="check_order_title">
				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;PAYMENT 付款方式</h1>
			</div>
			
			<div class="ckbutton">
			  <label>
			    <input type="radio" value="1" class="ckbutton_checkbox" name="payment_no" checked/>
			     	積分付款 目前剩餘積分: ${memberVO.member_point} GP  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  </label>
			  <i class="fas fa-question-circle"></i>&nbsp;
			  <a id="show22" onclick="show22()" href="javascript:;">【如何取得積分?】</a>
			     	<div class="getPoint_info" id="hide22" style="display: none;">透過以下連結發表食記文章可以取得積分</br>
			     	<a href="<%=request.getContextPath()%>/article/article_homepage.jsp" style="color:blue;">【文章專區】</a>
			     	</div>
			</div>
			
			<div class="ckbutton">
			  <label>
			    <input type="radio" value="2" class="ckbutton_checkbox" name="payment_no"/>
			     	貨到付款
			  </label>
			</div>
			
			<div class="ckbutton">
			  <label>
			    <input type="radio" value="3" class="ckbutton_checkbox" name="payment_no"/>
			     	信用卡付款
			  </label>
			</div>
			
			<div class="credit_card_info">
					
				<div>
					信用卡號:					
					<input type="text" name="credit_card_number" maxlength="4" size="4" onKeyUp="next(this)">- 
					<input type="text" name="credit_card_number" maxlength="4" size="4" onKeyUp="next(this)">- 
					<input type="text" name="credit_card_number" maxlength="4" size="4" onKeyUp="next(this)">- 
					<input type="text" name="credit_card_number" maxlength="4" size="4" onKeyUp="next(this)">
					<c:if test="${not empty credit_card_number_errorMsgs}">						
							<c:forEach var="message" items="${credit_card_number_errorMsgs}">
									<span style="color:red; font-size:12px;">${message}</span>
							</c:forEach>							
					</c:if>			
				</div>
				<div>
					有效日期: 
					<select name="credit_card_month">
						<option value="1">01</option>
						<option value="2">02</option>
						<option value="3">03</option>
						<option value="4">04</option>
						<option value="5">05</option>
						<option value="6">06</option>
						<option value="7">07</option>
						<option value="8">08</option>
						<option value="9">09</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>&nbsp;月		
					<select name="credit_card_year" id="sel"></select>&nbsp;年
				</div>
				<div>背面末三碼: <input type="text" name="credit_card_cvv" size="3">
					<c:if test="${not empty credit_card_cvv_errorMsgs}">						
							<c:forEach var="message" items="${credit_card_cvv_errorMsgs}">
									<span style="color:red; font-size:12px;">${message}</span>
							</c:forEach>							
					</c:if>	
				</div>
				<br>
				<div>
					<font style="color:#990000; font-weight:bold;">持卡人資料(必填)</font> 
					<input type="checkbox" id="autofill2" onchange="billingFunction2()">同會員資料
				</div>
				<div>持卡人姓名: <input type="text" name="credit_card_name" id="credit_card_name">
					<c:if test="${not empty credit_card_name_errorMsgs}">						
							<c:forEach var="message" items="${credit_card_name_errorMsgs}">
									<span style="color:red; font-size:12px;">${message}</span>
							</c:forEach>							
					</c:if>	
					<input class="copy_input" type="TEXT" id="copy_credit_card_name" value="<%=memberVO.getMember_name() %>">
				</div>
				<div>
					持卡人生日: 	
					<input name="credit_card_birthday" id="f_date1" type="text">
					<input class="copy_input" type="TEXT" id="copy_credit_card_birthday" value="<%=memberVO.getMember_birth() %>">
					
				</div>
				<div>手機號碼: <input type="text" name="credit_card_phone" id="credit_card_phone">
					<c:if test="${not empty credit_card_phone_errorMsgs}">						
							<c:forEach var="message" items="${credit_card_phone_errorMsgs}">
									<span style="color:red; font-size:12px;">${message}</span>
							</c:forEach>							
					</c:if>	
					<input class="copy_input" type="TEXT" id="copy_credit_card_phone" value="<%=memberVO.getMember_phone() %>">
				</div>
				<div>帳單地址: 
					<%	String address2=memberVO.getMember_address();
							String[] add1=address2.split("-");%>
					<select id="city-list1" name="city1" onchange="changeCity(this.selectedIndex)"></select>
					 <select id="county-list1" name="county1" ></select>
					 <input type="text" name="address1" id="credit_card_address">
					 <input class="copy_input" type="TEXT" id="copy_credit_card_address" value="<%=add1[2] %>">					
				
				</div>
			</div>
			
			
			<div class="check_order_title">
				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;DELIVERY INFORMATION 請填寫收件資料</h1>
			</div>
			
			<div class="form">
				<font style="color:#990000; font-weight:bold;">收件人資料(必填)</font> 
				<input type="checkbox" id="autofill" onchange="billingFunction()">同會員資料
			</div>
			
			<div class="form">
				收件姓名:<input type="TEXT" name="orders_shipping_name" size="20" id="orders_shipping_name"
							value="<%=(ordersVO == null) ? "" : ordersVO.getOrders_shipping_name()%>" />
					<c:if test="${not empty name_errorMsgs}">						
							<c:forEach var="message" items="${name_errorMsgs}">
									<span style="color:red; font-size:12px;">${message}</span>
							</c:forEach>							
					</c:if>
					<input class="copy_input" type="TEXT" id="copy_shipping_name" value="<%=memberVO.getMember_name() %>">
			</div>

			<div class="form">
				手機號碼:<input type="TEXT" name="orders_shipping_phone" size="20" id="orders_shipping_phone"
							value="<%=(ordersVO == null) ? "" : ordersVO.getOrders_shipping_phone()%>" />
							<c:if test="${not empty phone_errorMsgs}">						
							<c:forEach var="message" items="${phone_errorMsgs}">
									<span style="color:red; font-size:12px;">${message}</span>
							</c:forEach>							
					</c:if>
					<input class="copy_input" type="TEXT" id="copy_shipping_phone" value="<%=memberVO.getMember_phone() %>">
			</div>


			<div class="form">
						<%	String address=memberVO.getMember_address();
							String[] add=address.split("-");%>
					收件地址:				 
						 <select id="city-list" name="city" onchange="changeCity(this.selectedIndex)"></select>
						 <select id="county-list" name="county" ></select>
						 <input type="text" name="address" id="orders_shipping_address">
						 <input class="copy_input" type="TEXT" id="copy_shipping_address" value="<%=add[2] %>">					
	
			</div>

			<div class="form">
				備註: <textarea name="orders_shipping_note" rows="3" cols="60"></textarea>
			</div>			
		</div>

		<div class="check_order">
			<div class="check_order_title">
				<h1><i class="far fa-compass" style="font-size: 20px;"></i>&nbsp;&nbsp;RECEIPT 請選擇發票格式</h1>
			</div>
			<div class="ckbutton">
			  <label>
			    <input type="radio" value="1" class="ckbutton_checkbox"  id="invoice_1" name="invoice_no" checked/>
			  	  個人紙本發票
			  </label>
			</div>
			<div class="ckbutton">
			  <label>
			    <input type="radio" value="2" class="ckbutton_checkbox" id="invoice_2" name="invoice_no"/>
			  	 公司紙本發票
			  	  <c:if test="${not empty invoice_tax_number_errorMsgs}">						
							<c:forEach var="message" items="${invoice_tax_number_errorMsgs}">
									<span style="color:red; font-size:12px;">${message}</span>
							</c:forEach>							
					</c:if>
			  </label>
			  	<div class="invoice_tax_id">
			  		統一編號: <input type="text" name="orders_invoice_tax_number" value="<%=(ordersVO == null) ? "" : ordersVO.getOrders_invoice_tax_number()%>"/>
			  	</div>
			</div>
			<div style="height: 100px;"></div>
		</div>	
		
		
					
	</div>	
	
	
		<div class="finish_button">
			<input type="button" value="取消" class="back" 
					onclick="location.href='<%=request.getContextPath()%>/product_list/product_homePage.jsp'">
	
			
			<input type="hidden" name="action"  value="CHECKOUT4">
			<input type="submit" value="確定付款" class="next" style="background-color:black; color:white;">	
			
		</div>



</form>

	<script>
		function showAlert() {
	    	alert ("積分餘額不足!!請選擇其他付款方式");
	 	}
	</script>


<!-- 	收件人自動填單 -->
	<script>
	function billingFunction() {
	    if (document.getElementById('autofill').checked) {
	        var shippingName = document.getElementById('copy_shipping_name').value;
	        var shippingPhone = document.getElementById('copy_shipping_phone').value;
	        var shippingAddress = document.getElementById('copy_shipping_address').value;

	        document.getElementById('orders_shipping_name').value = shippingName;
	        document.getElementById('orders_shipping_phone').value = shippingPhone;
	        document.getElementById('orders_shipping_address').value = shippingAddress;
	    } 
	}
	</script>
	
<!-- 	信用卡自動填單 -->
	<script>
	function billingFunction2() {
	    if (document.getElementById('autofill2').checked) {
	        var credit_cardName = document.getElementById('copy_credit_card_name').value;
	        var credit_cardBirthday = document.getElementById('copy_credit_card_birthday').value;
	        var credit_cardPhone = document.getElementById('copy_credit_card_phone').value;
	        var credit_cardAddress = document.getElementById('copy_credit_card_address').value;

	        document.getElementById('credit_card_name').value = credit_cardName;
	        document.getElementById('f_date1').value = credit_cardBirthday;
	        document.getElementById('credit_card_phone').value = credit_cardPhone;
	        document.getElementById('credit_card_address').value = credit_cardAddress;
	    } 
	}
	</script>
	
	<script>
// 信用卡填單顯示 
	$(document).ready(function(){ 
	
	   $('input[name="payment_no"]').click(function(){
        if($(this).attr("value")=="1"){
            $(".credit_card_info").hide();
        }
        if($(this).attr("value")=="2"){
            $(".credit_card_info").hide();
        }
        if($(this).attr("value")=="3"){
            $(".credit_card_info").show();
        }
    });	   
 });
	
// 統一編號填單顯示 
   $(document).ready(function(){ 
	   $('input[name="invoice_no"]').click(function(){
        if($(this).attr("value")=="1"){
            $(".invoice_tax_id").hide();
        }
        if($(this).attr("value")=="2"){
            $(".invoice_tax_id").show();
        }
    });	   
 });
	

//    積分方法顯示
 var isShow = false;
  function show22() {
      if(!isShow) {
          isShow = true;
         document.getElementById('hide22').style.display='';
         document.getElementById('show22').innerText = "【如何取得積分?】";
     }
     else {
         isShow = false;
         document.getElementById('hide22').style.display='none';
         document.getElementById('show22').innerText = "【如何取得積分?】";
     }
  }
	  
	
	  
// 	日期下拉選擇
 onload = function (){
 
    var year=new Date().getFullYear(); //獲取當前年份
  
    var sel = document.getElementById ('sel');//獲取select下拉列表
    for ( var i = year; i < year+10; i++)//迴圈新增2006到當前年份加3年的每個年份依次新增到下拉列表
    {
        var option = document.createElement ('option');
        option.value = i;
        var txt = document.createTextNode (i);
        option.appendChild (txt);
        sel.appendChild (option);
    }
     
    var sel = document.getElementById ('sel2');//獲取select下拉列表
    for ( var i = 1970; i < year+1; i++)//迴圈新增2006到當前年份加3年的每個年份依次新增到下拉列表
    {
        var option = document.createElement ('option');
        option.value = i;
        var txt = document.createTextNode (i);
        option.appendChild (txt);
        sel.appendChild (option);
    }
    
    var sel = document.getElementById ('sel3');//獲取select下拉列表
    for ( var i = 1; i <= 31; i++)//迴圈新增2006到當前年份加3年的每個年份依次新增到下拉列表
    {
        var option = document.createElement ('option');
        option.value = i;
        var txt = document.createTextNode (i);
        option.appendChild (txt);
        sel.appendChild (option);
    }     
 }
// 	    信用卡四格填單
   function next(obj) { 
   	if (obj.value.length == obj.maxLength) { 
   		do { 
   			obj = obj.nextSibling; 
   		} while (obj.nodeName != "INPUT"); 
   		obj.focus(); 
   	}		 
   } 

   document.forms[0].N1.focus(); 
	
	
	</script>

	<script src="<%=request.getContextPath()%>/product_list/js/check_out.js"></script>
</body>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date credit_card_birthday = null;
  try {
	  credit_card_birthday = credit_cardVO.getCredit_card_bithday();
   } catch (Exception e) {
	   credit_card_birthday = new java.sql.Date(System.currentTimeMillis());
   }
%>



	<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=credit_card_birthday%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>



<script type="text/javascript">
	var city=['臺北市-', '新北市-','基隆市-' ,'桃園市-' , '新竹市-', '新竹縣-', '苗栗縣-',
		'臺中市-', '彰化縣-', '南投縣-', '雲林縣-', '嘉義市-', '嘉義縣-', '臺南市-', '高雄市-', '屏東縣-',
		'宜蘭縣-', '花蓮縣-', '臺東縣-', '澎湖縣-', '金門縣-', '連江縣-',];
	var citySelect=document.getElementById("city-list");
	var inner="";
	for(var i=0;i<city.length;i++){
	console.log(city[i]);
	console.log('<%=add[0]%>-');
	console.log(city[i]=='<%=add[0]%>-');
		if(city[i]=='<%=add[0]%>-'){
		inner=inner+'<option value='+city[i]+' selected>'+city[i]+'</option>';			
		}else{
		inner=inner+'<option value='+city[i]+'>'+city[i]+'</option>';
		}
	}
	citySelect.innerHTML=inner;
	var county=new Array();
	county[0]=['中正區-','大同區-','中山區-','松山區-','大安區-','萬華區-','信義區-','士林區-','北投區-','內湖區-','南港區-','文山區-'];
	county[1]=['板橋區-','新莊區-','中和區-','永和區-','土城區-','樹林區-','三峽區-','鶯歌區-','三重區-','蘆洲區-','五股區-','泰山區-','林口區-','八里區-','淡水區-','三芝區-','石門區-','金山區-','萬里區-','汐止區-','瑞芳區-','貢寮區-','平溪區-','雙溪區-','新店區-','深坑區-','石碇區-','坪林區-','烏來區-'];	
	county[2]=['仁愛區-','中正區-','信義區-','中山區-','安樂區-','暖暖區-','七堵區-'];	
	county[3]=['桃園區-','中壢區-','平鎮區-','八德區-','楊梅區-','蘆竹區-','大溪區-','龍潭區-','龜山區-','大園區-','觀音區-','新屋區-','復興區-'];
	county[4]=['東區-','北區-','香山區-']
	county[5]=['竹北市-','竹東鎮-','新埔鎮-','關西鎮-','湖口鄉-','新豐鄉-','峨眉鄉-','寶山鄉-','北埔鄉-','芎林鄉-','橫山鄉-','尖石鄉-','五峰鄉-'];
	county[6]=['苗栗市-','頭份市-','竹南鎮-','後龍鎮-','通霄鎮-','苑裡鎮-','卓蘭鎮-','造橋鄉-','西湖鄉-','頭屋鄉-','公館鄉-','銅鑼鄉-','三義鄉-','大湖鄉-','獅潭鄉-','三灣鄉-','南庄鄉-','泰安鄉-'];
	county[7]=[' 中區-','東區-','南區-','西區-','北區-','北屯區-','西屯區-','南屯區-','太平區-','大里區-','霧峰區-','烏日區-','豐原區-','后里區-','石岡區-','東勢區-','新社區-','潭子區-','大雅區-','神岡區-','大肚區-','沙鹿區-','龍井區-','梧棲區-','清水區-','大甲區-','外埔區-','大安區-','和平區-'];
	county[8]=['彰化市-','員林市-','和美鎮-','鹿港鎮-','溪湖鎮-','二林鎮-','田中鎮-','北斗鎮-','花壇鄉-','芬園鄉-','大村鄉-','永靖鄉-','伸港鄉-','線西鄉-','福興鄉-','秀水鄉-','埔心鄉-','埔鹽鄉-','大城鄉-','芳苑鄉-','竹塘鄉-','社頭鄉-','二水鄉-','田尾鄉-','埤頭鄉-','溪州鄉-'];
	county[9]=['南投市-','埔里鎮-','草屯鎮-','竹山鎮-','集集鎮-','名間鄉-','鹿谷鄉-','中寮鄉-','魚池鄉-','國姓鄉-','水里鄉-','信義鄉-','仁愛鄉-'];
	county[10]=['斗六市-','斗南鎮-','虎尾鎮-','西螺鎮-','土庫鎮-','北港鎮-','林內鄉-','古坑鄉-','大埤鄉-','莿桐鄉-','褒忠鄉-','二崙鄉-','崙背鄉-','麥寮鄉-','臺西鄉-','東勢鄉-','元長鄉-','四湖鄉-','口湖鄉-','水林鄉-'];
	county[11]=['東區-','西區-'];
	county[12]=['太保市-','朴子市-','布袋鎮-','大林鎮-','民雄鄉-','溪口鄉-','新港鄉-','六腳鄉-','東石鄉-','義竹鄉-','鹿草鄉-','水上鄉-','中埔鄉-','竹崎鄉-','梅山鄉-','番路鄉-','大埔鄉-','阿里山鄉-'];
	county[13]=['中西區-','東區-','南區-',' 北區-',' 安平區-','安南區-','永康區-','歸仁區-','新化區-','左鎮區-','玉井區-','楠西區-','南化區-','仁德區-','關廟區-','龍崎區-','官田區-','麻豆區-','佳里區-','西港區-','七股區-','將軍區-','學甲區-','北門區-','新營區-','後壁區-','白河區-','東山區-','六甲區-','下營區-','柳營區-','鹽水區-','善化區-','大內區-','山上區-','新市區-','安定區-'];
	county[14]=['楠梓區-','左營區-','鼓山區-','三民區-','鹽埕區-','前金區-','新興區-','苓雅區-','前鎮區-','旗津區-','小港區-','鳳山區-','大寮區-','鳥松區-','林園區-','仁武區-','大樹區-','大社區-','岡山區-','路竹區-','橋頭區-','梓官區-','彌陀區-','永安區-','燕巢區-','田寮區-','阿蓮區-','茄萣區-','湖內區-','旗山區-','美濃區-','內門區-','杉林區-','甲仙區-','六龜區-','茂林區-','桃源區-','那瑪夏區-'];
	county[15]=['屏東市-','潮州鎮-','東港鎮-','恆春鎮-','萬丹鄉-','長治鄉-','麟洛鄉-','九如鄉-','里港鄉-','鹽埔鄉-','高樹鄉-','萬巒鄉-','內埔鄉-','竹田鄉-','新埤鄉-','枋寮鄉-','新園鄉-','崁頂鄉-','林邊鄉-','南州鄉-','佳冬鄉-','琉球鄉-','車城鄉-','滿州鄉-','枋山鄉-','霧臺鄉-','瑪家鄉-','泰武鄉-','來義鄉-','春日鄉-','獅子鄉-','牡丹鄉-','三地門鄉-'];
	county[16]=['宜蘭市-','頭城鎮-','羅東鎮-','蘇澳鎮-','礁溪鄉-','壯圍鄉-','員山鄉-','冬山鄉-','五結鄉-','三星鄉-','大同鄉-','南澳鄉-'];
	county[17]=['花蓮市-','鳳林鎮-','玉里鎮-','新城鄉-','吉安鄉-','壽豐鄉-','光復鄉-','豐濱鄉-','瑞穗鄉-','富里鄉-','秀林鄉-','萬榮鄉-','卓溪鄉-'];
	county[18]=['臺東市-','成功鎮-','關山鎮-','長濱鄉-','池上鄉-','東河鄉-','鹿野鄉-','卑南鄉-','大武鄉-','綠島鄉-','太麻里鄉-','海端鄉-','延平鄉-','金峰鄉-','達仁鄉-','蘭嶼鄉-'];
	county[19]=['馬公市-','湖西鄉-','白沙鄉-','西嶼鄉-','望安鄉-','七美鄉-'];
	county[20]=['金城鎮-','金湖鎮-','金沙鎮-','金寧鄉-','烈嶼鄉-','烏坵鄉-'];
	county[21]=['南竿鄉-','北竿鄉-','莒光鄉-','東引鄉-'];
	function changeCity(index){
		var Sinner="";
		for(var i=0;i<county[index].length;i++){
			if(county[index][i]=='<%=add[1]%>-'){
			Sinner=Sinner+'<option selected value='+county[index][i]+'>'+county[index][i]+'</option>';
			}else{
			Sinner=Sinner+'<option value='+county[index][i]+'>'+county[index][i]+'</option>';
			}
		}
		var countyelect=document.getElementById("county-list");
		countyelect.innerHTML=Sinner;
	}
	changeCity(document.getElementById("city-list").selectedIndex);
</script>



<script type="text/javascript">
	var city=['臺北市-', '新北市-','基隆市-' ,'桃園市-' , '新竹市-', '新竹縣-', '苗栗縣-',
		'臺中市-', '彰化縣-', '南投縣-', '雲林縣-', '嘉義市-', '嘉義縣-', '臺南市-', '高雄市-', '屏東縣-',
		'宜蘭縣-', '花蓮縣-', '臺東縣-', '澎湖縣-', '金門縣-', '連江縣-',];
	var citySelect=document.getElementById("city-list1");
	var inner="";
	for(var i=0;i<city.length;i++){
	console.log(city[i]);
	console.log('<%=add1[0]%>-');
	console.log(city[i]=='<%=add1[0]%>-');
		if(city[i]=='<%=add1[0]%>-'){
		inner=inner+'<option value='+city[i]+' selected>'+city[i]+'</option>';			
		}else{
		inner=inner+'<option value='+city[i]+'>'+city[i]+'</option>';
		}
	}
	citySelect.innerHTML=inner;
	var county=new Array();
	county[0]=['中正區-','大同區-','中山區-','松山區-','大安區-','萬華區-','信義區-','士林區-','北投區-','內湖區-','南港區-','文山區-'];
	county[1]=['板橋區-','新莊區-','中和區-','永和區-','土城區-','樹林區-','三峽區-','鶯歌區-','三重區-','蘆洲區-','五股區-','泰山區-','林口區-','八里區-','淡水區-','三芝區-','石門區-','金山區-','萬里區-','汐止區-','瑞芳區-','貢寮區-','平溪區-','雙溪區-','新店區-','深坑區-','石碇區-','坪林區-','烏來區-'];	
	county[2]=['仁愛區-','中正區-','信義區-','中山區-','安樂區-','暖暖區-','七堵區-'];	
	county[3]=['桃園區-','中壢區-','平鎮區-','八德區-','楊梅區-','蘆竹區-','大溪區-','龍潭區-','龜山區-','大園區-','觀音區-','新屋區-','復興區-'];
	county[4]=['東區-','北區-','香山區-']
	county[5]=['竹北市-','竹東鎮-','新埔鎮-','關西鎮-','湖口鄉-','新豐鄉-','峨眉鄉-','寶山鄉-','北埔鄉-','芎林鄉-','橫山鄉-','尖石鄉-','五峰鄉-'];
	county[6]=['苗栗市-','頭份市-','竹南鎮-','後龍鎮-','通霄鎮-','苑裡鎮-','卓蘭鎮-','造橋鄉-','西湖鄉-','頭屋鄉-','公館鄉-','銅鑼鄉-','三義鄉-','大湖鄉-','獅潭鄉-','三灣鄉-','南庄鄉-','泰安鄉-'];
	county[7]=[' 中區-','東區-','南區-','西區-','北區-','北屯區-','西屯區-','南屯區-','太平區-','大里區-','霧峰區-','烏日區-','豐原區-','后里區-','石岡區-','東勢區-','新社區-','潭子區-','大雅區-','神岡區-','大肚區-','沙鹿區-','龍井區-','梧棲區-','清水區-','大甲區-','外埔區-','大安區-','和平區-'];
	county[8]=['彰化市-','員林市-','和美鎮-','鹿港鎮-','溪湖鎮-','二林鎮-','田中鎮-','北斗鎮-','花壇鄉-','芬園鄉-','大村鄉-','永靖鄉-','伸港鄉-','線西鄉-','福興鄉-','秀水鄉-','埔心鄉-','埔鹽鄉-','大城鄉-','芳苑鄉-','竹塘鄉-','社頭鄉-','二水鄉-','田尾鄉-','埤頭鄉-','溪州鄉-'];
	county[9]=['南投市-','埔里鎮-','草屯鎮-','竹山鎮-','集集鎮-','名間鄉-','鹿谷鄉-','中寮鄉-','魚池鄉-','國姓鄉-','水里鄉-','信義鄉-','仁愛鄉-'];
	county[10]=['斗六市-','斗南鎮-','虎尾鎮-','西螺鎮-','土庫鎮-','北港鎮-','林內鄉-','古坑鄉-','大埤鄉-','莿桐鄉-','褒忠鄉-','二崙鄉-','崙背鄉-','麥寮鄉-','臺西鄉-','東勢鄉-','元長鄉-','四湖鄉-','口湖鄉-','水林鄉-'];
	county[11]=['東區-','西區-'];
	county[12]=['太保市-','朴子市-','布袋鎮-','大林鎮-','民雄鄉-','溪口鄉-','新港鄉-','六腳鄉-','東石鄉-','義竹鄉-','鹿草鄉-','水上鄉-','中埔鄉-','竹崎鄉-','梅山鄉-','番路鄉-','大埔鄉-','阿里山鄉-'];
	county[13]=['中西區-','東區-','南區-',' 北區-',' 安平區-','安南區-','永康區-','歸仁區-','新化區-','左鎮區-','玉井區-','楠西區-','南化區-','仁德區-','關廟區-','龍崎區-','官田區-','麻豆區-','佳里區-','西港區-','七股區-','將軍區-','學甲區-','北門區-','新營區-','後壁區-','白河區-','東山區-','六甲區-','下營區-','柳營區-','鹽水區-','善化區-','大內區-','山上區-','新市區-','安定區-'];
	county[14]=['楠梓區-','左營區-','鼓山區-','三民區-','鹽埕區-','前金區-','新興區-','苓雅區-','前鎮區-','旗津區-','小港區-','鳳山區-','大寮區-','鳥松區-','林園區-','仁武區-','大樹區-','大社區-','岡山區-','路竹區-','橋頭區-','梓官區-','彌陀區-','永安區-','燕巢區-','田寮區-','阿蓮區-','茄萣區-','湖內區-','旗山區-','美濃區-','內門區-','杉林區-','甲仙區-','六龜區-','茂林區-','桃源區-','那瑪夏區-'];
	county[15]=['屏東市-','潮州鎮-','東港鎮-','恆春鎮-','萬丹鄉-','長治鄉-','麟洛鄉-','九如鄉-','里港鄉-','鹽埔鄉-','高樹鄉-','萬巒鄉-','內埔鄉-','竹田鄉-','新埤鄉-','枋寮鄉-','新園鄉-','崁頂鄉-','林邊鄉-','南州鄉-','佳冬鄉-','琉球鄉-','車城鄉-','滿州鄉-','枋山鄉-','霧臺鄉-','瑪家鄉-','泰武鄉-','來義鄉-','春日鄉-','獅子鄉-','牡丹鄉-','三地門鄉-'];
	county[16]=['宜蘭市-','頭城鎮-','羅東鎮-','蘇澳鎮-','礁溪鄉-','壯圍鄉-','員山鄉-','冬山鄉-','五結鄉-','三星鄉-','大同鄉-','南澳鄉-'];
	county[17]=['花蓮市-','鳳林鎮-','玉里鎮-','新城鄉-','吉安鄉-','壽豐鄉-','光復鄉-','豐濱鄉-','瑞穗鄉-','富里鄉-','秀林鄉-','萬榮鄉-','卓溪鄉-'];
	county[18]=['臺東市-','成功鎮-','關山鎮-','長濱鄉-','池上鄉-','東河鄉-','鹿野鄉-','卑南鄉-','大武鄉-','綠島鄉-','太麻里鄉-','海端鄉-','延平鄉-','金峰鄉-','達仁鄉-','蘭嶼鄉-'];
	county[19]=['馬公市-','湖西鄉-','白沙鄉-','西嶼鄉-','望安鄉-','七美鄉-'];
	county[20]=['金城鎮-','金湖鎮-','金沙鎮-','金寧鄉-','烈嶼鄉-','烏坵鄉-'];
	county[21]=['南竿鄉-','北竿鄉-','莒光鄉-','東引鄉-'];
	function changeCity2(index){
		var Sinner="";
		for(var i=0;i<county[index].length;i++){
			if(county[index][i]=='<%=add1[1]%>-'){
			Sinner=Sinner+'<option selected value='+county[index][i]+'>'+county[index][i]+'</option>';
			}else{
			Sinner=Sinner+'<option value='+county[index][i]+'>'+county[index][i]+'</option>';
			}
		}
		var countyelect=document.getElementById("county-list1");
		countyelect.innerHTML=Sinner;
	}
	changeCity2(document.getElementById("city-list1").selectedIndex);
</script>

</html>