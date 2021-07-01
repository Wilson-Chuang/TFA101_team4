<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<%
	Set set = new LinkedHashSet();
	set.add(request.getAttribute("set"));
%>


<%
// 	ArticleService articleSvc = new ArticleService();
// 	Set<ArticleVO> set = articleSvc.getArticle_ByMember_no(1);
// 	pageContext.setAttribute("set", set);
%>


<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>發表文章</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/article/css/listArticle.css">

<script src="${pageContext.request.contextPath}/article/vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>

</head>


<body>
	<!-- 遮罩層 -->
	<div id="cover"></div>
	<!-- 彈窗 -->
	<div id="showdiv">
		<div class="showdiv_title">匯入文章</div>
		 <FORM METHOD="POST" ACTION="article.do" name="form1">
				<input type="TEXT" name="scraping_url" size="40" class="input_text" placeholder="網址"/>
				<input type="hidden" name="action" value="scraping">
				<input type="submit" value="確定" class="enter">
		</FORM>
	  <!-- 按鈕 -->
	<button class="cancel" onclick="closeWindow()">取消</button>
	</div>
<div class="article_content">


<!-- 標題 -->
    <div class="maintop_header">
        <div class="category">
            <h2>
               	List
            </h2>
        </div>
    </div>
<!-- 側邊功能列 -->

	



	<div class="nav_bar">
		<input type="button" value="返回主頁" onclick="location.href='${pageContext.request.contextPath}/article/article_homepage.jsp'">
		<input type="button" value="匯入文章" onclick="showWindow()">
		<input type="button" value="寫新食記" onclick="location.href='${pageContext.request.contextPath}/article/addArticle.jsp'">
	</div>
	
	
<!-- 您的文章 -->	
	<div class="title">您的文章</div>

<!-- 中間標籤頁 -->
    <div class="tabs-container">

        <ul class="tabs-pages">
            <li class="tab active"><a href="#draft">草稿</a></li>
            <li class="tab"><a href="#published">已發佈</a></li>
            <li class="tab"><a href="#verify">審核中</a></li>
        </ul>
	
	</div>
	
	<div class="tabs-contents">
		<!-- 草稿 -->	
		<div id="draft" class="tab-inner">
		
		<c:forEach var="articleVO" items="${set}">
		<c:if test="${articleVO.article_verify_status == 0}">
			<div class="draft_list">
		   		 <a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=getOne_For_Update">             
                  	<img src="/upload/${articleVO.article_img_name}">
                  </a>
                 <a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=getOne_For_Update">  
				 	<div class="article_title">${articleVO.article_title}</div>
				 </a>
				 <div class="draft_title">(草稿)</div>
				 <div class="article_create_time">
				 	${articleVO.article_create_time}				 	
				 	&nbsp;◆&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;◆&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;◆
				 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;◆
				 	<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do">
						<input type="submit" value="編輯" class="edit">
						<input type="hidden" name="article_no" value="${articleVO.article_no}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>	
				 	
		 			<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do">
    					<input type="submit" value="刪除" class="delete" onclick="javascript:return del();">
     					<input type="hidden" name="article_no"  value="${articleVO.article_no}">
     					<input type="hidden" name="action" value="delete">
	     			</FORM>
	     			
     				<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do">
							<input type="submit" value="預覽" class="view">
							<input type="hidden" name="article_no" value="${articleVO.article_no}">
							<input type="hidden" name="action" value="view">
					</FORM>		
	     			
	     			<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do">
    					<input type="submit" value="送審" class="submit" onclick="javascript:return sub();">
     					<input type="hidden" name="article_no"  value="${articleVO.article_no}">
     					<input type="hidden" name="action" value="update_verify_status">
	     			</FORM>		
				 </div>
				 <table class="article_content">
				 	<tr>
				 		<td width="200px">
				 			---------------------
				 		</td>
				 	</tr>
				 </table>
			</div>
		</c:if>
		</c:forEach>
		</div>
		
		
		<!-- 已發佈 -->	
		<div id="published" class="tab-inner">
		
		
		<c:forEach var="articleVO" items="${set}">
		<c:if test="${articleVO.article_status == 1}">
			<div class="draft_list">
		   		 <a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=see_article">            
                 	 <img src="/upload/${articleVO.article_img_name}">    
                  </a>
                 <a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=see_article">
					<div class="article_title">${articleVO.article_title}</div>
				 </a>
				 <div class="draft_title">(公開中)</div>
				 <div class="favorite_rate"><i class="far fa-heart"></i>&nbsp;收藏:&nbsp;${articleVO.article_collection}次</div>
				 <div class="click_rate"><i class="far fa-eye"></i>&nbsp;人氣:&nbsp;1233次</div>
			</div>		
		</c:if>
		</c:forEach>	
		</div>
		
		
		<!-- 審核中 -->	
		<div id="verify" class="tab-inner">
		
		
		<c:forEach var="articleVO" items="${set}">
		<c:if test="${articleVO.article_verify_status == 1 && articleVO.article_status == 0}">
			<div class="draft_list">
		   		 <a href="#">             
                  	<img src="/upload/${articleVO.article_img_name}">        
                  </a>
				 <div class="article_title">${articleVO.article_title}</div>
				 <div class="draft_title">(審核中)</div>
				 <div class="cancel_varify">
				 
					 <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do">
					 	<input type="submit" value="◆&nbsp;取消審核" onclick="javascript:return cancel();">	
					 	<input type="hidden" name="article_no"  value="${articleVO.article_no}">
	     				<input type="hidden" name="action" value="cancel_verify_status">	
	     			</FORM>
	     			<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do">
						<input type="submit" value="◆&nbsp;預覽" class="verify_view">
						<input type="hidden" name="article_no" value="${articleVO.article_no}">
						<input type="hidden" name="action" value="view">
					</FORM>	
				 </div>
			</div>
		</c:if>
		</c:forEach>
		</div>
	</div>
</div>     	



<script>
function del() {
	var msg = "您真的確定要刪除嗎？\n\n請確認！";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}
</script>

<script>
function sub() {
	var msg = "確定要送審了嗎？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}
</script>

<script>
function cancel() {
	var msg = "確定要取消審核嗎？";
	if (confirm(msg)==true){
	return true;
	}else{
	return false;
	}
}


</script>

<script type="text/javascript">
  // 彈窗
  function showWindow() {
    $('#showdiv').show();  //顯示彈窗
    $('#cover').css('display','block'); //顯示遮罩層
    $('#cover').css('height',document.body.clientHeight+'px'); //設定遮罩層的高度為當前頁面高度
  }
  // 關閉彈窗
  function closeWindow() {
    $('#showdiv').hide();  //隱藏彈窗
    $('#cover').css('display','none');   //顯示遮罩層
  }
</script>

<script src="${pageContext.request.contextPath}/article/js/listArticle.js"></script>
</body>
</html>
