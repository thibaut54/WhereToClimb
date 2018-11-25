package org.thibaut.wheretoclimb.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thibaut.wheretoclimb.model.entity.Comment;
import org.thibaut.wheretoclimb.model.entity.Element;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import org.thibaut.wheretoclimb.webapp.validation.pojo.AreaForm;
import org.thibaut.wheretoclimb.webapp.validation.pojo.CommentForm;
import org.thibaut.wheretoclimb.webapp.validation.validator.CommentValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Component
@Slf4j
public class CommentController extends AbstractController {

	@Autowired
	private CommentValidator commentValidator;

	// Set a form validator
	@InitBinder
	protected void initBinderComment( WebDataBinder dataBinder ) {
		// Form target
		Object target = dataBinder.getTarget( );
		if ( target == null ) {
			return;
		}

		if ( target.getClass( ) == CommentForm.class ) {
			dataBinder.setValidator( commentValidator );
		}
	}


	@GetMapping( "/user/commentElement" )
	public String commentElement( Model model, Integer elementId ) {
		model.addAttribute( "commentForm", new CommentForm( ) );
		model.addAttribute( "elementId", elementId );
		return "view/createComment";
	}


	@PostMapping( "/user/saveComment/{elementId}" )
	public String saveComment( Model model,
	                           HttpSession httpSession,
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
				                          .with( Comment::setUser, getManagerFactory( ).getUserManager( ).findById( ( Integer ) httpSession.getAttribute( "connectedUserId" ) ) )
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


	@PostMapping( "/admin/deleteComment/{id}" )
	public String deleteComment(@PathVariable(name = "id") Integer id ){
		getManagerFactory().getCommentManager().deleteComment( id );
		return "redirect:/public/showAtlas";
	}
}
