package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet{
	
	//페이지별로 DB를 열고 닫는 것이 아닌
	//공통으로 사용한다. 
	//init 초기화시 최초 연동
	//ServletContext에 connection을 저장해 static전역변수같이  사용할 수 있도록 한다. 
	 @Override
	public void init() throws ServletException {

		System.out.println("AppInitServlet 준비...");
		super.init();
		
		String driver = "";
		String url = "";
		String user = "";
		String password = "";
		
		Connection conn = null;
		
		try {
			//doc. 데이터보관소.text 참고 
			//ServletContext로 전역객체를 받음 static의 영역 	
			ServletContext sc = this.getServletContext();
			
			driver = sc.getInitParameter("driver");
			
//			클래스 로드
//			1. jdbc드라이버 등록
			Class.forName(driver);
			
			url = sc.getInitParameter("url");
			user = sc.getInitParameter("user");
			password = sc.getInitParameter("password");
			
//			2. 데이터베이스 연결
			conn = DriverManager.getConnection(url, user, password);

			//어느페이지로 가든 conn이름으로 connection을 호출 할 수 있게 된다. 
			sc.setAttribute("conn", conn);
			
			System.out.println("DB 연결 성공");
			
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		
	}//end init()

	 
	 //서버가 종료될 때 destroy() 실행된다.
	 //즉, 서버 올리고 init()으로 db연결되고  (destroy()작동후)서버를 내릴때까지 db는 계속 연결되어 있다. 
	 @Override
	public void destroy() {

		 System.out.println("AppInitServlet 마무리...");
		 super.destroy();
		 
		 ServletContext sc = this.getServletContext();
		 
		 Connection conn = (Connection) sc.getAttribute("conn");
		 
		 try {
			if(conn != null) {
				conn.close();
				System.out.println("DB 연결 해제");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
		 
		 
		 
	}
	 
}
