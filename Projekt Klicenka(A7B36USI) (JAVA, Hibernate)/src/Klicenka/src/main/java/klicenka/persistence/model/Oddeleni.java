package klicenka.persistence.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ODDELENI")
/**
 * 
 * Třida reprezentujici oddeleni
 *
 */
public class Oddeleni {

	@Id
	@Column(name = "ODDELENIID", unique = true, nullable = false, precision = 5, scale = 0)
	@GeneratedValue
	private int oddeleniId;
	@Column(name = "JMENOODDELENI", nullable = true, length = 40)
	private String jmenoOddeleni;

	public Oddeleni() {
		this.zamestnanciOddeleni = new HashSet<User>();
	}

	public Oddeleni(String jmeno) {
		this.jmenoOddeleni = jmeno;
		this.zamestnanciOddeleni = new HashSet<User>();
	}

	public int getOddeleniId() {
		return this.oddeleniId;
	}

	public void setOddeleniId(int ID) {
		this.oddeleniId = ID;
	}

	@OneToOne
	@JoinColumn(name = "vedouci_id")
	private User vedouci;

	public User getVedouci() {
		return vedouci;
	}

	public void setVedouci(User vedouci) {
		this.vedouci = vedouci;
	}

	@OneToMany(mappedBy = "oddeleni")
	private Set<User> zamestnanciOddeleni;

	public Set<User> getZamestnanciOddeleni() {
		return zamestnanciOddeleni;
	}

	public void setZamestnanciOddeleni(Set<User> zamestnanciOddeleni) {
		this.zamestnanciOddeleni = zamestnanciOddeleni;
	}

	public String getJmenoOddeleni() {
		return jmenoOddeleni;
	}

	public void setJmenoOddeleni(String jmenoOddeleni) {
		this.jmenoOddeleni = jmenoOddeleni;
	}
	
	public String toString() {
		return this.getJmenoOddeleni();
	}

}
