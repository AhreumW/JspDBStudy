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
<style type="text/css">
	
	div{
		border: 1px solid black;
	}
</style>
</head>

<%
	List<String> nameList = new ArrayList<>();
	
	nameList.add("고재민");
	nameList.add("원아름");
	nameList.add("박상아");
	nameList.add("차정경");
	
	pageContext.setAttribute("nameList", nameList);
%>
<body>
	
	
	<c:forEach var="name" items="${nameList}">
		<div>${name}</div>
	</c:forEach>


</body>

</html>