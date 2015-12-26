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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gr.uom.uomsecretaryspring.domain.Lesson;
import gr.uom.uomsecretaryspring.domain.StudentEnrolledToLesson;
import gr.uom.uomsecretaryspring.domain.StudentEnrolledToLessonPK;
import gr.uom.uomsecretaryspring.domain.User;
import gr.uom.uomsecretaryspring.domain.UserDetails;
import gr.uom.uomsecretaryspring.service.LessonService;
import gr.uom.uomsecretaryspring.service.StudentEnrolledToLessonService;
import gr.uom.uomsecretaryspring.service.UserDetailsService;
import gr.uom.uomsecretaryspring.service.UserService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

	private static final String CONTAINER_TITLE = "containerTitle";
	private static final String LESSONS = "lessons";
	private static final String STUDENT = "student";
	private static final String ADMINS_PANEL = "'s admin panel";
	private static final String STUDENT_HOME = "student/home";
	private UserService userService;
	private UserDetailsService userDetailsService;
	private StudentEnrolledToLessonService studentEnrolledToLessonService;
	private LessonService lessonService;

	@Autowired(required=true)
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	@Autowired(required=true)
	public void setStudentEnrolledToLessonService(StudentEnrolledToLessonService studentEnrolledToLessonService) {
		this.studentEnrolledToLessonService = studentEnrolledToLessonService;
	}
	@Autowired(required=true)
	public void setLessonService(LessonService lessonService) {
		this.lessonService = lessonService;
	}
	@Autowired(required=true)
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String studentHome(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDetails userDetails = userDetailsService.findByUsername(username);
		model.addAttribute(CONTAINER_TITLE, userDetails.getName() + " " + userDetails.getSurname() + ADMINS_PANEL);
		model.addAttribute(STUDENT, userDetails);
		return STUDENT_HOME;
	}

	@RequestMapping(value = "/myLessons", method = RequestMethod.GET)
	public String myLessons(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute(LESSONS, studentEnrolledToLessonService.findByStudent(username));
		model.addAttribute(CONTAINER_TITLE, "My enrolled Lessons");
		return "student/myLessons";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUserDetails(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("userDetails", userDetailsService.findByUsername(username));
		return "student/edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView storeEdit(@ModelAttribute("userDetails") UserDetails userDetails) {
		ModelAndView modelAndView = new ModelAndView(STUDENT_HOME);
		userDetailsService.update(userDetails);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDetails updatedUserDetails = userDetailsService.findByUsername(username);
		modelAndView.addObject(CONTAINER_TITLE, updatedUserDetails.getName() + " " + updatedUserDetails.getSurname() + ADMINS_PANEL);
		modelAndView.addObject(STUDENT, updatedUserDetails);
		return modelAndView;
	}

	@RequestMapping(value = "/enroll", method = RequestMethod.GET)
	public String enrollToLessons(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute(CONTAINER_TITLE, "Choose the lessons that you would like to enroll");
		model.addAttribute(STUDENT, userService.findByUsername(username));
		model.addAttribute(LESSONS, lessonService.findByNotEnrolledStudent(username));
		return "student/enrollToLessons";
	}

	@RequestMapping(value="/enroll", method=RequestMethod.POST)
	public ModelAndView storeEnrollToLessons(@ModelAttribute(STUDENT) User student) {
		List<StudentEnrolledToLesson> studentEnrolledToLessonsList = new ArrayList<>();

		List<Integer> checkedLessons = student.getCheckedLessons();
		List<Lesson> lessons = lessonService.findByIds(checkedLessons);

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDetailsService.findByUsername(username).getUser();

		for (Lesson lesson : lessons) {
			StudentEnrolledToLesson studentEnrolledToLesson = new StudentEnrolledToLesson(user.getUsername(), lesson.getId());
			studentEnrolledToLesson.setStudentEnrolledToLessonPK(new StudentEnrolledToLessonPK(user.getUsername(), lesson.getId()));
			studentEnrolledToLesson.setLesson(lesson);
			studentEnrolledToLesson.setUser(user);

			studentEnrolledToLessonsList.add(studentEnrolledToLesson);
		}

		studentEnrolledToLessonService.insert(studentEnrolledToLessonsList);

		ModelAndView model = new ModelAndView(STUDENT_HOME);
		model.addObject(CONTAINER_TITLE, user.getUserDetails().getName() + " " + user.getUserDetails().getSurname() + ADMINS_PANEL);
		model.addObject(STUDENT, userDetailsService.findByUsername(username));
		return model;
	}

	@RequestMapping(value = "/dis-enroll", method = RequestMethod.GET)
	public String disEnrollToLessons(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute(CONTAINER_TITLE, "Choose the lessons that you would like to dis-enroll");
		model.addAttribute(STUDENT, userService.findByUsername(username));
		model.addAttribute(LESSONS, lessonService.findByEnrolledStudent(username));
		return "student/disenrollToLessons";
	}

	@RequestMapping(value="/dis-enroll", method=RequestMethod.POST)
	public ModelAndView storeDisenrollToLessons(@ModelAttribute(STUDENT) User student) {
		List<StudentEnrolledToLesson> studentEnrolledToLessonsList = new ArrayList<>();

		List<Integer> checkedLessons = student.getCheckedLessons();
		List<Lesson> lessons = lessonService.findByIds(checkedLessons);

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userDetailsService.findByUsername(username).getUser();

		for (Lesson lesson : lessons) {
			StudentEnrolledToLesson studentEnrolledToLesson = new StudentEnrolledToLesson(user.getUsername(), lesson.getId());
			studentEnrolledToLesson.setStudentEnrolledToLessonPK(new StudentEnrolledToLessonPK(user.getUsername(), lesson.getId()));
			studentEnrolledToLesson.setLesson(lesson);
			studentEnrolledToLesson.setUser(user);

			studentEnrolledToLessonsList.add(studentEnrolledToLesson);
		}
		studentEnrolledToLessonService.delete(studentEnrolledToLessonsList);

		ModelAndView model = new ModelAndView(STUDENT_HOME);
		UserDetails userDetails = userDetailsService.findByUsername(username);
		model.addObject(CONTAINER_TITLE, userDetails.getName() + " " + userDetails.getSurname() + ADMINS_PANEL);
		model.addObject(STUDENT, userDetails);
		return model;
	}

}
