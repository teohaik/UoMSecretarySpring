/**
 * 
 */
package gr.uom.UoMSecretarySpring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gr.uom.UoMSecretarySpring.domain.Lesson;
import gr.uom.UoMSecretarySpring.domain.StudentEnrolledToLesson;
import gr.uom.UoMSecretarySpring.domain.StudentEnrolledToLessonPK;
import gr.uom.UoMSecretarySpring.domain.User;
import gr.uom.UoMSecretarySpring.domain.UserDetails;
import gr.uom.UoMSecretarySpring.service.LessonService;
import gr.uom.UoMSecretarySpring.service.StudentEnrolledToLessonService;
import gr.uom.UoMSecretarySpring.service.UserDetailsService;
import gr.uom.UoMSecretarySpring.service.UserService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Controller
@RequestMapping(value = "/student")
public class StudentController {

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
		model.addAttribute("containerTitle", userDetails.getName() + " " + userDetails.getSurname() + "'s admin panel");
		model.addAttribute("student", userDetails);
		return "student/home";
	}

	@RequestMapping(value = "/myLessons", method = RequestMethod.GET)
	public String myLessons(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("lessons", studentEnrolledToLessonService.findByStudent(username));
		model.addAttribute("containerTitle", "My enrolled Lessons");
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
		ModelAndView modelAndView = new ModelAndView("student/home");
		userDetailsService.update(userDetails);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		UserDetails updatedUserDetails = userDetailsService.findByUsername(username);
		modelAndView.addObject("containerTitle", updatedUserDetails.getName() + " " + updatedUserDetails.getSurname() + "'s admin panel");
		modelAndView.addObject("student", updatedUserDetails);
		return modelAndView;
	}

	@RequestMapping(value = "/enroll", method = RequestMethod.GET)
	public String enrollToLessons(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("containerTitle", "Choose the lessons that you would like to enroll");
		model.addAttribute("student", userService.findByUsername(username));
		model.addAttribute("lessons", lessonService.findByNotEnrolledStudent(username));
		return "student/enrollToLessons";
	}

	@RequestMapping(value="/enroll", method=RequestMethod.POST)
	public ModelAndView storeEnrollToLessons(@ModelAttribute("student") User student) {
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

		ModelAndView model = new ModelAndView("student/home");
		model.addObject("containerTitle", user.getUserDetails().getName() + " " + user.getUserDetails().getSurname() + "'s admin panel");
		model.addObject("student", userDetailsService.findByUsername(username));
		return model;
	}

	@RequestMapping(value = "/dis-enroll", method = RequestMethod.GET)
	public String disEnrollToLessons(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		model.addAttribute("containerTitle", "Choose the lessons that you would like to dis-enroll");
		model.addAttribute("student", userService.findByUsername(username));
		model.addAttribute("lessons", lessonService.findByEnrolledStudent(username));
		return "student/disenrollToLessons";
	}

	@RequestMapping(value="/dis-enroll", method=RequestMethod.POST)
	public ModelAndView storeDisenrollToLessons(@ModelAttribute("student") User student) {
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

		ModelAndView model = new ModelAndView("student/home");
		UserDetails userDetails = userDetailsService.findByUsername(username);
		model.addObject("containerTitle", userDetails.getName() + " " + userDetails.getSurname() + "'s admin panel");
		model.addObject("student", userDetails);
		return model;
	}

}
