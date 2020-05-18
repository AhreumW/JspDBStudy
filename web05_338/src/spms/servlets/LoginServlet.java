package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dto.MemberDto;

//패키지 auth, url 주소일 것이다. 폴더를 보고 경로를 예측해보자 
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		RequestDispatcher rd = req.getRequestDispatcher("./LoginForm.jsp");	
		//.forward : 아예 페이지로 보내니까
		//.include : 페이지 조합, 일부를 주고받을때 
		//	이걸 인클루딩으로 하면 head와 meta가 다 깨져있음, 인클루딩은 일부를 가져오는 기술+조합하는 용도이기때문에 훼손되어 있음
		rd.forward(req, res);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		 
		
		String sql = "";
		int colIndex = 1; 
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			String email = req.getParameter("email");
			String pwd = req.getParameter("password");
			String name = "";
			
			//왠만한 로직은 자바가 아닌 *db에서 처리*한다. 
			//db에서 처리하고 가져오는것이 코드도 간편.
			sql += "SELECT EMAIL, MNAME";
			sql += " FROM MEMBER";
			sql += " WHERE EMAIL = ?";
			sql += " AND PWD= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(colIndex++, email);
			pstmt.setString(colIndex, pwd);
			
			rs = pstmt.executeQuery();
			
			//member가 없으면 rs.next()=false로 아예 들어가지 않는다. 
			if(rs.next()) {	//어차피 객체는 1개만 나오므로 while이 아닌 if문을 사용한다. 
				email = rs.getString("email");
				name = rs.getString("mname");

				MemberDto memberDto = new MemberDto();
				
				memberDto.setEmail(email);
				memberDto.setName(name);
				
				//이미 request엔 session이 존재했었다. 그걸 재사용으로 불러옴
				HttpSession session = req.getSession();
				session.setAttribute("memberDto", memberDto);
				
				//미리 만들어진 페이지로 보내기 위해선
				//컨트롤러로 보내야한다. 
				//컨트롤러의 로직을 타서 리스트화면이 보여지는것이다.
				res.sendRedirect("../member/list");
				//화면의 요청은 컨트롤러로 보내자!!
				
				//인클루딩, 포워드가 데이터(res,req)를 가져가는 것과 달리
				//sendRedirect는 데이터를 소멸시킨다.
				
				//여기서 사용한 패스워드 데이터 소멸시키려고...
				//sendRedirect는 response완전초기화 시킴 
				
			}else {	//아이디나 비밀번호가 틀린경우
				RequestDispatcher rd = 
						req.getRequestDispatcher("./LoginFail.jsp");
				rd.forward(req, res);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("login 수행 실패");
			
			throw new ServletException(e);
		}finally {
			 
			if(rs != null) { 
				try {
					rs.close();
					System.out.println("ResultSet 종료");
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("pstmt 종료");
				}
			}
		}
		
		
	}
	
}
