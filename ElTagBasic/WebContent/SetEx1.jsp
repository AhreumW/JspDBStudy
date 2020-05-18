<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>c:set 태그 - 변수를 생성하거나 기존 변수의 값을 덮어쓸 때 사용</title>

</head>

<!-- set으로 생성한 변수는 JSP 페이지의 로컬 변수가 아니라 보관소에 저장된다. -->

<%-- 표현식 발전 
1. <% %>
2. <jsp:setProperty property="" name=""/>
3. <c:set value=""></c:set> --%>


<body>
	
	<h3>값 설정 방식</h3>
	<c:set var="userName1" value="홍길동"/>
	<c:set var="userName2">바니프</c:set>
	
	- ${userName1}<br>
	- ${userName2}<br>
	
	<h3>기본 보관소</h3>
	- ${pageScope.userName1}<br>
	- ${requestScope.userName1}<br>
	
	<h3>보관소 지정 - scope 속성</h3>
	<c:set var="userName3" scope="request" value="라랄랄"></c:set>
	- ${pageScope.userName3}<br>
	- ${requestScope.userName3}<br>
	
	<h3>기존의 값 덮어씀</h3>
	<% pageContext.setAttribute("userName4", "바이너리"); %>
	기존 값 = ${userName4}<br>
	
	<c:set var="userName4" value="binary"/>
	덮어쓴 값 = ${userName4}<br>
	
	<!-- c:set 없으면 만들고 있으면 가져와 수정 -->

</body>

</html>