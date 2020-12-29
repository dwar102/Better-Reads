package dev.shrews.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name="friendships")
@Component
public class Friendships {
	
	public Friendships() {
		friendship_id = 0;
		user_id = new User();
		friend_id = new User();
	}
	
	@Override
	public String toString() {
		return "friendships [friendship_id=" + friendship_id + ", user_id=" + user_id + ", friend_id=" + friend_id
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friend_id == null) ? 0 : friend_id.hashCode());
		result = prime * result + ((friendship_id == null) ? 0 : friendship_id.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		Friendships other = (Friendships) obj;
		if (friend_id == null) {
			if (other.friend_id != null)
				return false;
		} else if (!friend_id.equals(other.friend_id))
			return false;
		if (friendship_id == null) {
			if (other.friendship_id != null)
				return false;
		} else if (!friendship_id.equals(other.friendship_id))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	
	public Integer getFriendship_id() {
		return friendship_id;
	}
	public void setFriendship_id(Integer friendship_id) {
		this.friendship_id = friendship_id;
	}
	public User getUser_id() {
		return user_id;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	public User getFriend_id() {
		return friend_id;
	}
	public void setFriend_id(User friend_id) {
		this.friend_id = friend_id;
	}

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer friendship_id;
@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="user_id")
@Autowired
private User user_id;
@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="friend_id")
@Autowired
private User friend_id;

}
