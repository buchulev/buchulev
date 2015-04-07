
package klicenka.persistence.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
/**
 * 
 * Třida reprezentujici licence na produkt
 *
 */
public class Licence {

    @Id
    @GeneratedValue
    private int id;
    private String code;
    private boolean activated;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date activeFrom;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date activeTill;
    private int licenceLength;
    private boolean isCorporate;

    public Licence() {
        users = new HashSet<User>();
    }


    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }
    
    
    public boolean getActivated() {
    	return activated;
    }
    public void setActivated(boolean activated) {
    	this.activated = activated;
    }
    
    public boolean getIsCorporate() {
    	return isCorporate;
    }
    
    public void setIsCorporate(boolean isCorporate) {
    	this.isCorporate = isCorporate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setActiveFrom(Date activeFrom) {
    	Date d = (Date) activeFrom.clone();
        this.activeFrom = d;
    }

    public Date getActiveFrom() {
        Date d = (Date) this.activeFrom.clone();
        return d;
    }

    public void setActiveTill(Date activeTill) {
    	Date d = (Date) activeTill.clone();
        this.activeTill = d;
    }

    public Date getActiveTill() {
    	Date d = (Date) this.activeTill.clone();
        return d;
    }

    public void setLicenceLength(int licenceLength) {
        this.licenceLength = licenceLength;
    }

    public int getLicenceLength() {
        return licenceLength;
    }

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "licences")
    public Set<User> users;

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<User> getUsers() {
        return users;
    }
    @Override
    public String toString() {
    	return getProduct().getName() + " " + getActiveTill() + " " + getCode() ;
    }

}
