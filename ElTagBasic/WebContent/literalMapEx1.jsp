<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Map 객체에서 값 꺼내기</title>

</head>

<%
	Map<String, String> nameMap = new HashMap<>();
	
	nameMap.put("s01","홍길동");
	nameMap.put("s02","스텔라");
	nameMap.put("s03","카트린느");
	
	request.setAttribute("nameMap", nameMap);
	//보관소 레벨에 들어있다면
	//key를 통해서 el태그로 찾을 수 있다. 
	
	//만약 key가 없다면
	//null로서 오류가 뜨는 것이 아닌 그냥 빈값으로 화면에 나타나지 않는다. 
	
%>

<body>

	${nameMap["s01"]}
	${nameMap["s02"]}
	${nameMap.s03}		<!-- 이 방식을 주로 사용 -->
	${nameMap["홍길동"]}	<!-- value가 아닌 key만 조회됨  -->

	

</body>

</html>