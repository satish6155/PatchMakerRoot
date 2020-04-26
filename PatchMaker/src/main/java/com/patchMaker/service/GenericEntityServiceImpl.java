package com.patchMaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.patchMaker.dao.GenericEntityDaoImpl;

@Service("genericEntityServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GenericEntityServiceImpl<T> {


	@Autowired
	private GenericEntityDaoImpl<T> genericDao;
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T save(T entity) {
		System.out.println("Inside GenericEntityDaoImpl.save()");
		return genericDao.save(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public T findOne(Class<T> clazz,Long id) {
		return genericDao.findOne(clazz,id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<T> findAll(Class<T> clazz) {
		return genericDao.findAll(clazz);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void update(T entity) {
		genericDao.update(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(T entity) {
		genericDao.delete(entity);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteById(Class<T> clazz,Long entityId) {
		genericDao.deleteById(clazz,entityId);
	}

}
