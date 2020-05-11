package tg.servlets;

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

public class GuestDeleteServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
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
		
	//	사용자의 입력을 받는다.
		int mNo = Integer.parseInt(req.getParameter("mNo"));
		
		System.out.println("회원번호 : "+ mNo +" 삭제를 한다.");
		
		try {
			Class.forName(driverUrl);
			conn = DriverManager.getConnection(url, user, password);
			
			String sql ="DELETE FROM GUEST" 
					+ " WHERE MNO = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			//순서 지켜서 key-value로 입력
			pstmt.setInt(1, mNo);
			
			//리턴타입을 사용하면 재사용성을 높일 수 있다. 
			int resultNum = pstmt.executeUpdate();
			System.out.println("delete 수행결과 : "+resultNum);	//1
			//.executeUpdate() - 올바른 delete 수행결과 개수 
			
			
			//페이지존재를 남기지않고 바로 ./list로 넘긴다.
			res.sendRedirect("./list");
			
			
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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		
		
		
	}
	
}
