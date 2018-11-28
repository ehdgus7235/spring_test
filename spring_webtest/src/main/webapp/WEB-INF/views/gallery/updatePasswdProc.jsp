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
	font-size: 15px;
}
</style>
<script type="text/javascript">
function read(){
	var url = "read";
	url = url + "?gno=${gno}";
	url = url +"&col=${param.col}";
	url = url +"&word=${param.word}";
	url = url +"&nowPage=${param.nowPage}";
	location.href = url;
}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head>

<body>

	<h2>비밀번호 변경</h2>
	<div class="content">
		<c:choose>
			<c:when test="${not gflag }">
			비밀번호가 틀렸습니다.<br><br>
			<input type='button' value='내 정보' onclick="read()">
			</c:when>
			<c:when test="${flag }">
			비밀번호 변경 완료!<br><br>
			<input type='button' value='내 정보' onclick="read()">
			</c:when>
			<c:otherwise>
			비밀번호 변경에 실패했습니다.<br><br>
			<input type='button' value='다시 시도' onclick="history.back()">
			</c:otherwise>
		</c:choose>
</div>
</body>
</html>
