package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.dto.MemberDto;

@WebServlet(value = "/auth/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
						throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher rd = 
				req.getRequestDispatcher("./LoginForm.jsp");
		rd.forward(req, res);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
					throws ServletException, IOException {

		// 데이터베이스 관련 객체 변수 선언
		Connection conn = null; // 연결
		
		try {
			String email = req.getParameter("email");
			String pwd = req.getParameter("password");

			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			MemberDto memberDto = memberDao.memberExist(email, pwd);
			
			if(memberDto == null) {
				RequestDispatcher rd = req.getRequestDispatcher("./LoginFail.jsp");
				rd.forward(req, res);
			}else {	
				// Cannot call sendRedirect() after the response has been committed
				// 페이지이동은 하나만 수행되어야하므로 else문에 입력한다.
				HttpSession session = req.getSession();
				session.setAttribute("member", memberDto);
				
				res.sendRedirect("../member/list");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new ServletException(e);
		}
	}

	
}
