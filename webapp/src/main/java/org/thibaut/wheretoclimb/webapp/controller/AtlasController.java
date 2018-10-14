package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;
import org.thibaut.wheretoclimb.model.entity.User;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class AtlasController {

	@Autowired
	private ManagerFactory managerFactory;


	@GetMapping( "/public/showAtlas" )
	public String atlas( Model model,
	                     @RequestParam(name = "page", defaultValue = "0") int page,
	                     @RequestParam(name = "size", defaultValue = "5") int size,
	                     @RequestParam(name = "keyword", defaultValue = "") String keyword){

		Page< Atlas > atlases = this.managerFactory.getAtlasManager().searchAtlas(page, size, keyword);

//		//Get the connected user
//		Optional<User> userConnectedOpt = Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );
//
//		boolean isConnected = false;
//
//		//If there is a connected user, put a boolean at true in the model
//		if( userConnectedOpt.isPresent() ){
//
//			User userConnected = userConnectedOpt.get();
//
//			isConnected = true;
//			final boolean[] isAdmin = { false };
//
//			userConnected.getRoles().forEach( role -> isAdmin[0] = role.getRole( ).equals( "ROLE_ADMIN" ) );
//
//			model.addAttribute( "userIsAdmin" , isAdmin[0] );
//		}

		isUserAdmin( model );

		model.addAttribute( "connectedUser" , this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()));
//		model.addAttribute( "isConnected", isConnected );
		model.addAttribute( "atlases", atlases.getContent() );

		int[] pages = new int[atlases.getTotalPages()];
		model.addAttribute( "pages", pages );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );
		model.addAttribute( "keyword", keyword );

		return "view/showAtlas";
	}


	@GetMapping("/public/showAtlasDetail")
	public String showAtlasDetail(Model model, Integer atlasId, String atlasName,
	                              @RequestParam(name = "page", defaultValue = "0") int page,
	                              @RequestParam(name = "size", defaultValue = "5") int size,
	                              @RequestParam(name = "keyword", defaultValue = "") String keyword){

		System.out.println( "Run showAtlasDetail method " );

		Optional<Atlas> atlasOpt = Optional.ofNullable( this.managerFactory.getAtlasManager().findById( atlasId ) );
//		Atlas atlas = this.managerFactory.getAtlasManager().findById( atlasId ) ;

		Page< Area > areas = this.managerFactory.getAreaManager().searchArea(page, size, keyword);

		isUserAdmin( model );

//		Optional<User> userConnectedOpt = Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );
//
//		boolean isConnected = false;
//
//		//If there is a connected user, put a boolean at true in the model
//		if( userConnectedOpt.isPresent() ){
//
//			User userConnected = userConnectedOpt.get();
//
//			isConnected = true;
//			final boolean[] isAdmin = { false };
//
//			userConnected.getRoles().forEach( role -> isAdmin[0] = role.getRole( ).equals( "ROLE_ADMIN" ) );
//
//			model.addAttribute( "userIsAdmin" , isAdmin[0] );
//		}
		isCommented( model, atlasOpt.get() );

		atlasOpt.ifPresent( atlas -> model.addAttribute( "atlas", atlas ) );
//			model.addAttribute( "atlas" , atlas );
			model.addAttribute( "areas" , areas );
			model.addAttribute( "atlasName", atlasName );
//			isUserAdmin( model );
			int[] pages = new int[areas.getTotalPages()];
			model.addAttribute( "pages", pages );
			model.addAttribute( "size", size );
			model.addAttribute( "currentPage", page );
			model.addAttribute( "keyword", keyword );
			model.addAttribute( "bookingRequest" , new BookingRequest() );

			//		} else {
//			return "error/403";
//		}

		return "view/showAtlasDetail";
	}


//	@GetMapping("/user/bookAtlas")
//	public String btnBooking(Model model){
//		model.addAttribute( "bookingRequest" , new BookingRequest() );
//		return "view/showAtlasDetail";
//	}


	@PostMapping("/user/saveBookingRequest")
	public String saveBookingRequest(Model model, Integer atlasId, /*@Valid*/ BookingRequest bookingRequest/*, BindingResult result*/){

//		if(result.hasErrors()){
//			System.out.println( "ERROR" );
//			return "view/showAtlasDetail";
//		}

		bookingRequest.setCreateDate( LocalDateTime.now() );
		bookingRequest.setUserEmitter( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()));
		bookingRequest.setAtlas( this.managerFactory.getAtlasManager().findById( atlasId ) );

		this.managerFactory.getBookingRequestManager().saveBookingRequest( bookingRequest );

		System.out.println( "SaveBooking" );

		return "view/confirmationBookingDelivery";
	}


	@GetMapping("/public/layout")
	public String loyoutBtn(Model model){

		//Get the connected user
		Optional<User> userConnectedOpt = Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );

		boolean isConnected = false;

		//If there is a connected user, put a boolean at true in the model
		if( userConnectedOpt.isPresent() ){

			User userConnected = userConnectedOpt.get();

			isConnected = true;

		}

		model.addAttribute( "isConnected", isConnected );

		return "layout/layout";
	}


	@GetMapping( "/admin/deleteAtlas" )
	public String deleteAtlas(Integer id, String keyword, int page, int size){
		this.managerFactory.getAtlasManager().deleteAtlas( id );
		return "redirect:/public/showAtlas?page=" + page + "&size=" + size + "&keyword=" + keyword;
	}


	@GetMapping( "/user/createAtlas" )
	public String  createAtlas(Model model){
		model.addAttribute( "atlas", new Atlas() );
		return "view/createAtlas";
	}


	@PostMapping( "/user/saveAtlas" )
	public String  saveAtlas( Model model, @Valid Atlas atlas, BindingResult result ){
		if(result.hasErrors()){
			return "view/createAtlas";
		}
		atlas.setCreateDate( LocalDateTime.now() );
		//Warnging : vérifier si ok avec plusieurs utilisateurs connecté en mm temps
		atlas.setUser( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()));
		this.managerFactory.getAtlasManager().saveAtlas( atlas );
		return "view/confirmation";
	}


	@GetMapping( "/admin/editAtlas" )
	public String editAtlas( Model model, Integer id){
		Atlas atlas = this.managerFactory.getAtlasManager().findById( id );
		model.addAttribute( "atlas", atlas );
		return "view/editAtlas";
	}


	@GetMapping( "/public/403" )
	public String accessDenied(){
		return"error/403";
	}



	private void isUserAdmin( Model model ){
		//Get the connected user
		Optional<User> userConnectedOpt = Optional.ofNullable( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );

		boolean isConnected = false;

		//If there is a connected user, put a boolean at true in the model
		if( userConnectedOpt.isPresent() ){

			User userConnected = userConnectedOpt.get();

			isConnected = true;
			final boolean[] isAdmin = { false };

			userConnected.getRoles().forEach( role -> isAdmin[0] = role.getRole( ).equals( "ROLE_ADMIN" ) );

			model.addAttribute( "userIsAdmin" , isAdmin[0] );
			model.addAttribute( "isConnected", isConnected );

		}
	}


	private void isCommented( Model model, Atlas atlas ){

		boolean atlasIsCommented = false;

//		System.out.println( atlas.getComments().get( 0 ).getTitle());

		if( ! atlas.getComments().isEmpty() ){
			atlasIsCommented = true;
			model.addAttribute( "atlasIsCommented", atlasIsCommented );
		}

		System.out.println( "The atlas " + atlas.getName() + " has been commented: " + atlasIsCommented );

	}

}
