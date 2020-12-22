package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.Friendships;

public interface FriendshipDAO extends GenericDAO<Friendships>{
	
	//create
	public Friendships addFriendship(Friendships f);
	//read 
	public Friendships getById(Integer id);
	public Set<Friendships> getAll();
	public Set<Friendships> getByUserId(Integer id);
	//update
	public void update(Friendships f);
	//delete
	public void delete(Friendships f);

}
