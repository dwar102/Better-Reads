package dev.shrews.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Set<Media> getSearch(String searchType, String searchContent) {
		if (searchType.equals("title")) {
			System.out.println("Checking for title...");
			Media m = mediaDao.getByTitle(searchContent);
			if (m == null)
				return null;
			Set<Media> titleSet = new HashSet<Media>();
			titleSet.add(m);
			return titleSet;
		} else {
			System.out.println("Checking for author...");
			return mediaDao.getByAuthor(searchContent);
		}
	}

}
