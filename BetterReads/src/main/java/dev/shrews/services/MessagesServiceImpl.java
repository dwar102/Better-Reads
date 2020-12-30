package dev.shrews.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.shrews.beans.Messages;
import dev.shrews.data.MessagesDAO;
import dev.shrews.data.MessagesHibernate;
@Service
public class MessagesServiceImpl implements MessagesService {

	private MessagesDAO messageDAO;
	
	@Autowired
	public MessagesServiceImpl(MessagesDAO m) {
		messageDAO = m;
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
