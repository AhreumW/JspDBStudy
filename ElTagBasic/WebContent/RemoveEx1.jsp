<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>c:remove - 보관소에 저장된 값을 제거하는 태그</title>

</head>

<!-- scope의 기본값은 page이다.  -->

<body>
	
	<h3>보관소에 저장된 값 제거</h3>
	<c:set var="userName1" value="홍길동"/>
	1 ${userName1}	<br>
	
	<c:remove var="userName1"/>
	2 ${userName1}	<br>
	
	

</body>

</html>