package org.thibaut.wheretoclimb.webapp.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.Crag;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CragController extends AbstractController {

	@GetMapping("/public/showCrag")
	public String showCrag( Model model, Integer areaId,
	                        @RequestParam(name = "page", defaultValue = "0") int page,
	                        @RequestParam(name = "size", defaultValue = "5") int size){

		Optional< Area > areaOpt = Optional.ofNullable( getManagerFactory().getAreaManager().findById( areaId ) );

		Page< Crag > crags = new PageImpl<Crag>( areaOpt.get().getCrags(), PageRequest.of(page, size), areaOpt.get().getCrags().size()) ;

		isUserAdmin( model );

		isCommented( model, areaOpt.get() );

		areaOpt.ifPresent( atlas -> model.addAttribute( "area", areaOpt.get() ) );
		model.addAttribute( "crags" , crags );
		model.addAttribute( "pages", new int[crags.getTotalPages()] );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );

		return "view/showCrag";
	}


	@GetMapping( "/user/createCrag" )
	public String  createCrag(Model model){
		setConnectedUser( model );
		model.addAttribute( "crag" , new Crag() );
		List< Atlas > atlases = getConnectedUser().get().getAtlases();
		List<Area> areas = new ArrayList<>();
		for ( Atlas atlas: atlases ) {
			areas.addAll( atlas.getAreas( ) );
		}
		model.addAttribute( "areas" , areas );
		return "view/createCrag";
	}


	@PostMapping( "/user/saveCrag" )
	public String  saveCrag( Model model, @Valid Crag crag, BindingResult result ){
		if(result.hasErrors()){
			return "view/createCrag";
		}
		crag.setCreateDate( LocalDateTime.now() );
		crag.setArea( getManagerFactory().getAreaManager().findById( crag.getParentCreateId() ) );
		//Warnging : vérifier si ok avec plusieurs utilisateurs connecté en mm temps
		getManagerFactory().getCragManager().saveCrag( crag );
		return "view/createCragConfirm";
	}
}
