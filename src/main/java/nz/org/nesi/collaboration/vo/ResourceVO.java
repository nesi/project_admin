package nz.org.nesi.collaboration.vo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * 
 * @author kraj562
 * Mapping class
 */

public class ResourceVO {
	
	private int id;

	private String code;

	private String description;

	private ResourceTypeVO resourceTypeVO;

	private List<ResourceInstanceVO> resourceInstanceVO;

	public ResourceVO() {
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

	public ResourceTypeVO getResourceTypeVO() {
		return resourceTypeVO;
	}

	public void setResourceTypeVO(ResourceTypeVO resourceTypeVO) {
		this.resourceTypeVO = resourceTypeVO;
	}

	public List<ResourceInstanceVO> getResourceInstanceVO() {
		return resourceInstanceVO;
	}

	public void setResourceInstanceVO(List<ResourceInstanceVO> resourceInstanceVO) {
		this.resourceInstanceVO = resourceInstanceVO;
	}

}