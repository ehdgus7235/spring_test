<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%
	request.setCharacterEncoding("utf-8");
	String url = "Jdbc:oracle:thin:@127.0.0.1:1521:XE";
	String user = "soldesk";
	String password = "1234";
	String driver = "oracle.jdbc.driver.OracleDriver";
	Class.forName(driver);
	Connection con = DriverManager.getConnection(url, user, password);
	StringBuffer sql = new StringBuffer();
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
%>

