<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.article_category.model.*"%>
<%@ page import="java.util.*"%>

<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<%
	Article_categoryService article_categorySvc1 = new Article_categoryService();
	Set<ArticleVO> set = article_categorySvc1.getArticlesByariticle_category_no(1);
	pageContext.setAttribute("set", set);
%>


<jsp:useBean id="article_categorySvc" scope="page" class="com.article_category.model.Article_categoryService" />
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Guide好食-文章列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/article/css/article_list.css">
<script src="${pageContext.request.contextPath}/article/vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>


</head>
<body>
<div class="article_content">
<!-- 標題 -->
    <div class="maintop_header">
        <div class="category">
            <h2>
               	Column
            </h2>
        </div>
    </div>
    
<!-- 分類 -->
    <div class="article_category">
        <a href="${pageContext.request.contextPath}/article/article_homepage.jsp"><div class="article_category_tag">專欄首頁</div></a>
    	<a href="${pageContext.request.contextPath}/article/article_list1.jsp"><div class="article_category_tag" style="color:black; border-left:5px solid #ef6e51; transform: scale(1.2); margin-right:30px;" >美食開箱</div></a>
    	<a href="${pageContext.request.contextPath}/article/article_list2.jsp"><div class="article_category_tag">推薦美食</div></a>
    	<a href="${pageContext.request.contextPath}/article/article_list3.jsp"><div class="article_category_tag">餐廳介紹</div></a>
    	<a href="${pageContext.request.contextPath}/article/article_list4.jsp"><div class="article_category_tag">新店快報</div></a>
    	
    	<i class="fas fa-pen-square"></i>
		<a class="apost" href="${pageContext.request.contextPath}/article/addArticle.jsp">發表文章</a>
    </div>
    
<!-- 分類標題   -->
<!--     <div class="category_title"> -->
<!--     	老饕/美食開箱 -->
<!--     </div> -->
    
<!-- 文章列表   -->
	
	<c:forEach var="articleVO" items="${set}">
	<c:if test="${articleVO.article_status == 1}">
	<div class="article_list">
	
		<a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=see_article">
			<img src="/upload/${articleVO.article_img_name}">
		</a>
		
		<div class="article_title">
		<a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=see_article">
			${articleVO.article_title}
		</a>
		</div>
		<div class="article_create_time">
			<span style="color:rgb(0, 143, 209);">
			<c:forEach var="article_categoryVO" items="${article_categorySvc.all}">
         		<c:if test="${articleVO.category_no==article_categoryVO.article_category_no}">
         			${article_categoryVO.article_category_name}
         		</c:if>
     		</c:forEach>
			</span>
			&nbsp; &nbsp; ${articleVO.article_create_time}
		</div>
		<table class="article_info">
			<tr>
				<td>
				<a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=see_article">
				
					${articleVO.article_content}
					
				</a>
				</td>
			</tr>
		</table>
		
		<div class="author">
			<img src="./img/model8.jpeg">
			<div class="member_name">
				<c:forEach var="memberVO" items="${memberSvc.all}">
		       		<c:if test="${articleVO.member_no==memberVO.member_id}">
		       			${memberVO.member_name}
		       		</c:if>
			     </c:forEach> 	
			</div>
			<div class="article_collection"><i class="far fa-heart"></i>${articleVO.article_collection}</div>
			<div class="article_view_count"><i class="fas fa-chart-line"></i>999</div>
		</div>
		
	</div>
	</c:if>
	</c:forEach>
	
	

</div>
<script src="${pageContext.request.contextPath}/article/js/article_list.js"></script>
</body>
</html>