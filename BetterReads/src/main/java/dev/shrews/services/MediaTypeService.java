package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.MediaType;

public interface MediaTypeService {

	//create
	public Integer addMediaType(MediaType mt);
	//read
	public MediaType getById(Integer id);
	public Set<MediaType> getAll();
	//update
	void update(MediaType mt);
	//delete
	void delete(MediaType mt);
	
}
