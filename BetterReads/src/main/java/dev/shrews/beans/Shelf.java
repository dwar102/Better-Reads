package dev.shrews.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name="shelves")
@Component
public class Shelf {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="shelf_id")
	private Integer id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	@Autowired
	private User user;
	@Column(name="shelf_name")
	private String name;
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "shelf_assignment_id")
//	@Autowired
//	private Set<ShelfAssignment> shelfAssignments;
	
	public Shelf(Integer id, User user, String name, Set<ShelfAssignment> shelfAssignments) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
//		this.shelfAssignments = shelfAssignments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Autowired
	public Shelf() {
		super();
		this.id = 0;
		this.user = new User();
		this.name = "";
	}
}