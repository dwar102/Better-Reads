package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.UserTag;

public interface UserTagService {

	//create
	public Integer addUserTag(UserTag ut);
	//read
	public UserTag getByUserTagId(Integer id);
	public Set<UserTag> getAll();
	public Set<UserTag> getUserTagsByMediaId(Integer id);
	//update
	//void updateUserTag(UserTag ut);
	//delete
	void deleteUserTag(UserTag ut);
	
}
