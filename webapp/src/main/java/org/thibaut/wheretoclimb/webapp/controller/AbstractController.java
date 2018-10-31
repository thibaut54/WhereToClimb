package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.Element;
import org.thibaut.wheretoclimb.model.entity.User;

import java.util.Optional;

@Controller
public abstract class AbstractController {

//	@Autowired
	private ManagerFactory managerFactory;


	public ManagerFactory getManagerFactory( ) {
		return managerFactory;
	}

	@Autowired
	public void setManagerFactory( ManagerFactory managerFactory ) {
		this.managerFactory = managerFactory;
	}


	protected void isUserAdmin( Model model ){
		//Get the connected user
		Optional<User> userConnectedOpt = Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );

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

	protected void isCommented( Model model, Element atlas ){

		boolean elementIsCommented = false;

//		System.out.println( atlas.getComments().get( 0 ).getTitle());

		if( ! atlas.getComments().isEmpty() ){
			elementIsCommented = true;
			model.addAttribute( "atlasIsCommented", elementIsCommented );
		}

		System.out.println( "The atlas " + atlas.getName() + " has been commented: " + elementIsCommented );

	}

}
