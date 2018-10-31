package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.User;

import java.util.Optional;

@Controller
public class AdvancedSearchController extends AbstractController{

	@Autowired
	private ManagerFactory managerFactory;


	@GetMapping("/public/advancedSearch")
	public String search(  Model model,
	                       @RequestParam(name = "page", defaultValue = "0") int page,
	                       @RequestParam(name = "size", defaultValue = "5") int size,
	                       @RequestParam(name = "name", defaultValue = "") String name,
	                       @RequestParam(name = "country", defaultValue = "") String country,
	                       @RequestParam(name = "region", defaultValue = "") String region,
	                       @RequestParam(name = "department", defaultValue = "") String department,
	                       @RequestParam(name = "searchIn", defaultValue = "") String searchIn){

		model.addAttribute( "searchIn", searchIn );
		model.addAttribute( "searchHasResults" , false );

//		Page< Atlas > atlases = this.managerFactory.getAtlasManager().searchAtlasByNameAndCountryAndRegionAndDepartment(page, size, name, country, region, department);
//		model.addAttribute( "searchHasResults" , !atlases.getContent().isEmpty() );
//		model.addAttribute( "atlases", atlases.getContent() );
//		model.addAttribute( "pages", new int[atlases.getTotalPages()] );

		switch ( searchIn ){
			case "Atlas" :
				Page< Atlas > atlases = getManagerFactory().getAtlasManager().searchAtlasByNameAndCountryAndRegionAndDepartment(page, size, name, country, region, department);
				model.addAttribute( "searchHasResults" , !atlases.getContent().isEmpty() );
				model.addAttribute( "results", atlases.getContent() );
				model.addAttribute( "pages", new int[atlases.getTotalPages()] );
				break;
			case "Area" :
				Page< Area > areas = getManagerFactory().getAreaManager().searchAreaByNameAndCountryAndRegionAndDepartment(page, size, name, country, region, department);
				model.addAttribute( "searchHasResults" , !areas.getContent().isEmpty() );
				model.addAttribute( "results", areas.getContent() );
				model.addAttribute( "pages", new int[areas.getTotalPages()] );
				break;
		}


		isUserAdmin( model );

		model.addAttribute( "connectedUser" , this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );
		model.addAttribute( "name", name );
		model.addAttribute( "country", country );
		model.addAttribute( "region", region );
		model.addAttribute( "department", department );

		System.out.println( "Search in: " + searchIn );

		return "view/advancedSearch";
	}


}
