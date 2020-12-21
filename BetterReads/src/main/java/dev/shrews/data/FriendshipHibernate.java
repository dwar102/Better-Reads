package dev.shrews.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import dev.shrews.beans.Friendships;
import dev.shrews.utils.HibernateUtil;

public class FriendshipHibernate implements FriendshipDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Friendships addFriendship(Friendships f) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(f);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return f;
	}

	@Override
	public Friendships getById(Integer id) {
		Session s = hu.getSession();
		Friendships f = s.get(Friendships.class, id);
		s.close();
		return f;
	}

	@Override
	public Set<Friendships> getAll() {
		Session s = hu.getSession();
		String query = "FROM Friendships";
		Query<Friendships> q = s.createQuery(query, Friendships.class);
		List<Friendships> genreList = q.getResultList();
		Set<Friendships> genreSet = new HashSet<>();
		genreSet.addAll(genreList);
		s.close();
		return genreSet;
	}

	@Override
	public void update(Friendships f) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(f);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(Friendships f) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(f);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

}
