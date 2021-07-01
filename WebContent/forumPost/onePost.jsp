<%@ page import="java.util.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.forum_post.model.*"%>
<%@ page import="com.forum_reply.model.*"%>
<%@ page import="com.member.model.*"%>
<%@ page import="com.search.model.*"%>
<%@ page import="com.shop.model.*"%>
<%@include file="../../pages/header.file"%>
<%-- <%=(request.getAttribute("forumPost") == null)%> --%>

<%
	ForumPostVO forumPost = (ForumPostVO) request.getAttribute("forumPost");
	Integer forum_post_id = null;
	if (forumPost.getForum_post_id() == null) {
		forum_post_id = (Integer) request.getAttribute("postid");
	} else {
		forum_post_id = forumPost.getForum_post_id();
	}

	ForumReplyService forumReplySvc = new ForumReplyService();
	List<ForumReplyVO> list = forumReplySvc.getByFoumPostID(forum_post_id);
	pageContext.setAttribute("list", list);
%>

<%
	MemberVO member = (MemberVO)session.getAttribute("login");
	pageContext.setAttribute("member", member);
%>

<%-- <%=forumPost.getForum_post_id()%> --%>

<jsp:useBean id="memberSvc" scope="page"
	class="com.member.model.MemberService" />

<html>
<head>
<title>Guide好食|討論文章</title>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}


/* div#post, #reply { */
/* 	border: solid 1px black; */
/* } */

FORM {
	display: inline-block;
}

div.like_block{
  display: inline-block;
}
div.like_block{
  cursor: pointer;
  display: inline-block;
  margin-right: 3px;
}

.svg-inline--fa{
	font-size: medium;
}
</style>
	<!-- fontawesome -->
	<script defer src="https://use.fontawesome.com/releases/v5.0.10/js/all.js" integrity="sha384-slN8GvtUJGnv6ca26v8EzVaR9DC58QEwsIk9q1QXdCU8Yu8ck/tL/5szYlBbqmS+" crossorigin="anonymous"></script>
	
	<!-- ckeditor -->
	<script src="<%=request.getContextPath()%>/forumPost/resources/ckeditor/ckeditor.js"></script>
	<script src="<%=request.getContextPath()%>/forumPost/resources/ckeditor/config.js"></script>
	
	<!-- Header -->
	<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/bootstrap-icons.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/materialdesignicons.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/wrunner-default-theme.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet">
	<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/header.js"></script>
	
	<link rel="stylesheet" href="css/onePost.css">
