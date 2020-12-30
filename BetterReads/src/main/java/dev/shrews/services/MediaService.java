package dev.shrews.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import dev.shrews.beans.Media;

public interface MediaService {

	//create
	public Media addMedia(Media m);
	//read
	public Media getByMediaId(Integer id);
	public Long getNumRatingsById(Integer id);
	public double getAvgRatingById(Integer id);
	public List<String> getTagnamesById(Integer id);
	public List<Long> getTagCountById(Integer id);
	public Set<Media> getAll();
	public Media[] getByTagnameAvgRatingNumRating(String tagName, Long minRating, Double minAvgRating);
	public Media[] getByTagnAndNotTagAndAvgRatingAndNumRating(String tagName, String notTagName, Long minRating, Double minAvgRating);
	public Media[] getByTagnAndNotTagAndAvgRatingAndNumRatingWithDates(String tagName, String notTagName, Long minRating,
			Double minAvgRating, LocalDate minDate, LocalDate maxDate);
	public Media[] getByTagnameAvgRatingNumRatingWithDates(String tagName, Long minRating, Double minAvgRating,
			LocalDate minDate, LocalDate maxDate);
	public Media[] getByGenreTagnameAvgRatingNumRating(Integer gid, String tagName, Long minRating, Double minAvgRating);
	public Media[] getByGenreTagnAndNotTagAndAvgRatingAndNumRating(Integer gid, String tagName, String notTagName, Long minRating, Double minAvgRating);
	public Media[] getByGenreTagnAndNotTagAndAvgRatingAndNumRatingWithDates(Integer gid, String tagName, String notTagName, Long minRating,
			Double minAvgRating, LocalDate minDate, LocalDate maxDate);
	public Media[] getByGenreTagnameAvgRatingNumRatingWithDates(Integer gid, String tagName, Long minRating, Double minAvgRating,
			LocalDate minDate, LocalDate maxDate);
	public Media[] getByGenreAvgRatingNumRating(Integer gid, Long minRating, Double minAvgRating);
	public Media[] getByGenreAvgRatingNumRatingWithDates(Integer gid, Long minRating, Double minAvgRating,
			LocalDate minDate, LocalDate maxDate);
	
	//update
	void updateMedia(Media m);
	//delete
	void deleteMedia(Media m);
	public Set<Media> getSearch(String searchType, String searchContent);
	
}
