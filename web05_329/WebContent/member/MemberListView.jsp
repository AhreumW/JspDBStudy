<%@page import="spms.dto.MemberDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	<jsp:useBean 
		id="memberList" 
		scope="request" 
		class="java.util.ArrayList"
		type="java.util.ArrayList<spms.dto.MemberDto>"									
	/>
	
<%-- 	<jsp:useBean  --%>
<%-- 		id="testActionTag" --%>
<%-- 		scope="request" --%>
<%-- 		class="spms.dto.MemberDto"	 --%>
<%-- 	/> --%>
	
<%-- 	<jsp:setProperty property="password" name="testActionTag" --%>
<%-- 		value="OhOhOh"/> --%>
<%-- 	<jsp:getProperty property="password" name="testActionTag"/> --%>
	
<!-- 	<br/> -->
	
	<%
		//System.out.println(testActionTag);
	
		//확장된 for문 - 임시변수 선언 : 대상배열명
		for(MemberDto memberDto : memberList){	 
	%>
			<%=memberDto.getNo()%>,
			<a href='update?no=<%=memberDto.getNo() %>'>
				<%=memberDto.getName()%>
			</a>,
			<%=memberDto.getEmail()%>,
			<%=memberDto.getCreateDate()%>
			<a href='delete?no=<%=memberDto.getNo()%>'>[ 삭제 ]</a>
			<br/>
	<%
		} 
	%>	
		
	<jsp:include page="/Tail.jsp"/>		
			
</body>

</html>