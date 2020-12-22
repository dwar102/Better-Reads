package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Shelf;

public interface ShelfService {
//create
	public Shelf addShelf(Shelf s);
//read
	public Shelf getShelf(Shelf s);
//update
	public Shelf updateShelf(Shelf shelf);
//delete
	public void deleteShelf(Shelf s);
//list
	public Set<Shelf> getShelves();
}
