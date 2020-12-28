package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.Shelf;
import dev.shrews.beans.User;

public interface ShelfDAO extends GenericDAO<Shelf> {

	//create
		public Shelf addShelf(Shelf s);
	//read
		public Shelf getShelf(Shelf s);
		public Shelf getShelfById(Integer id);
	//update
		public Shelf updateShelf(Shelf shelf);
	//delete
		public void deleteShelf(Shelf s);
	//list
		public Set<Shelf> getShelves();
		Set<Shelf> getShelves(User loggedUser);
		Set<Shelf> getUserShelves(User loggedUser);
}
