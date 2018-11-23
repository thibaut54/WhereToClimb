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

	// Set a form validator
	@InitBinder
	protected void initBinderComment( WebDataBinder dataBinder ) {
		// Form target
		Object target = dataBinder.getTarget( );
		if ( target == null ) {
			return;
		}

		if ( target.getClass( ) == CommentForm.class ) {
			dataBinder.setValidator( commentValidator);
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

		//Create new Atlas
		if ( atlasForm.getId( ) == null ) {
			Atlas atlasToCreate = GenericBuilder.of( Atlas::new )
					                      .with( Atlas::setUser, getManagerFactory( ).getUserManager( ).findByUserName( SecurityContextHolder.getContext( ).getAuthentication( ).getName( ) ) )
					                      .with( Atlas::setName, atlasForm.getName( ) )
					                      .with( Atlas::setCountry, atlasForm.getCountry( ) )
					                      .with( Atlas::setRegion, atlasForm.getRegion( ) )
					                      .with( Atlas::setDepartment, atlasForm.getDepartment( ) )
					                      .with( Atlas::setAvailable, atlasForm.isAvailable( ) )
					                      .with( Atlas::setCreateDate, LocalDateTime.now() )
					                      .build();
			try {
				newAtlas = getManagerFactory( ).getAtlasManager( ).createAtlas( atlasToCreate );
			}
			// Other error!!
			catch ( Exception e ) {
				log.error( "error occuring create/update atlas: " + atlasForm.getId() + "/" + atlasForm.getName( ), e );
				model.addAttribute( "errorMessage", "Error: " + e.getMessage( ) );
				return "view/createAtlas";
			}

		}
		else if ( atlasForm.getId( ) != null ) {

			Atlas updatedAtlas = getManagerFactory().getAtlasManager().findAtlasById( atlasForm.getId() );
			updatedAtlas.setUpdateDate( LocalDateTime.now() );
			updatedAtlas.setName( atlasForm.getName() );
			updatedAtlas.setCountry( atlasForm.getCountry() );
			updatedAtlas.setRegion( atlasForm.getRegion() );
			updatedAtlas.setDepartment( atlasForm.getDepartment() );
			updatedAtlas.setAvailable( atlasForm.isAvailable() );

			try {
				newAtlas = getManagerFactory( ).getAtlasManager( ).createAtlas( updatedAtlas );
			}
			// Other error!!
			catch ( Exception e ) {
				log.error( "error occuring create/update atlas: " + atlasForm.getId() + "/" + atlasForm.getName( ), e );
				model.addAttribute( "errorMessage", "Error: " + e.getMessage( ) );
				return "view/createAtlas";
			}
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


	@GetMapping( "/user/commentElement" )
	public String commentElement( Model model, Integer elementId ) {
 		model.addAttribute( "commentForm", new CommentForm( ) );
		model.addAttribute( "elementId", elementId );
		return "view/createComment";
	}


	@PostMapping( "/user/saveComment/{elementId}" )
	public String saveComment( Model model,
                            @PathVariable(name = "elementId") Integer elementId,
                            @ModelAttribute("commentForm") @Valid CommentForm commentForm,
                            BindingResult result,
                            final RedirectAttributes redirectAttributes ) {
		if ( result.hasErrors( ) ) {
			return "view/createComment";
		}

		Comment newComment = null;

		//Create new Comment
		Element parentElement = getManagerFactory().getElementManager().findElementById( elementId ) ;

		Comment commentToCreate = GenericBuilder.of( Comment::new )
				                      .with( Comment::setUser, getManagerFactory( ).getUserManager( ).findByUserName( SecurityContextHolder.getContext( ).getAuthentication( ).getName( ) ) )
			                          .with( Comment::setElement, parentElement)
			                          .with( Comment::setTitle, commentForm.getTitle( ) )
			                          .with( Comment::setContent, commentForm.getContent( ) )
				                      .with( Comment::setCreateDate, LocalDateTime.now() )
				                      .build();
		try {
			newComment = getManagerFactory( ).getCommentManager().createComment( commentToCreate );
		}
		// Other error!!
		catch ( Exception e ) {
			log.error( "error occuring create comment: " + commentForm.getId() + "/" + commentForm.getTitle( ), e );
			model.addAttribute( "errorMessage", "Error: " + e.getMessage( ) );
			return "view/createComment";
		}

		redirectAttributes.addFlashAttribute( "flashComment", newComment);

		return "redirect:/user/createCommentSuccessful";
	}


	@GetMapping( "/user/createCommentSuccessful" )
	public String createCommentSuccessful( Model model ) {
		return "view/createCommentSuccessful";
	}


	@GetMapping( "/public/403" )
	public String accessDenied( ) {
		return "error/403";
	}




}
