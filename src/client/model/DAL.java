package client.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import client.model.User;
import client.model.Logic;

public class DAL {
	
	public static User DalRetrieveUserInfo(String email) throws HibernateException {
		Session session = null;
		User foundUser = null;
		try {
			System.out.println("openSession");
			session = Logic.MySessionFactory.openSession();
			System.out.println("beginTransaction");
			session.beginTransaction();
			foundUser = (User) session.createQuery("from User as user where USER_EMAIL = ?").setString(0, email).uniqueResult();
			System.out.println("commit");
			session.getTransaction().commit();
			System.out.println("close");
			session.close();
		} catch(Throwable he) {
			System.out.println(he.toString());
			he.printStackTrace();
		}
		return foundUser;
	}
	
	public static int DalCreateUser(User newUser) {
		Session session = null;
		
		try {
			System.out.println("Apro la connessione.");
			session = Logic.MySessionFactory.openSession();
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
