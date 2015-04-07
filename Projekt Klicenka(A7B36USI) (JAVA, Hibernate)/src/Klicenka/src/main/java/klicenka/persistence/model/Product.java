package klicenka.persistence.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Entity;

@Entity
/**
 * 
 * Třida reprezentujici softvarovy produkt
 *
 */
public class Product {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int price;

	public Product() {
		licences = new HashSet<Licence>();
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	@OneToMany(mappedBy = "product")
	private Set<Licence> licences;

	public Set<Licence> getLicences() {
		return licences;
	}

	public void setLicences(Set<Licence> licences) {
		this.licences = licences;
	}

	@OneToMany(mappedBy = "product")
	private Set<ZadostProduct> zadosty;

	public Set<ZadostProduct> getZadosty() {
		return zadosty;
	}

	public void setZadosty(Set<ZadostProduct> zadosty) {
		this.zadosty = zadosty;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
