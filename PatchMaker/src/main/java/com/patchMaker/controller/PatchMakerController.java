package com.patchMaker.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.patchMaker.entity.Patch;
import com.patchMaker.service.JasperServiceImpl;
import com.patchMaker.service.PatchServiceImpl;
import com.patchMaker.util.SVNUtills;
import com.patchMaker.util.Values;

@Controller
public class PatchMakerController {

	@Autowired
	private JasperServiceImpl jasperServiceImpl;

	@Autowired
	private PatchServiceImpl patchServiceImpl;

	@RequestMapping(value = "/login")
	public String authenticateUserDtls(@RequestParam String username, @RequestParam String password,
			HttpServletRequest request) {

		SVNUtills svnUtills = new SVNUtills();
		HttpSession session = request.getSession();

		Patch patch = new Patch();

		patch = patchServiceImpl.findOne(50000L);
		
		/*patch.setId(768L);
		patch.setBankJira("NEOPROD-150");
		patch.setPatchDate("2020-04-24");
		patch.setProject("P-1605-HDFC");
		patch.setDefectsFixed("Dummy defects fixed");
		patch.setPatchName(patch.getProject()+"_"+patch.getBankJira()+"_"+patch.getPatchDate()+"_"+patch.getId());*/

		// generateReport(patch);

		if (username.equalsIgnoreCase("test")) {
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			return "patchMaker";
		}

		String errorMsg = svnUtills.authenticateUser(username, password);

		if (null != errorMsg) {

			session.setAttribute("errorMsg", errorMsg);
			session.setAttribute("isValidUser", false);
			return "login";
		} else {
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			return "patchMaker";
		}
	}

	@RequestMapping(value = "/logout")
	public String logOutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.getSession().invalidate();

		return "login";
	}

	@RequestMapping(value = "/savePatchDetails", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody String savePatchDetails(HttpServletRequest request, Patch patch) {

		HttpSession session = request.getSession();
		session.setAttribute("patchName", patch.getPatchName());

		patchServiceImpl.save(patch);
		System.out.println(patch);
		return String.valueOf(patch.getId());
	}

	@RequestMapping(value = "/releaseTracker")
	public ModelAndView getReleaseTracker(ModelAndView model, HttpServletRequest request) {

		System.out.println("Inside getReleaseTracker");

		List<Patch> patches = new ArrayList<Patch>();

		patches = patchServiceImpl.findAll();
		//patches = getDummyPatches(); // this will be commented and above will be uncommented for actual data fetch

		Map<String, Patch> patchesMap = new HashMap<String, Patch>();

		for (Patch thisPatch : patches) {
			patchesMap.put(String.valueOf(thisPatch.getId()), thisPatch);
		}

		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			System.out.println("Username is null");
			session.setAttribute("username", "Guest");
		}

		model.addObject("patches", patchesMap);
		model.setViewName("releaseTracker");
		return model;

	}

	@RequestMapping(value = "/fileBrowser")
	public ModelAndView filebrowser(ModelAndView model, HttpServletRequest request) {
		model.setViewName("browser");
		return model;
	}

	private List<Patch> getDummyPatches() {
		List<Patch> patches = new ArrayList<Patch>();

		Patch patch = new Patch();

		patch.setId(768L);
		patch.setBankJira("NEOPROD-150");
		patch.setPatchDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		patch.setProject("P-1605-HDFC");
		patch.setDefectsFixed("Dummy defects fixed");
		patch.setPatchName(
				patch.getProject() + "_" + patch.getBankJira() + "_" + patch.getPatchDate() + "_" + patch.getId());

		patches.add(patch);

		return patches;
	}

	@RequestMapping(value = "/generateReport")
	public void generateReport(Patch patch) {

		System.out.println("Yuhuu");
		String templatePathWithReport = Values.JRXML_DIRECTORY + File.separator + Values.RELEASE_NOTE_JRXML_NAME;
		String generationPath = Values.RELEASE_NOTE_PDF_DIRECTORY + File.separator + Values.RELEASE_NOTE_PDF_NAME;
		/*
		
		String templatePathWithReport = "D:\\PatchMakerRoot\\JRXML_AND_REPORT\\JRXML\\releaseNote.jrxml";
		String generationPath = "D:\\PatchMakerRoot\\JRXML_AND_REPORT\\GENERATED_REPORT\\RELEASE_NOTE.pdf";*/
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("defectsFixed", patch.getDefectsFixed());
		parameters.put("features", patch.getFeatures());
		parameters.put("impact", patch.getImpact());
		parameters.put("installSteps", patch.getInstallSteps());
		parameters.put("knownBugs", patch.getKnownBugs());
		parameters.put("newFunctionality", patch.getNewFunctionality());
		parameters.put("patchEnvironments", patch.getEnvironments());
		parameters.put("patchName", patch.getPatchName());
		parameters.put("rollbackSteps", patch.getRollbackSteps());
		parameters.put("svnRevisions", patch.getSvnRevisions());
		parameters.put("testingDetails", patch.getTestingDetails());
		parameters.put("relatedJiras",
				patch.getBankJira() + " " + patch.getInternalJira() + " " + patch.getProductJira());
		parameters.put("dateString", patch.getPatchDate());

		jasperServiceImpl.generatePDFFromJasper(templatePathWithReport, generationPath, parameters);

	}

	@RequestMapping(value = "/uploadFiles/{fileType}", method = RequestMethod.POST)
	public @ResponseBody String uploadFiles(HttpServletRequest request, @PathVariable String fileType,
			HttpServletResponse response) throws IOException {

		String message = null;
		
		HttpSession session = request.getSession();
		String patchName = (String) session.getAttribute("patchName");

		System.out.println("patchName : " + patchName);
		
				

		try {
			message = patchServiceImpl.writeFiles(request, fileType, patchName);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;

	}

}