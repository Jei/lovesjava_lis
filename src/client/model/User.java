package client.model;

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
	char gender;
	String cf;
	
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
	
	public char getGender() {
		return this.gender;
	}
	
	public void setGender(char gender) {
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
		char[] correctPassword = { 'm', 'a', 'i', 'o', 'r', 'c', 'a' };
		String hexString;
		/*try {
			// inizializzo l'oggetto per creare il digest
			MessageDigest md = MessageDigest.getInstance("MD5");
			// resetto l'oggetto
			md.reset();
			// trasformo le due password in stringhe
			String temp1 = new String(correctPassword);
			String temp = new String(pass);
			// creo lo md5 della password in input
			md.update(temp.getBytes());
			md5pass = md.digest();
			// creo lo md5 della password "vera"
			md.reset();
			md.update(temp1.getBytes());
			md5correct = md.digest();
			// un po' di output su console per debug
			System.out.println("mail: " + mail);
			System.out.println("password: " + temp);
			System.out.println("correctPassword: " + temp1);
			hexString = byteArrayToHexString(md5pass);
			System.out.println("MD5 password: " + hexString);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		// confronto gli md5 delle due password
		if (Arrays.equals(md5pass, md5correct)) {
			int isadm = 0; // 0 == user, 1 == admin
			if (isadm == 0) {
				MyUser = new User();
				MyUser.email = mail;
				MyUser.pass = hexString;
				MyUser.name = "Richard";
				MyUser.sname = "Benson";
				MyUser.gender = 'm';
				MyUser.cf = "RCRDBNSN666P4U24";
				try {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
					MyUser.birth = formatter.parse("1955/03/10");
				} catch (ParseException e) {
					System.out.println(e.toString());
					e.printStackTrace();
				}
			} else {
				MyAdmin = new Admin();
				MyAdmin.email = mail;
				MyAdmin.pass = hexString;
				MyAdmin.name = name;
				MyAdmin.sname = sname;
			}
			return 1;
		} else {
			return 0;
		}*/
		
		Logic.MyUser = DAL.DalRetrieveUserInfo(mail);
		
		return 1;
		
	
	}
	
	public int logout() {
		return 1;
	}

}
