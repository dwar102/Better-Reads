package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.shrews.beans.Media;
import dev.shrews.beans.MediaType;
import dev.shrews.beans.User;
import dev.shrews.beans.UserTag;
import dev.shrews.data.UserTagDAO;
import dev.shrews.data.UserTagHibernate;

@TestMethodOrder(OrderAnnotation.class)
class userTagTest {
	
	private static UserTagDAO userTagDao;

	@BeforeAll public static void setUp(){
		userTagDao = new UserTagHibernate();
	}

	@Test
	@Order(1)
	void testGetById() {
		UserTag ut = userTagDao.getByUserTagId(1);
		//System.out.println(ut);
		assertTrue(ut != null);
		//System.out.println(ut);
	}

	@Test
	@Order(2)
	void testGetAll() {
		Set<UserTag> ms = userTagDao.getAll();
		//System.out.println(ut);
		assertTrue(ms.size() > 0);
	}

	@Test
	@Order(3)
	void testGetByMediaId() {
		Set<UserTag> uts = userTagDao.getByMediaId(1);
		//System.out.println(ut);
		assertTrue(uts.size() > 0);
	}

	@Test
	@Order(4)
	void testAddAndDelete() {
		UserTag ut = new UserTag();
		ut.setTagName("test");
		Media m = new Media();
		m.setId(1);
		MediaType mt = new MediaType();
		mt.setId(1);
		m.setMediaType(mt);
		ut.setMedia(m);
		User u = new User();
		u.setId(1);
		ut.setUser(u);
		ut = userTagDao.addUserTag(ut);
		System.out.println(ut);
		assertTrue(ut.getId() != 0);
		Integer id = ut.getId();
		userTagDao.delete(ut);
		UserTag ut2 = userTagDao.getByUserTagId(id);
		assertTrue(ut2 == null);
	}

}
