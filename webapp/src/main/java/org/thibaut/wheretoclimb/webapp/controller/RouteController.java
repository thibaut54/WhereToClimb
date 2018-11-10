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

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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


	@GetMapping( "/user/createRoute" )
	public String  createRoute(Model model){
		setConnectedUser( model );
		model.addAttribute( "route" , new Route() );
		List< Atlas > atlases = getConnectedUser().get().getAtlases();
		List< Area > areas = new ArrayList<>();
		List< Crag > crags = new ArrayList<>();
		for ( Atlas atlas: atlases ) {
			areas.addAll( atlas.getAreas( ) );
		}
		for ( Area area: areas ) {
			crags.addAll( area.getCrags() );
		}
		model.addAttribute( "crags" , crags );
		getGradesAndVerticalities(model);
		return "view/createRoute";
	}


	@PostMapping( "/user/saveRoute" )
	public String  saveRoute( Model model, @Valid Route route, BindingResult result ){
		if(result.hasErrors()){
			return "view/createRoute";
		}
		route.setCreateDate( LocalDateTime.now() );
		route.setCrag( getManagerFactory().getCragManager().findById( route.getParentCreateId() ) );
		//Warnging : vérifier si ok avec plusieurs utilisateurs connecté en mm temps
		getManagerFactory().getRouteManager().saveRoute( route );
		return "view/createRouteConfirm";
	}
}
