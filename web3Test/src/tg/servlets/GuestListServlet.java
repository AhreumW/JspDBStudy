package tg.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GuestListServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
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
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		//계정
		ServletContext sc = this.getServletContext();
		//<context-param>의 이름들을 가져온다. 
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");
		
		try {
//			1. jdbc드라이버 등록
			Class.forName(driver);
			
//			2. 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);
			
			
			String sql = "SELECT MNO, MNAME, EMAIL, CRE_DATE, MOD_DATE, USER_ID, SAL"
					+ " FROM GUEST"
					+ " ORDER BY MNO DESC";
			
//			3. sql 실행 객체 준비
			pstmt = conn.prepareStatement(sql);

//			sql 실행문
//			4. 결과 가져오기
			rs = pstmt.executeQuery();
			
			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			
			//응답에 대한 모든 결과를 볼 수 있도록 함
			PrintWriter out = res.getWriter();
			
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
					"<form action='./delete'>" + 
					"<div>"	+
					rs.getInt("mno") + ", " 
					+ "<a href='./update?mNo=" + rs.getString("MNO")+"'>" +  
					rs.getString("mname") + "</a>, " + 
					rs.getString("email") + ", " + 
					rs.getDate("cre_date") + ", " + 
					rs.getDate("mod_date") + ", " + 
					rs.getString("user_id") + ", " + 
					rs.getInt("sal") + 
					"<input type='hidden' name='mNo' value='"+rs.getInt("MNO")+"'>" + 
					"<input type='submit' value='삭제'>" + 
					"</div>"+
					"</form>"
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
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
	}

}
