package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.shrews.beans.Genre;
import dev.shrews.data.GenreDAO;
import dev.shrews.data.GenreHibernate;

@TestMethodOrder(OrderAnnotation.class)
class genreTest {
	
	private static GenreDAO genreDao;

	@BeforeAll public static void setUp(){
		genreDao = new GenreHibernate();
	}

	@Test
	@Order(1)
	void testGetById() {
		Genre g = genreDao.getById(1);
		System.out.println(g);
		assertTrue(g != null);
		//System.out.println(g);
	}

	@Test
	@Order(2)
	void testGetAll() {
		Set<Genre> gs = genreDao.getAll();
		//System.out.println(gs);
		assertTrue(gs.size() > 0);
	}

	@Test
	@Order(3)
	void testAddAndDelete() {
		Genre g = new Genre();
		g.setName("test");
		g = genreDao.addGenre(g);
		System.out.println(g);
		assertTrue(g.getId() != 0);
		Integer id = g.getId();
		genreDao.delete(g);
		Genre g2 = genreDao.getById(id);
		assertTrue(g2 == null);
	}

}
