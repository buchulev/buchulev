package klicenka.persistence.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
/**
 * 
 * Třida reprezentujici žadost na nakup produktu
 *
 */
public class ZadostNakup {
	@Id
	@Column(name = "zadostNakuptId", unique = true, nullable = false, precision = 5, scale = 0)
    @GeneratedValue
	private int zadostNakupId;
	
	private String message;

	public void setZadostProductId(int zadostNakupId) {
		this.zadostNakupId = zadostNakupId;
	}
	
	public int getZadostNakupId() {
		return zadostNakupId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	

	
	
	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	
	private Product product;
	
	public Product getProduct() {
		return  product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String toString() {
		return getUser().getFirstName() + " " + getUser().getLastName() + " " + getProduct().getName();
		
	}
	
}

