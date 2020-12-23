package dev.shrews.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.shrews.beans.Genre;
import dev.shrews.data.GenreDAO;
import dev.shrews.data.GenreHibernate;

@Service
public class GenreServiceImpl implements GenreService{

	private GenreDAO genreDao;
	
	@Autowired
	public GenreServiceImpl(GenreDAO g) {
		genreDao = g;
	}
	
	@Override
	public Integer add(Genre g) {
		return genreDao.addGenre(g).getId();
	}

	@Override
	public Genre getByGenreId(Integer id) {
		return genreDao.getById(id);
	}

	@Override
	public Set<Genre> getAll() {
		return genreDao.getAll();
	}

	@Override
	public void update(Genre g) {
		genreDao.update(g);
	}

	@Override
	public void delete(Genre g) {
		genreDao.delete(g);
	}

}
