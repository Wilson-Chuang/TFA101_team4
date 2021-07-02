<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.shop_favorites.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@ page import="com.comment_report.model.*"%>
<%@ page import="com.member_follower.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.search.model.*"%>

<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Giude好食|評論檢舉處理</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/bootstrap-icons.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/materialdesignicons.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/wrunner-default-theme.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/manager/vendors/bootstrap/css/bootstrap.min.css">


<style>
  div.main_content {
    width: 100%;
    float: left;
  }

  .breadcrumb{
     background-color: white;
     margin:20px 0 0 0;
     font-size:15px;
  }
</style>
<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</head>
<body>
<div class="main_content">

    <div>
	<jsp:include page="/cms/header_asideMenu/cmsAsideMenu.jsp" flush="true" />
    </div>
    
    <!--麵包屑，請大家對應側邊欄幫忙修改一下以下名稱，因為是bootstrap，所以需載入script-->
    <div>
	<nav aria-label="breadcrumb">
	    <ol class="breadcrumb">
		<li class="breadcrumb-item">意見回饋</li>
		<li class="breadcrumb-item active" aria-current="page">評論檢舉</li>
	    </ol>
	</nav>
    </div>

<div class="container">

        <div class="row">
           <div >
                
                <div class="row">
                <c:if test="${not empty errorMsgs}">
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
                <h1>檢舉清單</h1>
                    <hr>
                    <%
                		Comment_ReportService cmSvc=new Comment_ReportService();
                		List<Comment_ReportVO> rtList=cmSvc.getAll();
 							for (Comment_ReportVO rt : rtList) {
 							int member_id=rt.getMEMBER_ID();
 							int comment_id=rt.getCOMMENT_ID();
 							String reason = rt.getCOMMENT_REPORT_REASON();
 							MemberService memSvc=new MemberService();
 							MemberVO rt_MemberVO=memSvc.GET_ONE_BY_ID(member_id);
 							String member_name=rt_MemberVO.getMember_name();
 							CommentService comSvc=new CommentService();
 							CommentVO CommentVO=comSvc.getOneStmt(comment_id);
 							MemberVO MemberVO=memSvc.GET_ONE_BY_ID(CommentVO.getMEMBER_ID());
 							String uploadFilePath = request.getContextPath()+ File.separator+ 
 									"UPLOAD" + File.separator + "comment"+ File.separator + "pic"+ File.separator;
 						%>
                    
                    <div class="comment_zone">
                   	 <h3 class="user">檢舉者:<%=member_name%>，檢舉原因:<%=reason %></h3>
                        <p>檢舉時間<%=rt.getCOMMENT_REPORT_TIME() %></p>
                        <hr>
                       	<p>檢舉評論:</p>
                        <h3 class="user"><%=MemberVO.getMember_name() %></h3>
                        <p><%=CommentVO.getCOMMENT_TIME() %></p>
                        <span class="ratins"><%=CommentVO.getCOMMENT_RATING()%><i class="fas fa-star"></i></span><br>
                        <p><%=CommentVO.getCOMMENT_CONTENT() %></p>
                            <img src="<%=uploadFilePath+CommentVO.getCOMMENT_PIC()%>" alt="" style="width:25%"><br>
                            
                            
                            
                            
                         <%if(rt.getCOMMENT_REPORT_STATUS()==1){ %>
                        <form action="member.html" method="post">
                         <input type="hidden" name="comment_id" value="<%=comment_id %>">
                         <input type="hidden" name="comment_status" value="0">
                         <input type="hidden" name="comment_report_id" value="<%=rt.getCOMMENT_REPORT_ID() %>">
                         <input type="hidden" name="comment_report_status" value="0">
                         <input type=hidden name="action" value="delete_comment_rt">
                         <input type="submit" value="遮蔽該評論" class="save_btn" style="width:150px">
                         </form>
                        <form action="member.html" method="post">
                         <input type="hidden" name="comment_id" value="<%=comment_id %>">
                         <input type="hidden" name="comment_status" value="1">
                         <input type="hidden" name="comment_report_id" value="<%=rt.getCOMMENT_REPORT_ID() %>">
                         <input type="hidden" name="comment_report_status" value="0">
                         <input type=hidden name="action" value="delete_comment_rt">
                         <input type="submit" value="通過該評論" class="save_btn" style="width:150px">
                         </form>
                         <%}else{ %>
                        <h3>已處理此檢舉</h3>
                         <%} %>
                            <hr>
                    </div>
                    <%
 							}
						%> 
                </div>

            </div>
        </div>
    </div>


</body>
    <script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
	<script src="./vendors/popper/popper.min.js"></script>
	<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>
    <script type='text/javascript'>
			function change() {
  				var value = document.getElementById('range').value ;
  				document.getElementById('value').innerHTML = value;
			}
			
		</script>
</html>