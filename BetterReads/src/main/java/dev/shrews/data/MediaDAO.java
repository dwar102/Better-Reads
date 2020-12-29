package dev.shrews.data;

import java.util.List;
import java.util.Set;

import dev.shrews.beans.Media;
import dev.shrews.beans.Review;

public interface MediaDAO extends GenericDAO<Media>{
	
	//create
	public Media addMedia(Media m);
	//read 
	public Media getById(Integer id);
	public Set<Media> getAll();
	public Long getNumRatingsById(Integer id);
	public double getAvgRatingById(Integer id);
	public List<Long> getNumTagsById(Integer id);
	public List<String> getTagnamesById(Integer id);
	public List<Integer> getByTagAndAvgRatingAndNumberOfRatings(String tagName, Long minRatings, Double minAvgRating);
	//update
	public void update(Media m);
	//delete
	public void delete(Media m);

}
