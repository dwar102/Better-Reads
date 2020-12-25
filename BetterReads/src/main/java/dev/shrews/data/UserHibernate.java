package dev.shrews.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dev.shrews.beans.Media;
import dev.shrews.beans.Review;
import dev.shrews.beans.User;
import dev.shrews.beans.User_Media_Comments;
import dev.shrews.beans.User_Review_Comments;
import dev.shrews.exceptions.NonUniqueUsernameException;
import dev.shrews.utils.HibernateUtil;
@Repository
public class UserHibernate implements UserDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public User add(User u) throws NonUniqueUsernameException {
		Session s = hu.getSession();
		Transaction tx = null;
		try { 
			tx = s.beginTransaction();
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
	public User getByUsername(String username) {
		System.out.println("getBy reached" + "" + username);
		
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<User> criteria = cb.createQuery(User.class);
		Root<User> root = criteria.from(User.class);

		Predicate predicateForUsername = cb.equal(root.get("username"), username);
		criteria.select(root).where(predicateForUsername);
		User u = s.createQuery(criteria).getSingleResult();
		System.out.println(u);
		return u;
		
	}

	@Override
	public User getUserById(Integer id) {
		Session s = hu.getSession();
		User u = s.get(User.class, id);
		s.close();
		return u;
	}

	@Override
	public void delete(User u) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(u);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}

	}

	@Override
	public Set<User> getUsers() {
		Session s = hu.getSession();
		String query = "FROM User";
		Query<User> q = s.createQuery(query, User.class);
		List<User> userList = q.getResultList();
		Set<User> userSet = new HashSet<>();
		userSet.addAll(userList);
		s.close();
		return userSet;
	}

	@Override
	public User_Media_Comments placeCommentForMedia(User_Media_Comments c) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(c);
			tx.commit();
		} catch(Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return c;
	}

	@Override
	public User_Review_Comments placeCommentForReview(User_Review_Comments c) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(c);
			tx.commit();
		} catch(Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return c;
	}

	@Override
	public Set<User_Media_Comments> getCommentsForMedia(Integer id) { // receive media.getId() at controller; db stores serial
		Session s = hu.getSession();
		System.out.println("getcomments reached");
		String query = "FROM User_Media_Comments where media_id = :id";
		Query<User_Media_Comments> q = s.createQuery(query, User_Media_Comments.class);
		q.setParameter("id", id);
		List<User_Media_Comments> approvalList = q.getResultList();
		Set<User_Media_Comments> mCommentSet = new HashSet<>();
		mCommentSet.addAll(approvalList);
		s.close();
		return mCommentSet;
	}

	@Override
	public Set<User_Review_Comments> getCommentsForReview(Integer id) { // Needs to receive review.getId() from controller
		Session s = hu.getSession();
		String query = "FROM User_Review_Comments where review_id = :id";
		Query<User_Review_Comments> q = s.createQuery(query, User_Review_Comments.class);
		q.setParameter("id", id);
		List<User_Review_Comments> approvalList = q.getResultList();
		Set<User_Review_Comments> rCommentSet = new HashSet<>();
		rCommentSet.addAll(approvalList);
		s.close();
		return rCommentSet;
	}

@Override
public void delete(User_Review_Comments c) {
	Session s = hu.getSession();
	Transaction tx = null;
	try {
		tx = s.beginTransaction();
		s.delete(c);
		tx.commit();
	} catch (Exception e) {
		if (tx != null)
			tx.rollback();
	} finally {
		s.close();
	}
}
public void delete(User_Media_Comments c) {
	Session s = hu.getSession();
	Transaction tx = null;
	try {
		tx = s.beginTransaction();
		s.delete(c);
		tx.commit();
	} catch (Exception e) {
		if (tx != null)
			tx.rollback();
	} finally {
		s.close();
	}
}
}