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
	
	public Shelf(Integer id, User user, String name) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
	}
//		this.shelfAssignments = shelfAssignments;
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

	@Override
	public String toString() {
		return "Shelf [id=" + id + ", name=" + name + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Shelf other = (Shelf) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Autowired
	public Shelf() {
		super();
		this.id = 0;
		this.user = new User();
		this.name = "";
	}
}