package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/member/add")
public class MemberAddServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		res.sendRedirect("../member/MemberForm.jsp");
//		res.sendRedirect("./MemberForm.jsp");	//랑 같음
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		//db연동을 위해서는 connection이 필수 
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		ServletContext sc = this.getServletContext();
		
//		사용자의 입력을 받는다.
		String emailStr = req.getParameter("email");
		String pwdStr = req.getParameter("password");
		String nameStr = req.getParameter("name");
		
		try {
			
			conn = (Connection)sc.getAttribute("conn");
			
			
			String sql ="INSERT INTO MEMBER"
					+ "(MNO, EMAIL, PWD, MNAME, CRE_DATE, MOD_DATE)"
					+ "VALUES(MEMBER_MNO_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, SYSDATE)";
			
			pstmt = conn.prepareStatement(sql);
			
			//순서 지켜서 key-value로 입력
			pstmt.setString(1, emailStr);
			pstmt.setString(2, pwdStr);
			pstmt.setString(3, nameStr);
			
			pstmt.executeUpdate();
			
			res.sendRedirect("./list");
			
			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			
			PrintWriter out = res.getWriter();
			
			String htmlStr = "";
			
			htmlStr += "<html><head><title>회원등록결과</title>";			
			htmlStr += "<meta http-equiv='Refresh' content='3; url=./list'>";
			htmlStr += "</head><body>";
			htmlStr += "<p>등록 성공입니다.!!</p>";
			htmlStr += "</body></html>";
			
			out.println(htmlStr);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("insert into member 수행 실패");
		}finally {
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
