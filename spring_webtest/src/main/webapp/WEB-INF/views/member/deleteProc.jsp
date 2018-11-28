<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
</style> 
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<body>
 <div class="container">
<h5>처리결과</h5>
<c:choose>
	<c:when test="${not pflag }">
	비밀번호가 일치하지 않습니다.
	</c:when>
	<c:when test="${flag }">
	탈퇴되었습니다. 자동 로그아웃됩니다.
	</c:when>
	<c:otherwise>
	탈퇴를 실패하였습니다.
	</c:otherwise>
</c:choose>
    <input type='button' value='홈' onclick="location.href='../index.jsp'">
    <input type='button' value='다시시도' onclick="history.back()">
</DIV>

 
 
</body>
</html> 