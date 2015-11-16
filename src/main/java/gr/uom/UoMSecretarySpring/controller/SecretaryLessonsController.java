/**
 * 
 */
package gr.uom.UoMSecretarySpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gr.uom.UoMSecretarySpring.domain.Lesson;
import gr.uom.UoMSecretarySpring.service.LessonService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Controller
@RequestMapping("/secretary/lessons")
public class SecretaryLessonsController {

	private LessonService lessonService;

	@Autowired(required=true)
	public void setLessonService(LessonService lessonService) {
		this.lessonService = lessonService;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listLessons(Model model) {
		model.addAttribute("lessons", lessonService.findAll());
		model.addAttribute("containerTitle", "University of Macedonia Lessons");
		return "secretary/listLessons";
	}

	@RequestMapping(value = {"insert"}, method = RequestMethod.GET)
	public String createLesson(Model model) {
		model.addAttribute("lesson", new Lesson());
		model.addAttribute("containerTitle", "Create a lesson");
		return "secretary/createLesson";
	}

	@RequestMapping(value="insert", method=RequestMethod.POST)
	public ModelAndView storeLesson(@ModelAttribute("lesson") Lesson lesson, BindingResult bindingResult) {
		lessonService.insert(lesson);
		ModelAndView model = new ModelAndView("secretary/listLessons");
		model.addObject("lessons", lessonService.findAll());
		return model;
	}

	@RequestMapping(value = {"edit/{id}"}, method = RequestMethod.GET)
	public String editLesson(@PathVariable(value="id") Integer id, Model model) {
		model.addAttribute("lesson", lessonService.findById(id));
		return "secretary/editLesson";
	}

	@RequestMapping(value="edit", method=RequestMethod.POST)
	public ModelAndView storeEditedLesson(@ModelAttribute("lesson") Lesson lesson, BindingResult bindingResult) {
		lessonService.update(lesson);
		ModelAndView model = new ModelAndView("secretary/listLessons");
		model.addObject("lessons", lessonService.findAll());
		return model;
	}

	@RequestMapping(value = {"delete/{id}"}, method = RequestMethod.GET)
	public String deleteLesson(@PathVariable(value="id") Integer id, Model model) {
		lessonService.delete(lessonService.findById(id));
		model.addAttribute("lessons", lessonService.findAll());
		return "secretary/listLessons";
	}
}
