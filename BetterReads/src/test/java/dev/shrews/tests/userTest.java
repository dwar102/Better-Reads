package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.shrews.beans.*;
import dev.shrews.services.*;
import dev.shrews.data.*;

@ExtendWith(MockitoExtension.class)
public class userTest {
	@Mock
	static UserDAO userDao;
	
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
}
