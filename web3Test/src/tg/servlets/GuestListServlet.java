package tg.servlets;

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

public class GuestListServlet extends GenericServlet{

	@Override
	public void service(ServletRequest request, ServletResponse response) 
			throws ServletException, IOException {
		
//		UserList 제너릭 서블릿 만든다
//
//		서비스에
//		jsp계정의 멤버 테이블을 불러온다
//
//		mno, mname, email, cre_date, 수정날짜까지
//		번호 내림차순으로 정렬하여 표시하시오
//
//		, 뛰워쓰기정도는 해보시오
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; 
		
		//계정
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		//localhost = 127.0.0.1 = ip주소
		String user = "jsp";
		String password = "jsp12";
		
		try {
//			1. jdbc드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2. 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);
			
//			3. sql 실행 객체 준비
			stmt = conn.createStatement();
			
			String sql = "select mno, mname, email, cre_date, mod_date, user_id, sal"
					+ " from guest"
					+ " order by mno desc";
			
//			sql 실행문
//			4. 결과 가져오기
			rs = stmt.executeQuery(sql);
			
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
//			out.println("<div style='width:100px; border:1px solid black; text-align:center;'>");
//			out.println("<a href='./add'>신규 회원</a>");
//			out.println("</div><br>");
			out.println(htmlStr);
			
//			5. 출력
			//Iterator .next() : 값이 있는지 없는지에 대한 true, false
			//ResultSet rs는 size(), length 따위 없음
			while(rs.next()) {
				out.println(
					rs.getInt("mno") + ", " + 
					rs.getString("mname") + ", " + 
					rs.getString("email") + ", " + 
					rs.getDate("cre_date") + ", " + 
					rs.getDate("mod_date") + ", " + 
					rs.getString("user_id") + ", " + 
					rs.getInt("sal") + "</br>"
				);
			}
			out.println("</body></html>");
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
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
		}
		
		
	}

}
