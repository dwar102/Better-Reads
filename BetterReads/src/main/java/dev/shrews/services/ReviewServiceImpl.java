package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Review;
import dev.shrews.data.ReviewDAO;
import dev.shrews.data.ReviewHibernate;

public class ReviewServiceImpl implements ReviewService{
	
	private ReviewDAO reviewDao;

	@Override
	public Integer addReview(Review r) {
		reviewDao = new ReviewHibernate();
		return reviewDao.addReview(r).getId();
	}

	@Override
	public Review getById(Integer id) {
		reviewDao = new ReviewHibernate();
		return reviewDao.getById(id);
	}

	@Override
	public Set<Review> getAll() {
		reviewDao = new ReviewHibernate();
		return reviewDao.getAll();
	}

	@Override
	public Set<Review> getByMediaId(Integer id) {
		reviewDao = new ReviewHibernate();
		return reviewDao.getByMediaId(id);
	}

	@Override
	public Set<Review> getByUserId(Integer id) {
		reviewDao = new ReviewHibernate();
		return reviewDao.getByUserId(id);
	}

	@Override
	public void updateReview(Review r) {
		reviewDao = new ReviewHibernate();
		reviewDao.update(r);
	}

	@Override
	public void deleteReview(Review r) {
		reviewDao = new ReviewHibernate();
		reviewDao.delete(r);
	}

}
