<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>管理員管理</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllManager.jsp'>List</a> all Managers.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="manager.do" >
        <b>輸入管理員編號 :</b>
        <input type="text" name="manager_id">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="managerSvc" scope="page" class="com.manager.model.ManagerService" />
   
  <li>
     <FORM METHOD="post" ACTION="manager.do" >
       <b>選擇管理員編號:</b>
       <select size="1" name="manager_id">
         <c:forEach var="managerVO" items="${managerSvc.all}" > 
          <option value="${managerVO.manager_id}">${managerVO.manager_id}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="manager.do" >
       <b>選擇管理員姓名:</b>
       <select size="1" name="manager_id">
         <c:forEach var="managerVO" items="${managerSvc.all}" > 
          <option value="${managerVO.manager_id}">${managerVO.manager_name}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


</body>
</html>