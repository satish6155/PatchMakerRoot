package com.patchMaker.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.patchMaker.util.SVNUtills;

@Controller
public class PatchMakerController {
	
	/*@Autowired
	private PatchServiceImpl patchServiceImpl;*/
	
	@RequestMapping(value = "/login")
	public String authenticateUserDtls(@RequestParam String username, @RequestParam String password,HttpServletRequest request) {

		SVNUtills svnUtills = new SVNUtills();
		HttpSession session = request.getSession();

		String errorMsg = svnUtills.authenticateUser(username, password);
		
		if(null != errorMsg){
			
			session.setAttribute("errorMsg", errorMsg);
			session.setAttribute("isValidUser", false);
			return "Login";
		}
		else{
			session.setAttribute("username", username);
			return "patchMaker";
		}	
	}
	
	@RequestMapping(value = "/logout")
	public String logOutUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		request.getSession().invalidate();
		
		return "Login";
	}
	
}