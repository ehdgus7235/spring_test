<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<div class="container">
<c:choose>
<c:when test="${flag }">
답변글이 존재합니다<br>
부모글을 삭제할 수 없습니다.<br>
<button onclick="location.href='list.do'">목록</button>
</c:when>

<c:otherwise>
삭제하면 복구할 수 없습니다.
 <h2>글삭제</h2>
  <hr>
  <form action="./delete" method="post">
  
  <input type = "hidden" name = "num" value="${param.num }">
  <input type = "hidden" name = "oldfile" value="${param.filename }">
  <input type = "hidden" name = "col" value="${param.col }">
  <input type = "hidden" name = "word" value="${param.word }">
  <input type = "hidden" name = "nowPage" value="${param.nowPage }">
 
    <div class="form-group">
      <label for="passwd">비밀번호</label>
      <input type="password" class="form-control" id="passwd" placeholder="Enter name" name="passwd">
    </div>
    <button type="reset" class="btn btn-primary">취소</button>
    <button type="submit" class="btn btn-primary">삭제</button>
  </form>
  </c:otherwise>
</c:choose>
 
</div>
</body>
</html>