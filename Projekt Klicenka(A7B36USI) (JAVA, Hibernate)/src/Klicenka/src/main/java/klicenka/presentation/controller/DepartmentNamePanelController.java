package klicenka.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import klicenka.persistence.model.Licence;
import klicenka.persistence.model.Oddeleni;
import klicenka.persistence.model.User;
import klicenka.presentation.ui.DepartmentNamePanel;
import klicenka.service.LicenceProductService;
import klicenka.service.Impl.LicenceProductServiceImpl;
import klicenka.service.Impl.OddeleniServiceImpl;
import klicenka.service.Impl.UserServiceImpl;
import klicenka.util.SessionContext;

import org.hibernate.Session;

/**
 * 
 * Kontroller pro provadeni změn v  oddeleni(pro vedouciho)
 *
 */
public class DepartmentNamePanelController {
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

	LicenceProductService licenceService = new LicenceProductServiceImpl(session);
	/**
	 * Odkaz na přislušny view BuyLicencePanel
	 * 
	 * @see klicenka.presentation.ui.BuyLicencePanel
	 */
	DepartmentNamePanel departmentPanel;

	/**
	 * Konstruktor
	 * 
	 * @param departmentPanel
	 * @param mainController
	 */
	public DepartmentNamePanelController(DepartmentNamePanel departmentPanel,
			MainController mainController) {
		this.departmentPanel = departmentPanel;
		this.mainController = mainController;

	}

	/**
	 * Nastaveni zakladniho stavu : fieldu a listeneru
	 */
	public void start() {
		refresh();
		renameListener();
		addUserListener();
		removeUserListener();
		refreshListener();
		userListener();
		removeListener();
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
							(Oddeleni) sessionContext.getUser()
									.getVedoucioddeleni(),
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
							(Oddeleni) sessionContext.getUser()
									.getVedoucioddeleni(),
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

	

		HashSet<User> users = userService.userDAOImpl.getUsers();

		DefaultListModel<User> usersWithout = new DefaultListModel<User>();
		if (users != null) {
			for (User u : users) {
				if (u.getOddeleni() == null && u.getVedoucioddeleni() == null
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
				if (u.getOddeleni() == (Oddeleni) sessionContext.getUser()
						.getVedoucioddeleni() && u.getSchvaleny()) {
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

				session.beginTransaction();

				Oddeleni o = (Oddeleni) sessionContext.getUser()
						.getVedoucioddeleni();
				oddeleniService.oddeleniDAOImpl.setOddeleniRename(
						o.getJmenoOddeleni(), departmentPanel.name.getText());

				session.getTransaction().commit();
				refresh();

			}

		});

	}

	/**
	 * Nastavi listener pro departmentUsers
	 */
	public void userListener() {
		departmentPanel.departmentUsers
				.addListSelectionListener(new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent arg0) {
						if (departmentPanel.departmentUsers.getSelectedValue() != null) {

							DefaultListModel<Licence> userLicences = new DefaultListModel<Licence>();
							HashSet<Licence> licences = licenceService
									.getUserLicences(departmentPanel.departmentUsers
											.getSelectedValue());
							if (licences != null) {
								for (Licence l : licences) {

									userLicences.addElement(l);

								}

								departmentPanel.licenceList
										.setModel(userLicences);

								departmentPanel.licenceList
										.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
								departmentPanel.licenceList
										.setLayoutOrientation(JList.HORIZONTAL_WRAP);
								departmentPanel.licenceList
										.setVisibleRowCount(-1);
							} else {

								departmentPanel.licenceList
										.setModel(new DefaultListModel<Licence>());

								departmentPanel.licenceList
										.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
								departmentPanel.licenceList
										.setLayoutOrientation(JList.HORIZONTAL_WRAP);
								departmentPanel.licenceList
										.setVisibleRowCount(-1);
							}

						}
					}

				});
	}

	/**
	 * Nastavi listener pro odebratButton
	 */
	public void removeListener() {
		departmentPanel.odebratButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (departmentPanel.licenceList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(departmentPanel,
							"Nic neni vybrano");
				} else {
					licenceService.vzdatUsersLicence(
							(User) departmentPanel.departmentUsers
									.getSelectedValue(),
							(Licence) departmentPanel.licenceList
									.getSelectedValue());
				}

			}

		});
	}
}
