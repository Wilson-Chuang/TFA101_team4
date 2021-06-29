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
<%@ include file="/pages/header.file" %>

<%String path =request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
	
// 	MemberVO myMemberVO = (MemberVO) session.getAttribute("login");
// 	int shop_id= Integer.valueOf(request.getParameter("shop_id"));
// 	ShopService shopSvc=new ShopService();
// 	shopSvc.add_total_view(shop_id);
// 	ShopVO ShopVO= shopSvc.getOneShop(shop_id);
// 	CommentService comSvc = new CommentService();
// 	double shopRating=comSvc.countRatings(shop_id);
// 	if(Double.isNaN(shopRating)){
// 		shopRating =0.0;
// 	}
// 	int countByShop = comSvc.countByShop(ShopVO.getShop_id());
// 	List<CommentVO> list_Com =(List<CommentVO>)comSvc.getAllByShop(shop_id);

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
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="./fontawesome-free-5.15.3-web/css/all.css">

</head>
<body>
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
 						%>
                    
                    <div class="comment_zone">
                   	 <h3 class="user">檢舉者:<%=member_name%>，檢舉原因:<%=reason %></h3>
                        <p>檢舉時間<%=rt.getCOMMENT_REPORT_TIME() %></p>
                       	<p>檢舉評論:</p>
                        <h3 class="user"><%=MemberVO.getMember_name() %></h3>
                        <p><%=CommentVO.getCOMMENT_TIME() %></p>
                        <span class="ratins"><%=CommentVO.getCOMMENT_RATING()%><i class="fas fa-star"></i></span><br>
                        <p><%=CommentVO.getCOMMENT_CONTENT() %></p>
                            <img src="/upload/<%=CommentVO.getCOMMENT_PIC()%>" alt="" style="width:25%"><br>
                        <form action="member.html" method="post">
                         <input type="hidden" name="comment_id" value="<%=comment_id %>">
                         <input type="hidden" name="comment_report_id" value="<%=rt.getCOMMENT_ID() %>">
                         <input type=hidden name="action" value="delete_comment_rt">
                         <input type="submit" value="刪除該評論" class="save_btn" style="width:150px">
                         </form>
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
   <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/wrunner-jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/scripts.js"></script>
    <script type='text/javascript'>
			function change() {
  				var value = document.getElementById('range').value ;
  				document.getElementById('value').innerHTML = value;
			}
			
		</script>
</html>