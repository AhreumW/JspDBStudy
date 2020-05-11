package tg.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿	->	제너릭서블릿	->	httpServlet
//GenericServlet보다 구현도가 높음
public class GuestAddServlet extends HttpServlet{
//HttpServlet는 doGet과 doPost 두 가지 방식이 존재한다. 
	
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
		htmlStr += "ID: <input type='text' name='user_id'><br>";
		htmlStr += "이메일: <input type='text' name='email'><br>";
		htmlStr += "암호: <input type='password' name='password'><br>";
		htmlStr += "월급: <input type='number' name='sal'><br>";
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
		//GenericServlet - Statement의 보안에 취약한 등 단점을 보안한 것이
		//HttpServletd - PreparedStatement이다. 
		PreparedStatement pstmt = null;
		
		ServletContext sc = this.getServletContext();
		//<context-param>의 이름들을 가져온다. 
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String user = sc.getInitParameter("user");
		String password = sc.getInitParameter("password");
		
		//입력시의 한글 인코딩설정  
		req.setCharacterEncoding("UTF-8");
		
//		사용자의 입력을 받는다.
//		.getParameter는 form태그 input태그의 name명을 받는다. 
		String emailStr = req.getParameter("email");
		String pwdStr = req.getParameter("password");
		String nameStr = req.getParameter("name");
		String userIdStr = req.getParameter("user_id");
		int salStr =  Integer.parseInt(req.getParameter("sal"));
//		valueOf도 int 형변환을 시켜주나 이는 new Integer객체로 가져오게된다. parseInt보다 비효율적
//		int salStr =  Integer.valueOf(req.getParameter("sal"));
		
		try {
			//.forName은 import와 같이 불러오는 기능을 하는 메서드이다. 
			//있을수도 없을수도 있는 영역이기 때문에 try-catch가 필요하다.
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			
//			? : 변화할 수 있는 식별자. (단, 순서를 지켜야 한다.)
			String sql ="insert into guest "
					+ "(mno, email, pwd, mname, cre_date, mod_date, user_id, sal) "
					+ "values(guest_mno_seq.nextval, ?, ?, ?, sysdate, sysdate, ?, ?)";
//			문자열 + 를 쓰지않고 한번에 입력가능해졌다. 
			
			pstmt = conn.prepareStatement(sql);
			
			//순서 지켜서 key-value로 입력
			pstmt.setString(1, emailStr);
			pstmt.setString(2, pwdStr);
			pstmt.setString(3, nameStr);
			pstmt.setString(4, userIdStr);
			pstmt.setInt(5, salStr);
			
			//이로 update 쿼리가 수행됨
			pstmt.executeUpdate();
			//pstmt.executeQuery();	- 이건  select쿼리
			//-> 자동커밋 수행중
			
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
