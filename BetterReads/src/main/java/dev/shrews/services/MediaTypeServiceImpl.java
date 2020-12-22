package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.MediaType;
import dev.shrews.data.MediaTypeDAO;
import dev.shrews.data.MediaTypeHibernate;

public class MediaTypeServiceImpl implements MediaTypeService{

	private MediaTypeDAO mediaTypeDao;
	
	@Override
	public Integer addMediaType(MediaType mt) {
		mediaTypeDao = new MediaTypeHibernate();
		return mediaTypeDao.addMediaType(mt).getId();
	}

	@Override
	public MediaType getById(Integer id) {
		mediaTypeDao = new MediaTypeHibernate();
		return mediaTypeDao.getById(id);
	}

	@Override
	public Set<MediaType> getAll() {
		mediaTypeDao = new MediaTypeHibernate();
		return mediaTypeDao.getAll();
	}

	@Override
	public void update(MediaType mt) {
		mediaTypeDao = new MediaTypeHibernate();
		mediaTypeDao.update(mt);
	}

	@Override
	public void delete(MediaType mt) {
		mediaTypeDao = new MediaTypeHibernate();
		mediaTypeDao.delete(mt);
	}

}
