package nz.org.nesi.collaboration.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.org.nesi.collaboration.dao.AdviserDao;
import nz.org.nesi.collaboration.model.Adviser;
import nz.org.nesi.collaboration.service.AdviserService;
import nz.org.nesi.collaboration.vo.AdviserVO;

@Service
public class AdviserServiceImpl implements AdviserService {
	@Autowired
	private AdviserDao adviserDao;
	
	@Override
	public Adviser findByTuakiriToken(String sharedToken) {
		// TODO Auto-generated method stub
		return adviserDao.findByTuakiriToken(sharedToken);
	}

	@Override
	public Adviser findByEppn(String eppn) {
		// TODO Auto-generated method stub
		return adviserDao.findByEppn(eppn);
	}
	
	@Override
	public Adviser getAdviserByTuakiriOrEppn(HttpServletRequest request){
		 String sharedToken = (String) request.getAttribute("auEduPersonSharedToken");
	        System.out.println("shared Token is: " + sharedToken);
	        String eppn = (String) request.getAttribute("eppn");
	        Adviser adviser = null;
	        try {
	        	adviser = findByTuakiriToken(sharedToken);
	        	if(adviser == null){
	        		adviser = findByEppn(eppn);
	        	}		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return adviser;
	}
	
	@Override
	public List<AdviserVO> findAll(){
		List<Adviser> advisers = adviserDao.findAll();
		List<AdviserVO> adviserVOs = new ArrayList<AdviserVO>();
		for(Adviser adviser: advisers){
			AdviserVO adviserVO = new AdviserVO();
			//adviserVO.setAddress(adviser.getAddress());
			adviserVO.setDepartment(adviser.getDepartment());
			adviserVO.setDivision(adviser.getDivision());
			adviserVO.setEmail(adviser.getEmail());
			//adviserVO.setEndDate(adviser.getEndDate());
			adviserVO.setFullName(adviser.getFullName());
			adviserVO.setId(adviser.getId());
			adviserVO.setInstitution(adviser.getInstitution());
			adviserVO.setIsAdmin(adviser.getIsAdmin());
			//adviserVO.setLastModified(adviser.getLastModified());
			//adviserVO.set
			adviserVOs.add(adviserVO);
		}
		Collections.sort(adviserVOs, AdviserVO.adviserVOComparator);
		return adviserVOs;
	}

}
