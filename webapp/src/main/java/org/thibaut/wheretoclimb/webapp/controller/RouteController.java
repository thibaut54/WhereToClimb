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
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.Crag;
import org.thibaut.wheretoclimb.model.entity.Route;

import javax.servlet.http.HttpSession;
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

		Optional< Crag > cragOpt = Optional.ofNullable( getManagerFactory().getCragManager().findCragById( cragId ) );

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
	public String  createRoute( Model model /*, HttpSession httpSession*/ ){
//		putUserInHttpSession( httpSession );
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
		if( route.getId()==null ){
			route.setCreateDate( LocalDateTime.now() );
//			route.setCrag( getManagerFactory().getCragManager().findCragById( route.getParentCreateId() ) );
		}
		else if ( route.getId()!=null ){
			route.setUpdateDate( LocalDateTime.now() );
		}
		getManagerFactory().getRouteManager().saveRoute( route );
		return "view/createRouteConfirm";
	}


	@GetMapping( "/admin/editRoute" )
	public String editRoute( Model model, Integer id){
		Route route = getManagerFactory().getRouteManager().findRouteById( id );
		model.addAttribute( "route", route );
		return "view/createRoute";
	}


	@GetMapping( "/admin/deleteRoute" )
	public String deleteRoute(Integer id, int page, int size){
		getManagerFactory().getRouteManager().deleteRoute( id );
		return "redirect:/public/showRoute?cragId=" + id + "&page=" + page + "&size=" + size;
	}
}
