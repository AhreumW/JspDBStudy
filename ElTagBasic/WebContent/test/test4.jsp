<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<%
	List<String> strList = new ArrayList<>();
	
	for(int i=0; i<6; i++){
		strList.add("JSTL 예제");
	}
	
	request.setAttribute("strList", strList);
%>
<body>

<!-- 	<h1>JSTL 예저 1</h1> -->
<!-- 	<h2>JSTL 예저 2</h2> -->
<!-- 	<h1>JSTL 예저 3</h1> -->
<!-- 	<h1>JSTL 예저 4</h1> -->
<!-- 	<h1>JSTL 예저 5</h1> -->
<!-- 	<h1>JSTL 예저 6</h1> -->


	<!-- 문자와 변수를 분리시켜 저장하자 -->
	<c:forEach var="str" items="${strList}" varStatus="indexVal" begin="1" end="6">
		<h1>${str} ${indexVal.index}</h1>
	</c:forEach>


	<c:forEach varStatus="indexVal" begin="1" end="6">
		<h${indexVal.index}> JSTL 예제 ${indexVal.index} </h${indexVal.index}>
	</c:forEach>
	

</body>

</html>