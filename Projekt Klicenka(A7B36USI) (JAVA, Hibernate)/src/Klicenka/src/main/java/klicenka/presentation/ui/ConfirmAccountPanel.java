package klicenka.presentation.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import klicenka.persistence.model.ZadostUser;
import javax.swing.JTextArea;
/**
 * 
 * JPanel pro schvaleni uzivatele v systemu(pro administratora)
 *
 */
public class ConfirmAccountPanel  extends JPanel{
	
	public JButton saveButton;
	public JButton refuseButton;
	
	public JLabel name;
	public JLabel surname;
	public JLabel username;
	public JLabel email;
	public JLabel password;
	
	public JRadioButton radioAdmin;
	public JRadioButton radioNakupci;
	
	public JComboBox<ZadostUser> userCombo;

	public JButton buttonRefresh;
	public JTextArea komentar;
	
	public ConfirmAccountPanel() {
		setLayout(null);
		
		JLabel label = new JLabel("Jmeno ");
		label.setBounds(26, 48, 101, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Prijmeni");
		label_1.setBounds(26, 77, 101, 14);
		add(label_1);
		
		 radioAdmin = new JRadioButton("Je administrator");
		 radioAdmin.setEnabled(false);
		radioAdmin.setBounds(295, 48, 149, 23);
		add(radioAdmin);
		
		
		radioNakupci = new JRadioButton("Je Nakupcim");
		radioNakupci.setBounds(295, 87, 149, 23);
		radioNakupci.setEnabled(false);
		add(radioNakupci);
		
		JLabel label_2 = new JLabel("User Name");
		label_2.setBounds(26, 102, 101, 14);
		add(label_2);
		
		JLabel label_3 = new JLabel("Email");
		label_3.setBounds(26, 127, 101, 14);
		add(label_3);
		
		JLabel lblHeslo = new JLabel("heslo");
		lblHeslo.setBounds(26, 152, 90, 14);
		add(lblHeslo);
		
		saveButton = new JButton("Ulozit");
		saveButton.setBounds(311, 241, 89, 23);
		add(saveButton);
		
		refuseButton = new JButton("Odmitnout");
		refuseButton.setBounds(300, 188, 114, 23);
		add(refuseButton);
		
		name = new JLabel("");
		name.setBounds(126, 48, 128, 14);
		add(name);
		
		surname = new JLabel("");
		surname.setBounds(126, 77, 128, 14);
		add(surname);
		
		username = new JLabel("");
		username.setBounds(126, 102, 128, 14);
		add(username);
		
		email = new JLabel("");
		email.setBounds(126, 127, 128, 14);
		add(email);
		
		userCombo = new JComboBox<ZadostUser>();
		userCombo.setBounds(26, 11, 172, 20);
		add(userCombo);
		
		password = new JLabel("");
		password.setBounds(126, 152, 128, 14);
		add(password);
		
		
		
		buttonRefresh = new JButton("refresh");
		buttonRefresh.setBounds(295, 10, 89, 23);
		add(buttonRefresh);
		
		komentar = new JTextArea();
		komentar.setEditable(false);
		komentar.setBounds(26, 192, 264, 72);
		add(komentar);
	}
}
