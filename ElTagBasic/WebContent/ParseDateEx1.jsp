<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>fmf:parseDate - 날짜 형식으로 작성된 문자열을 분석하여 
		java.util.Date 객체를 생성한다. </title>

</head>

<body>

	<fmt:parseDate var="date" value="2020-05-19"
		pattern="yyyy-MM-dd"/>	<!-- pattern으로 타입형식을 알려준다. -->
		
	${date}	<br>
	
	<!-- 날짜를 원하는 형식으로 맞춰 출력시킴 -->
	<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>
	
</body>

</html>