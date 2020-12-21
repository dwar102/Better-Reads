package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.shrews.beans.*;
import dev.shrews.services.*;
import dev.shrews.data.*;
import dev.shrews.exceptions.NonUniqueUsernameException;

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
		u.setId(userSequenceMock);
		u = userDao.add(u);
		System.out.println(u);
		assertTrue(u.getId() != 0);
		userDao.delete(u);
		}
	}

