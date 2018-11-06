package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class AdvancedSearchController extends AbstractController{

	@Autowired
	private ManagerFactory managerFactory;

	private static int currentPage = 1;
	private static int PAGE_SIZE = 5;

	@GetMapping("/public/advancedSearch")
	public String advancedSearch(  Model model,
	                       @RequestParam(name = "page") Optional<Integer> page,
	                       @RequestParam(name = "size") Optional<Integer> size,
	                       @RequestParam(name = "searchIn", defaultValue = "Atlas") String searchIn,
	                       @RequestParam(name = "name", defaultValue = "") String name,
	                       @RequestParam(name = "country", defaultValue = "") String country,
	                       @RequestParam(name = "region", defaultValue = "") String region,
	                       @RequestParam(name = "department", defaultValue = "") String department,
	                       @RequestParam(name = "city", defaultValue = "") String city){

		page.ifPresent(p -> currentPage = p);
		size.ifPresent(s -> PAGE_SIZE = s);

		model.addAttribute( "searchIn", searchIn );
		model.addAttribute( "searchHasResults" , false );


		Page< ? > result = getManagerFactory().getElementManager().searchElementByNameAndCountryAndRegionAndDepartmentAndCity( PageRequest.of(currentPage - 1, PAGE_SIZE ), searchIn, name, country, region, department, city );
		model.addAttribute( "searchHasResults" , !result.getContent().isEmpty() );
		model.addAttribute( "results", result.getContent() );
		model.addAttribute( "resultNumber", result.getNumber() );
		model.addAttribute( "pages", new int[result.getTotalPages()] );

		int totalPages = result.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
					                            .boxed()
					                            .collect( Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

//		switch ( searchIn ){
//			case "Atlas" :
//				Page< Atlas > atlases = getManagerFactory().getAtlasManager().searchAtlasByNameAndCountryAndRegionAndDepartment(page, size, searchIn, name, country, region, department, city);
//				model.addAttribute( "searchHasResults" , !atlases.getContent().isEmpty() );
//				model.addAttribute( "results", atlases.getContent() );
//				model.addAttribute( "pages", new int[atlases.getTotalPages()] );
//				break;
//			case "Area" :
//				Page< Area > areas = getManagerFactory().getAreaManager().searchAreaByNameAndCountryAndRegionAndDepartment(page, size, searchIn, name, country, region, department, city);
//				model.addAttribute( "searchHasResults" , !areas.getContent().isEmpty() );
//				model.addAttribute( "results", areas.getContent() );
//				model.addAttribute( "pages", new int[areas.getTotalPages()] );
//				break;
//			case "Crag" :
//				Page< Crag > crags = getManagerFactory().getCragManager().searchCragByNameAndCountryAndRegionAndDepartment(page, size, searchIn, name, country, region, department, city);
//				model.addAttribute( "searchHasResults" , !crags.getContent().isEmpty() );
//				model.addAttribute( "results", crags.getContent() );
//				model.addAttribute( "pages", new int[crags.getTotalPages()] );
//				break;
//			case "Route" :
//				Page< Route > routes = getManagerFactory().getRouteManager().searchRouteByNameAndCountryAndRegionAndDepartment(page, size, searchIn, name, country, region, department, city);
//				model.addAttribute( "searchHasResults" , !routes.getContent().isEmpty() );
//				model.addAttribute( "results", routes.getContent() );
//				model.addAttribute( "pages", new int[routes.getTotalPages()] );
//				break;
//		}


		isUserAdmin( model );

		model.addAttribute( "connectedUser" , this.managerFactory.getUserManager().findByUserName( SecurityContextHolder.getContext().getAuthentication().getName()));
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );
		model.addAttribute( "name", name );
		model.addAttribute( "country", country );
		model.addAttribute( "region", region );
		model.addAttribute( "department", department );
		model.addAttribute( "city", city);

		System.out.println( "Search in: " + searchIn );

		return "view/advancedSearch";
	}


}
