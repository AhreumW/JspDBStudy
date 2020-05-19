<%@page import="java.util.Date"%>
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

	<%
		Date date = new Date();
	
		application.setAttribute("dateObj", date);
	
		System.out.println(date);
	%>
	
	
<body>

	<!-- Tue May 19 11:06:54 KST 2020 -->
<%-- 	<fmt:parseDate var="datePD" value="${dateObj}" --%>
<%-- 		pattern="E MMM dd HH:mm:ss z yyyy"/>	<!-- pattern으로 타입형식을 알려준다. -->  --%>
		
<%-- 	${datePD}	<br> --%>
	

	<!-- 05월 19일  -->
	<!-- 날짜를 원하는 형식으로 맞춰 출력시킴 -->
	<fmt:formatDate value="${dateObj}" pattern="M월d일"/>	<!-- 0 없어짐 -->

	
</body>

</html>