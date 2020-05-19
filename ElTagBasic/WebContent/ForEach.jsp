<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>c:forEach 반복적인 작업을 정의할 때</title>

</head>

<!-- 	문법 
	c:forEach var="변수명" items="목록데이터" 
		begin="시작인덱스" end="종료인덱스" step="인덱스간격" varStatus="인덱스값"
		내용들	
	/c:forEach
-->
<%
	List<String> nameList = new ArrayList<>();
	
	nameList.add("홍길동");
	nameList.add("스텔라");
	nameList.add("카트린느");
	
	request.setAttribute("nameList", nameList);
%>

<body>

	<ul>	<!-- 복수의 데이터(nameList)가 확장된for문처럼 name 변수에 하나하나 들어가서 출력된다. -->
		<c:forEach var="name" items="${nameList}">
			<li>${name}</li>
		</c:forEach>
	</ul>
	
	var ${name}은 c:forEach태그 안의 지역변수로서  태그가 끝나면 소멸한다.
	
	<div>	 
		<c:forEach var="name" items="${nameList}" varStatus="indexVal">
			<span>${indexVal.index} ) </span><span>${name}</span>
		</c:forEach>
	</div>
	
<%-- 	${status.current}<br/>      <!– 현재 아이템 –> --%>
<%-- 	${status.index}<br/>        <!– 0부터의 순서 –> --%>
<%-- 	${status.count}<br/>        <!– 1부터의 순서 –> --%>
<%-- 	${status.first}<br/>        <!– 현재 루프가 처음인지 반환 –> --%>
<%-- 	${status.last}<br/>         <!– 현재 루프가 마지막인지 반환 –>  --%>
<%-- 	${status.begin}<br/>        <!– 시작값 –> --%>
<%-- 	${status.end}<br/>          <!– 끝값 –> --%>
<%-- 	${status.step}<br/>         <!– 증가값 –> --%>

	
	
	
</body>

</html>