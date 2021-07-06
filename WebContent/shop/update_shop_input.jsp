<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>

<%
	ShopVO shopVO = (ShopVO) request.getAttribute("shopVO"); 
// ShopServlet.java (Concroller) 存入req的shopVO物件
// (包括幫忙取出的shopVO, 也包括輸入資料錯誤時的shopVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商家資料修改 - update_shop_input.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/bootstrap/css/bootstrap.min.css">


<style>

table {
	width: 900px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
	height:50px;
}

div.main_content {
    width: 100%;
    float: left;
  }
  
 h3{
 	margin:20px 0;
 	font-size:22px;
  	color: gray;
  }
  
  input{
  	border:0;
  	border-bottom: 1px solid gray;
  }
 }
.rightcontent{
	margin-left:20%;
}


#preview {
      border: 1px solid lightgray;
      display: table;
      width: 100px;
      min-height: 150px;
      position: relative;
    }
    #preview span.text {
      position: absolute;
      display: inline-block;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      color: lightgray;
    }
    #preview img.preview_img {
      width: 100%;
    }
     #preview_m {
      border: 1px solid lightgray;
      display: table;
      width: 100px;
      min-height: 150px;
      position: relative;
    }
    #preview_m span.text {
      position: absolute;
      display: inline-block;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      z-index: 1;
      color: lightgray;
    }
    #preview_m img.preview_img {
      width: 100%;
    }

</style>

</head>
<body bgcolor='white'>


<header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>

<div class="main_content">

    <div>
	<jsp:include page="/cms/header_asideMenu/cmsAsideMenu.jsp" flush="true" />
    </div>

	<div class="rightcontent">
		<h3>商家資料修改</h3>
	
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	
		<FORM METHOD="post" ACTION="shop.do" name="form1" enctype="multipart/form-data">
			<table>
				<tr>
					<td>商家編號<font color=red><b>*</b></font></td>
					<td><%=shopVO.getShop_id()%></td>
				</tr>
				<jsp:useBean id="memberSvc" scope="page"
					class="com.member.model.MemberService" />
				<tr>
					<td>會員編號<font color=red><b>*</b></font></td>
					<td><select size="1" name="member_id">
							<c:forEach var="memberVO" items="${memberSvc.all}">
								<option value="${memberVO.member_id}"
									${(shopVO.member_id==memberVO.member_id)? 'selected':'' }>${memberVO.member_id}
							</c:forEach>
					</select></td>
				</tr>
	
				<tr>
					<td>統一編號</td>
					<td>
						<input type="TEXT" size="45" disabled="disabled" value="<%=shopVO.getShop_tax_id()%>" />
						<input type="hidden" name="shop_tax_id" size="45" value="<%=shopVO.getShop_tax_id()%>" />
					</td>
				</tr>
				<tr>
					<td>商家名稱</td>
					<td><input type="TEXT" name="shop_name" size="45"
						value="<%=shopVO.getShop_name()%>" /></td>
				</tr>
				<tr>
					<td>郵遞區號</td>
					<td><input type="TEXT" name="shop_zip_code" size="45"
						value="<%=shopVO.getShop_zip_code()%>" /></td>
				</tr>
				<tr>
					<td>縣市</td>
					<td><input type="TEXT" name="shop_city" size="45"
						value="<%=shopVO.getShop_city()%>" /></td>
				</tr>
				<tr>
					<td>地址</td>
					<td><input type="TEXT" name="shop_address" size="45"
						value="<%=shopVO.getShop_address()%>" /></td>
				</tr>
				<tr>
					<td>緯度</td>
					<td><input type="TEXT" name="shop_latitude" size="45" 
						value="<%=shopVO.getShop_latitude()%>" /></td>
				</tr>
				<tr>
					<td>經度</td>
					<td><input type="TEXT" name="shop_longitude" size="45"
						value="<%=shopVO.getShop_longitude()%>" /></td>
				</tr>
				<tr>
					<td>商家介紹</td>
					<td><input type="TEXT" name="shop_description" size="45"
						value="<%=shopVO.getShop_description()%>" /></td>
				</tr>
				<tr>
					<td>標籤</td>
					<td><input type="TEXT" name="shop_tag" size="45"
						value="<%=shopVO.getShop_tag()%>" /></td>
				</tr>
				<tr>
					<td>評價</td>
					<td>
						<input type="TEXT"  size="45" disabled="disabled" value="<%=shopVO.getShop_rating()%>" />
						<input type="hidden" name="shop_rating" size="45" value="<%=shopVO.getShop_rating()%>"/>				
					</td>
				</tr>
				<tr>
					<td>評價總數</td>
					<td>
						<input type="TEXT" size="45" disabled="disabled" value="<%=shopVO.getShop_rating_count()%>" />
						<input type="hidden" name="shop_rating_count" size="45" value="<%=shopVO.getShop_rating_count()%>" />
					</td>
				</tr>
				<tr>
					<td>評價總和</td>
					<td>
						<input type="TEXT" size="45" disabled="disabled" value="<%=shopVO.getShop_rating_total()%>" />
						<input type="hidden" name="shop_rating_total" size="45" value="<%=shopVO.getShop_rating_total()%>" />
					</td>
				</tr>
				<tr>
					<td>信箱</td>
					<td><input type="TEXT" name="shop_email" size="45"
						value="<%=shopVO.getShop_email()%>" /></td>
				</tr>
				<tr>
					<td>聯絡電話</td>
					<td><input type="TEXT" name="shop_phone" size="45"
						value="<%=shopVO.getShop_phone()%>" /></td>
				</tr>
				<tr>
					<td>均消</td>
					<td><input type="TEXT" name="shop_price_level" size="45"
						value="<%=shopVO.getShop_price_level()%>" /></td>
				</tr>
				<tr>
					<td>營業時間</td>
					<td><input type="TEXT" name="shop_opening_time" size="45"
						value="<%=shopVO.getShop_opening_time()%>" /></td>
				</tr>
				<tr>
					<td>網站</td>
					<td><input type="TEXT" name="shop_website" size="45"
						value="<%=shopVO.getShop_website()%>" /></td>
				</tr>
				<tr>
					<td>圖片</td>
					<td style="padding: 10px 0;">
						<input type="file" id="p_file" name="shop_main_img" accept="image/*" /><br />
				      	<div id="preview">
				      	<% if(shopVO.getShop_main_img().length()<1 || shopVO.getShop_id()==null){ %>
				      	<span class="text">預覽圖</span>
				      	<% }else{ %>
				      	<div><img class='preview_img' src='<%=request.getContextPath()%>/uploads/shop/<% out.print(shopVO.getShop_tax_id()); %>/images/<%=shopVO.getShop_main_img()%>'
				      	title='<%=shopVO.getShop_main_img()%>'/></div>
				      	<% } %>
				      	</div>
					</td>
				</tr>
				<tr>
					<td>圖片庫</td>
					<td style="padding: 10px 0;">
						<input type="file" id="p_file_m" name="shop_gallery" accept="image/*" multiple /><br />
					     <div id="preview_m">
					      	<% if(shopVO.getShop_gallery().length()<1 || shopVO.getShop_id()==null){ %>
					      	<span class="text">預覽圖</span>
					      	<% }else{ %>      	
					      	<div>
					      	<%
					      	String filename = shopVO.getShop_gallery().replaceAll("[\\[\\]\"]", "");
					      	String[] values = filename.split(", ");				      	
						    for (int i=0; i<values.length; i++) {
						    %>
						      	<img class='preview_img' src='<%=request.getContextPath()%>/uploads/shop/<% out.print(shopVO.getShop_tax_id()); %>/gallery/<% out.print(values[i]); %>' 
						      	title='<% out.print(values[i]); %>'/>		
								<% } %>
					      	</div>
					      	<% } %>
	      				</div>
					</td>
				</tr>
