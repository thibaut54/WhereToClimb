package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import org.thibaut.wheretoclimb.webapp.util.ParentEntityId;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class AreaController extends AbstractController {


	private Model model;

	@GetMapping("/public/showArea")
	public String showArea( Model model, Integer atlasId,
	                        @RequestParam(name = "page", defaultValue = "0") int page,
	                        @RequestParam(name = "size", defaultValue = "5") int size/*,
	                        @RequestParam(name = "keyword", defaultValue = "") String keyword*/){

		Optional< Atlas > atlasOpt = Optional.ofNullable( getManagerFactory().getAtlasManager().findAtlasById( atlasId ) );

		Page< Area > areas = new PageImpl<Area>( atlasOpt.get().getAreas(), PageRequest.of(page, size),atlasOpt.get().getAreas().size()) ;

		isUserAdmin( model );

		isCommented( model, atlasOpt.get() );

		atlasOpt.ifPresent( atlas -> model.addAttribute( "atlas", atlas ) );
		model.addAttribute( "areas" , areas );
		model.addAttribute( "pages", new int[areas.getTotalPages()] );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );
//		model.addAttribute( "keyword", keyword );
		model.addAttribute( "bookingRequest" , new BookingRequest() );

		//		} else {
//			return "error/403";
//		}

		return "view/showArea";
	}

	@PostMapping("/user/saveBookingRequest")
	public String saveBookingRequest(Model model, Integer atlasId, /*@Valid*/ BookingRequest bookingRequest/*, BindingResult result*/){

//		if(result.hasErrors()){
//			System.out.println( "ERROR" );
//			return "view/showArea";
//		}

		bookingRequest.setCreateDate( LocalDateTime.now() );
		bookingRequest.setUserEmitter( getManagerFactory().getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()));
		Atlas atlas = getManagerFactory().getAtlasManager().findAtlasById( atlasId ) ;
		bookingRequest.setAtlas( atlas );

//		this.managerFactory.getBookingRequestManager().saveBookingRequest( bookingRequest );

		getManagerFactory().getAtlasManager().saveBookingRequest( atlasId, bookingRequest );

		model.addAttribute( "atlas" , atlas );
		model.addAttribute( "bookingRequest" , bookingRequest );

		System.out.println( "SaveBooking" );

		return "view/confirmationBookingDelivery";
	}


	@GetMapping( "/user/createArea" )
	public String  createArea(Model model){
		setConnectedUser( model );
		model.addAttribute( "area" , new Area() );
//		model.addAttribute( "parentEntityId" , new ParentEntityId() );
		model.addAttribute( "atlases" , getConnectedUser().get().getAtlases() );
		return "view/createArea";
	}


	@PostMapping( "/user/saveArea" )
	public String  saveArea( Model model, @Valid Area area, BindingResult result ){
		if(result.hasErrors()){
			return "view/createArea";
		}
		area.setCreateDate( LocalDateTime.now() );
		area.setAtlas( getManagerFactory().getAtlasManager().findAtlasById( area.getParentCreateId() ) );
//		area.setAtlas( getManagerFactory().getAtlasManager().findAtlasById( parentEntityId.getParentId() ) );
				//Warnging : vérifier si ok avec plusieurs utilisateurs connecté en mm temps
		getManagerFactory().getAreaManager().saveArea( area );
		return "view/createAreaConfirm";
	}


	@GetMapping( "/admin/deleteArea" )
	public String deleteArea(Integer id, int page, int size){
		getManagerFactory().getAreaManager().deleteArea( id );
		return "redirect:/public/showArea?page=" + page + "&size=" + size ;
	}


	@GetMapping( "/admin/editArea" )
	public String editArea( Model model, Integer id){
		Area area = getManagerFactory().getAreaManager().findById( id );
		model.addAttribute( "atlas", area );
		return "view/editArea";
	}

}
