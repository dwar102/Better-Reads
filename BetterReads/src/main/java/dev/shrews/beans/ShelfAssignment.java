package dev.shrews.beans;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@Table(name="shelf_assignments")
public class ShelfAssignment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="assignment_date")
	private LocalDate assignmentDate;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="shelf_id")
	private Shelf shelf;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="media_id")
	private Media media;
	
	
	
}