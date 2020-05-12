<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function prePageFnc(){
		location.href='./userInput.jsp';
	}
</script>

</head>
<%!	//선언문 Declaration	//실제 현업에서는 사용하지 않는다. 비즈니스 로직은 컨트롤러에서~
	private String calculate(int a, int b, String op){
		int r = 0;
		
		if("+".equals(op)){
			r = a + b;
		}else if("-".equals(op)){
			r = a - b;
		}
		
		return r + "";
	}
%>
<%
	String v1 = "";
	String v2 = "";
// 	String result = "";
	String result = "";
	
// 	v1 = String.valueOf(10);
// 	v2 = String.valueOf(20);
// 	result = String.valueOf(Integer.parseInt(v1) + Integer.parseInt(v2));
// 	int result = Integer.parseInt(v1) + Integer.parseInt(v2);

	String[] selectedArr = {"","","",""};
	
	if(request.getParameter("v1") != null && request.getParameter("v2") != null){
		v1 = request.getParameter("v1");
		v2 = request.getParameter("v2");
		
		String op = request.getParameter("op");
		
		if( !("".equals(v1)) && !("".equals(v2))){
			result = calculate(Integer.parseInt(v1), Integer.parseInt(v2), op);
		}	//end second if
		
		if("+".equals(op)){
			selectedArr[0] = "selected";
		}else if("-".equals(op)){
			selectedArr[1] = "selected";
		}
		
		
	}//end first if
	
%>

<body>
			<!-- =을 달아줘야 출력이 된다. -->
	<%-- 결과값은 : <%= result + 2 %> --%>

	<!-- C:\workspaceAvdJsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\web04_280\org\apache\jsp -->
	<!-- jsp에 스크립트릿, 표현식이 있는 경우 서버에서 태그는 java로 번영되어 실행된다.  -->
	
	
	<h2>JSP 계산기 결과 페이지</h2>

	<input type="text" name="v1" size="4" value="<%=v1%>">
	<select name="op">
		<option value="+" <%=selectedArr[0] %>>+</option>
		<option value="-" <%=selectedArr[1] %>>-</option>
	</select>
	<input type="text" name="v2" size="4" value="<%=v2%>">
	<input type="button" value="=">
	<input type="text" size="8" value="<%=result%>">
	<input type="button" onclick="prePageFnc();" value="계산페이지 이동">
	
	
</body>

</html>