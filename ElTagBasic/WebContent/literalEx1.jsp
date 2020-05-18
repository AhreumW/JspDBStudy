<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>리터럴 표현식</title>

</head>

<body>

	문자열: 		${"test"}	<br/>
	문자열: 		${'test'}	<br/>
	문자열: 		${'test'}${"test"}	<br/>
	문자열: 		${'3'+''+4}<br/>
	점수: 		${20}		<br/>
	점수: 		${20+100*2}		<br/>
	부동소수점: 	${3.14}		<br/>
	참거짓: 		${true}		<br/>
	null: 		${null} = ${''}		<br/>
	<!-- null이라는 값이 들어와도 알아서 validation 처리해줌 -->
</body>

</html>