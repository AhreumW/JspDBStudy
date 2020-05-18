package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dto.MemberDto;

@WebServlet(value="/auth/logout")
public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
			
		//session은 어느 서블릿이든 사용할 수 있다. 
		HttpSession session = req.getSession();
		
//		세션 test
//		MemberDto memberDto = (MemberDto)session.getAttribute("memberDto");
//		System.out.println(memberDto.getEmail());
		
		//.invalidate() : 세션의 attribute를 전부 지워버린다.
		session.invalidate();	
		
		res.sendRedirect("./login");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		
		
	}
	
}
