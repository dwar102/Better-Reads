package dev.shrews.services;

import java.util.Set;

import org.eclipse.jetty.server.Authentication.User;

import dev.shrews.beans.Friendships;
import dev.shrews.data.FriendshipDAO;
import dev.shrews.data.FriendshipHibernate;
import dev.shrews.data.UserDAO;
import dev.shrews.data.UserHibernate;

public class FriendshipServiceImpl implements FriendshipService{
	
	private FriendshipDAO friendshipDao;

	@Override
	public Integer addFriendships(Friendships f) {
		friendshipDao = new FriendshipHibernate();
		// adds two entries with user_id and friend_id swapped for faster, easier querying of the database
		Friendships f2 = new Friendships();
		f2.setMessage(f.getMessage());
		f2.setFriend_id(f.getUser_id());
		f2.setUser_id(f.getFriend_id());
		friendshipDao.addFriendship(f2).getFriendship_id();
		Integer pkid = friendshipDao.addFriendship(f).getFriendship_id();
		return pkid;
	}

	@Override
	public Set<Friendships> getFriendshipsByUserId(Integer id) {
		friendshipDao = new FriendshipHibernate();
		return friendshipDao.getByUserId(id);
	}

	@Override
	public Friendships getFriendshipsById(Integer id) {
		friendshipDao = new FriendshipHibernate();
		return friendshipDao.getById(id);
	}

	@Override
	public void updateFriendships(Friendships f) {
		friendshipDao.update(f);
		
	}

	@Override
	public void deleteFriendships(Friendships f) {
		friendshipDao.addFriendship(f);
		
	}

}
