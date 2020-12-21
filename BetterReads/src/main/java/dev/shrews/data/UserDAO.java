package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.User;
import dev.shrews.exceptions.NonUniqueUsernameException;

public interface UserDAO extends GenericDAO {
	public User add(User u) throws NonUniqueUsernameException ;
	public User getByUsrname(String username);
}

