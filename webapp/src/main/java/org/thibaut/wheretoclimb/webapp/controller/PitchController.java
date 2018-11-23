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
import org.thibaut.wheretoclimb.webapp.validation.pojo.PitchForm;
import org.thibaut.wheretoclimb.webapp.validation.pojo.RouteForm;
import org.thibaut.wheretoclimb.webapp.validation.validator.PitchValidator;
import org.thibaut.wheretoclimb.webapp.validation.validator.RouteValidator;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class PitchController extends AbstractController{

	@Autowired
	private PitchValidator pitchValidator;

	// Set a form validator
	@InitBinder
	protected void initBinder( WebDataBinder dataBinder ) {
		// Form target
		Object target = dataBinder.getTarget( );
		if ( target == null ) {
			return;
		}

		if ( target.getClass( ) == PitchForm.class ) {
			dataBinder.setValidator( pitchValidator );
		}
	}


	@GetMapping("/public/showPitch")
	public String showPitch( Model model,
	                         Integer routeId,
	                        HttpSession httpSession,
	                        @RequestParam(name = "page", defaultValue = "0") int page,
	                        @RequestParam(name = "size", defaultValue = "5") int size){

		Optional< Route > routeOpt = Optional.ofNullable( getManagerFactory().getRouteManager().findRouteById( routeId ) );

		Page< Pitch > pitches = new PageImpl<Pitch>( routeOpt.get().getPitches(), PageRequest.of(page, size), routeOpt.get().getPitches().size()) ;

		User connectedUser = (User) httpSession.getAttribute( "connectedUser" );

		if (connectedUser!=null){
			putPitchesFromUserInModel( model , httpSession );
		}

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
	public String  createPitch( Model model,
	                            HttpSession httpSession ){
		model.addAttribute( "pitchForm" , new PitchForm() );
		putRoutesFromUserInModel( model , httpSession );
		getGradesAndVerticalities( model );
		return "view/createPitch";
	}


	@PostMapping( "/user/savePitch" )
	public String  savePitch( Model model,
	                          HttpSession httpSession,
	                          @ModelAttribute("pitchForm") @Validated PitchForm pitchForm,
	                          BindingResult result,
	                          final RedirectAttributes redirectAttributes ){

		if(result.hasErrors()){
			putPitchesFromUserInModel( model , httpSession );
			getGradesAndVerticalities( model );
			return "view/createPitch";
		}

		Pitch newPitch = null;
		Pitch pitchToSave = null;

		if( pitchForm.getId()==null ) {
			pitchToSave = GenericBuilder.of( Pitch::new )
					              .with( Pitch::setCreateDate, LocalDateTime.now( ) )
					              .with( Pitch::setRoute, pitchForm.getRoute( ) )
					              .with( Pitch::setName, pitchForm.getName( ) )
					              .with( Pitch::setGrade, pitchForm.getGrade( ) )
					              .with( Pitch::setLength, pitchForm.getLength( ) )
					              .with( Pitch::setNbAnchor, pitchForm.getNbAnchor( ) )
					              .with( Pitch::setVerticality, pitchForm.getVerticality( ) )
					              .with( Pitch::setStyle, pitchForm.getStyle( ) )
					              .build( );
		}
		//If a user wants to edit an existing route
		else if ( pitchForm.getId() != null) {
			pitchToSave = getManagerFactory( ).getPitchManager( ).findPitchById( pitchForm.getId( ) );
			pitchToSave.setUpdateDate( LocalDateTime.now( ) );
			pitchToSave.setRoute( pitchForm.getRoute() );
			pitchToSave.setName( pitchForm.getName());
			pitchToSave.setGrade( pitchForm.getGrade());
			pitchToSave.setLength( pitchForm.getLength());
			pitchToSave.setNbAnchor( pitchForm.getLength());
			pitchToSave.setVerticality( pitchForm.getVerticality());
			pitchToSave.setStyle( pitchForm.getStyle());
		}
		try {
			newPitch = getManagerFactory( ).getPitchManager( ).createPitch( pitchToSave );
		}
		// Other error!!
		catch ( Exception e ) {
			log.error( "error occuring create/update a route: " + pitchForm.getName( ), e );
			model.addAttribute( "errorMessage", "Error: " + e.getMessage( ) );
			getGradesAndVerticalities(model);
			putCragsFromUserInModel( model, httpSession );
			return "view/createRoute";
		}
		redirectAttributes.addFlashAttribute("flashPitch", newPitch);
		return "redirect:/user/createPitchConfirm";
	}


	@GetMapping("/user/createPitchConfirm")
	public String createPitchConfirm(){
		return "view/createPitchConfirm";
	}



	@GetMapping( "/user/editPitch" )
	public String editPitch( Model model,
	                         HttpSession httpSession,
	                         Integer id){
		PitchForm pitchForm = new PitchForm(getManagerFactory().getPitchManager().findPitchById( id ));
		putRoutesFromUserInModel( model , httpSession );
		getGradesAndVerticalities( model );
		model.addAttribute( "pitchForm", pitchForm);
		return "view/createPitch";
	}


	@PostMapping( "/user/deletePitch/{id}/{parentId}/{page}/{size}" )
	public String deletePitch(@PathVariable(name = "id") Integer id,
	                          @PathVariable(name = "parentId") Integer parentId,
	                          @PathVariable(name = "page") Integer page,
	                          @PathVariable(name = "size") Integer size){
		getManagerFactory().getPitchManager().deletePitch( id );
		return "redirect:/public/showPitch?routeId=" + parentId + "&page=" + page + "&size=" + size;
	}
}
