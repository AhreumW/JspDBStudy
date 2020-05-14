<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	function listPageMoveFnc(){	
		location.href = '<%=request.getContextPath()%>/auth/login'; 
// 		location.href = '/web05_312/auth/login'; 
	}
</script>

</head>

<%-- <%
	pageContext.setAttribute("d", "d");	//String f 같이 지역변수 같음
	
	//<form>태그없이도 데이터를 전달할 수 있다. 
	request.setAttribute("a", "a");
	session.setAttribute("b", "b");		//로그인,로그아웃 등 invalidate만 안하면 언제 어디서든 접근가능
	application.setAttribute("c", "c");	//운영서버와 시작과끝이 같다.
%> --%>

<body>
	
	<p onclick='listPageMoveFnc();'>312 페이지 / 안녕하세요</p>
	

</body>

</html>