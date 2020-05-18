<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>배열에서 값 꺼내기</title>

</head>

<%
	pageContext.setAttribute("scoreArr", new int[]{
			90, 80, 70, 100
	});

	pageContext.setAttribute("nameArr", new String[]{
			"고재민", "원아름", "박상아", "차정경"
	});
%>

<body>

<!-- get만 EL태그 가능 -->
	${scoreArr[2]}	<br/>
	
	${nameArr[1]}

</body>

</html>