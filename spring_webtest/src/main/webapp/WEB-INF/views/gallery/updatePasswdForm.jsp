<%@ include file="/ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>

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
	
	function inputCheck(f) {
		if(f.newpw.value==""){
			alert("변경할 비밀번호를 입력하세요.");
			f.newpw.focus();
			return false;
		}
		if(f.repw.value==""){
			alert("확인 비밀번호를 입력하세요.");
			f.repw.focus();
			return false;
		}
		if (f.newpw.value != f.repw.value) {
			alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			f.pw.focus();
			return false;
		}

	}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head>

<body>

	<DIV class="title">비밀번호 수정</DIV>

	<FORM name='frm' method='POST' action='./updatePasswd'
		onsubmit="return inputCheck(this)">
		<input type="hidden" name="gno" value="${gno}">
		<input type="hidden" name="col" value="${param.col}">
		<input type="hidden" name="word" value="${param.word}">
		<input type="hidden" name="nowPage" value="${param.nowPage}">
		<TABLE>
			<TR>
				<TH>현재 비밀번호 입력</TH>
				<TD><input type="password" name="pw"></TD>
			</TR>
			<TR>
				<TH>변경할 비밀번호 입력</TH>
				<TD><input type="password" name="newpw"></TD>
			</TR>
			<TR>
				<TH>비밀번호 재확인</TH>
				<TD><input type="password" name="repw"></TD>
			</TR>
		</TABLE>

		<DIV class='bottom'>
			<input type='submit' value='수정'>
			<input type='button' value='취소' onclick="history.back()">
		</DIV>
	</FORM>

</body>
</html>