</head>
<body>

	
<!-- 	<table id="table-1"> -->
<!-- 		<tr> -->
<!-- 			<td><h3>文章 ForumPost: onePost.jsp</h3> -->
<!-- 				<h4>( MVC )</h4></td> -->
<!-- 		</tr> -->
<!-- 	</table> -->

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<ul>
		<li><a href="allPost.jsp">討論區</a><br></li>
	</ul>
	
    <article class="task_container">

        <h1 class="title1">
            <%=forumPost.getForum_post_title()%>
        </h1>

        <div><i class="fas fa-user"></i> ${memberSvc.GET_ONE_BY_ID(forumPost.member_id).member_email}</div>
        <div><i class="fas fa-clock"></i>
            <fmt:formatDate value="<%=forumPost.getForum_post_time()%>" pattern="yyyy/MM/dd HH:mm" />
        </div>
        <div>最後更新時間:
            <fmt:formatDate value="<%=forumPost.getForum_update_time()%>" pattern="yyyy/MM/dd HH:mm" />
        </div>
        <div>
            <%=forumPost.getForum_post_content()%>
        </div>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumPost/forumPost.do">
            <c:if test='${(forumPost.forum_post_content).equals("此內容已被刪除")}'>
                <button type="sumbit" id="btn_post_edit" style="display:none;">編輯</button>
                <input type="hidden" name="action" value="getOne_Post_For_update">
                <input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                <input type="hidden" name="memberID" value="${forumPost.member_id}">
                <input type="hidden" name="post_title" value="${forumPost.forum_post_title}">
                <input type="hidden" id="edit_post_content" name="post_content" value='${forumPost.forum_post_content}'>
            </c:if>
            <c:if test='${!(forumPost.forum_post_content).equals("此內容已被刪除")}'>
            	<c:if test='${forumPost.member_id == member.member_id}'>
                	<button type="sumbit" id="btn_post_edit" class="btn btn-outline-primary">編輯</button>
                	<input type="hidden" name="action" value="getOne_Post_For_update">
                	<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                	<input type="hidden" name="memberID" value="${forumPost.member_id}">
                	<input type="hidden" name="post_title" value="${forumPost.forum_post_title}">
                	<input type="hidden" id="edit_post_content" name="post_content" value='${forumPost.forum_post_content}'>
            	</c:if>
            </c:if>
        </FORM>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumPost/forumPost.do">
            <c:if test='${(forumPost.forum_post_content).equals("此內容已被刪除")}'>
                <button type="sumbit" id="btn_post_delete" style="display:none;">刪除</button>
                <input type="hidden" name="action" value="post_delete">
                <input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                <input type="hidden" name="memberID" value="${forumPost.member_id}">
            </c:if>
            <c:if test='${!(forumPost.forum_post_content).equals("此內容已被刪除")}'>
            	<c:if test='${forumPost.member_id == member.member_id}'>
                	<button type="button" id="btn_post_delete" class="btn btn-outline-secondary">刪除</button>
                	<input type="hidden" name="action" value="post_delete">
                	<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                	<input type="hidden" name="memberID" value="${forumPost.member_id}">
                </c:if>
            </c:if>
        </FORM>
        <FORM METHOD="post" ACTION="forumPost.do">
            <c:if test='${(forumPost.forum_post_content).equals("此內容已被刪除")}'>
                <button type="sumbit" id="btn_post_report" style="display:none;">檢舉</button>
                <input type="hidden" name="action" value="getOne_Post_For_report">
                <input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                <input type="hidden" name="memberID" value="${forumPost.member_id}">
                <input type="hidden" name="post_content" value='${forumPost.forum_post_content}'>
            </c:if>
            <c:if test='${!(forumPost.forum_post_content).equals("此內容已被刪除")}'>
            	<c:if test="${member != null}">
            		<c:if test='${!(forumPost.member_id == member.member_id)}'>
						<button type="button" id="btn_post_report" class="btn btn-outline-danger">檢舉</button>
                		<input type="hidden" name="action" value="getOne_Post_For_report">
                		<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                		<input type="hidden" name="memberID" value="${forumPost.member_id}">
                		<input type="hidden" name="post_content" value='${forumPost.forum_post_content}'>
            	 	</c:if>
            	</c:if>
            </c:if>
        </FORM>
        
        <c:if test='${!(forumPost.forum_post_content).equals("此內容已被刪除")}'>
        	<c:if test="${member != null}">
        		<div class="like_block"><i class="far fa-thumbs-up"></i></div>
        		<input type="hidden" name="action" id="post_like_action" value="post_like">
        		<input type="hidden" name="postid" id="post_like_postid" value="${forumPost.forum_post_id}">
        		<input type="hidden" name="memberID" id="post_like_memberID" value="${member.member_id}">
        	
        		<div><a class="btn btn-primary" href="#reply_editor" role="button">回復</a></div>
        	</c:if>
        </c:if>
        

        <div class="task_add_block"></div>

        <div class="task_list_parent">
            <ul class="task_list">
                <c:forEach var="forumReply" items="${list}">
                    <li>
                        <div class="item_flex">
                            <div id="reply">
                                <div><i class="fas fa-user"></i> ${memberSvc.GET_ONE_BY_ID(forumReply.member_id).member_email}</div>
                                <div><i class="fas fa-clock"></i>
                                    <fmt:formatDate value="${forumReply.forum_reply_time}" pattern="yyyy/MM/dd HH:mm" />
                                </div>
                                <div> 最後更新時間:
                                    <fmt:formatDate value="${forumReply.forum_reply_update_time}"
                                        pattern="yyyy/MM/dd HH:mm" />
                                </div>
                                <div>${forumReply.forum_reply_content}</div>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumPost/forumReply.do">
                                    <c:if test='${(forumReply.forum_reply_content).equals("此內容已被刪除")}'>
                                        <button type="sumbit" class="btn_reply_edit" style="display:none;">編輯</button>
                                        <input type="hidden" name="action" value="getOne_Reply_For_update">
                                        <input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                                        <input type="hidden" name="replyid" value="${forumReply.forum_reply_id}">
                                        <input type="hidden" name="memberID" value="${forumReply.member_id}">
                                        <input type="hidden" name="reply_content" class="edit_reply_content"
                                            value='${forumReply.forum_reply_content}'>
                                    </c:if>
                                    <c:if test='${!(forumReply.forum_reply_content).equals("此內容已被刪除")}'>
                                    	<c:if test='${forumReply.member_id == member.member_id}'>
											<button type="sumbit" class="btn btn-outline-primary btn_reply_edit" >編輯</button>
                                        	<input type="hidden" name="action" value="getOne_Reply_For_update">
                                        	<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                                        	<input type="hidden" name="replyid" value="${forumReply.forum_reply_id}">
                                        	<input type="hidden" name="memberID" value="${forumReply.member_id}">
                                        	<input type="hidden" name="reply_content" class="edit_reply_content"
                                            value='${forumReply.forum_reply_content}'>
                                        </c:if>
                                    </c:if>

                                </FORM>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumPost/forumReply.do">
                                    <c:if test='${(forumReply.forum_reply_content).equals("此內容已被刪除")}'>
                                        <button type="sumbit" class="btn_reply_delete" style="display:none;">刪除</button>
                                        <input type="hidden" name="action" value="reply_delete">
                                        <input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                                        <input type="hidden" name="replyid" value="${forumReply.forum_reply_id}">
                                        <input type="hidden" name="memberID" value="${forumReply.member_id}">
                                        <input type="hidden" name="reply_content" class="delete_reply_content"
                                            value='${forumReply.forum_reply_content}'>
                                    </c:if>
                                    <c:if test='${!(forumReply.forum_reply_content).equals("此內容已被刪除")}'>
                                    	<c:if test='${forumReply.member_id == member.member_id}'>
                                        	<button type="button" class="btn btn-outline-secondary btn_reply_delete">刪除</button>
                                       		<input type="hidden" name="action" value="reply_delete">
                                        	<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                                        	<input type="hidden" name="replyid" value="${forumReply.forum_reply_id}">
                                        	<input type="hidden" name="memberID" value="${forumReply.member_id}">
                                        	<input type="hidden" name="reply_content" class="delete_reply_content"
                                            	value='${forumReply.forum_reply_content}'>
                                         </c:if>
                                    </c:if>

                                </FORM>
                                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/forumPost/forumReply.do">
                                    <c:if test='${(forumReply.forum_reply_content).equals("此內容已被刪除")}'>
                                        <button type="sumbit" class="btn_reply_report" style="display:none;">檢舉</button>
                                        <input type="hidden" name="action" value="getOne_Reply_For_report">
                                        <input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                                        <input type="hidden" name="replyid" value="${forumReply.forum_reply_id}">
                                        <input type="hidden" name="memberID" value="${forumReply.member_id}">
                                        <input type="hidden" name="reply_content" class="report_reply_content"
                                            value='${forumReply.forum_reply_content}'>
                                    </c:if>
                                    <c:if test='${!(forumReply.forum_reply_content).equals("此內容已被刪除")}'>
                                    	<c:if test="${member != null}">
                                    		<c:if test='${!(forumReply.member_id == member.member_id)}'>
                                        	<button type="button" class="btn btn-outline-danger btn_reply_report">檢舉</button>
                                        	<input type="hidden" name="action" value="getOne_Reply_For_report">
                                        	<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                                        	<input type="hidden" name="replyid" value="${forumReply.forum_reply_id}">
                                        	<input type="hidden" name="memberID" value="${forumReply.member_id}">
                                        	<input type="hidden" name="reply_content" class="report_reply_content"
                                            	value='${forumReply.forum_reply_content}'>
                                        	</c:if>
                                    	</c:if>
                                    </c:if>
                                </FORM>
                            </div>
                        </div>
                    </li>
                </c:forEach>
                 <li id="reply_editor">
        			<div class="item_flex">
            			<FORM METHOD="post" ACTION="forumReply.do">
                			<c:if test='${!(forumPost.forum_post_content).equals("此內容已被刪除")}'>
