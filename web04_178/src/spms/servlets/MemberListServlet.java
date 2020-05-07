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

/*
	서블릿		implements Servlet
	5개 만든다. 
	- init(), service(), destroy(), getServletConfig(), getServletInfo()
	
	제너릭서블릿	 extends GenericServlet
	서비스만 구현시키면 된다.
*/
public class MemberListServlet extends GenericServlet{

	//ServletRequest request, ServletResponse response로 응답을 주고 받는다. 
	@Override
	public void service(ServletRequest request, ServletResponse response) 
			throws ServletException, IOException {
		
//		데이터베이스 관련 객체 변수 선언
		//import java.sql.
		Connection conn = null;		//연결		- 세션 관리 
		Statement stmt = null;		//상태		- sql 관련 구문 등록
		ResultSet rs = null;		//결과		- 결과 관련 매핑 방식
		//크기
		//Connection > Statement > ResultSet
		//DB 			sql문장		결과
		
		//사용할 jdbc 드라이버:드라이버타입:서버주소와 포트:db서비스 아이디
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		//localhost = 127.0.0.1 = ip주소
		String user = "jsp";
		String password = "jsp12";
		
		//외부 연동에서 예외처리는 필수 
		try {
//			클래스 로드
//			1. jdbc드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2. 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);
			
//			3. sql 실행 객체 준비
			stmt = conn.createStatement();
			
			String sql = "select mno, mname, email, cre_date"
					+ " from member"
					+ " order by mno asc";
			
//			sql 실행문
//			4. 결과 가져오기
			rs = stmt.executeQuery(sql);
			//.executeQuery() - select 쿼리
			//.executeUpdate() - update 쿼리
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			//응답에 대한 모든 결과를 볼 수 있도록 함
			PrintWriter out = response.getWriter();
			
			String htmlStr = "";
			//추가 
			htmlStr += "<p>";
			htmlStr += "<a href='./add'>신규회원";	//상대경로
			htmlStr += "</a>";
			htmlStr += "</p>";
			
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body><h1>회원목록</h1>");
			out.println(htmlStr);
			
//			5. 출력
			//Iterator .next() : 값이 있는지 없는지에 대한 true, false
			//ResultSet rs는 size(), length 따위 없음
			while(rs.next()) {
				out.println(
					rs.getInt("mno") + ", " + 
					rs.getString("mname") + ", " + 
					rs.getString("email") + ", " + 
					rs.getDate("cre_date") + "</br>"
				);
			}
			out.println("</body></html>");
			
		} catch (ClassNotFoundException e) {
			 
			e.printStackTrace();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}finally {
			//6. 자원 해제 
			//해제는 자식먼저 해제시키고 부모를 해제시켜야한다. - 작은 단위 먼저 해제 
			//크기 : (부모) Connection > Statement > ResultSet (자식)
			//해제순서 : ResultSet -> Statement -> Connection 		
			
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
