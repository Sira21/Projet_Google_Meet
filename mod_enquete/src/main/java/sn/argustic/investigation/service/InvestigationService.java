package sn.argustic.investigation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import sn.argustic.investigation.domain.Answer;
import sn.argustic.investigation.domain.Investigation;
import sn.argustic.investigation.domain.Possibility;
import sn.argustic.investigation.domain.Question;
import sn.argustic.investigation.dto.PossibilityDTO;
import sn.argustic.investigation.dto.QuestionDTO;
import sn.argustic.investigation.repository.AnswerRepository;
import sn.argustic.investigation.repository.InvestigationRepository;
import sn.argustic.investigation.repository.PossibilityRepository;
import sn.argustic.investigation.repository.QuestionRepository;

@Service
public class InvestigationService {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private PossibilityRepository possibilityRepository;
	@Autowired
	private InvestigationRepository investigationRepository;
	@Autowired
	private AnswerRepository answerRepository;

	public Investigation retrieveInvestigation(String reference) {
		if (reference == null || reference.isEmpty())
			return null;
		return investigationRepository.findByReference(reference.trim());
	}

	public List<QuestionDTO> retrieveQuestions() {
		List<QuestionDTO> questions = new ArrayList<>();
		questionRepository.findAll().forEach(question -> {
			questions.add(retrieveQuestions(question, possibilityRepository.findAllByQuestionId(question.getId())));
		});
		return questions;
	}

	public QuestionDTO retrieveQuestions(Question question, Iterable<Possibility> possibilities) {

		return new QuestionDTO(question.getId(), question.getLabel(), question.getNoteMax(),
				retrievePossibilities(possibilities));
	}

	public List<PossibilityDTO> retrievePossibilities(Iterable<Possibility> possibilities) {

		List<PossibilityDTO> possibilitiesDTO = new ArrayList<>();
		possibilities.forEach(possibility -> {
			possibilitiesDTO.add(new PossibilityDTO(possibility.getId(), possibility.getLabel()));
		});
		return possibilitiesDTO;
	}

	public ResponseEntity<String> saveAnswers(String reference, List<Long> answerIds) {

		if (answerIds.size() != questionRepository.count())
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(
					"Les réponses ne sont pas complétes " + answerIds.size() + "/" + questionRepository.count());

		Iterable<Possibility> possibilities = possibilityRepository.findAllByIdIn(answerIds);

		if (StreamSupport.stream(possibilities.spliterator(), false).count() != questionRepository.count())
			return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body("Certaines réponses sont fausses");

		Investigation investigation = investigationRepository.findByReference(reference);

		List<Answer> answers = new ArrayList<>();
		String anonymous = UUID.randomUUID().toString();
		possibilities.forEach(possibility -> {
			answers.add(new Answer(null, possibility, anonymous, investigation));
		});
		answerRepository.saveAll(answers);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Merci d'avoir pris le temps de répondre à ce questionnaire");
	}
}
