package dev.shrews.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dev.shrews.beans.Friendships;
import dev.shrews.utils.HibernateUtil;

@Repository
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
		List<Friendships> friendList = q.getResultList();
		Set<Friendships> friendSet = new HashSet<>();
		friendSet.addAll(friendList);
		s.close();
		return friendSet;
	}

	@Override
	public Set<Friendships> getByUserId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM Friendships where user_id.id= :id";
		System.out.println(query);
		Query<Friendships> q = s.createQuery(query, Friendships.class);
		//System.out.println(q);
		q.setParameter("id",  id);
		List<Friendships> friendList = q.getResultList();
		Set<Friendships> friendSet = new HashSet<>();
		friendSet.addAll(friendList);
		s.close();
		return friendSet;
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
