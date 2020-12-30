package dev.shrews.data;

import java.time.LocalDate;
import java.util.ArrayList;
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
	public ArrayList<Object[]> getByTagAndAvgRatingAndNumberOfRatings(String tagName, Long minRatings, Double minAvgRating);
	public ArrayList<Object[]> getByTagAndNotTagAndAvgRatingAndNumberOfRatings(String tagName, String notTagName, Long minRatings, Double minAvgRating);
	public ArrayList<Object[]> getByTagAndAvgRatingAndNumberOfRatingsWithDateRange(String tagName, Long minRatings, Double minAvgRating,
			LocalDate minDate, LocalDate maxDate);
	public ArrayList<Object[]> getByTagAndNotTagAndAvgRatingAndNumberOfRatingsWithDateRange(String tagName, String notTagName, Long minRatings,
			Double minAvgRating, LocalDate minDate, LocalDate maxDate);
	public ArrayList<Object[]> getByGenreAndTagAndAvgRatingAndNumberOfRatings(Integer gid, String tagName, Long minRatings, Double minAvgRating);
	public ArrayList<Object[]> getByGenreAndTagAndNotTagAndAvgRatingAndNumberOfRatings(Integer gid, String tagName, String notTagName,
			Long minRatings, Double minAvgRating);
	public ArrayList<Object[]> getByGenreAndTagAndAvgRatingAndNumberOfRatingsWithDateRange(Integer gid, String tagName, Long minRatings,
			Double minAvgRating, LocalDate minDate, LocalDate maxDate);
	public ArrayList<Object[]> getByGenreAndTagAndNotTagAndAvgRatingAndNumberOfRatingsWithDateRange(Integer gid, String tagName, String notTagName,
			Long minRatings, Double minAvgRating, LocalDate minDate, LocalDate maxDate);
	public ArrayList<Object[]> getByGenreAndAvgRatingAndNumberOfRatingsWithDateRange(Integer gid, Long minRatings,
			Double minAvgRating, LocalDate minDate, LocalDate maxDate);
	public ArrayList<Object[]> getByGenreAndAvgRatingAndNumberOfRatings(Integer gid, Long minRatings, Double minAvgRating);
	public Media getByTitle(String title);
	public Set<Media> getByAuthor(String author);
	public Set<Media> getLikeTitle(String likeTitle);
	//update
	public void update(Media m);
	//delete
	public void delete(Media m);
}
