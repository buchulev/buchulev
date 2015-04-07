package klicenka.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ZADOSTUSER")
/**
 * 
 * Třida reprezentujici žadost na vytvařeni učtu
 *
 */
public class ZadostUser  {

    @Id
    @Column(name = "ZADOSTUSERID", unique = true, nullable = false, precision = 5, scale = 0)
    @GeneratedValue
	private int zadostUserId;
    @Column(name = "MASSAGE" , length=1000 )
	private String massage; 
	


		public int getZadostUserId() {
		return this.zadostUserId;
	}
	
	public void setZadostUserId(int id){
		this.zadostUserId = id;
	}
	

		public String getMassage() {
		return this.massage;
	}
	
	public void setMassage(String massage){
		this.massage = massage;
	} 
	

	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String toString(){
		return user.getUserName();
	}
	
	
	
	
}
