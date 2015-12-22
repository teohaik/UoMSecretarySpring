package gr.uom.UoMSecretarySpring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.uom.UoMSecretarySpring.service.LessonService;
import gr.uom.UoMSecretarySpring.service.UserDetailsService;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final String CONTAINER_TITLE = "containerTitle";
	@Autowired private LessonService lessonService;
	@Autowired private UserDetailsService userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@ModelAttribute
	public void addingCommonObject(Model model) {
		model.addAttribute("title", "University of Macedonia");
		model.addAttribute("homeMessage", "Secretary Home Page");
	}

	@RequestMapping(value = "/lessons", method = RequestMethod.GET)
	public String listLessons(Model model) {
		model.addAttribute(CONTAINER_TITLE, "Lessons");
		model.addAttribute("lessons", lessonService.findAll());
		return "listLessons";
	}

	@RequestMapping(value = "/professors", method = RequestMethod.GET)
	public String listProfessors(Model model) {
		model.addAttribute(CONTAINER_TITLE, "Professors");
		model.addAttribute("professors", userDetailsService.findByRole("ROLE_PROFESSOR"));
		return "listProfessors";
	}

	@RequestMapping(value = "/secretaries", method = RequestMethod.GET)
	public String listSecretaries(Model model) {
		model.addAttribute(CONTAINER_TITLE, "Secretaries");
		model.addAttribute("secretaries", userDetailsService.findByRole("ROLE_SECRETARY"));
		return "listSecretaries";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate );

		return "home";
	}

	public void setLessonService(LessonService lessonService) {
		this.lessonService = lessonService;
	}
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
