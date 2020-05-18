<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>c:if - 속성 값이 참이면 컨텐츠 실행</title>

</head>


<body>
	
<%--	<c:if test="조건" var="변수명" scope="보관소4가지">	- var, scope는 선택사항
			컨텐츠(수행될 문장들)	
		</c:if> --%>
	
	
	<c:if test="${10 > 20}" var="result">
		1) 10은 20보다 크다<br>		<!-- if문이 false이기 때문에 출력되지 않는다!!!! -->
	</c:if>
	${result}<br>	<!-- false -->
	
	<c:if test="${10 < 20}" var="result2">
		2) 10은 20보다 작다<br>
	</c:if>
	${result2}<br>	<!-- true -->
	
	
</body>

</html>