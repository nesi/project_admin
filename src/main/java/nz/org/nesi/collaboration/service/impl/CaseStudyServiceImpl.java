package nz.org.nesi.collaboration.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nz.org.nesi.collaboration.dao.CasestudyDao;
import nz.org.nesi.collaboration.dao.CasestudystatusDao;
import nz.org.nesi.collaboration.dao.ResearcherDao;
import nz.org.nesi.collaboration.model.Adviser;
import nz.org.nesi.collaboration.model.CaseStudy;
import nz.org.nesi.collaboration.model.CaseStudyStatus;
import nz.org.nesi.collaboration.model.Project;
import nz.org.nesi.collaboration.model.Researcher;
import nz.org.nesi.collaboration.service.CaseStudyService;
import nz.org.nesi.collaboration.util.Util;
import nz.org.nesi.collaboration.vo.CaseStudyVO;

@Service
public class CaseStudyServiceImpl implements CaseStudyService {

	@Autowired
	private ResearcherDao researcherDao;
	
	@Autowired
	private CasestudyDao casestudyDao;
	
	@Autowired
	private CasestudystatusDao casestudystatusDao;
	
	@Override
	public List<CaseStudyVO> listCaseStudy(){
		List<CaseStudy> casestudystatus = casestudyDao.findAll();
		List<CaseStudyVO> caseStudyVOs = new ArrayList<CaseStudyVO>(casestudystatus.size());
		casestudystatus.forEach(x -> {
			CaseStudyVO caseStudyVO = new CaseStudyVO(x);
			caseStudyVOs.add(caseStudyVO);
		});
		
		for(CaseStudyVO casestudy : caseStudyVOs) {
			System.out.println(casestudy.getName());
			if(casestudy.getResearchers() != null){
				String researcherIds[] = casestudy.getResearchers().split(",");
				List<Researcher> researcher = researcherDao.findByIds(researcherIds);
				String researchers = "";			
				int i=0;
				for (Researcher res : researcher){
					//don't show the main researcher and contact person in the researcher list for the list page
					if(casestudy.getContactPerson() != null && casestudy.getContactPerson().equals(String.valueOf(res.getId()))){
						continue;
					}
					if(casestudy.getMainResearcher() != null && casestudy.getMainResearcher().equals(String.valueOf(res.getId()))){
						continue;
					}
					if (i==0){
						researchers = res.getFullName();
					} else {
						researchers+=", " + res.getFullName();
					}
					i++;
				}
				casestudy.setResearchers(researchers);
			}
			if(casestudy.getMainResearcher() != null){
				casestudy.setMainResearcher(researcherDao.findOne(Integer.parseInt(casestudy.getMainResearcher())).getFullName());
			}
			if(casestudy.getContactPerson() != null){
				casestudy.setContactPerson(researcherDao.findOne(Integer.parseInt(casestudy.getContactPerson())).getFullName());
			}
		}
		
		return caseStudyVOs;
	}
	
	@Override
	public List<CaseStudy> findAll(){
		return casestudyDao.findAll();
	}

	@Override
	public CaseStudyVO getEditCaseStudy(Integer id) {
		CaseStudy caseStudyResult = casestudyDao.findOne(id);
		CaseStudyVO caseStudy = new CaseStudyVO(caseStudyResult.getId(), caseStudyResult.getName(), caseStudyResult.getProject().getProjectCode(), String.valueOf(caseStudyResult.getRequestor().getId()),
				Util.formatDate(caseStudyResult.getRequested(), "yyyy-MM-dd"), Util.formatDate(caseStudyResult.getApproved(), "yyyy-MM-dd"), Util.formatDate(caseStudyResult.getInprogress(), "yyyy-MM-dd"),
				Util.formatDate(caseStudyResult.getWithdrawn(), "yyyy-MM-dd"), Util.formatDate(caseStudyResult.getRejected(), "yyyy-MM-dd"), Util.formatDate(caseStudyResult.getPublished(), "yyyy-MM-dd"),
				caseStudyResult.getResearchers(), caseStudyResult.getMainResearcher(), caseStudyResult.getContactPerson(), String.valueOf(caseStudyResult.getStatus().getId()), caseStudyResult.getUrl());
		
		return caseStudy;
	}

	@Override
	public CaseStudy addCaseStudy(CaseStudyVO casestudy) {
		//insert the data, map from VO to entity first
    	Project savedProject = new Project();
    	savedProject.setId(Integer.parseInt(casestudy.getProject()));
    	Adviser savedRequester = new Adviser();
    	savedRequester.setId(Integer.parseInt(casestudy.getRequestor()));
    	CaseStudyStatus savedStatus = new CaseStudyStatus();
    	savedStatus.setId(Integer.parseInt(casestudy.getStatus()));
    	CaseStudy casestudyEntity = new CaseStudy(casestudy, savedProject, savedRequester, savedStatus);
    	CaseStudy savedCaseStudy = casestudyDao.saveAndFlush(casestudyEntity);
        casestudy.setId(savedCaseStudy.getId());
		return casestudyEntity;
	}

	@Override
	public CaseStudy updateCaseStudy(CaseStudyVO casestudy) {
		Project savedProject = new Project();
    	savedProject.setId(Integer.parseInt(casestudy.getProject()));
    	Adviser savedRequester = new Adviser();
    	savedRequester.setId(Integer.parseInt(casestudy.getRequestor()));
    	CaseStudyStatus savedStatus = new CaseStudyStatus();
    	savedStatus.setId(Integer.parseInt(casestudy.getStatus()));
    	CaseStudy casestudyEntity = new CaseStudy(casestudy, savedProject, savedRequester, savedStatus);
    	casestudyDao.saveAndFlush(casestudyEntity);
		return casestudyEntity;
	}

	@Override
	public List<CaseStudyStatus> findAllStatus() {
		// TODO Auto-generated method stub
		return casestudystatusDao.findAll();
	}
	
	
}
