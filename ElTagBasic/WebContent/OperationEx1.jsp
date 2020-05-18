<%@page import="tg.com.UserVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>산술 연산자</title>

</head>

<%
	
%>

<body>

	<!-- 앞에 \를 붙이면 문자열로 인식됨 -->
	
	\${10+20} = ${10+20}	<br>
	\${10-20} = ${10-20}	<br>
<%-- 	\${10 div 20} = ${10 div 20}	: 나누기 <br>	<!-- 0.5 --> --%>
	\${10 % 20} = ${10 % 20}		: 나머지 <br>	<!-- 10 -->
	\${10 mod 20} = ${10 mod 20}	: 나머지 <br>	<!-- 10 -->
	
	<!-- div, mod 처럼 아예 글자로서 더 쉽게 인식하게 하는 것도 좋은 방법 -->
	
	
</body>

</html>