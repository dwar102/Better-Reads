package dev.shrews.beans;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name="messages")
@Component
public class Messages {
	@Autowired
	public Messages() {
		message_id = 0;
		message_date = LocalDateTime.now();
		parent = null;
		sender = new User();
		recipient = new User();
		message = "";
	}
	@Override
	public String toString() {
		return "messages [message_id=" + message_id + ", message_date=" + message_date + ", parent_message_id="
				+ parent + ", sender_id=" + sender + ", recipient_id=" + recipient + ", message="
				+ message + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((message_date == null) ? 0 : message_date.hashCode());
		result = prime * result + ((message_id == null) ? 0 : message_id.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((recipient == null) ? 0 : recipient.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Messages other = (Messages) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (message_date == null) {
			if (other.message_date != null)
				return false;
		} else if (!message_date.equals(other.message_date))
			return false;
		if (message_id == null) {
			if (other.message_id != null)
				return false;
		} else if (!message_id.equals(other.message_id))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (recipient == null) {
			if (other.recipient != null)
				return false;
		} else if (!recipient.equals(other.recipient))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		return true;
	}
	public LocalDateTime getMessage_date() {
		return message_date;
	}
	public void setMessage_date(LocalDateTime message_date) {
		this.message_date = message_date;
	}
	public Messages getParent_Message() {
	return parent;
	}
	public void setParent_Message(Messages parent) {
		this.parent = parent;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getRecipient() {
		return recipient;
	}
	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer message_id;
public Integer getMessage_id() {
	return message_id;
}
public void setMessage_id(Integer message_id) {
	this.message_id = message_id;
}
@Column(name="message_date")
private LocalDateTime message_date;

@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="parent_message_id")
@Autowired
private Messages parent;

@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="sender_id")
@Autowired
private User sender;
@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="recipient_id")
@Autowired
private User recipient;
@Column(name="message")
private String message;

}
