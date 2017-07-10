package nz.org.nesi.collaboration.dao;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import nz.org.nesi.collaboration.model.ResourceInstance;

@Transactional
public interface ResourceInstanceDao  extends JpaRepository<ResourceInstance, Integer> {

}

