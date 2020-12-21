package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.User;
import dev.shrews.exceptions.NonUniqueUsernameException;

public interface UserDAO extends GenericDAO<User> {
	public User add(User u) throws NonUniqueUsernameException ;
	public User getByUsername(String username);
	public User getUserById(Integer id);
	public void delete(User u); // No such user exception
	public Set<User> getUsers();
}

