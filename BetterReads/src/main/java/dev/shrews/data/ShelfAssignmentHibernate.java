package dev.shrews.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dev.shrews.beans.ShelfAssignment;
import dev.shrews.utils.HibernateUtil;

@Repository
public class ShelfAssignmentHibernate implements ShelfAssignmentDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public ShelfAssignment addShelfAssignment(ShelfAssignment sa) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(sa);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return sa;
	}

	@Override
	public List<ShelfAssignment> getAll() {
		Session s = hu.getSession();
		String query = "FROM ShelfAssignment";
		Query<ShelfAssignment> q = s.createQuery(query, ShelfAssignment.class);
		List<ShelfAssignment> shelfAssignmentList = q.getResultList();
		s.close();
		return shelfAssignmentList;
	}

	@Override
	public void update(ShelfAssignment sa) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(sa);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(ShelfAssignment sa) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(sa);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public ShelfAssignment getByShelfAssignmentId(Integer id) {
		Session s = hu.getSession();
		ShelfAssignment sa = s.get(ShelfAssignment.class, id);
		s.close();
		return sa;
	}

	@Override
	public List<ShelfAssignment> getByShelfId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM ShelfAssignment WHERE shelf.id = :id";
		Query<ShelfAssignment> q = s.createQuery(query, ShelfAssignment.class);
		q.setParameter("id", id);
		List<ShelfAssignment> shelfAssignmentList = q.getResultList();
		s.close();
		return shelfAssignmentList;
	}

}
