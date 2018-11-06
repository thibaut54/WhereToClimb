package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class AreaController extends AbstractController {


	@GetMapping("/public/showArea")
	public String showArea( Model model, Integer atlasId,
	                        @RequestParam(name = "page", defaultValue = "0") int page,
	                        @RequestParam(name = "size", defaultValue = "5") int size/*,
	                        @RequestParam(name = "keyword", defaultValue = "") String keyword*/){

		Optional< Atlas > atlasOpt = Optional.ofNullable( getManagerFactory().getAtlasManager().findById( atlasId ) );

//		Page< Area > areas = getManagerFactory().getAreaManager().searchArea(page, size, keyword);

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
		Atlas atlas = getManagerFactory().getAtlasManager().findById( atlasId ) ;
		bookingRequest.setAtlas( atlas );

//		this.managerFactory.getBookingRequestManager().saveBookingRequest( bookingRequest );

		getManagerFactory().getAtlasManager().saveBookingRequest( atlasId, bookingRequest );

		model.addAttribute( "atlas" , atlas );
		model.addAttribute( "bookingRequest" , bookingRequest );

		System.out.println( "SaveBooking" );

		return "view/confirmationBookingDelivery";
	}
}
