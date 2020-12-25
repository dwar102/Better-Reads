package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.shrews.beans.*;
import dev.shrews.services.*;
import dev.shrews.data.*;
import dev.shrews.exceptions.NonUniqueUsernameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class userTest {
	static UserDAO userDao;
	
	@BeforeAll public static void setUp() {
		userDao = new UserHibernate();
	}
	
	@InjectMocks
	static UserServiceImpl userServ;
	
	static Set<User> userMock = new HashSet<>();
	static Integer userSequenceMock = 1;
	
	@Test
	public void testAddandRetrieveUser() { // Testing service layer through the DAO
		User u = new User();
		u.setId(2);
		userMock.add(u);
		User u2 = new User();
		u2.setId(userSequenceMock++);
	}
	@Test
	public void testAddandDelete() throws NonUniqueUsernameException { // testing hibernate connection; not using mockito
		User u = new User();
		u.setUsername("Peter");
		u = userDao.add(u);
		System.out.println(u);
		assertTrue(u.getId() != 0);
		userDao.delete(u);
		}
	@Test
	public void testAddCommentandDelete() {
		User_Media_Comments m = new User_Media_Comments();
		User u = new User();
		m.setUser(u);
		m = userDao.placeCommentForMedia(m);
		assertTrue(m.getComment_id() != 0);
		userDao.delete(m);		
	}
	@Test
	public void testAddReviewCommentandDelete() {
		User_Review_Comments m = new User_Review_Comments();
		User u = new User();
		Review r = new Review();
		Media n = r.getMedia();
		n.setId(0);
		m.setUser(u);
		u.setId(1);
		m.setreview(r);
		m = userDao.placeCommentForReview(m);
		assertTrue(m.getComment_id() != 0);	
		userDao.delete(m);
		User f = userDao.getUserById(1);
		System.out.println(f.getUsername());
	}
}


