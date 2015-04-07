package klicenka.presentation.ui;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

import klicenka.persistence.model.Oddeleni;
import klicenka.persistence.model.User;

/**
 * 
 * JPanel pro prace s oddelenim (pro administratora)
 *
 */
public class DepartmentEditPanel extends JPanel {

	public JComboBox<Oddeleni> departmentBox;
	public JComboBox<User> vedouciBox;

	public JList<User> freeUsers;
	public JList <User> departmentUsers;

	public JTextField name;

	
	public JButton addButton;
	public JButton removeButton;
	public JButton deleteButton;
    
	
	public JButton changeVedouciButton;
	public JButton createButton;
	public JButton buttonRefresh;
	public JButton renameButton;
	public JTextField aktualniVedouci;
	
	public DepartmentEditPanel() {
		setLayout(null);

		departmentBox = new JComboBox<Oddeleni>();
		departmentBox.setBounds(10, 11, 166, 20);
		add(departmentBox);

		JLabel lblNewLabel = new JLabel("Oddeleni");
		lblNewLabel.setBounds(186, 14, 46, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Jmeno oddeleni k uprave");
		lblNewLabel_1.setBounds(10, 61, 166, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nov\u00FD vedouci");
		lblNewLabel_2.setBounds(166, 61, 82, 14);
		add(lblNewLabel_2);

		vedouciBox = new JComboBox<User>();
		vedouciBox.setBounds(159, 86, 136, 20);
		add(vedouciBox);

		name = new JTextField();
		name.setBounds(10, 86, 136, 20);
		add(name);
		name.setColumns(10);

		changeVedouciButton = new JButton("zm\u011Bn\u00EDt vedouc\u00EDho");
		changeVedouciButton.setBounds(326, 108, 136, 23);
		add(changeVedouciButton);

		freeUsers = new JList<User>();
		freeUsers.setBounds(10, 272, 136, 194);
		add(freeUsers);

		departmentUsers = new JList<User>();
		departmentUsers.setBounds(267, 272, 136, 194);
		add(departmentUsers);

		addButton = new JButton("=>");
		addButton.setBounds(166, 304, 89, 23);
		add(addButton);

		removeButton = new JButton("<=");
		removeButton.setBounds(166, 404, 89, 23);
		add(removeButton);

		JLabel lblNewLabel_3 = new JLabel("Nezarazeni uzivatele");
		lblNewLabel_3.setBounds(25, 247, 109, 14);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Uzivatele v oddeleni");
		lblNewLabel_4.setBounds(280, 247, 123, 14);
		add(lblNewLabel_4);

		deleteButton = new JButton("smazat");
		deleteButton.setBounds(373, 84, 89, 23);
		add(deleteButton);

		createButton = new JButton("vytvorit");
		createButton.setBounds(373, 57, 89, 23);
		add(createButton);
		
		buttonRefresh = new JButton("obnovit");
		buttonRefresh.setBounds(373, 10, 89, 23);
		add(buttonRefresh);
		
		renameButton = new JButton("p\u0159ejmenovat");
		renameButton.setBounds(326, 135, 136, 23);
		add(renameButton);
		
		aktualniVedouci = new JTextField();
		aktualniVedouci.setBounds(159, 135, 136, 23);
		add(aktualniVedouci);
		aktualniVedouci.setColumns(10);
		aktualniVedouci.setEditable(false);
		
		JLabel lblNewLabel_5 = new JLabel("aktualni vedouci");
		lblNewLabel_5.setBounds(166, 117, 109, 14);
		add(lblNewLabel_5);
	}
}
