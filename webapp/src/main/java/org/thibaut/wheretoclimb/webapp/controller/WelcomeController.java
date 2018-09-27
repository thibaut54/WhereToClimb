package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.webapp.validation.WebUtils;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;

@Controller
public class WelcomeController {

	@Autowired
	private ManagerFactory managerFactory;


//	@Autowired
//	private RoleRepository roleRepository;
//
//	@GetMapping(value = "/test/role/{recherche}")
//	public List< Role > testeDeRequetes( @PathVariable String recherche) {
//		return this.roleRepository.findByRoleLike("%"+recherche+"%");
//	}


	// Injectez (inject) via application.properties.
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;


	@GetMapping("/index")
	public String index(Model model) {

		model.addAttribute("message", message);

		return "view/index";
	}


	@GetMapping("/userInfo")
	public String userInfo(Model model, Principal principal) {

		// After user login successfully.
		String userName = principal.getName();

		System.out.println("User Name: " + userName);

		User connectedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(connectedUser);
		model.addAttribute("userInfo", userInfo);

		return "view/userInfoPage";
	}


	@GetMapping("/admin")
	public String admin(Model model) {

		String msg = "Hello dear ADMIN!";

		model.addAttribute("message", msg);

		return "view/admin";
	}


	@GetMapping("/login")
	public String login(Model model){
		return "view/login";
	}


	@GetMapping("/logoutt")
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout" );
		return "view/logoutSuccessfulPage";
	}


	@GetMapping("/403")
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			User loginedUser = (User) (( Authentication ) principal).getPrincipal();

			String userInfo = WebUtils.toString(loginedUser);

			model.addAttribute("userInfo", userInfo);

			String message = "Hi " + principal.getName() //
					                 + "<br> You do not have permission to access this page!";
			model.addAttribute("message", message);

		}

		return "error/403Page";
	}

}
