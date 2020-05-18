<%@page import="tg.com.UserVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>관계 연산자</title>

</head>

<body>

<!-- 글자표기법도 알아두기 -->

	${10 == 11}		<br>    <!-- false -->
	${10 eq 11}		<br>    <!-- false -->
	${10 != 11}		<br>    <!-- true -->
<%-- 	${10 ne 11}		<br>    <!-- true --> --%>
	
	${10 < 11}		<br>    <!-- true -->
	${10 lt 11}		<br>    <!-- true -->
	${10 lt 11 + -10}	<br>    <!-- false -->
	${10 > 11}		<br>    <!-- false  -->
	${10 gt 11}		<br>    <!-- false  -->
	
	${10 <= 11}		<br>    <!-- true -->  
	${10 le 11}		<br>    <!-- true -->  
	${10 >= 11}		<br>    <!-- false --> 
	${10 ge 11}		<br>    <!-- false --> 
	
	
</body>

</html>