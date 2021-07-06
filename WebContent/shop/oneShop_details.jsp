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

th, td {
	padding: 1px 0 1px 15px;
	height:50px;
}
td{
	border:0;
	border-bottom: 1px solid #d1d1d1;
	padding: 1px 0 1px 15px;
}
td .tabletitle{
	border:none;
	width:100px;
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
    
   #back button:focus {
     outline: none;
  }
  #back button{
  	 /* 圓角 */
     border-radius: 5%;
    /* 輸入文字色彩設定 */
     color: rgb(41, 41, 41);
     padding: 5px 10px;
  	 width:150px;
  	 height:50px;
  	 margin:20px 10px;
  	 border-radius:20%;
  	 background-color:#e9e9e9;
  	 border: none;
  }  
  #back button a{
	text-decoration: none; 
	font-size:15px;
	color: rgb(41, 41, 41);
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
		<h3>商家詳細資料</h3>
	
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
			<table  style="width:980px; table-layout:fixed; word-wrap:break-word; word-break ; break-all;">
				<tr>
					<td class="tabletitle" width="100px" style="border:none">商家編號</td>
					<td><%=shopVO.getShop_id()%></td>
				</tr>
				<jsp:useBean id="memberSvc" scope="page"
					class="com.member.model.MemberService" />
				<tr>
					<td class="tabletitle" style="border:none">會員編號</td>
					<td>
						<c:forEach var="memberVO" items="${memberSvc.all}">
		                    <c:if test="${shopVO.member_id==memberVO.member_id}">
			                    ${memberVO.member_id}
		                    </c:if>
		                </c:forEach>
					</td>
				</tr>
	
				<tr>
					<td class="tabletitle" style="border:none">統一編號</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_tax_id()%>
					</td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">商家名稱</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_name()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">郵遞區號</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_zip_code()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">縣市</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_city()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">地址</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_address()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">緯度</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_latitude()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">經度</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_longitude()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">商家介紹</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_description()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">標籤</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_tag()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">評價</td>
					<td>
						<%= (shopVO==null)? "" : shopVO.getShop_rating()%>
					</td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">評價總數</td>
					<td>
						<%= (shopVO==null)? "" : shopVO.getShop_rating_count()%>
					</td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">評價總和</td>
					<td>
						<%= (shopVO==null)? "" : shopVO.getShop_rating_total()%>
					</td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">信箱</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_email()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">聯絡電話</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_phone()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">均消</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_price_level()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">營業時間</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_opening_time()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">網站</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_website()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">圖片</td>
					<td style="padding: 10px 0;">
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
					<td class="tabletitle" style="border:none">圖片庫</td>
					<td style="padding: 10px 0;">
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
				<tr>
					<td class="tabletitle" style="border:none">更新時間</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_update_time()%></td>
				</tr>
				<tr>
					<td class="tabletitle" style="border:none">總瀏覽數</td>
					<td>
						<%= (shopVO==null)? "" : shopVO.getShop_total_view()%>
					</td>
				</tr>
				<tr>
				<td class="tabletitle" style="border:none">預約功能</td>
					<td><%= (shopVO==null)? "" : shopVO.getShop_reserv_status()%>
					</td>
				</tr>
	
			</table>
			
			<br> 
			<div class="back" style="margin-left:20%;" height:80px;>
				<button style="border:1px solid gray; border-radius:20px; height:50px; width:150px; padding:10px;">
					<a href="${pageContext.request.contextPath}/shop/listAllShop.jsp" 
					style="text-decoration: none; font-size:15px; color: rgb(41, 41, 41);">返回商家列表</a>
				</button>
				<input type="hidden" name="action" value="details_Update_display"> 
				<input type="hidden" name="shop_id" value="<%=shopVO.getShop_id()%>">
				<input type="submit" value="修改商家資料" 
					style="margin-left: 20%; margin-bottom: 30px; border:1px solid gray; border-radius:20px;  height:50px;  width:150px; padding:10px;" >
			</div>
			
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