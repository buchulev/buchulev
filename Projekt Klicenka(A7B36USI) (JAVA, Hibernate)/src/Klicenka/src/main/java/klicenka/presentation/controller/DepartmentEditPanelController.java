package klicenka.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import klicenka.persistence.model.Oddeleni;
import klicenka.persistence.model.User;
import klicenka.presentation.ui.DepartmentEditPanel;
import klicenka.service.Impl.OddeleniServiceImpl;
import klicenka.service.Impl.UserServiceImpl;
import klicenka.util.SessionContext;

import org.hibernate.Session;

/**
 * 
 * Kontroller pro provadeni změn v oddeleni(pro administratora)
 *
 */
public class DepartmentEditPanelController {
	/**
	 * Odkaz na main controller
	 * 
	 * @see MainController
	 */
	MainController mainController;

	/**
	 * Aktualni SessionContext
	 * 
	 * @see klicenka.util.SessionContext
	 */
	SessionContext sessionContext = SessionContext.getInstance();
	/**
	 * Session
	 * 
	 * @see org.hibernate.Session
	 */
	Session session = sessionContext.getSession();

	/**
	 * 
	 * @see klicenka.service.Impl.UserServiceImpl
	 */
	UserServiceImpl userService = new UserServiceImpl(session);
	/**
	 * 
	 * @see klicenka.service.OdeleniService
	 */
	OddeleniServiceImpl oddeleniService = new OddeleniServiceImpl(session);

	/**
	 * Odkaz na přislušny view DepartmentEditPanel
	 * 
	 * @see klicenka.presentation.ui.DepartmentEditPanel
	 */
	DepartmentEditPanel departmentPanel;

	/**
	 * Konstruktor
	 * 
	 * @param departmentPanel
	 * @param mainController
	 */
	public DepartmentEditPanelController(DepartmentEditPanel departmentPanel,
			MainController mainController) {
		this.departmentPanel = departmentPanel;
		this.mainController = mainController;

	}

	/**
	 * Nastaveni zakladniho stavu : fieldu a listeneru
	 */
	public void start() {
		refresh();
		changeVedouciListener();
		renameListener();
		createListener();
		deleteListener();
		addUserListener();
		removeUserListener();
		refreshListener();
		departmentListener();
	}

