package com.patchMaker.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
		patch = patchDao.save(patch);
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

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void SaveFilesJson(Long patchId, String filesJson) {

		Patch patch = patchDao.findOne(Patch.class, patchId);		
		patch.setFilesJson(filesJson);		
		patchDao.update(patch);
	}

	public void createZip(String patchName){
		
		 FileOutputStream fos;
		try {
			fos = new FileOutputStream(Values.BASE_DIRECTORY+File.separator+patchName+".zip");
		
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			addDirToZipArchive(zos, new File(Values.BASE_DIRECTORY+File.separator+patchName), null);
		
			    zos.flush();
			    fos.flush();
			    zos.close();
			    fos.close();
	
		} catch (Exception e) {
			System.out.println("Excepiton while creating zip : "+e);
		}
		
	}
	
	public static void addDirToZipArchive(ZipOutputStream zos, File fileToZip, String parrentDirectoryName) throws Exception {
	    if (fileToZip == null || !fileToZip.exists()) {
	        return;
	    }

	    String zipEntryName = fileToZip.getName();
	    if (parrentDirectoryName!=null && !parrentDirectoryName.isEmpty()) {
	        zipEntryName = parrentDirectoryName + File.separator + fileToZip.getName();
	    }

	    if (fileToZip.isDirectory()) {
	        for (File file : fileToZip.listFiles()) {
	            addDirToZipArchive(zos, file, zipEntryName);
	        }
	    } else {
	        byte[] buffer = new byte[1024];
	        FileInputStream fis = new FileInputStream(fileToZip);
	        zos.putNextEntry(new ZipEntry(zipEntryName));
	        int length;
	        while ((length = fis.read(buffer)) > 0) {
	            zos.write(buffer, 0, length);
	        }
	        zos.closeEntry();
	        fis.close();
	    }
	}
}
