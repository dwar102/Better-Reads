package dev.shrews.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dev.shrews.beans.Media;
import dev.shrews.beans.Review;
import dev.shrews.beans.User;
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
	public Media getByTitle(String title) {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<Media> criteria = cb.createQuery(Media.class);
		Root<Media> root = criteria.from(Media.class);
		
		Predicate predicateForTitle = cb.equal(root.get("title"), title);
		criteria.select(root).where(predicateForTitle);
		Media m = s.createQuery(criteria).getSingleResult();
		s.close();
		return m;
	}
	
	@Override
	public Set<Media> getLikeTitle(String likeTitle) {
		Session s = hu.getSession();
		String query = "FROM Media WHERE title like :likeTitle";
		Query<Media> q = s.createQuery(query, Media.class);
		q.setParameter("likeTitle", "%"+likeTitle+"%");
		List<Media> resultList = q.getResultList();
		Set<Media> resultSet = new HashSet<>();
		resultSet.addAll(resultList);
		s.close();
		return resultSet;
	}
	
	@Override
	public Set<Media> getByAuthor(String author) {
		Session s = hu.getSession();
		String query = "FROM Media WHERE creator = :author";
		Query<Media> q = s.createQuery(query, Media.class);
		q.setParameter("author", author);
		List<Media> resultList = q.getResultList();
		Set<Media> resultSet = new HashSet<>();
		resultSet.addAll(resultList);
		s.close();
		return resultSet;
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

	@Override
	public Long getNumRatingsById(Integer id) {
		Session s = hu.getSession();
		String query = "select count(rating) FROM Review where media_id = :id";
		Query q = s.createQuery(query);
		q.setParameter("id",  id);
		Long numRatings = (Long) q.getSingleResult();
		s.close();
		return numRatings;
	}

	@Override
	public double getAvgRatingById(Integer id) {
		Session s = hu.getSession();
		String query = "select avg(rating) FROM Review where media_id = :id";
		Query q = s.createQuery(query);
		q.setParameter("id",  id);
		try {
			double numRatings = (double) q.getSingleResult();
			s.close();
			return numRatings;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return (Double) null;
		}
	}

	@Override
	public List<Long> getNumTagsById(Integer id) {
		Session s = hu.getSession();
		String query = "select count(tag_name) FROM Media m left join UserTag ut on m.id = ut.media.id where m.id = :id "
					 + " group by tag_name order by tag_name";
		
		Query q = s.createQuery(query);
		q.setParameter("id",  id);
		List<Long> tagList = q.getResultList();
		return tagList;
	}

	@Override
	public List<String> getTagnamesById(Integer id) {
		Session s = hu.getSession();
		String query = "select distinct tagName FROM UserTag where media.id = :id "
					 + " order by tag_name";
		Query<String> q = s.createQuery(query, String.class);
		q.setParameter("id",  id);
		List<String> tagList = q.getResultList();
		return tagList;
	}

	@Override
	public ArrayList<Object[]> getByTagAndAvgRatingAndNumberOfRatings(String tagName, Long minRatings,
			Double minAvgRating) {
		Session s = hu.getSession();
		String query = 	 " select distinct m.id, count(distinct r.rating), avg(r.rating), m.title FROM Media m "
						+" join UserTag ut on ut.media.id = m.id "
						+" join Genre g on g.id = m.genre.id "
						+" join Review r on r.media.id = m.id "
						+" where ut.tagName = :tagName"
						+" group by m.id"
						+" having count(distinct r.id) >= :minRatings"
						+" and avg(r.rating) >= :minAvgRating"
						+" order by avg(r.rating) desc"
						+" ";
		Query<Object[]> q = s.createQuery(query, Object[].class);
		q.setParameter("tagName",  tagName);
		q.setParameter("minRatings",  minRatings);
		q.setParameter("minAvgRating",  minAvgRating);
		System.out.println(q.getResultList());
		ArrayList<Object[]> list = (ArrayList<Object[]>) q.getResultList();
		return list;
	}

	@Override
	public ArrayList<Object[]> getByTagAndAvgRatingAndNumberOfRatingsWithDateRange(String tagName, Long minRatings,
			Double minAvgRating, LocalDate minDate, LocalDate maxDate) {
		Session s = hu.getSession();
		String query = 	 " select distinct m.id, avg(r.rating) FROM Media m "
						+" join UserTag ut on ut.media.id = m.id "
						+" join Genre g on g.id = m.genre.id "
						+" join Review r on r.media.id = m.id "
						+" where ut.tagName = :tagName"
						+" and m.date >= :minDate"
						+" and m.date <= :maxDate"
						+" group by m.id"
						+" having count(distinct r.id) >= :minRatings"
						+" and avg(r.rating) >= :minAvgRating"
						+" order by avg(r.rating) desc"
						+" ";
		Query<Object[]> q = s.createQuery(query, Object[].class);
		q.setParameter("tagName",  tagName);
		q.setParameter("minDate",  minDate);
		q.setParameter("maxDate",  maxDate);
		q.setParameter("minRatings",  minRatings);
		q.setParameter("minAvgRating",  minAvgRating);
		//System.out.println(q.getResultList());
		ArrayList<Object[]> list = (ArrayList<Object[]>) q.getResultList();
		return list;
	}

	@Override
	public ArrayList<Object[]> getByGenreAndTagAndAvgRatingAndNumberOfRatings(Integer gid, String tagName,
			Long minRatings, Double minAvgRating) {
		Session s = hu.getSession();
		String query = 	 " select distinct m.id, avg(r.rating) FROM Media m "
						+" join UserTag ut on ut.media.id = m.id "
						+" join Genre g on g.id = m.genre.id "
						+" join Review r on r.media.id = m.id "
						+" where ut.tagName = :tagName"
						+" and g.id = :gid"
						+" group by m.id"
						+" having count(distinct r.id) >= :minRatings"
						+" and avg(r.rating) >= :minAvgRating"
						+" order by avg(r.rating) desc"
						+" ";
		Query<Object[]> q = s.createQuery(query, Object[].class);
		q.setParameter("tagName",  tagName);
		q.setParameter("gid", gid);
		q.setParameter("minRatings",  minRatings);
		q.setParameter("minAvgRating",  minAvgRating);
		//System.out.println(q.getResultList());
		ArrayList<Object[]> list = (ArrayList<Object[]>) q.getResultList();
		return list;
	}

	@Override
	public ArrayList<Object[]> getByGenreAndTagAndAvgRatingAndNumberOfRatingsWithDateRange(Integer gid, String tagName,
			Long minRatings, Double minAvgRating, LocalDate minDate, LocalDate maxDate) {
		Session s = hu.getSession();
		String query = 	 " select distinct m.id, avg(r.rating) FROM Media m "
						+" join UserTag ut on ut.media.id = m.id "
						+" join Genre g on g.id = m.genre.id "
						+" join Review r on r.media.id = m.id "
						+" where ut.tagName = :tagName"
						+" and g.id = :gid"
						+" and m.date >= :minDate"
						+" and m.date <= :maxDate"
						+" group by m.id"
						+" having count(distinct r.id) >= :minRatings"
						+" and avg(r.rating) >= :minAvgRating"
						+" order by avg(r.rating) desc"
						+" ";
		Query<Object[]> q = s.createQuery(query, Object[].class);
		q.setParameter("tagName",  tagName);
		q.setParameter("gid", gid);
		q.setParameter("minDate",  minDate);
		q.setParameter("maxDate",  maxDate);
		q.setParameter("minRatings",  minRatings);
		q.setParameter("minAvgRating",  minAvgRating);
		//System.out.println(q.getResultList());
		ArrayList<Object[]> list = (ArrayList<Object[]>) q.getResultList();
		return list;
	}

	@Override
	public ArrayList<Object[]> getByTagAndNotTagAndAvgRatingAndNumberOfRatings(String tagName, String notTagName,
			Long minRatings, Double minAvgRating) {
		Session s = hu.getSession();
		String query = 	 " select distinct m.id, avg(r.rating) FROM Media m "
						+" join UserTag ut on ut.media.id = m.id "
						+" join Genre g on g.id = m.genre.id "
						+" join Review r on r.media.id = m.id "
						+" where m.id not in (select m2.id from Media m2 join UserTag ut2 on ut2.media.id = m2.id where ut2.tagName = :notTagName)"
						+" and ut.tagName = :tagName"
						+" group by m.id"
						+" having count(distinct r.id) >= :minRatings"
						+" and avg(r.rating) >= :minAvgRating"
						+" order by avg(r.rating) desc"
						+" ";
		Query<Object[]> q = s.createQuery(query, Object[].class);
		q.setParameter("tagName",  tagName);
		q.setParameter("notTagName",  notTagName);
		q.setParameter("minRatings",  minRatings);
		q.setParameter("minAvgRating",  minAvgRating);

		ArrayList<Object[]> list = (ArrayList<Object[]>) q.getResultList();
		return list;
	}

	@Override
	public ArrayList<Object[]> getByTagAndNotTagAndAvgRatingAndNumberOfRatingsWithDateRange(String tagName,
			String notTagName, Long minRatings, Double minAvgRating, LocalDate minDate, LocalDate maxDate) {
		Session s = hu.getSession();
		String query = 	 " select distinct m.id, avg(r.rating) FROM Media m "
						+" join UserTag ut on ut.media.id = m.id "
						+" join Genre g on g.id = m.genre.id "
						+" join Review r on r.media.id = m.id "
						+" where m.id not in (select m2.id from Media m2 join UserTag ut2 on ut2.media.id = m2.id where ut2.tagName = :notTagName)"
						+" and ut.tagName = :tagName"
						+" and m.date >= :minDate"
						+" and m.date <= :maxDate"
						+" group by m.id"
						+" having count(distinct r.id) > :minRatings"
						+" and avg(r.rating) > :minAvgRating"
						+" order by avg(r.rating) desc"
						+" ";
		Query<Object[]> q = s.createQuery(query, Object[].class);
		q.setParameter("tagName",  tagName);
		q.setParameter("notTagName",  notTagName);
		q.setParameter("minDate",  minDate);
		q.setParameter("maxDate",  maxDate);
		q.setParameter("minRatings",  minRatings);
		q.setParameter("minAvgRating",  minAvgRating);

		ArrayList<Object[]> list = (ArrayList<Object[]>) q.getResultList();
		return list;
	}

	@Override
	public ArrayList<Object[]> getByGenreAndTagAndNotTagAndAvgRatingAndNumberOfRatings(Integer gid, String tagName,
			String notTagName, Long minRatings, Double minAvgRating) {
		Session s = hu.getSession();
		String query = 	 " select distinct m.id, avg(r.rating) FROM Media m "
						+" join UserTag ut on ut.media.id = m.id "
						+" join Genre g on g.id = m.genre.id "
						+" join Review r on r.media.id = m.id "
						+" where m.id not in (select m2.id from Media m2 join UserTag ut2 on ut2.media.id = m2.id where ut2.tagName = :notTagName)"
						+" and ut.tagName = :tagName"
						+" and g.id = :gid"
						+" group by m.id"
						+" having count(distinct r.id) >= :minRatings"
						+" and avg(r.rating) >= :minAvgRating"
						+" order by avg(r.rating) desc"
						+" ";
		Query<Object[]> q = s.createQuery(query, Object[].class);
		q.setParameter("gid", gid);
		q.setParameter("tagName",  tagName);
		q.setParameter("notTagName",  notTagName);
		q.setParameter("minRatings",  minRatings);
		q.setParameter("minAvgRating",  minAvgRating);

		ArrayList<Object[]> list = (ArrayList<Object[]>) q.getResultList();
		return list;
	}

	@Override
	public ArrayList<Object[]> getByGenreAndTagAndNotTagAndAvgRatingAndNumberOfRatingsWithDateRange(Integer gid,
			String tagName, String notTagName, Long minRatings, Double minAvgRating, LocalDate minDate,
			LocalDate maxDate) {
		Session s = hu.getSession();
		String query = 	 " select distinct m.id, avg(r.rating) FROM Media m "
						+" join UserTag ut on ut.media.id = m.id "
						+" join Genre g on g.id = m.genre.id "
						+" join Review r on r.media.id = m.id "
						+" where m.id not in (select m2.id from Media m2 join UserTag ut2 on ut2.media.id = m2.id where ut2.tagName = :notTagName)"
						+" and ut.tagName = :tagName"
						+" and m.date >= :minDate"
						+" and m.date <= :maxDate"
						+" and g.id = :gid"
						+" group by m.id"
						+" having count(distinct r.id) >= :minRatings"
						+" and avg(r.rating) >= :minAvgRating"
						+" order by avg(r.rating) desc"
						+" ";
		Query<Object[]> q = s.createQuery(query, Object[].class);
		q.setParameter("gid", gid);
		q.setParameter("tagName",  tagName);
		q.setParameter("notTagName",  notTagName);
		q.setParameter("minDate",  minDate);
		q.setParameter("maxDate",  maxDate);
		q.setParameter("minRatings",  minRatings);
		q.setParameter("minAvgRating",  minAvgRating);

		ArrayList<Object[]> list = (ArrayList<Object[]>) q.getResultList();
		return list;
	}

	@Override
	public ArrayList<Object[]> getByGenreAndAvgRatingAndNumberOfRatingsWithDateRange(Integer gid, Long minRatings,
			Double minAvgRating, LocalDate minDate, LocalDate maxDate) {
		Session s = hu.getSession();
		String query = 	 " select distinct m.id, avg(r.rating) FROM Media m "
						+" join UserTag ut on ut.media.id = m.id "
						+" join Genre g on g.id = m.genre.id "
						+" join Review r on r.media.id = m.id "
						+" where g.id = :gid"
						+" and m.date >= :minDate"
						+" and m.date <= :maxDate"
						+" group by m.id"
						+" having count(distinct r.id) >= :minRatings"
						+" and avg(r.rating) >= :minAvgRating"
						+" order by avg(r.rating) desc"
						+" ";
		Query<Object[]> q = s.createQuery(query, Object[].class);
		q.setParameter("gid",  gid);
		q.setParameter("minDate",  minDate);
		q.setParameter("maxDate",  maxDate);
		q.setParameter("minRatings",  minRatings);
		q.setParameter("minAvgRating",  minAvgRating);
		//System.out.println(q.getResultList());
		ArrayList<Object[]> list = (ArrayList<Object[]>) q.getResultList();
		return list;
	}

	@Override
	public ArrayList<Object[]> getByGenreAndAvgRatingAndNumberOfRatings(Integer gid, Long minRatings,
			Double minAvgRating) {
		Session s = hu.getSession();
		String query = 	 " select distinct m.id, avg(r.rating) FROM Media m "
						+" join UserTag ut on ut.media.id = m.id "
						+" join Genre g on g.id = m.genre.id "
						+" join Review r on r.media.id = m.id "
						+" where g.id = :gid"
						+" group by m.id"
						+" having count(distinct r.id) >= :minRatings"
						+" and avg(r.rating) >= :minAvgRating"
						+" order by avg(r.rating) desc"
						+" ";
		Query<Object[]> q = s.createQuery(query, Object[].class);
		q.setParameter("gid",  gid);
		q.setParameter("minRatings",  minRatings);
		q.setParameter("minAvgRating",  minAvgRating);
		System.out.println(q.getResultList());
		ArrayList<Object[]> list = (ArrayList<Object[]>) q.getResultList();
		return list;
	}

	
}
