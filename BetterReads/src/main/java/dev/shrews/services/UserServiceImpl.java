package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.User;
import dev.shrews.data.UserDAO;
import dev.shrews.data.UserHibernate;
import dev.shrews.exceptions.NonUniqueUsernameException;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	
	public UserServiceImpl () {
		userDAO = new UserHibernate();
	}

	@Override
	public Integer addUser(User u) throws NonUniqueUsernameException {
		return userDAO.add(u).getId();
	}

	@Override
	public Set<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByName(String username) {
		return userDAO.getByUsrname(username);
	}
	
	@Override
	public void deleteUser(User u) {
		//userDao.delete(u);
	}
	
	@Override
	public void updateUser(User u) {
		//userDao.update(u);
	}
}
