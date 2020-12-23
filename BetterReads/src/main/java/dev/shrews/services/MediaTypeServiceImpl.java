package dev.shrews.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.shrews.beans.MediaType;
import dev.shrews.data.MediaTypeDAO;
import dev.shrews.data.MediaTypeHibernate;

@Service
public class MediaTypeServiceImpl implements MediaTypeService{

	private MediaTypeDAO mediaTypeDao;
	
	@Autowired
	public MediaTypeServiceImpl(MediaTypeDAO m) {
		mediaTypeDao = m;
	}
	
	@Override
	public Integer addMediaType(MediaType mt) {
		return mediaTypeDao.addMediaType(mt).getId();
	}

	@Override
	public MediaType getById(Integer id) {
		return mediaTypeDao.getById(id);
	}

	@Override
	public Set<MediaType> getAll() {
		return mediaTypeDao.getAll();
	}

	@Override
	public void update(MediaType mt) {
		mediaTypeDao.update(mt);
	}

	@Override
	public void delete(MediaType mt) {
		mediaTypeDao.delete(mt);
	}

}
