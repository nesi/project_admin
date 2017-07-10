package nz.org.nesi.collaboration.vo;

import java.util.List;


/**
 * 
 * @author kraj562
 * Mapping class
 */

public class ResourceInstanceVO {

	private int id;

	private String code;

	private String description;

	private ResourceVO resourceVO;

	private List<ResourceProjectUsageVO> resourceProjectUsageVO;

	public ResourceInstanceVO() {
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

	public ResourceVO getResourceVO() {
		return resourceVO;
	}

	public void setResourceVO(ResourceVO resourceVO) {
		this.resourceVO = resourceVO;
	}

	public List<ResourceProjectUsageVO> getResourceProjectUsageVO() {
		return resourceProjectUsageVO;
	}

	public void setResourceProjectUsageVO(List<ResourceProjectUsageVO> resourceProjectUsageVO) {
		this.resourceProjectUsageVO = resourceProjectUsageVO;
	}


}