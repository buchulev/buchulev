package klicenka.service.Impl;

import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;

import klicenka.persistence.DAO.UserDAO;
import klicenka.persistence.DAO.ZadostUserDAO;
import klicenka.persistence.DAO.Impl.UserDAOImpl;
import klicenka.persistence.DAO.Impl.ZadostUserDAOImpl;
import klicenka.persistence.model.User;
import klicenka.persistence.model.ZadostUser;
import klicenka.service.ZadostUserService;

/**
 * 
 * Service pro žadosti na produkt
 *
 */
public class ZadostUserServiceImpl implements ZadostUserService {
	/**
	 * aktualni session
	 * 
	 * @see org.hibernate.Session
	 */
	public Session session;
	/**
	 * @see klicenka.persistence.DAO.Impl.ZadostUserDAOImpl
	 */
	public ZadostUserDAO zadostDAO;
	/**
	 * @see klicenka.persistence.DAO.Impl.UserDAOImpl
	 */
	public UserDAO userDAO;

	/**
	 * Konstruktor
	 * 
	 * @param session
	 * @see org.hibernate.Session
	 */
	public ZadostUserServiceImpl(Session session) {
		this.session = session;
		zadostDAO = new ZadostUserDAOImpl(this.session);
		userDAO = new UserDAOImpl(this.session);
	}

	/* (non-Javadoc)
	 * @see klicenka.service.Impl.ZadostUserService#getZadostUser(java.lang.String)
	 */
	@Override
	public ZadostUser getZadostUser(String username) {
		session.beginTransaction();
		if (userDAO.getUserByUsername(username) != null) {

			int id = userDAO.getUserByUsername(username).getUserId();
			Query q = session
					.createQuery("FROM ZadostUser where user_id=:user_id");
			q.setParameter("user_id", id);
			session.getTransaction().commit();
			return (ZadostUser) q.list().get(0);

		} else {
			session.getTransaction().commit();
			Logger.getLogger("Log from ZadostUserService - getZadostUser")
					.warning("Uzivatel s takovym to usernamem neexistuje");
			return null;

		}

	}

	/* (non-Javadoc)
	 * @see klicenka.service.Impl.ZadostUserService#schvalit(klicenka.persistence.model.ZadostUser)
	 */
	@Override
	public boolean schvalit(ZadostUser z) {
		session.beginTransaction();
		User u = z.getUser();
		if (u.getSchvaleny() == true) {
			Logger.getLogger("Log from ZadostUserService - schvalit()")
					.warning("uzivatel uz schvalen");
			u.setZadost(null);
			session.delete(z);
			session.saveOrUpdate(u);
			session.getTransaction().commit();
			return false;
		}

		u.setSchvaleny(true);
		u.setZadost(null);
		session.delete(z);
		session.saveOrUpdate(u);
		session.getTransaction().commit();
		return true;
	}

	/* (non-Javadoc)
	 * @see klicenka.service.Impl.ZadostUserService#addUserByZadost(klicenka.persistence.model.User, java.lang.String)
	 */
	@Override
	public boolean addUserByZadost(User u, String message) {
		session.beginTransaction();
		if (u != null) {
			ZadostUser zadost = new ZadostUser();
			zadost.setMassage(message);
			zadost.setUser(u);
			u.setZadost(zadost);
			session.saveOrUpdate(zadost);
			session.saveOrUpdate(u);
			session.getTransaction().commit();
			return true;
		} else {
			session.getTransaction().commit();
			Logger.getLogger("Log from ZadostUserService - addUserByZadost()")
					.warning("Ne muzu poslat zadost, doslo k chybě");
			return false;
		}

	}

	/* (non-Javadoc)
	 * @see klicenka.service.Impl.ZadostUserService#odmitnout(klicenka.persistence.model.ZadostUser)
	 */
	@Override
	public boolean odmitnout(ZadostUser z) {
		session.beginTransaction();
		User u = z.getUser();
		session.delete(z);
		session.delete(u);

		session.getTransaction().commit();
		return true;
	}

}
