<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
	
	<jsp:include page="/Header.jsp"/>
	
	<h1>회원목록</h1>
	<p>
		<a href='add'>신규 회원</a>
	</p>
	
	<!-- 이제 현업에서도 쓰는 방식	 -->
	<c:forEach var="memberDto" items="${memberList}">
		
		${memberDto.no},
		<a href='update?no=${memberDto.no}'>
			${memberDto.name}
		</a>,
		${memberDto.email},
		${memberDto.createDate}
		<a href='delete?no=${memberDto.no}'>[ 삭제 ]</a>
		<br/>
	
	</c:forEach>
	
	<jsp:include page="/Tail.jsp"/>		
			
</body>

</html>