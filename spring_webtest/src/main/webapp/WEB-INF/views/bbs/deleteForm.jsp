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
	function incheck(f) {
		if (f.passwd.value == "") {
			alert("비밀번호 입력");
			f.passwd.focus();
			return false;
		}
	}
	function blist() {
		var url = "list";
		url = url + "?col=${param.col}";
		url = url + "&word=${param.word}";
		url = url + "&nowPage=${param.nowPage}";
		location.href = url;
	}
</script>
</head>

<body>
<div class="container">
<h2>삭제</h2><br>
	<c:choose>
	<c:when test="${flag }">
		<b>답글이 존재하여 삭제가 불가능</b><br>
		<input type=button value='목록' onclick="blist()">
	</c:when>
	<c:otherwise>
		<b>정말로 삭제 하시겠습니까</b><br>
	
		<FORM name='frm' method='POST' action='./delete'
		onsubmit="return incheck(this)">
		<input type="hidden" name="bbsno" value="${param.bbsno}">
		<input type="hidden" name="oldfile" value="${param.oldfile}">
		<input type="hidden" name="col" value="${param.col}">
 		<input type="hidden" name="word" value="${param.word}">
 		<input type="hidden" name="nowPage" value="${param.nowPage}">
		<TABLE>
			<TR>
				<TH>패스워드</TH>
				<TD><input type="password" name="passwd"></TD>
			</TR>
		</TABLE>

		<DIV class='bottom'>
			<input type='submit' value='확인'> <input type='button'
				value='취소' onclick="history.back()">
		</DIV>
	</FORM>
		
	</c:otherwise>
	</c:choose>
</div>

</body>
</html>
