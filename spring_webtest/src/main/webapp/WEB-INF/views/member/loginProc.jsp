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
<h2>로그인 처리</h2>
<c:choose>
	<c:when test="${flag }">
	로그인이 되었습니다.<br><br>
    <input type='button' value='홈' onclick="location.href='${root}/'">	
	</c:when>
	<c:otherwise>
	아이디/비밀번호를 잘못입력하셨거나 <br>
	회원이 아닙니다. 회원가입을 하세요 <br><br>
    <input type='button' value='홈' onclick="location.href='${root}/'">
    <input type='button' value='다시시도' onclick="history.back()">
	</c:otherwise>
</c:choose>
</DIV>

 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html>