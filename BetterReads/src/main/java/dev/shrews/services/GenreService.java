package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Genre;

public interface GenreService {

	//create
	public Integer add(Genre g);
	//read
	public Genre getByGenreId(Integer id);
	public Set<Genre> getAll();
	//update
	void update(Genre g);
	//delete
	void delete(Genre g);
	
}
