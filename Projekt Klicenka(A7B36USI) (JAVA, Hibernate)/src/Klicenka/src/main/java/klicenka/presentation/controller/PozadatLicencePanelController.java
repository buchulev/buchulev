package klicenka.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import klicenka.persistence.model.Product;
import klicenka.presentation.ui.PozadatLicencePanel;
import klicenka.service.ZadostProductService;
import klicenka.service.Impl.LicenceProductServiceImpl;
import klicenka.service.Impl.ZadostProductServiceImpl;
import klicenka.util.SessionContext;

import org.hibernate.Session;
/**
 * 
 * Kontroller pro požadani o licence
 *
 */
public class PozadatLicencePanelController {
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
	 * @see klicenka.service.Impl.ZadostProductServiceImpl
	 */
	ZadostProductService zadostProductService;
	/**
	 * 
	 * @see klicenka.service.Impl.LicenceProductServiceImpl
	 */
	LicenceProductServiceImpl licenceProductService;

	/**
	 * Odkaz na přislušny view PozadatLicencePanel
	 * 
	 * @see klicenka.presentation.ui.PozadatLicencePanel
	 */
	PozadatLicencePanel pozadatLicencePanel;

	/**
	 * Konstruktor
	 * 
	 * @param pozadatLicencePanel
	 * @param mainController
	 */
	public PozadatLicencePanelController(
			PozadatLicencePanel pozadatLicencePanel,
			MainController mainController) {
		sessionContext = SessionContext.getInstance();
		session = sessionContext.getSession();

		licenceProductService = new LicenceProductServiceImpl(this.session);
		zadostProductService = new ZadostProductServiceImpl(this.session);

		this.pozadatLicencePanel = pozadatLicencePanel;
		this.mainController = mainController;

	}
	/**
	 * Nastaveni zakladniho stavu : fieldu a listeneru
	 */
	public void start() {
		searchListener();
		zadatListener();
	}
	/**
	 * Nastavi listener pro searchButton
	 */
	public void searchListener() {
		pozadatLicencePanel.searchButton
				.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						session.beginTransaction();
						HashSet<Product> products = new HashSet<Product>();
						if (pozadatLicencePanel.radioAll.isSelected()) {
							products = licenceProductService.productDAO
									.getProducts();
						}

						if (pozadatLicencePanel.radioProduct.isSelected()) {
							products = licenceProductService.productDAO
									.getProductsByName(pozadatLicencePanel.nazevProduktu
											.getText());
						}

						DefaultListModel<Product> lm = new DefaultListModel<Product>();

						if (products != null) {
							for (Product p : products) {
								lm.addElement(p);
							}
						}
						pozadatLicencePanel.licenceList.setModel(lm);

						pozadatLicencePanel.licenceList
								.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
						pozadatLicencePanel.licenceList
								.setLayoutOrientation(JList.HORIZONTAL_WRAP);
						pozadatLicencePanel.licenceList.setVisibleRowCount(-1);
						session.getTransaction().commit();
					}

				});
	}
	/**
	 * Nastavi listener pro zadatButton
	 */
	public void zadatListener() {
		pozadatLicencePanel.zadatButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (pozadatLicencePanel.licenceList.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(pozadatLicencePanel,
						    "Nic neni vybrano");
				}

				else {
					zadostProductService.createZadost(sessionContext.getUser(),
							pozadatLicencePanel.licenceList.getSelectedValue(),
							pozadatLicencePanel.komentar.getText());
				}

			}

		});
	}
}
