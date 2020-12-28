package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.shrews.beans.Media;
import dev.shrews.beans.MediaType;
import dev.shrews.beans.Shelf;
import dev.shrews.beans.User;
import dev.shrews.beans.ShelfAssignment;
import dev.shrews.data.ShelfAssignmentDAO;
import dev.shrews.data.ShelfAssignmentHibernate;

@TestMethodOrder(OrderAnnotation.class)
class shelfAssignmentTest {
	
	private static ShelfAssignmentDAO shelfAssignmentDao;

	@BeforeAll public static void setUp(){
		shelfAssignmentDao = new ShelfAssignmentHibernate();
	}

	@Test
	@Order(1)
	void testGetById() {
		ShelfAssignment sa = shelfAssignmentDao.getByShelfAssignmentId(1);
		System.out.println(sa);
		assertTrue(sa != null);
		System.out.println(sa);
	}

	@Test
	@Order(2)
	void testGetAll() {
		List<ShelfAssignment> sal = shelfAssignmentDao.getAll();
		System.out.println(sal);
		assertTrue(sal.size() > 0);
	}

	@Test
	@Order(3)
	void testGetByMediaId() {
		List<ShelfAssignment> sal = shelfAssignmentDao.getByShelfId(1);
		System.out.println(sal);
		assertTrue(sal.size() > 0);
	}

	@Test
	@Order(4)
	void testAddAndDelete() {
		ShelfAssignment sa = new ShelfAssignment();
		Shelf shelf = new Shelf();
		shelf.setId(1);
		sa.setShelf(shelf);
		User u = new User();
		u.setId(1);
		Media m = new Media();
		m.setId(1);
		MediaType mt = new MediaType();
		mt.setId(1);
		m.setMediaType(mt);
		sa.setMedia(m);
		sa.setUser(u);
		sa = shelfAssignmentDao.addShelfAssignment(sa);
		System.out.println(sa);
		assertTrue(sa.getId() != 0);
		Integer id = sa.getId();
		shelfAssignmentDao.delete(sa);
		ShelfAssignment sa2 = shelfAssignmentDao.getByShelfAssignmentId(id);
		assertTrue(sa2 == null);
	}

}
