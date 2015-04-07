package klicenka.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import klicenka.persistence.model.Licence;
import klicenka.presentation.ui.VzdatLicencePanel;
import klicenka.service.LicenceProductService;
import klicenka.service.Impl.LicenceProductServiceImpl;
import klicenka.util.SessionContext;

import org.hibernate.Session;
/**
 * 
 * Kontroller pro vzdaní licence
 * 
 *
 */
public class VzdatLicencePanelController {
	/**
	 * Odkaz na main controller
	 * @see MainController
	 */
	MainController mainController;
	
	/**
	 * Aktualni SessionContext
	 * @see klicenka.util.SessionContext 
	 */
	SessionContext sessionContext = SessionContext.getInstance();
	/**
	 * Session
	 * @see org.hibernate.Session
	 */
	Session session = sessionContext.getSession();
/**
 * @see  klicenka.service.Impl.LicenceProductServiceImpl
 */
	LicenceProductService licenceService = new LicenceProductServiceImpl(session);
	/**
	 * Odkaz na přislušny view BuyLicencePanel
	 * @see klicenka.presentation.ui.VzdatLicencePanel
	 */
	VzdatLicencePanel vzdatLicencePanel;
/**
 * Konstruktor
 * @param vzdatLicencePanel
 * @param mainController
 */
	public VzdatLicencePanelController(VzdatLicencePanel vzdatLicencePanel,
			MainController mainController) {
		this.vzdatLicencePanel = vzdatLicencePanel;
		this.mainController = mainController;

	}
	/**
	 * Nastaveni zakladniho stavu : fieldu a listeneru
	 */
	public void start() {
		refreshListener();
		vzdatListener();
	}
	/**
	 * Nastavi listener pro refreshButton
	 */
	public void refreshListener() {
		vzdatLicencePanel.refreshButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vzdatLicencePanel.licenceList.setModel(new DefaultListModel<Licence>());
				HashSet<Licence> licences = licenceService
						.getUserLicences(sessionContext.getUser());
				DefaultListModel<Licence> lm = new DefaultListModel<Licence>();
				if (licences != null) {
					for (Licence l : licences) {
						lm.addElement(l);
					}
				}
				vzdatLicencePanel.licenceList.setModel(lm);

				vzdatLicencePanel.licenceList
						.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
				vzdatLicencePanel.licenceList
						.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				vzdatLicencePanel.licenceList.setVisibleRowCount(-1);
			}

		});
	}
	/**
	 * Nastavi listener pro vzdatButton
	 */
	public void vzdatListener() {
		vzdatLicencePanel.vzdatButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (vzdatLicencePanel.licenceList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(vzdatLicencePanel,
						    "Nic neni vybrano");
				}
				 else {
					licenceService.vzdatUsersLicence(sessionContext.getUser(),
							vzdatLicencePanel.licenceList.getSelectedValue());
				}

			}

		});
	}

}
