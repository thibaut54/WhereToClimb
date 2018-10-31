package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;
import org.thibaut.wheretoclimb.model.entity.User;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class AtlasController extends AbstractController {

//	@Autowired
//	private ManagerFactory managerFactory;


	@GetMapping( "/public/showAtlas" )
	public String atlas( Model model,
	                     @RequestParam(name = "page", defaultValue = "0") int page,
	                     @RequestParam(name = "size", defaultValue = "5") int size,
	                     @RequestParam(name = "keyword", defaultValue = "") String keyword){

//		Page< Atlas > atlases = this.managerFactory.getAtlasManager().searchAtlas(page, size, keyword);
		Page< Atlas > atlases = getManagerFactory().getAtlasManager().searchAtlas(page, size, keyword);

		isUserAdmin( model );

		model.addAttribute( "connectedUser" , getManagerFactory().getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute( "atlases", atlases.getContent() );
		model.addAttribute( "pages", new int[atlases.getTotalPages()] );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );
		model.addAttribute( "keyword", keyword );

		return "view/showAtlas";
	}

//	@GetMapping("/user/bookAtlas")
//	public String btnBooking(Model model){
//		model.addAttribute( "bookingRequest" , new BookingRequest() );
//		return "view/showArea";
//	}


	@GetMapping("/public/layout")
	public String loyoutBtn(Model model){

		//Get the connected user
		Optional<User> userConnectedOpt = Optional.ofNullable( getManagerFactory().getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()) );

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
		getManagerFactory().getAtlasManager().deleteAtlas( id );
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
		atlas.setUser( getManagerFactory().getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()));
		getManagerFactory().getAtlasManager().saveAtlas( atlas );
		return "view/confirmation";
	}


	@GetMapping( "/admin/editAtlas" )
	public String editAtlas( Model model, Integer id){
		Atlas atlas = getManagerFactory().getAtlasManager().findById( id );
		model.addAttribute( "atlas", atlas );
		return "view/editAtlas";
	}


	@GetMapping( "/public/403" )
	public String accessDenied(){
		return"error/403";
	}


}
