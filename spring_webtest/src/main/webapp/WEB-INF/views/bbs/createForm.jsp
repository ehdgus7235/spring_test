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
     CKEDITOR.replace('content');  // <TEXTAREA>태그 id 값
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
	 if (CKEDITOR.instances['content'].getData() == '') {
	      window.alert('내용을 입력해 주세요.');
	      CKEDITOR.instances['content'].focus();
	      return false;
	    }
	if (f.passwd.value == "") {
		alert("비밀번호 입력");
		f.passwd.focus();
		return false;
	}
}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css">
 --%></head>

<body>

	<div class="container">
		<h2>
			<span class="glyphicon glyphicon-pencil"></span>등록
		</h2>

		<FORM name='frm' method='POST' action='./create' enctype="multipart/form-data"
			onsubmit="return incheck(this)">
			<TABLE class="table table-bordered">
				<TR>
					<TH>성명</TH>
					<TD><input type="text" name="wname"></TD>
				</TR>
				<TR>
					<TH>제목</TH>
					<TD><input type="text" name="title"></TD>
				</TR>
				<TR>
					<TH>내용</TH>
					<TD><textarea rows="10" cols="45" name="content"></textarea></TD>
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
