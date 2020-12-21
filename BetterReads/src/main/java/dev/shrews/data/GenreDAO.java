package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.Genre;

public interface GenreDAO extends GenericDAO<Genre>{
	
	//create
	public Genre addGenre(Genre g);
	//read 
	public Genre getById(Integer id);
	public Set<Genre> getAll();
	//update
	public void update(Genre g);
	//delete
	public void delete(Genre g);

}
