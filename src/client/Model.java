// Qui ci va la parte logica del programma

package client;

import java.sql.Time;
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
	
	public void viewTravelsList() {
		
	}
	
	public void viewPrenotationsList() {
		
	}
	
	public void viewOwnTravelsList() {
		
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
		
		public void modifyInfo(String name, String sname, char[] pass) {
			
		}
		
		public void viewUsersList() {
			
		}
		
		public void viewUserInfo(String mail) {
			
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