<!-- 				<tr> -->
				<!--	<td>更新時間:</td> -->
<!-- 					<td><input name="shop_update_time" id="f_date2" type="hidden"></td> -->
<!-- 				</tr> -->
				<tr>
					<td>總瀏覽數</td>
					<td>
						<input type="TEXT" size="45" value="<%=shopVO.getShop_total_view()%>" disabled="disabled"/>
						<input type="hidden" name="shop_total_view" size="45" value="<%=shopVO.getShop_total_view()%>" />
					</td>
				</tr>
				<tr>
<!-- 				<td>預約功能</td> -->
<!-- 					<td><input type="hidden" name="shop_reserv_status" size="45"  -->
<%-- 						value="<%=shopVO.getShop_reserv_status()%>" /> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
	
			</table>
			<br> 
			<input type="hidden" name="action" value="update"> 
			<input type="hidden" name="shop_id" value="<%=shopVO.getShop_id()%>">
			<input type="submit" value="送出修改" style="margin-left: 20%; margin-bottom: 30px; border:1px solid gray; border-radius:10%;" >
		</FORM>
	</div>
</div>



<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
	$('#f_date2').datetimepicker({
	  theme: '',              //theme: 'dark',
	  timepicker:false,       //timepicker:true,
	  step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	  format:'Y-m-d',         //format:'Y-m-d H:i:s',
	  value: '<%=shopVO.getShop_update_time()%>', // value:   new Date(),
		//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
		//startDate:	            '2017/07/10',  // 起始日
		//minDate:               '-1970-01-01', // 去除今日(不含)之前
		//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

    var output = document.getElementById("preview");
    var output_m = document.getElementById("preview_m");
    
 // 上傳監聽
    document
      .getElementById("p_file")
      .addEventListener("change", function (e) {
        var files = event.target.files;
        // 是否被放入圖片預覽的函式
        picPreview(files);
      });
 
    // 上傳監聽
    document
      .getElementById("p_file_m")
      .addEventListener("change", function (e) {
        var files = event.target.files;
        // 是否被放入圖片預覽的函式
        picPreview_m(files);
      });
    
    function picPreview(files) {
      output.innerHTML = "";
        let file = files[0];
        // 如果不是圖片，則跳出
        if (!file.type.match("image")) {
          output.innerHTML = '<span class="text">預覽圖</span>';
        }

        let picReader = new FileReader();
        picReader.addEventListener("load", function (e) {
          var picFile = e.target;
          var div = document.createElement("div");
          div.innerHTML =
            "<img class='preview_img' src='" +
            picFile.result +
            "'" +
            "title='" +
            file.name +
            "'/>";
          output.insertBefore(div, null);
        });
        // 讀取圖片
        picReader.readAsDataURL(file);
    }
    function picPreview_m(files) {
      output_m.innerHTML = "";

      for (let i = 0; i < files.length; i++) {
        let file = files[i];
        // 如果不是圖片，則跳出
        if (!file.type.match("image")) {
          output_m.innerHTML = '<span class="text">預覽圖</span>';
          continue;
        }

        let picReader = new FileReader();
        picReader.addEventListener("load", function (e) {
          var picFile = e.target;
          var div = document.createElement("div");
          div.innerHTML =
            "<img class='preview_img' src='" +
            picFile.result +
            "'" +
            "title='" +
            file.name +
            "'/>";
          output_m.insertBefore(div, null);
        });
        // 讀取圖片
        picReader.readAsDataURL(file);
      }
    }
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
</html>