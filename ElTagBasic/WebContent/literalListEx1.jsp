<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>List 객체에서 값</title>

</head>

<%
	List<String> nameList = new ArrayList<>();
	
	nameList.add("홍길동");
	nameList.add("스텔라");
	nameList.add("카트린느");
	
	request.setAttribute("nameList", nameList);
	//보관소 레벨에 들어있다면
	//key를 통해서 el태그로 찾을 수 있다. 
	
	//만약 key가 없다면
	//null로서 오류가 뜨는 것이 아닌 그냥 빈값으로 화면에 나타나지 않는다. 
	
%>

<body>

	${nameList[1]}
	${nameList[2]}

</body>

</html>