package dev.shrews.data;

import java.util.Set;

import org.hibernate.Session;

import dev.shrews.beans.Friendships;
import dev.shrews.beans.Shelf;
import dev.shrews.beans.User;
import dev.shrews.utils.HibernateUtil;
import java.util.HashSet;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.hibernate.Transaction;
import java.util.List;

@Repository
public class ShelfHibernate implements ShelfDAO {

	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	
	@Override
	public Shelf addShelf(Shelf shelf) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(shelf);
			tx.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
			
		} finally {
			s.close();
		}
		return shelf;
	}

	@Override
	public Shelf getShelf(Shelf s) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Shelf getShelfById(Integer id) {
		
		
		Shelf shelf = new Shelf();
		Session s = hu.getSession();
		String query = "FROM Shelf where shelf_id = :id";
		Query<Shelf> q = s.createQuery(query, Shelf.class);
		q.setParameter("id", id);
		shelf = q.getSingleResult();
		s.close();
		return shelf;
	}


	@Override
	public Shelf updateShelf(Shelf shelf) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(shelf);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return null;
	}

	@Override
	public void deleteShelf(Shelf shelf) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(shelf);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public Set<Shelf> getShelves(User loggedUser) {
		Session s = hu.getSession();
		String query = "FROM Shelf where user_id = :id";
		Query<Shelf> q = s.createQuery(query, Shelf.class);
		q.setParameter("id", loggedUser.getId());
		List<Shelf> shelvesList = q.getResultList();
		Set<Shelf> shelvesSet = new HashSet<>();
		shelvesSet.addAll(shelvesList);
		s.close();
		return shelvesSet;
	}
	
	@Override
	public Set<Shelf> getUserShelves(User loggedUser) {
		Session s = hu.getSession();
		String query = "FROM Shelf";
		Query<Shelf> q = s.createQuery(query, Shelf.class);
		List<Shelf> shelvesList = q.getResultList();
		Set<Shelf> shelvesSet = new HashSet<>();
		shelvesSet.addAll(shelvesList);
		s.close();
		return shelvesSet;
	}

	@Override
	public Set<Shelf> getShelves() {
		// TODO Auto-generated method stub
		return null;
	}
}
