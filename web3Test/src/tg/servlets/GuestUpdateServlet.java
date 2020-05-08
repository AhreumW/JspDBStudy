package tg.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GuestUpdateServlet extends HttpServlet{

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
		
		int mNo = Integer.parseInt(req.getParameter("mNo"));
		//System.out.println(mNo);
		
		try {	
//			클래스 로드
//			1. jdbc드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
//			2. 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);
			
			// select * 로 하지말기, 이렇게 되면 나중에 DB에서 컬럼명 확인해야한다. 
			String sql = "SELECT MNO, EMAIL, MNAME, CRE_DATE, USER_ID, SAL" 
//			String sql = "SELECT *"		// 명시하기 
			         + " FROM GUEST" 
			         + " WHERE MNO = ?";
//					+ " WHERE MNO = " + mNo;는  옛날 Statement방식
				
			
//			3. sql 실행 객체 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mNo);
			
//			sql 실행문
//			4. 결과 가져오기
			rs = pstmt.executeQuery();
			
			String mName = "";
			String email = "";
			Date creDate = null;
			String userId = "";
			int sal = 0;
			
			if(rs.next()) {
				mName = rs.getString("MNAME");
				email = rs.getString("email");
				creDate = rs.getDate("cre_date");
				userId = rs.getString("USER_ID");
				sal = rs.getInt("SAL");
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
			htmlStr += "<form action='/guest/update' method='post'>";
			htmlStr += "번호: <input type='text' name='mNo' value='"+ mNo +"' ";
			htmlStr += " readonly='readonly'><br>";
			htmlStr += "이름: <input type='text' name='name' value='"+ mName +"'><br> ";
			htmlStr += "이메일: <input type='text' name='email' value='"+ email +"'><br>";
			htmlStr += "가입일: "+ creDate +"<br>";
			htmlStr += "ID: <input type='text' name='user_id' value='"+ userId +"'><br>";
			htmlStr += "월급: <input type='text' name='sal' value='"+ sal +"'><br>";
			htmlStr += "<input type='submit' value='저장'>";
			//htmlStr += "<input type='button' value='취소' onclick='location.href=" + "./list" + "'>";
			htmlStr += "<input type='button' value='취소' onclick='location.href=\"./list\"'>";
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		
		
		
	}

}
