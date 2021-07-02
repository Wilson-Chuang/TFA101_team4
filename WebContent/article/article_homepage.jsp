<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.article_category.model.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.vote.model.*"%>
<%@ page import="java.util.* , shopping.product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
	ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>

<%
	Long addResult = (Long) request.getAttribute("addResult");
%>

<!-- 哈囉我是亂碼嗎 -->
<%
	Article_categoryService article_categorySvc1 = new Article_categoryService();
	Set<ArticleVO> set = article_categorySvc1.getArticlesByariticle_category_no(1);
	pageContext.setAttribute("set", set);
%>


<%
	Article_categoryService article_categorySvc3 = new Article_categoryService();
	Set<ArticleVO> set3 = article_categorySvc3.getArticlesByariticle_category_no(3);
	pageContext.setAttribute("set3", set3);
%>

<%
	Article_categoryService article_categorySvc4 = new Article_categoryService();
	Set<ArticleVO> set4 = article_categorySvc4.getArticlesByariticle_category_no(4);
	pageContext.setAttribute("set4", set4);
%>


<%
	Product_categoryService product_categorySvc2 = new Product_categoryService();
	Set<ProductVO> set2 = product_categorySvc2.getProductsByproduct_category_no(2);
	pageContext.setAttribute("set2", set2);
%>

<%

	MemberVO MemberVO2 = (MemberVO) session.getAttribute("login");
	pageContext.setAttribute("member_login", MemberVO2);
	if( MemberVO2 !=null){
		MemberService memberSvc = new MemberService();
		MemberVO memberVO = memberSvc.GET_ONE_BY_ID(MemberVO2.getMember_id());
		pageContext.setAttribute("memberVO", memberVO);
	}

%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>文章首頁</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/article/css/article_homepage.css">
<script src='https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sticky-sidebar/3.3.1/sticky-sidebar.min.js"></script>
</head>
<body>
<button class="js-back-to-top back-to-top" >&#65085; <p>TOP</p></button>


<div class="maintop">
    <div class="maintop_header">
<!-- Hot Now標題 -->
        <div class="category">
            <h2>
                Hot Now
            </h2>
        </div>
    </div>
<!-- 首頁最佳作家 -->
    <div class="maintop_slider">
        <a href="#"><img src="${pageContext.request.contextPath}/article/img/model.png"></a>
    </div>
    <div class="maintop_slider_text">
        <p>BEST AUTHOR</p>
        <h2>GuideFood 7月號封面，食尚作家</h2>
        <p>By Hank Wu, 2021/7/6</p>
    </div>

    <div style="height: 75px;"></div>
</div>


<!-- 導航欄 -->
 <div class="banner">
     <p><a href="${pageContext.request.contextPath}/article/addArticle.jsp">發表文章</a></p>
<%--          <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do" style="display:none;"> --%>
<!-- 	    	<input type="submit" class="article_post" value="發表文章" style="display:none;" id="img-upload"> -->
<%-- 	    	<input type="hidden" name="member_no"  value="${memberVO.member_id}"> --%>
<!-- 			<input type="hidden" name="action" value="post"> -->
<!--     	</FORM>	 -->
     <p><a href="${pageContext.request.contextPath}/article/article_list1.jsp">系列專欄</a></p>
     <p><a href="${pageContext.request.contextPath}/article/article.do?action=vote&author_no=11">活動投票</a></p>
     <p><a href="${pageContext.request.contextPath}/product_list/product_homePage.jsp">積分商城</a></p>
 </div>

<!-- Attention標題 -->
<div class="maintop">
    <div class="maintop_header">
        <div class="category">
            <h2>
                Attention
            </h2>
        </div>
    </div>
</div>

<div class="maintop">
     <div class="attention">
     
    <c:forEach var="articleVO" items="${set3}" begin="0" end ="3">
	<c:if test="${articleVO.article_status == 1}">
         <a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=see_article">
             <div class="content_short">
                 <img src="/upload/${articleVO.article_img_name}">
                 <div class="info_short">
                     <p>
                     	<c:forEach var="article_categoryVO" items="${article_categorySvc.all}">
			         		<c:if test="${articleVO.category_no==article_categoryVO.article_category_no}">
			         			${article_categoryVO.article_category_name}
			         		</c:if>
			     		</c:forEach> 
                     
                     </p>
                     <h2>${articleVO.article_title}</h2>
                     <div></div>
                      <p>By 	<c:forEach var="memberVO" items="${memberSvc.all}">
	                    		<c:if test="${articleVO.member_no==memberVO.member_id}">
		                   			${memberVO.member_name}
	                    		</c:if>
                			 </c:forEach>  
			     		,  <fmt:formatDate value="${articleVO.article_create_time}" pattern="yyyy/MM/dd"/></p>
                 </div>
             </div>
         </a>
    </c:if>
	</c:forEach>  
        
     </div>
           <div class="see_more"><a href="${pageContext.request.contextPath}/article/article_list1.jsp">Discover More</a></div>
 </div>
 
