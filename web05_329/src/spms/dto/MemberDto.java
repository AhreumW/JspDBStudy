package spms.dto;

import java.util.Date;

//model의 역할, DTO :Data Transfer Object
public class MemberDto {

	//원래는 db컬럼명과 동일하게 하기
	//자바는 낙타표기법, db는 파스칼표기법
	private int no;
	private String name;
	private String email;
	private String password;
	private Date createDate;
	private Date modifiedDate;
	
	
	public MemberDto() {
	}
	
	public MemberDto(int no, String name, String email, Date createDate) {
		this.no = no;
		this.name = name;
		this.email = email;
		this.createDate = createDate;
	}
	
	public MemberDto(int no, String name, String email, String password, Date createDate, Date modifiedDate) {
		this.no = no;
		this.name = name;
		this.email = email;
		this.password = password;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	@Override
	public String toString() {
		return "MemberDto [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
	
}
