package dev.shrews.beans;

import java.time.LocalDateTime;
import java.util.Set;

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
@Table(name="shelf_assignments")
@Component
public class ShelfAssignment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="shelf_assignment_id")
	private Integer id;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="shelf_id")
	@Autowired
	private Shelf shelf;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="media_id")
	@Autowired
	private Media media;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	@Autowired
	private User user;
	@Column(name="shelf_assignment_date")
	private LocalDateTime date;

	@Autowired
	public ShelfAssignment() {
		super();
		this.id = 0;
		this.shelf = new Shelf();
		this.media = new Media();
		this.date = null;
	}
	
	public ShelfAssignment(Integer id, Shelf shelf, Media media, User user, LocalDateTime date) {
		super();
		this.id = id;
		this.shelf = shelf;
		this.media = media;
		this.user = user;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Shelf getShelf() {
		return shelf;
	}
	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "ShelfAssignment [id=" + id + ", shelf=" + shelf + ", media=" + media + ", date=" + date + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((media == null) ? 0 : media.hashCode());
		result = prime * result + ((shelf == null) ? 0 : shelf.hashCode());
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
		ShelfAssignment other = (ShelfAssignment) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (media == null) {
			if (other.media != null)
				return false;
		} else if (!media.equals(other.media))
			return false;
		if (shelf == null) {
			if (other.shelf != null)
				return false;
		} else if (!shelf.equals(other.shelf))
			return false;
		return true;
	}
	
	
	
}
