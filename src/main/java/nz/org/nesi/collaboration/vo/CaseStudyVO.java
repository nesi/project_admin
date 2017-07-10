package nz.org.nesi.collaboration.vo;

import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;
import nz.org.nesi.collaboration.model.CaseStudy;
import nz.org.nesi.collaboration.model.Researcher;
import nz.org.nesi.collaboration.util.Util;

public class CaseStudyVO {
	
	int id;
	@NotEmpty
	String name;
	@NotEmpty
	String project;

	String requestor; 
	
	String requested; 
	
	String approved; 
	
	String inprogress;
	
	String withdrawn;
	
	String rejected;
	
	String published; 
	
	String researchers;
	
	List<Researcher> editResearchers;

	String mainResearcher;
	
	String contactPerson;
	
	String status; 
	
	String url;
	
	public CaseStudyVO(int id, String name, String project, String requestor, String requested, String approved,
			String inprogress, String withdrawn, String rejected, String published, String researchers, String mainResearcher, String contactPerson, String status,
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

	public CaseStudyVO() {
		// TODO Auto-generated constructor stub
	}
	
	public CaseStudyVO(String name, String project, String requestor, String requested, String approved,
			String published) {
		super();
		this.name = name;
		this.project = project;
		this.requestor = requestor;
		this.requested = requested;
		this.approved = approved;
		this.published = published;
	}
	
	public CaseStudyVO(CaseStudy caseStudy) {
		super();
		this.id = caseStudy.getId();
		this.name = caseStudy.getName();
		this.project = caseStudy.getProject().getProjectCode();
		this.requestor = caseStudy.getRequestor().getFullName();
		this.requested = Util.formatDate(caseStudy.getRequested(), "yyyy-MM-dd");
		this.approved = Util.formatDate(caseStudy.getApproved(), "yyyy-MM-dd");
		this.inprogress = Util.formatDate(caseStudy.getInprogress(), "yyyy-MM-dd");
		this.withdrawn = Util.formatDate(caseStudy.getWithdrawn(), "yyyy-MM-dd");
		this.rejected = Util.formatDate(caseStudy.getRejected(), "yyyy-MM-dd");
		this.published = Util.formatDate(caseStudy.getPublished(), "yyyy-MM-dd");
		this.researchers = caseStudy.getResearchers();
		this.mainResearcher = caseStudy.getMainResearcher();
		this.contactPerson = caseStudy.getContactPerson();
		this.status = caseStudy.getStatus().getStatus();
		this.url = caseStudy.getUrl();
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
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getRequestor() {
		return requestor;
	}
	public void setRequestor(String requestor) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public List<Researcher> getEditResearchers() {
		return editResearchers;
	}

	public void setEditResearchers(List<Researcher> editResearchers) {
		this.editResearchers = editResearchers;
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
				+ ", editResearchers=" + editResearchers + ", mainResearcher=" + mainResearcher + ", contactPerson=" + contactPerson + ", status=" + status
				+ ", url=" + url + "]";
	}
	
}
