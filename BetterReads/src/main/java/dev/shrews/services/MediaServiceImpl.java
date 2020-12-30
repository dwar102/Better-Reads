package dev.shrews.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dev.shrews.beans.Media;
import dev.shrews.data.MediaDAO;
import dev.shrews.data.MediaHibernate;

@Service
public class MediaServiceImpl implements MediaService{
	
	private MediaDAO mediaDao;
	
	@Autowired
	public MediaServiceImpl(MediaDAO m) {
		mediaDao = m;
	}

	@Override
	public Media addMedia(Media m) {
		return mediaDao.addMedia(m);
	}

	@Override
	public Media getByMediaId(Integer id) {
		return mediaDao.getById(id);
	}

	@Override
	public Set<Media> getAll() {
		return mediaDao.getAll();
	}

	@Override
	public void updateMedia(Media m) {
		mediaDao.update(m);
	}

	@Override
	public void deleteMedia(Media m) {
		mediaDao.delete(m);
	}

	@Override
	public Long getNumRatingsById(Integer id) {
		return mediaDao.getNumRatingsById(id);
	}

	@Override
	public double getAvgRatingById(Integer id) {
		return mediaDao.getAvgRatingById(id);
	}

	@Override
	public List<String> getTagnamesById(Integer id) {
		return mediaDao.getTagnamesById(id);
	}

	@Override
	public List<Long> getTagCountById(Integer id) {
		return mediaDao.getNumTagsById(id);
	}

	@Override
	public Media[] getByTagnameAvgRatingNumRating(String tagName, Long minRating, Double minAvgRating) {
		ArrayList<Object[]> oList = mediaDao.getByTagAndAvgRatingAndNumberOfRatings(tagName, minRating, minAvgRating);
		Media[] mList = new Media[oList.size()];
		for(int i = 0; i < oList.size(); i++) {
			mList[i] = mediaDao.getById((Integer) oList.get(i)[0]);
		}
		return mList;
	}

	@Override
	public Media[] getByTagnameAvgRatingNumRatingWithDates(String tagName, Long minRating, Double minAvgRating,
			LocalDate minDate, LocalDate maxDate) {
		ArrayList<Object[]> oList = mediaDao.getByTagAndAvgRatingAndNumberOfRatingsWithDateRange(tagName, minRating, 
				minAvgRating, minDate, maxDate);
		Media[] mList = new Media[oList.size()];
		for(int i = 0; i < oList.size(); i++) {
			mList[i] = mediaDao.getById((Integer) oList.get(i)[0]);
		}
		return mList;
	}

	@Override
	public Media[] getByTagnAndNotTagAndAvgRatingAndNumRating(String tagName, String notTagName, Long minRating, Double minAvgRating) {
		ArrayList<Object[]> oList = mediaDao.getByTagAndNotTagAndAvgRatingAndNumberOfRatings(tagName, notTagName, minRating, minAvgRating);
		Media[] mList = new Media[oList.size()];
		for(int i = 0; i < oList.size(); i++) {
			mList[i] = mediaDao.getById((Integer) oList.get(i)[0]);
		}
		return mList;
	}

	@Override
	public Media[] getByTagnAndNotTagAndAvgRatingAndNumRatingWithDates(String tagName, String notTagName,
			Long minRating, Double minAvgRating, LocalDate minDate, LocalDate maxDate) {
		ArrayList<Object[]> oList = mediaDao.getByTagAndNotTagAndAvgRatingAndNumberOfRatingsWithDateRange(tagName, notTagName, minRating, 
				minAvgRating, minDate, maxDate);
		Media[] mList = new Media[oList.size()];
		for(int i = 0; i < oList.size(); i++) {
			mList[i] = mediaDao.getById((Integer) oList.get(i)[0]);
		}
		return mList;
	}

	@Override
	public Media[] getByGenreTagnameAvgRatingNumRating(Integer gid, String tagName, Long minRating,
			Double minAvgRating) {
		ArrayList<Object[]> oList = mediaDao.getByGenreAndTagAndAvgRatingAndNumberOfRatings(gid, tagName, minRating, minAvgRating);
		Media[] mList = new Media[oList.size()];
		for(int i = 0; i < oList.size(); i++) {
			mList[i] = mediaDao.getById((Integer) oList.get(i)[0]);
		}
		return mList;
	}

