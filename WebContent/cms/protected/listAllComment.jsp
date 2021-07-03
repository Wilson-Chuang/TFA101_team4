<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@ page import="com.shop_favorites.model.*"%>
<%@ page import="com.comment.model.*"%>
<%@ page import="com.comment_report.model.*"%>
<%@ page import="com.member_follower.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
    
    MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
    pageContext.setAttribute("memberVO",memberVO);
    String picpath=request.getContextPath()+ File.separator+"UPLOAD" + File.separator + "member"+ File.separator + "pic"+ File.separator;

%>


<html>
<head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/cms/vendors/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/cms/vendors/bootstrap/css/bootstrap.min.css">


<title>評論檢舉 - listAllMember.jsp</title>


<style>
  ul {
    list-style: none;
  }
  
  table {
	width: 950px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  .table1 {
  	border: 0;
    border-bottom: 1px solid #ccc;
    padding: 5px;
    margin: 0 5px;
    text-align: center;
    height: 80px;
    vertical-align: middle;
  }
  .table0{
   padding-top: 15px;
   vertical-align: middle;
   }
  img {
  width: 90%;
  }
  button{
  	border: none;
  	background-color: white;	
  }
  
  div.main_content {
    width: 100%;
    float: left;
  }

  .breadcrumb{
     background-color: white;
     margin:20px 0 0 0;
     font-size:15px;
  }
  
  div. firstline{
  	float:left;
  }
  
  
</style>

</head>
<body bgcolor='white'>

 
 <header>
	<jsp:include page="/cms/header_asideMenu/cmsHeader.jsp" flush="true" />
</header>


<div class="main_content">

    <div>
		<jsp:include page="/cms/header_asideMenu/cmsAsideMenu.jsp" flush="true" />
    </div>
    
    <div class="firstline">
		<nav aria-label="breadcrumb">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item">用戶管理</li>
		    <li class="breadcrumb-item active" aria-current="page">會員管理</li>
		  </ol>
		</nav>
    </div>

	
	<div class="container">

        <div class="row">
           <div >
                <div class="comment_table">
                    	<form action="member.html"  method="post"  enctype="multipart/form-data">
                    	<input type="hidden" name="action" value="getdbcomment">
						<input type="submit" value="導入評論" class="comment_btn" style="width:150px;"><br><hr>
						</form>
                <div class="row">
                <c:if test="${not empty errorMsgs}">
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
                <h1>評論檢舉清單</h1>
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
                    <table class="table0  table-striped"" >
                    	<tr>
                    		<td scope="col"width="100px">檢舉者</td>
                    		<td scope="col"width="100px">檢舉原因</td>
                    		<td scope="col"width="100px">評論作者</td>
                    		<td scope="col"width="100px">評論等級</td>
                    		<td scope="col"width="100px">評論內容</td>
                    		<td scope="col" width="100px">評論圖案</td>
                    		<td scope="col"width="100px">接受檢舉</td>
                    		<td scope="col"width="100px">拒絕檢舉</td>
               		  </tr>
                    	<tr style="align: center;">
                    		<td scope="row"><%=member_name%></td>
                    		<td><%=reason %></td>
                    		<td><%=MemberVO.getMember_name()%></td>
                    		<td><%=CommentVO.getCOMMENT_RATING()%></td>
                    		<td><%=CommentVO.getCOMMENT_CONTENT() %></td>
                    		<td><img src="<%=uploadFilePath+CommentVO.getCOMMENT_PIC()%>" alt=""></td>
                    		<td><form action="member.html" method="post">
                         <input type="hidden" name="comment_id" value="<%=comment_id %>">
                         <input type="hidden" name="comment_status" value="0">
                         <input type="hidden" name="comment_report_id" value="<%=rt.getCOMMENT_REPORT_ID() %>">
                         <input type="hidden" name="comment_report_status" value="0">
                         <input type=hidden name="action" value="delete_comment_rt">
                         <label><i class="fas fa-lock"></i>
                         <input type="submit" value="遮蔽" class="save_btn"  style="display:none"></label>
                         </form></td>
                    		<td><form action="member.html" method="post">
                         <input type="hidden" name="comment_id" value="<%=comment_id %>">
                         <input type="hidden" name="comment_status" value="1">
                         <input type="hidden" name="comment_report_id" value="<%=rt.getCOMMENT_REPORT_ID() %>">
                         <input type="hidden" name="comment_report_status" value="0">
                         <input type=hidden name="action" value="delete_comment_rt">
                         <label><i class="fas fa-lock-open"></i>
                         <input type="submit" value="通過" class="save_btn" style="display:none"></label>
                         </form></td>
               		  </tr>
                    
                    
                    
                    </table>
                            <hr>
                    </div>
                    <%
 							}
						%> 
                </div>

            </div>
        </div>
    </div>


</div>
</body>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
<script src="./vendors/jquery/jquery-3.5.1.min.js"></script>
<script src="./vendors/popper/popper.min.js"></script>
<script src="./vendors/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>