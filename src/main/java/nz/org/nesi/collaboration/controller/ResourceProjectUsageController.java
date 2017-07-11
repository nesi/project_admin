package nz.org.nesi.collaboration.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import nz.org.nesi.collaboration.model.Project;
import nz.org.nesi.collaboration.model.ResourceInstance;
import nz.org.nesi.collaboration.model.ResourceProjectUsage;
import nz.org.nesi.collaboration.service.AdviserService;
import nz.org.nesi.collaboration.service.ProjectService;
import nz.org.nesi.collaboration.service.ResourceProjectUsageService;
import nz.org.nesi.collaboration.vo.AdviserVO;
import nz.org.nesi.collaboration.vo.ResourceProjectUsageVO;

@Controller
public class ResourceProjectUsageController {
//	@Autowired
//	DataSource dataSource;
	
	@Autowired
	private AdviserService adviserService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ResourceProjectUsageService resourceProjectUsageService;
	
	@RequestMapping(value = "/scientific_programmer" , method = RequestMethod.GET)
	public ModelAndView viewCaseStudy(Map<String, Object> model, HttpServletRequest request) {
		if(!(Boolean) request.getAttribute("isAdviser")){
        	return new ModelAndView("redirect:login");
        }else{
			ModelAndView mav = new ModelAndView("generic_resources");
			List<ResourceProjectUsageVO> resourceProjectUsageList = resourceProjectUsageService.listAll();
	        mav.addObject("ojbects",resourceProjectUsageList);
	        return mav;
	    }
	}
	
	@GetMapping("/add_scientific_programmer_usage")
	public ModelAndView newCaseStudy(Map<String, Object> model, HttpServletRequest request) {
		if(!(Boolean) request.getAttribute("isAdviser")){
        	return new ModelAndView("redirect:login");
        }else{
			ModelAndView mav = new ModelAndView("new_generic_resource");
			List<String> projectCodes = projectService.findProjectCodes();
			mav.addObject("genericresource", new ResourceProjectUsageVO());
			mav.addObject("edit", false);
			mav.addObject("projectCodes", projectCodes);
			mav.addObject("adviserList", adviserService.findAll());
			mav.addObject("resourceList", resourceProjectUsageService.findAllResourceInstance());
			System.out.println("Add generic resource");
			return mav;
        }
	}
	
	@GetMapping("/edit_scientific_programmer_usage")
	public ModelAndView editGenericResource(@RequestParam("id") int id, Map<String, Object> model, HttpServletRequest request) {	
		if(!(Boolean) request.getAttribute("isAdviser")){
        	return new ModelAndView("redirect:login");
        }else{
        	ModelAndView mav = new ModelAndView("edit_scientific_programmer_usage");
		    ResourceProjectUsage resourceProjectUsage = resourceProjectUsageService.getResourceProjectUsage(id);
			if(resourceProjectUsage != null){
				ResourceProjectUsageVO resourceProjectUsageVO = new ResourceProjectUsageVO();
				resourceProjectUsageVO.setId(resourceProjectUsage.getId());
				resourceProjectUsageVO.setAdviser(resourceProjectUsage.getAdviser().getFullName());
				resourceProjectUsageVO.setAllocation(resourceProjectUsage.getAllocation());
				resourceProjectUsageVO.setDate(resourceProjectUsage.getDate());
				resourceProjectUsageVO.setNote(resourceProjectUsage.getNote());
				resourceProjectUsageVO.setProject(resourceProjectUsage.getProject().getProjectCode());
				resourceProjectUsageVO.setResourceInstance(resourceProjectUsage.getResourceInstance().getDescription());
				resourceProjectUsageVO.setValue(resourceProjectUsage.getValue());
				mav.addObject("resourceProjectUsage", resourceProjectUsageVO);
				mav.addObject("edit", true);
				List<String> projectCodes = projectService.findProjectCodes();
				mav.addObject("projectCodes", projectCodes);
				List<AdviserVO> adviserList = adviserService.findAll();
				List<ResourceInstance> instanceList = resourceProjectUsageService.findAllResourceInstance();
				if(adviserList != null){
					adviserList.removeIf(p -> p.getId() == resourceProjectUsage.getAdviser().getId());
				}
				if(instanceList != null){
					instanceList.removeIf(p -> p.getId() == resourceProjectUsage.getResourceInstance().getId());
				}
				mav.addObject("adviserList", adviserList);
				mav.addObject("selAdviserName", resourceProjectUsage.getAdviser().getFullName());
				mav.addObject("selAdviserId", resourceProjectUsage.getAdviser().getId());
				mav.addObject("resourceList", instanceList);
				mav.addObject("selResourceName", resourceProjectUsage.getResourceInstance().getDescription());
				mav.addObject("selResourceId", resourceProjectUsage.getResourceInstance().getId());
				mav.setViewName("new_generic_resource");
			}
			return mav;
        }	
	}
	
