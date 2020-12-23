package dev.shrews.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name="users")
@Component
public class User {
	
	@Autowired
	public User() {
		id = 0;
		salt = "";
		username = "";
		pass = "";
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", salt=" + salt + ", username=" + username + ", pass=" + pass + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer id;
	
	private String salt;
	
	private String username;
	
	private String pass;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
}