<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../ssi/ssi.jsp"%>

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
<link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>

	<DIV class="title">ID 중복 확인</DIV>

	<div class="content"> ID를 입력해주세요 <br><br>
		<FORM name='frm' method='POST' action='./id_proc.jsp'>
			<TABLE>
				<TR>
					<TH>ID</TH>
					<TD><input type="text" name="id" size="20"></TD>
				</TR>
			</TABLE>

			<DIV class='bottom'>
				<input type='submit' value='확인'>
				<input type='button' value='취소' onclick="window.close()">
			</DIV>
		</FORM>
	</div>

</body>
<!-- *********************************************** -->
</html>