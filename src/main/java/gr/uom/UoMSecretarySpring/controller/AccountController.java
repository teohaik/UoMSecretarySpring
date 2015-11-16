/**
 * 
 */
package gr.uom.UoMSecretarySpring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

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

import gr.uom.UoMSecretarySpring.domain.User;
import gr.uom.UoMSecretarySpring.domain.UserDetails;
import gr.uom.UoMSecretarySpring.service.UserDetailsService;
import gr.uom.UoMSecretarySpring.service.UserService;

/**
 * @author Georgios Digkas <mai153@uom.edu.gr>
 *
 */
@Controller
@RequestMapping(value="/myAccount")
public class AccountController {
	private UserDetailsService userDetailsService;
	private UserService userService;

	@Autowired(required=true)
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
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
		model.addAttribute("user", userDetails);
		model.addAttribute("containerTitle", "Hello, " + userDetails.getName() + " " + userDetails.getSurname());
		return "myAccount";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("userDetails", userDetailsService.findByUsername(username));
		model.addAttribute("containerTitle", "Edit your personal info");
		return "myAccountEdit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView storeEdit(@ModelAttribute("userDetails") UserDetails userDetails, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("myAccount");
		userDetailsService.update(userDetails);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		userDetails = userDetailsService.findByUsername(username);
		modelAndView.addObject("user", userDetails);
		modelAndView.addObject("containerTitle", "Hello, " + userDetails.getName() + " " + userDetails.getSurname());
		return modelAndView;
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("user", userService.findByUsername(username));
		model.addAttribute("containerTitle", "Enter your new password");
		return "changePassword";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView storeChangePassword(@ModelAttribute("user") User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("myAccount");
		System.out.println(user);
		userService.update(user);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		modelAndView.addObject("user", userDetailsService.findByUsername(username));
		return modelAndView;
	}
}
