package klicenka.service;

import klicenka.persistence.model.User;
import klicenka.persistence.model.ZadostUser;

public interface ZadostUserService {

	/**
	 * Vrati žadost na vytvařeni učtu podle username
	 * 
	 * @param username
	 * @return vrati žadost pokud takova žadost exituje, jinak <code>null</code>
	 */
	public abstract ZadostUser getZadostUser(String username);

	/**
	 * Schvalit uživatele
	 * 
	 * @param z
	 *            žadost
	 * @return <code>true</code> pokud to podařilo, jinak<code>false</code>
	 */
	public abstract boolean schvalit(ZadostUser z);

	/**
	 * Vytvaři uživatele podle žadosti
	 * 
	 * @param u
	 *            uživatel
	 * @param message
	 *            komentař k žadost
	 * @return <code>true</code> pokud to podaři, jinak <code>false</code>
	 */
	public abstract boolean addUserByZadost(User u, String message);

	/**
	 * Odmitnout žadost o vytvařeni uživatele
	 * 
	 * @param z
	 *            žadost o vytvařeni uživatele
	 * @return <code>true</code>
	 */
	public abstract boolean odmitnout(ZadostUser z);

}