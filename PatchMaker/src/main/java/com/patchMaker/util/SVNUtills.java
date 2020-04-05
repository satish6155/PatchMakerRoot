package com.patchMaker.util;

import java.io.ByteArrayInputStream;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNErrorCode;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.patchMaker.entity.PatchDetail;


public class SVNUtills {

    public static void svn_actions(PatchDetail patchDetail, String username, String password) throws SVNException {
        
        setupLibrary();
        
       
       /* try {*/
            commitExample(username,password);
       /* } catch (SVNException e) {
            SVNErrorMessage err = e.getErrorMessage();
            
             * Display all tree of error messages. 
             * Utility method SVNErrorMessage.getFullMessage() may be used instead of the loop.
             
            while(err != null) {
                System.err.println(err.getErrorCode().getCode() + " : " + err.getMessage());
            if(err.getMessage().contains("Authentication"))    {
            	System.err.println("Invalid Username or Password!!");
            }
                err = err.getChildErrorMessage();
            }
            System.exit(1);
        }
        System.exit(0);*/
    }

    private static void commitExample(String username, String password) throws SVNException {
        /*
         * URL that points to repository. 
         */
        SVNURL url = SVNURL.parseURIEncoded("https://nucnocorpsvn.nucleussoftware.com:18080/svn/neo_implementations/hdfc_neo/branches/dev_hdfc_neo/hdfc_neo_3.0/custom_development/neo_intg/source_code/dbscript/Loan_Movement_Patch");
        /*
         * Credentials to use for authentication.
         */
      /*  String userName = "dipaliya.jain";
        String userPassword = "mar@2020";
        */
        System.out.println("SVN 1 : URL IS : "+url);
        System.out.println("username : "+username);
        System.out.println("username : "+password);
        
        /*deepali 
         * Sample file contents.
         */
     /*   byte[] content = "This is test data".getBytes();
        
        try {
			
			FileInputStream ips = new FileInputStream("D:/SVN_CHECKOUT/dev_hdfc_neo/hdfc_neo_3.0/custom_development/neo_intg/source_code/dbscript/Loan_Movement_Patch/test4/file2.doc"); 
			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream(); 

			int nRead; 
			byte[] data = new byte[16384]; 

			while ((nRead = ips.read(data, 0, data.length)) != -1) { 
			  buffer.write(data, 0, nRead); 
			} 

			buffer.flush(); 

			content =  buffer.toByteArray(); 
			
						
		} catch (Exception e) {
			e.printStackTrace();
		}
*deepali /        
      //  byte[] newContent = "\n261 \t16/08/19\tAneesha\tNEOSFR-1000\tP-1605-HDFC-PDF_NEOSFR-1207_16.08.2019_295\tY\t17/08/19\tY\t17/08/19".getBytes();
        
        byte[] newContent = "New content.".getBytes();
        
        byte[] finalContent = new byte[content.length + newContent.length];

        System.arraycopy(content, 0, finalContent, 0, content.length);

        // copy mac into end of destination (from pos ciphertext.length, copy mac.length bytes)
        System.arraycopy(newContent, 0, finalContent, content.length, newContent.length);
        
        String fileData = new String(newContent)     ;
        
        System.out.println(fileData);
        
        fileData = fileData.replace("kkkkkkkkkkkkkkkkkkkk", "Not Applicable as we are sharing this release for onsite SIT.");
        
        System.out.println(fileData);
        
        newContent = fileData.getBytes();
        
        /*
         * Create an instance of SVNRepository class. This class is the main entry point 
         * for all "low-level" Subversion operations supported by Subversion protocol. 
         * 
         * These operations includes browsing, update and commit operations. See 
         * SVNRepository methods javadoc for more details.
         */
        SVNRepository repository = SVNRepositoryFactory.create(url);
        
        System.out.println("SVN 2 : repository is :"+repository);
        

        /*
         * User's authentication information (name/password) is provided via  an 
         * ISVNAuthenticationManager  instance.  SVNWCUtil  creates  a   default 
         * authentication manager given user's name and password.
         * 
         * Default authentication manager first attempts to use provided user name 
         * and password and then falls back to the credentials stored in the 
         * default Subversion credentials storage that is located in Subversion 
         * configuration area. If you'd like to use provided user name and password 
         * only you may use BasicAuthenticationManager class instead of default 
         * authentication manager:
         * 
         *  authManager = new BasicAuthenticationsManager(userName, userPassword);
         *  
         * You may also skip this point - anonymous access will be used. 
         */
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username, password);
        
        System.out.println("SVN 3 : authManager is :"+authManager);
    
        
      /*  try{*/
        repository.setAuthenticationManager(authManager);
        
        System.out.println("SVN 4 : authentication set"+repository.getAuthenticationManager());

