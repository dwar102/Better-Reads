package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import dev.shrews.beans.*;
import dev.shrews.services.*;
import dev.shrews.data.*;

public class messagesTest {
	static MessagesDAO messagesDao;
	
	@BeforeAll public static void setUp() {
		messagesDao = new MessagesHibernate();
	}
	@Test
	public void testAddMessageandDelete() {
		Messages m = new Messages();
		User u = new User();
		m.setRecipient(u);
		m = messagesDao.add(m);
		assertTrue(m.getMessage_id() != 0);
		messagesDao.delete(m);		
	}
}
