package dev.shrews.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name="reviews")
@Component
public class Review {

	public Review() {
		id = 0;
		date = LocalDate.now();
		rating = 0;
		user = new User();
		media = new Media();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id")
	private Integer id;
	
	@Column(name="review_date")
	private LocalDate date;

	@Column(name="rating")
	private Integer rating;
	

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	@Autowired
	private User user;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="media_id")
	@Autowired
	private Media media;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

}