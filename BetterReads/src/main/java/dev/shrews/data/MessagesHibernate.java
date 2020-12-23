package dev.shrews.data;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import dev.shrews.beans.Messages;
import dev.shrews.beans.User;
import dev.shrews.utils.HibernateUtil;

@Repository
public class MessagesHibernate implements MessagesDAO {
	private HibernateUtil hu = HibernateUtil.getHibernateUtil();
	@Override
	public Messages add(Messages m) { // this works because we will be receiving the JSON user objects and storing them as fields 
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(m);
			tx.commit();
		} catch(Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return m;
	}
	@Override
	public Set<Messages> getByUserId(Integer id) {
		Session s = hu.getSession();
		String query = "FROM Messages where recipient_id = :id";
		Query<Messages> q = s.createQuery(query, Messages.class);
		q.setParameter("id", id);
		List<Messages> messageList = q.getResultList();
		Set<Messages> messageSet = new HashSet<>();
		messageSet.addAll(messageList);
		s.close();
		return messageSet;
	}
	@Override
	public void delete(Messages m) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(m);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}

	}
}
