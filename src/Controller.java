// Qui ci va la parte di gestione degli input dell'utente (listener...)

package client;

import client.View.*;
import client.Model.*;

import java.awt.event.*;

public class Controller {

	static View view;
	static Model model;
	static Persona MyPersona;
	static RegisteringForm MyRegForm;
	static UserPage MyUserPage;
	static AdminPage MyAdminPage;
	static LoginForm MyLogForm = new LoginForm();
	
	//COSTRUTTORE
	public Controller(Model model, View view) {
		Controller.view = view;
		Controller.model = model;
		MyLogForm.setVisible(true); // visualizzo il frame di login
	}

	//LISTENER PER BOTTONE REGISTRAZIONE
	public static class RegistrationStart implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			MyRegForm = new RegisteringForm(); // creo un nuovo frame di registrazione
    		MyLogForm.dispose(); // rimuovo il frame di login
    		MyRegForm.addWindowListener(new WindowListener() {
    			@Override
    			public void windowOpened(WindowEvent e) {
    				
    			}
    			@Override
    			public void windowClosed(WindowEvent e) {
    				MyLogForm = new LoginForm(); // se la finestra di registrazione viene chiusa non dal bottone
    				MyLogForm.setVisible(true);
    			}
				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
    		});
    		MyRegForm.setVisible(true); // visualizzo il frame di registrazione
    	}
	}
	
	//LISTENER PER BOTTONE LOGIN
	public static class LoginStart implements ActionListener {
		
		public void actionPerformed(ActionEvent evt) {
			String ui = MyLogForm.UserInput.getText();
			char[] pi = MyLogForm.PassInput.getPassword();
			MyPersona = new Persona();
			if (MyPersona.login(ui, pi) == 1) { // se il login va a buon fine
	    		MyLogForm.dispose(); // rimuovo il frame di login
				if (MyPersona.adm == 0) { // se l'utente non è admin
					MyUserPage = new UserPage(Model.MyUser); // creo un nuovo frame utente
					MyUserPage.addWindowListener(new WindowListener() {
		    			@Override
		    			public void windowOpened(WindowEvent e) {
		    				
		    			}
		    			@Override
		    			public void windowClosed(WindowEvent e) {
		    				MyLogForm = new LoginForm(); // se la finestra utente viene chiusa non dal bottone
		    				MyLogForm.setVisible(true);
		    			}
						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
		    		});
					MyUserPage.setVisible(true);
				} else {
					MyAdminPage = new AdminPage(Model.MyAdmin); // creo un nuovo frame utente
					MyAdminPage.addWindowListener(new WindowListener() {
		    			@Override
		    			public void windowOpened(WindowEvent e) {
		    				
		    			}
		    			@Override
		    			public void windowClosed(WindowEvent e) {
		    				MyLogForm = new LoginForm(); // se la finestra admin viene chiusa non dal bottone
		    				MyLogForm.setVisible(true);
		    			}
						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub
							
						}
		    		});
					MyAdminPage.setVisible(true);
				}
			} else { // altrimenti visualizzo una notifica nella form di login
				MyLogForm.ViewLoginError();
			}
		}
	}
	
	//LISTENER PER BOTTONE INVIO REGISTRAZIONE
	public static class RegistrationSend implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			// controllo se tutti i campi sono stati riempiti
    		// controllo se passInput e confirmInput coincidono
    		// recupero tutti i dati
    		// invio la richiesta di registrazione
    		// a registrazione completata riapro la schermata di login
    		// altrimenti visualizzo errore e rimango alla schermata attuale
		}
	}
	
	//LISTENER PER BOTTONE ANNULLA REGISTRAZIONE
	public static class RegistrationAbort implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			MyLogForm = new LoginForm();
			MyRegForm.dispose();
		}
	}
	
	//LISTENER PER BOTTONE USCITA DA PAGINA UTENTE
	public static class ExitUserPage implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (Model.MyUser.logout() == 1) {
				MyLogForm = new LoginForm();
				MyUserPage.dispose();
			}
		}
	}
	
	//LISTENER PER BOTTONE USCITA DA PAGINA ADMIN
	public static class ExitUserPageAd implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (Model.MyAdmin.logout() == 1) {
				MyLogForm = new LoginForm();
				MyAdminPage.dispose();
			}
		}
	}
	
	public static void main(String[] args) {
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(model, view);
	}

}
