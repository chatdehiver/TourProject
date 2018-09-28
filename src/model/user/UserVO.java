package model.user;

import java.util.ArrayList;

public class UserVO {
	String userName;
	int ssn;
	String id;
	String password;
	String tel;
	String mail;
	
	ArrayList<Integer> scraps;
	
	UserVO(){}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	@Override
	public String toString() {
		return "UserVO [userName=" + userName + ", ssn=" + ssn + ", id=" + id + ", password=" + password + ", tel="
				+ tel + ", mail=" + mail + "]";
	}
	
	
}
