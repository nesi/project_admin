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
import nz.org.nesi.collaboration.dao.ProjectDao;
import nz.org.nesi.collaboration.model.Project;
import nz.org.nesi.collaboration.service.impl.ProjectServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceImplTest {

	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private ProjectService projectService;
	
	@Configuration
	static class ProjectServiceTestContextConfiguration {
		@Bean
		public ProjectService projectService() {
			return new ProjectServiceImpl();
		}
		@Bean
		public ProjectDao projectDao() {
			return Mockito.mock(ProjectDao.class);
		}
	}
	
	@Before
	public void setup() {
		String projectCode1 = "uoa00001";
		String projectCode2 = "uoa00002";
		
		List<String> projectCodes = new ArrayList<String>();
		projectCodes.add(projectCode1);
		projectCodes.add(projectCode2);
		
		Project project = new Project();
		project.setId(1);
		project.setProjectCode(projectCode1);
		
		Mockito.when(projectDao.findProjectCodes()).thenReturn(projectCodes);
		Mockito.when(projectDao.findProjectByCode(projectCode1)).thenReturn(project);
	}
	
	@Test
	public void testFindProjectCodes(){
		List<String> projectCodes = projectService.findProjectCodes();
		assertEquals(projectCodes.size(), 2);
		assertEquals(projectCodes.get(0), "uoa00001");
		assertEquals(projectCodes.get(1), "uoa00002");
	}
	
	@Test
	public void testFindProjectCByCode(){
		Project project = projectService.findProjectByCode("uoa00001");
		assertEquals(project.getId(), 1);
		assertEquals(project.getProjectCode(), "uoa00001");
	}
}