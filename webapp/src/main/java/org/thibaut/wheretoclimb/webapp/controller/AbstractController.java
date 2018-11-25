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

			List< Atlas > atlasesFromUser = getManagerFactory( ).getAtlasManager( ).findAtlasesByUserId( ( Integer ) httpSession.getAttribute( "connectedUserId" ) );
			List< Integer > atlasesIds = new ArrayList<>( );
			for ( Atlas atlas : atlasesFromUser ) {
				atlasesIds.add( atlas.getId( ) );
			}

			model.addAttribute( "atlases", atlasesFromUser );
			model.addAttribute( "atlasesIds", atlasesIds );

		}
	}

	void putAreasFromUserInModel( Model model, HttpSession httpSession ) {
		List< Atlas > atlasesFromUser = getManagerFactory().getAtlasManager().findAtlasesByUserId( (Integer) httpSession.getAttribute("connectedUserId") );
		List<Area> areasFromUser = new ArrayList<>();

		for ( Atlas atlas: atlasesFromUser ) {
			areasFromUser.addAll( getManagerFactory().getAreaManager().findAreasByAtlasId( atlas.getId() ) );
		}

		List< Integer > areasIds = new ArrayList<>( );
		model.addAttribute( "areas", areasFromUser );

		for ( Area area : areasFromUser ) {
			areasIds.add( area.getId( ) );
		}
		model.addAttribute( "areasIds", areasIds );
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
		model.addAttribute( "routes", routesFromUser);
		model.addAttribute( "routesIds", routesIds);

	}


	void putPitchesFromUserInModel( Model model, HttpSession httpSession ){
		List< Atlas > atlasesFromUser = getManagerFactory().getAtlasManager().findAtlasesByUserId( (Integer) httpSession.getAttribute("connectedUserId") );
		List<Area> areasFromUser = new ArrayList<>();
		List< Crag > cragsFromUser = new ArrayList<>();
		List< Route > routesFromUser = new ArrayList<>();
		List< Pitch > pitchesFromUser = new ArrayList<>();

		for ( Atlas atlas: atlasesFromUser ) {
			areasFromUser.addAll( getManagerFactory().getAreaManager().findAreasByAtlasId( atlas.getId() ) );
		}
		for ( Area area: areasFromUser) {
			cragsFromUser.addAll( area.getCrags( ) );
		}
		for ( Crag crag: cragsFromUser) {
			routesFromUser.addAll( crag.getRoutes( ) );
		}
		for ( Route route: routesFromUser) {
			pitchesFromUser.addAll( route.getPitches( ) );
		}

		List< Integer > pitchesIds = new ArrayList<>( );
		for ( Pitch pitch: pitchesFromUser ) {
			pitchesIds.add( pitch.getId( ) );
		}
		model.addAttribute( "pitches", pitchesFromUser);
		model.addAttribute( "pitchesIds", pitchesIds);
	}


	public User getConnectedUser ( HttpSession httpSession ){
		return getManagerFactory().getUserManager().findById( ( Integer ) httpSession.getAttribute( "connectedUserId" ) );
	}


	void putUserInHttpSession( Model model, HttpSession httpSession ) {

			httpSession.setAttribute( "user" , getManagerFactory().getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );
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
