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

import nz.org.nesi.collaboration.dao.AdviserDao;
import nz.org.nesi.collaboration.model.Adviser;
import nz.org.nesi.collaboration.service.AdviserService;
import nz.org.nesi.collaboration.service.impl.AdviserServiceImpl;
import nz.org.nesi.collaboration.vo.AdviserVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdviserServiceImplTest {

	@Autowired
	private AdviserService adviserService;
	
	@Autowired
	private AdviserDao adviserDao;
	
	@Configuration
	static class AdviserServiceTestContextConfiguration {
		@Bean
		public AdviserService adviserService() {
			return new AdviserServiceImpl();
		}
		@Bean
		public AdviserDao adviserDao() {
			return Mockito.mock(AdviserDao.class);
		}
	}
	
	@Before
	public void setup() {
		Adviser adviser = new Adviser();
		adviser.setId(1);
		adviser.setFullName("Shawn");
		adviser.setInstitution("University of Auckland");
		adviser.setDepartment("Faculty of Science");
		adviser.setEmail("shen.wang@auckland.ac.nz");
		adviser.setAddress("23 Symonds Street");
		adviser.setTuakiriToken("7gZun7Hzn4ktxOuBCbAlldeMheY");
		adviser.setTuakiriUniqueId("swan940@auckland.ac.nz");
		List<Adviser> advisers = new ArrayList<Adviser>();
		advisers.add(adviser);
		Mockito.when(adviserDao.findAll()).thenReturn(advisers);
		Mockito.when(adviserDao.findByTuakiriToken("7gZun7Hzn4ktxOuBCbAlldeMheY")).thenReturn(adviser);
		Mockito.when(adviserDao.findByEppn("swan940@auckland.ac.nz")).thenReturn(adviser);
	}
	
	@Test
	public void testFindAll(){
		List<AdviserVO> advisers = adviserService.findAll();
		assertEquals(advisers.get(0).getFullName(), "Shawn");
		assertEquals(advisers.get(0).getId(), 1);
	}
	
	@Test
	public void testFindByTuakiriToken(){
		Adviser adviser = adviserService.findByTuakiriToken("7gZun7Hzn4ktxOuBCbAlldeMheY");
		assertEquals(adviser.getFullName(), "Shawn");
		assertEquals(adviser.getId(), 1);
	}
	
	@Test
	public void testFindByEppn(){
		Adviser adviser = adviserService.findByEppn("swan940@auckland.ac.nz");
		assertEquals(adviser.getFullName(), "Shawn");
		assertEquals(adviser.getId(), 1);
	}
}