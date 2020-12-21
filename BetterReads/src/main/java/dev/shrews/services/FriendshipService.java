package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Friendships;


public interface FriendshipService {
	
	//create
	public Integer addFriendships(Friendships f);
	//read
	public Set<Friendships> getFriendshipsByUserId(Integer id);
	public Friendships getFriendshipsById(Integer id);
	//update
	void updateFriendships(Friendships f);
	//delete
	void deleteFriendships(Friendships f);
}
