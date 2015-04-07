package klicenka.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import klicenka.persistence.model.Oddeleni;
import klicenka.persistence.model.User;
import klicenka.presentation.ui.CreateAccountByAdminPanel;
import klicenka.service.OddeleniService;
import klicenka.service.Impl.OddeleniServiceImpl;
import klicenka.service.Impl.UserServiceImpl;
import klicenka.util.SessionContext;

import org.hibernate.Session;

/**
 * 
 * Kontroller pro vytvařeni uzivatele(pro administratora)
 *
 */
public class CreateAccountByAdminController {
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
 * @see klicenka.service.Impl.UserServiceImpl
 */
	UserServiceImpl userService = new UserServiceImpl(session);
	/**
	 * @see klicenka.service.Impl.OddeleniServiceImpl
	 */
	OddeleniService oddeleniService = new OddeleniServiceImpl(session);

	/**
	 * Odkaz na přislušny view ConfirmLicenceByVedouciPanel
	 * @see klicenka.presentation.ui.CreateAccountByAdminPanel
	 */
	CreateAccountByAdminPanel accountPanel;
/**
 * Konstruktor
 * @param accountPanel
 * @param mainController
 */
	public CreateAccountByAdminController(CreateAccountByAdminPanel accountPanel,MainController mainController) {
		this.accountPanel = accountPanel;
		this.mainController = mainController;
		
		
	}
	/**
	 * Nastaveni zakladniho stavu : fieldu a listeneru
	 */
public void start() {
	sendListener();
}
/**
 * Nastavi listener pro sendButton
 */
	public void sendListener() {

		accountPanel.sendButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
            session.beginTransaction();
				if (accountPanel.userPassword1.getText().equals(
						accountPanel.userPassword2.getText())) {
					User user = userService.userDAOImpl.add(accountPanel.userName.getText(),
							accountPanel.userSurname.getText(),
							accountPanel.userLoginName.getText(),
							accountPanel.userPassword1.getText(),
							accountPanel.userEmail.getText(),
							accountPanel.radioNakupciButton.isSelected(),
							accountPanel.radioAdminButton.isSelected(),
							true);
					if(user==null){
						JOptionPane.showMessageDialog(accountPanel,
							    "Zkontrolujte udaje");
						return;
					}
					
					if(accountPanel.radioOddeleniButton.isSelected()) {
						Oddeleni o = (Oddeleni) accountPanel.oddeleniSelect.getSelectedItem();
						user.setOddeleni(o);
						o.getZamestnanciOddeleni().add(user);
	                    
						session.saveOrUpdate(user);
						session.saveOrUpdate(o);	
					}
					else {

	                    
						session.saveOrUpdate(user);

					}
			
					
					accountPanel.userSurname.setText("");
					accountPanel.userLoginName.setText("");
					accountPanel.userPassword1.setText("");
					accountPanel.userEmail.setText("");
					accountPanel.radioAdminButton.setSelected(false);
					accountPanel.radioNakupciButton.setSelected(false);
					
					session.getTransaction().commit();
					mainController.mainWindow.switchPanel("blankPanel");
			
				}

				else {
				
						JOptionPane.showMessageDialog(accountPanel,
							    "Hesla nejsou stejna");
					
				}
			}
		});

	}

}
