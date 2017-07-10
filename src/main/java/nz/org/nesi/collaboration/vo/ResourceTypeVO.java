package nz.org.nesi.collaboration.vo;

import java.util.List;


/**
 * 
 * @author kraj562
 * Mapping class
 */

public class ResourceTypeVO {
	
	private int id;

	private String code;

	private String description;

	private String measure;

	private String unit;

	private List<ResourceVO> resourceVO;

	public ResourceTypeVO() {
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

	public String getMeasure() {
		return this.measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<ResourceVO> getResourceVO() {
		return resourceVO;
	}

	public void setResourceVO(List<ResourceVO> resourceVO) {
		this.resourceVO = resourceVO;
	}


//	public Resource addResource(Resource resource) {
//		getResources().add(resource);
//		resource.setResourceTypeBean(this);
//
//		return resource;
//	}
//
//	public Resource removeResource(Resource resource) {
//		getResources().remove(resource);
//		resource.setResourceTypeBean(null);
//
//		return resource;
//	}

}