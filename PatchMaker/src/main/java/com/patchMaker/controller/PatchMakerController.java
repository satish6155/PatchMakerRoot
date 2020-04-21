package com.patchMaker.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.patchMaker.entity.FileUpload;
import com.patchMaker.entity.Patch;
import com.patchMaker.service.JasperServiceImpl;
import com.patchMaker.service.PatchServiceImpl;
import com.patchMaker.util.SVNUtills;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
public class PatchMakerController {
	
	@Autowired
	private JasperServiceImpl jasperServiceImpl;
	
	@Autowired
	private PatchServiceImpl patchServiceImpl;
	
	
	
	@RequestMapping(value = "/login")
	public String authenticateUserDtls(@RequestParam String username, @RequestParam String password,HttpServletRequest request) {

		SVNUtills svnUtills = new SVNUtills();
		HttpSession session = request.getSession();
		
		Patch patch = new Patch();		

		patch.setId(768L);
		patch.setBankJira("NEOPROD-150");
		patch.setDate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		patch.setProject("P-1605-HDFC");
		patch.setDefectsFixed("Dummy defects fixed");
		patch.setPatchName(patch.getProject()+"_"+patch.getBankJira()+"_"+patch.getDate()+"_"+patch.getId());
		
		//generateReport(patch);
		
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
	@RequestMapping(value="/savePatchDetails",produces="application/json",method=RequestMethod.POST)
    public String updatePumpStatus(Patch patch) {
		
		
		System.out.println(patch);
		return "yoyo";
	}
	
	@RequestMapping(value = "/releaseTracker")
	public ModelAndView getReleaseTracker(ModelAndView model, HttpServletRequest request) {

		System.out.println("Inside getReleaseTracker");
		
		List<Patch> patches = new ArrayList<Patch>();
		
		//patches = patchServiceImpl.findAll();
		patches = getDummyPatches();   //this will be commented and above will be uncommented for actual data fetch
		
		Map<String,Patch> patchesMap = new HashMap<String,Patch>();
		
		for (Patch thisPatch : patches) {
			patchesMap.put(String.valueOf(thisPatch.getId()), thisPatch);
		}		
		
		 HttpSession session = request.getSession();		   
		    if(session.getAttribute("username") == null){
		    	System.out.println("Username is null");
		    	session.setAttribute("username", "Guest");
		    }
		
	 	model.addObject("patches", patchesMap);
		model.setViewName("releaseTracker");
		return model;
		
	}
	
	private List<Patch> getDummyPatches(){
		List<Patch> patches = new ArrayList<Patch>();
		
		Patch patch = new Patch();		

		patch.setId(768L);
		patch.setBankJira("NEOPROD-150");
		patch.setDate(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
		patch.setProject("P-1605-HDFC");
		patch.setDefectsFixed("Dummy defects fixed");
		patch.setPatchName(patch.getProject()+"_"+patch.getBankJira()+"_"+patch.getDate()+"_"+patch.getId());
		
			
		patches.add(patch);
		
		return patches;
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
		parameters.put("patchEnvironments",patch.getEnvironments());
		parameters.put("patchName",patch.getPatchName());
		parameters.put("rollbackSteps",patch.getRollbackSteps());
		parameters.put("svnRevisions",patch.getSvnRevisions());
		parameters.put("testingDetails",patch.getTestingDetails());				
		parameters.put("relatedJiras", patch.getBankJira() +" "+patch.getInternalJira() + " " +patch.getProductJira());					
		parameters.put("dateString",new SimpleDateFormat("dd/MM/yyyy").format(patch.getDate()));		
		
		
		jasperServiceImpl.generatePDFFromJasper(templatePathWithReport, generationPath, parameters);		
		
	}
	@RequestMapping(value = "/demo/{fileType}",method=RequestMethod.POST)
	public @ResponseBody String uploadFiles(@PathVariable String fileType,HttpServletRequest request,FileUpload fileUpload) throws IOException {
		 String message = null;
		 System.out.println("fileType:1: "+fileType);
		 System.out.println("fileType:2: "+fileUpload.getFileType());
		 System.out.println(":getParameter from js :"+request.getParameter("patchFileType"));
		 System.out.println(":getParameter from jsp :"+request.getParameter("patchFile"));
		 String fileType1 = fileType(fileType);
		 HttpSession session = request.getSession();		   
		try {
			String  username = (String) session.getAttribute("username");
			String patchName=getPatchName(username);
			message = patchServiceImpl.extracted(request,fileType1,patchName);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  message;
		
	}

	private String getPatchName(String username) {
			String name="";
			name=username;
			return name;
	}

	private String fileType(String fileType) {
		System.out.println("fileType:: "+fileType);
		String name ="Other";
		if((".Class").equalsIgnoreCase(fileType)) {
			name="Class_Files";
		}else if(("DB_Script").equalsIgnoreCase(fileType)){
			name="DB_Script";
		}else if(("Property").equalsIgnoreCase(fileType)) {
			name="Property";
		}
		return name;
	}

}