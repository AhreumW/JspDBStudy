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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dto.MemberDto;
import sun.rmi.server.Dispatcher;


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
		
		
		RequestDispatcher rd = null;
		
		try {	
		
			String noStr = req.getParameter("no");
			int no = Integer.parseInt(noStr);
			
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			String sql = "SELECT MNO, EMAIL, MNAME, CRE_DATE" 
			         + " FROM MEMBER" 
			         + " WHERE MNO = ?";
				
			
//			3. sql 실행 객체 준비
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
//			sql 실행문
//			4. 결과 가져오기
			rs = pstmt.executeQuery();
			
			String name = "";
			String email = "";
			Date creDate = null;
			
			MemberDto memberDto = new MemberDto();
			
			if(rs.next()) {
				name = rs.getString("MNAME");
				email = rs.getString("email");
				creDate = rs.getDate("cre_date");
				
				memberDto.setNo(no);
				memberDto.setName(name);
				memberDto.setEmail(email); 
				memberDto.setCreateDate(creDate); 
				
			}else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}

			req.setAttribute("memberDto", memberDto);
			
			rd = req.getRequestDispatcher("./MemberUpdateForm.jsp");
			rd.forward(req, res);
			
			
		} catch (Exception e) {
			 
			e.printStackTrace();
			
			req.setAttribute("error", e);
			rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
			
		} finally {
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
		int no = Integer.parseInt(req.getParameter("no"));
		
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
			pstmt.setInt(3, no);
			
			pstmt.executeUpdate();	//여기서 DB 반영됨
			
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
