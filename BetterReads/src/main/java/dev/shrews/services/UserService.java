package dev.shrews.services;

import java.util.Set;

//import dev.shrews.beans.Media;
import dev.shrews.beans.User;
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
	public void updateUsr(User u);
}
