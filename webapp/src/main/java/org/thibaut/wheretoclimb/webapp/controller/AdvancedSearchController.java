package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.User;

import java.util.Optional;

@Controller
public class AdvancedSearchController {

	@Autowired
	private ManagerFactory managerFactory;


	@GetMapping("/public/advancedSearch")
	public String search(  Model model,
	                       @RequestParam(name = "page", defaultValue = "0") int page,
	                       @RequestParam(name = "size", defaultValue = "5") int size,
	                       @RequestParam(name = "keyword", defaultValue = "") String keyword){

//		Page< Atlas > atlases = this.managerFactory.getAtlasManager().searchAtlas(page, size, keyword);

		isUserAdmin( model );

		model.addAttribute( "connectedUser" , this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()));
//		model.addAttribute( "isConnected", isConnected );
//		model.addAttribute( "atlases", atlases.getContent() );

//		int[] pages = new int[atlases.getTotalPages()];
//		model.addAttribute( "pages", pages );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );
		model.addAttribute( "keyword", keyword );

		return "view/advancedSearch";
	}


	private void isUserAdmin( Model model ){
		//Get the connected user
		Optional< User > userConnectedOpt = Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );

		boolean isConnected = false;

		//If there is a connected user, put a boolean at true in the model
		if( userConnectedOpt.isPresent() ){

			User userConnected = userConnectedOpt.get();

			isConnected = true;
			final boolean[] isAdmin = { false };

			userConnected.getRoles().forEach( role -> isAdmin[0] = role.getRole( ).equals( "ROLE_ADMIN" ) );

			model.addAttribute( "userIsAdmin" , isAdmin[0] );
			model.addAttribute( "isConnected", isConnected );

		}
	}
}
