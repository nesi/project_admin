package nz.org.nesi.collaboration.dao;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nz.org.nesi.collaboration.model.Researcher;


@Transactional
public interface ResearcherDao extends JpaRepository<Researcher, Integer> {
	
	@Query(value = "select * from researcher r where r.id in (?1)", nativeQuery = true)
	List<Researcher> findByIds(String[] researcherIds);
	
}
