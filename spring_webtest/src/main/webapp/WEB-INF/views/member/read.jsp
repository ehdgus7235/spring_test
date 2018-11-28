<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
</style> 
<script type="text/javascript">
function updateFile(){
	var url = "updateFile";
	url += "?id=${dto.id}";
	url += "&oldfile=${dto.fname}";
	location.href=url;
}
function updatePasswd(){
	var url = "updatePasswd";
	url += "?id=${dto.id}";
	location.href=url;
}
function mupdate(){
	var url = "update";
	url += "?id=${dto.id}";
	url += "&col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}"
	location.href=url;
}
function mdel(){
	var url = "delete";
	url = url + "?id=${dto.id}";
	url += "&col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}"
	location.href=url;
}

</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<body>
 <div class="container">
<h2>${dto.mname}님의 회원정보 </h2>
 

  <TABLE>
    <tr>
    <td colspan="2" style="text-align:center">
    <img src="${root }/member/storage/${dto.fname }">
    </td>
    </tr>
    <TR>
      <TH>아이디</TH>
      <TD>${dto.id }</TD>
    </TR>
    <TR>
      <TH>성명</TH>
      <TD>${dto.mname }</TD>
    </TR>
    <TR>
      <TH>전화번호</TH>
      <TD>${dto.tel }</TD>
    </TR>
    <TR>
      <TH>이메일</TH>
      <TD>${dto.email }</TD>
    </TR>
    <TR>
      <TH>우편번호</TH>
      <TD>${dto.zipcode }</TD>
    </TR>
    <TR>
      <TH>주소</TH>
      <TD>
      ${dto.address1 }
      (${dto.address2 })
      </TD>
    </TR>
    <TR>
      <TH>직업</TH>
      <TD>
      직업코드 : ${dto.job }
      (${util:jobName(dto.job) })
      </TD>
    </TR>
    <TR>
      <TH>가입날짜</TH>
      <TD>${dto.mdate }</TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='button' value='정보수정' onclick="mupdate()">
    <input type='button' value='회원탈퇴' onclick="mdel()">
  	<c:if test="${not empty sessionScope.id && sessionScope.grade !='A' }">
	    <input type='button' value='사진수정' onclick="updateFile()">
	    <input type='button' value='패스워드 변경' onclick="updatePasswd()">
    </c:if>
  </DIV>

 </div>
 
</body>
</html> 