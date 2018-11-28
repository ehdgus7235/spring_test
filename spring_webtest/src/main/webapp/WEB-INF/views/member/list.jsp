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
function read(id){
	var url;
	url = "${root}/member/read";
	url += "?id="+id;
 	url += "&col=${col}";
	url += "&word=${word}";
	url += "&nowPage=${nowPage}";
	location.href=url;
}
</script>

</head> 
<body>
 
<!-- <DIV class="title">회원목록</DIV> -->
<div style="text-align:center">
<FORM name='frm' method='POST' action='./list'>
<select name="col">
<option value = "mname"
<c:if test="${col=='mname' }">selected='selected'</c:if>
>성명</option>
<option value = "email"
<c:if test="${col=='email' }">selected='selected'</c:if>
>이메일</option>
<option value = "id"
<c:if test="${col=='id' }">selected='selected'</c:if>
>아이디</option>
<option value="total">전체출력</option>
</select>
<input type="text" name = "word" value='${word }'>
<input type="submit" value="검색">
<input type="button" value="회원가입" onclick="location.href='${root}/member/agree'">
</form>
</div>

<div class="container">
<h5>
회원 목록
</h5>
<c:forEach var="dto" items="${list }">

  <TABLE class="table table-hover">
    <TR>
      <TD rowspan='5' width="30%">
      <img src='${root }/member/storage/${dto.fname }' width='250px' height='250px'>
      </TD>
      <TH width="20%">아이디</TH>
      <TD width="50%"><a href="javascript:read('${dto.id}')">${dto.id }</a></TD>
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
      <TH>거주지역</TH>
      <TD>${dto.address1 }</TD>
    </TR>
  </TABLE>
</c:forEach>
  
  <DIV class='bottom'>
    ${paging }
  </DIV>

 
 </div>
</body>
</html> 