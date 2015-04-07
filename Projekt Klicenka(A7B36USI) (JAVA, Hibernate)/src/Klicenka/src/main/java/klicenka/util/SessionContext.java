package klicenka.util;


import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import klicenka.persistence.model.User;
/**
 * 
 * Reprezentuje zalogovannou personu
 * Využiva pattern Singlton 
 *
 */
public class SessionContext {

	/**
	 * Aktualni instance SessionContext
	 */
	private static SessionContext instance;
	/**
	 * Zalogovanny User
	 * @see User
	 */
	private User user;
	/**
	 * aktualni oteřena Session
	 * @see org.hibernate.session
	 */
	private Session session;
	 
    /**
     * privatni Konstruktor, začne novou session
     */
    private SessionContext() {
    	 session =  new Configuration().configure().buildSessionFactory().openSession();
    }

   /**
    * V připadě že persona neni autorizovana, zavola konstruktor SessionContext
    * @return instance SessionContext
    */
    
   public static SessionContext getInstance() {
    /*
     * Je-li promenna instance null, tak se vytvori objekt
     */   
	   	if (instance == null) {
            instance = new SessionContext();
           
        }
       
        return instance;
    }
   
   /**
    * Nastavi udaje autorizovanneho uzivatela
    * @param user udaje uzivatela
    * @see klicenka.persistence.model.User
    */
   public void setUser(User user){
	   if(this.user == null) this.user = user;
   }
   	/**
   	 * vrati autorizovanneho uzivatele
   	 * @return aktualna uzivatelska udaje
   	 * @see klicenka.persistence.model.User
   	 */
   public User getUser(){
	   return this.user;
   }
   /**
    * getter pro session
    * @return aktualni session
    * @see org.hibernate.Session
    */
   public Session getSession() {
	   return session;
   }
   
}


