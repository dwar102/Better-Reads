package dev.shrews.data;

import java.util.Set;

import org.hibernate.Session;

import dev.shrews.beans.Shelf;
import dev.shrews.beans.User;
import dev.shrews.utils.HibernateUtil;
import java.util.HashSet;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import java.util.List;


public class ShelfHibernate implements ShelfDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Shelf addShelf(Shelf shelf) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(s);
			tx.commit();
			return shelf;
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			
		} finally {
			s.close();
		}
		return null;
	}

	@Override
	public Shelf getShelf(Shelf s) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Shelf getShelfById(Integer id) {
		
		
		Shelf shelf = new Shelf();
		Session s = hu.getSession();
		String query = "FROM Shelves where id = :id";
		Query<Shelf> q = s.createQuery(query, Shelf.class);
		q.setParameter("id", id);
		shelf = q.getSingleResult();
		s.close();
		return shelf;
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
