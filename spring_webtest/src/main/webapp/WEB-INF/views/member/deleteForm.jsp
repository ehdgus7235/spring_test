<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
</style> 
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<body>
<div class="container">
<h2>회원탈퇴</h2>
 
<FORM name='frm' method='POST' action='./delete'>
<input type='hidden' name='id' value='${id }'>
 <input type="hidden" name="col" value="${param.col}">
 <input type="hidden" name="word" value="${param.word}">
 <input type="hidden" name="nowPage" value="${param.nowPage}">
탈퇴를 하시면 더 이상 서비스를 이용하실 수 없습니다.<br>
그래도 탈퇴를 원하시면 비밀번호를 입력해 주세요.
 <input type="password" name="passwd" size="15">
  
    <input type='submit' value='탈퇴'>
    <input type='button' value='돌아가기' onclick="history.back()">
</FORM>
</div> 
 
</body>
</html> 