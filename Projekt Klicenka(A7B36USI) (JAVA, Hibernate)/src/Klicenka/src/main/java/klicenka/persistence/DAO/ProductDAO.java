package klicenka.persistence.DAO;

import java.util.HashSet;

import klicenka.persistence.model.Product;

public interface ProductDAO {

	/**
	 * Vrati vše produkty
	 * @return vše produkty
	 */
	public abstract HashSet<Product> getProducts();

	/**
	 * Vrati produkt podle nazvu
	 * @param name nazev produktu
	 * @return produkt
	 */
	public abstract HashSet<Product> getProductsByName(String name);

}