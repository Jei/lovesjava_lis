package client;

import static org.junit.Assert.*;

import org.junit.Test;
import client.Model;
import client.Model.Travel;
import client.Model.Prenotation;

public class ModelTest {
	
	String mail;
	
	@Test
	public final void testViewTravelsList() {
		Travel[] result = Model.viewTravelsList(mail);
		assertNotNull("viewTravelsList() return un expected NULL", result);
	}

	@Test
	public final void testViewPrenotationsList() {
		Prenotation[] result = Model.viewPrenotationsList(mail);
		assertNotNull("viewPrenotationList() return un expected NULL", result);
	}

	@Test
	public final void testViewOwnTravelsList() {
		Travel[] result = Model.viewOwnTravelsList(mail);
		assertNotNull("viewOwnTravelsList() return un expected NULL", result);
	}

	@Test
	public final void testViewUsersList() {
		User[] result = Model.viewUsersList(mail);
		assertNotNull = ("viewUsersList() return un expected NULL", result);
	}

}