package klicenka.service;

import klicenka.persistence.model.ZadostNakup;
import klicenka.persistence.model.ZadostProduct;

public interface ZadostNakupService {

	/**
	 * Vytvaři žadost na nakup
	 * @param z žadost na produkt
	 * @return <code>true</code>
	 */
	public abstract boolean createZadost(ZadostProduct z);

	/**
	 * Zapiše licence do systemu a přiřadi ji uživatelovi
	 * @param z žadost na nakup
	 * @param code 
	 * @param licenceLength
	 * @param isCorporate je-li uřčena na skupinu lidi
	 * @return <code>true</code>
	 */
	public abstract boolean buyLicenceAndAdd(ZadostNakup z, String code,
			int licenceLength, boolean isCorporate);

	/**
	 * Odmitne zadost na produkt
	 * @param z Zadost na produkt
	 * @return <code>true</code>
	 */
	public abstract boolean odmitnout(ZadostNakup z);

}