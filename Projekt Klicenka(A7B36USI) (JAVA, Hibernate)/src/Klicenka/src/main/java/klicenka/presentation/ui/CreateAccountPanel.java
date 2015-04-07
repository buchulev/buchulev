package klicenka.presentation.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;

import klicenka.persistence.model.Oddeleni;
/**
 * 
 * JPanel pro podani žadosti o vytvařeni uživatele
 *
 */
public class CreateAccountPanel extends JPanel {
	
	
	
	public JTextField userName;
	public JTextField userSurname;
	public JTextField userEmail;
	public JTextField userLoginName;
	public JTextField userPassword1;
	public JTextField userPassword2;
	public JTextField comment;
	
	public JRadioButton radioAdminButton;
	public JRadioButton radioNakupciButton;
	public JRadioButton radioOddeleniButton;
	public JButton backButton;
	public JButton sendButton;
	
	public JComboBox<Oddeleni> oddeleniSelect;
	
	public CreateAccountPanel() {
		setLayout(null);
		
		JLabel label = new JLabel("Tvorba zadosti o tvorbu noveho uivatele");
		label.setBounds(10, 11, 285, 20);
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(label);
		
		JLabel label_1 = new JLabel("Jmeno");
		label_1.setBounds(10, 42, 72, 14);
		add(label_1);
		
		userName = new JTextField();
		userName.setColumns(10);
		userName.setBounds(68, 42, 169, 20);
		add(userName);
		
		JLabel label_2 = new JLabel("Prijmeni");
		label_2.setBounds(10, 81, 72, 14);
		add(label_2);
		
		userSurname = new JTextField();
		userSurname.setColumns(10);
		userSurname.setBounds(68, 78, 169, 20);
		add(userSurname);
		
		JLabel label_3 = new JLabel("Email");
		label_3.setBounds(10, 115, 72, 14);
		add(label_3);
		
		userEmail = new JTextField();
		userEmail.setColumns(10);
		userEmail.setBounds(68, 109, 169, 20);
		add(userEmail);
		
		JLabel label_4 = new JLabel("UserName");
		label_4.setBounds(10, 147, 72, 14);
		add(label_4);
		
		userLoginName = new JTextField();
		userLoginName.setColumns(10);
		userLoginName.setBounds(68, 141, 169, 20);
		add(userLoginName);
		
		JLabel label_5 = new JLabel("Password");
		label_5.setBounds(10, 187, 72, 14);
		add(label_5);
		
		userPassword1 = new JTextField();
		userPassword1.setFont(UIManager.getFont("PasswordField.font"));
		userPassword1.setColumns(10);
		userPassword1.setBounds(68, 184, 169, 20);
		add(userPassword1);
		
		userPassword2 = new JTextField();
		userPassword2.setFont(UIManager.getFont("PasswordField.font"));
		userPassword2.setColumns(10);
		userPassword2.setBounds(247, 184, 189, 20);
		add(userPassword2);
		
		 radioAdminButton = new JRadioButton("Zadate o roli administratora");
		radioAdminButton.setBounds(254, 38, 235, 23);
		add(radioAdminButton);
		
		 radioNakupciButton = new JRadioButton("Zadate o roli nakupciho");
		radioNakupciButton.setBounds(257, 77, 202, 23);
		add(radioNakupciButton);
		
		JLabel label_6 = new JLabel("Spadate pod nejake oddeleni?");
		label_6.setBounds(10, 226, 169, 31);
		add(label_6);
		
		radioOddeleniButton = new JRadioButton("");
		radioOddeleniButton.setBounds(194, 230, 43, 23);
		add(radioOddeleniButton);
		
	    oddeleniSelect = new JComboBox<Oddeleni>();
		oddeleniSelect.setBounds(240, 231, 196, 20);
		add(oddeleniSelect);
		
		JLabel label_7 = new JLabel("Komentar pro administratory k vytvoreni noveho uctu");
		label_7.setBounds(10, 279, 450, 14);
		add(label_7);
		
		comment = new JTextField();
		comment.setColumns(10);
		comment.setBounds(10, 304, 450, 102);
		add(comment);
		
		backButton = new JButton("Zpet na prihlasovaci obrazovku");
		backButton.setBounds(10, 432, 117, 23);
		add(backButton);
		
		
		sendButton = new JButton("Odeslat zadost");
		sendButton.setBounds(302, 432, 157, 23);
		add(sendButton);
	}
}
