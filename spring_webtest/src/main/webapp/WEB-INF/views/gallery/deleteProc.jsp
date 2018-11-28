<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 15px; 
} 
</style> 
<script type="text/javascript">
function glist(){
	var url = "list";
	url = url +"?col=${param.col}";
	url = url +"&word=${param.word}";
	url = url +"&nowPage=${param.nowPage}";
	location.href = url;	
}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<h2>처리결과</h2>
 <div class="container">
 	<c:choose>
 		<c:when test="${pflag==false }">
 		비밀번호 오류<BR><BR>
 		<input type='button' value='다시시도' onclick="history.back()">
 		<input type='button' value='홈' onclick="location.href='${root}/'">
 		</c:when>
 		<c:when test="${flag }">
 		삭제 완료<br><br>
 		<button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
 		</c:when>
 		<c:otherwise>
 		삭제 실패<br><br>
 		<button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
  		<button type="button" class="btn btn-default btn-sm" onclick="history.back()"><span class="glyphicon glyphicon-repeat"></span> 다시시도</button>
 		</c:otherwise>
 	</c:choose>
  </DIV> 	
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 
