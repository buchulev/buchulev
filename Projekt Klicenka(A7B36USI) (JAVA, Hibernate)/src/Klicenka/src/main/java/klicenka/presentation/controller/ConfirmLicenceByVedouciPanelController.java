package klicenka.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;



import javax.swing.JOptionPane;

import klicenka.persistence.model.Licence;
import klicenka.persistence.model.ZadostProduct;
import klicenka.presentation.ui.ConfirmLicenceByVedouciPanel;
import klicenka.service.LicenceProductService;
import klicenka.service.ZadostNakupService;
import klicenka.service.Impl.LicenceProductServiceImpl;
import klicenka.service.Impl.ZadostNakupServiceImpl;
import klicenka.service.Impl.ZadostProductServiceImpl;
import klicenka.util.SessionContext;

import org.hibernate.Session;
/**
 * 
 * Kontroller pro schvaleni zadosty o pridani licenci. (pro vedouci)
 * 
 *
 */
public class ConfirmLicenceByVedouciPanelController {
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
	 * @see klicenka.service.ZadostProdctService 
	 */
    ZadostProductServiceImpl zadostProductService = new ZadostProductServiceImpl(session);
	/**
	 * 
	 * @see klicenka.service.LidostProductService 
	 */
    LicenceProductService licenceProductService = new LicenceProductServiceImpl(session);
	/**
	 * 
	 * @see klicenka.service.Impl.ZadostNakupServiceImpl 
	 */
    ZadostNakupService zadostNakupService = new ZadostNakupServiceImpl(session);
	/**
	 * Odkaz na přislušny view ConfirmLicenceByVedouciPanel
	 * @see klicenka.presentation.ui.ConfirmLicenceByVedouciPanel
	 */
	ConfirmLicenceByVedouciPanel confirmPanel;
	/**
	 * Konstruktor
	 * @param confirmPanel
	 * @param mainController
	 */
	public ConfirmLicenceByVedouciPanelController(ConfirmLicenceByVedouciPanel confirmPanel,MainController mainController) {
		this.confirmPanel = confirmPanel;
	this.mainController = mainController;
		
	}
	/**
	 * Nastaveni zakladniho stavu : fieldu a listeneru
	 */
	public void start() {
		refresh();
		
		confirmPanel.productName.setEditable(false);
		confirmPanel.username.setEditable(false);
		confirmPanel.komentar.setEditable(false);
		zadostBoxListener();
	    confirmListener();
		refuseListener();
		refreshListener();
	}
	
	

	/**
	 * Nastavi listener pro confirmButton
	 */
	public void confirmListener() {
		confirmPanel.confirmButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(confirmPanel.zadostCombo.getSelectedItem()!=null) {
				ZadostProduct z = (ZadostProduct) confirmPanel.zadostCombo.getSelectedItem();
				/*
				 * Zkusime najit licence podle požadavku
				 */
				
				Licence l = licenceProductService.getLicenceByZadost((ZadostProduct) confirmPanel.zadostCombo.getSelectedItem());
				if(l!=null) {
					/*
					 * Když ano tak pošleme přimo uzivatelovi
					 */
					licenceProductService.giveLicence(l, z.getUser(), z);
					}
				else {
					/*
					 * Když neni, pošleme žadost na nakup nakupčimu
					 */
					zadostNakupService.createZadost(z);
				}
			}
			else {
				JOptionPane.showMessageDialog(confirmPanel,
					    "Nic neni vybrano");
			}
			
			}
		});
	}
	/**
	 * Nastavi listener pro refuseButton
	 */
	public void refuseListener() {
	
		confirmPanel.refuseButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(confirmPanel.zadostCombo.getSelectedItem()!=null) {
				session.beginTransaction();
				zadostProductService.zadostProductDAO.odmitnout((ZadostProduct)confirmPanel.zadostCombo.getSelectedItem());
				session.getTransaction().commit();
				refresh();
				}
				else {
					JOptionPane.showMessageDialog(confirmPanel,
						    "Nic neni vybrano");
				}
			}
			
		});
	}
	/**
	 * Nastavi listener pro buttonRefresh
	 */
	public void refreshListener() {
		confirmPanel.buttonRefresh.addActionListener(new ActionListener(){

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
		 confirmPanel.zadostCombo.setModel(new DefaultComboBoxModel<ZadostProduct>());
	    HashSet<ZadostProduct> zadosty = zadostProductService.zadostProductDAO.getZadostyByOddeleni(sessionContext.getUser().getVedoucioddeleni(),sessionContext.getUser());

		if(zadosty!=null) {
		for(ZadostProduct z : zadosty) {

		   confirmPanel.zadostCombo.addItem(z);
		}
		}
		
		session.getTransaction().commit();

		
		confirmPanel.username.setText("");
        confirmPanel.productName.setText("");
		confirmPanel.komentar.setText("");
		
		
	}
	/**
	 * Nastavi listener pro zadostCombo
	 */
	public void zadostBoxListener() {
		confirmPanel.zadostCombo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(confirmPanel.zadostCombo.getSelectedItem()!=null) {
					
					ZadostProduct z = (ZadostProduct) confirmPanel.zadostCombo.getSelectedItem();

					confirmPanel.username.setText(z.getUser().getUserName());
                    confirmPanel.productName.setText(z.getProduct().getName());
					confirmPanel.komentar.setText(z.getMessage());
					
				}
				
				
			}
			
		});
	}
	
}
