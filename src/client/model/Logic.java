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
import client.model.User.*;
import client.model.DAL.*;

public class Logic {

	public static SessionFactory MySessionFactory;
	static Controller controller;
	public static User MyUser;
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
	
	public static int registerUser(String email, String name, String sname, Date birth, char gender, String cf, char[] pass) {
		byte[] md5pass = null;
		String hexString;
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
		
			if (DAL.DalCreateUser(newUser) == 1) {
				System.out.println("Inserzione nel database riuscita.");
				return 1;
			} else {
				System.out.println("Inserzione nel database fallita.");
				return 0;
			}
		} else {
			System.out.println("Utente con mail " + email + " già presente nel database.");
			return 0;
		}
	}
	
}
