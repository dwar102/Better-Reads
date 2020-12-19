package dev.shrews.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class shelfTest {

	ShelfService shelfServ = new ShelfServiceImpl();
	Shelf ShelfToAdd = new Shelf();
	@Test public void testShelfService() { //this test intended to 1. add a shelf to database 2. retrieve the item from the database
		
		//make a shelf object (and all the sub objects)
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
		for(int i = 0; i < 10; i++) {
			sa.setDate(null);
			Media media = new Media();
			media.setCreator("test creator" + i);
			MediaType mt = new MediaType();
			Genre g = new Genre();
			g.setName("test genre");
			mt.setGenre(g);
			mt.setName("test media type");
			media.setMediaType(mt);
			sa.setMedia(media);
			sa.setShelf(ShelfToAdd);
			sa.setUser(kyle);
			assgns.add(sa);
		}
		ShelfToAdd.setShelfAssignments(assgns);
		try {
			//try to add to database now
			Shelf newShelf = shelfServ.addShelf(ShelfToAdd);
			if(newShelf != null)
			assertEquals(newShelf.getName(), ShelfToAdd.getName());
		}
		catch(Exception e){
			e.printStackTrace();
		}

		

	}
}
