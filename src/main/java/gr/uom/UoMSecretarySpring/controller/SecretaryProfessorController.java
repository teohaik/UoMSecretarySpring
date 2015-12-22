package gr.uom.UoMSecretarySpring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gr.uom.UoMSecretarySpring.domain.Lesson;
import gr.uom.UoMSecretarySpring.domain.ProfessorTeachesLessons;
import gr.uom.UoMSecretarySpring.domain.ProfessorTeachesLessonsPK;
import gr.uom.UoMSecretarySpring.domain.User;
import gr.uom.UoMSecretarySpring.domain.UserDetails;
import gr.uom.UoMSecretarySpring.service.LessonService;
import gr.uom.UoMSecretarySpring.service.ProfessorTeachesLessonsService;
import gr.uom.UoMSecretarySpring.service.UserDetailsService;
import gr.uom.UoMSecretarySpring.service.UserService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Controller
@RequestMapping("/secretary/professors")
public class SecretaryProfessorController {

	private static final String PROFESSOR = "professor";
	private static final String PROFESSOR_DETAILS = "professorDetails";
	private static final String PROFESSORS = "professors";
	private static final String SECRETARY_PROFESSORS = "secretary/professors";
	private static final String LESSONS = "lessons";
	private static final String ROLE_PROFESSOR = "ROLE_PROFESSOR";
	private UserDetailsService userDetailsService;
	private UserService userService;
	private LessonService lessonService;
	private ProfessorTeachesLessonsService professorTeachesLessonsService;

	@Autowired(required=true)
	public void setProfessorTeachesLessonsService(ProfessorTeachesLessonsService professorTeachesLessonsService) {
		this.professorTeachesLessonsService = professorTeachesLessonsService;
	}

	@Autowired(required=true)
	public void setLessonService(LessonService lessonService) {
		this.lessonService = lessonService;
	}

	@Autowired(required=true)
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	@Autowired(required=true)
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String secretaryProfessorsHome(Model model) {
		model.addAttribute(PROFESSORS, userDetailsService.findByRole(ROLE_PROFESSOR));
		return SECRETARY_PROFESSORS;
	}

	@RequestMapping(value = "/assign/{username}", method = RequestMethod.GET)
	public String assignLessons(@PathVariable(value="username") String username, Model model) {
		UserDetails professorDetails = userDetailsService.findByUsername(username);
		model.addAttribute(LESSONS, lessonService.findByNotTeachProfessor(username));
		model.addAttribute(PROFESSOR_DETAILS, professorDetails);
		model.addAttribute(PROFESSOR, professorDetails.getUser());
		model.addAttribute("containerTitle", "Assign Lessons to Proffesor: " + professorDetails.getName() + " " + professorDetails.getSurname());
		return "secretary/assignLessons";
	}

	@RequestMapping(value="/assign/{username}", method=RequestMethod.POST)
	public ModelAndView storeAssignedLessons(@PathVariable(value="username") String username, @ModelAttribute(PROFESSOR) User professor) {

		List<ProfessorTeachesLessons> professorTeachesLessonsList = new ArrayList<>();
		List<Integer> checkedLessons = professor.getCheckedLessons();
		List<Lesson> lessons = lessonService.findByIds(checkedLessons);

		User user = userService.findByUsername(username);

		for (Lesson lesson : lessons) {
			ProfessorTeachesLessons professorTeachesLesson = new ProfessorTeachesLessons(user.getUsername(), lesson.getId());
			professorTeachesLesson.setProfessorTeachesLessonsPK(new ProfessorTeachesLessonsPK(user.getUsername(), lesson.getId()));
			professorTeachesLesson.setLesson(lesson);
			professorTeachesLesson.setUser(user);
			professorTeachesLesson.setStartedFrom(new Date());
			professorTeachesLessonsList.add(professorTeachesLesson);
		}

		professorTeachesLessonsService.insert(professorTeachesLessonsList);
		ModelAndView model = new ModelAndView(SECRETARY_PROFESSORS);
		model.addObject(PROFESSORS, userDetailsService.findByRole(ROLE_PROFESSOR));
		return model;
	}

	@RequestMapping(value = "/revoke/{username}", method = RequestMethod.GET)
	public String revokeLessons(@PathVariable(value="username") String username, Model model) {
		UserDetails professorDetails = userDetailsService.findByUsername(username);
		model.addAttribute(LESSONS, lessonService.findByTeachProfessor(username));
		model.addAttribute(PROFESSOR_DETAILS, professorDetails);
		model.addAttribute(PROFESSOR, professorDetails.getUser());
		model.addAttribute("containerTitle", "Revoke Lessons from Proffesor: " + professorDetails.getName() + " " + professorDetails.getSurname()); 
		return "secretary/revokeLessons";
	}

	@RequestMapping(value="/revoke/{username}", method=RequestMethod.POST)
	public ModelAndView storeRevokedLessons(@PathVariable(value="username") String username, @ModelAttribute(PROFESSOR) User professor) {

		List<ProfessorTeachesLessons> professorTeachesLessonsList = new ArrayList<>();
		List<Integer> checkedLessons = professor.getCheckedLessons();
		List<Lesson> lessons = lessonService.findByIds(checkedLessons);

		for (Lesson lesson : lessons)
			professorTeachesLessonsList.add(professorTeachesLessonsService.findByProfessorAndLessonId(username, lesson.getId()));

		professorTeachesLessonsService.delete(professorTeachesLessonsList);
		ModelAndView model = new ModelAndView(SECRETARY_PROFESSORS);
		model.addObject(PROFESSORS, userDetailsService.findByRole(ROLE_PROFESSOR));
		return model;
	}

	@RequestMapping(value = "/professorsLessons/{username}", method = RequestMethod.GET)
	public String professorsLessons(@PathVariable(value="username") String username, Model model) {
		UserDetails professorDetails = userDetailsService.findByUsername(username);
		model.addAttribute(LESSONS, lessonService.findByTeachProfessor(username));
		model.addAttribute(PROFESSOR_DETAILS, professorDetails);
		model.addAttribute(PROFESSOR, professorDetails.getUser());
		return "secretary/professorsLessons";
	}

}
