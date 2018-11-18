package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController{

//	@Autowired
//	private ManagerFactory managerFactory;

	// Injectez (inject) via application.properties.
//	@Value("${welcome.message}")
//	private String message;
//
//	@Value("${error.message}")
//	private String errorMessage;


	@GetMapping("/public/login")
	public String login(Model model ){


		return "view/login";
	}



	@GetMapping("/public/logoutSuccessful")
	public String logout(Model model) {

		return "view/logoutSuccessful";

	}


//	@GetMapping("/403")
//	public String accessDenied(Model model, Principal principal) {
//
//		if (principal != null) {
//			User loginedUser = (User) (( Authentication ) principal).getPrincipal();
//
//			String userInfo = WebUtils.toString(loginedUser);
//
//			model.addAttribute("userInfo", userInfo);
//
//			String message = "Hi " + principal.getName() //
//					                 + "<br> You do not have permission to access this page!";
//			model.addAttribute("message", message);
//
//		}
//
//		return "403";
//	}

}
