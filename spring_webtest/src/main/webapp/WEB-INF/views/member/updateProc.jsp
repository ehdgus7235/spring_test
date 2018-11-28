<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function mread(){
	var url = "read";
	url += "?id=${id}";
	
	location.href=url;
}
</script> 
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<body>
<div class="container">
<h5>처리결과</h5>
<c:choose>
	<c:when test="${flag }">
	회원정보를 수정하였습니다.<br><br>
    <input type='button' value='정보확인' onclick="mread()">
	</c:when>
	<c:otherwise>
	수정을 실패하였습니다.<br><br>
    <input type='button' value='다시시도' onclick="history.back()">
	</c:otherwise>
</c:choose>
</DIV>

 
 
</body>
</html> 