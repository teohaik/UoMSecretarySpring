package gr.uom.uomsecretaryspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gr.uom.uomsecretaryspring.domain.Lesson;
import gr.uom.uomsecretaryspring.service.LessonService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Controller
@RequestMapping("/secretary/lessons")
public class SecretaryLessonsController {

	private static final String LESSONS = "lessons";
	private static final String SECRETARY_LIST_LESSONS = "secretary/listLessons";
	private LessonService lessonService;

	@Autowired(required=true)
	public void setLessonService(LessonService lessonService) {
		this.lessonService = lessonService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listLessons(Model model) {
		model.addAttribute(LESSONS, lessonService.findAll());
		model.addAttribute("containerTitle", "University of Macedonia Lessons");
		return SECRETARY_LIST_LESSONS;
	}

	@RequestMapping(value = {"insert"}, method = RequestMethod.GET)
	public String createLesson(Model model) {
		model.addAttribute("lesson", new Lesson());
		model.addAttribute("containerTitle", "Create a lesson");
		return "secretary/createLesson";
	}

	@RequestMapping(value="insert", method=RequestMethod.POST)
	public ModelAndView storeLesson(@ModelAttribute("lesson") Lesson lesson) {
		lessonService.insert(lesson);
		ModelAndView model = new ModelAndView(SECRETARY_LIST_LESSONS);
		model.addObject(LESSONS, lessonService.findAll());
		return model;
	}

	@RequestMapping(value = {"edit/{id}"}, method = RequestMethod.GET)
	public String editLesson(@PathVariable(value="id") Integer id, Model model) {
		model.addAttribute("lesson", lessonService.findById(id));
		return "secretary/editLesson";
	}

	@RequestMapping(value="edit", method=RequestMethod.POST)
	public ModelAndView storeEditedLesson(@ModelAttribute("lesson") Lesson lesson) {
		lessonService.update(lesson);
		ModelAndView model = new ModelAndView(SECRETARY_LIST_LESSONS);
		model.addObject(LESSONS, lessonService.findAll());
		return model;
	}

	@RequestMapping(value = {"delete/{id}"}, method = RequestMethod.GET)
	public String deleteLesson(@PathVariable(value="id") Integer id, Model model) {
		lessonService.delete(lessonService.findById(id));
		model.addAttribute(LESSONS, lessonService.findAll());
		return SECRETARY_LIST_LESSONS;
	}
}
