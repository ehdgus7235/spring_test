<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function inputCheck(f){
	if(f.passwd.value==""){
		alert("현재 비밀번호를 입력해 주세요.");
		f.passwd.focus();
		return false;
	}
	if(f.newPasswd.value==""){
		alert("새 비밀번호를 입력해 주세요.");
		f.newPasswd.focus();
		return false;
	}
	if(f.newPasswd.value!=f.checkNewPasswd.value){
		alert("새 비밀번호가 일치하지 않습니다.");
		f.newPasswd.focus();
		return false;
	}
}
</script> 
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<body>
 <div class="container">
<h5>비밀번호 변경</h5>
 
<FORM name='frm' method='POST' action='./updatePasswd' onsubmit="return inputCheck(this)">
<input type="hidden" name="id" value="${param.id}">
  <TABLE>
    <TR>
      <TH>현재 비밀번호</TH>
      <TD><input type="password" name="passwd" size="15" value="${dto.passwd}"></TD>
    </TR>
    <TR>
      <TH>새 비밀번호</TH>
      <TD><input type="password" name="newPasswd" size="15"></TD>
    </TR>
    <TR>
      <TH>새 비밀번호 확인</TH>
      <TD><input type="password" name="checkNewPasswd" size="15"></TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='변경'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 </div>
 
</body>
</html> 