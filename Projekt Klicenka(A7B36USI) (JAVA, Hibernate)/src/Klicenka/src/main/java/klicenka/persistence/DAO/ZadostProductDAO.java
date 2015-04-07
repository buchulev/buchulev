package klicenka.persistence.DAO;

import java.util.HashSet;

import klicenka.persistence.model.Oddeleni;
import klicenka.persistence.model.User;
import klicenka.persistence.model.ZadostProduct;

public interface ZadostProductDAO {

	/**
	 * Zkouši je-li uživatel už podal žadost na proukt 
	 * @param username uživatelu
	 * @param productName 
	 * @return <code>true</code> je-li uživatel už takovou žadost podaval a je ne rozhodnuta, jinak <code>false</code>
	 */
	public abstract boolean checkIfExists(String username, String productName);

	/**
	 * Vrati vše žadosti uživatelu v určitem oddeleni
	 * @param o oddeleni
	 * @param vedouci vedouci toho oddeleni
	 * @return vše žadosti uživatelu v určitem oddeleni
	 */
	public abstract HashSet<ZadostProduct> getZadostyByOddeleni(Oddeleni o,
			User vedouci);

	/**
	 * Odmitnout žadost
	 * @param z žadost
	 * @return <code>true</code>
	 */
	public abstract boolean odmitnout(ZadostProduct z);

}