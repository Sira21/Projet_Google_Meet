package sn.argustic.investigation.repository;

import org.springframework.data.repository.CrudRepository;

import sn.argustic.investigation.domain.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{

}
