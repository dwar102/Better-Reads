package dev.shrews.data;

import java.util.List;
import java.util.Set;

import dev.shrews.beans.ShelfAssignment;

public interface ShelfAssignmentDAO extends GenericDAO<ShelfAssignment>{
	
	//create
	public ShelfAssignment addShelfAssignment(ShelfAssignment sa);
	//read 
	public ShelfAssignment getByShelfAssignmentId(Integer id);
	public List<ShelfAssignment> getByShelfId(Integer id);
	public List<ShelfAssignment> getAll();
	//update
	public void update(ShelfAssignment sa);
	//delete
	public void delete(ShelfAssignment sa);

}
