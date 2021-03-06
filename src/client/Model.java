// Qui ci va la parte logica del programma

package client;

import java.security.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class Model {

	/**
	 * @param args
	 */

	static SessionFactory MySessionFactory;
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
	
	public static SessionFactory configureSessionFactory() throws HibernateException {
		SessionFactory sf = null;
		ServiceRegistry serviceRegistry;
		Configuration configuration = null;
		try {
			configuration = new Configuration()  
	    		.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect")
	    		.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/CarPooling")
	    		.setProperty("hibernate.connection.username", "root")
	    		.setProperty("hibernate.connection.password", "root")
	    		.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
	    		.addResource("hres/User.hbm.xml");
		} catch (Throwable he) {
			System.out.println(he.toString());
			he.printStackTrace();
		}
	    try {
	    	configuration.configure();
	    } catch(HibernateException he) {
	    	System.out.println(he.toString());
			he.printStackTrace();
	    }
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	    try {
	    	sf = configuration.buildSessionFactory(serviceRegistry);
	    } catch(HibernateException he) {
	    	System.out.println(he.toString());
			he.printStackTrace();
	    }
	    MySessionFactory = sf;
	    return sf;
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
	
	public static int registerUser(String email, String name, String sname, Date birth, char gender, String cf, char[] pass) {
		byte[] md5pass = null;
		String hexString;
		User newUser = new User(); // Cpt. Obvious goes programming!
		
		try {
			// inizializzo l'oggetto per creare il digest
			MessageDigest md = MessageDigest.getInstance("MD5");
			// resetto l'oggetto
			md.reset();
			String temp1 = new String(pass);
			md.update(temp1.getBytes());
			md5pass = md.digest();
			hexString = byteArrayToHexString(md5pass);
		} catch(NoSuchAlgorithmException e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
			return 0;
		}
		
		newUser.setName(name);
		newUser.setSname(sname);
		newUser.setEmail(email);
		newUser.setBirth(birth);
		newUser.setGender(gender);
		newUser.setCf(cf);
		newUser.setPass(hexString);
		newUser.setAdm(0);
		
		DAL.DalCreateUser(newUser);
		
		return 1;
	}
	
	// CLASSE UTENTE
	public static class User extends Persona {
		
		String userId;
		char gender;
		Date birth;
		String cf;
		
		public User() {
		}
		
		public String getId() {
			return this.userId;
		}
		
		public void setId(String id) {
			this.userId = id;
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
		
		String personaId;
		String name;
		String sname;
		String email;
		String pass;
		int adm;
		
		public Persona() {
		}
		
		public String getId() {
			return this.personaId;
		}
		
		public void setId(String id) {
			this.personaId = id;
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
			
			MyUser = DAL.DalRetrieveUserInfo(mail);
			
			return 1;
			
		
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
	
	static public class DAL {
		
		public static User DalRetrieveUserInfo(String email) throws HibernateException {
			Session session = null;
			try {
				System.out.println("Apro la connessione.");
				session = MySessionFactory.openSession();
				session.beginTransaction();
				// roba che recupera l'utente dalla tabella
				session.getTransaction().commit();
				System.out.println("Chiudo la connessione.");
				session.close();
			} catch(Throwable he) {
				System.out.println(he.toString());
				he.printStackTrace();
			}
			User person = new User();
			return person;
		}
		
		public static int DalCreateUser(User newUser) {
			Session session = null;
			
			try {
				System.out.println("Apro la connessione.");
				session = MySessionFactory.openSession();
				System.out.println("Comincio la transazione");
				session.beginTransaction();
				session.save(newUser);
				// roba che crea il nuovo utente
				System.out.println("Committo la transazione.");
				session.getTransaction().commit();
				System.out.println("Chiudo la connessione.");
				session.close();
			} catch(Throwable he) {
				System.out.println(he.toString());
				he.printStackTrace();
				return 0;
			}
			return 1;
		}
		
	}
	
}
