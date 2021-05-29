package sn.argustic.investigation.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import sn.argustic.investigation.domain.Possibility;

public interface PossibilityRepository extends CrudRepository<Possibility, Long>{

	public Iterable<Possibility> findAllByQuestionId(Long questionID);
	
	public Iterable<Possibility> findAllByIdIn(List<Long> possibilitiesID);
}
