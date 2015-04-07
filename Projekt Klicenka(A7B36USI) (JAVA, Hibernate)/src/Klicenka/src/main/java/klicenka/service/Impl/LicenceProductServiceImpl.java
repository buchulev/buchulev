package klicenka.service.Impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.logging.Logger;

import klicenka.persistence.DAO.ProductDAO;
import klicenka.persistence.DAO.Impl.ProductDAOImpl;
import klicenka.persistence.model.User;
import klicenka.persistence.model.Licence;
import klicenka.persistence.model.ZadostProduct;
import klicenka.service.LicenceProductService;

import org.hibernate.Query;
import org.hibernate.Session;
/**
 * 
 * Service pro prace s licence
 *
 */
public class LicenceProductServiceImpl implements LicenceProductService {
	
	/**
	 * Session
	 * @see org.hibernate.Session
	 */
	public Session session;
	/**
	 * @see klicenka.persistence.DAO.Impl.ProductDAOImpl
	 */
	public ProductDAO productDAO;
	
	
	/**
	 * Konstruktor
	 * @param session
	 * @see org.hibernate.Session
	 */
	public LicenceProductServiceImpl(Session session){
		this.session = session;
		productDAO = new ProductDAOImpl(this.session);
	}
	/* (non-Javadoc)
	 * @see klicenka.service.Impl.LicenceProductService#getUserLicences(klicenka.persistence.model.User)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public HashSet<Licence> getUserLicences(User user) {
		session.beginTransaction();
		Query q = session.createQuery("Select l From Licence l join l.users u where u.userId=:id ");
		q.setParameter("id", user.getUserId());
		if(q.list().isEmpty()) {
			session.getTransaction().commit();
			return null;
		}
		else {
			HashSet<Licence> licences = new HashSet<Licence>(q.list());
			session.getTransaction().commit();
			return licences;
		}
	}
	/* (non-Javadoc)
	 * @see klicenka.service.Impl.LicenceProductService#vzdatUsersLicence(klicenka.persistence.model.User, klicenka.persistence.model.Licence)
	 */
	@Override
	public boolean vzdatUsersLicence(User user,Licence licence) {
		try {
		session.beginTransaction();
		user.getLicences().remove(licence);
		licence.getUsers().remove(user);
		session.saveOrUpdate(user);
		session.saveOrUpdate(licence);
		session.getTransaction().commit();
		return true;
		}
		catch(Exception ex) {
			Logger.getLogger("Log from LicenceService vzdatUsersLicence()").warning(ex.getMessage());
			return false;
		}
	}
	/* (non-Javadoc)
	 * @see klicenka.service.Impl.LicenceProductService#getLicenceByZadost(klicenka.persistence.model.ZadostProduct)
	 */
	@Override
	public Licence getLicenceByZadost(ZadostProduct z) {
		Query q = session.createQuery("Select l From Licence l  where l.product=:product and (l.activeTill>:at or l.activated=false) and (l.users.size < 1 or l.isCorporate = true )");

		q.setParameter("product", z.getProduct());
		q.setParameter("at", new Date());
		if(q.list().isEmpty())
		return null;
		else return (Licence) q.list().get(0);
	}
	/* (non-Javadoc)
	 * @see klicenka.service.Impl.LicenceProductService#giveLicence(klicenka.persistence.model.Licence, klicenka.persistence.model.User, klicenka.persistence.model.ZadostProduct)
	 */
	@Override
	public boolean giveLicence(Licence l, User u,ZadostProduct z) {
		session.beginTransaction();
		
		if(!l.getActivated()) {
		l.setActivated(true);
		l.setActiveFrom(new Date());
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(l.getActiveFrom()); 
		c.add(Calendar.DATE, l.getLicenceLength());
		c.getTime();
		
		l.setActiveTill(c.getTime());
		}
		
		u.getLicences().add(l);
		l.getUsers().add(u);
		session.saveOrUpdate(u);
		session.saveOrUpdate(l);
        session.delete(z);
		session.getTransaction().commit();
		
		return true;
	}
}
