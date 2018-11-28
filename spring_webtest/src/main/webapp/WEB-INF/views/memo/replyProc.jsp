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
	font-size: 15px;
}
</style>
<script type="text/javascript">
function mlist(){
	var url = "list";
	url = url + "?col=${col}";
	url = url + "&word=${word}";
	url = url + "&nowPage=${nowPage}";
	location.href = url;
}
</script>
<%-- <link href="${root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head>
<!-- *********************************************** -->
<body>
	
	<!-- *********************************************** -->


	<div class="container">
	<h2>처리 결과</h2>
		<c:choose>
			<c:when test="${flag }">
			답변 등록 성공
			</c:when>
			<c:otherwise>
			답변 등록 실패
			</c:otherwise>
		</c:choose>

		<input type='button' value='목록' onclick="mlist()">
		<input type='button' value='등록' onclick="location.href='create'">
	</DIV>



	<!-- *********************************************** -->
	
</body>
<!-- *********************************************** -->
</html>