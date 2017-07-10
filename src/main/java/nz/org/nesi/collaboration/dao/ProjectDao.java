package nz.org.nesi.collaboration.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nz.org.nesi.collaboration.model.Project;

public interface ProjectDao extends JpaRepository<Project, Integer>{

	@Query(value = "SELECT DISTINCT CONCAT_WS(': ', projectCode, IF(LENGTH(name)>60,CONCAT(SUBSTRING(name,1,80), '...'),name)) FROM project ORDER BY projectCode", nativeQuery = true)
	List<String> findProjectCodes();
	
	@Query(value = "SELECT * FROM project WHERE projectCode = ?1", nativeQuery = true)
	Project findProjectByCode(String projectCode);
	
}
