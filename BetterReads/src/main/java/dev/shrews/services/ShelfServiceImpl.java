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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Shelf updateShelf(Shelf oldShelf, Shelf newShelf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteShelf(Shelf s) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Shelf> getShelves() {
		// TODO Auto-generated method stub
		return null;
	}

}
