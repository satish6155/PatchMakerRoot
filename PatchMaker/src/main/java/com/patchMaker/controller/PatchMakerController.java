package com.patchMaker.controller;


import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.patchMaker.service.JasperServiceImpl;
import com.patchMaker.util.SVNUtills;

@Controller
public class PatchMakerController {
	
	@Autowired
	private JasperServiceImpl jasperServiceImpl;
	
	
	
	@RequestMapping(value = "/login")
	public String authenticateUserDtls(@RequestParam String username, @RequestParam String password,HttpServletRequest request) {

		SVNUtills svnUtills = new SVNUtills();
		HttpSession session = request.getSession();
		
		generateReport();
		
		if(username.equalsIgnoreCase("test")){
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			return "patchMaker";
		}

		String errorMsg = svnUtills.authenticateUser(username, password);
		
		if(null != errorMsg){
			
			session.setAttribute("errorMsg", errorMsg);
			session.setAttribute("isValidUser", false);
			return "login";
		}
		else{
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			return "patchMaker";
		}	
	}
	
	@RequestMapping(value = "/logout")
	public String logOutUser(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		request.getSession().invalidate();
		
		return "login";
	}
	
	@RequestMapping(value = "/generateReport")
	public void generateReport() {
		
		System.out.println("Yuhuu");
		String templatePathWithReport = "D:\\JRXML_AND_REPORT\\JRXML\\releaseNote.jrxml";
		String generationPath = "D:\\JRXML_AND_REPORT\\GENERATED_REPORT\\RELEASE_NOTE.PDF";
		Map<String, Object> parameters = null ;
		
		jasperServiceImpl.generatePDFFromJasper(templatePathWithReport, generationPath, parameters);
			
		
		
	}
	
}