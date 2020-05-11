package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//어노테이션 @	 
@WebServlet("/member/list")		
public class MemberListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
//		데이터베이스 관련 객체 변수 선언
		Connection conn = null;		//연결  
		Statement stmt = null;		//상태 
		ResultSet rs = null;		//결과 
		
		ServletContext sc = this.getServletContext();

		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");
		
		try {
//			클래스 로드
//			1. jdbc드라이버 등록
			Class.forName(driver);
			
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
			htmlStr += "<html>"; 
			htmlStr += "<head>"; 
			htmlStr += "<title>회원목록</title>"; 
			htmlStr += "</head>";
			htmlStr += "<body>";
			htmlStr += "<h1>회원목록</h1>";
			htmlStr += "<p>";
			htmlStr += "<a href='./add'>신규회원</a>";
			htmlStr += "</p>"; 
			
			while(rs.next()) {
				htmlStr += "<div>";
				htmlStr += rs.getInt("MNO") + ", ";
				htmlStr += "<a href='./update?mNo="+rs.getInt("MNO")+"'>"
						+ rs.getString("MNAME") +"</a>, "
						+ rs.getString("EMAIL") + ", "
						+ rs.getDate("CRE_DATE") + " "; 
				htmlStr += "<a href='./delete?mNo="+rs.getInt("MNO")+"'>[ 삭제 ]</a>";
				htmlStr += "</div>";	
			}
			htmlStr += "</body>";
			htmlStr += "</html>";
			
			out.println(htmlStr);
			
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
		
		
	}// doGet end

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ServletContext sc = this.getServletContext();

		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");
		
		int mNo = Integer.parseInt(req.getParameter("mno"));
		
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="";
			
			sql ="DELETE FROM MEMBER";
			sql += " WHERE MNO=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, mNo);
			
			pstmt.executeUpdate();	 
			
			res.sendRedirect("./list");
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			//6. 자원 해제 
			//상태 해제
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			//연결 해제 - conn가 db
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
