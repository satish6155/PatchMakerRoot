package com.patchMaker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tmatesoft.svn.core.SVNErrorMessage;
import org.tmatesoft.svn.core.SVNException;

import com.patchMaker.entity.PatchDetail;
import com.patchMaker.util.SVNUtills;

@Controller
public class MainController {
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/svnloginController")
	public String authenticateUserDtls(@RequestParam String username,@RequestParam String password,HttpServletRequest request) {
		
		System.out.println("HI");
	    
		   PatchDetail patchDetail = new PatchDetail();
		   
		   SVNUtills svnUtills = new SVNUtills();
		   
		   System.out.println("kaaye"); 
		   System.out.println("username is : "+username);
		   System.out.println("password is : "+password);
		   
		   try {
			   System.out.println("Going in ");  
			     svnUtills.svn_actions(patchDetail,username,password);
			     System.out.println("Coming out");  
		   } catch (SVNException e) {
	            SVNErrorMessage err = e.getErrorMessage();
	            
	            HttpSession session = request.getSession();
				session.setAttribute("isValidUser", false);
	           	            
	            /* * Display all tree of error messages. 
	             * Utility method SVNErrorMessage.getFullMessage() may be used instead of the loop.*/
	             
	            while(err != null) {
	                System.err.println(err.getErrorCode().getCode() + " : " + err.getMessage());
	            if(err.getMessage().contains("Authentication"))    {
	            	System.err.println("Invalid Username or Password!!");
	            	session.setAttribute("errorMsg", "Invalid User name or Password!!");
	            }
	            else if(err.getErrorCode().getCode() == 175002) {
	            	session.setAttribute("errorMsg", "SVN is down please check later");
	            }
	                err = err.getChildErrorMessage();
	            }
	            System.out.println("before exit 1");  
	           // System.exit(1);
	            System.out.println("after exit 1");
	            
	            
				System.out.println("valid user is : "+session.getAttribute("isValidUser"));
				System.out.println("errorMsg is : "+session.getAttribute("errorMsg"));
	            return "Login";
	        }
		   System.out.println("before exit 0");
	      //  System.exit(0);
	        System.out.println("after exit 0");
	
	
		 System.out.println("before login jsp");  
		   return "patchMaker";

}
	
	}
