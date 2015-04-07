package klicenka.persistence.DAO.Impl;

import java.util.HashSet;

import klicenka.persistence.DAO.ZadostUserDAO;
import klicenka.persistence.model.ZadostUser;

import org.hibernate.Query;
import org.hibernate.Session;






/**
 * 
 * DAO pro prace s klicenka.persistence.model.Oddeleni
 *
 */
public class ZadostUserDAOImpl implements ZadostUserDAO  {
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
		public ZadostUserDAOImpl(Session session){
		    this.session = session;
		}
		/* (non-Javadoc)
		 * @see klicenka.persistence.DAO.Impl.ZadostUserDAO#getUsersForAdmin()
		 */
		@Override
		@SuppressWarnings("unchecked")
		public HashSet<ZadostUser> getUsersForAdmin() {
	    Query q = session.createQuery("From ZadostUser u where u.user.oddeleni=null" );
	    if(q.list().isEmpty()) {
	
	    	return null;
	    	
	    }
	    else {
	    	return new HashSet<ZadostUser>(q.list());
	    }
		}
		/* (non-Javadoc)
		 * @see klicenka.persistence.DAO.Impl.ZadostUserDAO#getUsersForVedouci(int)
		 */
		@Override
		@SuppressWarnings("unchecked")
		public HashSet<ZadostUser> getUsersForVedouci(int oid) {
		    Query q = session.createQuery("From ZadostUser u where u.user.oddeleni.id = :oid" );
		    q.setParameter("oid", oid);
		    
		    if(q.list().isEmpty()) {
		
		    	return null;
		    	
		    }
		    else {
		    	return new HashSet<ZadostUser>(q.list());
		    }
			}
		
		
		
		
	
	
	
}
