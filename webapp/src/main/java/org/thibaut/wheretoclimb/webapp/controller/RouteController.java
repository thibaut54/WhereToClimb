package org.thibaut.wheretoclimb.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thibaut.wheretoclimb.model.entity.*;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import org.thibaut.wheretoclimb.webapp.validation.pojo.AreaForm;
import org.thibaut.wheretoclimb.webapp.validation.pojo.RouteForm;
import org.thibaut.wheretoclimb.webapp.validation.validator.RouteValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class RouteController extends AbstractController{

	@Autowired
	private RouteValidator routeValidator;

	// Set a form validator
	@InitBinder
	protected void initBinder( WebDataBinder dataBinder ) {
		// Form target
		Object target = dataBinder.getTarget( );
		if ( target == null ) {
			return;
		}

		if ( target.getClass( ) == RouteForm.class ) {
			dataBinder.setValidator( routeValidator );
		}
	}


	@GetMapping("/public/showRoute")
	public String showRoute( Model model,
	                         Integer cragId,
	                        HttpSession httpSession,
	                        @RequestParam(name = "page", defaultValue = "0") int page,
	                        @RequestParam(name = "size", defaultValue = "5") int size){

		Optional< Crag > cragOpt = Optional.ofNullable( getManagerFactory().getCragManager().findCragById( cragId ) );

		Page< Route > routes = new PageImpl<Route>( cragOpt.get().getRoutes(), PageRequest.of(page, size), cragOpt.get().getRoutes().size()) ;

		User connectedUser = (User) httpSession.getAttribute( "connectedUser" );

		if (connectedUser!=null){
			putRoutesFromUserInModel( model , httpSession );
		}

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
	public String  createRoute( Model model ,
	                            HttpSession httpSession ){
		model.addAttribute( "routeForm", new RouteForm() );
		putCragsFromUserInModel( model ,httpSession );
		getGradesAndVerticalities(model);
		return "view/createRoute";
	}


	@PostMapping( "/user/saveRoute" )
	public String  saveRoute( Model model,
	                          HttpSession httpSession,
	                          @ModelAttribute("routeForm") @Validated RouteForm routeForm,
	                          BindingResult result,
	                          final RedirectAttributes redirectAttributes ){

		if(result.hasErrors()){
			putRoutesFromUserInModel( model , httpSession );
			getGradesAndVerticalities(model);
			return "view/createRoute";
		}

		Route newRoute = null;
		Route routeToSave = null;

		if( routeForm.getId()==null ) {
			routeToSave = GenericBuilder.of( Route::new )
					                      .with( Route::setCreateDate, LocalDateTime.now( ) )
					                      .with( Route::setCrag, routeForm.getCrag( ) )
					                      .with( Route::setName, routeForm.getName( ) )
					                      .with( Route::setGrade, routeForm.getGrade( ) )
					                      .with( Route::setLength, routeForm.getLength( ) )
					                      .with( Route::setNbAnchor, routeForm.getNbAnchor( ) )
					                      .with( Route::setVerticality, routeForm.getVerticality( ) )
					                      .with( Route::setStyle, routeForm.getStyle( ) )
					                      .build( );
		}
		//If a user wants to edit an existing route
		else if ( routeForm.getId() != null) {
			routeToSave = getManagerFactory( ).getRouteManager( ).findRouteById( routeForm.getId( ) );
			routeToSave.setUpdateDate( LocalDateTime.now( ) );
			routeToSave.setCrag( routeForm.getCrag() );
			routeToSave.setName( routeForm.getName());
			routeToSave.setGrade( routeForm.getGrade());
			routeToSave.setLength( routeForm.getLength());
			routeToSave.setNbAnchor( routeForm.getLength());
			routeToSave.setVerticality( routeForm.getVerticality());
			routeToSave.setStyle( routeForm.getStyle());
		}
		try {
			newRoute = getManagerFactory( ).getRouteManager( ).createRoute( routeToSave );
		}
		// Other error!!
		catch ( Exception e ) {
			log.error( "error occuring create/update a route: " + routeForm.getName( ), e );
			model.addAttribute( "errorMessage", "Error: " + e.getMessage( ) );
			getGradesAndVerticalities(model);
			putCragsFromUserInModel( model, httpSession );
			return "view/createRoute";
		}
		redirectAttributes.addFlashAttribute("flashRoute", newRoute);
		return "redirect:/user/createRouteConfirm";
	}


	@GetMapping("/user/createRouteConfirm")
	public String createRouteConfirm(){
		return "view/createRouteConfirm";
	}


	@GetMapping( "/user/editRoute" )
	public String editRoute( Model model,
	                         HttpSession httpSession,
	                         Integer id){
		RouteForm routeForm = new RouteForm(getManagerFactory().getRouteManager().findRouteById( id ));
		putCragsFromUserInModel( model, httpSession );
		getGradesAndVerticalities(model);
		model.addAttribute( "routeForm", routeForm );
		return "view/createRoute";
	}


	@PostMapping( "/user/deleteRoute/{id}/{parentId}/{page}/{size}" )
	public String deleteRoute(@PathVariable(name = "id") Integer id,
	                         @PathVariable(name = "parentId") Integer parentId,
	                         @PathVariable(name = "page") Integer page,
	                         @PathVariable(name = "size") Integer size){
		getManagerFactory().getRouteManager().deleteRoute( id );
		return "redirect:/public/showRoute?cragId=" + parentId + "&page=" + page + "&size=" + size ;
	}

}
