package klicenka.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import klicenka.persistence.DAO.Impl.ZadostUserDAOImpl;
import klicenka.persistence.model.ZadostNakup;
import klicenka.persistence.model.ZadostUser;
import klicenka.presentation.ui.BuyLicencePanel;
import klicenka.presentation.ui.ConfirmAccountPanel;
import klicenka.service.Impl.OddeleniServiceImpl;
import klicenka.service.Impl.UserServiceImpl;
import klicenka.service.Impl.ZadostNakupServiceImpl;
import klicenka.service.Impl.ZadostUserServiceImpl;
import klicenka.util.SessionContext;

import org.hibernate.Session;
/**
 * 
 * Kontroller pro nakup novych licenci
 * 
 *
 */
public class BuyLicencePanelController {
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
	 * 
	 * @see klicenka.service.Impl.ZadostNakupServiceImpl 
	 */
	ZadostNakupServiceImpl zadostNakupService = new ZadostNakupServiceImpl(session);
    
	/**
	 * Odkaz na přislušny view BuyLicencePanel
	 * @see klicenka.presentation.ui.BuyLicencePanel
	 */
	BuyLicencePanel licencePanel;
/**
 * Konstruktor
 * @param licencePanel
 * @param mainController
 */
	public BuyLicencePanelController(BuyLicencePanel licencePanel,
			MainController mainController) {
		this.licencePanel = licencePanel;
		this.mainController = mainController;

	}
/**
 * Nastaveni zakladniho stavu : fieldu a listeneru
 */
	public void start() {

		licencePanel.komentar.setEditable(false);
		licencePanel.name.setEditable(false);
		licencePanel.username.setEditable(false);

		refresh();
		refreshListener();
		zadostBoxListener();
		confirmListener();
		refuseListener();
	}

	/**
	 * Nastavi listener pro buyButton
	 */
	public void confirmListener() {
		licencePanel.buyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (licencePanel.zadostCombo.getSelectedItem() != null) {
					ZadostNakup z = (ZadostNakup) licencePanel.zadostCombo
							.getSelectedItem();

					zadostNakupService.buyLicenceAndAdd(z, licencePanel.code
							.getText(), Integer
							.parseInt(licencePanel.licenceLength.getText()),
							licencePanel.isCorporateCheck.isSelected());
					refresh();
				}
				
				else {
					JOptionPane.showMessageDialog(licencePanel,
						    "Nic neni vybrano");
				}

			}

		});
	}

	
/**
 * Nastavi listener pro buttonRefresh
 */
	public void refreshListener() {
		licencePanel.buttonRefresh.addActionListener(new ActionListener() {

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
/*
 * akutalizace seznamu s žadostmii
 */
		HashSet<ZadostNakup> zadosty = zadostNakupService.zadostNakupDAOImpl
				.getZadostNakup();
		licencePanel.zadostCombo
				.setModel(new DefaultComboBoxModel<ZadostNakup>());

		if (zadosty != null) {
			for (ZadostNakup z : zadosty) {

				licencePanel.zadostCombo.addItem(z);

			}
		}
		
		licencePanel.code.setText("");
		licencePanel.isCorporateCheck.setSelected(false);
		licencePanel.komentar.setText("");
		licencePanel.licenceLength.setText("");
		licencePanel.name.setText("");
		licencePanel.username.setText("");

		session.getTransaction().commit();
	}
/**
 * Nastavi listener na JComboBox z zadostmi na nakup
 */
	public void zadostBoxListener() {
		licencePanel.zadostCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (licencePanel.zadostCombo.getSelectedItem() != null) {

					ZadostNakup z = (ZadostNakup) licencePanel.zadostCombo
							.getSelectedItem();

					licencePanel.komentar.setText(z.getMessage());
					licencePanel.name.setText(z.getProduct().getName());
					licencePanel.username.setText(z.getUser().getUserName());

				}

			}

		});
	}
	
	public void refuseListener() {
	   licencePanel.refuseButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			if(licencePanel.zadostCombo.getSelectedItem()!=null) {
				zadostNakupService.odmitnout((ZadostNakup)licencePanel.zadostCombo.getSelectedItem());
				
			}
			else {
				JOptionPane.showMessageDialog(licencePanel,
					    "Nic neni vybrano");
			}
			
		}
		   
	   });
	}

}
