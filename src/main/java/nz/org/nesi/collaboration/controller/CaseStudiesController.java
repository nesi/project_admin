package nz.org.nesi.collaboration.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.databind.ObjectMapper;
import nz.org.nesi.collaboration.model.CaseStudy;
import nz.org.nesi.collaboration.model.Project;
import nz.org.nesi.collaboration.model.Researcher;
import nz.org.nesi.collaboration.service.AdviserService;
import nz.org.nesi.collaboration.service.CaseStudyService;
import nz.org.nesi.collaboration.service.ProjectService;
import nz.org.nesi.collaboration.util.Util;
import nz.org.nesi.collaboration.vo.CaseStudyVO;

@Controller
public class CaseStudiesController {
	@Autowired
	DataSource dataSource;
	
	@Autowired
	private CaseStudyService caseStudyService;
	
	@Autowired
	private AdviserService adviserService;
	
	@Autowired
	private ProjectService projectService;
	
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping("/")
	public ModelAndView welcome(Map<String, Object> model) {
		ModelAndView mav = new ModelAndView("welcome");
		mav.addObject("message", this.message);
		System.out.println("DATASOURCE = " + dataSource);
		System.out.println("Display casestudy");
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView loginRequired(Map<String, Object> model) {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "/case_studies" , method = RequestMethod.GET)
	public ModelAndView viewCaseStudy(Map<String, Object> model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("case_studies");
        if(!(Boolean) request.getAttribute("isAdviser")){
        	return new ModelAndView("redirect:login");
        }else{ 			
    		List<CaseStudyVO> caseStudyVOs = caseStudyService.listCaseStudy();
        	mav.addObject("noAdviser", false);
        	mav.addObject("ojbects",caseStudyVOs);
        }	
		return mav;
	}
	
	@GetMapping("/new_case_study")
	public ModelAndView newCaseStudy(Map<String, Object> model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("new_case_study");
		if(!(Boolean) request.getAttribute("isAdviser")){
        	return new ModelAndView("redirect:login");
        }else{
			List<String> projectCodes = projectService.findProjectCodes();
			mav.addObject("casestudy", new CaseStudy());
			mav.addObject("edit", false);
			//project codes lookup list
			mav.addObject("projectCodes", projectCodes);
			mav.addObject("statusList", caseStudyService.findAllStatus());
			//all the requesters for requesters dropdown
			mav.addObject("requesterList", adviserService.findAll());
			System.out.println("Add casestudy");
        }
		return mav;
	}
	
	@GetMapping("/edit_case_study")
	public ModelAndView editCaseStudy(@RequestParam("id") int id, Map<String, Object> model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("edit_case_study");
		if(!(Boolean) request.getAttribute("isAdviser")){
        	return new ModelAndView("redirect:login");
        }else{
			CaseStudyVO caseStudy = caseStudyService.getEditCaseStudy(id);
			if(caseStudy != null){
				mav.addObject("casestudy", caseStudy);
				Project project = projectService.findProjectByCode(caseStudy.getProject());
				mav.addObject("researcherList", project.getResearchers());
				if(caseStudy.getRejected() != null && !"".equals(caseStudy.getRejected())){
					mav.addObject("isReject", true);
				}
				if(caseStudy.getWithdrawn() != null && !"".equals(caseStudy.getWithdrawn())){
					mav.addObject("isWithdrawn", true);
				}
				mav.addObject("edit", true);
				List<String> projectCodes = projectService.findProjectCodes();
				mav.addObject("projectCodes", projectCodes);
				mav.addObject("statusList", caseStudyService.findAllStatus());
				mav.addObject("requesterList", adviserService.findAll());
				mav.setViewName("new_case_study");
			}
        }
		return mav;
	}
	
	@PostMapping("/new_case_study")
	public ModelAndView addCaseStudy(@Valid @ModelAttribute("casestudy") CaseStudyVO casestudy, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();     
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDate = df.format(today);
        if("0".equals(casestudy.getMainResearcher())){
        	casestudy.setMainResearcher(null);
        }
        //project codes
	    List<String> projectCodes = projectService.findProjectCodes();	
		//add project codes, status list and requester list to the page
		modelAndView.addObject("projectCodes", projectCodes);
		modelAndView.addObject("statusList", caseStudyService.findAllStatus());
		modelAndView.addObject("requesterList", adviserService.findAll());
		modelAndView.addObject("edit", false);
        //transfer from project code to project id
		//if no project, add error message, get project related researchers
		Project project = null;
        if(casestudy.getProject() != null){
        	project = projectService.findProjectByCode(retrieveProjectCode(casestudy.getProject()));
        	if(project != null){
        		setProjectandResearchers(casestudy, project);
        	}else{        	    
        	    modelAndView.addObject("errormsg", "Please enter correct project code!");
                modelAndView.setViewName("new_case_study");
                return modelAndView;
        	}
        }
        
        //set the init status to requested when add one case study
        casestudy.setStatus("1");
        casestudy.setRequested(reportDate);
        //set the case study object
	    modelAndView.addObject("casestudy", casestudy);
	    
        if (result.hasErrors()) {
    		List<ObjectError> list = result.getAllErrors();
    	    for(ObjectError error : list){
    	        System.out.println(error.getObjectName()+"---"+error.getArguments().toString()+"---"+error.getDefaultMessage());
    	    }
            modelAndView.setViewName("new_case_study");
        }else{
        	caseStudyService.addCaseStudy(casestudy);
            //after insertion logic
    		casestudy.setProject(project.getProjectCode());
    		casestudy.setRequested(Util.formatDate(casestudy.getRequested(), "yyyy-MM-dd"));
    		modelAndView.addObject("researcherList", project.getResearchers());
            modelAndView.addObject("edit", true);
            modelAndView.setViewName("new_case_study");
        }     
		return modelAndView;
	}
	
	@PostMapping("/edit_case_study")
	public ModelAndView updateCaseStudy(@Valid @ModelAttribute("casestudy") CaseStudyVO casestudy, BindingResult result) {
		//edit the case study, basically same logic as add
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("edit", true);
        if("0".equals(casestudy.getMainResearcher())){
        	casestudy.setMainResearcher(null);
        }
		Project project = null;
        if(casestudy.getProject() != null){
        	project = projectService.findProjectByCode(retrieveProjectCode(casestudy.getProject()));
        	if(project != null){
        		setProjectandResearchers(casestudy, project);
        	}else{
        	    modelAndView.addObject("errormsg", "Please enter correct project code!");
        	    List<String> projectCodes = projectService.findProjectCodes();
        		modelAndView.addObject("projectCodes", projectCodes);
        		modelAndView.addObject("statusList", caseStudyService.findAllStatus());
        		modelAndView.addObject("requesterList", adviserService.findAll());    	    
        	    modelAndView.addObject("casestudy", casestudy);
                modelAndView.setViewName("new_case_study");
                return modelAndView;
            }
        }
        caseStudyService.updateCaseStudy(casestudy);
        modelAndView.setViewName("redirect:/case_studies");
		return modelAndView;
	}
	
	private void setProjectandResearchers(CaseStudyVO casestudy, Project project){
		//private method for setting project and researchers to case study to avoid repeated codes
		casestudy.setProject(String.valueOf(project.getId()));
		if(project.getResearchers() != null){
			List<Researcher> researchers = project.getResearchers();
			String researcherIds = "";			
			int i = 0;
			for (Researcher res : researchers){
				if (i == 0){
					researcherIds = String.valueOf(res.getId());
				} else {
					researcherIds+="," + String.valueOf(res.getId());
				}
				i++;
			}
			casestudy.setResearchers(researcherIds);
		}
	}
	
	//retrieve project code from code and name combined string
	private String retrieveProjectCode(String projectCodeAndName){
		String projectCode = null;
		if(projectCodeAndName != null && projectCodeAndName.indexOf(":") != -1){
			projectCode = projectCodeAndName.split(":")[0];
		}else{
			return projectCodeAndName;
		}
		return projectCode;
	}
	
    @GetMapping(value = "get_researchers_byproject", produces="application/json")
    public @ResponseBody String getProject(
            @RequestParam(value="projectCode", required=false) String projectCode, 
            HttpServletRequest request) throws Exception {
    	//getting researcher by project, used by the front end ajax call, returning json
    	Project project = projectService.findProjectByCode(retrieveProjectCode(projectCode));
    	List<Researcher> rList = new ArrayList<Researcher>(project.getResearchers().size());
    	project.getResearchers().forEach(x -> {
    		Researcher r = new Researcher();
    		r.setId(x.getId());
    		r.setFullName(x.getFullName());
			rList.add(r);
		});
    	ObjectMapper objectMapper = new ObjectMapper();
    	String jsonStr = objectMapper.writeValueAsString(rList);
        return jsonStr;
    }
}