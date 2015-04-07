package klicenka.service.Impl;

import org.hibernate.Session;






import klicenka.persistence.DAO.UserDAO;
import klicenka.persistence.DAO.Impl.UserDAOImpl;
import klicenka.service.UserService;
/**
 * 
 * Service pro prace s uzivatelem
 *
 */
public class UserServiceImpl implements UserService {
	/**
	 * Session
	 * @see org.hibernate.Session
	 */
	Session session;
	/**
	 * @see klicenka.persistence.DAO.Impl.OddeleniDAOImpl
	 */
	public UserDAO userDAOImpl;


	/**
	 * Konstruktor
	 * @param session
	 * @see org.hibernate.Session
	 */
	public UserServiceImpl(Session session){
		this.session = session;
		userDAOImpl = new UserDAOImpl(this.session);
		

	}
}
