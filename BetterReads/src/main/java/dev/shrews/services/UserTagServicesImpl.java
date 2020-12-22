package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.UserTag;
import dev.shrews.data.FriendshipDAO;
import dev.shrews.data.UserTagDAO;
import dev.shrews.data.UserTagHibernate;

public class UserTagServicesImpl implements UserTagService{

	private UserTagDAO userTagDao;
	
	@Override
	public Integer addUserTag(UserTag ut) {
		userTagDao = new UserTagHibernate();
		return userTagDao.addUserTag(ut).getId();
	}

	@Override
	public UserTag getByUserTagId(Integer id) {
		userTagDao = new UserTagHibernate();
		return userTagDao.getByUserTagId(id);
	}

	@Override
	public Set<UserTag> getAll() {
		userTagDao = new UserTagHibernate();
		return userTagDao.getAll();
	}

	@Override
	public Set<UserTag> getUserTagsByMediaId(Integer id) {
		userTagDao = new UserTagHibernate();
		return userTagDao.getByMediaId(id);
	}

//	@Override
//	public void updateUserTag(UserTag ut) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void deleteUserTag(UserTag ut) {
		userTagDao = new UserTagHibernate();
		userTagDao.delete(ut);
	}

}
