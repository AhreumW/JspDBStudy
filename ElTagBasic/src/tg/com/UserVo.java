package tg.com; 

public class UserVo {

	private int userNo = 0;
	private String userName = "";
	private String userEmail ="";
	
	
	public UserVo() {
	}
	
	public UserVo(int userNo, String userName, String userEmail) {
		super();
		this.userNo = userNo;
		this.userName = userName;
		this.userEmail = userEmail;
	}
	
	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	@Override
	public String toString() {
		return "UserVo [userNo=" + userNo + ", userName=" + userName + ", userEmail=" + userEmail + "]";
	}
	
	
	
	
}
