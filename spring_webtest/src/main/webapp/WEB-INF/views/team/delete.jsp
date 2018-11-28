<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title></title> 
<script type="text/javascript">
function tlist(){
	var url="list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href=url;
}
</script>
</head> 
<body> 
<div class="container">
<h5>처리결과</h5>
	<c:choose>
		<c:when test="${dflag}">
			답변이 있어 삭제 할 수 없습니다.
		</c:when>
		<c:when test="${flag }">
			삭제 성공하였습니다.
		</c:when>
		<c:otherwise>
			삭제 실패하였습니다.
		</c:otherwise>
	</c:choose>
<button onclick="tlist()">목록</button>
</div>
</body> 
</html> 