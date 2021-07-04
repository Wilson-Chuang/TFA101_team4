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
		//objid�G�U�Կ��ت�ID�FtargetValue�G��e�ҿ��
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
		<option value="0" <c:if test="${gp.typeState == 0 }">selected</c:if>>�ҥ�</option>
		<option value="1" <c:if test="${gp.typeState == 1 }">selected</c:if>>�T��</option>
		
		
		
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
			<option value="9">���W9�I</option>
			<option value="10">���W10�I</option>
			<option value="11">���W11�I</option>
			<option value="12">����12�I</option>
			<option value="13">�U��1�I</option>
			<option value="14">�U��2�I</option>
			<option value="15">�U��3�I</option>
			<option value="16">�U��4�I</option>
			<option value="17">�U��5�I</option>
			<option value="18">�U��6�I</option>
			<option value="19">�ߤW7�I</option>
			<option value="20">�ߤW8�I</option>
			<option value="21">�ߤW9�I</option>
			<option value="22">�ߤW10�I</option>
			<option value="23">�ߤW11�I</option>
			<option value="24">�ߤW12�I</option>
			
			
			
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