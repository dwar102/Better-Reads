package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.User;
import dev.shrews.exceptions.NonUniqueUsernameException;

public class UserServiceImpl implements UserService {

	@Override
	public Integer addUser(User u) throws NonUniqueUsernameException {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
