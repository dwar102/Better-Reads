package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Media;
import dev.shrews.data.MediaDAO;
import dev.shrews.data.MediaHibernate;

public class MediaServiceImpl implements MediaService{
	
	private MediaDAO mediaDao;

	@Override
	public Integer addMedia(Media m) {
		mediaDao = new MediaHibernate();
		return mediaDao.addMedia(m).getId();
	}

	@Override
	public Media getByMediaId(Integer id) {
		mediaDao = new MediaHibernate();
		return mediaDao.getById(id);
	}

	@Override
	public Set<Media> getAll() {
		mediaDao = new MediaHibernate();
		return mediaDao.getAll();
	}

	@Override
	public void updateMedia(Media m) {
		mediaDao = new MediaHibernate();
		mediaDao.update(m);
	}

	@Override
	public void deleteMedia(Media m) {
		mediaDao = new MediaHibernate();
		mediaDao.delete(m);
	}

}
