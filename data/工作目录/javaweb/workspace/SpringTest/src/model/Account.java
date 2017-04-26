package model;

public class Account {
	private String username;
	private String password;
	private String sexid;
	private String mail;
	private String phone;
	private String remark;
	public Account(String username, String password, String sexid, String mail, String phone, String remark) {
		super();
		this.username = username;
		this.password = password;
		this.sexid = sexid;
		this.mail = mail;
		this.phone = phone;
		this.remark = remark;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSexid() {
		return sexid;
	}
	public void setSexid(String sexid) {
		this.sexid = sexid;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
