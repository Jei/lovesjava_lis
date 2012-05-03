// Qui ci va la parte logica del programma

package client;

import java.security.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class Model {

	/**
	 * @param args
	 */

	static Controller controller;
	public static User MyUser;
	public static Admin MyAdmin;
	static char[] hexChar = {
		   '0' , '1' , '2' , '3' ,
		   '4' , '5' , '6' , '7' ,
		   '8' , '9' , 'a' , 'b' ,
		   'c' , 'd' , 'e' , 'f'};
	
	/* Converte un array di byte in una stringa composta dai corrispondenti valori esadecimali.
	 * Presa da http://mindprod.com/jgloss/hex.html
	 * @param b byte[] da convertire in stringa di esadecimali
	 * @return rappresentazione esadecimale dell'array di byte, sottoforma di stringa
	 */
	public static String byteArrayToHexString ( byte[] b ) {
		StringBuffer sb = new StringBuffer( b.length * 2 );
		for ( int i=0; i<b.length; i++ ) {
			// look up high nibble char
			sb.append( hexChar [( b[i] & 0xf0 ) >>> 4] );

			// look up low nibble char
			sb.append( hexChar [b[i] & 0x0f] );
		}
		return sb.toString();
	}
	
	public void viewTravelsList() {
		
	}
	
	public void viewPrenotationsList() {
		
	}
	
	public void viewOwnTravelsList() {
		
	}
	
	public void viewUsersList() {
		
	}
	
	public void getUser(String mail) {
		
	}
	
	public void getTravel(String travelId) {
		
	}
	
	public void getPrenotation(String prenotationId) {
		
	}
	
	// CLASSE UTENTE
	public static class User extends Persona {

		char gender;
		Date birth;
		String CF;
		
		public User() {
			super();
		}
		
		public void modifyInfo(String name, String sname, char[] pass, Date birth, char gender) {
			
		}
		
		public void register(String name, String sname, char[] pass, Date birth, char gender, String CF) {
			
		}
		
		public int delete() {
			return 1;
		}
		
		public void viewTravelInfo(String travelId) {
			
		}
		
		public void viewPrenotationInfo(String prenotationId) {
			
		}
		
	}
	
	
	// CLASSE ADMIN
	public static class Admin extends Persona {
		
		public Admin() {
			super();
		}
		
		public int blockUser(String mail) {
			return 1;
		}
		
		public int unblockUser(String mail) {
			return 1;
		}
		
	}
	
	
	// CLASSE PERSONA
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
			// intanto mettiamoci un falso login
			byte[] md5pass = null;
			byte[] md5correct = null;
			char[] correctPassword = { 'm', 'a', 'i', 'o', 'r', 'c', 'a' };
			try {
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
				String hexString = byteArrayToHexString(md5pass);
				System.out.println("MD5 password: " + hexString);
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			// confronto gli md5 delle due password
			if (Arrays.equals(md5pass, md5correct)) {
				int isadm = 0; // 0 == user, 1 == admin
				if (isadm == 0) {
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
			} else {
				return 0;
			}
			
		
		}
		
		public int logout() {
			return 1;
		}
		
		public void modifyInfo(String name, String sname, char[] pass) {
			
		}
		
	}
	
	// CLASSE VIAGGIO
	static public class Travel {
		
		String travelId;
		String author;
		String start;
		String end;
		Date date;
		Time time;
		int maxSeats;
		int totalKm;
		float price;
		String[] stops;
		
		public Travel(String author, String start, String end, Date date, Time time, int maxSeats, int totalKm, float price, String[] stops) {
			this.author = author;
			this.start = start;
			this.end = end;
			this.date = date;
			this.time = time;
			this.maxSeats = maxSeats;
			this.totalKm = totalKm;
			this.price = price;
			this.stops = stops;
		}
		
		public int createTravel() {
			return 1;
		}
		
		public int deleteTravel(String travelId) {
			return 1;
		}
		
		public int checkAvailableSeat(String travelId) {
			return 1;
		}
		
	}
	
	static public class Prenotation {
		
		String prenotationId;
		String passenger;
		String travelId;
		
		public Prenotation(String passenger, String travelId) {
			this.passenger = passenger;
			this.travelId = travelId;
		}
		
		public int createPrenotation() {
			return 1;
		}
		
		public int deletePrenotation() {
			return 1;
		}
		
	}
	
	static public class Feedback {
		
		String author;
		int vote;
		String comment;
		
		public Feedback(String author, int vote, String comment) {
			this.author = author;
			this.vote = vote;
			this.comment = comment;
		}
		
		public int sendFeedback() {
			return 1;
		}
		
	}
	
	static public class UserFeedback extends Feedback {
		
		String target;

		public UserFeedback(String author, String target, int vote, String comment) {
			super(author, vote, comment);
			this.target = target;
		}
		
		public int sendFeedback() {
			return 1;
		}
		
	}
	
}
