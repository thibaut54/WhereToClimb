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
import org.thibaut.wheretoclimb.model.entity.*;

import javax.servlet.http.HttpSession;
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

		Optional< Area > areaOpt = Optional.ofNullable( getManagerFactory().getAreaManager().findAreaById( areaId ) );

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
	public String  createCrag( Model model, HttpSession httpSession ){
//		putUserInHttpSession( httpSession );
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
		if( crag.getId()==null ){
			crag.setCreateDate( LocalDateTime.now() );
//			crag.setArea( getManagerFactory().getAreaManager().findAreaById( crag.getParentCreateId() ) );
		}
		else if ( crag.getId()!=null ){
			crag.setUpdateDate( LocalDateTime.now() );
		}
		getManagerFactory().getCragManager().saveCrag( crag );
		return "view/createCragConfirm";
	}


	@GetMapping( "/admin/editCrag" )
	public String editCrag( Model model, Integer id){
		Crag crag = getManagerFactory().getCragManager().findCragById( id );
		model.addAttribute( "crag", crag );
		return "view/createCrag";
	}

	@GetMapping( "/admin/deleteCrag" )
	public String deleteCrag(Integer id, int page, int size){
		getManagerFactory().getCragManager().deleteCrag( id );
		return "redirect:/public/showCrag?areaId=" + id + "?page=" + page + "&size=" + size;
	}


}