	@PostMapping("/add_scientific_programmer_usage")
	public ModelAndView addCaseStudy(@Valid @ModelAttribute("genericresource") ResourceProjectUsageVO resourceProjectUsage, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
        if(resourceProjectUsage.getProject() != null){
        	Project project = projectService.findProjectByCode(resourceProjectUsage.getProject());
        	if(project != null){
        		resourceProjectUsage.setProject(String.valueOf(project.getId()));
        	}else{
        	    modelAndView.addObject("edit", false);
        	    modelAndView.addObject("errormsg", "Please enter correct project code!");
                modelAndView.setViewName("new_generic_resource");
                return modelAndView;
        	}
        }
	    modelAndView.addObject("genericresource", resourceProjectUsage);	    
        if (result.hasErrors()) {
    		List<ObjectError> list = result.getAllErrors();
    	    for(ObjectError error : list){
    	        System.out.println(error.getObjectName()+"---"+error.getArguments().toString()+"---"+error.getDefaultMessage());
    	    }
    	    modelAndView.addObject("edit", false);
            modelAndView.setViewName("new_generic_resource");
        }else{
        	resourceProjectUsageService.addResourceProjectUsage(resourceProjectUsage);
            //after insertion logic 		
        	modelAndView.setViewName("redirect:/scientific_programmer");
        }     
		return modelAndView;
	}
	
	@PostMapping("/edit_scientific_programmer_usage")
	public ModelAndView updateCaseStudy(@Valid @ModelAttribute("resourceProjectUsage") ResourceProjectUsageVO resourceProjectUsage, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("edit", true);
		Project project = null;
        if(resourceProjectUsage.getProject() != null){
        	project = projectService.findProjectByCode(resourceProjectUsage.getProject());
        	if(project != null){
        		resourceProjectUsage.setProject(String.valueOf(project.getId()));
        	}
        }else{
    	    modelAndView.addObject("errormsg", "Please enter correct project code!");
            modelAndView.setViewName("new_generic_resource");
            return modelAndView;
        }
        if (result.hasErrors()) {
    		List<ObjectError> list = result.getAllErrors();
    	    for(ObjectError error : list){
    	        System.out.println(error.getObjectName()+"---"+error.getArguments().toString()+"---"+error.getDefaultMessage());
    	    }
    	    List<String> projectCodes = projectService.findProjectCodes();
    	    modelAndView.addObject("projectCodes", projectCodes);
    	    modelAndView.addObject("adviserList", adviserService.findAll());
    	    modelAndView.addObject("resourceList", resourceProjectUsageService.findAllResourceInstance());  	    
    	    modelAndView.addObject("resourceProjectUsage", resourceProjectUsage);
            modelAndView.setViewName("new_generic_resource");
        }else{
        	resourceProjectUsageService.updateResourceProjectUsage(resourceProjectUsage);        	
            modelAndView.setViewName("redirect:/scientific_programmer");
        }     
		return modelAndView;
	}
}