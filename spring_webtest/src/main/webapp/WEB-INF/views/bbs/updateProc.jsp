<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>
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
		url = url + "?col=${col}";
		url = url + "&word=${word}";
		url = url + "&nowPage=${nowPage}";
		location.href = url;
	}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css">
 --%></head>

<body>

	<div class="container">
	<h2>수정결과</h2>
	<c:choose>
		<c:when test="${pflag==false}">비밀번호 오류<br><input type='button' value='다시 시도' onclick="history.back()"></c:when>
		<c:when test="${flag }">글 수정에 성공했습니다.<br>	<input type='button' value='목록' onclick="blist()"></c:when>
		<c:otherwise>글 수정에 실패했습니다.<br><input type='button' value='다시 시도' onclick="history.back()"></c:otherwise>
	</c:choose>
	</div>

</body>
</html>
