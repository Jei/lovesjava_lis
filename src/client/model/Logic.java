package client.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import client.Controller;

public class Logic {

	public static SessionFactory MySessionFactory;
	static Controller controller;
	public static User MyUser;
	static char[] hexChar = {
		   '0' , '1' , '2' , '3' ,
		   '4' , '5' , '6' , '7' ,
		   '8' , '9' , 'a' , 'b' ,
		   'c' , 'd' , 'e' , 'f'};
	
	
	public Logic() {
	}
	
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
	    		// .addResource("client/User.hbm.xml");
	    		.addClass(client.model.User.class);
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
	
	// REGISTRAZIONE NUOVO UTENTE
	public void registerUser(String email, String name, String sname, Date birth, char gender, String cf, char[] pass) throws RegistrationException {
		byte[] md5pass = null;
		String hexString = null;
		User newUser = new User(); // Cpt. Obvious goes programming!
		User checkUser;
		
		checkUser = DAL.DalRetrieveUserInfo(email);
		if (checkUser == null) { 
		
			try {
				// inizializzo l'oggetto per creare il digest
				MessageDigest md = MessageDigest.getInstance("MD5");
				// resetto l'oggetto
				md.reset();
				String temp1 = new String(pass);
				md.update(temp1.getBytes());
				md5pass = md.digest();
				hexString = byteArrayToHexString(md5pass);
				System.out.println("MD5 password: " + hexString);
			} catch(NoSuchAlgorithmException e1) {
				System.out.println(e1.toString());
				e1.printStackTrace();
			}
		
			newUser.setName(name);
			newUser.setSname(sname);
			newUser.setEmail(email);
			newUser.setBirth(birth);
			newUser.setGender(gender);
			newUser.setCf(cf);
			newUser.setPass(hexString);
			newUser.setAdm(0);
			newUser.setBlocked(0);
		
			if (DAL.DalCreateUser(newUser) == 1) {
				System.out.println("Inserzione nel database riuscita.");
			} else {
				System.out.println("Inserzione nel database fallita.");
				throw new RegistrationException("Errore nell'inserimento del nuovo utente nel database.");
			}
		} else {
			System.out.println("Utente con mail " + email + " già presente nel database.");
			throw new RegistrationException("Utente con mail " + email + " già registrato.");
		}
	}
	
	//MODIFICA INFORMAZIONI UTENTE
	public void updateUserInfo(String name, String sname, char[] pass, Date birth, char gender, String cf) throws UpdateException {
		byte[] md5pass = null;
		String hexString = null;
		User modifiedUser = new User();
		
		try {
			// inizializzo l'oggetto per creare il digest
			MessageDigest md = MessageDigest.getInstance("MD5");
			// resetto l'oggetto
			md.reset();
			String temp1 = new String(pass);
			md.update(temp1.getBytes());
			md5pass = md.digest();
			hexString = byteArrayToHexString(md5pass);
			System.out.println("MD5 password: " + hexString);
		} catch(NoSuchAlgorithmException e1) {
			System.out.println(e1.toString());
			e1.printStackTrace();
		}
		
		modifiedUser.setId(MyUser.getId());
		modifiedUser.setName(name);
		modifiedUser.setSname(sname);
		modifiedUser.setEmail(MyUser.getEmail());
		modifiedUser.setBirth(birth);
		modifiedUser.setGender(gender);
		modifiedUser.setCf(cf);
		modifiedUser.setPass(hexString);
		modifiedUser.setAdm(MyUser.getAdm());
		modifiedUser.setBlocked(MyUser.getBlocked());
		
		if (DAL.DALUpdateUser(modifiedUser) == 1) {
			MyUser = modifiedUser;
		} else {
			System.out.println("Modifica informazioni utente fallita.");
			throw new UpdateException("Modifica informazioni utente fallita.");
		}
		
	}
	
	public void removeUser() throws RemoveException {
		
		if (DAL.DALRemoveUser(MyUser.email) == 1) {
			MyUser.setId(null);
			MyUser.setName(null);
			MyUser.setSname(null);
			MyUser.setEmail(null);
			MyUser.setPass(null);
			MyUser.setAdm(0);
			MyUser.setBirth(null);
			MyUser.setGender(null);
			MyUser.setCf(null);
			MyUser.setBlocked(0);
		} else {
			throw new RemoveException("Cancellazione utente non riuscita.");
		}
		
	}
	
	public void login(String mail, char[] pass) throws LoginException {
		byte[] md5pass = null;
		String hexPass = null;
		
		User checkUser = DAL.DalRetrieveUserInfo(mail);
		if (checkUser != null) {
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
			} else {
				throw new LoginException("Password non valida.");
			}
		} else {
			throw new LoginException("Utente inesistente.");
		}
	}
	
	public void logout() throws LogoutException {
		MyUser.setId(null);
		MyUser.setName(null);
		MyUser.setSname(null);
		MyUser.setEmail(null);
		MyUser.setPass(null);
		MyUser.setAdm(0);
		MyUser.setBirth(null);
		MyUser.setGender(null);
		MyUser.setCf(null);
		MyUser.setBlocked(0);
	}

	// ECCEZIONE: utente non trovato per il login
	public class LoginException extends Exception {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public LoginException(String reason) {
			super(reason);
		}
		
	}
	
	//ECCEZIONI
	public class RegistrationException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public RegistrationException(String reason) {
			super(reason);
		}
		
	}
	
	public class UpdateException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public UpdateException(String reason) {
			super(reason);
		}
		
	}
	
	// ECCEZIONE: eliminazione non riuscita
	public class RemoveException extends Exception {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public RemoveException(String reason) {
			super(reason);
		}
		
	}
	
	// ECCEZIONE: logout non riuscito
	public class LogoutException extends Exception {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public LogoutException(String reason) {
			super(reason);
		}
		
	}
	
}
