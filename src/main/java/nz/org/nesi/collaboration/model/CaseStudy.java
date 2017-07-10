package nz.org.nesi.collaboration.model;

import java.sql.Timestamp;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import nz.org.nesi.collaboration.vo.CaseStudyVO;

@Entity
@Table(name = "casestudy")
public class CaseStudy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	String name;

	@ManyToOne
	@JoinColumn(name="project")
	Project project;
	
	@ManyToOne
	@JoinColumn(name="requestor")
	Adviser requestor; 
	
	String requested; 
	
	String approved; 
	
	String inprogress;
	
	String withdrawn;
	
	String rejected;
	
	String published; 
	
	String researchers;

	String mainResearcher;
	
	String contactPerson;
	
	@ManyToOne
	@JoinColumn(name="status")
	CaseStudyStatus status; 
	
	String url;
	
	public CaseStudy(int id, String name, Project project, Adviser requestor, String requested, String approved,
			String inprogress, String withdrawn, String rejected, String published, String researchers, String mainResearcher, String contactPerson, CaseStudyStatus status,
			String url) {
		super();
		this.id = id;
		this.name = name;
		this.project = project;
		this.requestor = requestor;
		this.requested = requested;
		this.approved = approved;
		this.inprogress = inprogress;
		this.withdrawn = withdrawn;
		this.rejected = rejected;
		this.published = published;
		this.researchers = researchers;
		this.mainResearcher = mainResearcher;
		this.contactPerson = contactPerson;
		this.status = status;
		this.url = url;
	}
	
	public CaseStudy(CaseStudyVO caseStudyVO, Project project, Adviser requester, CaseStudyStatus status) {
		super();
		this.id = caseStudyVO.getId();
		this.name = caseStudyVO.getName();
		this.project = project;
		this.requestor = requester;
		this.requested = caseStudyVO.getRequested() != null && !"".equals(caseStudyVO.getRequested()) ? caseStudyVO.getRequested() : null;
		this.inprogress = caseStudyVO.getInprogress() != null && !"".equals(caseStudyVO.getInprogress()) ? caseStudyVO.getInprogress() : null;
		this.approved = caseStudyVO.getApproved() != null && !"".equals(caseStudyVO.getApproved()) ? caseStudyVO.getApproved() : null;
		this.published = caseStudyVO.getPublished() != null && !"".equals(caseStudyVO.getPublished()) ? caseStudyVO.getPublished() : null;
		this.rejected = caseStudyVO.getRejected() != null && !"".equals(caseStudyVO.getRejected()) ? caseStudyVO.getRejected() : null;
		this.withdrawn = caseStudyVO.getWithdrawn() != null && !"".equals(caseStudyVO.getWithdrawn()) ? caseStudyVO.getWithdrawn() : null;
		/*if(status.getId() == 1){
			this.requested = new Timestamp(Calendar.getInstance().getTime().getTime()).toString();
		}else if(status.getId() == 2){
			this.inprogress = new Timestamp(Calendar.getInstance().getTime().getTime()).toString();
		}else if(status.getId() == 3){
			this.approved = new Timestamp(Calendar.getInstance().getTime().getTime()).toString();
		}else if(status.getId() == 4){
			this.published = new Timestamp(Calendar.getInstance().getTime().getTime()).toString();
		}else if(status.getId() == 5){
			this.rejected = new Timestamp(Calendar.getInstance().getTime().getTime()).toString();
		}else if(status.getId() == 6){
			this.withdrawn = new Timestamp(Calendar.getInstance().getTime().getTime()).toString();
		}*/
		this.researchers = caseStudyVO.getResearchers();
		this.mainResearcher = caseStudyVO.getMainResearcher();
		this.contactPerson = caseStudyVO.getContactPerson();
		this.status = status;
		this.url = caseStudyVO.getUrl();
	}

	public CaseStudy() {
		// TODO Auto-generated constructor stub
	}
	
	public CaseStudy(String name, Project project, Adviser requestor, String requested, String approved,
			String published) {
		super();
		this.name = name;
		this.project = project;
		this.requestor = requestor;
		this.requested = requested;
		this.approved = approved;
		this.published = published;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Adviser getRequestor() {
		return requestor;
	}
	public void setRequestor(Adviser requestor) {
		this.requestor = requestor;
	}
	public String getRequested() {
		return requested;
	}
	public void setRequested(String requested) {
		this.requested = requested;
	}
	public String getApproved() {
		return approved;
	}
	public void setApproved(String approved) {
		this.approved = approved;
	}
	public String getInprogress() {
		return inprogress;
	}
	public void setInprogress(String inprogress) {
		this.inprogress = inprogress;
	}
	public String getWithdrawn() {
		return withdrawn;
	}
	public void setWithdrawn(String withdrawn) {
		this.withdrawn = withdrawn;
	}
	public String getRejected() {
		return rejected;
	}
	public void setRejected(String rejected) {
		this.rejected = rejected;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public CaseStudyStatus getStatus() {
		return status;
	}
	public void setStatus(CaseStudyStatus status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getResearchers() {
		return researchers;
	}
	public void setResearchers(String researchers) {
		this.researchers = researchers;
	}

	public String getMainResearcher() {
		return mainResearcher;
	}
	public void setMainResearcher(String mainResearcher) {
		this.mainResearcher = mainResearcher;
	}
	

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	@Override
	public String toString() {
		return "CaseStudy [id=" + id + ", name=" + name + ", project=" + project + ", requestor=" + requestor
				+ ", requested=" + requested + ", approved=" + approved + ", inprogress=" + inprogress + ", withdrawn="
				+ withdrawn + ", rejected=" + rejected + ", published=" + published + ", researchers=" + researchers
				+ ", mainResearcher=" + mainResearcher + ", contactPerson=" + contactPerson + ", status=" + status
				+ ", url=" + url + "]";
	}
	
}
