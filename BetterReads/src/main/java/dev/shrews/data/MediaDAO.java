package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.Media;

public interface MediaDAO extends GenericDAO<Media>{
	
	//create
	public Media addMedia(Media m);
	//read 
	public Media getById(Integer id);
	public Set<Media> getAll();
	//update
	public void update(Media m);
	//delete
	public void delete(Media m);

}
