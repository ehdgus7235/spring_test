<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function listb(){
	var url = "list.do";
	url += "?col=${col}";
	url += "&word=${word}";
	url += "&nowPage=${nowPage}";
	
	location.href = url;
}
</script>

</head>
<body>
	<div class="container">
	
		<c:choose>
		<c:when test="${flag}">답변에 성공했습니다.<br><button onclick="listb()">목록</button></c:when>
		<c:otherwise>답변에 실패했습니다.<br><button onclick="history.back()">다시 시도</button></c:otherwise>
		</c:choose>
	</div>
</body>
</html>