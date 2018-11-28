<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

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
		url = url + "?col=${param.col}";
		url = url + "&word=${param.word}";
		url = url + "&nowPage=${param.nowPage}";
		location.href = url;
	}
</script>
</head>

<body>

	<DIV class="title">처리결과</DIV>
	<div class="content">
		<c:choose>
		<c:when test="${pflag==false }">
		비밀번호 오류
		<input type='submit' value='다시시도' onclick="history.back()"> 
			<input type='button' value='목록' onclick="blist()">
		</c:when>
		<c:when test="${flag}">
		삭제 완료
		<input type='button' value='목록' onclick="blist()">
		</c:when>
		<c:otherwise>
			삭제 실패
			<input type='submit' value='다시시도' onclick="history.back()"> 
			<input type='button' value='목록' onclick="blist()">
		</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
