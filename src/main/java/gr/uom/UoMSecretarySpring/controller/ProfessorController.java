package gr.uom.uomsecretaryspring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gr.uom.uomsecretaryspring.domain.StudentEnrolledToLesson;
import gr.uom.uomsecretaryspring.domain.UserDetails;
import gr.uom.uomsecretaryspring.domain.wrapper.StudentEnrolledToLessonWrapper;
import gr.uom.uomsecretaryspring.service.LessonService;
import gr.uom.uomsecretaryspring.service.StudentEnrolledToLessonService;
import gr.uom.uomsecretaryspring.service.UserDetailsService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Controller
@RequestMapping(value = "/professor")
public class ProfessorController {

	private static final String CONTAINER_TITLE = "containerTitle";
	private static final String PROFESSOR_DETAILS = "professorDetails";
	private static final String PROFESSOR = "professor";
	private UserDetailsService userDetailsService;
	private LessonService lessonService;
	private StudentEnrolledToLessonService studentEnrolledToLessonService; 

	@Autowired(required=true)
	public void setStudentEnrolledToLessonService(StudentEnrolledToLessonService studentEnrolledToLessonService) {
		this.studentEnrolledToLessonService = studentEnrolledToLessonService;
	}

	@Autowired(required=true)
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Autowired(required=true)
	public void setLessonService(LessonService lessonService) {
		this.lessonService = lessonService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ModelAttribute
	public void addingCommonObject(Model model) {
		model.addAttribute("title", "University of Macedonia");
		model.addAttribute("homeMessage", "Professor Home Page");
	}

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String professorHome(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDetails userDetails = userDetailsService.findByUsername(username);
		model.addAttribute(PROFESSOR, userDetails);
		model.addAttribute(CONTAINER_TITLE, userDetails.getName() + " " + userDetails.getSurname() + "'s admin panel");
		return "professor/home";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUserDetails(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("userDetails", userDetailsService.findByUsername(username));
		model.addAttribute(CONTAINER_TITLE, "Edit your personal info");
		return "professor/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView storeEdit(@ModelAttribute("userDetails") UserDetails userDetails) {
		ModelAndView modelAndView = new ModelAndView("professor/home");
		userDetailsService.update(userDetails);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		modelAndView.addObject(PROFESSOR, userDetailsService.findByUsername(username));
		return modelAndView;
	}

	@RequestMapping(value = "/myLessons", method = RequestMethod.GET)
	public String professorsLessons(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDetails professorDetails = userDetailsService.findByUsername(username);
		model.addAttribute("lessons", lessonService.findByTeachProfessor(username));
		model.addAttribute(PROFESSOR_DETAILS, professorDetails);
		model.addAttribute(PROFESSOR, professorDetails.getUser());
		model.addAttribute(CONTAINER_TITLE, "Professor's: " + professorDetails.getName() + " " + professorDetails.getSurname() + " lessons");
		return "professor/myLessons";
	}

	@RequestMapping(value = "/setGrades/{lessonId}", method = RequestMethod.GET)
	public String setGrades(@PathVariable(value="lessonId") int lessonId, Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDetails professorDetails = userDetailsService.findByUsername(username);

		List<StudentEnrolledToLesson> students = studentEnrolledToLessonService.findByLessonId(lessonId);
		if (!students.isEmpty()) {
			StudentEnrolledToLessonWrapper studentEnrolledToLessonWrapper = new StudentEnrolledToLessonWrapper();
			studentEnrolledToLessonWrapper.setStudentsEnrolledToLessonList(students);

			model.addAttribute(CONTAINER_TITLE, "Set grades to students");
			model.addAttribute("studentEnrolledToLessonWrapper", studentEnrolledToLessonWrapper);
			model.addAttribute(PROFESSOR_DETAILS, professorDetails);
			model.addAttribute(PROFESSOR, professorDetails.getUser());
			return "professor/setGrades";
		}
		return "professor/noStudents";
	}

	@RequestMapping(value = "/setGrades", method = RequestMethod.POST)
	public ModelAndView storeGrades(@ModelAttribute("studentEnrolledToLessonWrapper") StudentEnrolledToLessonWrapper studentEnrolledToLessonWrapper) {
		List<StudentEnrolledToLesson> studentsEnrolledToLessonList = studentEnrolledToLessonWrapper.getStudentsEnrolledToLessonList();
		List<StudentEnrolledToLesson> studentsEnrolledToLessonUpdateList = new ArrayList<>();

		for (StudentEnrolledToLesson studentEnrolledToLesson : studentsEnrolledToLessonList) {
			Double grade = studentEnrolledToLesson.getGrade();
			String student = studentEnrolledToLesson.getUser().getUsername();
			Integer lessonId = studentEnrolledToLesson.getLesson().getId();

			studentEnrolledToLesson = studentEnrolledToLessonService.findByStudentAndLessonId(student, lessonId);
			studentEnrolledToLesson.setGrade(grade);
			studentsEnrolledToLessonUpdateList.add(studentEnrolledToLesson);
		}

		studentEnrolledToLessonService.update(studentsEnrolledToLessonUpdateList);

		ModelAndView modelAndView = new ModelAndView("professor/myLessons");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDetails professorDetails = userDetailsService.findByUsername(username);
		modelAndView.addObject("lessons", lessonService.findByTeachProfessor(username));
		modelAndView.addObject(PROFESSOR_DETAILS, professorDetails);
		modelAndView.addObject(PROFESSOR, professorDetails.getUser());
		return modelAndView;
	}

}
