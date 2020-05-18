<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>회원정보</title>
<script type="text/javascript">

	function pageMoveDeleteFnc(mNo){
		location.href= "./delete?no=" + mNo;
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
	번호: <input type='text' name='mNo' value='${memberDto.no}'  
	 readonly='readonly'><br>
	이름: <input type='text' name='name' value='${memberDto.name}'><br> 
	이메일: <input type='text' name='email' value='${memberDto.email}'><br>
	가입일: ${requestScope.memberDto.createDate}<br>
	<input type='submit' value='저장'>
	 <input type='button' value='삭제' onclick='pageMoveDeleteFnc(${memberDto.no});'>
	 <input type='button' value='취소' onclick='pageMoveListFnc();'>
	</form> 
	
</body>

</html>