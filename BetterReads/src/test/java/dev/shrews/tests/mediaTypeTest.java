package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.shrews.beans.MediaType;
import dev.shrews.data.MediaTypeDAO;
import dev.shrews.data.MediaTypeHibernate;

@TestMethodOrder(OrderAnnotation.class)
class mediaTypeTest {
	
	private static MediaTypeDAO mediaTypeDao;

	@BeforeAll public static void setUp(){
		mediaTypeDao = new MediaTypeHibernate();
	}

	@Test
	@Order(1)
	void testGetById() {
		MediaType mt = mediaTypeDao.getById(1);
		System.out.println(mt);
		assertTrue(mt != null);
		//System.out.println(g);
	}

	@Test
	@Order(2)
	void testGetAll() {
		Set<MediaType> mts = mediaTypeDao.getAll();
		//System.out.println(gs);
		assertTrue(mts.size() > 0);
	}

	@Test
	@Order(3)
	void testAddAndDelete() {
		MediaType mt = new MediaType();
		mt.setName("test");
		mt = mediaTypeDao.addMediaType(mt);
		System.out.println(mt);
		assertTrue(mt.getId() != 0);
		Integer id = mt.getId();
		mediaTypeDao.delete(mt);
		MediaType mt2 = mediaTypeDao.getById(id);
		assertTrue(mt2 == null);
	}

}
