package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.User;
import dev.shrews.beans.User_Media_Comments;
import dev.shrews.beans.User_Review_Comments;
import dev.shrews.exceptions.NonUniqueUsernameException;

public interface UserDAO extends GenericDAO<User> {
	public User add(User u) throws NonUniqueUsernameException ;
	public User getByUsername(String username);
	public User getUserById(Integer id);
	public void delete(User u); // No such user exception
	public Set<User> getUsers();
	public Set<User_Media_Comments> getCommentsForMedia(Integer id);
	public Set<User_Review_Comments> getCommentsForReview(Integer id);
	public User_Review_Comments placeCommentForReview(User_Review_Comments c);
	public User_Media_Comments placeCommentForMedia(User_Media_Comments c);
	public void delete(User_Review_Comments c);
	public void delete(User_Media_Comments c);
}

