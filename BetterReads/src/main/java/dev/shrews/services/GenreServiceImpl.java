package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Genre;
import dev.shrews.data.GenreDAO;
import dev.shrews.data.GenreHibernate;

public class GenreServiceImpl implements GenreService{

	private GenreDAO genreDao;
	
	@Override
	public Integer add(Genre g) {
		genreDao = new GenreHibernate();
		return genreDao.addGenre(g).getId();
	}

	@Override
	public Genre getByGenreId(Integer id) {
		genreDao = new GenreHibernate();
		return genreDao.getById(id);
	}

	@Override
	public Set<Genre> getAll() {
		genreDao = new GenreHibernate();
		return genreDao.getAll();
	}

	@Override
	public void update(Genre g) {
		genreDao = new GenreHibernate();
		genreDao.update(g);
	}

	@Override
	public void delete(Genre g) {
		genreDao = new GenreHibernate();
		genreDao.delete(g);
	}

}
