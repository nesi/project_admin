package nz.org.nesi.collaboration.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import nz.org.nesi.collaboration.model.CaseStudyStatus;

@Transactional
public interface CasestudystatusDao extends JpaRepository<CaseStudyStatus, Integer> {

}
