package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Media;

public interface MediaService {

	//create
	public Media addMedia(Media m);
	//read
	public Media getByMediaId(Integer id);
	public Set<Media> getAll();
	//update
	void updateMedia(Media m);
	//delete
	void deleteMedia(Media m);
	
}
