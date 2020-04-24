package com.patchMaker.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.patchMaker.controller.PatchMakerController;
import com.patchMaker.dao.GenericEntityDaoImpl;
import com.patchMaker.entity.Patch;
import com.patchMaker.util.Values;

@Service("patchServiceImpl")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class PatchServiceImpl {

	@Autowired
	GenericEntityDaoImpl<Patch> patchDao;
    private static final String SAVE_DIR = "PATCH";

		
	
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

	public String writeFiles(HttpServletRequest request, String fileType, String patchName) throws IOException, ServletException {
		String message = "" ;
		if(ServletFileUpload.isMultipartContent(request)){
			try {
				
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
            	File fileSaveDir= new File(Values.BASE_DIRECTORY+File.separator+patchName+File.separator+fileType);
            	if (!fileSaveDir.exists()) {
                    fileSaveDir.mkdirs();
        		}
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write(new File(fileSaveDir + File.separator + name));						
						  message = message +fileType + ":" + name+";";
						 
                        }
                }
			} catch (Exception e) {
				// exception handling
				System.out.println("Exception in upload"+e);
				/*
				 * m1="Exception in upload"; message=e.toString();
				 */
			}
		}
		return message;
	}

}

