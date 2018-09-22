package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.webapp.validation.DateFormatterCustom;
import org.thibaut.wheretoclimb.webapp.validation.UserValidator;
import org.thibaut.wheretoclimb.webapp.validation.UserForm;
import org.thibaut.wheretoclimb.model.beans.User;

@Controller
public class RegisterController {

	@Autowired
	private UserValidator userValidator;
	@Autowired
	private ManagerFactory managerFactory;


	// Set a form validator
	@InitBinder
	protected void initBinder( WebDataBinder dataBinder) {
		// Form target
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == UserForm.class) {
			dataBinder.setValidator(userValidator);
		}
		// ...
	}

	// Show Register page.
	@GetMapping("/register")
	public String viewRegister( Model model) {

		UserForm userForm = new UserForm();

		model.addAttribute("userForm", userForm);

		return "registerPage";
	}

	// This method is called to save the registration information.
	// @Validated: To ensure that this Form
	// has been Validated before this method is invoked.
	@PostMapping("/register")
	public String saveRegister( Model model,
	                            @ModelAttribute("userForm") @Validated UserForm userForm,
	                            BindingResult result,
	                            final RedirectAttributes redirectAttributes) {

		// Validate result
		if (result.hasErrors()) {
//			List<Country> countries = countryDAO.getCountries();
//			model.addAttribute("countries", countries);
			return "registerPage";
		}
		User newUser = null;

		User user = new User(/*userId,*/
				userForm.getEmail(),
				userForm.getPassword(),
				userForm.getFirstName(),
				userForm.getLastName(),
				userForm.getUserName(),
				userForm.getGender(),
				userForm.isEmailVisible(),
				DateFormatterCustom.getCurrentDate()/*,
				form.getDateOfBirth(),
				form.getGradeMax(),
				form.getGradeFirstAttempt(),
				form.getGradeAverage()*/);

		try {
			newUser = managerFactory.getUserManager().createUser(user);
		}
		// Other error!!
		catch (Exception e) {
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			return "registerPage";
		}

		redirectAttributes.addFlashAttribute("flashUser", newUser);

		return "redirect:/registerSuccessful";
	}

	@RequestMapping("/registerSuccessful")
	public String viewRegisterSuccessful(Model model) {

		return "registerSuccessfulPage";
	}
}
