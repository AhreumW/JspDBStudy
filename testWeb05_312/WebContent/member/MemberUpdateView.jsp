<%@page import="spms.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>회원정보</title>

</head>

<body>
	
	<% MemberDto member = (MemberDto)request.getAttribute("member"); %>
	
	<jsp:include page="/Header.jsp"/>
	
<%-- 	<form action='./update' method='post'>
		번호: <input type='text' name='mNo' value='<%=request.getAttribute("mNo")%>' readonly='readonly'><br>
		이름: <input type='text' name='name' value='<%=request.getAttribute("mName")%>'><br> 
		이메일: <input type='text' name='email' value='<%=request.getAttribute("email")%>'><br>
		가입일: <%=request.getAttribute("creDate")%> <br>
		
		 <input type='submit' value='저장'>
		 <input type='button' value='삭제' onclick='location.href="./delete?no=<%=request.getAttribute("mNo")%>"'>
		 <input type='button' value='취소' onclick='location.href="./list"'>
	</form> --%>
	
	<form action='./update' method='post'>
		번호: <input type='text' name='mNo' value='<%=member.getNo()%>' readonly='readonly'><br>
		이름: <input type='text' name='name' value='<%=member.getName()%>'><br> 
		이메일: <input type='text' name='email' value='<%=member.getEmail()%>'><br>
		가입일: <%=member.getCreateDate()%> <br>
		
		 <input type='submit' value='저장'>
		 <input type='button' value='삭제' onclick='location.href="./delete?no=<%=member.getNo()%>"'>
		 <input type='button' value='취소' onclick='location.href="./list"'>
	</form>

	<jsp:include page="/Tail.jsp"/>		
			
</body>

</html>