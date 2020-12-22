package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.Shelf;

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
}
