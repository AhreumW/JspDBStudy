<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<%
	String v1 = "";
	String v2 = "";
// 	String result = "";
	
	v1 = String.valueOf(10);
	v2 = String.valueOf(20);
// 	result = String.valueOf(Integer.parseInt(v1) + Integer.parseInt(v2));
	int result = Integer.parseInt(v1) + Integer.parseInt(v2);
%>

<body>
			<!-- =을 달아줘야 출력이 된다. -->
	결과값은 : <%= result %>

</body>

</html>