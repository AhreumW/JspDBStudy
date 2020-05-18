<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>c:choose 태그 - 조건문2</title>

</head>

	
<body>
	
	<c:set var="season" value="5"/>
	
	<c:choose>
		<c:when test="${season >= 3 && season <= 5}">
			<c:set var="result" value="봄"/>
		</c:when>
		<c:when test="${season >= 6 && season <= 8}">
			<c:set var="result" value="여름"/>
		</c:when>
		<c:when test="${season >= 9 && season <= 11}">
			<c:set var="result" value="가을"/>
		</c:when>
		<c:when test="${(season == 12) || (season >= 1 && season <= 2)}">
			<c:set var="result" value="겨울"/>
		</c:when>
		
		<c:otherwise>
			<c:set var="result" value="1년은 1 ~ 12월까지 입니다."/>
		</c:otherwise>
		
	</c:choose>
	
	${season}월의 계절은 무엇입니까? <br>
	= ${result}
	

</body>

</html>