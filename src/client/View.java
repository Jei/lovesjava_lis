package client;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import client.Controller.*;
import client.model.Logic;
import client.model.User;

public class View {
	
	static Controller controller;
	
	//FORM DI LOGIN
	public static class LoginForm extends JFrame {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		JTextField UserInput = new JTextField(10);
		JPasswordField PassInput = new JPasswordField(10);
		JPanel LogPanel = new JPanel();
		JPanel RegPanel = new JPanel();
		JButton SubmitLogin = new JButton("Login");
		JButton RegStart = new JButton("Registrati");
		
		// costruttore del frame di login
		public LoginForm() {

			// finestra
			super("Car Pooling");
			
			// imposto il layout del frame
	        setLayout(new FlowLayout());
	    
			// aggiungo il panel login
			add(LogPanel);
			
			// aggiungo il panel registrazione
			add(RegPanel);
	    
			// imposto il layout a blocchi per i due pannelli
			LogPanel.setLayout(new BoxLayout(LogPanel, BoxLayout.PAGE_AXIS));
			RegPanel.setLayout(new BoxLayout(RegPanel, BoxLayout.PAGE_AXIS));
			
			// aggiungo gli elementi di LogPanel
			LogPanel.add(new JLabel("e-mail:"));
			LogPanel.add(UserInput);
			LogPanel.add(new JLabel("password:"));
			LogPanel.add(PassInput);
			LogPanel.add(SubmitLogin);
			
			// aggiungo gli elementi di RegPanel
			RegPanel.add(new JLabel("Non sei registrato?"));
			RegPanel.add(RegStart);
			
			// creo i bordi dei pannelli
			TitledBorder loginBorder;
			TitledBorder registrationBorder;
			loginBorder = BorderFactory.createTitledBorder("Login");
			registrationBorder = BorderFactory.createTitledBorder("Registrazione");
			LogPanel.setBorder(loginBorder);
			RegPanel.setBorder(registrationBorder);
	        
	        // aggiungo i listener ai pulsanti di login e registrazione
			LoginStart aclilo = new Controller.LoginStart();
	        SubmitLogin.addActionListener(aclilo);
	        RegistrationStart aclire = new Controller.RegistrationStart();
	        RegStart.addActionListener(aclire);
	        
	        // aggiusto le dimensioni del frame
	        pack();

	        setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	}

	//FORM DI REGISTRAZIONE
	public static class RegisteringForm extends JFrame {
		
		/**
		 * 
		 */
		String[] days = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
				"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};
		String[] months = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		String[] years = {"1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", 
				"1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989",
				"1990", "1991", "1992", "1993", "1994"};
		String[] genders = {"m", "f"};
		private static final long serialVersionUID = 1L;
		JTextField nameInput = new JTextField(10);
		JTextField snameInput = new JTextField(10);
		JTextField mailInput = new JTextField(10);
		JPasswordField passInput = new JPasswordField(10);
		JPasswordField confirmInput = new JPasswordField(10);
		JTextField cfInput = new JTextField(10);
		JComboBox dayInput = new JComboBox(days);
		JComboBox monthInput = new JComboBox(months);
		JComboBox yearInput = new JComboBox(years);
		JComboBox genderInput = new JComboBox(genders);
		
