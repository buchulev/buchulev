package klicenka.presentation.ui;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * JFrame reprezentujici hlavni menu programmu 
 *
 */
public class MainWindow extends JFrame {

	// Panels

	public AccountView accountView;
	public ConfirmAccountPanel confirmAccountPanel;
	public CreateAccountPanel createAccountPanel;
	public CreateAccountByAdminPanel createAccountByAdminPanel;
	public DepartmentEditPanel departmentEditPanel;
	public DepartmentNamePanel departmentNamePanel;
	public LoginPanel loginPanel;
	public VzdatLicencePanel vzdatLicencePanel;
	public PozadatLicencePanel pozadatLicencePanel;
	public JPanel blankPanel;
	public ConfirmAccountByVedouciPanel confirmAccountByVedouciPanel;
	public ConfirmLicenceByVedouciPanel confirmLicenceByVedouciPanel;
	public BuyLicencePanel buyLicencePanel;
    
	/**
	 * Drop-down menu
	 */
	public JMenuBar menuBar;

	public CardLayout cardLayout;
	/**
	 * JPanel pro drop-down menu
	 */
	public JPanel panelForMenu;
	
	/**
	 * JPanel pro kontent
	 */
	public JPanel panelForContent;
/**
 * Konstruktor
 */
	public MainWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cardLayout = new CardLayout(0, 0);
		
		loginPanel = new LoginPanel();
		accountView = new AccountView();
		confirmAccountPanel = new ConfirmAccountPanel();
		createAccountPanel = new CreateAccountPanel();
		departmentEditPanel = new DepartmentEditPanel();
		departmentNamePanel = new DepartmentNamePanel();
		vzdatLicencePanel = new VzdatLicencePanel();
		blankPanel = new JPanel();
		pozadatLicencePanel =new PozadatLicencePanel();
		createAccountByAdminPanel = new CreateAccountByAdminPanel();
		confirmAccountByVedouciPanel = new ConfirmAccountByVedouciPanel();
		confirmLicenceByVedouciPanel = new ConfirmLicenceByVedouciPanel();
		buyLicencePanel = new BuyLicencePanel();
		getContentPane().setLayout(null);

		panelForMenu = new JPanel();
		panelForMenu.setBounds(10, 0, 537, 49);
		getContentPane().add(panelForMenu);

		panelForContent = new JPanel();
		panelForContent.setBounds(10, 55, 537, 600);
		getContentPane().add(panelForContent);
		panelForContent.setLayout(cardLayout);
		
		panelForContent.add(loginPanel, "loginPanel");
		panelForContent.add(accountView, "accountView");
		panelForContent.add(confirmAccountPanel, "confirmAccountPanel");
		panelForContent.add(createAccountPanel, "createAccountPanel");
		panelForContent.add(departmentEditPanel, "departmentEditPanel");
		panelForContent.add(departmentNamePanel, "departmentNamePanel");
		panelForContent.add(vzdatLicencePanel, "vzdatLicencePanel");
		panelForContent.add(blankPanel, "blankPanel");
		panelForContent.add(pozadatLicencePanel, "pozadatLicencePanel");
		panelForContent.add(createAccountByAdminPanel, "createAccountByAdminPanel");
		panelForContent.add(confirmAccountByVedouciPanel, "confirmAccountByVedouciPanel");
		panelForContent.add(confirmLicenceByVedouciPanel, "confirmLicenceByVedouciPanel");
		panelForContent.add(buyLicencePanel, "buyLicencePanel");
		cardLayout.show(panelForContent,"loginPanel");

		

		setBounds(250, 80, 500, 600);
		setVisible(true);
	}
/**
 * Inicializuje drop-down menu
 */
	public void initMenuBar() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

	}
/**
 * Inicializuje menu pro veškerého uživatele
 */
	public void initUserMenu() {
		JMenu userMenu = new JMenu("Uzivatelske menu");
		menuBar.add(userMenu);
		
		JMenuItem spravaUctu = new JMenuItem("Sprava Uctu");
		spravaUctu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel("accountView");
				
			}
			
		});
		JMenuItem zadaniLicence = new JMenuItem("Pozadat o licence");
		zadaniLicence.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel("pozadatLicencePanel");
				
			}
		});
		
		JMenuItem vzdatLicence = new JMenuItem("Vzdat licence");
		vzdatLicence.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel("vzdatLicencePanel");
				
			}
			
		});
		
		userMenu.add(spravaUctu);
		userMenu.add(zadaniLicence);
		userMenu.add(vzdatLicence);
		userMenu.add(zadaniLicence);

	}
/**
 * Inicializuje menu pro administratora
 */
	public void initAdminMenu() {
		JMenu adminMenu = new JMenu("Administratorske menu");
		menuBar.add(adminMenu);
		JMenuItem spravaOddeleni = new JMenuItem("Sprava odděleni");
		spravaOddeleni.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchPanel("departmentEditPanel");
				
			}
			
		});
		JMenuItem schvalitUzivatele = new JMenuItem("Schvalit uzivatele");
		schvalitUzivatele.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel("confirmAccountPanel");
				
			}
			
		});
		
		adminMenu.add(schvalitUzivatele);
		
		JMenuItem vytvaritUzivatele = new JMenuItem("Vytvarit uzivatele");
		vytvaritUzivatele.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel("createAccountByAdminPanel");
				
			}
			
		});
		
		adminMenu.add(schvalitUzivatele);
		adminMenu.add(vytvaritUzivatele);
		adminMenu.add(spravaOddeleni);
	}
/**
 * Inicializuje menu pro vedouciho
 */
	public void initVedouciMenu() {
		JMenu vedouciMenu = new JMenu("Vedouci menu");
		menuBar.add(vedouciMenu);

		JMenuItem schvalitUzivatele = new JMenuItem("Schvalit uzivatele");
		schvalitUzivatele.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanel("confirmAccountByVedouciPanel");
				
			}
			
		});
		
		JMenuItem spravaOddeleni = new JMenuItem("Sprava odděleni");
		spravaOddeleni.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchPanel("departmentNamePanel");
				
			}
			
		});
		
		JMenuItem schvalitLicence = new JMenuItem("Schvalit licence");
		schvalitLicence.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchPanel("confirmLicenceByVedouciPanel");
				
			}
			
		});
		
		vedouciMenu.add(schvalitUzivatele);
		vedouciMenu.add(spravaOddeleni);
		vedouciMenu.add(schvalitLicence);
		
		

	}
/**
 * Inicializuje menu pro nakupčiho
 */
	public void initNakupciMenu() {
		JMenu nakupciMenu = new JMenu("Nakupči menu");
		menuBar.add(nakupciMenu);
		
		JMenuItem koupitLicence = new JMenuItem("Koupit licence");
		koupitLicence.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switchPanel("buyLicencePanel");
				
			}
			
		});
		
		nakupciMenu.add(koupitLicence);
	}
	
	/**
	 * Přepina JPanely v oblasti pro kontent
	 * @param name nazev JPanelu
	 */
	public void switchPanel(String name) {
		cardLayout.show(panelForContent,name);
	}
	/**
	 * Přepne na prazdny JPanel
	 */
	public void blankMenuPanel() {
		cardLayout.show(panelForContent, "blankPanel");
	}
}
