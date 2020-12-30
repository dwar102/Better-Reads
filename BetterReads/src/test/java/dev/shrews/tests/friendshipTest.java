package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import dev.shrews.beans.Friendships;
import dev.shrews.data.FriendshipDAO;
import dev.shrews.data.FriendshipHibernate;

@TestMethodOrder(OrderAnnotation.class)
class friendshipTest {
	
	private static FriendshipDAO friendshipDao;

	@BeforeAll public static void setUp(){
		friendshipDao = new FriendshipHibernate();
	}

	@Test
	@Order(1)
	void testGetById() {
		Friendships f = friendshipDao.getById(1);
		//System.out.println(f);
		assertTrue(f != null);
		//System.out.println(f);
	}

	@Test
	@Order(2)
	void testGetAll() {
		Set<Friendships> fs = friendshipDao.getAll();
		//System.out.println(fs);
		assertTrue(fs.size() > 0);
	}

	@Test
	@Order(3)
	void testGetByUserId() {
		Set<Friendships> fs = friendshipDao.getByUserId(1);
		System.out.println(fs);
		assertTrue(fs.size() > 0);
	}

	@Test
	@Order(4)
	void testAddAndDelete() {
		Friendships f = new Friendships();
		f = friendshipDao.addFriendship(f);
		//System.out.println(f);
		assertTrue(f.getFriendship_id() != 0);
		Integer id = f.getFriendship_id();
		friendshipDao.delete(f);
		Friendships f2 = friendshipDao.getById(id);
		assertTrue(f2 == null);
	}

}
