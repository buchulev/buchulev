package klicenka.test;

import klicenka.persistence.model.User;
import klicenka.persistence.model.ZadostUser;
import klicenka.service.ZadostUserService;
import klicenka.service.Impl.UserServiceImpl;
import klicenka.service.Impl.ZadostUserServiceImpl;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
	
	
	@Test
	public void podatZadostTest() {
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();

		session.beginTransaction();

		UserServiceImpl userService = new UserServiceImpl(session);
		ZadostUserService zadostUserService = new ZadostUserServiceImpl(session);

		User user = userService.userDAOImpl.add("TJ", "TP", "TEST", "test777",
				"test@gmail.com", false, false, false);

		session.saveOrUpdate(user);

		session.getTransaction().commit();

		zadostUserService
				.addUserByZadost(
						user,
						"Potrebuju učet..........................................................................");

		session.close();
	}
	
	@Test
	public void schvalitZadostTest() {
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();

	


		ZadostUserService zadostUserService = new ZadostUserServiceImpl(session);
        
		ZadostUser z = zadostUserService.getZadostUser("TEST");
		zadostUserService.schvalit(z);

		session.close();
	}
	
	@Test
	public void userDeleteTest() {
		Session session = new Configuration().configure().buildSessionFactory()
				.openSession();
		UserServiceImpl userService = new UserServiceImpl(session);
		session.beginTransaction();
		session.delete(userService.userDAOImpl.getUserByUsername("TEST"));
		session.getTransaction().commit();
		session.close();
	}
}
