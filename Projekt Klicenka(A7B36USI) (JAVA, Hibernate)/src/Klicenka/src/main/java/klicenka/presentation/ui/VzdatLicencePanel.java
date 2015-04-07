package klicenka.presentation.ui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JList;

import klicenka.persistence.model.Licence;
/**
 * 
 * JPanel pro vzdání licence
 *
 */
public class VzdatLicencePanel extends JPanel {
	
	public JButton vzdatButton;
	public JList<Licence> licenceList;
	public JButton refreshButton;
	
	public VzdatLicencePanel() {
		setLayout(null);
		
		vzdatButton = new JButton("vzdat licence");
		vzdatButton.setBounds(22, 39, 140, 23);
		add(vzdatButton);
		
		licenceList = new JList<Licence>();
		licenceList.setBounds(212, 42, 217, 236);
		add(licenceList);
		
		refreshButton = new JButton("refresh list");
		refreshButton.setBounds(22, 255, 140, 23);
		add(refreshButton);
	}
}
