package dev.shrews.services;

import java.util.List;
import java.util.Set;

import dev.shrews.beans.Media;
import dev.shrews.beans.Shelf;
import dev.shrews.beans.ShelfAssignment;
import dev.shrews.beans.User;

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
	public Set<Shelf>getUserShelves(User loggedUser);
	public List<ShelfAssignment> getShelfAssignments(Shelf s);
	public ShelfAssignment addShelfAssignment(ShelfAssignment sa);
	public void deleteShelfAssignment(ShelfAssignment sa);
	public ShelfAssignment getShelfAssignmentById(ShelfAssignment sa);
}
