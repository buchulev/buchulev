package klicenka.presentation.ui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import klicenka.persistence.model.ZadostProduct;
import javax.swing.JTextField;

/**
 * 
 * JPanel pro schvaleni licencepPro vedouci)
 *
 */
public class ConfirmLicenceByVedouciPanel extends JPanel {
	public JButton confirmButton;
	public JButton refuseButton;
	


	public JButton buttonRefresh;
	public JTextArea komentar;
	
	public JComboBox<ZadostProduct> zadostCombo;
	public JTextField username;
	public JTextField productName;




	
	public ConfirmLicenceByVedouciPanel() {
		setLayout(null);

		JLabel label_2 = new JLabel("User Name");
		label_2.setBounds(26, 64, 101, 14);
		add(label_2);
		

		
		confirmButton = new JButton("Schvalit");
		confirmButton.setBounds(311, 241, 89, 23);
		add(confirmButton );
		
		refuseButton = new JButton("Odmitnout");
		refuseButton.setBounds(300, 188, 114, 23);
		add(refuseButton);
			

	
		zadostCombo = new JComboBox<ZadostProduct>();
		zadostCombo.setBounds(26, 11, 172, 20);
		add(zadostCombo);

		buttonRefresh = new JButton("refresh");
		buttonRefresh.setBounds(295, 10, 89, 23);
		add(buttonRefresh);
		
		komentar = new JTextArea();
		komentar.setEditable(false);
		komentar.setBounds(26, 192, 264, 72);
		add(komentar);
		
		username = new JTextField();
		username.setBounds(112, 61, 128, 20);
		add(username);
		username.setColumns(10);
		

		
		JLabel lblNewLabel = new JLabel("Produkt");
		lblNewLabel.setBounds(26, 102, 64, 14);
		add(lblNewLabel);
		
		productName = new JTextField();
		productName.setBounds(112, 96, 128, 20);
		add(productName);
		productName.setColumns(10);
	}
}
