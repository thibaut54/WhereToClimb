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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thibaut.wheretoclimb.model.entity.Area;
import org.thibaut.wheretoclimb.model.entity.Atlas;
import org.thibaut.wheretoclimb.model.entity.BookingRequest;
import org.thibaut.wheretoclimb.model.entity.User;
import org.thibaut.wheretoclimb.util.GenericBuilder;
import org.thibaut.wheretoclimb.webapp.validation.AreaForm;
import org.thibaut.wheretoclimb.webapp.validation.BookingRequestForm;
import org.thibaut.wheretoclimb.webapp.validation.BookingRequestValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class AreaController extends AbstractController {


	@GetMapping("/public/showArea")
	public String showArea( Model model, Integer atlasId, HttpSession httpSession,
	                        @RequestParam(name = "page", defaultValue = "0") int page,
	                        @RequestParam(name = "size", defaultValue = "5") int size){

//		putUserInHttpSession( httpSession );
		User userConnected = (User) httpSession.getAttribute( "user" );

		if (userConnected!=null){
			putAreasFromUserInModel( model, userConnected );
		}

		Optional< Atlas > atlasOpt = Optional.ofNullable( getManagerFactory().getAtlasManager().findAtlasById( atlasId ) );

		Page< Area > areas = new PageImpl<Area>( atlasOpt.get().getAreas(), PageRequest.of(page, size),atlasOpt.get().getAreas().size()) ;

		isUserAdmin( model );

		isCommented( model, atlasOpt.get() );

		atlasOpt.ifPresent( atlas -> model.addAttribute( "atlas", atlas ) );
		model.addAttribute( "areas" , areas );
		model.addAttribute( "pages", new int[areas.getTotalPages()] );
		model.addAttribute( "size", size );
		model.addAttribute( "currentPage", page );
//		model.addAttribute( "keyword", keyword );
		model.addAttribute( "bookingRequest" , new BookingRequest() );

		//		} else {
//			return "error/403";
//		}

		return "view/showArea";
	}



	@GetMapping( "/user/createArea" )
	public String  createArea( Model model, HttpSession httpSession ){
//		putUserInHttpSession( httpSession );
		model.addAttribute( "area" , new Area() );
		model.addAttribute( "atlases" , getConnectedUser().get().getAtlases() );
		return "view/createArea";
	}




	@PostMapping( "/user/saveArea" )
	public String  saveArea( Model model, @ModelAttribute("userForm") @Valid AreaForm areaForm, BindingResult result,
	                         final RedirectAttributes redirectAttributes){

		if(result.hasErrors()){
			return "view/createArea";
		}

		Area newArea = null;

		//If a user wants to create a new Area
		if ( areaForm.getId() == null) {
			Area areaToCreate = GenericBuilder.of( Area::new )
					            .with( Area::setCreateDate, LocalDateTime.now())
					            .with( Area::setAtlas, areaForm.getAtlas( ) )
					            .with( Area::setName, areaForm.getName( ) )
					            .with( Area::setNearestCity, areaForm.getNearestCity( ) )
					            .with( Area::setApproachDuration, areaForm.getApproachDuration( ) )
					            .with( Area::setAccess, areaForm.getAccess( ) )
					            .with( Area::setRockType, areaForm.getRockType( ) )
					            .with( Area::setGpsLatitude, areaForm.getGpsLatitude( ) )
					            .with( Area::setGpsLongitude, areaForm.getGpsLongitude( ) )
					            .with( Area::setAtltitude, areaForm.getAtltitude( ) )
					            .with( Area::setParkingAccess, areaForm.getParkingAccess( ) )
					            .with( Area::setParkingGpsLatitude, areaForm.getParkingGpsLatitude( ) )
					            .with( Area::setParkingGpsLongitude, areaForm.getParkingGpsLongitude( ) )
								.build();

			try {
				newArea = getManagerFactory().getAreaManager().createArea(areaToCreate);
			}
			// Other error!!
			catch (Exception e) {
				log.error( "error occuring create/update an Area: " + areaForm.getName(), e );
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
				return "view/createArea";
			}
			redirectAttributes.addFlashAttribute("flashArea", newArea);

		}
		//If a user wants to edit an existing area
		else if ( areaForm.getId() != null){
			Area areaToUpdate = getManagerFactory().getAreaManager().findAreaById( areaForm.getId() );
			areaToUpdate.setUpdateDate( LocalDateTime.now());
			areaToUpdate.setAtlas( areaForm.getAtlas(  ));
			areaToUpdate.setName( areaForm.getName(  ));
			areaToUpdate.setNearestCity( areaForm.getNearestCity( ));
			areaToUpdate.setApproachDuration( areaForm.getApproachDuration( ));
			areaToUpdate.setAccess( areaForm.getAccess(  ));
			areaToUpdate.setRockType( areaForm.getRockType(  ));
			areaToUpdate.setGpsLatitude( areaForm.getGpsLatitude(  ));
			areaToUpdate.setGpsLongitude( areaForm.getGpsLongitude(  ));
			areaToUpdate.setAtltitude( areaForm.getAtltitude(  ));
			areaToUpdate.setParkingAccess( areaForm.getParkingAccess(  ));
			areaToUpdate.setParkingGpsLatitude( areaForm.getParkingGpsLatitude(  ));
			areaToUpdate.setParkingGpsLongitude( areaForm.getParkingGpsLongitude(  ));

			try {
				newArea = getManagerFactory().getAreaManager().createArea(areaToUpdate);
			}
			// Other error!!
			catch (Exception e) {
				log.error( "error occuring create/update an Area: " + areaForm.getName(), e );
				model.addAttribute("errorMessage", "Error: " + e.getMessage());
				return "view/createArea";
			}
			redirectAttributes.addFlashAttribute("flashArea", newArea);

		}

		return "redirect:/public/registerSuccessful";
	}


	@GetMapping("/user/createAreaConfirm")
	public String createAreaConfirm(){
		return "view/createAreaConfirm";
	}


	@GetMapping( "/user/deleteArea" )
	public String deleteArea(Integer id, Integer parentId, int page, int size){
		getManagerFactory().getAreaManager().deleteArea( id );
		return "redirect:/public/showArea?atlasId=" + parentId + "&page=" + page + "&size=" + size ;
	}


	@GetMapping( "/user/editArea" )
	public String editArea( Model model, Integer id){
		Area area = getManagerFactory().getAreaManager().findAreaById( id );
		model.addAttribute( "area", area );
		return "view/createArea";
	}


	private void putAreasFromUserInModel( Model model, User userConnected ) {
		List< Atlas > atlasesFromConnectedUser = userConnected.getAtlases( );
		List<Area> areasFromConnectedUser = new ArrayList<>();

		for ( Atlas atlas: atlasesFromConnectedUser ) {
			areasFromConnectedUser.addAll( atlas.getAreas( ) );
		}

		List< Integer > areasId = new ArrayList<>( );
		model.addAttribute( "areas", areasFromConnectedUser );

		for ( Area area : areasFromConnectedUser ) {
			areasId.add( area.getId( ) );
		}
		model.addAttribute( "areasId", areasId );
	}
}
