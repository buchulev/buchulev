package klicenka.persistence.DAO;

import java.util.HashSet;

import klicenka.persistence.model.ZadostNakup;

public interface ZadostNakupDAO {

	/**
	 * vrati vše žadosti na nakup
	 * @return vše žadosti na nakup
	 */
	public abstract HashSet<ZadostNakup> getZadostNakup();

}