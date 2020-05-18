<%@page import="tg.com.UserVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>빈(Bean) 객체에서 값</title>

</head>

<%
	UserVo userVo1 = new UserVo(100, "홍길동", "hong@test.com");
	UserVo userVo2 = new UserVo(200, "아아름", "ar@test.com");
	
	session.setAttribute("userVo", userVo1);		 
	request.setAttribute("userVo", userVo2);	
	
// 	세션보관소, requeset보관소 객체를 같은 userVo이름으로 저장했음에도
// 	우선순위에 의해 가장 작은 단위 먼저 조회가 된다. -> request 보관소에 저장된 객체 
	
	
//	파라미터는 문자열 	//어트리뷰트는 객체 		전달
	request.getParameter("testtest");
//	리퀘스트에서 setParameter 불가
%>

<body>

	<!-- default는 가작 작은 보관소 순서  -->
	${userVo.userNo}	<br>
	${userVo.userName}	<br>
	${userVo.userEmail}	<br>
	${userVo}	<!-- toString도 조회가능 -->	<br>	
	
	<!-- 세션단위의 객체를 찾고 싶다면 sessionScope로 명시해서 조회하면 찾을 수 있다.  -->
	${sessionScope.userVo}		<br>	 <!-- 세션범위 -->
	${requestScope.userVo}		<br>	 <!-- 리퀘스트범위 -->


	<!-- 파라미터는 param으로 찾을수있다.  -->
	${param.testtest}

</body>

</html>