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
import org.thibaut.wheretoclimb.webapp.validation.pojo.CragForm;
import org.thibaut.wheretoclimb.webapp.validation.validator.CragValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class CragController extends AbstractController {

	@Autowired
	private CragValidator cragValidator;

	// Set a form validator
	@InitBinder
	protected void initBinder( WebDataBinder dataBinder ) {
		// Form target
		Object target = dataBinder.getTarget( );
		if ( target == null ) {
			return;
		}

		if ( target.getClass( ) == CragForm.class ) {
			dataBinder.setValidator( cragValidator );
		}
	}


	@GetMapping("/public/showCrag")
	public String showCrag( Model model,
	                        Integer areaId,
	                        HttpSession httpSession,
	                        @RequestParam(name = "page", defaultValue = "0") int page,
	                        @RequestParam(name = "size", defaultValue = "5") int size){

		Optional< Area > areaOpt = Optional.ofNullable( getManagerFactory().getAreaManager().findAreaById( areaId ) );

		Page< Crag > crags = new PageImpl<Crag>( areaOpt.get().getCrags(), PageRequest.of(page, size), areaOpt.get().getCrags().size()) ;

		User connectedUser = (User) httpSession.getAttribute( "connectedUser" );

		if (connectedUser!=null){
			putCragsFromUserInModel( model, httpSession );
		}


		isCommented( model, areaOpt.get() );

		areaOpt.ifPresent( atlas -> model.addAttribute( "area", areaOpt.get() ) );
		model.addAttribute( "area" , areaOpt.get());
		model.addAttribute( "crags" , crags );
		model.addAttribute( "pages", new int[crags.getTotalPages()] );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );

		return "view/showCrag";
	}


	@GetMapping( "/user/createCrag" )
	public String  createCrag( Model model,
	                           HttpSession httpSession ){
		List<Atlas> atlases = getConnectedUser( httpSession ).getAtlases();
		List<Area> areas = new ArrayList<>();
		boolean hasArea = false;
		for ( Atlas atlas: atlases ) {
			if(!atlas.getAreas().isEmpty()){
				hasArea = true;
				break;
			}
		}
		if(!hasArea){
			return "error/noArea";
		}
		model.addAttribute( "cragForm" , new CragForm() );
		putAreasFromUserInModel( model, httpSession );
		if ( ! model.containsAttribute( "areas" ) ){
			return "error/noArea";
		}
		return "view/createCrag";
	}


	@PostMapping( "/user/saveCrag" )
	public String  saveCrag( Model model,
	                         HttpSession httpSession,
	                         @ModelAttribute @Validated CragForm cragForm,
	                         BindingResult result,
	                         final RedirectAttributes redirectAttributes){

		if(result.hasErrors()){
			putAreasFromUserInModel( model, httpSession );
			return "view/createCrag";
		}

		Crag newCrag = null;
		Crag cragTosave = null;

		if( cragForm.getId()==null ){
			cragTosave = GenericBuilder.of( Crag::new )
					.with( Crag::setCreateDate, LocalDateTime.now())
					.with( Crag::setArea, cragForm.getArea())
					.with( Crag::setName, cragForm.getName())
					.with( Crag::setAccess, cragForm.getAccess())
					.with( Crag::setApproachDuration, cragForm.getApproachDuration())
					.build();

		}
		else if ( cragForm.getId()!=null ){
			cragTosave = getManagerFactory().getCragManager().findCragById( cragForm.getId() );
			cragTosave.setArea( cragForm.getArea() );
			cragTosave.setName( cragForm.getName() );
			cragTosave.setUpdateDate( LocalDateTime.now() );
			cragTosave.setApproachDuration( cragForm.getApproachDuration() );
			cragTosave.setAccess( cragForm.getAccess() );
		}
		try {
			newCrag = getManagerFactory().getCragManager().createCrag(cragTosave);
		}
		// Other error!!
		catch (Exception e) {
			log.error( "error occuring create/update a Crag: " + cragForm.getName(), e );
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			putAreasFromUserInModel( model, httpSession );
			return "view/createCrag";
		}
		redirectAttributes.addFlashAttribute("flashCrag", newCrag);
		return "redirect:/user/createCragConfirm";
	}


	@GetMapping("/user/createCragConfirm")
	public String createCragConfirm(){
		return "view/createCragConfirm";
	}


	@GetMapping( "/user/editCrag" )
	public String editCrag( Model model,
	                        HttpSession httpSession,
	                        Integer id){
		CragForm cragForm = new CragForm( getManagerFactory().getCragManager().findCragById( id ));
		putAreasFromUserInModel( model, httpSession );
		model.addAttribute( "cragForm", cragForm );
		return "view/createCrag";
	}


	@PostMapping("/user/deleteCrag/{id}/{parentId}/{page}/{size}")
	public String deleteCrag( @PathVariable(name = "id") Integer id,
	                          @PathVariable(name = "parentId") Integer parentId,
	                          @PathVariable(name = "page") Integer page,
	                          @PathVariable(name = "size") Integer size){
		getManagerFactory().getCragManager().deleteCrag( id );
		return "redirect:/public/showCrag?areaId=" + parentId + "&page=" + page + "&size=" + size;
	}


}
