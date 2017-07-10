package nz.org.nesi.collaboration.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nz.org.nesi.collaboration.model.Adviser;

@Transactional
public interface AdviserDao extends JpaRepository<Adviser, Integer> {

	@Query(value = "SELECT * FROM adviser WHERE tuakiriToken = ?1", nativeQuery = true)
	Adviser findByTuakiriToken(String sharedToken);
	
	@Query(value = "SELECT * FROM adviser WHERE tuakiriUniqueId = ?1", nativeQuery = true)
	Adviser findByEppn(String eppn);
}
