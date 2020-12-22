package dev.shrews.services;

import java.util.Set;

import dev.shrews.beans.Messages;
import dev.shrews.data.MessagesDAO;
import dev.shrews.data.MessagesHibernate;

public class MessagesServiceImpl implements MessagesService {
	
	private MessagesDAO messageDAO; 
	public MessagesServiceImpl() {
		messageDAO = new MessagesHibernate();
	}
	@Override
	public Messages addMessages(Messages m) {
		return messageDAO.add(m);
	}

	@Override
	public Set<Messages> getMessagesByUserId(Integer id) {
		return messageDAO.getByUserId(id);
	}

	@Override
	public void delete(Messages m) {
		messageDAO.delete(m);
	}

}
