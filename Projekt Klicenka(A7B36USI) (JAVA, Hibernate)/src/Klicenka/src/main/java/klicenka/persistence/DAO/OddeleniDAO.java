package klicenka.persistence.DAO;

import java.util.HashSet;

import klicenka.persistence.model.Oddeleni;

public interface OddeleniDAO {

	/**
	 * Přidava oddeleni do systemu
	 * @param jmeno Nazev oddeleni
	 * @return vrati oddeleni je-li oddeleni bylo zapsano do systemu jinak <code>null</code>
	 */
	public abstract Oddeleni addOddeleni(String jmeno);

	/**
	 * Vrati vše oddeleni
	 * @return vše oddeleni
	 */
	public abstract HashSet<Oddeleni> getOddeleni_pl();

	/**
	 * Vrati oddeleni podle jmena
	 * @param jmeno Nazev oddeleni
	 * @return oddeleni
	 */
	public abstract Oddeleni getOddeleni(String jmeno);

	/**
	 * Přejmenuje oddeleni
	 * @param jmenoOddeleni aktualni nazev oddeleni
	 * @param noveJmeno novy nazev oddeleni
	 * @return <code>true</code> je-li oddeleni bylo přejmenovano do systemu jinak <code>false</code>
	 */
	public abstract boolean setOddeleniRename(String jmenoOddeleni,
			String noveJmeno);

	/**
	 * Smaže oddeleni
	 * @param oddeleni 
	 * @return <code>true</code>
	 */
	public abstract boolean deleteDepartment(Oddeleni oddeleni);

}