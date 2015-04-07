package klicenka.persistence.DAO.Impl;

import java.util.HashSet;

import klicenka.persistence.DAO.ZadostProductDAO;
import klicenka.persistence.model.Oddeleni;
import klicenka.persistence.model.User;
import klicenka.persistence.model.ZadostProduct;

import org.hibernate.Query;
import org.hibernate.Session;
/**
 * 
 * DAO pro prace s klicenka.persistence.model.Oddeleni
 *
 */
public class ZadostProductDAOImpl implements ZadostProductDAO {
	/**
	 * Session
	 * 
	 * @see org.hibernate.Session
	 */
	Session session;
	/**
	 * Konstruktor
	 * 
	 * @param session
	 * @see org.hibernate.Session
	 */
	public ZadostProductDAOImpl(Session session) {
		this.session = session;
	}
	
	/* (non-Javadoc)
	 * @see klicenka.persistence.DAO.Impl.ZadostProductDAO#checkIfExists(java.lang.String, java.lang.String)
	 */
@Override
public boolean checkIfExists(String username , String productName) {
	Query q = session.createQuery("From ZadostProduct zp where zp.product.name=:pname and zp.user.userName=:uname");
	q.setParameter("pname", productName);
	q.setParameter("uname", username);
	return  !(q.list().isEmpty());
	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.ZadostProductDAO#getZadostyByOddeleni(klicenka.persistence.model.Oddeleni, klicenka.persistence.model.User)
 */
@Override
@SuppressWarnings("unchecked")
public HashSet<ZadostProduct> getZadostyByOddeleni(Oddeleni o,User vedouci) {
	Query q = session.createQuery("From ZadostProduct where user.oddeleni=:o or user=:u");
	q.setParameter("o", o);
	q.setParameter("u", vedouci);
	if(!q.list().isEmpty()) {
		return new HashSet<ZadostProduct>(q.list());
	}
	
	else return null;
}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.ZadostProductDAO#odmitnout(klicenka.persistence.model.ZadostProduct)
 */
@Override
public boolean odmitnout(ZadostProduct z) {
	session.delete(z);
	return true;
}


}
