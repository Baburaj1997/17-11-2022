package com.hibernate.jpa.demo;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ActorRepository {

	  private final EntityManager entityManager;

	  public ActorRepository(final EntityManager entityManager) {
	    this.entityManager = entityManager;
	  }

	  public Actor save(final Actor actor) {
	    EntityTransaction transaction = null;
	    try {
	      transaction = entityManager.getTransaction();
	      if (!transaction.isActive()) {
	        transaction.begin();
	      }

	      entityManager.persist(actor);
	      transaction.commit();
	      return actor;
	    } catch (final Exception e) {
	      if (transaction != null) {
	        transaction.rollback();
	      }
	      return null;
	    }
	  }

	  public Optional<Actor> findById(final Long id) {
	    return Optional.ofNullable(entityManager.find(Actor.class, id));
	  }

	  public List<Actor> findAllBornAfter(final int lowerBoundExclusive) {
	    return entityManager.createQuery("SELECT a FROM Actor a WHERE a.yearOfBirth > :year", Actor.class)
	        .setParameter("year", lowerBoundExclusive)
	        .getResultList();
	  }

	  public List<Actor> findAllWithLastNameEndsWith(final String surnameEndsWith) {
	    return entityManager.createQuery("SELECT a FROM Actor a WHERE a.lastName LIKE :lastName", Actor.class)
	        .setParameter("lastName", "%" + surnameEndsWith)
	        .getResultList();
	  }
	  
	  public List<Actor> findAllWithLastNameWith(final String surnameWith) {
		    return entityManager.createQuery("SELECT a FROM Actor a WHERE a.lastName LIKE :lastName", Actor.class)
		        .setParameter("lastName", "%" + surnameWith + "%")
		        .getResultList();
	  }
	}
