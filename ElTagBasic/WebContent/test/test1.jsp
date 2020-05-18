<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>조건1</title>

</head>

<body>

	<!-- 이름, 나이, 변수로 만들어서 출력 -->
	<c:set var="name" value="원아름"/>
	<c:set var="age" value="27"/>

	${name} <br>
	${age}

</body>

</html>