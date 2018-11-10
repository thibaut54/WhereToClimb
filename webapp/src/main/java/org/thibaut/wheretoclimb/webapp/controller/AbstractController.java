package org.thibaut.wheretoclimb.webapp.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.*;

import javax.persistence.EntityManager;
import java.util.Optional;

@Controller
public abstract class AbstractController {

	private ManagerFactory managerFactory;

	@Autowired
	private EntityManager em;


	ManagerFactory getManagerFactory( ) {
		return managerFactory;
	}

	@Autowired
	public void setManagerFactory( ManagerFactory managerFactory ) {
		this.managerFactory = managerFactory;
	}


	void setConnectedUser(Model model){
		//Get the connected user
		Optional<User> userConnectedOpt = Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );

		boolean isConnected = false;

		if( userConnectedOpt.isPresent() ){
			model.addAttribute( "user", userConnectedOpt );
			isConnected = true;
		}
		model.addAttribute( "isConnected", isConnected );
	}


	Optional<User> getConnectedUser(){
		return Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );
	}


	void isUserAdmin( Model model ){
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


	void isCommented( Model model, Element atlas ){

		boolean elementIsCommented = false;

		if( ! atlas.getComments().isEmpty() ){
			elementIsCommented = true;
			model.addAttribute( "elementIsCommented", elementIsCommented );
		}
	}

	void getGradesAndVerticalities( Model model ){
		model.addAttribute( "grades" , Grade.getGrades() );
		model.addAttribute( "verticalities" , Verticality.getVerticalities() );
	}

}
