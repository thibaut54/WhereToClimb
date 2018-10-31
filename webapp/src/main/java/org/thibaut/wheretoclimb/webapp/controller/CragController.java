package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;
import org.thibaut.wheretoclimb.model.entity.Crag;

import java.util.Optional;

@Controller
public class CragController extends AbstractController {

	@GetMapping("/public/showCrag")
	public String showCrag( Model model, Integer areaId,
	                        @RequestParam(name = "page", defaultValue = "0") int page,
	                        @RequestParam(name = "size", defaultValue = "5") int size){

		Optional< Area > areaOpt = Optional.ofNullable( getManagerFactory().getAreaManager().findById( areaId ) );

		Page< Crag > crags = new PageImpl<Crag>( areaOpt.get().getCrags(), PageRequest.of(page, size), areaOpt.get().getCrags().size()) ;

		isUserAdmin( model );

		isCommented( model, areaOpt.get() );

		areaOpt.ifPresent( atlas -> model.addAttribute( "area", areaOpt.get() ) );
		model.addAttribute( "crags" , crags );
		model.addAttribute( "pages", new int[crags.getTotalPages()] );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );

		return "view/showCrag";
	}
}
