<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop.model.*"%>

<%
	ShopVO shopVO = (ShopVO) request.getAttribute("shopVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商家資料新增 - addShop.jsp</title>

<style>
  table {
	width: 950px;
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
      z-index: 1;
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

		<h3>商家資料新增</h3>
		
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color:red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		
		<form action="${pageContext.request.contextPath}/manager/cmsMember.do" class="personal_form" method="post"  enctype="multipart/form-data">
			<table>
                   <jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
					<tr style="margin-bottom:20px;">
						<td width="150px">會員編號<font color=red><b>*</b></font></td>
						<td><select size="1" name="member_id">
							<c:forEach var="memberVO" items="${memberSvc.all}">
								<option value="${memberVO.member_id}" ${(shopVO.member_id==memberVO.member_id)? 'selected':'' } >${memberVO.member_id}
							</c:forEach>
						</select></td>
					</tr><br>
                    <tr>
                    	<td>商家名稱</td>		
                    	<td><label for=""><input type="text" name="Shop_name" required style="width:400px; font-size:16px;"></label></td>
                    </tr>
                    
                     <tr>
                    	<td>統一編號</td>		
                    	<td><label for=""><input type="text" name="Shop_tax_id" style="width:400px;font-size:16px;" maxlength="8" minlength="8" onkeyup="value=value.replace(/[^(\d)]/g,'')" required></label></td>
                    </tr>
                    
                     <tr>
                    	<td>郵遞區號</td>		
                    	<td><label for=""><input type="text" name="Shop_zip_code" style="width:400px;font-size:16px;" onkeyup="value=value.replace(/[^(\d)]/g,'')" required></label></td>
                    </tr>	
                    
                    <tr>
                    	<td>地址</td>		
                    	<td><label for=""><input type="text" name="address" style="width:400px;font-size:16px;" required></label></td>
                    </tr>	
                    
                    <tr>
                    	<td>縣市</td>		
                    	<td><label for=""><input type="text" name="SHOP_CITY" style="width:400px;font-size:16px;"></label></td>
                    </tr>
                    
                    <tr>
                    	<td>商家所在地-經度</td>		
                    	<td><label for=""><input type="text" name="Shop_latitude" style="width:400px;font-size:16px;" maxlength="10" onkeyup="value=value.replace(/[^(\-|\+)?\d+(\.\d+)?$]/g,'.')" required></label></td>
                    </tr>
                    
                    <tr>
                    	<td>商家所在地-緯度</td>		
                    	<td><label for=""><input type="text" name="Shop_longitude" style="width:400px;font-size:16px;" maxlength="10" onkeyup="value=value.replace(/[^(\-|\+)?\d+(\.\d+)?$]/g,'.')" required></label></td>
                    </tr>
                    
                    <tr>
                    	<td>商家電話</td>		
                    	<td><label for=""><input type="text" name="Shop_phone"style="width:400px;font-size:16px;"  maxlength="10" onkeyup="value=value.replace(/[^(\d)]/g,'')" required></label></td>
                    </tr>
                    
                    <tr>
                    	<td>商家信箱</td>		
                    	<td><label for=""><input type="email" name="Shop_email" style="width:400px;font-size:16px;" ></label></td>
                    </tr>
                    
                    <tr>
                    	<td>商家介紹</td>		
                    	<td><label for=""><textarea name="shop_description" style="width:400px;font-size:16px;" required></textarea></label></td>
                    </tr>
                    
                    <tr>
                    	<td>商家標籤</td>		
                    	<td><label for=""><textarea name="Shop_tag" style="width:400px;font-size:16px;" placeholder="多個標籤請用空格分隔"></textarea></label></td>
                    </tr>
                    
                    <tr>
                    	<td>商家均消</td>		
                    	<td><label for=""><select name="Shop_price_level"><option value=0>不選擇</option><option value=1>$150以下</option><option value=2>$150~$300</option><option value=3>$300~$600</option><option value=4>$600以上</option></select></label></td>
                    </tr>
                    
                    <tr>
                    	<td>營業時間</td>		
                    	<td><label for=""><input type="text" name="Shop_opening_time" style="width:400px;font-size:16px;"></label></td>
                    </tr>
                    
                    <tr>
                    	<td>商家網頁</td>		
                    	<td><label for=""><input type="text" name="Shop_website" style="width:400px;font-size:16px;"></label></td>
                    </tr>
                    
                    <tr>
                    	<td>上傳封面</td>		
                    	<td><label style="border:1px solid black;border-radius:10px;padding:5px;font-size:16px;">請選擇檔案<input style="display:none" type="file" name="SHOP_MAIN_IMG" ></label></td>
                    </tr>
                    
                    <tr>
                    	<td>上傳圖片庫</td>		
                    	<td><label style="border:1px solid black;border-radius:10px;padding:5px;font-size:16px;">請選擇檔案<input style="display:none" type="file" name="SHOP_GALLERY" multiple></label></td>
                    </tr>
                    
                    <tr>
                    	<td>訂位開放狀態</td>		
                    	<td>
	                    	<label for=""><select name="Shop_reservation_status">
	                            <option value=1 selected=selected >開放</option>
	                            <option value=2 >未開放</option>
	                  		 </select></label>
                  		 </td>
                    </tr>
          
          <table>
                    
                    <input type="hidden" name="action" value="insert_shop">
                    <input type="submit" value="儲存" class="save_btn" style="border:1px solid gray; border-radius:20px; height:40px; width:150px; padding:10px;">
			              
        </form>
	</div>
</div>

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Timestamp shop_create_time = null;
  try {
	  shop_create_time = shopVO.getShop_create_time();
   } catch (Exception e) {
	   shop_create_time = new java.sql.Timestamp(System.currentTimeMillis());
   }
  
  java.sql.Timestamp shop_update_time = null;
  try {
	  shop_update_time = shopVO.getShop_update_time();
   } catch (Exception e) {
	   shop_update_time = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>
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

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=shop_create_time%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $('#f_date2').datetimepicker({
 	       theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=shop_update_time%>', // value:   new Date(),
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
         
         $("#address").focusout(function() {
        	 if ($(this).val().length >= 3)
             {
                 $.ajax({
                     type: "post",
                     dataType: "json",
                     url: "https://maps.googleapis.com/maps/api/geocode/json?address=" + $(this).val() + 
                    		 "&region=tw&language=zh-TW&key=AIzaSyDwNdeufZ5kVv0nhwFe9Y4dukeonaUO6Ac",
                     success: function (data)
                     {
                         if (data.status == "OK")
                         {
                        	 $("#lat").attr("value", data.results[0].geometry.location.lat);
                        	 $("#lng").attr("value", data.results[0].geometry.location.lng);
                        	 let address = data.results[0].address_components;
                        	 $("#postal").val(data.results[0].address_components[address.length -1].long_name);
                         }
                         else
                         {
                             alert("資料錯誤");
                             document.getElementById('#address').focus();
                         }
                     },
                     error: function ()
                     {
                         alert("資料錯誤");
                         document.getElementById('#address').focus();
                     }
                 });
             }
             else
             {
                 alert("請輸入正確地址");
                 document.getElementById('#address').focus();
             }
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
</html>