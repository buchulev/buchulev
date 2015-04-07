package klicenka.persistence.DAO;

import java.util.HashSet;

import klicenka.persistence.model.Licence;

public interface LicenceDAO {

	public abstract HashSet<Licence> getActualFreeLicences();

	public abstract HashSet<Licence> getActualFreeLicencesByName(String name);

}