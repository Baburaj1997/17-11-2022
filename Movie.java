package com.hibernate.jpa.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movies")
public class Movie {

	@Id
  	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name = "id")
  	private long id;

  	@Column(name = "title")
  	private String title;

  	@Column(name = "year_of_release")
  	private int yearOfRelease;

  	@ManyToMany(mappedBy = "movies")
  	private List<Actor> actors = new ArrayList<>();

  	@ManyToOne
  	private Genre genre;

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(long id, String title, int yearOfRelease, List<Actor> actors, Genre genre) {
		super();
		this.id = id;
		this.title = title;
		this.yearOfRelease = yearOfRelease;
		this.actors = actors;
		this.genre = genre;
	}
	
	public Movie(String title, int yearOfRelease, List<Actor> actors, Genre genre) {
		super();
		
		this.title = title;
		this.yearOfRelease = yearOfRelease;
		this.actors = actors;
		this.genre = genre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYearOfRelease() {
		return yearOfRelease;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", yearOfRelease=" + yearOfRelease + ", actors=" + actors
				+ ", genre=" + genre + "]";
	}	
}

