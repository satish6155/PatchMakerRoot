package com.patchMaker.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNCommitClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class SVNUtills {

	public String authenticateUser(String username, String password) {

		// setupLibrary();
		String errorMsg = null;

		try {
			SVNURL url = SVNURL.parseURIEncoded(Values.SVN_DIR);
			SVNRepository repository = SVNRepositoryFactory.create(url);
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
			repository.setAuthenticationManager(authManager);
			repository.testConnection();

		} catch (SVNException e) {
			SVNErrorMessage err = e.getErrorMessage();

			while (err != null) {

				System.err.println(err.getErrorCode().getCode() + " : " + err.getMessage());

				if (err.getMessage().contains("Authentication")) {
					errorMsg = "Invalid User name or Password!!";
				} else if (err.getErrorCode().getCode() == 175002) {
					errorMsg = "SVN is down please check later";
				}
				err = err.getChildErrorMessage();

				return errorMsg;
			}
		}

		return errorMsg;

	}
	
	public static void svn_actions(String patchName, String username, String password) throws SVNException {

		setupLibrary();

		try {
				createZip(patchName);
				commitExample(patchName,username, password);
			}
		
		catch (SVNException e) {
		SVNErrorMessage err = e.getErrorMessage();

			while (err != null) {
				System.err.println(err.getErrorCode().getCode() + " : " + err.getMessage());
				/*if (err.getMessage().contains("Authentication")) {
					System.err.println("Invalid Username or Password!!");
				} */
				err = err.getChildErrorMessage();
			}
			
		}
		
	}
	
		private static void setupLibrary() {
			/*
			 * For using over http:// and https://
			 */
			DAVRepositoryFactory.setup();
			/*
			 * For using over svn:// and svn+xxx://
			 */
			SVNRepositoryFactoryImpl.setup();

			/*
			 * For using over file:///
			 */
			FSRepositoryFactory.setup();
		}
		
	public static void commitExample(String patchName,String username, String password) throws SVNException {
	
		SVNURL url = SVNURL.parseURIEncoded(Values.SVN_DIR);
		
		String path = Values.SVN_DIR+patchName+".zip";
		// this path is used to update folder 
		File svnUpdate = new File(Values.BASE_DIR_FOR_CHECKIN_CHKECKOUT);
		
		SVNRepository repository = SVNRepositoryFactory.create(url);
		ISVNOptions myOptions = SVNWCUtil.createDefaultOptions(true);
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);

		repository.setAuthenticationManager(authManager);

		SVNClientManager clientManager = SVNClientManager.newInstance(myOptions, authManager);
	    
		//SVNNodeKind nodeKind = repository.checkPath("", -1);  -- don't know the use of nodekind
		
		SVNUpdateClient uc = clientManager.getUpdateClient();
		// below method is used to update path in svn
        long[] l =  uc.doUpdate(new File[]{svnUpdate}, SVNRevision.HEAD,SVNDepth.INFINITY, true,true);
        System.out.println("update is done ....  :) :)" +l[0]);

		long latestRevision = repository.getLatestRevision();
		System.out.println("Repository latest revision (before committing): " + latestRevision);

		  SVNCommitClient commitClient = clientManager.getCommitClient();
		  
		  File fileToCheckin = new File(Values.BASE_DIR_FOR_CHECKIN_CHKECKOUT+patchName+".zip");
	      boolean recursive = true; 
	      
	      System.out.println("fileToCheckin before committing : "+fileToCheckin);
		  
	      System.out.println("svn path before committing : "+path);
		  
	       SVNCommitInfo importInfo = commitClient
	                .doImport(fileToCheckin ,SVNURL.parseURIDecoded(path), "testing svn kit integration", recursive);
	       System.out.println("The directory was added: new revision number is : "+importInfo.getNewRevision());
	       System.out.println("Repository latest revision (after committing): " + repository.getLatestRevision());
	       deleteFile(new File(Values.BASE_DIRECTORY+File.separator+patchName));
	       System.out.println("File deleted successfully!!");
	}
		
public static void createZip(String patchName) throws SVNException{
		
		FileOutputStream fos;
		try {				
			
			fos = new FileOutputStream(Values.BASE_DIR_FOR_CHECKIN_CHKECKOUT+patchName+".zip");
			
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
	
	
	private static void deleteFile(File file) {
		   if (file == null || !file.exists()) {
		       return;
		   }
		   
		   if (file.isDirectory()) {
		       for (File currentFile : file.listFiles()) {
		        System.out.println(" Directory :"+currentFile);
		           deleteFile(currentFile);
		       }
		       System.out.println(" deleting directory :"+file);
		       file.delete();
		   } else {
		    System.out.println(" deleting file :"+file);
		    file.delete();
		    }
		}

	
}