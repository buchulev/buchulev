package klicenka.service.Impl;

import java.util.logging.Logger;

import klicenka.persistence.DAO.ZadostProductDAO;
import klicenka.persistence.DAO.Impl.ZadostProductDAOImpl;
import klicenka.persistence.model.Product;
import klicenka.persistence.model.User;
import klicenka.persistence.model.ZadostProduct;
import klicenka.service.ZadostProductService;

import org.hibernate.Session;

/**
 * 
 * Service pro prace s žadostmi na produkt
 *
 */
public class ZadostProductServiceImpl implements ZadostProductService {
	/**
	 * Session
	 * 
	 * @see org.hibernate.Session
	 */
	public Session session;
	/**
	 * @see klicenka.persistence.DAO.Impl.ZadostProductDAOImpl
	 */
	public ZadostProductDAO zadostProductDAO;

	/**
	 * Konstruktor
	 * 
	 * @param session
	 * @see org.hibernate.Session
	 */
	public ZadostProductServiceImpl(Session session) {
		this.session = session;
		zadostProductDAO = new ZadostProductDAOImpl(this.session);
	}

	/* (non-Javadoc)
	 * @see klicenka.service.Impl.ZadostProductService#createZadost(klicenka.persistence.model.User, klicenka.persistence.model.Product, java.lang.String)
	 */
	@Override
	public boolean createZadost(User user, Product product, String kom) {
		session.beginTransaction();
		if (zadostProductDAO.checkIfExists(user.getUserName(),
				product.getName())) {
			Logger.getLogger("").warning("uz jste o ten produkt pozadali");
			return false;
		} else {
			addNewZadost(user, product, kom);
			session.getTransaction().commit();
			return true;
		}
	}

	/**
	 * Vytvaři žadost na produkt
	 * 
	 * @param user
	 * @param product
	 * @param kom
	 * @return <code>true</code>
	 */
	private boolean addNewZadost(User user, Product product, String kom) {
		ZadostProduct z = new ZadostProduct();

		z.setUser(user);
		z.setProduct(product);
		z.setMessage(kom);
		user.getZadostyNaProdukt().add(z);
		session.saveOrUpdate(user);
		session.saveOrUpdate(z);

		return true;
	}

}
