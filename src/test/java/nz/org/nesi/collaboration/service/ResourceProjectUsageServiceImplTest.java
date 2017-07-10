package nz.org.nesi.collaboration.service;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import nz.org.nesi.collaboration.dao.ResourceInstanceDao;
import nz.org.nesi.collaboration.dao.ResourceProjectUsageDao;
import nz.org.nesi.collaboration.model.Adviser;
import nz.org.nesi.collaboration.model.Project;
import nz.org.nesi.collaboration.model.ResourceInstance;
import nz.org.nesi.collaboration.model.ResourceProjectUsage;
import nz.org.nesi.collaboration.service.impl.ResourceProjectUsageServiceImpl;
import nz.org.nesi.collaboration.vo.ResourceProjectUsageVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceProjectUsageServiceImplTest {

	@Autowired
	private ResourceProjectUsageDao resourceDao;
	
	@Autowired
	private ResourceProjectUsageService resourceService;
	
	@Configuration
	static class ProjectServiceTestContextConfiguration {
		@Bean
		public ResourceProjectUsageService resourceService() {
			return new ResourceProjectUsageServiceImpl();
		}
		@Bean
		public ResourceProjectUsageDao resourceDao() {
			return Mockito.mock(ResourceProjectUsageDao.class);
		}
		
		@Bean
		public ResourceInstanceDao instanceDao() {
			return Mockito.mock(ResourceInstanceDao.class);
		}
	}
	
	@Before
	public void setup() {
		ResourceProjectUsage resourceUsage = new ResourceProjectUsage();
		resourceUsage.setId(1);
		Project project = new Project();
		project.setId(1);
		project.setProjectCode("uoa00001");
		resourceUsage.setProject(project);
		Adviser adviser = new Adviser();
		adviser.setId(2);
		adviser.setFullName("Shawn");
		resourceUsage.setAdviser(adviser);
		resourceUsage.setDate("2017-06-29");
		ResourceInstance instance = new ResourceInstance();
		instance.setId(1);
		resourceUsage.setResourceInstance(instance);
		resourceUsage.setValue("20");
		resourceUsage.setNote("Test");
		
		List<ResourceProjectUsage> resourceUsages = new ArrayList<ResourceProjectUsage>();
		resourceUsages.add(resourceUsage);
		
		Mockito.when(resourceDao.findAll()).thenReturn(resourceUsages);
	}
	
	@Test
	public void testListAll(){
		List<ResourceProjectUsageVO> resourcesUsageVOs = resourceService.listAll();
		assertEquals(resourcesUsageVOs.get(0).getAdviser(), "Shawn");
		assertEquals(resourcesUsageVOs.get(0).getProject(), "uoa00001");
	}
}
