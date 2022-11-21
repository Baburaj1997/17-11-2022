package com.hibernate.jpa.demo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "actors")
public class Actor {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "name", nullable=false, length=255)
  private String name;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "year_of_birth")
  private int yearOfBirth;

  @ManyToMany (cascade = CascadeType.ALL)
  @JoinTable(name = "actors_to_movies",
      joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "id"))
  private List<Movie> movies = new ArrayList<>();

	public Actor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Actor(long id, String name, String lastName, int yearOfBirth, List<Movie> movies) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.movies = movies;
	}
	
	public Actor(String name, String lastName, int yearOfBirth, List<Movie> movies) {
		super();
		
		this.name = name;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.movies = movies;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + name + ", lastName=" + lastName + ", yearOfBirth=" + yearOfBirth + "]";
				//+ ", movies=" + movies + "]";
	}
}


