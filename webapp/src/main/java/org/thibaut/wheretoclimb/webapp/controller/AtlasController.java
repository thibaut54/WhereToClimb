package org.thibaut.wheretoclimb.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thibaut.wheretoclimb.model.entity.*;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import org.thibaut.wheretoclimb.webapp.validation.pojo.AtlasForm;
import org.thibaut.wheretoclimb.webapp.validation.pojo.CommentForm;
import org.thibaut.wheretoclimb.webapp.validation.validator.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Transactional
@Slf4j
public class AtlasController extends AbstractController {

	@Autowired
	AtlasValidator atlasValidator;

	@Autowired
	CommentValidator commentValidator;


	// Set a form validator
	@InitBinder
	protected void initBinder( WebDataBinder dataBinder ) {
		// Form target
		Object target = dataBinder.getTarget( );
		if ( target == null ) {
			return;
		}

		if ( target.getClass( ) == AtlasForm.class ) {
			dataBinder.setValidator( atlasValidator );
		}
	}


	@GetMapping( "/public/showAtlas" )
	public String showAtlas( Model model,
	                     HttpSession httpSession,
	                     @RequestParam( name = "page", defaultValue = "0" ) int page,
	                     @RequestParam( name = "size", defaultValue = "5" ) int size,
	                     @RequestParam( name = "keyword", defaultValue = "" ) String keyword ) {

		Page< Atlas > atlases = getManagerFactory( ).getAtlasManager( ).searchAtlas( page, size, keyword );

		putAtlasFromUserInModel( model ,httpSession );

		model.addAttribute( "atlases", atlases.getContent( ) );
		model.addAttribute( "pages", new int[atlases.getTotalPages( )] );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );
		model.addAttribute( "keyword", keyword );

		return "view/showAtlas";
	}


	@GetMapping("/user/showMyAtlases")
	public String myAtlas( Model model,
	                       HttpSession httpSession ) {

		putAtlasFromUserInModel( model, httpSession );

		return "view/showAtlas";
	}


	@GetMapping( "/user/createAtlas" )
	public String createAtlas( Model model ) {
		model.addAttribute( "atlasForm", new AtlasForm( ) );
		return "view/createAtlas";
	}


	@PostMapping( "/user/saveAtlas" )
	public String saveAtlas( Model model,
	                         @ModelAttribute("atlasForm") @Validated AtlasForm atlasForm,
	                         BindingResult result,
	                         final RedirectAttributes redirectAttributes ) {
		if ( result.hasErrors( ) ) {
			return "view/createAtlas";
		}

		Atlas newAtlas = null;
		Atlas atlasToSave = null;

		//Create new Atlas
		if ( atlasForm.getId( ) == null ) {
			atlasToSave = GenericBuilder.of( Atlas::new )
					                      .with( Atlas::setUser, getManagerFactory( ).getUserManager( ).findByUserName( SecurityContextHolder.getContext( ).getAuthentication( ).getName( ) ) )
					                      .with( Atlas::setName, atlasForm.getName( ) )
					                      .with( Atlas::setCountry, atlasForm.getCountry( ) )
					                      .with( Atlas::setRegion, atlasForm.getRegion( ) )
					                      .with( Atlas::setDepartment, atlasForm.getDepartment( ) )
					                      .with( Atlas::setAvailable, atlasForm.isAvailable( ) )
					                      .with( Atlas::setCreateDate, LocalDateTime.now() )
					                      .build();

		}
		else if ( atlasForm.getId( ) != null ) {

			atlasToSave = getManagerFactory().getAtlasManager().findAtlasById( atlasForm.getId() );
			atlasToSave.setUpdateDate( LocalDateTime.now() );
			atlasToSave.setName( atlasForm.getName() );
			atlasToSave.setCountry( atlasForm.getCountry() );
			atlasToSave.setRegion( atlasForm.getRegion() );
			atlasToSave.setDepartment( atlasForm.getDepartment() );
			atlasToSave.setAvailable( atlasForm.isAvailable() );

		}

		try {
			newAtlas = getManagerFactory( ).getAtlasManager( ).createAtlas( atlasToSave );
		}
		// Other error!!
		catch ( Exception e ) {
			log.error( "error occuring create/update atlas: " + atlasForm.getId() + "/" + atlasForm.getName( ), e );
			model.addAttribute( "errorMessage", "Error: " + e.getMessage( ) );
			return "view/createAtlas";
		}

		redirectAttributes.addFlashAttribute( "flashAtlas", newAtlas);

		return "redirect:/user/createAtlasSuccessful";
	}


	@GetMapping( "/user/createAtlasSuccessful" )
	public String createAtlasSuccessful( Model model ) {
		return "view/createAtlasConfirm";
	}


	@GetMapping( "/user/editAtlas" )
	public String editAtlas( Model model,
	                         Integer id) {
		AtlasForm atlasForm = new AtlasForm(getManagerFactory( ).getAtlasManager( ).findAtlasById( id ));
		model.addAttribute( "atlasForm", atlasForm );
		return "view/createAtlas";
	}


	@PostMapping( "/user/deleteAtlas/{id}" )
	public String deleteAtlas( @PathVariable(name = "id") Integer id) {
		getManagerFactory( ).getAtlasManager( ).deleteAtlas( id );
		return "redirect:/public/showAtlas";
	}


	@GetMapping( "/public/403" )
	public String accessDenied( ) {
		return "error/403";
	}




}
