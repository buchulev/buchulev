package klicenka.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import klicenka.persistence.model.ZadostUser;
import klicenka.presentation.ui.ConfirmAccountPanel;
import klicenka.service.Impl.ZadostUserServiceImpl;
import klicenka.util.SessionContext;

import org.hibernate.Session;

/**
 * 
 * Kontroller pro schvaleni uzivatelu(pro Administratora)
 *
 */
public class ConfirmAccountPanelController {
	/**
	 * odkaz na mainController
	 * 
	 * @see MainController
	 */
	MainController mainController;
	/**
	 * odkaz na aktualni SessionContext
	 * 
	 * @see klicenka.util.SessionContext
	 */
	SessionContext sessionContext = SessionContext.getInstance();
	/**
	 * odkaz aktualni session
	 * 
	 * @see org.hibernate.session
	 */
	Session session = sessionContext.getSession();

	/**
	 * 
	 * @see klicenka.service.Impl.ZadostUserServiceImpl
	 */
	ZadostUserServiceImpl zadostUserService = new ZadostUserServiceImpl(session);
	/**
	 * Odkaz na přislušny view ConfirmAccountPanel
	 * 
	 * @see klicenka.presentation.ui.ConfirmAccountPanel
	 */
	ConfirmAccountPanel accountPanel;

	/**
	 * Konstruktor
	 * 
	 * @param accountPanel
	 * @param mainController
	 */
	public ConfirmAccountPanelController(ConfirmAccountPanel accountPanel,
			MainController mainController) {
		this.accountPanel = accountPanel;
		this.mainController = mainController;

	}

	/**
	 * Nastaveni zakladniho stavu : fieldu a listeneru
	 */
	public void start() {
		refresh();
		zadostBoxListener();
		confirmListener();
		refuseListener();
		refreshListener();
	}

	/**
	 * Nastavi listener pro saveButton
	 */
	public void confirmListener() {
		accountPanel.saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (accountPanel.userCombo.getSelectedItem() != null){
					zadostUserService
							.schvalit((ZadostUser) accountPanel.userCombo
									.getSelectedItem());
				refresh();
				}
				else {
					JOptionPane.showMessageDialog(accountPanel,
						    "Nic neni vybrano");
				}
			}

		});
	}

	/**
	 * Nastavi listener pro refuseButton
	 */
	public void refuseListener() {

		accountPanel.refuseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (accountPanel.userCombo.getSelectedItem() != null) {
					zadostUserService
							.odmitnout((ZadostUser) accountPanel.userCombo
									.getSelectedItem());
				} else
					JOptionPane.showMessageDialog(accountPanel,
							"Nic neni vybrano");
			}

		});
	}

	/**
	 * Nastavi listener pro buttonRefresh
	 */
	public void refreshListener() {
		accountPanel.buttonRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();

			}

		});
	}

	/**
	 * aktualizace udaju v přislušnem view'u
	 */
	public void refresh() {
		session.beginTransaction();
		HashSet<ZadostUser> zadosty = zadostUserService.zadostDAO
				.getUsersForAdmin();
		accountPanel.userCombo.setModel(new DefaultComboBoxModel<ZadostUser>());

		if (zadosty != null) {
			for (ZadostUser z : zadosty) {
				if (z.getUser().getOddeleni() == null)
					accountPanel.userCombo.addItem(z);

			}
		}

		accountPanel.email.setText("");
		accountPanel.username.setText("");
		accountPanel.name.setText("");
		accountPanel.password.setText("");
		accountPanel.radioAdmin.setSelected(false);
		accountPanel.radioNakupci.setSelected(false);
		accountPanel.komentar.setText("");

		session.getTransaction().commit();
	}

	/**
	 * Nastavi listener pro userCombo
	 */

	public void zadostBoxListener() {
		accountPanel.userCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (accountPanel.userCombo.getSelectedItem() != null) {

					ZadostUser z = (ZadostUser) accountPanel.userCombo
							.getSelectedItem();
					accountPanel.email.setText(z.getUser().getEmail());
					accountPanel.username.setText(z.getUser().getUserName());
					accountPanel.name.setText(z.getUser().getFirstName());
					accountPanel.password.setText(z.getUser().getPassword());
					accountPanel.radioAdmin.setSelected(z.getUser().getAdmin());
					accountPanel.radioNakupci.setSelected(z.getUser()
							.getNakupci());
					accountPanel.komentar.setText(z.getMassage());
				}

			}

		});
	}

}
