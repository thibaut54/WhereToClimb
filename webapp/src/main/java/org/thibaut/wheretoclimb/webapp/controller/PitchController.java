package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thibaut.wheretoclimb.model.entity.*;
import org.thibaut.wheretoclimb.webapp.util.ParentEntityId;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PitchController extends AbstractController{

	@GetMapping("/public/showPitch")
	public String showPitch( Model model, Integer routeId,
	                        @RequestParam(name = "page", defaultValue = "0") int page,
	                        @RequestParam(name = "size", defaultValue = "5") int size){

		Optional< Route > routeOpt = Optional.ofNullable( getManagerFactory().getRouteManager().findById( routeId ) );

		Page< Pitch > pitches = new PageImpl<Pitch>( routeOpt.get().getPitches(), PageRequest.of(page, size), routeOpt.get().getPitches().size()) ;

		isUserAdmin( model );

		isCommented( model, routeOpt.get() );

		routeOpt.ifPresent( atlas -> model.addAttribute( "route", routeOpt.get() ) );
//		model.addAttribute( "route", routeOpt.get() );
		model.addAttribute( "pitches" , pitches );
		model.addAttribute( "pages", new int[pitches.getTotalPages()] );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );

		return "view/showPitch";
	}


	@GetMapping( "/user/createPitch" )
	public String  createPitch(Model model){
		setConnectedUser( model );
		model.addAttribute( "pitch" , new Pitch() );
		List< Atlas > atlases = getConnectedUser().get().getAtlases();
		List< Area > areas = new ArrayList<>();
		List< Crag > crags = new ArrayList<>();
		List< Route > routes = new ArrayList<>();
		for ( Atlas atlas: atlases ) {
			areas.addAll( atlas.getAreas( ) );
		}
		for ( Area area: areas ) {
			crags.addAll( area.getCrags() );
		}
		for ( Crag crag: crags ) {
			routes.addAll( crag.getRoutes() );
		}
		model.addAttribute( "routes" , routes );
		getGradesAndVerticalities(model);
		return "view/createPitch";
	}


	@PostMapping( "/user/savePitch" )
	public String  savePitch( Model model, @Valid Pitch pitch, BindingResult result ){
		if(result.hasErrors()){
			return "view/createPitch";
		}
		pitch.setCreateDate( LocalDateTime.now() );
		pitch.setRoute( getManagerFactory().getRouteManager().findById( pitch.getParentCreateId() ) );
//		pitch.setRoute( getManagerFactory().getRouteManager().findAtlasById( parentId.getParentId() ) );
		//Warnging : vérifier si ok avec plusieurs utilisateurs connecté en mm temps
		getManagerFactory().getPitchManager().savePitch( pitch );
		return "view/createPitchConfirm";
	}
}