	@Override
	public Media[] getByGenreTagnAndNotTagAndAvgRatingAndNumRating(Integer gid, String tagName, String notTagName,
			Long minRating, Double minAvgRating) {
		ArrayList<Object[]> oList = mediaDao.getByGenreAndTagAndNotTagAndAvgRatingAndNumberOfRatings(gid, tagName, notTagName, minRating, minAvgRating);
		Media[] mList = new Media[oList.size()];
		for(int i = 0; i < oList.size(); i++) {
			mList[i] = mediaDao.getById((Integer) oList.get(i)[0]);
		}
		return mList;
	}

	@Override
	public Media[] getByGenreTagnAndNotTagAndAvgRatingAndNumRatingWithDates(Integer gid, String tagName,
			String notTagName, Long minRating, Double minAvgRating, LocalDate minDate, LocalDate maxDate) {
		ArrayList<Object[]> oList = mediaDao.getByGenreAndTagAndNotTagAndAvgRatingAndNumberOfRatingsWithDateRange(gid, 
				tagName, notTagName, minRating,  minAvgRating, minDate, maxDate);
		Media[] mList = new Media[oList.size()];
		for(int i = 0; i < oList.size(); i++) {
			mList[i] = mediaDao.getById((Integer) oList.get(i)[0]);
		}
		return mList;
	}

	@Override
	public Media[] getByGenreTagnameAvgRatingNumRatingWithDates(Integer gid, String tagName, Long minRating,
			Double minAvgRating, LocalDate minDate, LocalDate maxDate) {
		ArrayList<Object[]> oList = mediaDao.getByGenreAndTagAndAvgRatingAndNumberOfRatingsWithDateRange(gid, tagName, minRating, 
				minAvgRating, minDate, maxDate);
		Media[] mList = new Media[oList.size()];
		for(int i = 0; i < oList.size(); i++) {
			mList[i] = mediaDao.getById((Integer) oList.get(i)[0]);
		}
		return mList;
	}

	public Set<Media> getSearch(String searchType, String searchContent) {
		if (searchType.equals("title")) {
			System.out.println("Checking for title...");
			Set<Media> titleSet1 = mediaDao.getLikeTitle(searchContent.toLowerCase());
			Set<Media> titleSet2 = mediaDao.getLikeTitle(StringUtils.capitalize(searchContent));
			
			Set<Media> titleSet = new HashSet<Media>();
			titleSet.addAll(titleSet1);
			titleSet.addAll(titleSet2);
			if (titleSet.size() == 0) {
				return null;
			}
			return titleSet;
		} else {
			System.out.println("Checking for author...");
			return mediaDao.getByAuthor(searchContent);
		}
  
	}

	@Override
	public Media[] getByGenreAvgRatingNumRating(Integer gid, Long minRating, Double minAvgRating) {
		ArrayList<Object[]> oList = mediaDao.getByGenreAndAvgRatingAndNumberOfRatings(gid, minRating, 
				minAvgRating);
		Media[] mList = new Media[oList.size()];
		for(int i = 0; i < oList.size(); i++) {
			mList[i] = mediaDao.getById((Integer) oList.get(i)[0]);
		}
		return mList;
	}

	@Override
	public Media[] getByGenreAvgRatingNumRatingWithDates(Integer gid, Long minRating, Double minAvgRating,
			LocalDate minDate, LocalDate maxDate) {
		ArrayList<Object[]> oList = mediaDao.getByGenreAndAvgRatingAndNumberOfRatingsWithDateRange(gid, minRating, 
				minAvgRating, minDate, maxDate);
		Media[] mList = new Media[oList.size()];
		for(int i = 0; i < oList.size(); i++) {
			mList[i] = mediaDao.getById((Integer) oList.get(i)[0]);
		}
		return mList;
	}

}
