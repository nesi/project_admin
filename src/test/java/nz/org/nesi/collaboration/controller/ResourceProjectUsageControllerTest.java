package nz.org.nesi.collaboration.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import nz.org.nesi.collaboration.model.Adviser;
import nz.org.nesi.collaboration.model.Project;
import nz.org.nesi.collaboration.model.ResourceInstance;
import nz.org.nesi.collaboration.model.ResourceProjectUsage;
import nz.org.nesi.collaboration.service.AdviserService;
import nz.org.nesi.collaboration.service.ProjectService;
import nz.org.nesi.collaboration.service.ResourceProjectUsageService;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(ResourceProjectUsageController.class)
public class ResourceProjectUsageControllerTest {
	private static final int TEST_RPU_ID = 1;
	private static final String TEST_PRO_CODE = "1";
	private static final int TEST_ADV_ID = 1;
	private static final int TEST_RI_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResourceProjectUsageService resourceProjectUsageService;
    @MockBean
	private AdviserService adviserService;
    @MockBean
	private ProjectService projectService;
    
    private ResourceProjectUsage resourceProjectUsage;
    
    private Project project;
    private Adviser adviser;
    
    @Before
    public void setup() {
    	adviser = new Adviser(1, "FoS", "test@auckland.ac.nz", "Test", (byte) 1);
    	adviser.setTuakiriUniqueId("test@test.ac.nz");
    	adviser.setTuakiriToken("test");
    	
    	project = new Project(1, "Test DEP", "Test", TEST_PRO_CODE);
    	
    	resourceProjectUsage = new ResourceProjectUsage();
    	resourceProjectUsage.setId(TEST_RPU_ID);
    	resourceProjectUsage.setAdviser(adviser);;
    	resourceProjectUsage.setAllocation(100);;
    	resourceProjectUsage.setDate("25-05-2017");
    	resourceProjectUsage.setNote("Test Data");
    	resourceProjectUsage.setProject(project);
    	resourceProjectUsage.setResourceInstance(new ResourceInstance());
    	resourceProjectUsage.setValue("Test");
    	
        given(this.projectService.findProjectByCode(TEST_PRO_CODE)).willReturn(project);
        given(this.resourceProjectUsageService.getResourceProjectUsage(TEST_RPU_ID)).willReturn(resourceProjectUsage);
        given(this.adviserService.findByEppn("test@test.test.ac.nz")).willReturn(adviser);
    }
    
    @Test
    public void testListSPU() throws Exception {
        mockMvc.perform(get("/scientific_programmer"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("ojbects"))
            .andExpect(model().size(1))
            .andExpect(view().name("generic_resources"));
    }

    @Test
    public void testInitAddForm() throws Exception {
        mockMvc.perform(get("/add_scientific_programmer_usage"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("genericresource"))
            .andExpect(model().attributeExists("edit"))
            .andExpect(model().attributeExists("projectCodes"))
            .andExpect(model().attributeExists("adviserList"))
            .andExpect(model().attributeExists("resourceList"))
            .andExpect(model().size(5))
            .andExpect(view().name("new_generic_resource"));
    }
    
    @Test
    public void testInitAddSuccess() throws Exception {
        mockMvc.perform(post("/add_scientific_programmer_usage")
                .param("adviser", "1")
                .param("resourceInstance", "1")
                .param("date", "25-05-2017")
                .param("note", "Test note")
                .param("project", "1")
                .param("value", "100")
            )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/scientific_programmer"));
    }
    
    @Test
    public void testInitEditForm() throws Exception {
        mockMvc.perform(get("/edit_scientific_programmer_usage").param("id", "1"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("resourceProjectUsage"))
            .andExpect(model().attributeExists("edit"))
            .andExpect(model().attributeExists("projectCodes"))
            .andExpect(model().attributeExists("adviserList"))
            .andExpect(model().attributeExists("resourceList"))
            .andExpect(model().attributeExists("selAdviserName"))
            .andExpect(model().attributeExists("selAdviserId"))
            //.andExpect(model().attributeExists("selResourceName"))
            .andExpect(model().attributeExists("selResourceId"))
            .andExpect(model().size(9))
            .andExpect(view().name("new_generic_resource"));
    }
    
    @Test
    public void testInitEditSuccess() throws Exception {
        mockMvc.perform(post("/edit_scientific_programmer_usage")
                .param("id", "1")
                .param("adviser", "1")
                .param("resourceInstance", "1")
                .param("date", "25-05-2017")
                .param("note", "Test note")
                .param("project", "1")
                .param("value", "100")
            )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/scientific_programmer"));
    }
}
