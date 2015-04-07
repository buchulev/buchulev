package klicenka.service.Impl;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.Session;








import klicenka.persistence.DAO.OddeleniDAO;
import klicenka.persistence.DAO.Impl.OddeleniDAOImpl;
import klicenka.persistence.model.Oddeleni;
import klicenka.persistence.model.User;
import klicenka.service.OddeleniService;
/**
 * 
 * Service pro prace s oddeleni
 *
 */
public class OddeleniServiceImpl implements OddeleniService {
	/**
	 * Session
	 * @see org.hibernate.Session
	 */
	Session session;
	/**
	 * @see klicenka.persistence.DAO.Impl.OddeleniDAOImpl
	 */
	public OddeleniDAO oddeleniDAOImpl;


	/**
	 * Konstruktor
	 * @param session
	 * @see org.hibernate.Session
	 */
	public OddeleniServiceImpl(Session session) {
		this.session = session;
		oddeleniDAOImpl = new OddeleniDAOImpl(this.session);
	}
/* (non-Javadoc)
 * @see klicenka.service.Impl.OddeleniService#setVedouci(klicenka.persistence.model.Oddeleni, klicenka.persistence.model.User)
 */
	@Override
	public boolean setVedouci(Oddeleni oddeleni, User vedouciOddeleni) {

		if(oddeleni.getVedouci()!=null) {
		  this.removeVedouciOddeleni(oddeleni);
		}
		
		if(vedouciOddeleni.getOddeleni()!=null) {
             vedouciOddeleni.setOddeleni(null);
		}
		
		session.beginTransaction();
		oddeleni.setVedouci(vedouciOddeleni);
		vedouciOddeleni.setVedoucioddeleni(oddeleni);
		session.saveOrUpdate(oddeleni);
		session.saveOrUpdate(vedouciOddeleni);
		session.getTransaction().commit();
		return true;

	}
	/* (non-Javadoc)
	 * @see klicenka.service.Impl.OddeleniService#createOddeleni(java.lang.String, klicenka.persistence.model.User)
	 */
	@Override
	public boolean createOddeleni(String name, User vedouci) {
		session.beginTransaction();
		
		if(vedouci.getOddeleni()!=null) {
			Oddeleni o = vedouci.getOddeleni();
			o.getZamestnanciOddeleni().remove(vedouci);
			session.saveOrUpdate(o);
            vedouci.setOddeleni(null);
		}
		
		Oddeleni o = oddeleniDAOImpl.addOddeleni(name);
		if(o!=null) {
                
				o.setVedouci(vedouci);
				vedouci.setVedoucioddeleni(o);
				session.saveOrUpdate(o);
				session.saveOrUpdate(vedouci);
				session.getTransaction().commit();
				return true;
		}
		
		return false;
		
	}
/* (non-Javadoc)
 * @see klicenka.service.Impl.OddeleniService#conectUserWithDepartment(klicenka.persistence.model.Oddeleni, klicenka.persistence.model.User)
 */
	@Override
	public boolean conectUserWithDepartment(Oddeleni oddeleni, User user) {
		session.beginTransaction();
   
		if (user.getOddeleni() != null) {
			Logger.getLogger(
					"Log from Oddeleni Service  - connectUsersWithDepartment")
					.warning("Zamestnanec jiz v nejakem oodeleni je");
			return false;
		}

		if (oddeleniDAOImpl.getOddeleni(oddeleni.getJmenoOddeleni()) == null) {
			Logger.getLogger(
					"Log from Oddeleni Service  - connectUsersWithDepartment")
					.warning("Takove oddeleni neexistuje");

			return false;
		}

		
		user.setOddeleni(oddeleni);
		oddeleni.getZamestnanciOddeleni().add(user);
		session.saveOrUpdate(user);
		session.saveOrUpdate(oddeleni);
		session.getTransaction().commit();
		return true;
	}
/* (non-Javadoc)
 * @see klicenka.service.Impl.OddeleniService#removeVedouciOddeleni(klicenka.persistence.model.Oddeleni)
 */
	@Override
	public boolean removeVedouciOddeleni(Oddeleni oddeleni) {
		session.beginTransaction();
		User u = (User) oddeleni.getVedouci();
		oddeleni.setVedouci(null);
		u.setVedoucioddeleni(null);
		session.saveOrUpdate(oddeleni);
		session.saveOrUpdate(u);
		session.beginTransaction().commit();
		return true;
	}
/* (non-Javadoc)
 * @see klicenka.service.Impl.OddeleniService#removeUserFromDepartment(klicenka.persistence.model.Oddeleni, klicenka.persistence.model.User)
 */
	@Override
	public boolean removeUserFromDepartment(Oddeleni oddeleni, User user ) {
		session.beginTransaction();


		oddeleni.getZamestnanciOddeleni().remove(user);
		user.setOddeleni(null);

		session.saveOrUpdate(oddeleni);
		session.saveOrUpdate(user);
		session.beginTransaction().commit();
		return true;

	}
	/* (non-Javadoc)
	 * @see klicenka.service.Impl.OddeleniService#deleteDepartment(klicenka.persistence.model.Oddeleni)
	 */
	@Override
	public boolean deleteDepartment(Oddeleni o) {
		session.beginTransaction();
		User u = (User ) o.getVedouci();
		int uid = u.getUserId();
		u = (User) session.get(User.class, uid);
		u.setVedoucioddeleni(null);
		o.setVedouci(null);
		
	    Set<User> empl = o.getZamestnanciOddeleni();
	    if(!empl.isEmpty()) {
	    	 for(User u1: empl) {
	 	    	u1.setOddeleni(null);
	 	    	session.saveOrUpdate(u1);
	 	    }
	    }
	   
	    
		o.setZamestnanciOddeleni(new HashSet<User>());;
		session.saveOrUpdate(u);
		session.saveOrUpdate(o);
		
		
		oddeleniDAOImpl.deleteDepartment(o);
		session.getTransaction().commit();
		return true;
	}
}
