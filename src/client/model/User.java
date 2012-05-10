package client.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import client.model.DAL;

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
	
	public void modifyInfo(String name, String sname, char[] pass, Date birth, char gender, String cf) {
		
	}
	
	public void register(String name, String sname, char[] pass, Date birth, char gender, String CF) {
		
	}
	
	public int delete() {
		return 1;
	}
	
	public int login(String mail, char[] pass) {
		// qui ci andrà la richiesta al database
		// intanto mettiamoci un falso login
		byte[] md5pass = null;
		byte[] md5correct = null;
		String hexPass = null;
		
		User checkUser = DAL.DalRetrieveUserInfo(mail);
		if (checkUser.userId != null) {
			try {
				// inizializzo l'oggetto per creare il digest
				MessageDigest md = MessageDigest.getInstance("MD5");
				// resetto l'oggetto
				md.reset();
				// trasformo le due password in stringhe
				String temp = new String(pass);
				// creo lo md5 della password in input
				md.update(temp.getBytes());
				md5pass = md.digest();
				hexPass = Logic.byteArrayToHexString(md5pass);
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			// confronto gli md5 delle due password
			if (hexPass.equals(checkUser.pass)) {
				Logic.MyUser = checkUser;
				return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	public int logout() {
		return 1;
	}

}
