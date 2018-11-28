<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}
</style>

<script type="text/javascript">
	function blist() {
		var url = "list";
		location.href = url;
	}
</script>
</head>

<body>

	<DIV class="title">처리결과</DIV>
	<div class="content">
		<c:choose>
		<c:when test="${flag}">글이 등록되었습니다.</c:when>
		<c:otherwise>글 등록에 실패했습니다.</c:otherwise>
		</c:choose>
	</div>

	<DIV class='bottom'>
		<input type='button' value='목록' onclick="blist()"> <input
			type='button' value='계속 등록' onclick="location.href='create'">
	</DIV>

</body>
</html>
