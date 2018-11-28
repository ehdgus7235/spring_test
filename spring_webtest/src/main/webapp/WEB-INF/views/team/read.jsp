<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
function tlist(){
	var url = "list";
	url = url + "?col=${col}";
	url = url + "&word=${word}";
	url = url + "&nowPage=${nowPage}";
	location.href=url;
}
</script>
</head>
<body>
	
	<div class="container">
	<h5>직원 정보</h5>
	<table class="table table-bordered">
		<tr>
		<th>번호</th>
		<td>${dto.no}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${dto.name}</td>
		</tr>
		<tr>
			<th>성별</th>
			<td>${dto.gender}</td>
		</tr>
		<tr>
			<th>보유기술</th>
			<td>${dto.skills}</td>
		<tr>
			<th>취미</th>
			<td>${dto.hobby}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${dto.phone}</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${dto.zipcode}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${dto.address}</td>
		</tr>
		<tr>
			<th>상세주소</th>
			<td>${dto.address2}</td>
		</tr>
	</table>
	<div>
		<button type="button" onclick="tlist()">목록</button>
		
	</div>
	</div>
</body>
</html>