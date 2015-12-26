package gr.uom.uomsecretaryspring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gr.uom.uomsecretaryspring.service.UserDetailsService;
/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Controller
public class SecretaryController {

	private UserDetailsService userDetailsService;

	@Autowired(required=true)
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@ModelAttribute
	public void addingCommonObject(Model model) {
		model.addAttribute("title", "University of Macedonia");
		model.addAttribute("homeMessage", "Secretary Home Page");
	}

	@RequestMapping(value = {"/secretary", "/secretary/"}, method = RequestMethod.GET)
	public String secretaryHome(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userDetailsService.findByUsername(username));
		return "secretary/home";
	}

}
