package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.shrews.beans.Genre;
import dev.shrews.beans.Media;
import dev.shrews.beans.MediaType;
import dev.shrews.beans.Review;
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
		System.out.println(m);
	}

	@Test
	@Order(2)
	void testGetAll() {
		Set<Media> ms = mediaDao.getAll();
		System.out.println(ms);
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

	@Test
	@Order(4)
	void testGetNumRatingsById() {
		Long nr = mediaDao.getNumRatingsById(2);
		System.out.println(nr);
		assertTrue(nr > 0);
	}

	@Test
	@Order(5)
	void testGetNAvgRatingsById() {
		double avgr = mediaDao.getAvgRatingById(2);
		System.out.println(avgr);
		assertTrue(avgr > 0);
	}

	@Test
	@Order(6)
	void testGetNumTagsById() {
		List<Long> names = mediaDao.getNumTagsById(2);
		System.out.println(names);
		assertTrue(names.size() > 0);
	}

	@Test
	@Order(7)
	void testTagnamesById() {
		List<String> names = mediaDao.getTagnamesById(2);
		System.out.println(names);
		assertTrue(names.size() > 0);
	}


}
