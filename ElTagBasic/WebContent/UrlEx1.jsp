<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>c:url url을 만들 때 사용</title>

</head>


<body>

	<c:url var="htmlUrl" value="http://localhost:9123/ElTagBasic/OutEx1.jsp">
		<c:param name="v1" value="20"/>
		<c:param name="v2" value="30"/>
	</c:url>
	
	<!-- url에 param을 가지고 주소이동 -->
	<!-- http://localhost:9123/ElTagBasic/OutEx1.jsp?v1=20&v2=30 -->
	<!-- 로 이동함 -->
	<a href="${htmlUrl}">다른 페이지 이동</a>
	
</body>

</html>