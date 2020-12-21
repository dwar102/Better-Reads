package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.UserTag;

public interface UserTagDAO extends GenericDAO<UserTag>{
	
	//create
	public UserTag addUserTag(UserTag ut);
	//read 
	public UserTag getByUserTagId(Integer id);
	public Set<UserTag> getByMediaId(Integer id);
	public Set<UserTag> getAll();
	//update
	public void update(UserTag ut);
	//delete
	public void delete(UserTag ut);

}
