package dev.shrews.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="User_Review_Comments")
@Component
public class User_Review_Comments {
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public LocalDateTime getComment_date() {
		return comment_date;
	}
	public void setComment_date(LocalDateTime comment_date) {
		this.comment_date = comment_date;
	}
	public Review getreview() {
		return review;
	}
	public void setreview(Review review) {
		this.review = review;
	}
	public User_Review_Comments getParent() {
		return parent;
	}
	public void setParent(User_Review_Comments parent) {
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
		return "User_review_Comments [comment_id=" + comment_id + ", comment_date=" + comment_date + ", review=" + review
				+ ", parent=" + parent + ", user=" + user + ", message=" + message + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment_date == null) ? 0 : comment_date.hashCode());
		result = prime * result + ((comment_id == null) ? 0 : comment_id.hashCode());
		result = prime * result + ((review == null) ? 0 : review.hashCode());
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
		User_Review_Comments other = (User_Review_Comments) obj;
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
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
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
	@Autowired
	public User_Review_Comments() {
		comment_id = 0;
		comment_date = LocalDateTime.now();
		review = new Review();
		parent = null;
		user = new User();
		message = "";
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer comment_id;
	@Column(name="comment_date")
	private LocalDateTime comment_date;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL) 
	@JoinColumn(name="review_id")
	@Autowired
	private Review review;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="parent_comment_id")
	@Autowired
	private User_Review_Comments parent;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	@Autowired
	private User user;
	@Column(name="message")
	private String message;

}
