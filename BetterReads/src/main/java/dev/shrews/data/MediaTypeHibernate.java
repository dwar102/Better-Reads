package dev.shrews.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dev.shrews.beans.MediaType;
import dev.shrews.utils.HibernateUtil;

@Repository
public class MediaTypeHibernate implements MediaTypeDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public MediaType addMediaType(MediaType mt) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(mt);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return mt;
	}

	@Override
	public MediaType getById(Integer id) {
		Session s = hu.getSession();
		MediaType mt = s.get(MediaType.class, id);
		s.close();
		return mt;
	}

	@Override
	public Set<MediaType> getAll() {
		Session s = hu.getSession();
		String query = "FROM MediaType";
		Query<MediaType> q = s.createQuery(query, MediaType.class);
		List<MediaType> genreList = q.getResultList();
		Set<MediaType> genreSet = new HashSet<>();
		genreSet.addAll(genreList);
		s.close();
		return genreSet;
	}

	@Override
	public void update(MediaType mt) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(mt);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(MediaType mt) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(mt);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

}
