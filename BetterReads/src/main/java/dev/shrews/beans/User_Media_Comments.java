package dev.shrews.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name="user_media_comments")
@Component

public class User_Media_Comments {
	public User_Media_Comments(Integer comment_id, LocalDate comment_date, Media media, User_Media_Comments parent,
			User user, String message) {
		this.comment_id = comment_id;
		this.comment_date = LocalDate.now();
		this.media = media;
		this.parent = parent;
		this.user = user;
		this.message = message;
	}
	@Autowired
	public User_Media_Comments() {
		comment_id = 0;
		comment_date = LocalDate.now();
		media = new Media();
		parent = null;
		user = new User();
		message = "";
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public LocalDate getComment_date() {
		return comment_date;
	}
	public void setComment_date(LocalDate comment_date) {
		this.comment_date = comment_date;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	public User_Media_Comments getParent() {
		return parent;
	}
	public void setParent(User_Media_Comments parent) {
		this.parent = parent;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "User_Media_Comments [comment_id=" + comment_id + ", comment_date=" + comment_date + ", media=" + media
				+ ", parent=" + parent + ", user=" + user + ", message=" + message + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment_date == null) ? 0 : comment_date.hashCode());
		result = prime * result + ((comment_id == null) ? 0 : comment_id.hashCode());
		result = prime * result + ((media == null) ? 0 : media.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		User_Media_Comments other = (User_Media_Comments) obj;
		if (comment_date == null) {
			if (other.comment_date != null)
				return false;
		} else if (!comment_date.equals(other.comment_date))
			return false;
		if (comment_id == null) {
			if (other.comment_id != null)
				return false;
		} else if (!comment_id.equals(other.comment_id))
			return false;
		if (media == null) {
			if (other.media != null)
				return false;
		} else if (!media.equals(other.media))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer comment_id;
	@Column(name="comment_date")
	private LocalDate comment_date;
	
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="media_id")
	@Autowired
	private Media media;
	
		
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="parent_comment_id")
	@Autowired
	private User_Media_Comments parent;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	@Autowired
	private User user;
	@Column(name="message")
	private String message;

}