	/**
	 * Nastavi listener pro departmentBox
	 */
	public void departmentListener() {

		departmentPanel.departmentBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Oddeleni o = (Oddeleni) departmentPanel.departmentBox
						.getSelectedItem();

				departmentPanel.aktualniVedouci.setText(o.getVedouci()
						.getFirstName() + " " + o.getVedouci().getLastName());
				HashSet<User> users = userService.userDAOImpl.getUsers();
				DefaultListModel<User> usersWithout = new DefaultListModel<User>();
				if (users != null) {
					for (User u : users) {
						if (u.getOddeleni() == null
								&& u.getVedoucioddeleni() == null
								&& u.getSchvaleny() && u.getAdmin() == false
								&& u.getNakupci() == false) {
							usersWithout.addElement(u);
						}
					}

					departmentPanel.freeUsers.setModel(usersWithout);

					departmentPanel.freeUsers
							.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					departmentPanel.freeUsers
							.setLayoutOrientation(JList.HORIZONTAL_WRAP);
					departmentPanel.freeUsers.setVisibleRowCount(-1);
				} else {
					DefaultListModel<User> usersWithout1 = new DefaultListModel<User>();
					departmentPanel.freeUsers.setModel(usersWithout1);

					departmentPanel.freeUsers
							.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					departmentPanel.freeUsers
							.setLayoutOrientation(JList.HORIZONTAL_WRAP);
					departmentPanel.freeUsers.setVisibleRowCount(-1);
				}

				DefaultListModel<User> usersDepartment = new DefaultListModel<User>();
				if (users != null) {
					for (User u : users) {
						if (u.getOddeleni() == (Oddeleni) departmentPanel.departmentBox
								.getSelectedItem() && u.getSchvaleny()) {
							usersDepartment.addElement(u);
						}
					}

					departmentPanel.departmentUsers.setModel(usersDepartment);

					departmentPanel.departmentUsers
							.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					departmentPanel.departmentUsers
							.setLayoutOrientation(JList.HORIZONTAL_WRAP);
					departmentPanel.departmentUsers.setVisibleRowCount(-1);
				} else {
					DefaultListModel<User> usersDepartment1 = new DefaultListModel<User>();
					departmentPanel.departmentUsers.setModel(usersDepartment1);

					departmentPanel.departmentUsers
							.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
					departmentPanel.departmentUsers
							.setLayoutOrientation(JList.HORIZONTAL_WRAP);
					departmentPanel.departmentUsers.setVisibleRowCount(-1);
				}

			}

		});
	}

	/**
	 * Nastavi listener pro changeVedouciButton
	 */
	public void changeVedouciListener() {
		departmentPanel.changeVedouciButton
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (departmentPanel.departmentBox.getSelectedItem() != null) {
							oddeleniService.setVedouci(
									(Oddeleni) departmentPanel.departmentBox
											.getSelectedItem(),
									(User) departmentPanel.vedouciBox
											.getSelectedItem());
							refresh();
						} else {
							JOptionPane.showMessageDialog(departmentPanel,
									"Oddeleni neni vybrano");
						}
					}

				});
	}

	/**
	 * Nastavi listener pro createButton
	 */
	public void createListener() {
		departmentPanel.createButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (departmentPanel.name.getText().length() > 5
						|| departmentPanel.vedouciBox.getSelectedItem() != null) {
					oddeleniService.createOddeleni(
							departmentPanel.name.getText(),
							(User) departmentPanel.vedouciBox.getSelectedItem());
					refresh();
				} else {
					JOptionPane
							.showMessageDialog(departmentPanel,
									"Nazev oddeleni měl by byt větši než 5 symbolu, a měli byste vybrat vedoucího");

				}
			}

		});

	}

	/**
	 * Nastavi listener pro deleteButton
	 */
	public void deleteListener() {
		departmentPanel.deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (departmentPanel.departmentBox.getSelectedItem() != null) {
					Oddeleni o = (Oddeleni) departmentPanel.departmentBox
							.getSelectedItem();

					oddeleniService.deleteDepartment(o);

					refresh();
				} else {
					JOptionPane.showMessageDialog(departmentPanel,
							"Nic neni vybrano");
				}
			}

		});
	}

	/**
	 * Nastavi listener pro addButton
	 */

	public void addUserListener() {

		departmentPanel.addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (departmentPanel.freeUsers.getSelectedValue() != null) {
					oddeleniService.conectUserWithDepartment(
							(Oddeleni) departmentPanel.departmentBox
									.getSelectedItem(),
							departmentPanel.freeUsers.getSelectedValue());
					refresh();
				} else {
					JOptionPane.showMessageDialog(departmentPanel,
							"Nic neni vybrano");
				}
			}

		});
	}

	/**
	 * Nastavi listener pro removeButton
	 */
	public void removeUserListener() {

		departmentPanel.removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (departmentPanel.departmentUsers.getSelectedValue() != null) {
					oddeleniService.removeUserFromDepartment(
							(Oddeleni) departmentPanel.departmentBox
									.getSelectedItem(),
							(User) departmentPanel.departmentUsers
									.getSelectedValue());
					refresh();
				} else {
					JOptionPane.showMessageDialog(departmentPanel,
							"Nic neni vybrano");
				}
			}

		});

	}

	/**
	 * aktualizace udaju v přislušnem view'u
	 */
	public void refresh() {

		if (oddeleniService.oddeleniDAOImpl.getOddeleni_pl() != null) {
			HashSet<Oddeleni> oddeleni = oddeleniService.oddeleniDAOImpl
					.getOddeleni_pl();

			departmentPanel.departmentBox
					.setModel(new DefaultComboBoxModel<Oddeleni>());

			if (oddeleni != null) {
				for (Oddeleni o : oddeleni) {
					departmentPanel.departmentBox.addItem(o);

				}
			}
		} else {
			departmentPanel.departmentBox
					.setModel(new DefaultComboBoxModel<Oddeleni>());
		}

		HashSet<User> users = userService.userDAOImpl.getUsers();

		departmentPanel.vedouciBox.setModel(new DefaultComboBoxModel<User>());

		if (users != null) {
			for (User u : users) {
				if (u.getVedoucioddeleni() == null && u.getSchvaleny() && u.getAdmin()==false && u.getNakupci()==false)
					departmentPanel.vedouciBox.addItem(u);

			}
		}

	}

	/**
	 * Nastavi listener pro buttonRefresh
	 */
	public void refreshListener() {
		departmentPanel.buttonRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}

		});
	}

	/**
	 * Nastavi listener pro renameButton
	 */
	public void renameListener() {
		departmentPanel.renameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (departmentPanel.departmentBox.getSelectedItem() != null) {
					if (departmentPanel.name.getText().length() > 5) {
						session.beginTransaction();

						Oddeleni o = (Oddeleni) departmentPanel.departmentBox
								.getSelectedItem();
						oddeleniService.oddeleniDAOImpl.setOddeleniRename(
								o.getJmenoOddeleni(),
								departmentPanel.name.getText());

						session.getTransaction().commit();
						refresh();
					} else {
						JOptionPane
								.showMessageDialog(departmentPanel,
										"Nazev oddeleni měl by byt větši než 5 symbolu");
					}

				} else {
					JOptionPane.showMessageDialog(departmentPanel,
							"Vyberte oddeleni");
				}
			}

		});

	}
}
