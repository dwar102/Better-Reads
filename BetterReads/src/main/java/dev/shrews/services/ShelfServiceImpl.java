package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Shelf;
import dev.shrews.data.ShelfDAO;
import dev.shrews.data.ShelfHibernate;

public class ShelfServiceImpl implements ShelfService {
ShelfDAO sd = new ShelfHibernate();

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
}
