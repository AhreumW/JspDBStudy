<%@page import="tg.com.UserVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>빈(Bean) 객체에서 값</title>

</head>

<%
	UserVo userVo = new UserVo(100, "홍길동", "hong@test.com");

	session.setAttribute("userVo", userVo);
%>

<body>

	${userVo.userNo}	<br>
	${userVo.userName}	<br>
	${userVo.userEmail}	<br>
	${userVo}	<!-- toString도 조회가능 -->

</body>

</html>