<!--                     			<label for="memberID">會員ID</label>  -->
<!--                    				<input type="text" name="memberID"> -->
                   				<div class="input-group mb-3">
									<span class="input-group-text" id="basic-addon1">會員</span>
									<input type="text" class="form-control" name="member_email" disabled="disabled" value="${member.member_email}" aria-label="Username" aria-describedby="basic-addon1">
  									<input type="hidden" class="form-control" name="memberID" value="${member.member_id}" aria-label="Username" aria-describedby="basic-addon1">
								</div> 
                   				<textarea class="ckeditor" id="myContent" name="reply"></textarea>
                   				<br> 
                   				<input type="hidden" name="action" value="reply"> 
                   				<input type="hidden" name="postid" value="${forumPost.forum_post_id}">
                   				<button class="btn btn-primary" type="submit">送出</button> 
                			</c:if>
           				</FORM>
        			</div>
    			</li>
            </ul>
        </div>
    </article>

	
	<script src="vendors/jquery/jquery-3.6.0.min.js"></script>
	
	<script>
		
		$(document).ready(function(){
			let postid = $("#post_like_postid").val();

			var post_like_postid = JSON.parse(localStorage.getItem(postid));
			console.log(post_like_postid);
			if(post_like_postid == "like"){
				$("div.like_block").html('<i class="fas fa-thumbs-up"></i>');
		        $("div.like_block").addClass("-on");
			}else{
				$("div.like_block").html('<i class="far fa-thumbs-up"></i>');
		        $("div.like_block").removeClass("-on");
			}
			
			
			$("#btn_post_delete").on("click", function(){
				console.log($("#edit_post_content").val());
	       	 	if($("#edit_post_content").val() != "此內容已被刪除"){
	       	 		var yes = confirm("確定要刪除嗎?");
	       	 		if(yes){
	       	 			$("#btn_post_delete").attr("type", "sumbit");
		            	$("#btn_post_delete").sumbit();
	       	 		}
		    	}
	   		});
			
			$("#btn_post_report").on("click", function(){
				console.log($("#edit_post_content").val());
	       	 	if($("#edit_reply_content").val() != "此內容已被刪除"){
	       	 		var yes = confirm("確定要檢舉嗎?");
	       	 		if(yes){
	       	 			$("#btn_post_report").attr("type", "sumbit");
		            	$("#btn_post_report").sumbit();
	       	 		}
		    	}
	   		});
			
			
			$(".btn_reply_delete").on("click", function(){
// 				console.log($(this).siblings(".delete_reply_content").val());
// 				console.log($(this));
	       	 	if($(this).siblings(".delete_reply_content").val() != "此內容已被刪除"){
	       	 		var yes = confirm("確定要刪除嗎?");
	       	 		if(yes){
	       	 			$(this).attr("type", "sumbit");
	            		$(this).sumbit();
	       	 		}
		    	}
	   		});
			
			$(".btn_reply_report").on("click", function(){
// 				console.log($(this).siblings(".report_reply_content").val());
// 				console.log($(this));
	       	 	if($(this).siblings(".report_reply_content").val() != "此內容已被刪除"){
	       	 		var yes = confirm("確定要檢舉嗎?");
	       	 		if(yes){
	       	 			$(this).attr("type", "sumbit");
		            	$(this).sumbit();
	       	 		}
		    	}
	   		});
		
			//按讚
			$("div.like_block").on("click", function(){
				
				
				let action = $("#post_like_action").val();
				let postid = $("#post_like_postid").val();
				let memberID = $("#post_like_memberID").val();
				
				
				if($("div.like_block").hasClass("-on")){
					console.log("a");
			        $("div.like_block").html('<i class="far fa-thumbs-up"></i>');
			        $("div.like_block").removeClass("-on");
			        localStorage.removeItem(postid);
			    }else{
			        console.log("b");
			        $("div.like_block").html('<i class="fas fa-thumbs-up"></i>');
			        $("div.like_block").addClass("-on");
			        localStorage.setItem(postid , JSON.stringify("like"));        
			    }
				
				
				
				$.ajax({
				    url: "http://localhost:8081/Team4/forumPost/forumPost.do",
				    type: "POST",
				    data: { action: action, postid: postid, memberID: memberID },
				    dataType: "json",
				    success: function (data) {
				      console.log(data);
				    },
				    error: function (xhr) {
				      console.log(xhr);
				      console.log("error");
				    }
				  });		
			})			
		});
		
	</script>

</body>