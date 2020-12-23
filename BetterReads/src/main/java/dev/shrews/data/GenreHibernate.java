package dev.shrews.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dev.shrews.beans.Genre;
import dev.shrews.utils.HibernateUtil;

@Repository
public class GenreHibernate implements GenreDAO{
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();

	@Override
	public Genre addGenre(Genre g) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(g);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return g;
	}

	@Override
	public Genre getById(Integer id) {
		Session s = hu.getSession();
		Genre g = s.get(Genre.class, id);
		s.close();
		return g;
	}

	@Override
	public Set<Genre> getAll() {
		Session s = hu.getSession();
		String query = "FROM Genre";
		Query<Genre> q = s.createQuery(query, Genre.class);
		List<Genre> genreList = q.getResultList();
		Set<Genre> genreSet = new HashSet<>();
		genreSet.addAll(genreList);
		s.close();
		return genreSet;
	}

	@Override
	public void update(Genre g) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(g);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	@Override
	public void delete(Genre g) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(g);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

}
