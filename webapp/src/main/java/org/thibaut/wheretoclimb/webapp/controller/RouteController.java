package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.model.entity.Crag;
import org.thibaut.wheretoclimb.model.entity.Route;

import java.util.Optional;

@Controller
public class RouteController extends AbstractController{

	@GetMapping("/public/showRoute")
	public String showRoute( Model model, Integer cragId,
	                        @RequestParam(name = "page", defaultValue = "0") int page,
	                        @RequestParam(name = "size", defaultValue = "5") int size){

		Optional< Crag > cragOpt = Optional.ofNullable( getManagerFactory().getCragManager().findById( cragId ) );

		Page< Route > routes = new PageImpl<Route>( cragOpt.get().getRoutes(), PageRequest.of(page, size), cragOpt.get().getRoutes().size()) ;

		isUserAdmin( model );

		isCommented( model, cragOpt.get() );

		cragOpt.ifPresent( atlas -> model.addAttribute( "crag", cragOpt.get() ) );
		model.addAttribute( "crag", cragOpt.get() );
		model.addAttribute( "routes" , routes );
		model.addAttribute( "pages", new int[routes.getTotalPages()] );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );

		return "view/showRoute";
	}
}
