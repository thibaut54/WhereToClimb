package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.model.entity.Pitch;
import org.thibaut.wheretoclimb.model.entity.Route;

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
}
