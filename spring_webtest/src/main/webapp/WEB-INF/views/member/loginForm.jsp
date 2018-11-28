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
	font-size: 15px;
}
</style>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head>
<!-- *********************************************** -->
<body>
	
	<!-- *********************************************** -->
	<div class="container">
	<h2>로그인</h2>

	<FORM name='frm' method='POST' action='${root }/member/login'>
		<TABLE>
			<TR>
				<TH>ID</TH>
				<TD><input type="text" name="id" value="${c_id_val }">
				<c:choose>
					<c:when test="${c_id == 'Y' }">
				         <input type='checkbox' name='c_id' value='Y' checked='checked'> ID 저장 
					</c:when>
					<c:otherwise>
				         <input type='checkbox' name='c_id' value='Y' > ID 저장 
					</c:otherwise>
				</c:choose>
				</TD>
			</TR>
			<TR>
				<TH>비밀번호</TH>
				<TD><input type="password" name="passwd"></TD>
			</TR>
		</TABLE>


		<input type='submit' value='확인'>
		<input type='button' value='회원가입' onclick="location.href='agree'">
		<input type='button' value='ID/Passwd찾기' onclick="location.href='idpwFind'">
			
		<!-- 댓글 처리 -->	
		<input type="hidden" name="flag" value="${param.flag }">
		<input type="hidden" name="nPage" value="${param.nPage }">
		<input type="hidden" name="bbsno" value="${param.bbsno }">
		<input type="hidden" name="num" value="${param.num }">
		<input type="hidden" name="col" value="${param.col }">
		<input type="hidden" name="word" value="${param.word }">
		<input type="hidden" name="nowPage" value="${param.nowPage }">
	</FORM>
		</DIV>


	<!-- *********************************************** -->
	
</body>
<!-- *********************************************** -->
</html>