<div style="height: 100px; border-bottom: 1px black solid;"></div>

<!-- New類別 -->
	<div class="maintop">
	    <div class="maintop_header">
	        <div class="category">
	            <h2>
	                New
	            </h2>
	        </div>
	    </div>
	</div>

<!-- New內容 -->
<div id="main-content" class="main">
    <div id="sidebar" class="sidebar1">
        <a href="">
            <div class="sidebar__inner">
                <div class="sidebar_newopen">
                    <p>NEW RESTAURANT</p>
                    <h2>【新店專訪】日本六本木高人氣鬆餅店，BITE2即將於台北京站店盛大開幕</h2>
                    <p>By Hank Wu, 2021/7/4</p>
                </div>
            </div>
        </a>
    </div>
    
    
    <div id="content" class="content1">
    
	
	<c:forEach var="articleVO" items="${set4}" begin="0" end ="9">
	<c:if test="${articleVO.article_status == 1}">
		<a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=see_article">
             <div class="content_short">
                 <img src="/upload/${articleVO.article_img_name}">
                 <div class="info_short">
                     <p>
	                     <c:forEach var="article_categoryVO" items="${article_categorySvc.all}">
			         		<c:if test="${articleVO.category_no==article_categoryVO.article_category_no}">
			         			${article_categoryVO.article_category_name}
			         		</c:if>
			     		</c:forEach>                    
                     </p>
                     <h2>${articleVO.article_title}</h2>
                     <div></div>
                     <p>By 	<c:forEach var="memberVO" items="${memberSvc.all}">
	                    		<c:if test="${articleVO.member_no==memberVO.member_id}">
		                   			${memberVO.member_name}
	                    		</c:if>
                			 </c:forEach>  
			     		,  <fmt:formatDate value="${articleVO.article_create_time}" pattern="yyyy/MM/dd"/></p>
                    
                 </div>
             </div>
         </a>
 	</c:if>
	</c:forEach>
  
    </div>
</div>


	<div class="banner3">
	      <h1>"I always want only two things: lose weight & eat."</h1>
	      <h2>"I'm not hungry. But I am bored. Therefore I shall eat."</h2>
	      <h5>"There's no we in food."</h5>
	      <h3>"When someone says "I forgot to eat", I think they must be a special kind of stupid. I plan my whole day
	          around eating."</h3>
	      <h6>"I would lose weight, but I hate losing."</h6>
	      <h4>"I'm into fitness. Fitness whole pizz into my mouth."</h4>
	</div>

<!-- Unboxing類別 -->
	<div class="maintop">
	    <div class="maintop_header">
	        <div class="category">
	            <h2>
	                Unboxing
	            </h2>
	        </div>
	    </div>
	</div>



<!-- Unboxing內容 -->
<div id="main-content2" class="main">
    <div id="sidebar2" class="sidebar">
        <a href="">
            <div class="sidebar__inner2">
                <div class="sidebar_unboxing">
                    <p>NEW RESTAURANT</p>
                    <h2>【美食開箱】來自義大利米其林三星名廚來台展店，最道地的PASTA美食</h2>
                    <p>By Hank Wu, 2021/7/4</p>
                </div>
            </div>
        </a>
    </div>
    
    
    <div id="content2" class="content1">
    
    
	<c:forEach var="articleVO" items="${set}" begin="0" end ="9">
	<c:if test="${articleVO.article_status == 1}">
		<a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=see_article">
             <div class="content_short">
                 <img src="/upload/${articleVO.article_img_name}">
                 <div class="info_short">
                     <p>
	                     <c:forEach var="article_categoryVO" items="${article_categorySvc.all}">
			         		<c:if test="${articleVO.category_no==article_categoryVO.article_category_no}">
			         			${article_categoryVO.article_category_name}
			         		</c:if>
			     		</c:forEach>                    
                     </p>
                     <h2>${articleVO.article_title}</h2>
                     <div></div>
                     <p>By 	<c:forEach var="memberVO" items="${memberSvc.all}">
	                    		<c:if test="${articleVO.member_no==memberVO.member_id}">
		                   			${memberVO.member_name}
	                    		</c:if>
                			 </c:forEach>  
			     		,  <fmt:formatDate value="${articleVO.article_create_time}" pattern="yyyy/MM/dd"/></p>
                    
                 </div>
             </div>
         </a>
 	</c:if>
	</c:forEach>
     
    </div>
