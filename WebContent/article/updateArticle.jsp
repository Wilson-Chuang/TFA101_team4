<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.member.model.*"%>
<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>
<%
	MemberVO MemberVO2 = (MemberVO) session.getAttribute("login");
	MemberService memberSvc = new MemberService();
	MemberVO memberVO = memberSvc.GET_ONE_BY_ID(MemberVO2.getMember_id());
	pageContext.setAttribute("memberVO", memberVO);
%>



<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>發表文章</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/article/css/addArticle.css">
<script src="<%=request.getContextPath()%>/ckeditor4_hank_for_article/ckeditor/ckeditor.js"></script><!-- ckeditor -->
<script src="<%=request.getContextPath()%>/ckeditor4_hank_for_article/ckeditor/config.js"></script><!-- ckeditor -->

<script src="${pageContext.request.contextPath}/article/vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>

</head>


<body>

<FORM METHOD="POST" ACTION="article.do" name="form1" enctype="multipart/form-data" target="nm_iframe">
<div class="article_content">


<!-- 標題 -->
    <div class="maintop_header">
        <div class="category">
            <h2>
               	Edit
            </h2>
        </div>
    </div>


<!-- 作家資訊 -->
	<div class="author">
		<table>
			<tr>
				<th width="180px">
					<img src="${pageContext.request.contextPath}/article/img/model8.jpeg">
				</th>
				<th>
					${memberVO.member_name}<br><br>
					<font class="rule">*提醒您：關於積分規則，詳情請參考 「食記發表規則」</font>
				</th>
			</tr>
		</table>
		
	</div>
	

	
<!-- 文章標題 -->
	<div class="article_title">
	
	<input type="TEXT" name="article_title" size="40" class="article_title_input" placeholder="文章標題"
							value="<%=(articleVO == null) ? "" : articleVO.getArticle_title()%>" />
	
	</div>
	
	

<!-- 類別選擇 -->

	<div class="article_category">
	類型:
	<jsp:useBean id="article_categorySvc" scope="page" class="com.article_category.model.Article_categoryService" />
	<select size="1" name="category_no" class="select">
		<c:forEach var="article_categoryVO" items="${article_categorySvc.all}">
			<option value="${article_categoryVO.article_category_no}"
				${(articleVO.category_no==article_categoryVO.article_category_no)? 'selected':'' }>
				${article_categoryVO.article_category_name}
		</c:forEach>
	</select>
	</div>
	
	
	
<!-- 文章撰寫 -->
	 <div class="article_ckeditor">
 		<textarea class="ckeditor" id="myContent" name="article_content">${articleVO.article_content}</textarea>
 	 </div>



<!-- 上傳封面 -->	
		<div class="article_img_upload">
			<ul class="picture_list"></ul>
				<input type="file" name="article_img" id="img-upload">
			<a href="javascript:void(0)" id="my-img"><i class="fas fa-camera"></i></a>
		<div class="add_title">*封面圖</div>				
		</div>

		
<!-- 指定餐廳 -->

	<div class="pick_restaurant">
	<jsp:useBean id="shopSvc" scope="page" class="com.shop.model.ShopService" />
		指定餐廳:<input list="shop_no" name="shop_no"/>		
		<datalist id="shop_no">
		<c:forEach var="shopVO" items="${shopSvc.all}">
		    <option value="${shopVO.shop_id}"
				${(articleVO.shop_no==shopVO.shop_id)? 'selected':'' }>
				${shopVO.shop_name}
		</c:forEach>    
		</datalist>
	</div>

<!-- 	<div class="pick_restaurant"> -->
<!-- 		指定餐廳: <input list="ice-cream-flavors" name="shop_no"/>		 -->
<!-- 		<datalist id="ice-cream-flavors"> -->
<!-- 		    <option value="三芝尚讚豆腐乳雞排"> -->
<!-- 		    <option value="福伯黃金雞"> -->
<!-- 		    <option value="食家個人土雞鍋"> -->
<!-- 		    <option value="養鍋 Yang Guo 石頭涮涮鍋 台南文化店"> -->
<!-- 		    <option value="音樂廚房之麥迪遜"> -->
<!-- 		</datalist> -->
<!-- 	</div> -->
<!-- 返回 -->
	<div class="back">
		<a href="${pageContext.request.contextPath}/article/article.do?member_no=${memberVO.member_id}&action=post">-回到文章列表-</a>
	</div>

<!-- 送出 -->
	<div class="input_button">
		 <input type="hidden" name="article_no" value="<%=articleVO.getArticle_no()%>">
		 <input type="hidden" name="action" value="update">
	     <input type="submit" value="存檔" class="save" id="s1" onclick="changeValue();"> 
	</div>
</div>     
</FORM>
<iframe id="nm_iframe" name="nm_iframe" style="display:none;" ></iframe>

<script>
function changeValue(){
    var btnVal=document.getElementById("s1");
    if(btnVal.value=="存檔")
    {
        btnVal.value="*已存檔";
    }
    else
    alert("可以返回列表查看囉!!");
}

        var isCommitted = false;//表單是否已經提交標識，默認爲false
        function dosubmit(){
           if(isCommitted==false){
               isCommitted = true;//提交表單後，將表單是否已經提交標識設置爲true
                return true;//返回true讓表單正常提交
            }else{
               return false;//返回false那麼表單將不提交
            }
       }

</script>

<script src="${pageContext.request.contextPath}/article/js/addArticle.js"></script>
<script>
//重新整理頁面
// window.onload = function() {
//   	  document.getElementById("nm_iframe").onload = function() {  	
//   		  	location.reload(true);  
//   	  }
// }

</script>
</body>
</html>
