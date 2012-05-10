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
		JLabel ErrorLabel = new JLabel("Valori di login errati!");
		
		public void ViewLoginError() {
			LogPanel.add(ErrorLabel);
			LogPanel.revalidate();
		}
		
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
		String[] years = {"1990", "1991", "1992", "1993", "1994", "1995"};
		String[] genders = {"m", "f"};
		private static final long serialVersionUID = 1L;
		JTextField nameInput = new JTextField(10);
		JTextField snameInput = new JTextField(10);
		JTextField mailInput = new JTextField(10);
		JPasswordField passInput = new JPasswordField(10);
		JPasswordField confirmInput = new JPasswordField(10);
		JTextField cfInput = new JTextField(10);
		JList dayList = new JList(days);
		JList monthList = new JList(months);
		JList yearList = new JList(years);
		JScrollPane dayInput = new JScrollPane(dayList);
		JScrollPane monthInput = new JScrollPane(monthList);
		JScrollPane yearInput = new JScrollPane(yearList);
		JList genderList = new JList(genders);
		JScrollPane genderInput = new JScrollPane(genderList);
		
		public RegisteringForm() {
			
			// finestra
			super("Car Pooling");
			
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			// panel dei campi di inserimento
			JPanel inputPanel = new JPanel();
			
			// imposto il layout a blocchi per il pannello di inserimento
			inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
			
			// aggiungo gli elementi di InputPanel
			// nome
			inputPanel.add(new JLabel("Nome:"));
			inputPanel.add(nameInput);
			// cognome
			inputPanel.add(new JLabel("Cognome"));
			inputPanel.add(snameInput);
			// mail
			inputPanel.add(new JLabel("E-mail:"));
			inputPanel.add(mailInput);
			// password
			inputPanel.add(new JLabel("Password:"));
			inputPanel.add(passInput);
			inputPanel.add(new JLabel("Conferma password:"));
			inputPanel.add(confirmInput);
			// data di nascita
			dayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			dayList.setLayoutOrientation(JList.VERTICAL);
			dayList.setVisibleRowCount(1);
			monthList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			monthList.setLayoutOrientation(JList.VERTICAL);
			monthList.setVisibleRowCount(1);
			yearList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			yearList.setLayoutOrientation(JList.VERTICAL);
			yearList.setVisibleRowCount(1);
			inputPanel.add(dayInput);
			inputPanel.add(monthInput);
			inputPanel.add(yearInput);
			genderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			genderList.setLayoutOrientation(JList.VERTICAL);
			genderList.setVisibleRowCount(1);
			inputPanel.add(genderInput);
			// codice fiscale
			inputPanel.add(new JLabel("Codice Fiscale:"));
			inputPanel.add(cfInput);
			// pulsante di invio registrazione
			JButton submitRegistration = new JButton("Invia");
			inputPanel.add(submitRegistration);
			// pulsante di ritorno alla schermata di login
			JButton cancelRegistration = new JButton("Annulla");
			inputPanel.add(cancelRegistration);
			
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
