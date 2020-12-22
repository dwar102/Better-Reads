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
import dev.shrews.beans.Review;
import dev.shrews.data.ReviewDAO;
import dev.shrews.data.ReviewHibernate;

@TestMethodOrder(OrderAnnotation.class)
class reviewTest {
	
	private static ReviewDAO reviewDao;

	@BeforeAll public static void setUp(){
		reviewDao = new ReviewHibernate();
	}

	@Test
	@Order(1)
	void testGetById() {
		Review r = reviewDao.getById(2);
		//System.out.println(r);
		assertTrue(r != null);
	}

	@Test
	@Order(2)
	void testGetAll() {
		Set<Review> rs = reviewDao.getAll();
		//System.out.println(rs);
		assertTrue(rs.size() > 0);
	}

	@Test
	@Order(3)
	void testGetByMediaId() {
		Set<Review> rs = reviewDao.getByMediaId(2);
		//System.out.println(rs);
		assertTrue(rs.size() > 0);
	}

	@Test
	@Order(4)
	void testGetByUserId() {
		Set<Review> rs = reviewDao.getByUserId(1);
		System.out.println(rs);
		assertTrue(rs.size() > 0);
	}

	@Test
	@Order(5)
	void testAddAndDelete() {
		Review r = new Review();
		r.setRating(50);
		Media m = new Media();
		m.setId(1);
		MediaType mt = new MediaType();
		mt.setId(1);
		m.setMediaType(mt);
		r.setMedia(m);
		User u = new User();
		u.setId(1);
		r.setUser(u);
		r = reviewDao.addReview(r);
		//System.out.println(r);
		assertTrue(r.getId() != 0);
		Integer id = r.getId();
		reviewDao.delete(r);
		Review r2 = reviewDao.getById(id);
		assertTrue(r2 == null);
	}

}
