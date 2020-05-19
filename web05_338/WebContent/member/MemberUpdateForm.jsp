<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>회원정보</title>
<script type="text/javascript">

	function pageMoveDeleteFnc(no){
		var url= "./delete?no=" + no;
		location.href= url;
	}
	
	function pageMoveListFnc(){
		location.href= "./list";
	}
</script>
</head>

<body>
	
	<!-- 이제 현업에서도 쓰는 방식 -->
	
	<h1>회원정보</h1> 
	<form action='./update' method='post'>
		번호: <input type='text' name='no' value='${memberDto.no}'  
		 		readonly='readonly'><br>
		이름: <input type='text' name='name' value='${memberDto.name}'><br> 
		이메일: <input type='text' name='email' value='${memberDto.email}'><br>
		가입일: ${requestScope.memberDto.createDate}<br>
		<input type='submit' value='저장'>
		<input type='button' value='삭제' onclick='pageMoveDeleteFnc(${memberDto.no});'>
		<input type='button' value='취소' onclick='pageMoveListFnc();'>
	</form> 
	
	<!-- requestScope.memberDto.createDate -->
	<!-- 이렇게 선언해서 적으면 바로 찾아가기 때문에 속도는 빠르나 가독성은 떨어질수있음 -->
	<!-- sessionScope에 담은경우는 구분을 위해 적기도 함 (주로 requestScope로 사용하니까)-->	
	
</body>

</html>