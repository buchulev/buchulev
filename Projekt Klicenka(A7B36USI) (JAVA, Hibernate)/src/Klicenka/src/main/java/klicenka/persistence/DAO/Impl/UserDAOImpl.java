package klicenka.persistence.DAO.Impl;

import java.util.HashSet;
import java.util.logging.Logger;

import klicenka.persistence.DAO.UserDAO;
import klicenka.persistence.model.User;





import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 
 * DAO pro prace s klicenka.persistence.model.User
 *
 */
public class UserDAOImpl implements UserDAO {
	/**
	 * Session
	 * 
	 * @see org.hibernate.Session
	 */
	public Session session;
	/**
	 * Konstruktor
	 * 
	 * @param session
	 * @see org.hibernate.Session
	 */
	public UserDAOImpl(Session session) {
		this.session = session;
	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.UserDAO#getUserById(int)
 */
	@Override
	public User getUserById(int id) {
		return (User) session.get(User.class, id);
	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.UserDAO#getUsers()
 */
	@Override
	@SuppressWarnings("unchecked")
	public HashSet<User> getUsers() {
		Query q = session.createQuery("From User");
		if(q.list().isEmpty()) {
			return null;
		}
		else return new HashSet<User>(q.list());
	}
	

/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.UserDAO#getUserByEmail(java.lang.String)
 */
	@Override
	public User getUserByEmail(String email) {
		Query q = session.createQuery("From User where email=:email");
		q.setParameter("email", email);
		
		if( q.list().isEmpty()) {
			return null;
		}
		else return (User) q.list().get(0);
		
	}

	/* (non-Javadoc)
	 * @see klicenka.persistence.DAO.Impl.UserDAO#getUserByUsername(java.lang.String)
	 */
	@Override
	public User getUserByUsername(String username) {
		Query q = session.createQuery("From User where username=:username");
		q.setParameter("username", username);
		
		if( q.list().isEmpty()) {
			return null;
		}
		
		return (User) q.list().get(0);
	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.UserDAO#add(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, boolean)
 */
	@Override
	public User add(String firstName, String lastName, String userName,
			String password, String email, boolean nakupci, boolean admin,
			boolean schvaleny) {

		/*
		 * kontrola vyjimek
		 */
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		if (!m.matches()) {
			Logger.getLogger("Log from UserDAOImpl - add()").warning(
					"spatny email");
			return null;
		}

		/*
		 *  Kontrola zda je email unikatni
		 */
		if (getUserByEmail(email) != null) {

			Logger.getLogger("Log from UserDAOImpl - add()").warning(
					"Email je jiz v databбzi");
			return null;
		}

		if (getUserByUsername(userName) != null) {

			Logger.getLogger("Log from UserDAOImpl - add()").warning(
					"Vas userName neni unikatni");
			return null;
		}

		User user = new User(firstName, lastName, userName, password, email,
				nakupci, admin, schvaleny);
		session.save(user);
		return user;

	}

	/* (non-Javadoc)
	 * @see klicenka.persistence.DAO.Impl.UserDAO#isValid(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isValid(String nickName, String password) {
		Query q = session.createQuery("From User where password=:password and username=:username and schvaleny=true");
		q.setParameter("password", password);
		q.setParameter("username", nickName);
		return !(q.list().isEmpty());
	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.UserDAO#getAccaunt(java.lang.String, java.lang.String)
 */
	@Override
	public User getAccaunt(String userName, String password) {
		if (isValid(userName, password)) {
			return getUserByUsername(userName);
		} else
		    Logger.getLogger("Log from  UserDAOImpl getAccaunt()").warning("Spatne jmeno/heslo nebo uzivatel jiz nebyl schvalen");
			return null;
	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.UserDAO#editUser(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, boolean)
 */
	@Override
	public boolean editUser(int userId, String firstName, String lastName,
			String userName, String email, String heslo, boolean nakupci,
			boolean admin) {

		User user = getUserById(userId);

		if (user != null) {

			if (getUserByEmail(email) != null) {
				Logger.getLogger("Log from UserDAOImpl - editUser()").warning(
						"zivatel s tim to takovym to emailem uz exituje");
				return false;
			}

			if (getUserByUsername(userName) != null) {

				Logger.getLogger("Log from UserDAOImpl - editUser()").warning(
						"Uzivatel s tim to takovym to username uz exituje");
				return false;
			}
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUserName(userName);
			user.setEmail(email);
			user.setPassword(heslo);
			user.setNakupci(nakupci);
			user.setAdmin(admin);

			session.saveOrUpdate(user);
			return true;
		}

		else {
			Logger.getLogger("Log from UserDAOImpl - editUser()").warning(
					"Uzivatel s tim to id neexistuje");
			return false;
		}

	}
/* (non-Javadoc)
 * @see klicenka.persistence.DAO.Impl.UserDAO#getUsersToConfirm()
 */
	@Override
	@SuppressWarnings("unchecked")
	public HashSet<User> getUsersToConfirm() {
		Query q = session.createQuery("From User where schvaleny=false");
		if(q.list().isEmpty()) {
			return null;
		}
		else {
			return (new HashSet<User>(q.list()));
		}
		
	}
	

}
