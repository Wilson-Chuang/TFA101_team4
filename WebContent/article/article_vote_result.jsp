<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.article_category.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.vote.model.*"%>
<%@ page import="java.util.* , shopping.product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%
	Long addResult = (Long) request.getAttribute("addResult");
	int author_1_score = (int)request.getAttribute("author_1_score");
	int author_2_score = (int)request.getAttribute("author_2_score");
	int author_3_score = (int)request.getAttribute("author_3_score");
	int author_4_score = (int)request.getAttribute("author_4_score");
	int author_5_score = (int)request.getAttribute("author_5_score");
	int author_6_score = (int)request.getAttribute("author_6_score");
	int author_8_score = (int)request.getAttribute("author_8_score");
	int author_9_score = (int)request.getAttribute("author_9_score");
	int author_10_score = (int)request.getAttribute("author_10_score");
	int author_11_score = (int)request.getAttribute("author_11_score");
	int author_12_score = (int)request.getAttribute("author_12_score");
	int author_13_score = (int)request.getAttribute("author_13_score");
	
	
%>

<%
	List voteList = (List) request.getAttribute("voteList");
%>
<html>
<head>
 <title>投票結果</title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/article/css/article_vote_result.css">
 <script src="<%=request.getContextPath()%>/article/vendors/jquery/jquery-3.6.0.min.js"></script>
 <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
 </head>
<body>
<div class="vote_header">
	<div class="header_bg">
		<c:if test="${addResult == 0}">
			<div class="header_subtitle"><h2>- 您已經投過這位作家囉!! -</h2></div>
		</c:if>	
			<div class="header_title"><h1>【當月最佳美食專欄作家投票活動】</h1></div>
		
	</div>
</div>
<div class="vote_navbar">
	<div class="clock">
		<i class="far fa-clock"></i>
		<p>110/07/31</p>
	</div>
	<div class="vote_number">
		<i class="fas fa-home"></i>
		<a href="${pageContext.request.contextPath}/article/article_homepage.jsp">回首頁</a>
	</div>
</div>


<div class="rank">
	<img src="${pageContext.request.contextPath}/article/img/medal1.png" id="medal1">
	<img src="${pageContext.request.contextPath}/article/img/medal2.png" id="medal2">
	<img src="${pageContext.request.contextPath}/article/img/medal3.png" id="medal3">
    <div class="rank_now">排行榜</div>
	<c:forEach var="voteList" items="${voteList}" begin="0" end="2">
		<img src="/upload/${voteList.get(1)}" class="rank_img">
		
	</c:forEach>
</div>
	<div class="adjust"></div>
	<c:forEach var="voteList" items="${voteList}" begin="0" end="2">	
		<div class="rank_name">${voteList.get(0)}</div>
	</c:forEach>	

<div class="author_content">
		<c:forEach var="voteList" items="${voteList}">
	        <div class="author_intro">
	        
	        <div class="vote_count">目前票數: 
					
						<c:if test="${voteList.get(2)==1}">
							${author_1_score}
						</c:if>
						<c:if test="${voteList.get(2)==2}">
							${author_2_score}
						</c:if>
						<c:if test="${voteList.get(2)==3}">
							${author_3_score}
						</c:if>
						<c:if test="${voteList.get(2)==4}">
							${author_4_score}
						</c:if>
						<c:if test="${voteList.get(2)==5}">
							${author_5_score}
						</c:if>
						<c:if test="${voteList.get(2)==6}">
							${author_6_score}
						</c:if>
						<c:if test="${voteList.get(2)==8}">
							${author_8_score}
						</c:if>
						<c:if test="${voteList.get(2)==9}">
							${author_9_score}
						</c:if>
						<c:if test="${voteList.get(2)==10}">
							${author_10_score}
						</c:if>
						<c:if test="${voteList.get(2)==11}">
							${author_11_score}
						</c:if>
						<c:if test="${voteList.get(2)==12}">
							${author_12_score}
						</c:if>
						<c:if test="${voteList.get(2)==13}">
							${author_13_score}
						</c:if>
					
					
					</div>
	            <a href="${pageContext.request.contextPath}/article/article_homepage.jsp"><img src="/upload/${voteList.get(1)}" alt=""></a>
	            <div class="author_intro_text"><a href="">${voteList.get(0)}</a>
	         
		             <FORM METHOD="GET" ACTION="article.do" name="form1">
					 	 <input type="hidden" name="action" value="vote">
						 <input type="hidden" name="author_no" value="${voteList.get(2)}">
					     <input type="submit" value="投票" onclick="myVote()"> 
					</FORM>
	


				
	            </div>
	        </div>
        </c:forEach>

	  	
    </div>


<script src="<%=request.getContextPath()%>/article/js/article_vote_result.js"></script>
</body>
</html>