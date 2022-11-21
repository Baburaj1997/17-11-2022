package com.hibernate.jpa.demo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;

public class GenreRepository {
	  private final EntityManager entityManager;

	  public GenreRepository(EntityManager entityManager) {
	    this.entityManager = entityManager;
	  }

	  public List<Genre> findAll() { // JPQL
		  return entityManager.createQuery("from Genre", Genre.class).getResultList();
		  //return entityManager.createQuery("select g from Genre g", Genre.class).getResultList();
	  }

	  public Optional<Genre> findById(final long id) {
	    return Optional.ofNullable(entityManager.find(Genre.class, id));
		  
		  /*
		  Genre g = (Genre)entityManager.find(Genre.class, id);
		  
		  if(g!=null)
			  return Optional.of(g);
		  else
			  return Optional.empty();
		*/	  
	  }
	  
	  // findAll("Humour");
	  
	  public List<Genre> findAllByName(String name) {
	    return entityManager.createQuery("SELECT g FROM Genre WHERE name like :name", Genre.class)
	        .setParameter("name", name)
	        .getResultList();
	  }
	  
	  public Optional<Genre> findByName(String name) {
		    Genre g =  entityManager.createQuery("SELECT g FROM Genre WHERE name = :name", Genre.class)
		        .getSingleResult();
		    
		    return Optional.ofNullable(g);
			  
	  }

	  public Genre save(final Genre genre) {
	    EntityTransaction transaction = null;
	    try {
	      transaction = entityManager.getTransaction();
	      if (!transaction.isActive()) {
	        transaction.begin();
	      }

	      entityManager.persist(genre);
	      
	      //transaction.commit();
	      //return genre;
	    } 
	    catch (final HibernateException e) {
	      if (transaction != null) {
	        transaction.rollback();
	      }
	      //return null;
	      
	      throw e;
	    }
	    catch (final Exception e) {
	    	if (transaction != null) {
		        transaction.rollback();
		    }
		    //return null;
		      
		    throw e;
		}
	    finally {
	    	if(transaction != null) {
	    		transaction.commit();
	    	}
	    }
	    
	    return genre;
	  }

	  public void remove(final Genre genre) {
	    EntityTransaction transaction = null;
	    try {
	      transaction = entityManager.getTransaction();
	      if (!transaction.isActive()) {
	        transaction.begin();
	      }

	      entityManager.remove(genre);
	      transaction.commit();
	    } catch (final Exception e) {
	      if (transaction != null) {
	        transaction.rollback();
	      }
	    }
	  }
	}