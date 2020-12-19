package dev.shrews.data;

import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dev.shrews.beans.User;
import dev.shrews.exceptions.NonUniqueUsernameException;
import dev.shrews.utils.HibernateUtil;

public class UserHibernate implements UserDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public User add(User u) throws NonUniqueUsernameException {
		Session s = hu.getSession();
		Transaction tx = null;
		try { tx = s.beginTransaction();
			s.save(u);
			tx.commit();
	} catch (Exception e) {
		if (tx != null)
			tx.rollback();
	} finally {
		s.close();
	}
		return u;
	}

	@Override
	public User getByUsrname(String username) {
		System.out.println("getBy reached" + "" + username);
		
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		Predicate predicateForUsername = cb.equal(root.get("usrname"), username);
		criteria.select(root).where(predicateForUsername);
		User u = s.createQuery(criteria).getSingleResult();
		System.out.println(u);
		return u;
		
	}


}
