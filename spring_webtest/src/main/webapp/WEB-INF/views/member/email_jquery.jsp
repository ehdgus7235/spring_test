<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../ssi/ssi.jsp"%>
<%
	boolean flag = (boolean)request.getAttribute("flag");

	if (flag) {
		out.print("Email 중복");
	} else {
		out.print("Email 사용 가능");
	}
%>