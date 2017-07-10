package nz.org.nesi.collaboration.vo;
/**
 * 
 * @author kraj562
 * Mapping class
 */
public class ResourceProjectUsageVO {

	private int id;

	private String adviser;

	private int allocation;

	private String date;

	private String note;

	private String project;

	private String value;

	private String resourceInstance;

	public ResourceProjectUsageVO() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdviser() {
		return this.adviser;
	}

	public void setAdviser(String adviser) {
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

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getResourceInstance() {
		return resourceInstance;
	}

	public void setResourceInstance(String resourceInstance) {
		this.resourceInstance = resourceInstance;
	}

}