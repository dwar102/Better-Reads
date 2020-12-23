package dev.shrews.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dev.shrews.beans.Media;
import dev.shrews.utils.HibernateUtil;

@Repository
public class MediaHibernate implements MediaDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Media addMedia(Media m) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(m);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return m;
	}

	@Override
	public Media getById(Integer id) {
		Session s = hu.getSession();
		Media m = s.get(Media.class, id);
		s.close();
		return m;
	}

	@Override
	public Set<Media> getAll() {
		Session s = hu.getSession();
		String query = "FROM Media";
		Query<Media> q = s.createQuery(query, Media.class);
		List<Media> genreList = q.getResultList();
		Set<Media> genreSet = new HashSet<>();
		genreSet.addAll(genreList);
		s.close();
		return genreSet;
	}

	@Override
	public void update(Media m) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(m);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(Media m) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(m);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

}
