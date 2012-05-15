package client.model;

import java.util.Date;

public class User {
	
	String userId;
	String name;
	String sname;
	String email;
	String pass;
	public int adm;
	Date birth;
	Character gender;
	String cf;
	public int blocked;
	
	public User() {
	}
	
	public String getId() {
		return this.userId;
	}
	
	public void setId(String id) {
		this.userId = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSname() {
		return this.sname;
	}
	
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return this.pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public int getAdm() {
		return this.adm;
	}
	
	public void setAdm(int adm) {
		this.adm = adm;
	}
	
	public Character getGender() {
		return this.gender;
	}
	
	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	public Date getBirth() {
		return this.birth;
	}
	
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	public String getCf() {
		return this.cf;
	}
	
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	public int getBlocked() {
		return this.blocked;
	}
	
	public void setBlocked(int blocked) {
		this.blocked = blocked;
	}

}
