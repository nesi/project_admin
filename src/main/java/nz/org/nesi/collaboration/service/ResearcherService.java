package nz.org.nesi.collaboration.service;

import java.util.List;

import nz.org.nesi.collaboration.model.Researcher;

public interface ResearcherService {
	List<Researcher> findAll();
	Researcher findOne(Integer id);
}
