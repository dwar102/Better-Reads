package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.MediaType;

public interface MediaTypeDAO extends GenericDAO<MediaType>{
	
	//create
	public MediaType addMediaType(MediaType mt);
	//read 
	public MediaType getById(Integer id);
	public Set<MediaType> getAll();
	//update
	public void update(MediaType mt);
	//delete
	public void delete(MediaType mt);

}
