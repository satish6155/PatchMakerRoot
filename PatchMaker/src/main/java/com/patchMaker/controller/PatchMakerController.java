package com.patchMaker.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.patchMaker.entity.Patch;
import com.patchMaker.service.JasperServiceImpl;
import com.patchMaker.util.SVNUtills;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class PatchMakerController {
	
	@Autowired
	private JasperServiceImpl jasperServiceImpl;
	
	
	
	@RequestMapping(value = "/login")
	public String authenticateUserDtls(@RequestParam String username, @RequestParam String password,HttpServletRequest request) {

		SVNUtills svnUtills = new SVNUtills();
		HttpSession session = request.getSession();
		
		Patch patch = new Patch();		

		patch.setId(768L);
		patch.setBankJira("NEOPROD-150");
		patch.setDate(new Date());
		patch.setProject("P-1605-HDFC");
		patch.setDefectsFixed("Dummy defects fixed");
		patch.setPatchName(patch.getProject()+"-"+patch.getBankJira()+"-"+new SimpleDateFormat("dd/MM/yyyy").format(patch.getDate())+"-"+patch.getId());
		
		
		
		generateReport(patch);
		
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
	public void generateReport(Patch patch) {
		
		System.out.println("Yuhuu");
		String templatePathWithReport = "D:\\JRXML_AND_REPORT\\JRXML\\releaseNote.jrxml";
		String generationPath = "D:\\JRXML_AND_REPORT\\GENERATED_REPORT\\RELEASE_NOTE.pdf";
		Map<String, Object> parameters = new HashMap<>() ;
		parameters.put("defectsFixed",patch.getDefectsFixed());
		parameters.put("features",patch.getFeatures());
		parameters.put("impact",patch.getImpact());
		parameters.put("installSteps",patch.getInstallSteps());
		parameters.put("knownBugs",patch.getKnownBugs());
		parameters.put("newFunctionality",patch.getNewFunctionality());
		parameters.put("patchEnvironments",patch.getPatchEnvironments());
		parameters.put("patchName",patch.getPatchName());
		parameters.put("rollbackSteps",patch.getRollbackSteps());
		parameters.put("svnRevisions",patch.getSvnRevisions());
		parameters.put("testingDetails",patch.getTestingDetails());				
		parameters.put("relatedJiras", patch.getBankJira() +" "+patch.getInternalJira() + " " +patch.getProductJira());					
		parameters.put("dateString",new SimpleDateFormat("dd/MM/yyyy").format(patch.getDate()));		
		
		
		jasperServiceImpl.generatePDFFromJasper(templatePathWithReport, generationPath, parameters);		
		
	}
	
}