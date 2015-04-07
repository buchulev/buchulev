package klicenka.persistence.DAO.Impl;

import java.util.HashSet;

import klicenka.persistence.DAO.ZadostNakupDAO;
import klicenka.persistence.model.ZadostNakup;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 
 * DAO pro prace s klicenka.persistence.model.ZadostNakup
 *
 */
public class ZadostNakupDAOImpl implements ZadostNakupDAO {
	/**
	 * Session
	 * 
	 * @see org.hibernate.Session
	 */
	public Session session;
	/**
	 * Konstruktor
	 * 
	 * @param session
	 * @see org.hibernate.Session
	 */
	public ZadostNakupDAOImpl(Session session) {
		this.session = session;
	}
	/* (non-Javadoc)
	 * @see klicenka.persistence.DAO.Impl.ZadostNakupDAO#getZadostNakup()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public HashSet<ZadostNakup> getZadostNakup() {
		Query q = session.createQuery("From ZadostNakup");
		if(q.list().isEmpty()) {
			return null;
		}
		return new HashSet<ZadostNakup>(q.list());
	}
}
