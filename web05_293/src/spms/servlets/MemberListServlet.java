package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dto.MemberDto;

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
		
		//에러처리 해주기, try-catch로 이동
		String driver = "";
		String url = "";
		String user = "";
		String password = "";
		
		try {
			ServletContext sc = this.getServletContext();
			
			driver = sc.getInitParameter("driver");
			url = sc.getInitParameter("url");
			user = sc.getInitParameter("user");
			password = sc.getInitParameter("password");
			
//			클래스 로드
//			1. jdbc드라이버 등록
			Class.forName(driver);
			System.out.println("오라클 드라이버 로드 성공");
			
//			2. 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("오라클 드라이버 연결 성공");
			
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
			System.out.println("쿼리 수행 성공");
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
			//회원은 여러 값이 들어올 수 있고 
			//그 회원들은 arrayList로 저장된다. 
			ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();
			
			int mno =0;
			String mname = "";
			String email = "";
			Date creDate = null;
			
			while(rs.next()) {
				mno = rs.getInt("MNO");
				mname = rs.getString("MNAME");
				email = rs.getString("EMAIL");
				creDate = rs.getDate("CRE_DATE");
				
				MemberDto memberDto = new MemberDto();
				memberDto.setNo(mno);
				memberDto.setName(mname);
				memberDto.setEmail(email);
				memberDto.setCreateDate(creDate);
				
				memberList.add(memberDto);
			}
			
			/* setParameter vs setAttribute */
			//setParameter는 반드시 문자열만 들어가는 것에 비해
			//setAttribute는Object로 어떤 타입이든 전달할 수 있다. 
			
			//request에 회원목록 데이터 보관
			request.setAttribute("memberList",memberList);	
			
			//.getRequestDispatcher("/member/MemberListView.jsp")
			// 페이지 url를 호출하면서 
			//.include(request, response)
			//데이터를 같이 전달.  
			
			//jsp 페이지로 출력을 위임한다. 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/MemberListView.jsp");
			dispatcher.include(request, response);
			
		} catch (Exception e) {
			 
			e.printStackTrace();
			throw new ServletException(e);
			
		} finally {
			//6. 자원 해제 
			
			//결과셋 해제
			if(rs != null) {	//null이 아닌 경우=객체가 존재하는경우, close시킨다.
				try {
					rs.close();
					System.out.println("ResultSet 종료");
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			//상태 해제
			if(stmt != null) {
				try {
					stmt.close();
					System.out.println("쿼리질의 종료");
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			//연결 해제
			if(conn != null) {
				try {
					conn.close();
					System.out.println("DB연결 종료");
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		
		} // finally 종료
		
		
	}// doGet end

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		//req.setCharacterEncoding("UTF-8");
		
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
