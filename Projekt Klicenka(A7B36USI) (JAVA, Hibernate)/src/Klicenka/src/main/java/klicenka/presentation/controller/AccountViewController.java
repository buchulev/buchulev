package klicenka.presentation.controller;
import klicenka.persistence.model.User;
import klicenka.presentation.ui.AccountView;
import klicenka.util.SessionContext;

import org.hibernate.Session;

/**
 * 
 * Reprezentuje controller pro AccountView
 * @see AccountView
 *
 */
public class AccountViewController {
	
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
	 * Odkaz na prislusni  accountView
	 * @see AccountView
	 */
	AccountView accountView;
	
	/**
	 * Konstruktor
	 * @param accountView
	 * @param mainController
	 */
	public AccountViewController(AccountView accountView,MainController mainController) {
		this.accountView = accountView;
		this.mainController = mainController;
		
	
	}
	
	/**
	 * Nastavuje uzivatelske udaje do AccountView
	 * @see AccountView
	 */
	public void start() {
		User user = sessionContext.getUser();
		
	    this.accountView.name.setText(user.getFirstName());	
	    this.accountView.surname.setText(user.getLastName());	
	    this.accountView.email.setText(user.getEmail());	
	    this.accountView.username.setText(user.getUserName());	
	}
	

	

	
}