		public RegisteringForm() {
			
			// finestra
			super("Car Pooling");
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			// panel dei campi di inserimento
			JPanel inputPanel = new JPanel();
			JPanel datePanel = new JPanel();
			JPanel namePanel = new JPanel();
			JPanel snamePanel = new JPanel();
			JPanel emailPanel = new JPanel();
			JPanel passPanel = new JPanel();
			JPanel confirmPanel = new JPanel();
			JPanel genderPanel = new JPanel();
			JPanel cfPanel = new JPanel();
			JPanel buttonsPanel = new JPanel();
			
			// imposto il layout a blocchi per il pannello di inserimento
			inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
			datePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			namePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			snamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			emailPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			passPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			confirmPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			cfPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			// aggiungo gli elementi di InputPanel
			// nome
			namePanel.add(new JLabel("Nome:"));
			namePanel.add(nameInput);
			inputPanel.add(namePanel);
			// cognome
			snamePanel.add(new JLabel("Cognome:"));
			snamePanel.add(snameInput);
			inputPanel.add(snamePanel);
			// mail
			emailPanel.add(new JLabel("E-mail:"));
			emailPanel.add(mailInput);
			inputPanel.add(emailPanel);
			// password
			passPanel.add(new JLabel("Password:"));
			passPanel.add(passInput);
			inputPanel.add(passPanel);
			confirmPanel.add(new JLabel("Conferma password:"));
			confirmPanel.add(confirmInput);
			inputPanel.add(confirmPanel);
			// data di nascita
			inputPanel.add(new JLabel("Data di nascita (g/m/a):"));
			datePanel.add(dayInput);
			datePanel.add(monthInput);
			datePanel.add(yearInput);
			inputPanel.add(datePanel);
			// sesso
			genderPanel.add(new JLabel("Sesso:"));
			genderPanel.add(genderInput);
			inputPanel.add(genderPanel);
			// codice fiscale
			cfPanel.add(new JLabel("Codice Fiscale:"));
			cfPanel.add(cfInput);
			inputPanel.add(cfPanel);
			// pulsante di invio registrazione
			JButton submitRegistration = new JButton("Invia");
			buttonsPanel.add(submitRegistration);
			// pulsante di ritorno alla schermata di login
			JButton cancelRegistration = new JButton("Annulla");
			buttonsPanel.add(cancelRegistration);
			inputPanel.add(buttonsPanel);
			
			// aggiungo il pannello al frame
	        add(inputPanel);
	        
	        // imposto il layout del frame
	        setLayout(new FlowLayout());
	        
	        // aggiungo i listener ai pulsanti di invio e cancellazione
	        RegistrationSend abse = new Controller.RegistrationSend();
	        submitRegistration.addActionListener(abse);
	        RegistrationAbort abre = new Controller.RegistrationAbort();
	        cancelRegistration.addActionListener(abre);
	        
	        // aggiusto le dimensioni del frame
	        pack();
			
		}
	}
	
	// FRAME PRINCIPALE DELL'UTENTE
	public static class UserPage extends JFrame {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public UserPage(User user) {
			// finestra
			super("Car Pooling");
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JPanel MenuPanel = new JPanel();
			
			MenuPanel.setLayout(new BoxLayout(MenuPanel, BoxLayout.PAGE_AXIS));
			
			MenuPanel.add(new JLabel("Benvenuto " + user.getName() + " " + user.getSname()));
			
			JButton ViewOffersButton = new JButton("Visualizza offerte");
			MenuPanel.add(ViewOffersButton);
			JButton ViewProfileButton = new JButton("Il tuo profilo");
			MenuPanel.add(ViewProfileButton);
			JButton ViewUsersButton = new JButton("Lista utenti");
			MenuPanel.add(ViewUsersButton);
			JButton SendReportButton = new JButton("Contatta l'admin");
			MenuPanel.add(SendReportButton);
			JButton SendServiceFeedbackButton = new JButton("Valutaci!");
			MenuPanel.add(SendServiceFeedbackButton);
			JButton ExitButton = new JButton("Esci");
			MenuPanel.add(ExitButton);
			
			add(MenuPanel);
			
			setLayout(new FlowLayout());
			
			ExitUserPage lol = new Controller.ExitUserPage();
			ExitButton.addActionListener(lol);
			
			pack();
			
		}
		
	}
	
	// FRAME PRINCIPALE DELL'AMMINISTRATORE
public static class AdminPage extends JFrame {
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public AdminPage(User admin) {
			// finestra
			super("Car Pooling - Admin");
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JPanel AdMenuPanel = new JPanel();
			
			AdMenuPanel.setLayout(new BoxLayout(AdMenuPanel, BoxLayout.PAGE_AXIS));
			
			AdMenuPanel.add(new JLabel("Benvenuto, amministratore " + admin.getName() + " " + admin.getSname()));
			
			JButton ExitButtonAd = new JButton("Esci");
			AdMenuPanel.add(ExitButtonAd);
			
			add(AdMenuPanel);
			
			setLayout(new FlowLayout());
			
			ExitUserPageAd lol = new Controller.ExitUserPageAd();
			ExitButtonAd.addActionListener(lol);
			
			pack();
			
		}
		
	}
	
}