        /*
         * Get type of the node located at URL we used to create SVNRepository.
         * 
         * "" (empty string) is path relative to that URL, 
         * -1 is value that may be used to specify HEAD (latest) revision.
         */
        System.out.println("SVN4_1");
       /* repository.testConnection();
        }
        catch(SVNException e){
        	System.err.println("exception is : "+e);
        	if(String.valueOf(e).contains("Authentication")){
        		System.out.println("Invalid username Password !!");
        	}
        	
        }*/
        System.out.println("SVN4_2 : "+repository.checkPath("", -1));
        SVNNodeKind nodeKind = repository.checkPath("", -1);
        System.out.println("SVN 5 : nodeKind ");

        /*
         * Checks  up  if the current path really corresponds to a directory. If 
         * it doesn't, the program exits. SVNNodeKind is that one who says  what
         * is located at a path in a revision. 
         */
        if (nodeKind == SVNNodeKind.NONE) {
            SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.UNKNOWN, "No entry at URL ''{0}''", url);
            throw new SVNException(err);
        } else if (nodeKind == SVNNodeKind.FILE) {
            SVNErrorMessage err = SVNErrorMessage.create(SVNErrorCode.UNKNOWN, "Entry at URL ''{0}'' is a file while directory was expected", url);
            throw new SVNException(err);
        }
        
        
        long latestRevision = repository.getLatestRevision();
        System.out.println("Repository latest revision (before committing): " + latestRevision);
        
        ISVNEditor editor;
        SVNCommitInfo commitInfo;
/*        editor = repository.getCommitEditor("directory and file added", null);

        commitInfo = addDir(editor, "test4", "test4/file2.docx", content);
        System.out.println("The directory was added: " + commitInfo);
*/
       
  /*     deepali  
        editor = repository.getCommitEditor("file contents changed", null);
        
        commitInfo = modifyFile(editor, "test4", "test4/file2.doc", content, finalContent);
        
        System.out.println("The file was changed: " + commitInfo); deepali */

  
   
