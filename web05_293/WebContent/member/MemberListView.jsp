<!-- < % @ : 지시자 -->
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

	<h1>회원목록</h1>
	<p>
		<a href='add'>신규 회원</a>
	</p>
	
	<!-- 순차수행 -->
	<!-- 스크립트렛,릿 : scriptlet -->
	<!-- 스크립트릿 안에는 태그를 적을 수 없으며, 태그를 적기위해서는 스크립트렛을 닫고 바깥에 적어야한다.  -->
	<%
		ArrayList<MemberDto> memberList = 
			(ArrayList<MemberDto>)request.getAttribute("memberList");
	
		//확장된 for문 - 임시변수 선언 : 대상배열명
		for(MemberDto memberDto : memberList){	 
	%><!-- 여기까지의 자바코드는 화면에 나타나지 않으며 -->
			<!-- =이 붙은 Expression은 html에 나타난다. -->
			<%=memberDto.getNo()%>,
			<a href='update?no=<%=memberDto.getNo() %>'>
				<%=memberDto.getName()%>
			</a>,
			<%=memberDto.getEmail()%>,
			<%=memberDto.getCreateDate()%>
			<a href='.delete?no=<%=memberDto.getNo()%>'>[ 삭제 ]</a>
			<br/>
	<%
		}
	%>		
			
			
</body>

</html>