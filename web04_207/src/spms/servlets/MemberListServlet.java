package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

//어노테이션 @	- 메서드단위 어노테이션, 클래스단위 어노테이션
//			- 해당 메서도, 클래스 바로 위에 작성한다. 
//@WebServlet("/member/list") 
// : web.xml에 서블릿을 추가 하지 않고 그 대신 사용할 수 있다. 
//<servlet>, <servlet-mapping>의 기능
//- 요즘은 주로  web.xml보다  어노테이션 사용
@WebServlet("/member/list")		//url 매핑 
public class MemberListServlet extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) 
			throws ServletException, IOException {
		
//		데이터베이스 관련 객체 변수 선언
		Connection conn = null;		//연결  
		Statement stmt = null;		//상태 
		ResultSet rs = null;		//결과 
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	
		String user = "jsp";
		String password = "jsp12";
		
		try {
//			클래스 로드
//			1. jdbc드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2. 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);
			
//			3. sql 실행 객체 준비
			stmt = conn.createStatement();
			
			//sql문은 대문자로 작성해주기 
			//단축키 : 대문자 변환 ctrl+shift+x	소문자는 +y
			String sql = "SELECT MNO, MNAME, EMAIL, CRE_DATE"
					+ " FROM MEMBER"
					+ " ORDER BY MNO ASC";
			
//			sql 실행문
//			4. 결과 가져오기
			rs = stmt.executeQuery(sql);
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			PrintWriter out = response.getWriter();
			
			String htmlStr = "";
			//추가 
			htmlStr += "<p>";
			//a태그는 기본적으로 doGet을 호출한다. 
			htmlStr += "<a href='./add'>신규회원";	//상대경로 
			htmlStr += "</a>";
			htmlStr += "</p>";
			
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body><h1>회원목록</h1>");
			out.println(htmlStr);
			
//			5. 출력
			while(rs.next()) {
				out.println(
					"<div>"	+
					rs.getInt("MNO") + ", "
					+"<a href='./update?mNo=" + rs.getString("MNO")+"'>" + 
					rs.getString("MNAME") + "</a>, " + 
					rs.getString("EMAIL") + ", " + 
					rs.getDate("CRE_DATE") + "</div>"
				);
			}
			out.println("</body></html>");
			
		} catch (ClassNotFoundException e) {
			 
			e.printStackTrace();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}finally {
			//6. 자원 해제 
			
			//결과셋 해제
			if(rs != null) {	//null이 아닌 경우=객체가 존재하는경우, close시킨다.
				try {
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			//상태 해제
			if(stmt != null) {
				try {
					stmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			//연결 해제
			if(conn != null) {
				try {
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		
		} // finally 종료
		
		
	}// service end

}
