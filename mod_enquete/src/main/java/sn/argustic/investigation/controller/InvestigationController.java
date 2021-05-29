package sn.argustic.investigation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sn.argustic.investigation.domain.Investigation;
import sn.argustic.investigation.service.InvestigationService;

@Controller
@RequestMapping("/")
public class InvestigationController {

	@Autowired
	private InvestigationService investigationService;

	@GetMapping(path = { "/" })
	public String home(@RequestParam(name = "reference", required = false) String reference, Model model) {
		model.addAttribute("reference", "");
		model.addAttribute("investigation", new Investigation());

		if (reference != null && !reference.isEmpty())
			return "redirect:/complete/" + reference.trim();
		return "index";
	}

	@PostMapping(path = { "/find" })
	public String findInvestigation(@ModelAttribute Investigation investigation, Model model) {

		String reference = investigation.getReference();
		investigation = investigationService.retrieveInvestigation(reference);

		if (investigation != null)
			return "redirect:/complete/" + reference.trim();

		model.addAttribute("reference", reference);
		model.addAttribute("error", "Référence introuvable");
		return "index";
	}

	@GetMapping(path = { "/complete/{reference}" })
	public String completeSurvey(@PathVariable(name = "reference") String reference, Model model) {

		model.addAttribute("questions", investigationService.retrieveQuestions());
		return "survey";
	}
}
