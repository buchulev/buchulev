package klicenka.service;

import klicenka.persistence.model.Oddeleni;
import klicenka.persistence.model.User;

public interface OddeleniService {

	/**
	 * Nastavi vedouсího
	 * @param oddeleni
	 * @param vedouciOddeleni
	 * @return <code>true</code>
	 */
	public abstract boolean setVedouci(Oddeleni oddeleni, User vedouciOddeleni);

	/**
	 * Vytvaři nove oddeleni
	 * @param name
	 * @param vedouci
	 * @return <code>true</code> pokud bylo zapsano do systemu, jinak <code>false</code>
	 */
	public abstract boolean createOddeleni(String name, User vedouci);

	/**
	 * Zapiše uživatele v oddeleni
	 * @param oddeleni
	 * @param user
	 * @return <code>true</code> pokud proběhlo uspešně, jinak <code>false</code>
	 */
	public abstract boolean conectUserWithDepartment(Oddeleni oddeleni,
			User user);

	/**
	 * Smaže vedouiciho z oddeleni
	 * @param oddeleni
	 * @return <code>true</code>
	 */
	public abstract boolean removeVedouciOddeleni(Oddeleni oddeleni);

	/**
	 * Smaže pracovnika z oddeleni
	 * @param oddeleni
	 * @param user
	 * @return <code>true</code>
	 */
	public abstract boolean removeUserFromDepartment(Oddeleni oddeleni,
			User user);

	/**
	 * Smaže oddeleni
	 * @param o oddeleni
	 * @return <code>true</code>
	 */
	public abstract boolean deleteDepartment(Oddeleni o);

}