/*        String absoluteSrcPath = repository.getRepositoryPath("test");
        long srcRevision = repository.getLatestRevision();

        editor = repository.getCommitEditor("directory copied", null);        
        
        commitInfo = copyDir(editor, absoluteSrcPath, "test2", srcRevision);
        System.out.println("The directory was copied: " + commitInfo);

        
         * Delete directory "test".
         
        editor = repository.getCommitEditor("directory deleted", null);
        commitInfo = deleteDir(editor, "test");
        System.out.println("The directory was deleted: " + commitInfo);

        
         * And directory "test2".
         
        editor = repository.getCommitEditor("copied directory deleted", null);
        commitInfo = deleteDir(editor, "test2");
        System.out.println("The copied directory was deleted: " + commitInfo);
        
        latestRevision = repository.getLatestRevision();
        System.out.println("Repository latest revision (after committing): " + latestRevision);*/
    }

    /*
     * This method performs commiting an addition of a  directory  containing  a
     * file.
     */
    private static SVNCommitInfo addDir(ISVNEditor editor, String dirPath,
            String filePath, byte[] data) throws SVNException {
        /*
         * Always called first. Opens the current root directory. It  means  all
         * modifications will be applied to this directory until  a  next  entry
         * (located inside the root) is opened/added.
         * 
         * -1 - revision is HEAD (actually, for a comit  editor  this number  is 
         * irrelevant)
         */
        editor.openRoot(-1);
        /*
         * Adds a new directory (in this  case - to the  root  directory  for 
         * which the SVNRepository was  created). 
         * Since this moment all changes will be applied to this new  directory.
         * 
         * dirPath is relative to the root directory.
         * 
         * copyFromPath (the 2nd parameter) is set to null and  copyFromRevision
         * (the 3rd) parameter is set to  -1  since  the  directory is not added 
         * with history (is not copied, in other words).
         */
        editor.addDir(dirPath, null, -1);
        /*
         * Adds a new file to the just added  directory. The  file  path is also 
         * defined as relative to the root directory.
         *
         * copyFromPath (the 2nd parameter) is set to null and  copyFromRevision
         * (the 3rd parameter) is set to -1 since  the file is  not  added  with 
         * history.
         */
        editor.addFile(filePath, null, -1);
        /*
         * The next steps are directed to applying delta to the  file  (that  is 
         * the full contents of the file in this case).
         */
        editor.applyTextDelta(filePath, null);
        /*
         * Use delta generator utility class to generate and send delta
         * 
         * Note that you may use only 'target' data to generate delta when there is no 
         * access to the 'base' (previous) version of the file. However, using 'base' 
         * data will result in smaller network overhead.
         * 
         * SVNDeltaGenerator will call editor.textDeltaChunk(...) method for each generated 
         * "diff window" and then editor.textDeltaEnd(...) in the end of delta transmission.  
         * Number of diff windows depends on the file size. 
         *  
         */
        SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
        String checksum = deltaGenerator.sendDelta(filePath, new ByteArrayInputStream(data), editor, true);

        /*
         * Closes the new added file.
         */
        editor.closeFile(filePath, checksum);
        /*
         * Closes the new added directory.
         */
        editor.closeDir();
        /*
         * Closes the root directory.
         */
        editor.closeDir();
        /*
         * This is the final point in all editor handling. Only now all that new
         * information previously described with the editor's methods is sent to
         * the server for committing. As a result the server sends the new
         * commit information.
         */
        return editor.closeEdit();
    }

    /*
     * This method performs committing file modifications.
     */
    private static SVNCommitInfo modifyFile(ISVNEditor editor, String dirPath,
            String filePath, byte[] oldData, byte[] newData) throws SVNException {
        /*
         * Always called first. Opens the current root directory. It  means  all
         * modifications will be applied to this directory until  a  next  entry
         * (located inside the root) is opened/added.
         * 
         * -1 - revision is HEAD
         */
        editor.openRoot(-1);
        /*
         * Opens a next subdirectory (in this example program it's the directory
         * added  in  the  last  commit).  Since this moment all changes will be
         * applied to this directory.
         * 
         * dirPath is relative to the root directory.
         * -1 - revision is HEAD
         */
        editor.openDir(dirPath, -1);
        /*
         * Opens the file added in the previous commit.
         * 
         * filePath is also defined as a relative path to the root directory.
         */
        editor.openFile(filePath, -1);
        
        /*
         * The next steps are directed to applying and writing the file delta.
         */
        editor.applyTextDelta(filePath, null);
        
        /*
         * Use delta generator utility class to generate and send delta
         * 
         * Note that you may use only 'target' data to generate delta when there is no 
         * access to the 'base' (previous) version of the file. However, here we've got 'base' 
         * data, what in case of larger files results in smaller network overhead.
         * 
         * SVNDeltaGenerator will call editor.textDeltaChunk(...) method for each generated 
         * "diff window" and then editor.textDeltaEnd(...) in the end of delta transmission.  
         * Number of diff windows depends on the file size. 
         *  
         */
        SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
      //  String checksum = deltaGenerator.sendDelta(filePath, new ByteArrayInputStream(oldData), 0, new ByteArrayInputStream(newData), editor, true);
        
        String checksum = deltaGenerator.sendDelta(filePath, new ByteArrayInputStream(oldData), 0, new ByteArrayInputStream(newData), editor, true);

        /*
         * Closes the file.
         */
        editor.closeFile(filePath, checksum);

        /*
         * Closes the directory.
         */
        editor.closeDir();

        /*
         * Closes the root directory.
         */
        editor.closeDir();

        /*
         * This is the final point in all editor handling. Only now all that new
         * information previously described with the editor's methods is sent to
         * the server for committing. As a result the server sends the new
         * commit information.
         */
        return editor.closeEdit();
    }

    /*
     * This method performs committing a deletion of a directory.
     */
    private static SVNCommitInfo deleteDir(ISVNEditor editor, String dirPath) throws SVNException {
        /*
         * Always called first. Opens the current root directory. It  means  all
         * modifications will be applied to this directory until  a  next  entry
         * (located inside the root) is opened/added.
         * 
         * -1 - revision is HEAD
         */
        editor.openRoot(-1);
        /*
         * Deletes the subdirectory with all its contents.
         * 
         * dirPath is relative to the root directory.
         */
        editor.deleteEntry(dirPath, -1);
        /*
         * Closes the root directory.
         */
        editor.closeDir();
        /*
         * This is the final point in all editor handling. Only now all that new
         * information previously described with the editor's methods is sent to
         * the server for committing. As a result the server sends the new
         * commit information.
         */
        return editor.closeEdit();
    }

    /*
     * This  method  performs how a directory in the repository can be copied to
     * branch.
     */
    private static SVNCommitInfo copyDir(ISVNEditor editor, String srcDirPath,
            String dstDirPath, long revision) throws SVNException {
        /*
         * Always called first. Opens the current root directory. It  means  all
         * modifications will be applied to this directory until  a  next  entry
         * (located inside the root) is opened/added.
         * 
         * -1 - revision is HEAD
         */
        editor.openRoot(-1);
        
        /*
         * Adds a new directory that is a copy of the existing one.
         * 
         * srcDirPath   -  the  source  directory  path (relative  to  the  root 
         * directory).
         * 
         * dstDirPath - the destination directory path where the source will be
         * copied to (relative to the root directory).
         * 
         * revision    - the number of the source directory revision. 
         */
        editor.addDir(dstDirPath, srcDirPath, revision);
        /*
         * Closes the just added copy of the directory.
         */
        editor.closeDir();
        /*
         * Closes the root directory.
         */
        editor.closeDir();
        /*
         * This is the final point in all editor handling. Only now all that new
         * information previously described with the editor's methods is sent to
         * the server for committing. As a result the server sends the new
         * commit information.
         */
        return editor.closeEdit();
    }

    /*
     * Initializes the library to work with a repository via 
     * different protocols.
     */
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
 
}