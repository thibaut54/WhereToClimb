package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.model.entity.*;

import javax.servlet.http.HttpSession;
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

		Optional< Route > routeOpt = Optional.ofNullable( getManagerFactory().getRouteManager().findRouteById( routeId ) );

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
	public String  createPitch( Model model/*, HttpSession httpSession*/ ){
//		putUserInHttpSession( httpSession );
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
		if( pitch.getId()==null ){
			pitch.setCreateDate( LocalDateTime.now() );
		}
		else if ( pitch.getId()!=null ){
			pitch.setUpdateDate( LocalDateTime.now() );
		}
		getManagerFactory().getPitchManager().savePitch( pitch );
		return "view/createPitchConfirm";
	}


	@GetMapping( "/admin/editPitch" )
	public String editPitch( Model model, Integer id){
		Pitch pitch = getManagerFactory().getPitchManager().findPitchById( id );
		model.addAttribute( "pitch", pitch);
		return "view/createPitch";
	}

	@GetMapping( "/admin/deletePitch" )
	public String deletePitch(Integer id, int page, int size){
		getManagerFactory().getPitchManager().deletePitch( id );
		return "redirect:/public/showPitch?routeId=" + id + "?page=" + page + "&size=" + size;
	}
}
