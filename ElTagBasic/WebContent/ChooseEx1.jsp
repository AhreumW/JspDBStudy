<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>c:choose 태그 - 조건문2</title>

</head>
<!-- 	여러가지 다른 작업을 해야 할 때 이 태그를 사용한다.  -->
	
<body>
	
	<c:set var="userId" value="admin"/>
	
	<c:choose>
		<c:when test="${userId == 'hong'}">
			홍길동님 반갑습니다.
		</c:when>
		<c:when test="${userId == 'guest'}">
			guest님 반갑습니다.
		</c:when>
		<c:when test="${userId == 'admin'}">
			관리자 전용 페이지입니다.
		</c:when>
		
		<c:otherwise>
			등록되지 않은 사용자입니다.
		</c:otherwise>
		
	</c:choose>
	

</body>

</html>