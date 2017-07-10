package nz.org.nesi.collaboration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nz.org.nesi.collaboration.dao.ProjectDao;
import nz.org.nesi.collaboration.model.Project;
import nz.org.nesi.collaboration.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public List<String> findProjectCodes() {
		// TODO Auto-generated method stub
		return projectDao.findProjectCodes();
	}

	@Override
	public Project findProjectByCode(String code) {
		// TODO Auto-generated method stub
		return projectDao.findProjectByCode(code);
	}

	@Override
	public Project findById(int id) {
		// TODO Auto-generated method stub
		return projectDao.findOne(id);
	}

	@Override
	public List<Project> findAll() {
		// TODO Auto-generated method stub
		return projectDao.findAll();
	}

}
