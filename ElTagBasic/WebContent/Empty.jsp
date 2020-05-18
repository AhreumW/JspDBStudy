<%@page import="tg.com.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Empty 연산자</title>

</head>
	<%
		pageContext.setAttribute("title", "EL 연산자!");
		pageContext.setAttribute("titleTest", "");
		
		UserVo userVo = new UserVo();
// 		session.setAttribute("userVo", userVo);		//다른 jsp코드에 세션에서 객체가 살아있음
	%>
	
	<!-- 	empty는 값이 비어있거나 null인지를 조사할 때 사용하는 연산자이다
			값이  null이면 true를 반환한다.
			또한 문자열과 배열, Map, List 객체의 크기가 0인 경우에도 
			true를 반환한다.
			그 외에는 false를 반환한다.  -->
<body>
	
	<!-- empty 예약어 -->
	
	${empty title}		<br>	<!-- false -->
	${empty title2}		<br>	<!-- undefined : true -->
	${empty titleTest}	<br>	<!-- length가 0인 경우 : true -->

	${empty userVo}		<br>	 <!-- *** false *** -->
	${sessionScope.userVo}		<br>	 <!-- 세션범위 -->
	
</body>

</html>