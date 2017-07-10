package nz.org.nesi.collaboration.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the resource_instance database table.
 * 
 */
@Entity
@Table(name="resource_instance")
@NamedQuery(name="ResourceInstance.findAll", query="SELECT r FROM ResourceInstance r")
public class ResourceInstance implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String code;

	private String description;

	//bi-directional many-to-one association to Resource
	@ManyToOne
	@JoinColumn(name="resource")
	private Resource resourceBean;

	//bi-directional many-to-one association to ResourceProjectUsage
	@OneToMany(mappedBy="resourceInstance")
	private List<ResourceProjectUsage> resourceProjectUsages;

	public ResourceInstance() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Resource getResourceBean() {
		return this.resourceBean;
	}

	public void setResourceBean(Resource resourceBean) {
		this.resourceBean = resourceBean;
	}

	public List<ResourceProjectUsage> getResourceProjectUsages() {
		return this.resourceProjectUsages;
	}

	public void setResourceProjectUsages(List<ResourceProjectUsage> resourceProjectUsages) {
		this.resourceProjectUsages = resourceProjectUsages;
	}

	public ResourceProjectUsage addResourceProjectUsage(ResourceProjectUsage resourceProjectUsage) {
		getResourceProjectUsages().add(resourceProjectUsage);
		resourceProjectUsage.setResourceInstance(this);

		return resourceProjectUsage;
	}

	public ResourceProjectUsage removeResourceProjectUsage(ResourceProjectUsage resourceProjectUsage) {
		getResourceProjectUsages().remove(resourceProjectUsage);
		resourceProjectUsage.setResourceInstance(null);

		return resourceProjectUsage;
	}

}