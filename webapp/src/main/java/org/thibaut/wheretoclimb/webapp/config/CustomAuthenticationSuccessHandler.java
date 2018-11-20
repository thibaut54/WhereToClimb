package org.thibaut.wheretoclimb.webapp.config;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.business.impl.ManagerFactoryImpl;
import org.thibaut.wheretoclimb.consumer.contract.DaoFactory;
import org.thibaut.wheretoclimb.consumer.repository.UserRepository;
import org.thibaut.wheretoclimb.model.entity.*;
import org.thibaut.wheretoclimb.webapp.controller.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private ManagerFactory managerFactory;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    Authentication authentication)
			throws IOException, ServletException {

//		String userName = "";
		HttpSession httpSession = request.getSession();
//		Collection< GrantedAuthority > authorities = null;

		if(authentication.getPrincipal() instanceof Principal ) {
//			userName = ((Principal)authentication.getPrincipal()).getName();
			httpSession.setAttribute("role", "none");
		}
		else {
			User userSpringSecu = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			httpSession.setAttribute("role", String.valueOf( userSpringSecu.getAuthorities()));
			org.thibaut.wheretoclimb.model.entity.User connectedUser = managerFactory.getUserManager().findByUserName( userSpringSecu.getUsername() );
			httpSession.setAttribute( "connectedUserId" , connectedUser.getId() );
			httpSession.setAttribute( "connectedUser" , connectedUser );
			putElementFromUserInSession(httpSession);
		}
		response.sendRedirect("/public/showAtlas" );
	}

	private void putElementFromUserInSession( HttpSession httpsession ) {
		User userSpringSecu = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		org.thibaut.wheretoclimb.model.entity.User user = managerFactory.getUserManager().findByUserName(userSpringSecu.getUsername());
//		Hibernate.initialize(user);
		List< Atlas > atlasesFromConnectedUser = managerFactory.getAtlasManager().findAtlasesByUserId( user.getId() );
		List< Integer > atlasesIds = new ArrayList<>();
		for ( Atlas atlas : atlasesFromConnectedUser ) {
			atlasesIds.add( atlas.getId( ) );
		}
//		List< Area > areasFromConnectedUser = new ArrayList<>();
//		List< Crag > cragFromConnectedUser = new ArrayList<>();
//		List< Route > routesFromConnectedUser = new ArrayList<>();
//		List< Pitch > pitchesFromConnectedUser = new ArrayList<>();
//		List< Integer > atlasesIds = new ArrayList<>( );
//		List< Integer > areasIds = new ArrayList<>( );
//		List< Integer > cragsIds = new ArrayList<>( );
//		List< Integer > routesIds = new ArrayList<>( );
//		List< Integer > pitchesIds = new ArrayList<>( );
//
//		for ( Atlas atlas: atlasesFromConnectedUser ) {
//			areasFromConnectedUser.addAll( atlas.getAreas( ) );
//		}
//		for ( Area area: areasFromConnectedUser) {
//			cragFromConnectedUser.addAll( area.getCrags( ) );
//		}
//		for ( Crag crag: cragFromConnectedUser) {
//			routesFromConnectedUser.addAll( crag.getRoutes( ) );
//		}
//		for ( Route route: routesFromConnectedUser) {
//			pitchesFromConnectedUser.addAll( route.getPitches( ) );
//		}
//
//		for ( Atlas atlas : atlasesFromConnectedUser ) {
//			atlasesIds.add( atlas.getId( ) );
//		}
//		for ( Area area: areasFromConnectedUser ) {
//			areasIds.add( area.getId( ) );
//		}
//		for ( Crag crag : cragFromConnectedUser) {
//			cragsIds.add( crag.getId( ) );
//		}
//		for ( Route route : routesFromConnectedUser) {
//			routesIds.add( route.getId( ) );
//		}
//		for ( Pitch pitch: pitchesFromConnectedUser) {
//			pitchesIds.add( pitch.getId( ) );
//		}

		httpsession.setAttribute( "atlasesFromConnectedUser" , atlasesFromConnectedUser );
//		httpsession.setAttribute( "areasFromConnectedUser" , areasFromConnectedUser );
//		httpsession.setAttribute( "cragFromConnectedUser" , cragFromConnectedUser );
//		httpsession.setAttribute( "routesFromConnectedUser" , atlasesFromConnectedUser );
//		httpsession.setAttribute( "atlasesFromConnectedUser" , pitchesFromConnectedUser );
		httpsession.setAttribute( "atlasesIds" , atlasesIds );
//		httpsession.setAttribute( "areasIds" , areasIds);
//		httpsession.setAttribute( "cragsIds" , cragsIds);
//		httpsession.setAttribute( "routesIds" , routesIds);
//		httpsession.setAttribute( "pitchesIds" , pitchesIds);

	}
}
