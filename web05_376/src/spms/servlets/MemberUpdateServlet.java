package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

import spms.dao.MemberDao;
import spms.dto.MemberDto;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/member/update"})
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse res) 
					throws ServletException, IOException {

		Connection conn = null; 
		RequestDispatcher rd = null;
		
		try {
		
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			String noStr = req.getParameter("no");
			int no = Integer.parseInt(noStr);  
			
			MemberDto memberDto = new MemberDto();
			memberDto = memberDao.memberSelectOne(no);
			
			req.setAttribute("memberDto", memberDto);
			rd = req.getRequestDispatcher("./MemberUpdateForm.jsp");
			rd.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("error", e);
			rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
			
		} 

	} // doGet end
	
	@Override
	protected void doPost(HttpServletRequest req, 
			HttpServletResponse res) 
					throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		
		ServletContext sc = this.getServletContext();
		conn = (Connection)sc.getAttribute("conn");
		
		try {
			String email = req.getParameter("email");
			String name = req.getParameter("name");
			int mNo = Integer.parseInt(req.getParameter("no"));
			
			MemberDto memberDto = new MemberDto();
			memberDto.setEmail(email);
			memberDto.setName(name);
			memberDto.setNo(mNo);
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			int result = memberDao.memberUpdate(memberDto);
			
			if(result == 0) {
				System.out.println("update 수행 실패");
			}
			
			res.sendRedirect("./list");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
