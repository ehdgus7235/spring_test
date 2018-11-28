<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
.rlist{
	line-height: 1.2em;
	font-size: 15px;
	font-weight: bold;
	text-align: left;
	border-style: solid;
	border-width: 1px;
	border-color: #AAAAAA;
	color: #000000;
	width: 50%;
	padding: 10px;
	margin: 20px auto;
}
.rcreate{
	font-size: 20px;
	font-weight: bold;
	text-align: left;
	border-style: solid;
	border-width: 1px;
	border-color: #AAAAAA;
	color: #000000;
	width: 50%;
	padding: 10px;
	margin: 20px auto;
}
</style>
<script type="text/javascript">
function blist(){
	var url = "list";
	url = url + "?col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
function bupdate(){
	var url = "update";
	url = url + "?bbsno=${dto.bbsno}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;
}
function bdel(){
	var url = "delete";
	url = url + "?bbsno=${dto.bbsno}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	url = url + "&oldfile=${dto.filename}";
	location.href = url;	
}
function breply(){
	var url = "reply";
	url = url + "?bbsno=${dto.bbsno}";
	url = url + "&col=${param.col}";
	url = url + "&word=${param.word}";
	url = url + "&nowPage=${param.nowPage}";
	location.href = url;	
}
function fileDown(){
	var url = "${root}/download";
	url = url + "?filename=${dto.filename}";
	url = url + "&dir=/bbs/storage";
	
	location.href=url;
}
function input(f){
	if('${sessionScope.id}'==''){
		if(confirm("로그인이 필요합니다.")){
			var url = "../member/login";	
			url += "?bbsno=${dto.bbsno}";
			url += "&nPage=${nPage}";
			url += "&col=${param.col}";
			url += "&word=${param.word}";
			url += "&nowPage=${param.nowPage}";
			url += "&flag=../bbs/read";
			
			location.href = url;
			return false;
		}else{
			return false;
		}
	}else if(f.content.value==''){
		alert("댓글 내용을 입력하세요.");
		f.content.focus();
		return false;
	}
}
function rupdate(rnum, content){
	var f = document.rform;
	f.content.value = content;
	f.rnum.value = rnum;
	f.rbutton.value = '수정';
	f.action = "./rupdate";
}
function rdelete(rnum){
	if(confirm("정말 삭제 하시겠습니까?")){
		var url = "./rdelete";
		url += "?rnum="+rnum;
		url += "&bbsno=${dto.bbsno}";
		url += "&col=${param.col}";
		url += "&word=${param.word}";
		url += "&nowPage=${param.nowPage}";
		url += "&nPage=${nPage}";
		
		location.href=url;
	}
}
</script>
</head> 

<body>

 
<DIV class="title">조회</DIV>
 
  <TABLE class="table">
    <TR>
      <TH>제목</TH>
      <TD>${dto.title}</TD>
    </TR>
    <TR>
      <TD colspan="2">${dto.content}</TD>
    </TR>
    <TR>
      <TH>성명</TH>
      <TD>${dto.wname}</TD>
    </TR>
    <TR>
      <TH>조회수</TH>
      <TD>${dto.viewcnt}</TD>
    </TR>
    <TR>
      <TH>등록일</TH>
      <TD>${dto.wdate}</TD>
    </TR>
    <TR>
      <TH>파일</TH>
      <TD>
      <c:choose>
      <c:when test="${not empty dto.filename}">
      <a href="javascript:fileDown()"> ${dto.filename }
			</a> 
			(${dto.filesize/1024} KB)
      </c:when>
      <c:otherwise>
      "No file"
      </c:otherwise>
      </c:choose>
	  </TD>
    </TR>
  </TABLE>
  
  <DIV class='bottom'>
    <input type='button' value='목록' onclick="blist()">
    <input type='button' value='등록' onclick="location.href='create'">
    <button onclick="bupdate()">수정</button>
    <button onclick="bdel()">삭제</button>
    <button onclick="breply()">답변</button>
  </DIV>

	<hr>
	
	<c:forEach var="rdto" items="${rlist }">
		<div class="rlist">
			${rdto.id }<br>
			<p>${rdto.content }</p>
			${rdto.regdate }
			<c:if test="${sessionScope.id==rdto.id }">
			<span style="float:right">
			<a href="javascript:rupdate('${rdto.rnum }','${rdto.content }')">수정</a> | 
			<a href="javascript:rdelete('${rdto.rnum }')">삭제</a>
			</span>
			</c:if>
		</div>	
	</c:forEach>
	
	<div class="rcreate">
		<form name="rform" action="./rcreate" method="post" onsubmit="return input(this)">
			<textarea rows="3" cols="40"name="content"></textarea>
			<input type="submit" style="float:right" name="rbutton" value="등록">
			
			
			<input type="hidden" name="id" value="${sessionScope.id}">
			<input type="hidden" name="bbsno" value="${dto.bbsno}">
			<input type="hidden" name="nPage" value="${nPage}">
			<input type="hidden" name="nowPage" value="${param.nowPage}">
			<input type="hidden" name="col" value="${param.col}">
			<input type="hidden" name="word" value="${param.word}">
			<input type="hidden" name="rnum" value="${0}">
		</form>
	</div>

	<div style="text-align:center">${paging }</div>
	
</body>
</html> 
