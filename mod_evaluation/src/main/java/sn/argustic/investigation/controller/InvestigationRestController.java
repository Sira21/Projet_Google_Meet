package sn.argustic.investigation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sn.argustic.investigation.dto.QuestionDTO;
import sn.argustic.investigation.service.InvestigationService;

@RestController
@RequestMapping("/api")
public class InvestigationRestController {

	@Autowired
	private InvestigationService investigationService;

	@GetMapping("/questions")
	public List<QuestionDTO> getQuestions() {
		return investigationService.retrieveQuestions();
	}

	@PostMapping(path = { "/complete/{reference}/save" })
	public ResponseEntity<String> completeSurveySave(@PathVariable("reference") String reference,
			@RequestBody(required = false) List<Long> answers, Model model) {

		if (answers == null || answers.size() <= 0)
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Aucune réponse reçue");
		return investigationService.saveAnswers(reference, answers);
	}
}
