<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>로그인</title>

</head>

<body>

	<h2>사용자 로그인</h2>
	
	<!-- 실행은 컨트롤러로 보내자~ -->
	<!-- form action = 다음 경로 -->
	<form action="./login" method="post">
		이메일: <input type="text" name="email"><br>
		암호: <input type="password" name="password"><br>
			<input type="submit" value="로그인"> 
	</form>
	
	

</body>

</html>