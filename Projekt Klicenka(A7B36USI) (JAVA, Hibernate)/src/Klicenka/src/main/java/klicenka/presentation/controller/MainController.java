package klicenka.presentation.controller;

import org.hibernate.Session;

import klicenka.presentation.ui.MainWindow;
import klicenka.util.SessionContext;

public class MainController {
	/**
	 * Aktualni SessionContext
	 * 
	 * @see klicenka.util.SessionContext
	 */
	SessionContext sessionContext;
	/**
	 * Session
	 * 
	 * @see org.hibernate.Session
	 */
	Session session;

	/**
	 * Odkaz hlavni Jframe programmu see klicenka.presentation.ui.MainWindow
	 */
	public MainWindow mainWindow;
	
	
	/**
	 * @see klicenka.presentation.controller.AccountViewController
	 */
	AccountViewController accountViewController;
	/**
	 * @see klicenka.presentation.controller.ConfirmAccountPanelController
	 */
	ConfirmAccountPanelController confirmAccountPanelController;
	/**
	 * @see klicenka.presentation.controller.CreateAccountPanelController
	 */
	CreateAccountPanelController createAccountPanelController;
	/**
	 * @see klicenka.presentation.controller.CreateAccountByAdminController
	 */
	CreateAccountByAdminController createAccountByAdminController;
	/**
	 * @see klicenka.presentation.controller.DepartmentEditPanelController
	 */
	DepartmentEditPanelController departmentEditPanelController;
	/**
	 * @see klicenka.presentation.controller.DepartmentNamePanelController;
	 */
	DepartmentNamePanelController departmentNamePanelController;
	/**
	 * @see klicenka.presentation.controller.LoginPanelController
	 */
	LoginPanelController loginPanelController;
	/**
	 * @see klicenka.presentation.controller.VzdatLicencePanelController
	 */
	VzdatLicencePanelController vzdatLicencePanelController;
	/**
	 * @see klicenka.presentation.controller.PozadatLicencePanelController
	 */
	PozadatLicencePanelController pozadatLicencePanelController;
	/**
	 * @see klicenka.presentation.controller.ConfirmAccountByVedouciPanelController
	 */
	ConfirmAccountByVedouciPanelController confirmAccountByVedouciPanelController;
	/**
	 * @see klicenka.presentation.controller.ConfirmLicenceByVedouciPanelController
	 */
	ConfirmLicenceByVedouciPanelController confirmLicenceByVedouciPanelController;
	/**
	 * @see klicenka.presentation.controller.BuyLicencePanelController
	 */
	BuyLicencePanelController buyLicencePanelController;
/**
 * Konstruktor
 */
	public MainController() {
		sessionContext = SessionContext.getInstance();
		session = sessionContext.getSession();

		mainWindow = new MainWindow();

		accountViewController = new AccountViewController(
				mainWindow.accountView, this);
		confirmAccountPanelController = new ConfirmAccountPanelController(
				mainWindow.confirmAccountPanel, this);
		createAccountPanelController = new CreateAccountPanelController(
				mainWindow.createAccountPanel, this);
		departmentEditPanelController = new DepartmentEditPanelController(
				mainWindow.departmentEditPanel, this);
		departmentNamePanelController = new DepartmentNamePanelController(
				mainWindow.departmentNamePanel, this);
		loginPanelController = new LoginPanelController(mainWindow.loginPanel,
				this);
		vzdatLicencePanelController = new VzdatLicencePanelController(
				mainWindow.vzdatLicencePanel, this);
		pozadatLicencePanelController = new PozadatLicencePanelController(
				mainWindow.pozadatLicencePanel, this);
		createAccountByAdminController = new CreateAccountByAdminController(
				mainWindow.createAccountByAdminPanel, this);
		confirmAccountByVedouciPanelController = new ConfirmAccountByVedouciPanelController(
				mainWindow.confirmAccountByVedouciPanel, this);
		confirmLicenceByVedouciPanelController = new ConfirmLicenceByVedouciPanelController(
				mainWindow.confirmLicenceByVedouciPanel, this);
		buyLicencePanelController = new BuyLicencePanelController(
				mainWindow.buyLicencePanel, this);
	}


/**
 * Inicializuje složky menu po autorizovannou osobu 
 */
	public void postLogMenu() {
		
		accountViewController.start();
		vzdatLicencePanelController.start();
		pozadatLicencePanelController.start();

		mainWindow.initMenuBar();
		mainWindow.blankMenuPanel();
		mainWindow.initUserMenu();

		if (sessionContext.getUser().getAdmin()) {
			createAccountByAdminController.start();
			confirmAccountPanelController.start();
			departmentEditPanelController.start();
			mainWindow.initAdminMenu();
		}

		if (sessionContext.getUser().getNakupci()) {
			buyLicencePanelController.start();
			mainWindow.initNakupciMenu();
		}

		if (sessionContext.getUser().getVedoucioddeleni() != null) {
			confirmAccountByVedouciPanelController.start();
			departmentNamePanelController.start();
			mainWindow.initVedouciMenu();
			confirmLicenceByVedouciPanelController.start();
		}
	}

}
