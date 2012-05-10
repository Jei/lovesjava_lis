// Qui ci va la parte di gestione degli input dell'utente (listener...)

package client;

import client.View.*;
import client.model.*;

import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Controller {

	static View view;
	static RegisteringForm MyRegForm;
	static UserPage MyUserPage;
	static AdminPage MyAdminPage;
	static LoginForm MyLogForm = new LoginForm();
	
	//COSTRUTTORE
	public Controller(View view) {
		Controller.view = view;
		MyLogForm.setVisible(true); // visualizzo il frame di login
		client.model.Logic.configureSessionFactory();
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
			Logic.MyUser = new User();
			if (Logic.MyUser.login(ui, pi) == 1) { // se il login va a buon fine
				if (Logic.MyUser.blocked == 0) {
					MyLogForm.dispose(); // rimuovo il frame di login
					if (Logic.MyUser.adm == 0) { // se l'utente non è admin
						MyUserPage = new UserPage(Logic.MyUser); // creo un nuovo frame utente
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
						MyAdminPage = new AdminPage(client.model.Logic.MyUser); // creo un nuovo frame utente
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
				} else {
					System.out.println("L'utente è bloccato");
				}
			} else { // altrimenti visualizzo una notifica nella form di login
				MyLogForm.ViewLoginError();
			}
		}
	}
	
	//LISTENER PER BOTTONE INVIO REGISTRAZIONE
	public static class RegistrationSend implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			String name = MyRegForm.nameInput.getText();
			String sname = MyRegForm.snameInput.getText();
			String email = MyRegForm.mailInput.getText();
			String cf = MyRegForm.cfInput.getText();
			char[] pass1 = MyRegForm.passInput.getPassword();
			char[] pass2 = MyRegForm.confirmInput.getPassword();
			Date birth = null;
			try {
				String year = (String) MyRegForm.yearList.getSelectedValue();
				String month = (String) MyRegForm.monthList.getSelectedValue();
				String day = (String) MyRegForm.dayList.getSelectedValue();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				birth = formatter.parse(year + "/" + month + "/" + day);
			} catch (ParseException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
			String genderString = (String) MyRegForm.genderList.getSelectedValue();
			char[] genderTemp = genderString.substring(0).toCharArray();
			char gender = genderTemp[0];
			
			if (name != "" & sname != "" & email != "" & cf != "" & pass1 != null & Arrays.equals(pass1, pass2)) {
				if (client.model.Logic.registerUser(email, name, sname, birth, gender, cf, pass1) == 1) {
					System.out.println("Utente " + name + " " + sname + " registrato correttamente.");
					MyLogForm = new LoginForm();
					MyRegForm.dispose();
				} else {
					System.out.println("Creazione utente fallita.");
				}
			}
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
			if (client.model.Logic.MyUser.logout() == 1) {
				MyLogForm = new LoginForm();
				MyUserPage.dispose();
			}
		}
	}
	
	//LISTENER PER BOTTONE USCITA DA PAGINA ADMIN
	public static class ExitUserPageAd implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (client.model.Logic.MyUser.logout() == 1) {
				MyLogForm = new LoginForm();
				MyAdminPage.dispose();
			}
		}
	}
	
	public static void main(String[] args) {
		View view = new View();
		Controller controller = new Controller(view);
	}

}
