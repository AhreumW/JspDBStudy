package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/member/update"})	
public class MemberUpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
	
//		데이터베이스 관련 객체 변수 선언
		Connection conn = null;		//연결  
		
		PreparedStatement pstmt = null;		//상태 
		
		ResultSet rs = null;		//결과 
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	
		String user = "jsp";
		String password = "jsp12";
		
		String mNoStr = req.getParameter("mNo");
		int mNo = Integer.parseInt(mNoStr);
		
		try {	
//			클래스 로드
//			1. jdbc드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2. 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT MNO, EMAIL, MNAME, CRE_DATE" 
			         + " FROM MEMBER" 
			         + " WHERE MNO = ?";
				
			
//			3. sql 실행 객체 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNo);
			
//			sql 실행문
//			4. 결과 가져오기
			rs = pstmt.executeQuery();
			
			String mName = "";
			String email = "";
			Date creDate = null;
			
			if(rs.next()) {
				mName = rs.getString("MNAME");
				email = rs.getString("email");
				creDate = rs.getDate("cre_date");
			}
			
			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			
			PrintWriter out = res.getWriter();
			
			String htmlStr = "";
			//추가 		// alt+shift+a 다중코드선택키
			htmlStr += "<!DOCTYPE html>";
			htmlStr += "<html>";
			htmlStr += "<head>";
			htmlStr += "<meta charset='UTF-8'>";
			htmlStr += "<title>회원정보</title>";
			htmlStr += "</head>";
			htmlStr += "<body>";
			htmlStr += "<h1>회원정보</h1>";
			htmlStr += "<form action='./update' method='post'>";
			htmlStr += "번호: <input type='text' name='mNo' value='"+ mNo +"' ";
			htmlStr += " readonly='readonly'><br>";
			htmlStr += "이름: <input type='text' name='name' value='"+ mName +"'><br> ";
			htmlStr += "이메일: <input type='text' name='email' value='"+ email +"'><br>";
			htmlStr += "가입일: "+ creDate +"<br>";
			htmlStr += "<input type='submit' value='저장'>";
			htmlStr += " <input type='button' value='삭제' onclick='location.href=\"./delete?mNo="+mNo+"\"'>";
			htmlStr += " <input type='button' value='취소' onclick='location.href=\"./list\"'>";
			htmlStr += "</form>";
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
			if(pstmt != null) {
				try {
					pstmt.close();
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
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		//req.setCharacterEncoding("UTF-8");
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		//web.xml의 servlet단위의 context를 먼저 부르고 (context는 전역으로 프로젝트가 끝날때까지 사라지지 않는다.)
		ServletContext sc = this.getServletContext();
		//<context-param>의 이름들을 가져온다. 
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");
		
		String email = req.getParameter("email");
		String name = req.getParameter("name");
		int mNo = Integer.parseInt(req.getParameter("mNo"));
		
		try {
			Class.forName(driver);

			System.out.println("오라클 드라이버 로드");
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="";
			
			sql ="update member";
			sql += " set email=?, mname=?, mod_date=sysdate";
			sql += " where mno=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.setInt(3, mNo);
			
			pstmt.executeUpdate();	//여기서 DB 반영됨
			
			
			res.sendRedirect("./list");
			
//			res.setContentType("text/html");
//			res.setCharacterEncoding("UTF-8");
//			
//			PrintWriter out = res.getWriter();
//			
			
//			String htmlStr = "";
//			
//			htmlStr += "<html><head><title>회원정보수정결과</title>";			
//			htmlStr += "</head><body>";
//			htmlStr += "<p>수정 성공입니다.!!</p>";
//			htmlStr += "</body></html>";
//			
//			out.println(htmlStr);
			
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
