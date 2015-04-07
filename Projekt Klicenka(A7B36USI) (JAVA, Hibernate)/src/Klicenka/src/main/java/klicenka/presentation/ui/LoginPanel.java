package klicenka.presentation.ui;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * 
 * JPanel pro autorizace
 *
 */
public class LoginPanel extends JPanel {
	
	public JTextField userName;
	public JTextField userPassword;
	public JButton loginButton;
	public JButton createAccountButton;
	public JButton resetPasswordButton;
	
	public LoginPanel() {
		setLayout(null);
		
		userName = new JTextField();
		userName.setBounds(170, 78, 86, 20);
		userName.setColumns(10);
		add(userName);
		
		userPassword = new JTextField();
		userPassword.setBounds(170, 119, 86, 20);
		userPassword.setColumns(10);
		add(userPassword);
		
		loginButton = new JButton("Prihlasit se");
		loginButton.setBounds(146, 194, 141, 23);
		add(loginButton);
		
		createAccountButton = new JButton("Vytvorit si novy ucet");
		createAccountButton.setBounds(69, 228, 273, 23);
		add(createAccountButton);
		
		JLabel label = new JLabel("User Name");
		label.setBounds(87, 81, 91, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Heslo");
		label_1.setBounds(87, 122, 65, 14);
		add(label_1);
	}
}
