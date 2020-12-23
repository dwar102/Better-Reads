package dev.shrews.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dev.shrews.beans.UserTag;
import dev.shrews.utils.HibernateUtil;

@Repository
public class UserTagHibernate implements UserTagDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public UserTag addUserTag(UserTag ut) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(ut);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return ut;
	}

	@Override
	public Set<UserTag> getAll() {
		Session s = hu.getSession();
		String query = "FROM UserTag";
		Query<UserTag> q = s.createQuery(query, UserTag.class);
		List<UserTag> userTagList = q.getResultList();
		Set<UserTag> userTagSet = new HashSet<>();
		userTagSet.addAll(userTagList);
		s.close();
		return userTagSet;
	}

	@Override
	public void update(UserTag ut) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(ut);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(UserTag ut) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(ut);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public UserTag getByUserTagId(Integer id) {
		Session s = hu.getSession();
		UserTag ut = s.get(UserTag.class, id);
		s.close();
		return ut;
	}

	@Override
	public Set<UserTag> getByMediaId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM UserTag WHERE media.id = :id";
		Query<UserTag> q = s.createQuery(query, UserTag.class);
		q.setParameter("id", id);
		List<UserTag> userTagList = q.getResultList();
		Set<UserTag> userTagSet = new HashSet<>();
		userTagSet.addAll(userTagList);
		s.close();
		return userTagSet;
	}

}
