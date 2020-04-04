package com.patchMaker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.patchMaker.dao.GenericEntityDaoImpl;
import com.patchMaker.entity.Patch;

@Service("patchServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PatchServiceImpl {

	@Autowired
	GenericEntityDaoImpl<Patch> patchDao;
		
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Patch save(Patch patch) {
		System.out.println("Inside patchDao.save() before save patch :"+patch);
		patchDao.save(patch);
		System.out.println("Inside patchDao.save() after save patch :"+patch);
		return patch;
	}	
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Patch findOne(Long id) {
		return patchDao.findOne(Patch.class, id);
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Patch> findAll() {
		return patchDao.findAll(Patch.class);
	}
	
}


