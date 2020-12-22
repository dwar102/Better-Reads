package dev.shrews.data;

import java.util.Set;

import dev.shrews.beans.Messages;

public interface MessagesDAO {

	public Messages add(Messages m);

	public Set<Messages> getByUserId(Integer id);

	public void delete(Messages m);
	
}