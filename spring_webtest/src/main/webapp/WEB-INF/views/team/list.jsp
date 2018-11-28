<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function read(no){
	//alert(no);
	var url = "read";
	url = url + "?no="+no;
	url = url + "&col=${col}";
	url = url + "&word=${word}";
	url = url + "&nowPage=${nowPage}";
	location.href=url;
}
function update(no){
	//alert(no);
	var url = "update";
	url = url + "?no="+no;
	url = url + "&col=${col}";
	url = url + "&word=${word}";
	url = url + "&nowPage=${nowPage}";
	location.href=url;	
}
function del(no){
	//alert(no);
	if(confirm("정말 삭제할 거에요..?")){
	var url = "delete";
	url = url + "?no="+no;
	url = url + "&col=${col}";
	url = url + "&word=${word}";
	url = url + "&nowPage=${nowPage}";
	location.href=url;	
		
	}
	
}
function reply(no){
	var url =" reply";
	url = url + "?no="+no;
	location.href=url;
	
}
</script>
</head>
<body>
	<div class="container">
	<h5>팀 목록</h5>
	<form method="post" action="list">
	<div class="serarch">
	<select name="col">
	<option value="name"
	<c:if test="${col=='name'}">selected</c:if>>이름</option>
	<option value="skills" 
	<c:if test="${col=='name'}">selected</c:if>>보유기술</option>
	<option value="total">전체출력</option>
	</select>
	<input type="text" name="word" value="${word}">
	<button>검색</button>
	<button type="button" onclick="location.href='create'">등록</button>
	</div>
	</form>
	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>성별</th>
			<th>전화번호</th>
			<th>특기</th>
			<th>grpno</th>
			<th>indent</th>
			<th>ansnum</th>
			<th>수정/삭제/답변</th>
		</tr>
		<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.no}</td>
			<td>
			<c:forEach begin="1" end="${dto.indent}">
				&nbsp;&nbsp;
			</c:forEach>
			<c:if test="${dto.indent>0}">
				<img src='../images/re.jpg'>
			</c:if>
			<a href="javascript:read('${dto.no}')">${dto.name}</a></td>
			<td>${dto.gender}</td>
			<td>${dto.phone}</td>
			<td>${dto.skills}</td>
			<td>${dto.grpno}</td>
			<td>${dto.indent}</td>
			<td>${dto.ansnum}</td>
			<td>
			<a href="javascript:update('${dto.no}')">수정</a>/
			<a href="javascript:del('${dto.no}')">삭제</a>/
			<a href="javascript:reply('${dto.no}')">답변</a>
			</td>
		</tr>
</c:forEach>
	</table>
	<div>
	${paging}
	</div>
	</div>
</body>
</html>
