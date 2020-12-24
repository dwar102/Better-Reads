package dev.shrews.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.shrews.beans.User;
import dev.shrews.beans.User_Media_Comments;
import dev.shrews.beans.User_Review_Comments;
import dev.shrews.data.UserDAO;
import dev.shrews.data.UserHibernate;
import dev.shrews.exceptions.NonUniqueUsernameException;
@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	@Autowired
	public UserServiceImpl (UserDAO u) {
		userDAO = u;
	}

	@Override
	public User addUser(User u) throws NonUniqueUsernameException {
		return userDAO.add(u);
	}

	@Override
	public Set<User> getUsers() {
		return userDAO.getUsers();
	}

	@Override
	public User getUserById(Integer id) {
		return userDAO.getUserById(id);
	}

	@Override
	public User getUserByName(String username) {
		return userDAO.getByUsername(username);
	}
	
	@Override
	public void deleteUser(User u) {
		userDAO.delete(u);
	}
	
	@Override
	public void updateUser(User u) {
		//userDao.update(u);
	}

	@Override
	public Set<User_Review_Comments> getCommentsForReview(Integer id) {
		return userDAO.getCommentsForReview(id);
	}

	@Override
	public Set<User_Media_Comments> getCommentsForMedia(Integer id) {
		return userDAO.getCommentsForMedia(id);
	}

	@Override
	public void placeCommentForReview(User_Review_Comments c) {
		userDAO.placeCommentForReview(c);
	}

	@Override
	public void placeCommentForMedia(User_Media_Comments c) {
		userDAO.placeCommentForMedia(c);
	}
	@Override
	public void delete(User_Review_Comments c) {
		userDAO.delete(c);
		
	}

	@Override
	public void delete(User_Media_Comments c) {
		userDAO.delete(c);
	}
}
