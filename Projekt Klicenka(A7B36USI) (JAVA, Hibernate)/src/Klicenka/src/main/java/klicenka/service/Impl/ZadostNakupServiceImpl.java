package klicenka.service.Impl;

import java.util.Calendar;
import java.util.Date;





import org.hibernate.Session;





import klicenka.persistence.DAO.ZadostNakupDAO;
import klicenka.persistence.DAO.Impl.ZadostNakupDAOImpl;
import klicenka.persistence.model.Licence;
import klicenka.persistence.model.User;
import klicenka.persistence.model.ZadostNakup;
import klicenka.persistence.model.ZadostProduct;
import klicenka.service.ZadostNakupService;
/**
 * 
 * Service pro zadost na nakup produktu
 *
 */
public class ZadostNakupServiceImpl implements ZadostNakupService {
	/**
	 * @see klicenka.persistence.DAO.Impl.ZadostNakupDAOImpl
	 */
	public ZadostNakupDAO zadostNakupDAOImpl;

	/**
	 * Session
	 * @see org.hibernate.Session
	 */
	Session session;

	/**
	 * Konstruktor
	 * @param session
	 * @see org.hibernate.Session
	 */
	public ZadostNakupServiceImpl(Session session){
		this.session = session;
		zadostNakupDAOImpl = new ZadostNakupDAOImpl(this.session);
		

	}
	/* (non-Javadoc)
	 * @see klicenka.service.Impl.ZadostNakupService#createZadost(klicenka.persistence.model.ZadostProduct)
	 */
	@Override
	public boolean createZadost(ZadostProduct z) {
		session.beginTransaction();
	    ZadostNakup n = new ZadostNakup();
		n.setMessage(z.getMessage());
	    n.setProduct(z.getProduct());
	    n.setUser(z.getUser());
	    session.saveOrUpdate(n);
	    session.delete(z);
	    session.getTransaction().commit();
	    return true;
	}
	/* (non-Javadoc)
	 * @see klicenka.service.Impl.ZadostNakupService#buyLicenceAndAdd(klicenka.persistence.model.ZadostNakup, java.lang.String, int, boolean)
	 */
	@Override
	public boolean buyLicenceAndAdd(ZadostNakup z, String code, int licenceLength, boolean isCorporate) {
		session.beginTransaction();
		
		Licence l = new Licence();
		l.setCode(code);
		l.setIsCorporate(isCorporate);
		l.setLicenceLength(licenceLength);
		l.setProduct(z.getProduct());
		session.save(l);
		
		
		
		l.setActivated(true);
		/*
		 * zapiše dnešni datum
		 */
		l.setActiveFrom(new Date());
		
		/*
		 * Zpočita datum vypřeni 
		 */
		Calendar c = Calendar.getInstance(); 
		c.setTime(l.getActiveFrom()); 
		c.add(Calendar.DATE, l.getLicenceLength());
		c.getTime();
		/*
		 * Nastavi datum vypřeni
		 */
		l.setActiveTill(c.getTime());
		
		User u = z.getUser();
			u.getLicences().add(l);
			l.getUsers().add(u);
			
		session.saveOrUpdate(u);
		session.saveOrUpdate(l);
		session.delete(z);
		session.getTransaction().commit();
		return true;
	}
	/* (non-Javadoc)
	 * @see klicenka.service.Impl.ZadostNakupService#odmitnout(klicenka.persistence.model.ZadostNakup)
	 */
	@Override
	public boolean odmitnout(ZadostNakup z ) {
		session.beginTransaction();
		session.delete(z);
		session.getTransaction().commit();
		return true;
	}
}
