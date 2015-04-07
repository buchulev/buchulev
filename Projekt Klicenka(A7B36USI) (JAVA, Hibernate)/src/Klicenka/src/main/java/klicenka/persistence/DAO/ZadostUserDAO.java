package klicenka.persistence.DAO;

import java.util.HashSet;

import klicenka.persistence.model.ZadostUser;

public interface ZadostUserDAO {

	/**
	 * Vrati uživatele, ktere mě-li by byt schvalene do systemu (pro administratora)
	 * @return uživatele, ktere mě-li by byt schvalene do systemu (pro administratora)
	 */
	public abstract HashSet<ZadostUser> getUsersForAdmin();

	/**
	 * Vrati uživatele, ktere mě-li by byt schvalene do systemu v určitem oddeleni
	 * @return uživatele, ktere mě-li by byt schvalene do systemu  v určitem oddeleni
	 */
	public abstract HashSet<ZadostUser> getUsersForVedouci(int oid);

}