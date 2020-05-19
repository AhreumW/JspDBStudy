package spms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import spms.dto.MemberDto;

public class MemberDao {	//DB접근 객체는 DAO에서 분리하여 처리한다. 
	
	private Connection connection;
	
	
	// 주입 Injection -> 의존 dependency
	public void setConnection(Connection conn) {	// 객체주입
		this.connection = conn;
	}
	
	public List<MemberDto> selectList() throws Exception{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT MNO, MNAME, EMAIL, CRE_DATE";
		sql += " FROM MEMBER";
		sql += " ORDER BY MNO ASC";
		
		try {
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			ArrayList<MemberDto> memberList = new ArrayList<MemberDto>();
			
			int no = 0;
			String name = "";
			String email = "";
			Date creDate = null;
			
			while(rs.next()) {
				no = rs.getInt("mno");
				name = rs.getString("mname");
				email = rs.getString("email");
				creDate = rs.getDate("cre_date");
				
				MemberDto memberDto = 
						new MemberDto(no, name, email, creDate);
				
				memberList.add(memberDto);
				
			}
			
			return memberList;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			throw e;
			
		}finally {
			
			try {
				if(rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
	}
	
	
	//void 함수를 int형으로 사용해서 executeUpdate 수행 결과를 return 시킬수도있다. 
	public int memberInsert(MemberDto memberDto) throws Exception {
	
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String email = memberDto.getEmail();
			String pwd = memberDto.getPassword();
			String name = memberDto.getName();
			
			String sql = "insert into member ";
			sql +="value(mno, email, pwd, mname, cre_date, mod_date) ";
			sql +="values(member_mno_seq.nextval";
			sql +=", ?, ?, ?, sysdate, sysdate)";
			
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);

			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}//finally종료
		
		return result;
		
	}//memberInsert 종료
	
	
	
	public MemberDto memberSelectOne(int no) throws Exception {
		
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		
		try {
			String sql = "SELECT MNO, EMAIL, MNAME, CRE_DATE";
			sql += " FROM MEMBER";
			sql += " WHERE MNO = ?";

			// sql 실행문
			// 4 결과 가져오기
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			
			String name = "";
			String email = "";
			Date creDate = null;
			
			MemberDto memberDto = new MemberDto();
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("MNAME");
				email = rs.getString("email");
				creDate = rs.getDate("CRE_DATE");
				
				memberDto.setNo(no);
				memberDto.setName(name);
				memberDto.setEmail(email);
				memberDto.setCreateDate(creDate);
				System.out.println(memberDto);
			}else {
				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
			}
			
			return memberDto;	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

		} // finally 종료
		
	}
	
	
	public int memberUpdate(MemberDto memberDto) throws Exception{
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		try {
			String sql = "UPDATE MEMBER";
			sql += " SET EMAIL = ?, MNAME =?, MOD_DATE=SYSDATE";
			sql += " WHERE MNO = ?";
			
			pstmt = connection.prepareStatement(sql);
			
			int mNo = memberDto.getNo();
			String name = memberDto.getName();
			String email = memberDto.getEmail();
			
			pstmt.setString(1, email);
			pstmt.setString(2, name);
			pstmt.setInt(3, mNo);
			
			result = pstmt.executeUpdate();
			
			return result;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
			
		}finally {
			
			// 상태 해제
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public int memberDelete(int no) throws Exception {
		
		int result = 0; 
		
		PreparedStatement pstmt = null;
		
		try {
			
			String sql = "delete FROM member"; 
			sql += " where mno = ?";

			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, no);

			result = pstmt.executeUpdate();

			System.out.println("delete 수행결과 : " + result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("delete member 수행 실패");
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("pstmt 종료 실패");
				}
			}
			
		}
		
		return result;
		
	}
	
}
