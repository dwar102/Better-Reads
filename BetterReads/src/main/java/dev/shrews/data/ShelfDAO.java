package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.Shelf;

public interface ShelfDAO extends GenericDAO<Shelf> {

	//create
		public Shelf addShelf(Shelf s);
	//read
		public Shelf getShelf(Shelf s);
	//update
		public Shelf updateShelf(Shelf oldShelf, Shelf newShelf);
	//delete
		public void deleteShelf(Shelf s);
	//list
		public Set<Shelf> getShelves();
	
}
