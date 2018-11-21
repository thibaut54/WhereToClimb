package org.thibaut.wheretoclimb.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
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


	void putAtlasFromUserInModel( Model model, HttpSession httpSession ) {

		if ( httpSession.getAttribute( "connectedUserId" )!=null ) {

			List< Atlas > atlasesFromConnectedUser = getManagerFactory( ).getAtlasManager( ).findAtlasesByUserId( ( Integer ) httpSession.getAttribute( "connectedUserId" ) );
			List< Integer > atlasesIds = new ArrayList<>( );
			for ( Atlas atlas : atlasesFromConnectedUser ) {
				atlasesIds.add( atlas.getId( ) );
			}
			//		List< Area > areasFromConnectedUser = new ArrayList<>();
			//		List< Crag > cragFromConnectedUser = new ArrayList<>();
			//		List< Route > routesFromConnectedUser = new ArrayList<>();
			//		List< Pitch > pitchesFromConnectedUser = new ArrayList<>();
			//		List< Integer > atlasesIds = new ArrayList<>( );
			//		List< Integer > areasIds = new ArrayList<>( );
			//		List< Integer > cragsIds = new ArrayList<>( );
			//		List< Integer > routesIds = new ArrayList<>( );
			//		List< Integer > pitchesIds = new ArrayList<>( );
			//
			//		for ( Atlas atlas: atlasesFromConnectedUser ) {
			//			areasFromConnectedUser.addAll( atlas.getAreas( ) );
			//		}
			//		for ( Area area: areasFromConnectedUser) {
			//			cragFromConnectedUser.addAll( area.getCrags( ) );
			//		}
			//		for ( Crag crag: cragFromConnectedUser) {
			//			routesFromConnectedUser.addAll( crag.getRoutes( ) );
			//		}
			//		for ( Route route: routesFromConnectedUser) {
			//			pitchesFromConnectedUser.addAll( route.getPitches( ) );
			//		}
			//
			//		for ( Atlas atlas : atlasesFromConnectedUser ) {
			//			atlasesIds.add( atlas.getId( ) );
			//		}
			//		for ( Area area: areasFromConnectedUser ) {
			//			areasIds.add( area.getId( ) );
			//		}
			//		for ( Crag crag : cragFromConnectedUser) {
			//			cragsIds.add( crag.getId( ) );
			//		}
			//		for ( Route route : routesFromConnectedUser) {
			//			routesIds.add( route.getId( ) );
			//		}
			//		for ( Pitch pitch: pitchesFromConnectedUser) {
			//			pitchesIds.add( pitch.getId( ) );
			//		}

			model.addAttribute( "atlases", atlasesFromConnectedUser );
			//		httpsession.setAttribute( "areasFromConnectedUser" , areasFromConnectedUser );
			//		httpsession.setAttribute( "cragFromConnectedUser" , cragFromConnectedUser );
			//		httpsession.setAttribute( "routesFromConnectedUser" , atlasesFromConnectedUser );
			//		httpsession.setAttribute( "atlasesFromConnectedUser" , pitchesFromConnectedUser );
			model.addAttribute( "atlasesIds", atlasesIds );
			//		httpsession.setAttribute( "areasIds" , areasIds);
			//		httpsession.setAttribute( "cragsIds" , cragsIds);
			//		httpsession.setAttribute( "routesIds" , routesIds);
			//		httpsession.setAttribute( "pitchesIds" , pitchesIds);

		}
	}

	void putAreasFromUserInModel( Model model, HttpSession httpSession ) {
		List< Atlas > atlasesFromUser = getManagerFactory().getAtlasManager().findAtlasesByUserId( (Integer) httpSession.getAttribute("connectedUserId") );
		List<Area> areasFromUser = new ArrayList<>();

		for ( Atlas atlas: atlasesFromUser ) {
			areasFromUser.addAll( getManagerFactory().getAreaManager().findAreasByAtlasId( atlas.getId() ) );
		}

		List< Integer > areasId = new ArrayList<>( );
		model.addAttribute( "areas", areasFromUser );

		for ( Area area : areasFromUser ) {
			areasId.add( area.getId( ) );
		}
		model.addAttribute( "areasIds", areasId );
	}


	void putCragsFromUserInModel( Model model, HttpSession httpSession ) {
		List< Atlas > atlasesFromUser = getManagerFactory().getAtlasManager().findAtlasesByUserId( (Integer) httpSession.getAttribute("connectedUserId") );
		List<Area> areasFromUser = new ArrayList<>();
		List< Crag > cragsFromUser = new ArrayList<>();

		for ( Atlas atlas: atlasesFromUser ) {
			areasFromUser.addAll( getManagerFactory().getAreaManager().findAreasByAtlasId( atlas.getId() ) );
		}
		for ( Area area: areasFromUser) {
			cragsFromUser.addAll( area.getCrags( ) );
		}

		List< Integer > cragsIds = new ArrayList<>( );
		for ( Crag crag : cragsFromUser) {
			cragsIds.add( crag.getId( ) );
		}
		model.addAttribute( "crags", cragsFromUser);
		model.addAttribute( "cragsIds", cragsIds);

	}


	void putRoutesFromUserInModel( Model model, HttpSession httpSession ) {
		List< Atlas > atlasesFromUser = getManagerFactory().getAtlasManager().findAtlasesByUserId( (Integer) httpSession.getAttribute("connectedUserId") );
		List<Area> areasFromUser = new ArrayList<>();
		List< Crag > cragsFromUser = new ArrayList<>();
		List< Route > routesFromUser = new ArrayList<>();

		for ( Atlas atlas: atlasesFromUser ) {
			areasFromUser.addAll( getManagerFactory().getAreaManager().findAreasByAtlasId( atlas.getId() ) );
		}
		for ( Area area: areasFromUser) {
			cragsFromUser.addAll( area.getCrags( ) );
		}
		for ( Crag crag: cragsFromUser) {
			routesFromUser.addAll( crag.getRoutes( ) );
		}

		List< Integer > routesIds = new ArrayList<>( );
		for ( Route route : routesFromUser) {
			routesIds.add( route.getId( ) );
		}
		model.addAttribute( "routesFromUser", routesFromUser);
		model.addAttribute( "routesIds", routesIds);

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
