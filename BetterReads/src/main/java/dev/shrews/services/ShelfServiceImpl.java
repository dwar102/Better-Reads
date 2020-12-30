package dev.shrews.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.shrews.beans.Shelf;
import dev.shrews.beans.ShelfAssignment;
import dev.shrews.beans.User;
import dev.shrews.data.ShelfAssignmentDAO;
import dev.shrews.data.ShelfDAO;
import dev.shrews.data.ShelfHibernate;

@Service
public class ShelfServiceImpl implements ShelfService {
  
	private ShelfDAO sd;
	private ShelfAssignmentDAO sad;
	
	@Autowired
	public ShelfServiceImpl(ShelfDAO s, ShelfAssignmentDAO sa) {
		sd = s;
		sad = sa;
	}
	
	@Override
	public Shelf addShelf(Shelf s) {
		Shelf newShelf = sd.addShelf(s);
		return newShelf;
	}

	@Override
	public Shelf getShelf(Shelf s) {
		Shelf sh = sd.getShelfById(s.getId());
		// TODO Auto-generated method stub
		return sh;
	}

	@Override
	public Shelf updateShelf(Shelf shelf) {
		return sd.updateShelf(shelf);
	}

	@Override
	public void deleteShelf(Shelf s) {
		sd.deleteShelf(s);
	}

	@Override
	public Set<Shelf> getShelves() {
		return sd.getShelves();
	}
	@Override
	public Set<Shelf>getUserShelves(User loggedUser){
		return sd.getUserShelves(loggedUser);
	}
	
	public List<ShelfAssignment> getShelfAssignments(Shelf s){
		return sad.getByShelfId(s.getId());
	}

	@Override
	public ShelfAssignment addShelfAssignment(ShelfAssignment sa) {
		return sad.addShelfAssignment(sa);
		
	}

	
	@Override
	public void deleteShelfAssignment(ShelfAssignment sa) {
		
		sad.delete(sa);
		
	}

	@Override
	public ShelfAssignment getShelfAssignmentById(ShelfAssignment sa) {
		return sad.getByShelfAssignmentId(sa.getId());

	}
}
