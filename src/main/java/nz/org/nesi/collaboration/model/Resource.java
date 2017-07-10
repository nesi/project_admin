package nz.org.nesi.collaboration.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the resource database table.
 * 
 */
@Entity
@Table(name="resource")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String code;

	private String description;

	//bi-directional many-to-one association to ResourceType
	@ManyToOne
	@JoinColumn(name="resource_type")
	private ResourceType resourceTypeBean;

	//bi-directional many-to-one association to ResourceInstance
	@OneToMany(mappedBy="resourceBean")
	private List<ResourceInstance> resourceInstances;

	public Resource() {
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

	public ResourceType getResourceTypeBean() {
		return this.resourceTypeBean;
	}

	public void setResourceTypeBean(ResourceType resourceTypeBean) {
		this.resourceTypeBean = resourceTypeBean;
	}

	public List<ResourceInstance> getResourceInstances() {
		return this.resourceInstances;
	}

	public void setResourceInstances(List<ResourceInstance> resourceInstances) {
		this.resourceInstances = resourceInstances;
	}

	public ResourceInstance addResourceInstance(ResourceInstance resourceInstance) {
		getResourceInstances().add(resourceInstance);
		resourceInstance.setResourceBean(this);

		return resourceInstance;
	}

	public ResourceInstance removeResourceInstance(ResourceInstance resourceInstance) {
		getResourceInstances().remove(resourceInstance);
		resourceInstance.setResourceBean(null);

		return resourceInstance;
	}

}