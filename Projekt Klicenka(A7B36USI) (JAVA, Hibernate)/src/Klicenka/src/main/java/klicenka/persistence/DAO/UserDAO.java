package klicenka.persistence.DAO;

import java.util.HashSet;

import klicenka.persistence.model.User;

public interface UserDAO {

	/**
	 * Vrati uživatele podle id
	 * @param id uzivatelu
	 * @return vrati uživatele je-li uživatel existuje v systemu jinak <code>null</code>
	 */
	public abstract User getUserById(int id);

	/**
	 * Vrati všech uživatelů
	 * @return uživatele
	 */
	public abstract HashSet<User> getUsers();

	/**
	 * Vrati uživatele podle e-mail'u
	 * @param email 
	 * @return uzivatel
	 */
	public abstract User getUserByEmail(String email);

	/**
	 * Vrati uživatele podle usernamu
	 * @param username
	 * @return uzivatel
	 */
	public abstract User getUserByUsername(String username);

	/**
	 * Zapiše uživatele do systemu
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param password
	 * @param email
	 * @param nakupci
	 * @param admin
	 * @param schvaleny
	 * @return vrati uzivatele je-li uživatel byl zapsan do systemu jinak <code>null</code>
	 */
	public abstract User add(String firstName, String lastName,
			String userName, String password, String email, boolean nakupci,
			boolean admin, boolean schvaleny);

	/**
	 * Kontroluje autorizačni udaje
	 * @param nickName
	 * @param password
	 * @return <code>true</code> je-li autorizačni udaje jsou v pořadku, jinak <code>false</code>
	 */
	public abstract boolean isValid(String nickName, String password);

	/**
	 * Vrati uživatele podle usernam'u a heslu
	 * @param userName
	 * @param password
	 * @return vrati uzivatele je-li uživatel existuje jinak <code>null</code>
	 */
	public abstract User getAccaunt(String userName, String password);

	/**
	 * Změna uživatelských udaju
	 * @param userId
	 * @param firstName
	 * @param lastName
	 * @param userName
	 * @param email
	 * @param heslo
	 * @param nakupci
	 * @param admin
	 * @return vrati <code>true</code> nove uživatelske udaje byli zapsany do systemu jinak <code>false</code>
	 */
	public abstract boolean editUser(int userId, String firstName,
			String lastName, String userName, String email, String heslo,
			boolean nakupci, boolean admin);

	/**
	 * Vrati uživatele pro schvaleni
	 * @return uživatele pro schvaleni
	 */
	public abstract HashSet<User> getUsersToConfirm();

}