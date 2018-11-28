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
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script type="text/javascript">
function emailCheck(email){
	if(email==""){
		alert("이메일을 입력 해주세요");
		document.frm.email.focus();
	}else{
		var url = "emailCheck";
		$.ajax({
			url : url,
			type : "GET",
			dataType : 'text',
			data : {"email" : email},
			success : function(data){
				$("#emailresult").text(data.trim()).css("color", "red");
				if(data.trim().indexOf("중복 아님") != -1){
					$("#emailresult").append("<button onclick='use()'>적용</button>")
				}
			}
		})
	}
}
function use(){
	opener.frm.email.value = document.frm.email.value;
	self.close();
}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head>
<!-- *********************************************** -->
<body>

	<h2>Email 중복 확인</h2>
	<div class="content"> Email를 입력해주세요 <br><br>

		<FORM name='frm'>
			<TABLE>
				<TR>
					<TH>Email</TH>
					<TD><input type="text" name="email" size="20"></TD>
				</TR>
			</TABLE>
			
			<DIV id='emailresult'></DIV>
			<br><br>
			<input type='button' value='확인' onclick="emailCheck(this.form.email.value)">
			<input type='button' value='취소' onclick="window.close()">
		</FORM>
	</div>

</body>
<!-- *********************************************** -->
</html>