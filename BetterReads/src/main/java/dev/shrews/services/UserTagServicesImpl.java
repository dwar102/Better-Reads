package dev.shrews.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.shrews.beans.UserTag;
import dev.shrews.data.FriendshipDAO;
import dev.shrews.data.UserTagDAO;
import dev.shrews.data.UserTagHibernate;

@Service
public class UserTagServicesImpl implements UserTagService{

	private UserTagDAO userTagDao;
	
	@Autowired
	public UserTagServicesImpl(UserTagDAO u) {
		userTagDao = u;
	}
	
	@Override
	public Integer addUserTag(UserTag ut) {
		return userTagDao.addUserTag(ut).getId();
	}

	@Override
	public UserTag getByUserTagId(Integer id) {
		return userTagDao.getByUserTagId(id);
	}

	@Override
	public Set<UserTag> getAll() {
		return userTagDao.getAll();
	}

	@Override
	public Set<UserTag> getUserTagsByMediaId(Integer id) {
		return userTagDao.getByMediaId(id);
	}

//	@Override
//	public void updateUserTag(UserTag ut) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void deleteUserTag(UserTag ut) {
		userTagDao.delete(ut);
	}

}
