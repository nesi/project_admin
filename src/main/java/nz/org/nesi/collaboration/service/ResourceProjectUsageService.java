package nz.org.nesi.collaboration.service;

import java.util.List;
import nz.org.nesi.collaboration.model.ResourceInstance;
import nz.org.nesi.collaboration.model.ResourceProjectUsage;
import nz.org.nesi.collaboration.vo.ResourceProjectUsageVO;

public interface ResourceProjectUsageService {
	List<ResourceInstance> findAllResourceInstance();
	
	ResourceProjectUsage addResourceProjectUsage(ResourceProjectUsageVO resourceProjectUsage);
	
	List<ResourceProjectUsageVO> listAll();
	
	ResourceProjectUsage getResourceProjectUsage(int id);
	
	ResourceProjectUsage updateResourceProjectUsage(ResourceProjectUsageVO esourceProjectUsage);
}