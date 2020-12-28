package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;

import dev.shrews.beans.*;
import dev.shrews.services.*;
import dev.shrews.data.*;

public class shelfTest {
	private static ShelfDAO sd = new ShelfHibernate();
	static ShelfService shelfServ = new ShelfServiceImpl(sd);
	Shelf ShelfToAdd = new Shelf();
	Shelf newShelf = null;

	@Test public void testGetShelf(){//retrieve the item from the database
	Shelf shelfForId = new Shelf();
	shelfForId.setId(1);
	Shelf sh = shelfServ.getShelf(shelfForId);
	assertTrue(sh.getName().equalsIgnoreCase("Favorite Books"));
	}
	
	@Test public void testUpdateShelf(){//update shelf
	Shelf shelfForId = new Shelf();
	shelfForId.setId(1);
	Shelf sh = shelfServ.getShelf(shelfForId);
	assertTrue(sh.getName().equalsIgnoreCase("Favorite Books"));
	sh.setName("Favorite Bookz");
	shelfServ.updateShelf(sh);
	Shelf sh1 = shelfServ.getShelf(shelfForId);
	assertTrue(sh1.getName().equalsIgnoreCase("Favorite Bookz"));
	sh1.setName("Favorite Books");
	shelfServ.updateShelf(sh1);
	sh = shelfServ.getShelf(shelfForId);
	assertTrue(sh.getName().equalsIgnoreCase("Favorite Books"));
	
	}
	
	@Test public void testAddShelf() { //add a shelf to database 
		ShelfToAdd.setName("test shelf name");
		//user here corresponds to actual database entry
		User kyle = new User();
		kyle.setId(1);
		kyle.setPass("pass");
		kyle.setSalt("q3n11e2jcue3kzn3?");
		kyle.setUsername("kyle");
		ShelfToAdd.setUser(kyle);
		
		Set<ShelfAssignment> assgns = new HashSet<ShelfAssignment>();
		ShelfAssignment sa = new ShelfAssignment();
		
//		ShelfToAdd.setShelfAssignments(assgns);
		try {
			//try to get a list of all shelves and store count here
			Integer countBeforeAdd = 0;
			Integer countAfterAdd = 0;
			Integer countAfterDelete = 0;
			
			Set<Shelf> shelves = new HashSet<Shelf>();
			shelves = shelfServ.getShelves();
			countBeforeAdd = shelves.size();
			
			//try to add to database now
			newShelf = shelfServ.addShelf(ShelfToAdd);
			if(newShelf != null)
			assertEquals(newShelf.getName(), ShelfToAdd.getName());
			
			//get all shelves again and compare the count to before
			shelves = new HashSet<Shelf>();
			shelves = shelfServ.getShelves();
			countAfterAdd = shelves.size();
			assertTrue(countBeforeAdd < countAfterAdd);
			
			//try to delete added shelf
			assertTrue(newShelf != null);
			shelfServ.deleteShelf(newShelf);
			
			//get all shelves again and compare the count to before
			shelves = new HashSet<Shelf>();
			shelves = shelfServ.getShelves();
			
			countAfterDelete = shelves.size();
			assertTrue(countAfterAdd > countAfterDelete);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
