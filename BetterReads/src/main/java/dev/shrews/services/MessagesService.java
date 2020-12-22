package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Messages;

public interface MessagesService {
	public Messages addMessages(Messages m);
	public Set<Messages> getMessagesByUserId(Integer id);
	public void delete(Messages m);
}
