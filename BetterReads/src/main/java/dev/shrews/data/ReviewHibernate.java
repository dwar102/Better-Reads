package dev.shrews.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dev.shrews.beans.Review;
import dev.shrews.utils.HibernateUtil;

@Repository
public class ReviewHibernate implements ReviewDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Review addReview(Review r) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(r);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return r;
	}

	@Override
	public Review getById(Integer id) {
		Session s = hu.getSession();
		Review r = s.get(Review.class, id);
		s.close();
		return r;
	}

	@Override
	public Set<Review> getAll() {
		Session s = hu.getSession();
		String query = "FROM Review";
		Query<Review> q = s.createQuery(query, Review.class);
		List<Review> reviewList = q.getResultList();
		Set<Review> reviewSet = new HashSet<>();
		reviewSet.addAll(reviewList);
		s.close();
		return reviewSet;
	}

	@Override
	public Set<Review> getByMediaId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM Review where media.id= :id";
		Query<Review> q = s.createQuery(query, Review.class);
		q.setParameter("id",  id);
		List<Review> reviewList = q.getResultList();
		Set<Review> reviewSet = new HashSet<>();
		reviewSet.addAll(reviewList);
		s.close();
		return reviewSet;
	}

	@Override
	public Set<Review> getByUserId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM Review where user.id= :id";
		Query<Review> q = s.createQuery(query, Review.class);
		q.setParameter("id",  id);
		List<Review> reviewList = q.getResultList();
		Set<Review> reviewSet = new HashSet<>();
		reviewSet.addAll(reviewList);
		s.close();
		return reviewSet;
	}

	@Override
	public void update(Review r) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(r);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(Review r) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(r);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

}
