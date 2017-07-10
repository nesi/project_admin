package nz.org.nesi.collaboration.service;

import java.util.List;

import nz.org.nesi.collaboration.model.Project;

public interface ProjectService {
	List<String> findProjectCodes();
	Project findProjectByCode(String code);
	Project findById(int id);
	List<Project> findAll();
}
