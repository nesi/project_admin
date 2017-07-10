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
import nz.org.nesi.collaboration.dao.ResearcherDao;
import nz.org.nesi.collaboration.model.Researcher;
import nz.org.nesi.collaboration.service.impl.ResearcherServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResearcherServiceImplTest {

	@Autowired
	private ResearcherDao researcherDao;
	
	@Autowired
	private ResearcherService researcherService;
	
	@Configuration
	static class ResearcherServiceTestContextConfiguration {
		@Bean
		public ResearcherService researcherService() {
			return new ResearcherServiceImpl();
		}
		@Bean
		public ResearcherDao researcherDao() {
			return Mockito.mock(ResearcherDao.class);
		}
	}
	
	@Before
	public void setup() {
		List<Researcher> researchers = new ArrayList<Researcher>();
		
		Researcher researcher = new Researcher();
		researcher.setId(1);
		researcher.setFullName("Shawn");
		researchers.add(researcher);
		
		Mockito.when(researcherDao.findAll()).thenReturn(researchers);
	}
	
	@Test
	public void testFindAll(){
		List<Researcher> researchers = researcherService.findAll();
		assertEquals(researchers.get(0).getFullName(), "Shawn");
	}
}