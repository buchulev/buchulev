package klicenka.presentation.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * 
 * Jpanel pro zobrazeni uzivatelských udaje
 *
 */
public class AccountView extends JPanel{
	
	public JLabel name;
	public JLabel surname;
	public JLabel email;
	public JLabel username;
	
	public AccountView() {
		setLayout(null);

		JLabel label = new JLabel("Jmeno");
		label.setBounds(24, 40, 76, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Prijmeni");
		label_1.setBounds(24, 75, 76, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("Email");
		label_2.setBounds(24, 112, 76, 14);
		add(label_2);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(24, 149, 76, 14);
		add(lblUsername);
		
		name = new JLabel("New label");
		name.setBounds(132, 40, 106, 14);
		add(name);
		
	 surname = new JLabel("New label");
		surname.setBounds(132, 75, 116, 14);
		add(surname);
		
		 email = new JLabel("New label");
		email.setBounds(132, 112, 106, 14);
		add(email);
		
		 username = new JLabel("New label");
		username.setBounds(132, 149, 89, 14);
		add(username);
		

	}
}
