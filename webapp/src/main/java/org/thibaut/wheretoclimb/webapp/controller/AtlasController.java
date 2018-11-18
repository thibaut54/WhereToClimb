package org.thibaut.wheretoclimb.webapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thibaut.wheretoclimb.model.entity.*;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import org.thibaut.wheretoclimb.webapp.validation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class AtlasController extends AbstractController {

	@Autowired
	AtlasValidator atlasValidator;


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
	public String atlas( Model model,
	                     HttpSession httpSession,
	                     @RequestParam( name = "page", defaultValue = "0" ) int page,
	                     @RequestParam( name = "size", defaultValue = "5" ) int size,
	                     @RequestParam( name = "keyword", defaultValue = "" ) String keyword ) {

		putAtlasesFromUserInModel( model, httpSession );

		isUserAdmin( model );

		Page< Atlas > atlases = getManagerFactory( ).getAtlasManager( ).searchAtlas( page, size, keyword );

		isUserAdmin( model );

		model.addAttribute( "atlases", atlases.getContent( ) );
		model.addAttribute( "pages", new int[atlases.getTotalPages( )] );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );
		model.addAttribute( "keyword", keyword );

		return "view/showAtlas";
	}



	@GetMapping("/user/showMyAtlases")
	public String myAtlas( Model model,
	                       HttpSession httpSession,
	                       final RedirectAttributes redirectAttributes) {

//		putUserInHttpSession( httpSession );
//		putElementFromUserInSession( ( User ) httpSession.getAttribute( "user" ), httpSession );

//		User userConnected = (User) httpSession.getAttribute( "user" );

		putAtlasesFromUserInModel( model, httpSession );

		isUserAdmin( model );

//		redirectAttributes.addAttribute( userConnected );

		return "view/showAtlas";
//		return "redirect:/public/showAtlas";
	}


	@GetMapping( "/user/createAtlas" )
	public String createAtlas( Model model /*, HttpSession httpSession*/ ) {
//		putUserInHttpSession( httpSession );
//		model.addAttribute( "atlas", new Atlas( ) );
		model.addAttribute( "atlasForm", new AtlasForm( ) );

		return "view/createAtlas";
	}


	@PostMapping( "/user/saveAtlas" )
	public String saveAtlas( Model model,
	                         @ModelAttribute("atlasForm") @Valid AtlasForm atlasForm,
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

//		if ( atlas.getId( ) == null ) {
//			atlas.setCreateDate( LocalDateTime.now( ) );
//			//Warnging : vérifier si ok avec plusieurs utilisateurs connecté en mm temps
//			atlas.setUser( getManagerFactory( ).getUserManager( ).findByUserName( SecurityContextHolder.getContext( ).getAuthentication( ).getName( ) ) );
//		}
//		//Edit existing Atlas
//		else if ( atlas.getId( ) != null ) {
//			atlas.setUpdateDate( LocalDateTime.now( ) );
//		}
//		getManagerFactory( ).getAtlasManager( ).saveAtlas( atlas );

		return "redirect:/user/createAtlasSuccessful";
	}


	@GetMapping( "/user/createAtlasSuccessful" )
	public String createAtlasSuccessful( Model model ) {
		return "view/createAtlasConfirm";
	}


	@GetMapping( "/user/editAtlas" )
	public String editAtlas( Model model,
	                         Integer id/*,
	                          final RedirectAttributes redirectAttributes*/ ) {
		AtlasForm atlasForm = new AtlasForm(getManagerFactory( ).getAtlasManager( ).findAtlasById( id ));
		model.addAttribute( "atlasForm", atlasForm );
//		redirectAttributes.addAttribute( "atlasToEdit", atlas );
		return "view/createAtlas";
//		return "redirect:/user/createAtlas";

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


	private void putAtlasesFromUserInModel( Model model,
	                                        HttpSession httpSession) {

		if (httpSession.getAttribute( "role" )!=null
				    && (httpSession.getAttribute( "role" ).toString().contains( "USER")
						        || httpSession.getAttribute( "role" ).toString().contains( "ADMIN"))){

			List< Atlas > atlasesFromConnectedUser = getConnectedUser(httpSession).getAtlases();
			List< Integer > atlasesIds = new ArrayList<>( );

			//put atlas from user in model
			model.addAttribute( "atlases", atlasesFromConnectedUser );

			for ( Atlas atlas : atlasesFromConnectedUser ) {
				atlasesIds.add( atlas.getId( ) );
			}
			model.addAttribute( "atlasesIds", atlasesIds );
		}
	}


	/*private void putElementFromUserInSession( User userConnected, HttpSession httpsession ) {
		List< Atlas > atlasesFromConnectedUser = userConnected.getAtlases( );
		List< Area > areasFromConnectedUser = new ArrayList<>();
		List< Crag > cragFromConnectedUser = new ArrayList<>();
		List<Route> routesFromConnectedUser = new ArrayList<>();
		List<Pitch> pitchesFromConnectedUser = new ArrayList<>();
		List< Integer > atlasesIds = new ArrayList<>( );
		List< Integer > areasIds = new ArrayList<>( );
		List< Integer > cragsIds = new ArrayList<>( );
		List< Integer > routesIds = new ArrayList<>( );
		List< Integer > pitchesIds = new ArrayList<>( );

		for ( Atlas atlas: atlasesFromConnectedUser ) {
			areasFromConnectedUser.addAll( atlas.getAreas( ) );
		}
		for ( Area area: areasFromConnectedUser) {
			cragFromConnectedUser.addAll( area.getCrags( ) );
		}
		for ( Crag crag: cragFromConnectedUser) {
			routesFromConnectedUser.addAll( crag.getRoutes( ) );
		}
		for ( Route route: routesFromConnectedUser) {
			pitchesFromConnectedUser.addAll( route.getPitches( ) );
		}

		for ( Atlas atlas : atlasesFromConnectedUser ) {
			atlasesIds.add( atlas.getId( ) );
		}
		for ( Area area: areasFromConnectedUser ) {
			areasIds.add( area.getId( ) );
		}
		for ( Crag crag : cragFromConnectedUser) {
			cragsIds.add( crag.getId( ) );
		}
		for ( Route route : routesFromConnectedUser) {
			routesIds.add( route.getId( ) );
		}
		for ( Pitch pitch: pitchesFromConnectedUser) {
			pitchesIds.add( pitch.getId( ) );
		}

		httpsession.setAttribute( "atlasesFromConnectedUser" , atlasesFromConnectedUser );
		httpsession.setAttribute( "areasFromConnectedUser" , areasFromConnectedUser );
		httpsession.setAttribute( "cragFromConnectedUser" , cragFromConnectedUser );
		httpsession.setAttribute( "routesFromConnectedUser" , atlasesFromConnectedUser );
		httpsession.setAttribute( "atlasesFromConnectedUser" , pitchesFromConnectedUser );
		httpsession.setAttribute( "atlasesIds" , atlasesIds );
		httpsession.setAttribute( "areasIds" , areasIds);
		httpsession.setAttribute( "cragsIds" , cragsIds);
		httpsession.setAttribute( "routesIds" , routesIds);
		httpsession.setAttribute( "pitchesIds" , pitchesIds);

	}*/

}
