package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Media;
import dev.shrews.beans.Review;
import dev.shrews.beans.User;
import dev.shrews.beans.User_Media_Comments;
import dev.shrews.beans.User_Review_Comments;
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
	public void placeReviewComment(User_Review_Comments c) {
		userDAO.placeCommentForReview(c);
		
	}

	@Override
	public void placeMediaComment(User_Media_Comments c) {
		userDAO.placeCommentForMedia(c);
		
	}

	@Override
	public Set<User_Review_Comments> getReviewComments(Integer id) {
		return userDAO.getCommentsForReview(id);
	}

	@Override
	public Set<User_Media_Comments> getMediaComments(Integer id) {
		return userDAO.getCommentsForMedia(id);
	}
}
