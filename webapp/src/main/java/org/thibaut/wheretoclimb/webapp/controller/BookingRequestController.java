package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;
import org.thibaut.wheretoclimb.model.entity.User;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import org.thibaut.wheretoclimb.webapp.validation.pojo.BookingRequestForm;
import org.thibaut.wheretoclimb.webapp.validation.validator.BookingRequestValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BookingRequestController extends AbstractController {

	@Autowired
	private BookingRequestValidator bookingRequestValidator;


	// Set a form validator
	@InitBinder
	protected void initBinder( WebDataBinder dataBinder ) {
		// Form target
		Object target = dataBinder.getTarget( );
		if ( target == null ) {
			return;
		}

		if ( target.getClass( ) == BookingRequestForm.class ) {
			dataBinder.setValidator( bookingRequestValidator );
		}
	}


	@GetMapping("/user/createBookingRequest")
	public String createBookingRequest( Model model , Integer atlasId){
		model.addAttribute( "bookingRequestForm" , new BookingRequestForm() );
		model.addAttribute( "atlasId" , atlasId );

		return "view/createBookingRequest";
	}


//	@PostMapping( "/user/saveBookingRequest" )
	@PostMapping( "/user/saveBookingRequest/{atlasId}" )
	public String saveBookingRequest( Model model,
	                                  @PathVariable(name = "atlasId") Integer atlasId,
	                                  @ModelAttribute("bookingRequestForm") @Validated BookingRequestForm bookingRequestForm,
	                                  BindingResult result ) {

		if ( result.hasErrors( ) ) {
			return "view/createBookingRequest";
		}

		BookingRequest newBookingRequest = null;

		BookingRequest bookingRequest = GenericBuilder.of( BookingRequest::new )
				                                .with( BookingRequest::setStartDate, bookingRequestForm.getStartDate( ) )
				                                .with( BookingRequest::setEndDate, bookingRequestForm.getEndDate( ) )
				                                .with( BookingRequest::setUser, getManagerFactory( ).getUserManager( ).findByUserName( SecurityContextHolder.getContext( ).getAuthentication( ).getName( ) ) )
				                                .with( BookingRequest::setAtlas,getManagerFactory().getAtlasManager().findAtlasById( atlasId ) )
				                                .with( BookingRequest::setCreateDate, LocalDateTime.now( ) )
				                                .with( BookingRequest::setMessage, bookingRequestForm.getMessage( ) )
				                                .build( );

		try {
			newBookingRequest = getManagerFactory( ).getBookingRequestManager( ).createBookingRequest( bookingRequest );
		}
		// Other error!!
		catch ( Exception e ) {
			model.addAttribute( "errorMessage", "Error: " + e.getMessage( ) );
			return "view/createBookingRequest";
		}

		return "redirect:/public/showAtlas";
	}


	@GetMapping("/user/showBookingRequest")
	public String showBookingRequest( Model model ){

		Optional<User> userConnectedOpt = Optional.ofNullable(getManagerFactory().getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );

		if( userConnectedOpt.isPresent() ){
			model.addAttribute( "user", userConnectedOpt );
		}

		List< Atlas> atlases = userConnectedOpt.get().getAtlases();

		//Get bookingRequest received by the connected user
		List< BookingRequest > bookingRequestsReceived = new ArrayList<>();
		for ( Atlas atlas: atlases ) {
			bookingRequestsReceived.addAll( atlas.getBookingRequests() );
		}
		model.addAttribute( "bookingRequestsReceived" , bookingRequestsReceived );

		//Get bookingRequest sent by the connected user
		model.addAttribute( "bookingRequestsSent" , userConnectedOpt.get().getBookingRequests() );

		return "view/showBookingRequest";
	}


	@PostMapping( "/user/acceptBookingRequest/{id}" )
	public String acceptBookingRequest( @PathVariable(name = "id") Integer id) {
		BookingRequest bookingRequest = getManagerFactory().getBookingRequestManager().findBookingRequestById( id );
		bookingRequest.setAccepted( true );
		getManagerFactory( ).getBookingRequestManager( ).saveBookingRequest( bookingRequest );
		return "redirect:/user/showBookingRequest";
	}


	@PostMapping( "/user/refuseBookingRequest/{id}" )
	public String refuseBookingRequest( @PathVariable(name = "id") Integer id) {
		BookingRequest bookingRequest = getManagerFactory().getBookingRequestManager().findBookingRequestById( id );
		bookingRequest.setAccepted( false );
		getManagerFactory( ).getBookingRequestManager( ).saveBookingRequest( bookingRequest );
		return "redirect:/user/showBookingRequest";
	}


	@PostMapping( "/user/deleteBookingRequest/{id}" )
	public String deleteBookingRequest(@PathVariable(name = "id") Integer id){
		getManagerFactory().getBookingRequestManager().deleteBookingRequest( id );
		return "redirect:/user/showBookingRequest";
	}
}
