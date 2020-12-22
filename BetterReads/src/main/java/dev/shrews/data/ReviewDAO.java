package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.Review;

public interface ReviewDAO extends GenericDAO<Review>{
	
	//create
	public Review addReview(Review r);
	//read 
	public Review getById(Integer id);
	public Set<Review> getAll();
	public Set<Review> getByMediaId(Integer id);
	public Set<Review> getByUserId(Integer id);
	//update
	public void update(Review r);
	//delete
	public void delete(Review r);

}
