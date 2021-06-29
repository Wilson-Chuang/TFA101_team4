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
                <h1>檢舉清單</h1>
                    
                    <%
 							int comment_id=new Integer(request.getParameter("comment_ID"));
 							CommentService comSvc=new CommentService();
 							CommentVO CommentVO=comSvc.getOneStmt(comment_id);
 							MemberVO MemberVO=memSvc.GET_ONE_BY_ID(CommentVO.getMEMBER_ID());
 						%>
                    
                    <div class="comment_zone">
                        <h3 class="user"><%=MemberVO.getMember_name() %></h3>
                        <p><%=CommentVO.getCOMMENT_TIME() %></p>
                        <span class="ratins"><%=CommentVO.getCOMMENT_RATING()%><i class="fas fa-star"></i></span><br>
                        <p><%=CommentVO.getCOMMENT_CONTENT() %></p>
                            <img src="/upload/<%=CommentVO.getCOMMENT_PIC()%>" alt="" style="width:25%"><br>
                        <p>確定要刪除該評論嗎?</p>
                        <form action="member.html" method="post">
                         <input type="hidden" name="comment_id" value="<%=comment_id %>">
                         <input type=hidden name="action" value="delete_comment">
                         <input type="submit" value="是" class="save_btn" style="width:150px">
                         </form>
                         <a href="/public/comment_delete_comfirm.jsp"><input type="submit" value="否" class="save_btn" style="width:150px"></a>
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