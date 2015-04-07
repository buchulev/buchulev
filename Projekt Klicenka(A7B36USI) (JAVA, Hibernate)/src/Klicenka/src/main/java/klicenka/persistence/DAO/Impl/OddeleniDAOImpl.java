package klicenka.persistence.DAO.Impl;

import java.util.HashSet;
import java.util.logging.Logger;

import klicenka.persistence.DAO.OddeleniDAO;
import klicenka.persistence.model.Oddeleni;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 
 * DAO pro prace s klicenka.persistence.model.Oddeleni
 *
 */
public class OddeleniDAOImpl implements OddeleniDAO {
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
	public OddeleniDAOImpl(Session session) {
		this.session = session;
	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.OddeleniDAO#addOddeleni(java.lang.String)
 */
	@Override
	public Oddeleni addOddeleni(String jmeno) {
		Query q = session
				.createQuery("From Oddeleni where jmenoOddeleni=:name");
		q.setParameter("name", jmeno);
		if (q.list().isEmpty()) {
			Oddeleni oddeleni = new Oddeleni(jmeno);
			session.save(oddeleni);
			return oddeleni;
		}

		else
			Logger.getLogger("OddeleniDAO addOddeleni log").warning(
					"Takove oddeleni uz existuje");
		return null;

	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.OddeleniDAO#getOddeleni_pl()
 */
	@Override
	public HashSet<Oddeleni> getOddeleni_pl() {

		Query q = session.createQuery("From Oddeleni");

		if (q.list().isEmpty()) {
			return null;
		} else {
			@SuppressWarnings("unchecked")
			HashSet<Oddeleni> oddeleni = new HashSet<Oddeleni>(q.list());
			return oddeleni;
		}
	}

	/* (non-Javadoc)
	 * @see klicenka.persistence.DAO.Impl.OddeleniDAO#getOddeleni(java.lang.String)
	 */
	@Override
	public Oddeleni getOddeleni(String jmeno) {

		Query q = session
				.createQuery("From Oddeleni where jmenoOddeleni=:name");
		q.setParameter("name", jmeno);
		if (q.list().isEmpty())
			return null;
		else {
			Oddeleni oddeleni = (Oddeleni) q.list().get(0);
			return oddeleni;
		}
	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.OddeleniDAO#setOddeleniRename(java.lang.String, java.lang.String)
 */
	@Override
	public boolean setOddeleniRename(String jmenoOddeleni, String noveJmeno) {
		if (getOddeleni(jmenoOddeleni) == null) {
			Logger.getLogger("Log from OddeleniDao - setOddeleniRename")
					.warning("Oddeleni s takovym jmenem neexistuje");
			return false;
		}

		if (getOddeleni(noveJmeno) != null) {
			Logger.getLogger("Log from OddeleniDao - setOddeleniRename")
					.warning("Jmeno noveho oddeleni neni unikatni");
			return false;
		}

		if (jmenoOddeleni.equals(noveJmeno)) {
			Logger.getLogger("Log from OddeleniDao - setOddeleniRename")
					.warning("Ukladate zbytecne totozne jmeno");
		}

		
		Oddeleni o = getOddeleni(jmenoOddeleni);
		o.setJmenoOddeleni(noveJmeno);
		session.saveOrUpdate(o);
		return true;
	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.OddeleniDAO#deleteDepartment(klicenka.persistence.model.Oddeleni)
 */
	@Override
	public boolean deleteDepartment(Oddeleni oddeleni) {
		session.delete(oddeleni);
		return true;

	}

}
