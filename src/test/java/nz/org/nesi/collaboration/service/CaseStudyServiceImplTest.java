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
import nz.org.nesi.collaboration.dao.CasestudyDao;
import nz.org.nesi.collaboration.dao.CasestudystatusDao;
import nz.org.nesi.collaboration.dao.ResearcherDao;
import nz.org.nesi.collaboration.model.Adviser;
import nz.org.nesi.collaboration.model.CaseStudy;
import nz.org.nesi.collaboration.model.CaseStudyStatus;
import nz.org.nesi.collaboration.model.Project;
import nz.org.nesi.collaboration.model.Researcher;
import nz.org.nesi.collaboration.service.impl.CaseStudyServiceImpl;
import nz.org.nesi.collaboration.vo.CaseStudyVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CaseStudyServiceImplTest {
	
	@Autowired
	private CaseStudyService caseStudyService;
	
	@Autowired
	private CasestudyDao caseStudyDao;
	
	@Autowired
	private ResearcherDao researcherDao;
	
	@Autowired
	private CasestudystatusDao casestudystatusDao;
	
	@Configuration
	static class CaseStudyServiceTestContextConfiguration {
		@Bean
		public CaseStudyService caseStudyService() {
			return new CaseStudyServiceImpl();
		}
		@Bean
		public CasestudyDao caseStudyDao() {
			return Mockito.mock(CasestudyDao.class);
		}
		@Bean
		public ResearcherDao researcherDao() {
			return Mockito.mock(ResearcherDao.class);
		}
		@Bean
		public CasestudystatusDao caseStudyStatusDao() {
			return Mockito.mock(CasestudystatusDao.class);
		}
	}
	
	@Before
	public void setup() {
		CaseStudy caseStudy = new CaseStudy();
		caseStudy.setId(1);
		caseStudy.setName("Test case study example");
		Project project = new Project();
		project.setId(8);
		caseStudy.setProject(project);
		Adviser adviser = new Adviser();
		adviser.setId(1);
		caseStudy.setRequestor(adviser);
		CaseStudyStatus caseStudyStatus = new CaseStudyStatus();
		caseStudyStatus.setId(1);
		caseStudy.setStatus(caseStudyStatus);
		caseStudy.setResearchers("1,2");
		caseStudy.setMainResearcher("1");
		List<CaseStudy> caseStudies = new ArrayList<CaseStudy>();
		caseStudies.add(caseStudy);
		
		List<Researcher> researchers = new ArrayList<Researcher>();
		Researcher researcher = new Researcher();
		researcher.setId(1);
		researcher.setFullName("John");
		Researcher researcher2 = new Researcher();
		researcher2.setId(2);
		researcher2.setFullName("Ken");
		researchers.add(researcher);
		researchers.add(researcher2);
		
		Mockito.when(caseStudyDao.findAll()).thenReturn(caseStudies);
		Mockito.when(caseStudyDao.findOne(1)).thenReturn(caseStudy);
		Mockito.when(researcherDao.findByIds(new String[]{"1","2"})).thenReturn(researchers);
		Mockito.when(researcherDao.findOne(1)).thenReturn(researcher);
		
		List<CaseStudyStatus> caseStudyStatuses = new ArrayList<CaseStudyStatus>();
		CaseStudyStatus status1 = new CaseStudyStatus();
		status1.setId(1);
		status1.setStatus("Requested");
		caseStudyStatuses.add(status1);
		CaseStudyStatus status2 = new CaseStudyStatus();
		status2.setId(2);
		status2.setStatus("In progress");
		caseStudyStatuses.add(status2);
		CaseStudyStatus status3 = new CaseStudyStatus();
		status3.setId(3);
		status3.setStatus("Approved");
		caseStudyStatuses.add(status3);
		CaseStudyStatus status4 = new CaseStudyStatus();
		status4.setId(4);
		status4.setStatus("Published");
		caseStudyStatuses.add(status4);
		CaseStudyStatus status5 = new CaseStudyStatus();
		status5.setId(5);
		status5.setStatus("Rejected");
		caseStudyStatuses.add(status5);
		CaseStudyStatus status6 = new CaseStudyStatus();
		status6.setId(6);
		status6.setStatus("Withdrawn");
		caseStudyStatuses.add(status6);
		
		Mockito.when(casestudystatusDao.findAll()).thenReturn(caseStudyStatuses);
	}
	
	@Test
	public void testListCaseStudy(){
		List<CaseStudyVO> caseStudies = caseStudyService.listCaseStudy();
		assertEquals(caseStudies.get(0).getName(), "Test case study example");
		assertEquals(caseStudies.get(0).getId(), 1);
		assertEquals(caseStudies.get(0).getResearchers(), "Ken");
		assertEquals(caseStudies.get(0).getMainResearcher(), "John");
	}
	
	@Test
	public void testGetEditCaseStudy(){
		CaseStudyVO caseStudyVO = caseStudyService.getEditCaseStudy(1);
		assertEquals(caseStudyVO.getId(), 1);
	}
	
	@Test
	public void testFindAllStatus(){
		List<CaseStudyStatus> caseStudyStatus = caseStudyService.findAllStatus();
		assertEquals(caseStudyStatus.size(), 6);
		assertEquals(caseStudyStatus.get(0).getStatus(), "Requested");
		assertEquals(caseStudyStatus.get(1).getStatus(), "In progress");
		assertEquals(caseStudyStatus.get(2).getStatus(), "Approved");
		assertEquals(caseStudyStatus.get(3).getStatus(), "Published");
		assertEquals(caseStudyStatus.get(4).getStatus(), "Rejected");
		assertEquals(caseStudyStatus.get(5).getStatus(), "Withdrawn");
	}
}