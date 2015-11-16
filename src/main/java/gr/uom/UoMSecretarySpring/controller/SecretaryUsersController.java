/**
 * 
 */
package gr.uom.UoMSecretarySpring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/secretary/users")
public class SecretaryUsersController {

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

	@ModelAttribute
	public void addingCommonObject(Model model) {
		model.addAttribute("title", "University of Macedonia");
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("usersDetails", userDetailsService.findAll());
		return "secretary/listUsersDetails";
	}

	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String createUser(Model model) {
		model.addAttribute("userDetails", new UserDetails());
		model.addAttribute("containerTitle", "Create a user");
		return "secretary/createUser";
	}

	@RequestMapping(value="insert", method=RequestMethod.POST)
	public ModelAndView storeLesson(
			@RequestParam(value="user.username") String username, 
			@RequestParam(value="user.password") String password, 
			@RequestParam(value="name") String name, 
			@RequestParam(value="surname") String surname, 
			@RequestParam(value="sex") String sex, 
			@RequestParam(value="telephone") String telephone, 
			@RequestParam(value="address") String address, 
			@RequestParam(value="user.role") String role, 
			@RequestParam(value="birthday") Date birthday, 
			@RequestParam(value="user.enabled") boolean enabled) {

		User user = new User(username, password, role, enabled);
		userService.insert(user);

		UserDetails userDetails = new UserDetails(username);
		userDetails.setAddress(address);
		userDetails.setBirthday(birthday);
		userDetails.setName(name);
		userDetails.setSex(sex);
		userDetails.setSurname(surname);
		userDetails.setTelephone(telephone);
		userDetails.setUser(userService.findByUsername(username));
		userDetails.setUsername(username);
		userDetailsService.insert(userDetails);

		ModelAndView model = new ModelAndView("secretary/listUsersDetails");
		model.addObject("usersDetails", userDetailsService.findAll());
		return model;
	}

	@RequestMapping(value = {"edit/{username}"}, method = RequestMethod.GET)
	public String editUserDetails(@PathVariable(value="username") String username, Model model) {
		model.addAttribute("userDetails", userDetailsService.findByUsername(username));
		return "secretary/editUserDetails";
	}

	@RequestMapping(value="edit", method=RequestMethod.POST)
	public ModelAndView storeEditedUserDetails(@ModelAttribute("userDetails") UserDetails userDetails, BindingResult bindingResult) {
		userDetailsService.update(userDetails);
		ModelAndView model = new ModelAndView("secretary/listUsersDetails");
		model.addObject("usersDetails", userDetailsService.findAll());
		return model;
	}

	@RequestMapping(value = {"delete/{username}"}, method = RequestMethod.GET)
	public String deleteLesson(@PathVariable(value="username") String username, Model model) {
		userDetailsService.delete(userDetailsService.findByUsername(username));
		userService.delete(userService.findByUsername(username));
		model.addAttribute("usersDetails", userDetailsService.findAll());
		return "secretary/listUsersDetails";
	}

}
