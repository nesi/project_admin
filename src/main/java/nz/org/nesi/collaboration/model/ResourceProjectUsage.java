package nz.org.nesi.collaboration.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the resource_project_usage database table.
 * 
 */
@Entity
@Table(name="resource_project_usage")
@NamedQuery(name="ResourceProjectUsage.findAll", query="SELECT r FROM ResourceProjectUsage r")
public class ResourceProjectUsage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name="adviser")
	private Adviser adviser;

	private int allocation;

	private String date;

	private String note;

	@ManyToOne
	@JoinColumn(name="project")
	private Project project;

	private String value;

	//bi-directional many-to-one association to ResourceInstance
	@ManyToOne
	@JoinColumn(name="resource_instance")
	private ResourceInstance resourceInstance;

	public ResourceProjectUsage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Adviser getAdviser() {
		return this.adviser;
	}

	public void setAdviser(Adviser adviser) {
		this.adviser = adviser;
	}

	public int getAllocation() {
		return this.allocation;
	}

	public void setAllocation(int allocation) {
		this.allocation = allocation;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ResourceInstance getResourceInstance() {
		return this.resourceInstance;
	}

	public void setResourceInstance(ResourceInstance resourceInstance) {
		this.resourceInstance = resourceInstance;
	}

}