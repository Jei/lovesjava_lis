package client.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	//Creazione classe di prova;
	User test= new User();

	@Test
	public final void testRegister() {
		//Prendo le credenziali dalla classe prova
		String namee = test.name;
		String surname = test.sname;
		String mail = test.email;
		String password = test.pass;
		Date nascita = test.birth;
		Character genere = test.gender;
		String fiscalcode = test.cf;
		//Questa mi da problemi, non so perchè.
		assertEquals("Test fail; it's impossible doing login with right credential", 1, test.register(namee, surname, password, nascita, genere, fiscalcode));
				
		//Inserisco credenziali sbagliate
		namee = "Ciaoo";
		surname = "Ciaoo";
		password = "00001111";
		//nascita ="";
		genere = "F";
		fiscalcode = "FBBLNE90D54H294R";
		
		//Login con credenziali non giuste
		assertNotEquals("Test fail; it's possible doing login with no credential", 0, test.register(namee, surname, password, nascita, genere, fiscalcode));

	}

	@Test
	public final void testDelete() {
		assertEquals("Test fail; Delete tentative 1", 1, test.delete());
	}

	@Test
	public final void testLogin() {
		
		//Prendo le credenziali dalla classe prova
		String mail = test.email;
		String password = test.pass;
		
		//Questa mi da problemi, non so perchè.
		assertEquals("Test fail; it's impossible doing login with right credential", 1, test.login(mail, password));
		
		//Inserisco credenziali sbagliate
		mail = "ciao@gmail.com";
		password = "00001111";
		
		//Login con credenziali non giuste
		assertNotEquals("Test fail; it's possible doing login with no credential", 0, test.login(mail, password));
	}

	@Test
	public final void testLogout() {
		assertEquals("Test fail; Logout tentative", 1, test.logout());
	}

}
