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
// 	VoteVO votevo = (VoteVO) request.getAttribute("votevo");
	int author_1_score = (int)request.getAttribute("author_1_score");
	int author_2_score = (int)request.getAttribute("author_2_score");
	int author_3_score = (int)request.getAttribute("author_3_score");
	int author_4_score = (int)request.getAttribute("author_4_score");
	int author_5_score = (int)request.getAttribute("author_5_score");
	int author_6_score = (int)request.getAttribute("author_6_score");
	
%>
<html>
<head>
 <title>投票結果</title>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/article/css/article_vote_result.css">
 <script src="<%=request.getContextPath()%>/article/vendors/jquery/jquery-3.6.0.min.js"></script>
 <script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
 </head>
<body>

    
		   	<div>您已為此作家投過票，請勿重複投票！</div>	   	    

<script src="<%=request.getContextPath()%>/article/js/article_vote_result.js"></script>
</body>
</html>