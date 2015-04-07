package klicenka.persistence.DAO.Impl;

import java.util.Date;
import java.util.HashSet;

import org.hibernate.Query;
import org.hibernate.Session;

import klicenka.persistence.DAO.LicenceDAO;
import klicenka.persistence.model.Licence;
/**
 * 
 * DAO pro prace s klicenka.persistence.model.Licence
 *
 */
public class LicenceDAOImpl implements LicenceDAO  {
	/**
	 * Session
	 * @see org.hibernate.Session
	 */
	public Session session;
/**
 * Konstruktor
 * @param session
 * @see org.hibernate.Session
 */
	public LicenceDAOImpl(Session session) {
		this.session = session;
	}

	
	/* (non-Javadoc)
	 * @see klicenka.persistence.DAO.Impl.LicenceDAO#getActualFreeLicences()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public HashSet<Licence> getActualFreeLicences() {
		Query q = session
				.createQuery("From Licence l where l.users IS EMPTY and (l.activeTill > :date or l.activeTill = null)");
	    q.setParameter("date",new Date());
	    
		if (q.list().isEmpty()) {
			return null;
		} else {
			return new HashSet<Licence>(q.list());
		}
	}

	/* (non-Javadoc)
	 * @see klicenka.persistence.DAO.Impl.LicenceDAO#getActualFreeLicencesByName(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public HashSet<Licence> getActualFreeLicencesByName(String name) {
		Query q = session
				.createQuery("From Licence l where l.users IS EMPTY and (l.activeTill > :date or l.activeTill = null) and l.product.name LIKE :name");
	    q.setParameter("date",new Date());
	    q.setParameter("name","%" + name + "%");
		if (q.list().isEmpty()) {
			return null;
		} else {
			return new HashSet<Licence>(q.list());
		}
	}
}
