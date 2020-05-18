<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>c:out 태그 - 출력문을 만든다.</title>

</head>

<%-- 표현식 발전 
1. <%= %>
2. <jsp:getProperty property="" name=""/>
3. <c:out value=""></c:out> --%>


<body>
	
	1) <c:out value="출력할 값" /><br>
	2) <c:out value="출력할 값">기본값</c:out>	-> value가 더 강력하게 나타남 <br>
	3) <c:out value="${null}">반가워요</c:out>-> value 공백시 태그 사이문자열이 출력됨<br>
	4) <c:out value="안녕하세요">반가워요</c:out><br>
	5) <c:out value="${null}"></c:out>	-> 아무것도 출력되지 않음
	

</body>

</html>