</div>
<div style="height: 100px; border-bottom: 1px black solid;"></div>

<!-- 作家專欄 title-->
	<div class="maintop">
	     <div class="maintop_header">
	         <div class="category">
	             <h2>
	                 Authors
	             </h2>
	         </div>
	     </div>
	 </div>
 
<!--  投票標題 -->
 
 <div class="vote_title">【票選你心目中最佳作家】</div>
 
<!--  作家 -->
 	<div class="author_content">
        <div class="author_intro">
            <a href=""><img src="./img/model1.jpeg" alt=""></a>
            <div class="author_intro_text"><a href="">Saria專欄</a>
           
	             <FORM METHOD="GET" ACTION="article.do" name="form1">
				 	 <input type="hidden" name="action" value="vote">
					 <input type="hidden" name="author_no" value="1">
				     <input type="submit" value="投票"> 
				</FORM>
			
			
            </div>
        </div>
        <div class="author_intro">
            <a href=""><img src="./img/model2.jpeg" alt=""></a>
            <div class="author_intro_text"><a href="">唐熒霜專欄</a>
	             <FORM METHOD="GET" ACTION="article.do" name="form1">
				 	 <input type="hidden" name="action" value="vote">
					 <input type="hidden" name="author_no" value="2">
				     <input type="submit" value="投票"> 
				</FORM>
            
            
            </div>
        </div>
        <div class="author_intro">
            <a href=""><img src="./img/model4.jpeg" alt=""></a>
            <div class="author_intro_text"><a href="">喬夏專欄</a>
	             <FORM METHOD="GET" ACTION="article.do" name="form1">
				 	 <input type="hidden" name="action" value="vote">
					 <input type="hidden" name="author_no" value="3">
				     <input type="submit" value="投票"> 
				</FORM>
            
            </div>
        </div>
        <div class="author_intro">
            <a href=""><img src="./img/model7.jpeg" alt=""></a>
            <div class="author_intro_text"><a href="">Passion Tsai專欄</a>
	             <FORM METHOD="GET" ACTION="article.do" name="form1">
				 	 <input type="hidden" name="action" value="vote">
					 <input type="hidden" name="author_no" value="4">
				     <input type="submit" value="投票"> 
				</FORM>
            
            </div>
        </div>
        <div class="author_intro">
            <a href=""><img src="./img/model8.jpeg" alt=""></a>
            <div class="author_intro_text"><a href="">Tomo專欄</a>
	            <FORM METHOD="GET" ACTION="article.do" name="form1">
				 	 <input type="hidden" name="action" value="vote">
					 <input type="hidden" name="author_no" value="5">
				     <input type="submit" value="投票"> 
				</FORM>
            
            </div>
        </div>
        <div class="author_intro">
            <a href=""><img src="./img/model9.jpeg" alt=""></a>
            <div class="author_intro_text"><a href="">Leslie專欄</a>
	            <FORM METHOD="GET" ACTION="article.do" name="form1">
				 	 <input type="hidden" name="action" value="vote">
					 <input type="hidden" name="author_no" value="6">
				     <input type="submit" value="投票"> 
				</FORM>
            
            </div>
        </div>
	  
    </div>
    <FORM METHOD="GET" ACTION="article.do" name="form1">
    <div class="see_more"><a href="${pageContext.request.contextPath}/article/article.do?action=vote&author_no=8">Vote Now!</a></div>
    </FORM>
    <div class="banner2"></div>

<!-- 商城 title-->
	<div class="maintop">
	     <div class="maintop_header">
	         <div class="category">
	             <h2>
	                 Shopping
	             </h2>
	         </div>
	     </div>
	 </div>



      <div class="slider_body">
            <a href="javascript:;" class="dIcon next"></a>
            <a href="javascript:;" class="dIcon prev"></a>
            <div class="slider">
                <ul class="list">
   
         <c:forEach var="productVO" items="${set2}">

                    <li>    
                        <div class="hot_sales_product">              
                        <a href="${pageContext.request.contextPath}/product/product.do?product_no=${productVO.product_no}&action=getOne_For_Display">
                        <img src="/upload/${productVO.product_img_name}">
                        </a>

          		
                        <div class="hot_sales_product_info">
                                <h2>${productVO.product_name}</h2>
                        </div>
                        </div>                      
                    </li>                            
         </c:forEach>
                </ul>
            </div>
        </div>
		
		<div class="see_more">
			<a href="${pageContext.request.contextPath}/product_list/product_homePage.jsp">
				GO SHOPPING
			</a>
		</div>
		
<script src="${pageContext.request.contextPath}/product_list/vendors/jquery/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/article/js/article_homepage.js"></script>
</body>
</html>
