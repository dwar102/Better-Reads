package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Review;

public interface ReviewService {

	//create
	public Integer addReview(Review r);
	//read
	public Review getById(Integer id);
	public Set<Review> getAll();
	public Set<Review> getByMediaId(Integer id);
	public Set<Review> getByUserId(Integer id);
	//update
	void updateReview(Review r);
	//delete
	void deleteReview(Review r);
	
}
