<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
</style> 
<script type="text/javascript">
function read(){
	var url = "read.do";
	url = url +"?id=${id}";
	
	location.href=url;
}
</script>
</head> 
<body>
 <div class="container">
<h5>사진변경</h5>
<c:choose>
	<c:when test="${flag}">
	사진을 변경하였습니다.<br><br>
    <input type='button' value='나의정보' onclick="read()">
	</c:when>
	<c:otherwise>
	사진 변경을 실패하였습니다. <br><br>
    <input type='button' value='다시시도' onclick="history.back()">
	</c:otherwise>
</c:choose>
</DIV>

 
</body>
</html> 