<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 15px; 
} 
.img {
  max-width: 800px;
  height: auto; 
}

</style> 
<script type="text/javascript">
function getThumbnailPrivew(input, targetId) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var element = window.document.getElementById(targetId);
            element.setAttribute("src", e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}




function inputCheck(f){
	if(f.fname.value==""){
		alert("업로드할 이미지를 선택해주세요");
		f.fname.focus();
		
		return false;
	}
	if(f.title.value==""){
		alert("제목을 입력해주세요");
		f.title.focus();
		
		return false;
	}
	if(f.writer.value==""){
		alert("작성자 이름을 입력해주세요");
		f.writer.focus();
		
		return false;
	}
	if(f.content.value==""){
		alert("내용을 입력해주세요");
		f.content.focus();
		
		return false;
	}
	if(f.pw.value==""){
		alert("비밀번호를 입력해주세요");
		f.pw.focus();
		
		return false;
	}
	
	
}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<div class="container">
<h2><span class="glyphicon glyphicon-cloud-upload"></span>사진올리기</h2>
 
<FORM name='frm' method='POST' action='./create'
	enctype="multipart/form-data" onsubmit="return inputCheck(this)">

  <TABLE>
    <tr>
   	  <td rowspan='6' style="width:800px; height:600px; overflow:hidden; text-align:center">
   	  <img class="img" alt="미리보기" src="${root}/gallery/storage/default.jpg" id="thumbnail_image">
   	</td>
   	</tr>
    <TR>    
      <TD><input type="file"  name="fnameMF" accept=".jpg,.png,.gif" onchange="getThumbnailPrivew(this, 'thumbnail_image');"></TD>
    </TR>
    <TR>
      <TD>
      <div class="form-group" >
					 <label for="title">Title:</label>	
      <input type="text" name="title" size="50" placeholder="Title" style="width:400px"></div></TD>
	</TR>
    <TR>
     
      <TD>
      <div class="form-group" >
					 <label for="content">Content:</label>	
      <textarea rows="10" cols="50" name="content" placeholder="Content" style="width:400px; resize: none;"></textarea></div></TD>
	</TR>
    <TR>
   
      <TD>
      <div class="form-group" >
					 <label for="usr">Writer:</label>	
      <input type="text" name="writer" size="20" placeholder="Writer" style="width:400px"></div></TD>
    </TR>
    <TR>
 
      <TD><input type="password" name="pw" size="15" placeholder="password" style="width:400px"></TD>
    </TR>
  </TABLE>
  
  <br>
  
  <DIV class='bottom' style="text-align:center">
    <button type="submit" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-pencil"></span> 등록</button>
    <button type="button" class="btn btn-default btn-sm" onclick="history.back()"><span class="glyphicon glyphicon-repeat"></span> 뒤로가기</button>
    
  </DIV>
</FORM>
 
 </div>
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 