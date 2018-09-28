package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.beans.Atlas;

import java.util.List;

@Controller
public class AtlasController {

	@Autowired
	private ManagerFactory managerFactory;


	@GetMapping("/atlas")
	public String atlas( Model model,
	                     @RequestParam(name = "page", defaultValue = "0") int page,
	                     @RequestParam(name = "size", defaultValue = "5") int size,
	                     @RequestParam(name = "keyword", defaultValue = "") String keyword){

		Page< Atlas > atlases = this.managerFactory.getAtlasManager().searchAtlas(page, size, keyword);

//		model.addAttribute( "atlases", atlases );
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

	@GetMapping("createAtlas")
	public String  createAtlas(){
		return "createAtlasPage";
	}

}
