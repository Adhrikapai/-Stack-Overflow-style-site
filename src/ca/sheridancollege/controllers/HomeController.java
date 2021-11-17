package ca.sheridancollege.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.sheridancollege.beans.Answers;
import ca.sheridancollege.beans.Questions;
import ca.sheridancollege.dao.DAO;
import ca.sheridancollege.dao.QuestionsDAO;

@Controller
public class HomeController {
	
	private DAO dao;
	private Questions questions = new Questions();

	@Autowired
	public HomeController(QuestionsDAO questionsDAO) {
		super();
		this.dao = questionsDAO;
		dao.populate();
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("questions", dao.getQuestionsList());
		return "home";
	}
	
	@GetMapping("/home")
	public String backhome(Model model) {
		model.addAttribute("questions", dao.getQuestionsList());
		return "home";
	}
	
	@GetMapping(path = "getQuestions/{qid}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> getQuestions(@PathVariable Long qid) {
		Map<String, Object> data = new HashMap<String, Object>();
		Questions questions = dao.getQuestionsById(qid);
		data.put("questions", questions);
		return data;
	}
	
	@GetMapping("addAnswers/{qid}")
	public String addComment(Model model, @PathVariable Long qid) {
		Answers answers = new Answers();
		System.out.println(qid);
		List<Questions> questions = dao.getQuestionsListById(qid);
		model.addAttribute("questions", questions);
		model.addAttribute("answers", answers);
		return "answer";
	}
	
	
	@GetMapping("upVote/{id}/{qid}")
	public String voteLike(Model model, @PathVariable Long id, @PathVariable Long qid) {
		System.out.println("Upvote clicked");
		Answers answers = new Answers();
		dao.updateAnswerVotes(id);
		model.addAttribute("questions", dao.getQuestionsListById(qid));
		model.addAttribute("answers", answers);
		return "answer";
	}
	
	@PostMapping("insertAnswers/{qid}")
	public String insertAnswers(Model model, @PathVariable Long qid, @ModelAttribute Answers answer) {
		System.out.println(qid);
		List<Questions> questions = dao.getQuestionsList();
		if (qid != 0) {
			for (int i = 0; i < questions.size(); i++) {
				if (qid == questions.get(i).getQid()) {
					dao.insertAnswers(answer);
					questions.get(i).getAnswers().add(answer);
					dao.insertQuestions(questions.get(i));
				}
			}
		}
		model.addAttribute("questions", dao.getQuestionsListById(qid));
		return "answer";
	}
	
	@GetMapping("/addQuestion")
	public String addDisney(Model model) {
		System.out.println("Add new question");
		Questions questions = new Questions();
		model.addAttribute("questions",questions);
		return "question";
	}
	
	@PostMapping("saveQuestion")
	public String saveDisney(Model model,@ModelAttribute Questions questions) {
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Questions>> constraintViolations = validator.validate(questions);
		if (constraintViolations.size() > 0) {
			for (ConstraintViolation<Questions> violation : constraintViolations) {
				model.addAttribute(violation.getPropertyPath().toString().replace(".", "_"), violation.getMessage());
				model.addAttribute("questions", questions);
			}
			return "question";
		}
			else {
				dao.insertQuestions(questions);
			}
		model.addAttribute("questions", dao.getQuestionsList());
		return "home";
	}
	


}
