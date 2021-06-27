<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.forum_post.model.*" %>
<%@include file="../../pages/header.file"%>

<%
	ForumPostVO forumPost = (ForumPostVO)request.getAttribute("forumPost");
%>
<html>
<head>
<title>發起討論</title>

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
</style>

		<!-- ckeditor -->
		<script src="<%=request.getContextPath()%>/forumPost/resources/ckeditor/ckeditor.js"></script>
		<script src="<%=request.getContextPath()%>/forumPost/resources/ckeditor/config.js"></script>
<!--     include libraries(jQuery, bootstrap) -->
<!--     <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet"> -->
<!--     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<!--     <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->

<!--     include summernote css/js -->
<!--     <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet"> -->
<!--     <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script> -->

<!-- Header -->
<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/bootstrap-icons.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/materialdesignicons.min.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/wrunner-default-theme.css" rel="stylesheet">
<link href="<%=request.getContextPath() %>/css/style_header.css" rel="stylesheet">
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
<script src="<%=request.getContextPath() %>/js/header.js"></script>
</head>
<body>
	<table id="table-1">
		<tr>
			<td><h3>ForumPost: addPost.jsp</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<%-- 	錯誤列表 --%>
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
	
	<div class="container">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<FORM METHOD="post" ACTION="forumPost.do">
		<!-- 		<label for="memberID">會員</label> -->
		<!-- 		<input type="text" name="memberID" id="memberID"> -->	
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1">會員</span>
  						<input type="text" class="form-control" name="memberID" id="memberID" aria-label="Username" aria-describedby="basic-addon1">
					</div>
		<!-- 		<label for="title">主題</label> -->
		<!-- 		<input type="text" name="title" id="title"> -->
					<div class="input-group mb-3">
						<span class="input-group-text" id="basic-addon1">主題</span>
  						<input type="text" class="form-control" name="title" id="title" aria-label="Username" aria-describedby="basic-addon1">
					</div>
		<!-- 		<textarea id="summernote" name="content"></textarea> -->
					<textarea class="ckeditor" id="myContent" name="content"></textarea>
					<br>
					<input type="hidden" name="action" value="insert">
					<button type="submit" class="btn btn-primary">送出</button>
				</FORM>
			</div>
			<div class="col-3"></div>
		</div>
	</div>

	
	<script>
//         $('#summernote').summernote({
//           placeholder: '請輸入內容',
//           tabsize: 2,
//           height: 300,
//           width: 900,
//           minHeight: null,             // set minimum height of editor
//           maxHeight: null,             // set maximum height of editor
//           focus: true,                  // set focus to editable area after initializing summernote
//           toolbar: [
//             ['style', ['style']],
//             ['font', ['bold', 'underline', 'clear']],
//             ['color', ['color']],
//             ['para', ['ul', 'ol', 'paragraph']],
//             ['insert', ['picture']],
//             ['view', ['fullscreen']]
//           ]
//         });
      </script>
</body>
</html>