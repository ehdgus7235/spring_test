<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h2>답변</h2>
		<hr>
		<form action="./reply" method="post" enctype="multipart/form-data">
			
			<input type="hidden" name="ref" value="${dto.ref }">
			<input type="hidden" name="indent" value="${dto.indent}">
			<input type="hidden" name="ansnum" value="${dto.ansnum}">
			<input type="hidden" name="num" value="${dto.num}">

			<input type="hidden" name="col" value="${param.col}">
			<input type="hidden" name="word" value="${param.word}">
			<input type="hidden" name="nowPage" value="${param.nowPage}">
			
			<div class="form-group">
				<label for="name">성명</label> <input type="text" class="form-control"
					id="name" placeholder="Enter name" name="name">
			</div>
			<div class="form-group">
				<label for="subject">제목</label> <input type="text"
					class="form-control" id="subject" placeholder="Enter subject"
					name="subject">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textArea rows="10" class="form-control" id="content"
					placeholder="Enter content" name="content"></textArea>
			</div>
			<div class="form-group">
				<label for="pwd">비밀번호</label> <input type="password"
					class="form-control" id="pwd" placeholder="Enter password"
					name="passwd">
			</div>
			<div class="form-group">
				<label for="file">파일</label> <input type="file" class="form-control"
					id="file" placeholder="Enter file" name="filenameMF">
			</div>
			<button type="reset" class="btn btn-primary">취소</button>
			<button type="submit" class="btn btn-primary">답변</button>
		</form>
	</div>
</body>
</html>