// Qui ci va la parte logica del programma

package client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Model {

	/**
	 * @param args
	 */

	static Controller controller;
	public static User MyUser;
	public static Admin MyAdmin;
	
	// CLASSE UTENTE
	public static class User extends Persona {

		char gender;
		Date birth;
		String CF;
		
		public User() {
			super();
		}
		
	}
	
	public static class Admin extends Persona {
		
	}
	
	static public class Persona {
		
		String name;
		String sname;
		String mail;
		char[] pass;
		int adm;
		
		public Persona() {
		}
		
		public int login(String mail, char[] pass) {
			// qui ci andrà la richiesta al database
			// intanto mettiamoci un falso riempimento degli altri campi e una conferma al controller
			this.adm = 0; // 0 == user, 1 == admin
			if (this.adm == 0) {
				MyUser = new User();
				MyUser.mail = mail;
				MyUser.pass = pass;
				MyUser.name = "Richard";
				MyUser.sname = "Benson";
				MyUser.gender = 'm';
				MyUser.CF = "RCRDBNSN666P4U24";
				try {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
					MyUser.birth = formatter.parse("1955/03/10");
				} catch (ParseException e) {
					System.out.println(e.toString());
					e.printStackTrace();
				}
			} else {
				MyAdmin = new Admin();
				MyAdmin.mail = mail;
				MyAdmin.pass = pass;
				MyAdmin.name = name;
				MyAdmin.sname = sname;
			}
			
			return 1;
		
		}
		
		public int logout() {
			return 1;
		}
		
	}
	
}
