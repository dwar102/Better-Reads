package dev.shrews;

import java.util.Set;

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

@Entity
@Table(name="shelves")
public class Shelf {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="users_id")
	private User user;
	private String name;
	@OneToMany
	@JoinColumn(name = "shelf_assignment_id")
	private Set<ShelfAssignment> shelfAssignments;
	public Shelf(Integer id, User user, String name, Set<ShelfAssignment> shelfAssignments) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.shelfAssignments = shelfAssignments;
	}
	public Shelf() {
		super();
		this.id = 0;
		this.user = new User();
		this.name = "";
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
	public Set<ShelfAssignment> getShelfAssignments() {
		return shelfAssignments;
	}
	public void setShelfAssignments(Set<ShelfAssignment> shelfAssignments) {
		this.shelfAssignments = shelfAssignments;
	}
	@Override
	public String toString() {
		return "Shelf [id=" + id + ", name=" + name + ", shelfAssignments=" + shelfAssignments + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((shelfAssignments == null) ? 0 : shelfAssignments.hashCode());
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
		if (shelfAssignments == null) {
			if (other.shelfAssignments != null)
				return false;
		} else if (!shelfAssignments.equals(other.shelfAssignments))
			return false;
		return true;
	}

	


}