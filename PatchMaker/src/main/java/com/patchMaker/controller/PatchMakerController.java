package com.patchMaker.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.patchMaker.service.PatchServiceImpl;

/*not in use now*/
@Controller
public class PatchMakerController {
	
	@Autowired
	private PatchServiceImpl patchServiceImpl;
	
	
	@RequestMapping(value="/HomePage/{username}",produces="application/json")
	public ModelAndView patchMakerJsp(ModelAndView modelAndView,@PathVariable("username") String username) {
		
		System.out.println("In controller patchMakerJsp method patchMakerJsp, username: "+username);
				
		modelAndView.addObject("username",username);			
		modelAndView.setViewName("patchMaker");
		return modelAndView;
		
	}
	
	//Model and View Example Below
	
	/*@RequestMapping(value="/{clientId}/getPatchById/{patchId}",produces="application/json")
	public ModelAndView patchJspController(ModelAndView model,@PathVariable("patchId") long patchId) {
		
		System.out.println("In controller PatchControllerWebApp method patchJspController, patchId: "+patchId);
		Patch patch = new Patch();
		patch = patchServiceImpl.findOne(patchId);
		
		if(null!=patch){
			model.addObject("patch",patch);			
			model.setViewName("WaterPatch");
			return model;
		}
		return new ModelAndView("ErrorPage");	    
	}*/
	
	//Post method Example below
	
	/*
	@RequestMapping(value="/updatePumpStatus",produces="application/json",method=RequestMethod.POST)
    public String updatePumpStatus(String patchId,boolean pumpStatus)
    {
		long actualPumpStatus = 0 ;
		System.out.println("In updatePumpStatus");
		System.out.println("patchId :"+patchId);
		System.out.println("pumpStatus : "+pumpStatus);
		
		if(pumpStatus){
			actualPumpStatus = 1;
		}
		
		System.out.println("In actualPumpStatus"+actualPumpStatus);
		return Integer.toString(patchServiceImpl.updatePumpStatus(patchId,actualPumpStatus));
    }
*/	
	
}