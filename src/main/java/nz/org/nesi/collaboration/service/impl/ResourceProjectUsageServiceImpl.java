package nz.org.nesi.collaboration.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nz.org.nesi.collaboration.dao.ResourceInstanceDao;
import nz.org.nesi.collaboration.dao.ResourceProjectUsageDao;
import nz.org.nesi.collaboration.model.Adviser;
import nz.org.nesi.collaboration.model.Project;
import nz.org.nesi.collaboration.model.ResourceInstance;
import nz.org.nesi.collaboration.model.ResourceProjectUsage;
import nz.org.nesi.collaboration.service.ResourceProjectUsageService;
import nz.org.nesi.collaboration.vo.ResourceProjectUsageVO;

@Service
public class ResourceProjectUsageServiceImpl implements ResourceProjectUsageService {

	@Autowired
	private ResourceInstanceDao resourceInstanceDao;
	
	@Autowired
	private ResourceProjectUsageDao resourceProjectUsageDao;
	
	@Override
	public List<ResourceInstance> findAllResourceInstance() {
		return resourceInstanceDao.findAll();
	}

	@Override
	public ResourceProjectUsage addResourceProjectUsage(ResourceProjectUsageVO resourceProjectUsage) {
    	Project savedProject = new Project();
    	savedProject.setId(Integer.parseInt(resourceProjectUsage.getProject()));
    	Adviser savedAdviser = new Adviser();
    	savedAdviser.setId(Integer.parseInt(resourceProjectUsage.getAdviser()));
    	ResourceInstance savedInstance = new ResourceInstance();
    	savedInstance.setId(Integer.parseInt(resourceProjectUsage.getResourceInstance()));
    	ResourceProjectUsage resourceProjectUsageEntity = new ResourceProjectUsage();
    	resourceProjectUsageEntity.setAdviser(savedAdviser);
    	resourceProjectUsageEntity.setProject(savedProject);
    	resourceProjectUsageEntity.setResourceInstance(savedInstance);
    	resourceProjectUsageEntity.setDate(resourceProjectUsage.getDate());
    	resourceProjectUsageEntity.setNote(resourceProjectUsage.getNote());
    	resourceProjectUsageEntity.setValue(resourceProjectUsage.getValue());
    	ResourceProjectUsage savedResourceProjectUsageEntity = resourceProjectUsageDao.saveAndFlush(resourceProjectUsageEntity);
    	resourceProjectUsage.setId(savedResourceProjectUsageEntity.getId());
		return resourceProjectUsageEntity;
	}

	@Override
	public List<ResourceProjectUsageVO> listAll() {
		List<ResourceProjectUsage> list = resourceProjectUsageDao.findAll();
		List<ResourceProjectUsageVO> resourceProjectUsageVOs = new ArrayList<ResourceProjectUsageVO>(list.size());
		
		list.forEach(x -> {
			ResourceProjectUsageVO resourceProjectUsageVO = new ResourceProjectUsageVO();
			resourceProjectUsageVO.setAdviser(x.getAdviser().getFullName());
			resourceProjectUsageVO.setAllocation(x.getAllocation());
			resourceProjectUsageVO.setDate(x.getDate());
			resourceProjectUsageVO.setId(x.getId());
			resourceProjectUsageVO.setNote(x.getNote());
			resourceProjectUsageVO.setProject(x.getProject().getProjectCode());
			resourceProjectUsageVO.setResourceInstance(x.getResourceInstance().getDescription());
			resourceProjectUsageVO.setValue(x.getValue());
			resourceProjectUsageVOs.add(resourceProjectUsageVO);
		});
		
		return resourceProjectUsageVOs;
	}

	@Override
	public ResourceProjectUsage getResourceProjectUsage(int id) {
		ResourceProjectUsage resourceProjectUsage = resourceProjectUsageDao.findOne(id);
		return resourceProjectUsage;
	}

	@Override
	public ResourceProjectUsage updateResourceProjectUsage(ResourceProjectUsageVO resourceProjectUsage) {
		Project savedProject = new Project();
    	savedProject.setId(Integer.parseInt(resourceProjectUsage.getProject()));
    	Adviser savedAdviser = new Adviser();
    	savedAdviser.setId(Integer.parseInt(resourceProjectUsage.getAdviser()));
    	ResourceInstance savedInstance = new ResourceInstance();
    	savedInstance.setId(Integer.parseInt(resourceProjectUsage.getResourceInstance()));
    	ResourceProjectUsage resourceProjectUsageEntity = new ResourceProjectUsage();
    	resourceProjectUsageEntity.setId(resourceProjectUsage.getId());
    	resourceProjectUsageEntity.setAdviser(savedAdviser);
    	resourceProjectUsageEntity.setAllocation(resourceProjectUsage.getAllocation());
    	resourceProjectUsageEntity.setDate(resourceProjectUsage.getDate());
    	resourceProjectUsageEntity.setNote(resourceProjectUsage.getNote());
    	resourceProjectUsageEntity.setProject(savedProject);
    	resourceProjectUsageEntity.setResourceInstance(savedInstance);
    	resourceProjectUsageEntity.setValue(resourceProjectUsage.getValue());
    	resourceProjectUsageDao.saveAndFlush(resourceProjectUsageEntity);
		return resourceProjectUsageEntity;
	}
}