// Qui ci va la parte di gestione degli input dell'utente (listener...)

package client;

import client.View.*;
import client.model.*;
import client.model.Logic.*;
import client.model.User.*;

import java.awt.event.*;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Controller {

	static View view;
	static Logic MyLogic;
	static RegisteringForm MyRegForm;
	static UserPage MyUserPage;
	static AdminPage MyAdminPage;
	static LoginForm MyLogForm = new LoginForm();
	
	//COSTRUTTORE
	public Controller(View view, Logic logic) {
		Controller.view = view;
		Controller.MyLogic = logic;
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
			try {
				Logic.MyUser.login(ui, pi); // provo a chiamare il login
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
					JOptionPane.showMessageDialog(MyLogForm,
						    "Questo utente è stato bloccato dall'amministratore.",
						    "Accesso fallito",
						    JOptionPane.ERROR_MESSAGE);
				}
			} catch (LoginException le) {
				System.out.println(le);
				JOptionPane.showMessageDialog(MyLogForm,
					    le.getMessage(),
					    "Accesso fallito",
					    JOptionPane.WARNING_MESSAGE);
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
				String year = (String) MyRegForm.yearInput.getSelectedItem();
				String month = (String) MyRegForm.monthInput.getSelectedItem();
				String day = (String) MyRegForm.dayInput.getSelectedItem();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				birth = formatter.parse(year + "/" + month + "/" + day);
			} catch (ParseException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
			String genderString = (String) MyRegForm.genderInput.getSelectedItem();
			char[] genderTemp = genderString.substring(0).toCharArray();
			char gender = genderTemp[0];
			
			if (!name.equals("") && !sname.equals("") && !email.equals("") && !cf.equals("") && pass1.length != 0 && Arrays.equals(pass1, pass2)) {
				System.out.println(name);
				System.out.println(sname);
				System.out.println(email);
				
				try {
					MyLogic.registerUser(email, name, sname, birth, gender, cf, pass1);
					System.out.println("Utente " + name + " " + sname + " registrato correttamente.");
					JOptionPane.showMessageDialog(MyLogForm,
							"Utente " + name + " " + sname + " registrato correttamente.",
						    "Registrazione riuscita",
						    JOptionPane.INFORMATION_MESSAGE);
					MyLogForm = new LoginForm();
					MyRegForm.dispose();
				} catch (RegistrationException re) {
					System.out.println(re);
					JOptionPane.showMessageDialog(MyLogForm,
						    re.getMessage(),
						    "Registrazione fallita",
						    JOptionPane.WARNING_MESSAGE);
				}
			} else {
				System.out.println("Non sono stati riempiti tutti i campi.");
				JOptionPane.showMessageDialog(MyLogForm,
					    "Non sono stati riempiti tutti i campi.",
					    "Errore",
					    JOptionPane.ERROR_MESSAGE);
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
	
	//LISTENER PER BOTTONE VISUALIZZAZIONE PROFILO UTENTE
	public static class ViewProfile implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			MyUserPage.MenuPanel.setVisible(false);
			MyUserPage.UserInfoPanel.setVisible(true);
			MyUserPage.pack();
			MyUserPage.validate();
		}
	}
	
	//LISTENER PER BOTTONE INIZIO MODIFICA INFORMAZIONI UTENTE
	public static class ModifyUserInfo implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			MyUserPage.ModifyUserInfoButton.setVisible(false);
			MyUserPage.CancelModifyUserInfoButton.setVisible(true);
			MyUserPage.SaveUserInfoButton.setVisible(true);
			MyUserPage.nameInput.setEditable(true);
			MyUserPage.snameInput.setEditable(true);
			MyUserPage.nameInput.setEditable(true);
			MyUserPage.passInput.setEditable(true);
			MyUserPage.confirmInput.setEditable(true);
			MyUserPage.dayInput.setEnabled(true);
			MyUserPage.monthInput.setEnabled(true);
			MyUserPage.yearInput.setEnabled(true);
			MyUserPage.genderInput.setEnabled(true);
			MyUserPage.cfInput.setEditable(true);
			MyUserPage.pack();
			MyUserPage.validate();
		}
	}
	
	//LISTENER PER BOTTONE SALVA NUOVE INFORMAZIONI UTENTE
	public static class SaveUserInfo implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			String newName = MyUserPage.nameInput.getText();
			String newSname = MyUserPage.snameInput.getText();
			char[] newPass1 = MyUserPage.passInput.getPassword();
			char[] newPass2 = MyUserPage.confirmInput.getPassword();
			Date newBirth = null;
			try {
				String year = (String) MyUserPage.yearInput.getSelectedItem();
				String month = (String) MyUserPage.monthInput.getSelectedItem();
				String day = (String) MyUserPage.dayInput.getSelectedItem();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				newBirth = formatter.parse(year + "/" + month + "/" + day);
			} catch (ParseException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
			String newGenderString = (String) MyUserPage.genderInput.getSelectedItem();
			char[] newGenderTemp = newGenderString.substring(0).toCharArray();
			char newGender = newGenderTemp[0];
			String newCf = MyUserPage.cfInput.getText();
			
			
			if (!newName.equals("") && !newSname.equals("") && !newCf.equals("") && newPass1.length != 0 && Arrays.equals(newPass1, newPass2)) {
			
				try {
					MyLogic.UpdateUserInfo(newName, newSname, newPass1, newBirth, newGender, newCf);
					System.out.println("Informazioni utente modificate con successo.");
					JOptionPane.showMessageDialog(MyLogForm,
							"Informazioni utente modificate con successo.",
						    "Modifica riuscita",
						    JOptionPane.INFORMATION_MESSAGE);
				} catch(UpdateException ue) {
					System.out.println(ue);
					JOptionPane.showMessageDialog(MyLogForm,
						    ue.getMessage(),
						    "Modifica fallita",
						    JOptionPane.WARNING_MESSAGE);
				} finally {
					MyUserPage.nameInput.setText(Logic.MyUser.getName());
					MyUserPage.snameInput.setText(Logic.MyUser.getSname());
					MyUserPage.passInput.setText("");
					MyUserPage.confirmInput.setText("");
					Calendar dateReader = Calendar.getInstance();
					dateReader.setTime(Logic.MyUser.getBirth());
					MyUserPage.dayInput.setSelectedItem("" + dateReader.get(Calendar.DAY_OF_MONTH));
					MyUserPage.monthInput.setSelectedItem("" + (dateReader.get(Calendar.MONTH) + 1)); //gennaio == 0
					MyUserPage.yearInput.setSelectedItem("" + dateReader.get(Calendar.YEAR));
					MyUserPage.genderInput.setSelectedItem("" + Logic.MyUser.getGender());
					MyUserPage.cfInput.setText(Logic.MyUser.getCf());
					MyUserPage.ModifyUserInfoButton.setVisible(true);
					MyUserPage.CancelModifyUserInfoButton.setVisible(false);
					MyUserPage.SaveUserInfoButton.setVisible(false);
					MyUserPage.nameInput.setEditable(false);
					MyUserPage.snameInput.setEditable(false);
					MyUserPage.nameInput.setEditable(false);
					MyUserPage.passInput.setEditable(false);
					MyUserPage.confirmInput.setEditable(false);
					MyUserPage.dayInput.setEnabled(false);
					MyUserPage.monthInput.setEnabled(false);
					MyUserPage.yearInput.setEnabled(false);
					MyUserPage.genderInput.setEnabled(false);
					MyUserPage.cfInput.setEditable(false);
					MyUserPage.pack();
					MyUserPage.validate();
				}
			} else {
				System.out.println("Non sono stati riempiti tutti i campi.");
				JOptionPane.showMessageDialog(MyLogForm,
					    "Non sono stati riempiti tutti i campi.",
					    "Errore",
					    JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	//LISTENER PER BOTTONE ANNULLA MODIFICA INFORMAZIONI UTENTE
	public static class CancelModifyUserInfo implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			MyUserPage.nameInput.setText(Logic.MyUser.getName());
			MyUserPage.snameInput.setText(Logic.MyUser.getSname());
			MyUserPage.passInput.setText("");
			MyUserPage.confirmInput.setText("");
			Calendar dateReader = Calendar.getInstance();
			dateReader.setTime(Logic.MyUser.getBirth());
			MyUserPage.dayInput.setSelectedItem("" + dateReader.get(Calendar.DAY_OF_MONTH));
			MyUserPage.monthInput.setSelectedItem("" + (dateReader.get(Calendar.MONTH) + 1)); //gennaio == 0
			MyUserPage.yearInput.setSelectedItem("" + dateReader.get(Calendar.YEAR));
			MyUserPage.genderInput.setSelectedItem("" + Logic.MyUser.getGender());
			MyUserPage.cfInput.setText(Logic.MyUser.getCf());
			MyUserPage.ModifyUserInfoButton.setVisible(true);
			MyUserPage.CancelModifyUserInfoButton.setVisible(false);
			MyUserPage.SaveUserInfoButton.setVisible(false);
			MyUserPage.nameInput.setEditable(false);
			MyUserPage.snameInput.setEditable(false);
			MyUserPage.nameInput.setEditable(false);
			MyUserPage.passInput.setEditable(false);
			MyUserPage.confirmInput.setEditable(false);
			MyUserPage.dayInput.setEnabled(false);
			MyUserPage.monthInput.setEnabled(false);
			MyUserPage.yearInput.setEnabled(false);
			MyUserPage.genderInput.setEnabled(false);
			MyUserPage.cfInput.setEditable(false);
			MyUserPage.pack();
			MyUserPage.validate();
		}
	}
	
	//LISTENER PER BOTTONE USCITA INFORMAZIONI UTENTE
	public static class ExitUserInfo implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			MyUserPage.UserInfoPanel.setVisible(false);
			MyUserPage.MenuPanel.setVisible(true);
			MyUserPage.pack();
			MyUserPage.validate();
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
		Logic logic = new Logic();
		Controller controller = new Controller(view, logic);
	}

}
