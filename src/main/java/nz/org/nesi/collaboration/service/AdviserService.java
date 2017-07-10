package nz.org.nesi.collaboration.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import nz.org.nesi.collaboration.model.Adviser;
import nz.org.nesi.collaboration.vo.AdviserVO;

public interface AdviserService {
	Adviser findByTuakiriToken(String sharedToken);
	Adviser findByEppn(String eppn);
	Adviser getAdviserByTuakiriOrEppn(HttpServletRequest request);
	List<AdviserVO> findAll();
}
