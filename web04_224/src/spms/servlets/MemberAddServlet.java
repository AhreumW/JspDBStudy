package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//web.xml 사용 (요즘은 어노테이션을 주로 사용)
//@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {

		
		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		
		String htmlStr = "";
		
		htmlStr += "<html><head><title>회원 등록</title></head>";
		htmlStr += "<body>";
		htmlStr += "<h1>회원등록</h1>";
//		form태그의 
//		method는 Get, Post 방식을 뜻한다. 
//		어떤 방식이든 form 태그의 action 주소로 전송시킨다.
		htmlStr += "<form action='add' method='post'>";		 
		htmlStr += "이름: <input type='text' name='name'><br>";
		htmlStr += "이메일: <input type='text' name='email'><br>";
		htmlStr += "암호: <input type='password' name='password'><br>";
		htmlStr += "<input type='submit' value='추가'>";
		htmlStr += "<input type='reset' value='취소'>";
		htmlStr += "</form>";
		htmlStr += "</body></html>";
		
		out.println(htmlStr);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		//db연동을 위해서는 connection이 필수 
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		
		String user = "jsp";
		String password = "jsp12";
		String driverUrl = "oracle.jdbc.driver.OracleDriver";
		
		//입력시의 한글 인코딩설정   //설명 doGetdoPost.text 참고
		req.setCharacterEncoding("UTF-8");
		
//		사용자의 입력을 받는다.
		String emailStr = req.getParameter("email");
		String pwdStr = req.getParameter("password");
		String nameStr = req.getParameter("name");
		
		try {
			Class.forName(driverUrl);
			conn = DriverManager.getConnection(url, user, password);
			
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
			//http-equiv='Refresh'는 이 페이지가 meta태그에 의해 새로 읽는다는 것을 의미
			//content='3; url=./list'	: 3은 3초후에 url에 적힌 주소로 이동한 다는 것을 뜻 함
			//url을 생략했을 경우 현재의 페이지를 다시 읽어준다. 
			htmlStr += "<meta http-equiv='Refresh' content='3; url=./list'>";
			htmlStr += "</head><body>";
			htmlStr += "<p>등록 성공입니다.!!</p>";
			//htmlStr += "<div style='width:100px; border:1px solid black; text-align:center;'>";
			//htmlStr += "<a href='./list'>회원목록</a></div>";
			htmlStr += "</body></html>";
			
			out.println(htmlStr);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("jdbc 오라클 드라이버 로드 실패 ");
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
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("db연결 해제 실패");
				}
			}
		}
		
		
	}
	
}
