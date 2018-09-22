package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.webapp.validation.WebUtils;
import org.springframework.security.core.userdetails.User;
import org.thibaut.wheretoclimb.model.beans.Atlas;

import java.security.Principal;
import java.util.List;

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


	@RequestMapping(value = { "/", "/indexJSP" }, method = RequestMethod.GET)
	public String indexJSP( Model model ) {

		String message = "Hello Spring Boot + JSP";

		model.addAttribute("message", message);

		return "index";
	}


	// Injectez (inject) via application.properties.
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@GetMapping("/index")
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}


	@GetMapping("/atlas")
	public String atlas(Model model){

		List< Atlas > atlases = this.managerFactory.getAtlasManager().getAtlases();

		model.addAttribute( "atlases", atlases );

		return "atlasPage";
	}


	@GetMapping("/userInfo")
	public String userInfo(Model model, Principal principal) {

		// After user login successfully.
		String userName = principal.getName();

		System.out.println("User Name: " + userName);

		User connectedUser = (User) ((Authentication) principal).getPrincipal();

		String userInfo = WebUtils.toString(connectedUser);
		model.addAttribute("userInfo", userInfo);

		return "userInfoPage";
	}


	@GetMapping("/admin")
	public String admin(Model model) {

		String msg = "Hello dear ADMIN!";

		model.addAttribute("message", msg);

		return "admin";
	}


	@GetMapping("/login")
	public String login(Model model){
		return "login";
	}


	@GetMapping("/logout")
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
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

		return "403Page";
	}


}
