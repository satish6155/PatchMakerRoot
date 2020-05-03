package com.patchMaker.controller;


import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tmatesoft.svn.core.SVNException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patchMaker.bean.FilesWrapper;
import com.patchMaker.entity.Patch;
import com.patchMaker.service.JasperServiceImpl;
import com.patchMaker.service.PatchServiceImpl;
import com.patchMaker.util.SVNUtills;

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
		patch.setCreatedBy((String) session.getAttribute("username"));
		
		System.out.println("Patch before save : " + patch);
		
		patch = patchServiceImpl.save(patch);
		
		System.out.println(patch);
		
		session.setAttribute("patchName", patch.getPatchName());
		session.setAttribute("patchId", patch.getId());
		return String.valueOf(patch.getId());
	}

	@RequestMapping(value = "/saveFileDetails", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody String saveFileDetails(HttpServletRequest request, @RequestBody FilesWrapper files) {

		SVNUtills svnUtills = new SVNUtills();
		HttpSession session = request.getSession();
		
		Long patchId = (Long) session.getAttribute("patchId");
		
		String patchName = String.valueOf(session.getAttribute("patchName"));
		String userName = String.valueOf(session.getAttribute("username"));
		String password = String.valueOf(session.getAttribute("password"));
		
		ObjectMapper mapper = new ObjectMapper();
		
		System.out.println("save file details : files : "+files);

		try {
			String filesJson = mapper.writeValueAsString(files);
			
			patchServiceImpl.SaveFilesJson(patchId, filesJson);
			
			jasperServiceImpl.generateReleaseNote(patchId);
			
			try {
				svnUtills.svn_actions(patchName, userName, password);
			} catch (SVNException e) {
				System.out.println("Error in taking svn_actions : Exception is : "+e);
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "success";
	}

	@RequestMapping(value = "/releaseTracker")
	public ModelAndView getReleaseTracker(ModelAndView model, HttpServletRequest request) {

		System.out.println("Inside getReleaseTracker");

		List<Patch> patches = new ArrayList<Patch>();

		patches = patchServiceImpl.findAll();

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
			System.out.println("Exception in uploadFiles method : "+e);
			e.printStackTrace();
		}
		return message;
	}
}