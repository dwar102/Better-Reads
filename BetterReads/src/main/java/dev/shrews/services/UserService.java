package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Media;
import dev.shrews.beans.Review;
//import dev.shrews.beans.Media;
import dev.shrews.beans.User;
import dev.shrews.beans.User_Media_Comments;
import dev.shrews.beans.User_Review_Comments;
//import dev.shrews.beans.UserTag;
import dev.shrews.exceptions.NonUniqueUsernameException;


public interface UserService {
//	public Set<UserTag> getAllTags();
//	public Set<UserTag> getTagsByBookId(Integer Id);
//	public Integer addTagByBookId(UserTag t, Media m);
	public Integer addUser(User u) throws NonUniqueUsernameException;
	public Set<User> getUsers();
	public User getUserById(Integer id);
	public User getUserByName(String username);
	public void deleteUser(User u);
	public void updateUser(User u);
	public void placeReviewComment(User_Review_Comments c);
	public void placeMediaComment(User_Media_Comments c);
	public Set<User_Review_Comments> getReviewComments(Integer id);
	public Set<User_Media_Comments> getMediaComments(Integer id);
}
