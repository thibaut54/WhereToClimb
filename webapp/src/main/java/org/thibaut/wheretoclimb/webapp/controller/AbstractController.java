package org.thibaut.wheretoclimb.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.Element;
import org.thibaut.wheretoclimb.model.entity.Grade;
import org.thibaut.wheretoclimb.model.entity.User;
import org.thibaut.wheretoclimb.model.entity.Verticality;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@Slf4j
public abstract class AbstractController {

	private ManagerFactory managerFactory;

	HttpSession httpSession;



	private boolean edit = false;

	@Autowired
	private EntityManager em;

	private Integer editedEntityId;


	protected ManagerFactory getManagerFactory( ) {
		return managerFactory;
	}

	@Autowired
	public void setManagerFactory( ManagerFactory managerFactory ) {
		this.managerFactory = managerFactory;
	}


	public User getConnectedUser ( HttpSession httpSession ){
		return getManagerFactory().getUserManager().findByUserName( httpSession.getAttribute( "userName" ).toString() );
	}


	void putUserInHttpSession( Model model, HttpSession httpSession ) {

			httpSession.setAttribute( "user" , getManagerFactory().getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );
	}


	Optional<User> getConnectedUser(){
		return Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );
	}


	void isUserAdmin( Model model ){
		//Get the connected user
		Optional<User> userConnectedOpt = Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );

		final boolean[] isAdmin = { false };
		model.addAttribute( "userIsAdmin" , isAdmin[0] );

		//If there is a connected user, put a boolean at true in the model
		if( userConnectedOpt.isPresent() ){

			User userConnected = userConnectedOpt.get();

			userConnected.getRoles().forEach( role -> isAdmin[0] = role.getRole( ).equals( "ROLE_ADMIN" ) );

			model.addAttribute( "userIsAdmin" , isAdmin[0] );

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
