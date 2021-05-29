package sn.argustic.investigation.repository;

import org.springframework.data.repository.CrudRepository;

import sn.argustic.investigation.domain.Investigation;

public interface InvestigationRepository extends CrudRepository<Investigation, Long>{

	public Investigation findByReference(String reference);
}
