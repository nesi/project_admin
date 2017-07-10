package nz.org.nesi.collaboration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.org.nesi.collaboration.dao.ResearcherDao;
import nz.org.nesi.collaboration.model.Researcher;
import nz.org.nesi.collaboration.service.ResearcherService;

@Service
public class ResearcherServiceImpl implements ResearcherService {

	@Autowired
	private ResearcherDao researcherDao;
	
	@Override
	public List<Researcher> findAll() {
		// TODO Auto-generated method stub
		return researcherDao.findAll();
	}

	@Override
	public Researcher findOne(Integer id) {
		// TODO Auto-generated method stub
		return researcherDao.findOne(id);
	}

}
