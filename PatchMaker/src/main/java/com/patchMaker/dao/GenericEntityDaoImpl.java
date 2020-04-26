package com.patchMaker.dao;

import java.sql.Connection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.stereotype.Repository;

@Repository("genericDao")
public class GenericEntityDaoImpl<T> {

	static Logger logger = Logger.getLogger(GenericEntityDaoImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	public T save(T entity) {
		System.out.println("Inside GenericEntityDaoImpl.save()");
		//entityManager.persist(entity);
		return entityManager.merge(entity);
	}

	public T findOne(Class<T> clazz,Long id) {
		return entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> clazz) {
		return entityManager.createQuery("from " + clazz.getName())
				.getResultList();
	}

	public void update(T entity) {
		entityManager.merge(entity);
	}

	public void delete(T entity) {
		entityManager.remove(entity);
	}

	public void deleteById(Class<T> clazz,Long entityId) {
		T entity = findOne(clazz,entityId);
		delete(entity);
	}
	@SuppressWarnings("unchecked")
	public Connection getconnection() {

        final Session session = (Session) entityManager.unwrap((Class) Session.class);

        return ((SessionImplementor) session).connection();

 }
}
