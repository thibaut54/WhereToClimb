package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.Atlas;

import javax.validation.Valid;
import java.time.LocalDateTime;

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

		model.addAttribute( "atlases", atlases.getContent() );

		int[] pages = new int[atlases.getTotalPages()];
		model.addAttribute( "pages", pages );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );
		model.addAttribute( "keyword", keyword );

		return "view/atlas";
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


}
