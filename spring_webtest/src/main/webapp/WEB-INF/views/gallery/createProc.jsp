<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>

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
	location.href = url;	
}
function gcreate(){
	var url = "createForm";
	location.href = url;
}

</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<h2>
 <span class="glyphicon glyphicon-picture"></span>처리결과
 </h2>
<div class="content">
<c:choose>
	<c:when test="${flag }">
	사진 등록에 성공하였습니다.<br><br>
	<button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
  	<button type="button" class="btn btn-default btn-sm" onclick="gcreate()"><span class="glyphicon glyphicon-pencil"></span> 계속등록</button>
	</c:when>
	<c:otherwise>
	사진 등록에 실패하였습니다.<br><br>
	<button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
  	<button type="button" class="btn btn-default btn-sm" onclick="history.back()"><span class="glyphicon glyphicon-repeat"></span> 다시시도</button>
	</c:otherwise>
</c:choose>
</div>  
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 