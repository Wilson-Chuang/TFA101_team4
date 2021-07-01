<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>

<%
	ArticleService articleSvc = new ArticleService();
	List<ArticleVO> list = articleSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" /><html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=yes">
<title>文章管理(後台)</title>
<script src="https://kit.fontawesome.com/d210246855.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/article/css/listArticle_back_end.css">
<script src="${pageContext.request.contextPath}/article/vendors/jquery/jquery-3.6.0.min.js"></script>

</head>

<body>
	<div class="main">
		<div class="listProd_content">
			<div class="product_title">
				<h1>文章管理</h1>
			</div>
			<div class="product_navbar">
			
				<div class="search">
                <FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do" id="form">
                    <input class="search_bar" type="text" name="article_name" id="search" placeholder="文章標題..">
                    <input type="hidden" name="action" value="search">
        			<input type="submit" value="送出" class="search_submit">
                    <button class="search_button"><i class="fas fa-search"></i></button>
                </FORM>
                </div>

				<div class="datacount">
		
				</div>
			</div>
			<table>
				<tr>
					<th width="100px;"></th>
					<th width="350px;">文章標題</th>
					<th width="80px;">作家</th>			
	   				<th width="80px;">狀態</th>
					
					<th></th>
					<th></th>
				</tr>
				<c:forEach var="articleVO" items="${list}">
				<c:if test="${articleVO.article_verify_status == 1}">
					<tr>
						<td width="100px;">
							<div class="img">
									<a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=view_back_end"><img src="/upload/${articleVO.article_img_name}"></a>					
							</div>
						</td>
						<td width="350px;">
						
							<a href="${pageContext.request.contextPath}/article/article.do?article_no=${articleVO.article_no}&action=view_back_end">${articleVO.article_title}</a>
					
						</td>
						<td width="80px;">
						
						 <c:forEach var="memberVO" items="${memberSvc.all}">
                    		<c:if test="${articleVO.member_no==memberVO.member_id}">
	                   			${memberVO.member_name}
                    		</c:if>
                		 </c:forEach>  
						
						
						</td>
						<td width="80px;">
							<c:if test="${articleVO.article_status==0}">
	                   			<font style="color:red;">待審核</font>
                    		</c:if>
	
							<c:if test="${articleVO.article_status==1}">
	                   			<font style="color:blue;">公開中</font>
                    		</c:if>
						
						</td>				
						

						
						<td>
							<i class="far fa-trash-alt"></i>
			  				<FORM METHOD="get" ACTION="<%=request.getContextPath()%>/article/article.do">
			    					<input type="submit" value="移除" class="delete" onclick="javascript:return del();">
			     					<input type="hidden" name="article_no"  value="${articleVO.article_no}">
			     					<input type="hidden" name="action" value="delete2">
			     			</FORM>
						</td>
					</tr>
				</c:if>
				</c:forEach>
			</table>
			<div class="include_file">
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/article/js/listArticle_back_end.js"></script>
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
	$("#form").keydown(function(event) {
	    if(event.keyCode == 13){
	    	on 'submit';
	    };
	});
	</script>
</body>

</html>