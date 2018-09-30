package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.beans.Atlas;
import org.thibaut.wheretoclimb.model.beans.User;
import org.thibaut.wheretoclimb.webapp.validation.DateFormatterCustom;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AtlasController {

	@Autowired
	private ManagerFactory managerFactory;

//	@Autowired
//	private User user;


	@GetMapping("/atlas")
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

		return "view/atlasPage";
	}

	@GetMapping("/delete")
	public String delete(Integer id, String keyword, int page, int size){
		this.managerFactory.getAtlasManager().deleteAtlas( id );
		return "redirect:/atlas?page=" + page + "&size=" + size + "&keyword=" + keyword;
	}

	@GetMapping("/createAtlas")
	public String  createAtlas(Model model){
		model.addAttribute( "atlas", new Atlas() );
		return "view/createAtlasPage";
	}

	@PostMapping("/save")
	public String  saveAtlas( Model model, @Valid Atlas atlas, BindingResult result ){
		if(result.hasErrors()){
			return "view/createAtlasPage";
		}
		atlas.setUpdateDate( null );
		atlas.setComments( null );
		atlas.setCreateDate( DateFormatterCustom.getCurrentDate() );
		atlas.setUser( this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()));
		this.managerFactory.getAtlasManager().saveAtlas( atlas );
		return "view/confirmationPage";
	}

}
