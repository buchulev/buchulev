package klicenka.service;

import klicenka.persistence.model.Product;
import klicenka.persistence.model.User;

public interface ZadostProductService {

	/**
	 * Zapiše žadost na produkt do systemu
	 * 
	 * @param user
	 * @param product
	 * @param kom
	 *            komentař k žadosti
	 * @return <code> true </code> pokud se to podařio, jinak <code>false</code>
	 */
	public abstract boolean createZadost(User user, Product product, String kom);

}