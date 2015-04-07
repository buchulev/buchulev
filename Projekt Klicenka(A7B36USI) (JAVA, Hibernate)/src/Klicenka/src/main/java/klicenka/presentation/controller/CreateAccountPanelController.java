package klicenka.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import klicenka.persistence.model.Oddeleni;
import klicenka.persistence.model.User;
import klicenka.presentation.ui.CreateAccountPanel;
import klicenka.service.ZadostUserService;
import klicenka.service.Impl.OddeleniServiceImpl;
import klicenka.service.Impl.UserServiceImpl;
import klicenka.service.Impl.ZadostUserServiceImpl;
import klicenka.util.SessionContext;

import org.hibernate.Session;

/**
 * 
 * Kontroller pro vytvařeni uzivatele
 *
 */
public class CreateAccountPanelController {
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
	 * @see klicenka.service.Impl.UserServiceImpl
	 */
	UserServiceImpl userService = new UserServiceImpl(session);
	/**
	 * @see klicenka.service.Impl.OddeleniServiceImpl
	 */
	OddeleniServiceImpl oddeleniService = new OddeleniServiceImpl(session);
	/**
	 * @see klicenka.service.Impl.ZadostUserServiceImpl
	 */
	ZadostUserService zadostUserService = new ZadostUserServiceImpl(session);
	/**
	 * Odkaz na přislušny view CreateAccountPanel
	 * 
	 * @see klicenka.presentation.ui.CreateAccountPanel
	 */
	CreateAccountPanel accountPanel;

	/**
	 * Konstruktor
	 * 
	 * @param accountPanel
	 * @param mainController
	 */
	public CreateAccountPanelController(CreateAccountPanel accountPanel,
			MainController mainController) {
		this.accountPanel = accountPanel;
		this.mainController = mainController;
		refresh();
		sendListener();

		backListener();

	}

	/**
	 * Nastavi listener pro sendButton
	 */
	public void sendListener() {

		accountPanel.sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * Persistance bad
				 */
				session.beginTransaction();
				if (accountPanel.userPassword1.getText().equals(
						accountPanel.userPassword2.getText())) {
					User user = userService.userDAOImpl.add(
							accountPanel.userName.getText(),
							accountPanel.userSurname.getText(),
							accountPanel.userLoginName.getText(),
							accountPanel.userPassword1.getText(),
							accountPanel.userEmail.getText(),
							accountPanel.radioNakupciButton.isSelected(),
							accountPanel.radioAdminButton.isSelected(), false);

					if (user == null) {
						JOptionPane.showMessageDialog(accountPanel,
								"Zkontrolujte udaje");
						return;
						
					}
					if (accountPanel.radioOddeleniButton.isSelected()) {
						Oddeleni o = (Oddeleni) accountPanel.oddeleniSelect
								.getSelectedItem();
						user.setOddeleni(o);
						o.getZamestnanciOddeleni().add(user);

						session.saveOrUpdate(user);
						session.saveOrUpdate(o);
					} else {

						session.saveOrUpdate(user);

					}

					session.getTransaction().commit();

					if (zadostUserService.addUserByZadost(user,
							accountPanel.comment.getText())) {
						mainController.mainWindow.switchPanel("loginPanel");
					}

				}

				else {
					JOptionPane.showMessageDialog(accountPanel,
							"Hesla nejsou stejna");
				}
			}
		});

	}

	/**
	 * Nastavi listener pro backButton
	 */
	public void backListener() {

		accountPanel.backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainController.mainWindow.switchPanel("loginPanel");

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

			accountPanel.oddeleniSelect
					.setModel(new DefaultComboBoxModel<Oddeleni>());

			if (oddeleni != null) {
				for (Oddeleni o : oddeleni) {
					accountPanel.oddeleniSelect.addItem(o);

				}
			}
		}

	}
}
