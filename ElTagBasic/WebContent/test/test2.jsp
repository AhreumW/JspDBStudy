<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>조건2</title>

</head>

<body>

	<!-- 이름, 나이 변수 + 짝 로 만들어서 출력 -->
	<!-- 나와 짝 나이 비교해서 많은 사람 이름 출력  -->
	<c:set var="myName" value="원아름"/>
	<c:set var="myAge" value="29"/>

	<c:set var="friendName" value="이환상"/>
	<c:set var="friendAge" value="29"/>
	
	<c:if test="${myAge > friendAge}">
		${myName} 누나
	</c:if>
	
	<c:if test="${myAge == friendAge}">	
		${myName} ${friendName} 우리는 친구
	</c:if>
	
	<c:if test="${myAge < friendAge}">	
		${friendName} 오빠
	</c:if>

</body>

</html>