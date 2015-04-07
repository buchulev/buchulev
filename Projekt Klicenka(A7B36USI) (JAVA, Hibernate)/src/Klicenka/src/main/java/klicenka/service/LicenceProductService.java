package klicenka.service;

import java.util.HashSet;

import klicenka.persistence.model.Licence;
import klicenka.persistence.model.User;
import klicenka.persistence.model.ZadostProduct;

public interface LicenceProductService {

	/**
	 * Vrati licence přiřazene uřcitemu uživatelovi
	 * @param user
	 * @return licence přiřazene uřcitemu uživatelovi
	 */
	public abstract HashSet<Licence> getUserLicences(User user);

	/**
	 * Vzdat licence
	 * @param user
	 * @param licence
	 * @return <code> true </code> pokud se to podařio, jinak <code>false</code>
	 */
	public abstract boolean vzdatUsersLicence(User user, Licence licence);

	/**
	 * Vrati licence podle žadosti
	 * @param z žadost na produkt
	 * @return licence podle žadosti
	 */
	public abstract Licence getLicenceByZadost(ZadostProduct z);

	/**
	 * Přiřadi licence uživatelovi
	 * @param l licence
	 * @param u uživatel
	 * @param z žadost na produkt
	 * @return <code>true</code>
	 */
	public abstract boolean giveLicence(Licence l, User u, ZadostProduct z);

}