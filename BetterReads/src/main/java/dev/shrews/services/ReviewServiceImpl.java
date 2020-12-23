package dev.shrews.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.shrews.beans.Review;
import dev.shrews.data.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService{
	
	private ReviewDAO reviewDao;

	@Autowired
	public ReviewServiceImpl(ReviewDAO r) {
		reviewDao = r;
	}
	
	@Override
	public Integer addReview(Review r) {
		return reviewDao.addReview(r).getId();
	}

	@Override
	public Review getById(Integer id) {
		return reviewDao.getById(id);
	}

	@Override
	public Set<Review> getAll() {
		return reviewDao.getAll();
	}

	@Override
	public Set<Review> getByMediaId(Integer id) {
		return reviewDao.getByMediaId(id);
	}

	@Override
	public Set<Review> getByUserId(Integer id) {
		return reviewDao.getByUserId(id);
	}

	@Override
	public void updateReview(Review r) {
		reviewDao.update(r);
	}

	@Override
	public void deleteReview(Review r) {
		reviewDao.delete(r);
	}

}
