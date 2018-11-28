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
<script type="text/javascript" 
src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript">
$.ajaxSetup({
    error: function(jqXHR, exception) {
        if (jqXHR.status === 0) {
            alert('Not connect.\n Verify Network.');
        }
        else if (jqXHR.status == 400) {
            alert('Server understood the request, but request content was invalid. [400]');
        }
        else if (jqXHR.status == 401) {
            alert('Unauthorized access. [401]');
        }
        else if (jqXHR.status == 403) {
            alert('Forbidden resource can not be accessed. [403]');
        }
        else if (jqXHR.status == 404) {
            alert('Requested page not found. [404]');
        }
        else if (jqXHR.status == 500) {
            alert('Internal server error. [500]');
        }
        else if (jqXHR.status == 503) {
            alert('Service unavailable. [503]');
        }
        else if (exception === 'parsererror') {
            alert('Requested JSON parse failed. [Failed]');
        }
        else if (exception === 'timeout') {
            alert('Time out error. [Timeout]');
        }
        else if (exception === 'abort') {
            alert('Ajax request aborted. [Aborted]');
        }
        else {
            alert('Uncaught Error.n' + jqXHR.responseText);
        }
    }
});
function idfind(){
var f = document.frm;
var mname = f.mname.value;
var email = f.email.value;
var url = "./idFind";
$.ajax({
	url : url,
	type : "GET",
	dataType : 'text',
	data : {"mname" : mname,
			"email" : email},
	success : function(data){
		$("#idresult").text(data.trim()).css("color","red");
	}
});
}
function pwfind(){
var f = document.frm2;
var id = f.id.value;
var mname2 = f.mname2.value;
var url = "./pwFind";
$.ajax({
	url : url,
	type : "GET",
	dataType : 'text',
	data : {"id" : id,
			"mname2" : mname2},
	success : function(data){
		$("#pwresult").text(data.trim()).css("color","red");
	}
});
}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head>
<!-- *********************************************** -->
<body>
	
	<!-- *********************************************** -->
	<div class="container">
	<h3>아이디 찾기</h3>

	<FORM name='frm'>
		<TABLE >
			<TR>
				<TH>이름</TH>
				<TD><input type="text" name="mname" value="" required="required"></TD>
			</TR>
			<TR>
				<TH>이메일</TH>
				<TD><input type="email" name="email" required="required"></TD>
			</TR>
		</TABLE>
			<input type='button' value='찾기' onclick='idfind()'>
			<input type='reset' value="취소">
	</FORM>
	<div id="idresult"></div>
	<hr>
	<h3>비밀번호 찾기</h3>

	<FORM name='frm2' method='POST' action='./pwFind_jquery.do'>
		<TABLE>
			<TR>
				<TH>id</TH>
				<TD><input type="text" name="id" value="" required="required"></TD>
			</TR>
			<TR>
				<TH>이름</TH>
				<TD><input type="text" name="mname2" required="required"></TD>
			</TR>
		</TABLE>
			<input type='button' value='찾기' onclick='pwfind()'>
			<input type='reset' value="취소">
	</FORM>
	<div id="pwresult"></div>
		</DIV>


	<!-- *********************************************** -->
	
</body>
<!-- *********************************************** -->
</html>