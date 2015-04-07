package klicenka.presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import klicenka.presentation.ui.LoginPanel;
import klicenka.service.Impl.UserServiceImpl;
import klicenka.util.SessionContext;
/**
 * 
 * Kontroller pro autorizace
 *
 */
public class LoginPanelController {
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
	 * @see klicenka.service.Impl.UserServiceImpl
	 */
	UserServiceImpl userService;
	/**
	 * Odkaz na přislušny view LoginPanel
	 * @see klicenka.presentation.ui.LoginPanel
	 */
	LoginPanel loginPanel;

	/**
	 * Konstruktor
	 * @param loginPanel
	 * @param mainController
	 */
	public LoginPanelController(LoginPanel loginPanel,
		MainController mainController) {
		sessionContext = SessionContext.getInstance();
		session = sessionContext.getSession();

		userService = new UserServiceImpl(this.session);

		this.loginPanel = loginPanel;
		this.mainController = mainController;
		createAccountListener();
		loginListener();

	}
	/**
	 * Nastavi listener pro loginButton
	 */
	public void loginListener() {
		loginPanel.loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				session.beginTransaction();

				if (userService.userDAOImpl.isValid(
						loginPanel.userName.getText(),
						loginPanel.userPassword.getText())) {
					sessionContext.setUser(userService.userDAOImpl.getAccaunt(
							loginPanel.userName.getText(),
							loginPanel.userPassword.getText()));
					mainController.postLogMenu();
				}

				else {
					JOptionPane.showMessageDialog(loginPanel,"Chybi spravne heslo nebo username, nebo user zatim nebyl schvalen");
				}

			}

		});
	}
	/**
	 * Nastavi listener pro createAccountButton
	 */
	public void createAccountListener() {

		loginPanel.createAccountButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				mainController.mainWindow.switchPanel("createAccountPanel");

			}

		});

	}

}
