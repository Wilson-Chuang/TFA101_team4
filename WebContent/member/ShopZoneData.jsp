<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.member_follower.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/pages/header.file" %>


<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	MemberVO MemberVO = (MemberVO) session.getAttribute("login");
	MemberService memSvc = new MemberService();
	ShopVO ShopVO = memSvc.GET_ONE_BY_MEMBER(MemberVO.getMember_id());
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|修改個人資料</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/bootstrap-icons.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wrunner-default-theme.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">

</head>
<body>
<div class="container">
        
            

        <div class="row">

            <div class="col-2">
                
            </div>
            <div class="col-10">
                <form action="member.html"  method="post"  enctype="multipart/form-data">
                	<label for="">店家編號<br><input type=text value="<%=ShopVO.getShop_id()%>" name="SHOP_ID"></label><br>
                	<input type=hidden value="<%=MemberVO.getMember_email() %>" name="MEMBER_EMAIL">
                	<input type=hidden value="<%=ShopVO.getShop_main_img() %>" name="filename">
                    <label for="">店名<br><input type="text" name="SHOP_NAME" value="<%=ShopVO.getShop_name() %>"></label><br>
                    <label for="">均消<br><input type="text" name="SHOP_PRICE_LEVEL"  value="<%=ShopVO.getShop_price_level() %>"></label><br>
                    <label for="">營業時間<br><input type="text" name="SHOP_OPENING_TIME"  value="<%=ShopVO.getShop_opening_time() %>"></label><br>
                     <%
                    String address=ShopVO.getShop_address();
                    String[] add=address.split("-");
      
                    %>
                    <label for="">地址<br>
                    <select id="city-list" name="city" onchange="changeCity(this.selectedIndex)"></select>
                    <select id="county-list" name="county" ></select>
                    <input type="text" name="address" value="<%=add[2] %>"></label><br>
                    <label for="">縣市<br><input type="text" name="SHOP_CITY" value="<%=ShopVO.getShop_city() %>"></label><br>
                    <label for="">粉絲專頁<br><input type="text" name="SHOP_WEBSITE" value="<%=ShopVO.getShop_website() %>"></label><br>
                    <label for="">電話<br><input type="text" name="SHOP_PHONE" value="<%=ShopVO.getShop_phone() %>"></label><br>
                    <label for="">信箱<br><input type="email" name="SHOP_EMAIL" value="<%=ShopVO.getShop_email() %>"></label><br>
                    <label for="">介紹<br><input type="text" name="SHOP_DESC" value="<%=ShopVO.getShop_description() %>"></label><br>
                    <label for="">標籤<br><input type="text" name="SHOP_TAG" value="<%=ShopVO.getShop_tag() %>"></label><br>
                    <label style="border:1px solid black;border-radius:5px;">上傳封面<input style="display:none" type="file" name="SHOP_MAIN_IMG" value="/upload/<%=ShopVO.getShop_main_img() %>"></label><br>                
                    <label style="border:1px solid black;border-radius:5px;">上傳圖片庫<input style="display:none" type="file" name="SHOP_GALLERY" value="/upload/<%=ShopVO.getShop_gallery() %>" multiple></label><br>                
                    <input type="hidden" name="action" value="update_shop">
                    <input type="submit" value="儲存" class="save_btn" style="width:150px">
                </form>
            </div>
        </div>
    </div>

</body>
<script type="text/javascript">
	var city=['臺北市-', '新北市-','基隆市-' ,'桃園市-' , '新竹市-', '新竹縣-', '苗栗縣-',
		'臺中市-', '彰化縣-', '南投縣-', '雲林縣-', '嘉義市-', '嘉義縣-', '臺南市-', '高雄市-', '屏東縣-',
		'宜蘭縣-', '花蓮縣-', '臺東縣-', '澎湖縣-', '金門縣-', '連江縣-',];
	var citySelect=document.getElementById("city-list");
	var inner="";
	for(var i=0;i<city.length;i++){
// 	console.log(city[i]);
<%-- 	console.log('<%=add[0]%>-'); --%>
<%-- 	console.log(city[i]=='<%=add[0]%>-'); --%>
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
			console.log(county[index][i]);
			console.log('<%=add[1]%>-');
			console.log(county[index][i]=='<%=add[1]%>-');
			
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
</html>