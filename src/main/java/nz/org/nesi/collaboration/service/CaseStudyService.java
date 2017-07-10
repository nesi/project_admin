package nz.org.nesi.collaboration.service;

import java.util.List;

import nz.org.nesi.collaboration.model.CaseStudy;
import nz.org.nesi.collaboration.model.CaseStudyStatus;
import nz.org.nesi.collaboration.vo.CaseStudyVO;

public interface CaseStudyService {
	List<CaseStudyVO> listCaseStudy();
	
	List<CaseStudy> findAll();
	
	List<CaseStudyStatus> findAllStatus();
	
	CaseStudyVO getEditCaseStudy(Integer id);
	
	CaseStudy addCaseStudy(CaseStudyVO casestudy);
	
	CaseStudy updateCaseStudy(CaseStudyVO casestudy);
}
