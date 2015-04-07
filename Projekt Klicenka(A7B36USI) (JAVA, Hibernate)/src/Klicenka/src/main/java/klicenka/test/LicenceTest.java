package klicenka.test;

import java.util.HashSet;

import klicenka.persistence.model.Licence;
import klicenka.persistence.model.Product;
import klicenka.persistence.model.User;
import klicenka.persistence.model.ZadostNakup;
import klicenka.persistence.model.ZadostProduct;
import klicenka.service.LicenceProductService;
import klicenka.service.ZadostNakupService;
import klicenka.service.ZadostProductService;
import klicenka.service.Impl.LicenceProductServiceImpl;
import klicenka.service.Impl.OddeleniServiceImpl;
import klicenka.service.Impl.UserServiceImpl;
import klicenka.service.Impl.ZadostNakupServiceImpl;
import klicenka.service.Impl.ZadostProductServiceImpl;
import klicenka.service.Impl.ZadostUserServiceImpl;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LicenceTest {
	@Test
	public void podatZadostNaProdukt() {
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();

		
		ZadostProductService z = new ZadostProductServiceImpl(session);
		LicenceProductServiceImpl l = new LicenceProductServiceImpl(session);
		UserServiceImpl u = new UserServiceImpl(session);
		HashSet<Product> pr = l.productDAO.getProductsByName("Adobe CC");
		Product p = new Product();
		for (Product prod : pr) {
			p = prod;
		}

		z.createZadost(u.userDAOImpl.getUserByUsername("jn"), p,
				"Chci licence na produkt Adobe CC.................");

		session.close();
	}
	
	@Test
	public void schvalitLicence() {
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		session.beginTransaction();
		
		ZadostProductServiceImpl z = new ZadostProductServiceImpl(session);
		ZadostNakupService zn = new ZadostNakupServiceImpl(session);
		LicenceProductService l = new LicenceProductServiceImpl(session);
		OddeleniServiceImpl o = new OddeleniServiceImpl(session);
		
		UserServiceImpl u = new UserServiceImpl(session);
		
		HashSet<ZadostProduct> zadosty = z.zadostProductDAO.getZadostyByOddeleni(o.oddeleniDAOImpl.getOddeleni("Administration"), u.userDAOImpl.getUserByUsername("lb"));
		
		ZadostProduct zadost = new ZadostProduct();
		
		for(ZadostProduct za : zadosty) {
			zadost = za;
		}
		
		
		
		Licence lic = l.getLicenceByZadost(zadost);
		if(lic!=null) {
			/*
			 * Když ano tak pošleme přimo uzivatelovi
			 */
			l.giveLicence(lic, zadost.getUser(), zadost);
			}
		else {
			/*
			 * Když neni, pošleme žadost na nakup nakupčimu
			 */
			zn.createZadost(zadost);
		}
		
		
		session.close();

	}
	@Test
	public void zadostNaNakupTest() {
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		session.beginTransaction();
		ZadostNakupServiceImpl z = new ZadostNakupServiceImpl(session);
		HashSet<ZadostNakup> zadosty = z.zadostNakupDAOImpl.getZadostNakup();
		
		ZadostNakup zn = new ZadostNakup();
		for(ZadostNakup zadost : zadosty) {
			if(zadost.getUser().getUserId() == 1) zn = zadost;
		}
		z.buyLicenceAndAdd(zn, "aaaaaa", 30, false);
		session.close();
	}
}
