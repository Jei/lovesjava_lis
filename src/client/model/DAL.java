package client.model;

import org.hibernate.Session;

import client.model.User;
import client.model.Logic;

public class DAL {
	
	public static User DalRetrieveUserInfo(String email) {
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
			System.out.println("openSession");
			session = Logic.MySessionFactory.openSession();
			System.out.println("beginTransaction");
			session.beginTransaction();
			session.save(newUser);
			System.out.println("commit");
			session.getTransaction().commit();
			System.out.println("close");
			session.close();
		} catch(Throwable he) {
			System.out.println(he.toString());
			he.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public static int DALUpdateUser(User target) {
		Session session = null;
		
		try {
			System.out.println("openSession");
			session = Logic.MySessionFactory.openSession();
			System.out.println("beginTransaction");
			session.beginTransaction();
			session.update(target);
			System.out.println("commit");
			session.getTransaction().commit();
			System.out.println("close");
			session.close();
		} catch(Throwable he) {
			System.out.println(he.toString());
			he.printStackTrace();
			return 0;
		}
		
		return 1;
	}
	
	public static int DALRemoveUser(String email) {
		Session session = null;
		User foundUser = null;
		try {
			System.out.println("openSession");
			session = Logic.MySessionFactory.openSession();
			System.out.println("beginTransaction");
			session.beginTransaction();
			// rimuovi utente con email "email"
			foundUser = (User) session.createQuery("from User as user where USER_EMAIL = ?").setString(0, email).uniqueResult();
			session.delete(foundUser);
			System.out.println("commit");
			session.getTransaction().commit();
			System.out.println("close");
			session.close();
		} catch(Throwable he) {
			System.out.println(he.toString());
			he.printStackTrace();
			return 0;
		}
		
		return 1;
	}

}
