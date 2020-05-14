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
		
		
		try {
			//전역변수객체 - ServletContext 보관소
			ServletContext sc = this.getServletContext();
			//AppInitServlet에서 저장해둔  db connetion 가져온다. 
			conn = (Connection)sc.getAttribute("conn");
			
			
//			3. sql 실행 객체 준비
			stmt = conn.createStatement();
			
			String sql = "SELECT MNO, MNAME, EMAIL, CRE_DATE"
					+ " FROM MEMBER"
					+ " ORDER BY MNO ASC";
			
//			sql 실행문
//			4. 결과 가져오기
			rs = stmt.executeQuery(sql);
			System.out.println("쿼리 수행 성공");
			
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			
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
			
			request.setAttribute("memberList",memberList);	
			
			//RequestDispatcher는 정보를 가질수있다. 
			//데이터 들고감
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/member/MemberListView.jsp");
			dispatcher.include(request, response);	//url에 .MemberListView.jsp가 보이지 않는다.
			
		} catch (Exception e) { 
			request.setAttribute("error", e);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Error.jsp");	//절대경로
			dispatcher.forward(request, response);
			
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
					System.out.println("쿼리 질의 종료");
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
		
		} // finally 종료
		
		
	}// doGet end

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
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
