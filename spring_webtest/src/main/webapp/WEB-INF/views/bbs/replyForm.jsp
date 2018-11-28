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
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
  };
</script>
<script type="text/javascript">
	function incheck(f) {
		if (f.wname.value == "") {
			alert("이름 입력");
			f.wname.focus();
			return false;
		}
		if (f.title.value == "") {
			alert("제목 입력");
			f.title.focus();
			return false;
		}
		if (f.passwd.value == "") {
			alert("비밀번호 입력");
			f.passwd.focus();
			return false;
		}
	}
</script>
</head>

<body>
<div class="container">
	<h2>답변</h2>

	<FORM name='frm' method='POST' action='./reply'
		enctype="multipart/form-data" onsubmit="return incheck(this)">

		<!-- 답변있는 부모글의 삭제를 못하게 하기 위하여~ -->
		<input type="hidden" name="bbsno" value="${dto.bbsno }">

		<!-- 답변에 필요한 부모 자료들 -->
		<input type="hidden" name="grpno" value="${dto.grpno }">
		<input type="hidden" name="indent" value="${dto.indent }">
		<input type="hidden" name="ansnum" value="${dto.ansnum }">
		
		<!-- 페이징 및 검색 유지를 위한 것 -->
		<input type="hidden" name="col" value="${param.col }"> 
		<input type="hidden" name="word" value="${param.word }">
		<input type="hidden" name="nowPage" value="${param.nowPage }">


		<TABLE class="table table-bordered">
			<TR>
				<TH>성명</TH>
				<TD><input type="text" name="wname"></TD>
			</TR>
			<TR>
				<TH>제목</TH>
				<TD><input type="text" name="title" value="답변 : ${dto.title }"></TD>
			</TR>
			<TR>
				<TH>내용</TH>
				<TD><textarea rows="10" cols="45" name="content">${dto.content }</textarea></TD>
			</TR>
			<TR>
				<TH>비밀번호</TH>
				<TD><input type="password" name="passwd"></TD>
			</TR>
			<TR>
				<TH>파일</TH>
				<TD><input type="file" name="filenameMF"></TD>
			</TR>
		</TABLE>

		<DIV class='bottom'>
			<input type='submit' value='등록'> <input type='button'
				value='취소' onclick="history.back()">
		</DIV>
	</FORM>
</div>
</body>
</html>
