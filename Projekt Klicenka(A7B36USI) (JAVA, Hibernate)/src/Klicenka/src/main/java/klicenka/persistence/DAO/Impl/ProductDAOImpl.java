package klicenka.persistence.DAO.Impl;


import java.util.HashSet;

import klicenka.persistence.DAO.ProductDAO;
import klicenka.persistence.model.Product;

import org.hibernate.Query;
import org.hibernate.Session;
/**
 * 
 * DAO pro prace s klicenka.persistence.model.Product
 *
 */
public class ProductDAOImpl implements ProductDAO {
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
	public ProductDAOImpl(Session session) {
		this.session = session;
	}
	/* (non-Javadoc)
	 * @see klicenka.persistence.DAO.Impl.ProductDAO#getProducts()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public HashSet<Product> getProducts() {
		Query q = session.createQuery("From Product");
	   
	    
		if (q.list().isEmpty()) {
			return null;
		} else {
			return new HashSet<Product>(q.list());
		}
	}
	/* (non-Javadoc)
	 * @see klicenka.persistence.DAO.Impl.ProductDAO#getProductsByName(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public HashSet<Product> getProductsByName(String name) {
		Query q = session.createQuery("From Product p where p.name LIKE :name");
	   
	    q.setParameter("name","%" + name + "%");
		if (q.list().isEmpty()) {
			return null;
		} else {
			return new HashSet<Product>(q.list());
		}
	
	}
}
