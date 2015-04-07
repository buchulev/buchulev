package klicenka.persistence.model;


import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
/**
 * 
 * Třida reprezentujici učet uživatelu
 *
 */
public class User  {

	
    @Id
    @Column(name = "USERID", unique = true, nullable = false, precision = 5, scale = 0)
    @GeneratedValue
	private int userId;
    @Column(name = "LASTNAME", nullable = true, length = 40)
	private String lastName;
    @Column(name = "FIRSTNAME", nullable = true, length = 40)
	private String firstName;
    @Column(name = "USERNAME", nullable = true, length = 40)
	private String userName;
    @Column(name = "PASSWORD", nullable = true, length = 40)
	private String password;
    @Column(name = "EMAIL",  unique = true, nullable = false, length = 40)
	private String email;
    @Column(name = "NAKUPCI", nullable = true)
	private boolean nakupci;
    @Column(name = "ADMIN", nullable = true)
	private boolean admin; 
    @Column(name = "SCHVALENY")
	private boolean schvaleny;

	
	
	
	
	
	public User(String firstName, String lastName, String userName, String password, String email, boolean nakupci, boolean admin, boolean schvaleny){
		
		this.lastName = lastName;
		this.firstName = firstName;
		this.userName = userName;
		this.email = email;
		this.nakupci = nakupci;
		this.admin = admin; 
		this.password = password;
		this.schvaleny = schvaleny;
		this.vedoucioddeleni = null;
	
	}
	
	
	
	public User(){}

	// GET ....................................................................
	
	public int getUserId() {
		return this.userId;
	}
	
		public String getFirstName() {
		return this.firstName;
	}
	
		public String getLastName() {
		return this.lastName;
	}
	
		public String getUserName() {
		return this.userName;
	}
		public String getPassword() {
		return this.password;
	}

		public boolean getNakupci() {
		return this.nakupci;
	}
	
		public boolean getAdmin() {
		return this.admin;
	}
	
		public boolean getSchvaleny() {
		return this.schvaleny;
	}
	
		public String getEmail(){
		return this.email;
	}
	

	 

	public void setUserId(int ID){
		this.userId = ID;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAdmin(boolean admin){
		this.admin = admin;
	}
	
	public void setSchvaleny(boolean schvaleny){
		this.schvaleny = schvaleny;
	}
	
	public void setNakupci(boolean nakupci){
		this.nakupci = nakupci;
	}

	public void setEmail(String email){
		this.email = email;	
	}


	
//-------------------------------- Vazby -------------------------------------------
	
       @OneToOne(mappedBy="vedouci")
       private Oddeleni vedoucioddeleni;  
	
	public Oddeleni getVedoucioddeleni() {
	return vedoucioddeleni;
	}

	public void setVedoucioddeleni(Oddeleni vedoucioddeleni) {
		this.vedoucioddeleni = vedoucioddeleni;
	}

	

	 @ManyToOne
	 @JoinColumn(name = "oddeleniId")
	 private Oddeleni oddeleni;
	 
	public Oddeleni getOddeleni() {
		return oddeleni;
	}

	public void setOddeleni(Oddeleni oddeleni) {
		this.oddeleni = oddeleni;
	}

	//UPD
	@OneToOne( mappedBy = "user")
	 
	private ZadostUser zadost;
	
	public ZadostUser getZadost() {
		return zadost;
	}

	public void setZadost(ZadostUser zadost) {
		this.zadost = zadost;
	}
	
	
	@OneToMany( mappedBy = "user")
	 
	private Set<ZadostProduct> zadostyNaProdukt;
	
	public Set<ZadostProduct> getZadostyNaProdukt() {
		return zadostyNaProdukt;
	}

	public void setZadostyNaProdukt(Set<ZadostProduct> zadostyNaProdukt) {
		this.zadostyNaProdukt = zadostyNaProdukt;
	}
	
	
	//UPD
	
	@ManyToMany
    @JoinTable(name = "user_licence", joinColumns =
    @JoinColumn(name = "user_id"), inverseJoinColumns =
    @JoinColumn(name = "licence_id"))
	
	private Set<Licence> licences;
	
	public void setLicences(Set<Licence> licences) {
		this.licences = licences;
	}
	
	public Set<Licence> getLicences() {
		return licences;
	}
	

  ///////////////////////////////////////////////////////////////////////////////      
 
        
          @ManyToOne
    @JoinColumn(name = "nadrizenyid")
    private User nadrizeny;

    public void setNadrizeny(User nadrizeny) {
        this.nadrizeny = nadrizeny;
    }

    public User getNadrizeny() {
        return nadrizeny;
    }
    
    
   @OneToMany(mappedBy = "nadrizeny")
    private Set<User> podrizene;

    public void setPodrizene(Set<User> podrizene) {
        this.podrizene = podrizene;
    }

    public Set<User> getPodrizene() {
        return podrizene;
    }
    
    public String toString() { 
    	return this.getFirstName() + " " + this.getLastName();
    }
  
 
}

