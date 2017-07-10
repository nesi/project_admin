package nz.org.nesi.collaboration.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "casestudystatus")
public class CaseStudyStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String status;
	
	public CaseStudyStatus(){}
	
	public CaseStudyStatus(int id, String status){
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CaseStudyStatus [id=" + id + ", status=" + status + "]";
	}
}
