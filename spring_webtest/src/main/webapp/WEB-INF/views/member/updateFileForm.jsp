<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script type="text/javascript">
function inCheck(f){
	if(f.fname.value==""){
		alert("사진을 선택하세요");
		f.name.focus();
		return false;
	}
}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<body>
<div class="container">
<h5>사진수정</h5>
 
<FORM name='frm' method='POST' action='./updateFile' enctype="multipart/form-data"
	onsubmit = "return inCheck(this)">
	<input type="hidden" name="id" value="${param.id}">
	<input type="hidden" name="oldfile" value="${param.oldfile}">
  <TABLE>
    <tr>
      <Td colspan="2" style="text-align:center">
      <img src="./storage/${param.oldfile}">
	</Td>
    <tr>
      <th>파일</th>
      <td>
      <input type="file" name="fnameMF" required="required" accept=".png,.gif,.jpg">
      </td>
     </tr>
  </TABLE>
  
  
  <DIV class='bottom'>
    <input type='submit' value='변경'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 
 </div>
</body>
</html> 