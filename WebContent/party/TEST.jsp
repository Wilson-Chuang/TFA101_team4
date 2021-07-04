<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>


<script>
	function change() {
		document.selection.submit();
	}
</script>


<script>
	function setSelectOption(objId, targetValue) {
		//objid：下拉選單框的ID；targetValue：當前所選值
		var obj = document.getElementById(objId);
		if (obj) {
			var options = obj.options;
			if (options) {
				var len = options.length;
				for (var i = 0; i < len; i) {
					if (options[i].value == targetValue) {
						options[i].defaultSelected = true;
						options[i].selected = true;
						return true;
					}
				}
			} else {
				alert('missing element(s)!');
			}
		} else {
			alert('missing element(s)!');
		}
	}
	
	
	
</script>


	<link href="<%=request.getContextPath() %>/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/bootstrap-icons.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/materialdesignicons.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/wrunner-default-theme.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/css/header.css" rel="stylesheet">


</head>
<body>

	<%@include file="/pages/header.file"%>

	<select name="typeState" style="width: 160">
		<option value="0" <c:if test="${gp.typeState == 0 }">selected</c:if>>啟用</option>
		<option value="1" <c:if test="${gp.typeState == 1 }">selected</c:if>>禁用</option>
		
		
		
	</select>

<form action="some.jsp">
  <select name="item">
    <option value="1">a</option>
    <option value="2">b</option>
    <option value="3">c</option>
  </select>
  <input type="submit" value="Submit">
</form>


String value = request.getParameter("item");
	<form name="selection" action="index.jsp" method="post">
		<select name="time" size="1" id="time" onChange="change()">
			<option value="0" selected></option>
			<option value="9">早上9點</option>
			<option value="10">早上10點</option>
			<option value="11">早上11點</option>
			<option value="12">中午12點</option>
			<option value="13">下午1點</option>
			<option value="14">下午2點</option>
			<option value="15">下午3點</option>
			<option value="16">下午4點</option>
			<option value="17">下午5點</option>
			<option value="18">下午6點</option>
			<option value="19">晚上7點</option>
			<option value="20">晚上8點</option>
			<option value="21">晚上9點</option>
			<option value="22">晚上10點</option>
			<option value="23">晚上11點</option>
			<option value="24">晚上12點</option>
			
			
			
		</select>
		<p>
			<%
				String token = request.getParameter("time");
				out.println(token);
			%>
		
	</form>

    <script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.bundle.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/wrunner-jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/header.js"></script>


</body>
</html>