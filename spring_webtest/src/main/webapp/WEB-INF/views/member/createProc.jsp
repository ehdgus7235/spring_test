<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 15px; 
} 
</style> 
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<div class="container">
<h2>회원가입 처리</h2>
<c:choose>
	<c:when test="${flag }">
	회원가입을 했습니다. <br><br>
    <input type='button' value='로그인' onclick="location.href='login'">
    <input type='button' value='홈' onclick="location.href='${root}/'">
	</c:when>
	<c:otherwise>
	회원가입을 실패했습니다. <br><br>
	<input type='button' value='다시시도' onclick="history.back()">
    <input type='button' value='홈' onclick="location.href='${root}/'">
	</c:otherwise>
</c:choose>
</DIV>
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html>