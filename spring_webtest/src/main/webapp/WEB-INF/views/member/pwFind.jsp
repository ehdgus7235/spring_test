<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String pw = (String)request.getAttribute("pw");

	if(pw!=null){
		out.print("찾으시는 비밀번호는 "+pw+"입니다.");
	}else{
		out.print("잘못된 정보를 입력하셨습니다.");
	}
%>