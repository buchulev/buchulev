package klicenka.presentation.ui;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

import klicenka.persistence.model.Licence;

import klicenka.persistence.model.User;
/**
 * 
 * JPanel pro prace s oddelenim(pro vedouciho)
 *
 */
public class DepartmentNamePanel extends JPanel {

	public JList<User> freeUsers;
	public JList<User> departmentUsers;

	public JButton addButton;
	public JButton removeButton;

	public JButton buttonRefresh;
	public JButton renameButton;
	public JTextField name;

	public JList<Licence> licenceList;
	public JButton odebratButton;

	public DepartmentNamePanel() {
		setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Jmeno oddeleni k uprave");
		lblNewLabel_1.setBounds(10, 11, 166, 14);
		add(lblNewLabel_1);

		freeUsers = new JList<User>();
		freeUsers.setBounds(10, 143, 136, 194);
		add(freeUsers);

		departmentUsers = new JList<User>();
		departmentUsers.setBounds(264, 143, 136, 194);
		add(departmentUsers);

		addButton = new JButton("=>");
		addButton.setBounds(156, 178, 89, 23);
		add(addButton);

		removeButton = new JButton("<=");
		removeButton.setBounds(156, 249, 89, 23);
		add(removeButton);

		JLabel lblNewLabel_3 = new JLabel("Nezarazeni uzivatele");
		lblNewLabel_3.setBounds(27, 118, 109, 14);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Uzivatele v oddeleni");
		lblNewLabel_4.setBounds(266, 118, 123, 14);
		add(lblNewLabel_4);

		buttonRefresh = new JButton("obnovit");
		buttonRefresh.setBounds(285, 7, 89, 23);
		add(buttonRefresh);

		renameButton = new JButton("p\u0159ejmenovat");
		renameButton.setBounds(236, 41, 136, 23);
		add(renameButton);

		name = new JTextField();
		name.setBounds(10, 36, 136, 28);
		add(name);
		name.setColumns(10);

		licenceList = new JList<Licence>();
		licenceList.setBounds(10, 393, 430, 98);
		add(licenceList);

		odebratButton = new JButton("Odebrat licence");
		odebratButton.setBounds(10, 502, 176, 23);
		add(odebratButton);

		JLabel lblNewLabel = new JLabel("Licence uzivatelu");
		lblNewLabel.setBounds(10, 359, 149, 23);
		add(lblNewLabel);

	}
}
