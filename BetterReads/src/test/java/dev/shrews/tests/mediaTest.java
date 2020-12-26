package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.shrews.beans.Genre;
import dev.shrews.beans.Media;
import dev.shrews.beans.MediaType;
import dev.shrews.data.MediaDAO;
import dev.shrews.data.MediaHibernate;

@TestMethodOrder(OrderAnnotation.class)
class mediaTest {
	
	private static MediaDAO mediaDao;

	@BeforeAll public static void setUp(){
		mediaDao = new MediaHibernate();
	}

	@Test
	@Order(1)
	void testGetById() {
		Media m = mediaDao.getById(1);
		System.out.println(m);
		assertTrue(m != null);
		//System.out.println(g);
	}

	@Test
	@Order(2)
	void testGetAll() {
		Set<Media> ms = mediaDao.getAll();
		//System.out.println(gs);
		assertTrue(ms.size() > 0);
	}

	@Test
	@Order(3)
	void testAddAndDelete() {
		Media m = new Media();
		Genre g = m.getGenre();
		g.setId(1);
		MediaType mt = m.getMediaType();
		mt.setId(1);
		//mt.setName("Book");
		m.setMediaType(mt);
		System.out.println(m);
		m.setTitle("test");
		m = mediaDao.addMedia(m);
		System.out.println(m);
		assertTrue(m.getId() != 0);
		Integer id = m.getId();
		mediaDao.delete(m);
		Media m2 = mediaDao.getById(id);
		assertTrue(m2 == null);
	}

}
