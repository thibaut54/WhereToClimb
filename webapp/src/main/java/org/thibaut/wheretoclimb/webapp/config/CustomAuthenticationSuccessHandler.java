package org.thibaut.wheretoclimb.webapp.config;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
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
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private ManagerFactory managerFactory;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    Authentication authentication)
			throws IOException, ServletException {


		HttpSession httpSession = request.getSession();

		if(authentication.getPrincipal() instanceof Principal ) {
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
		List< Atlas > atlasesFromConnectedUser = managerFactory.getAtlasManager().findAtlasesByUserId( user.getId() );
		List< Integer > atlasesIds = new ArrayList<>();
		for ( Atlas atlas : atlasesFromConnectedUser ) {
			atlasesIds.add( atlas.getId( ) );
		}

		httpsession.setAttribute( "atlasesFromConnectedUser" , atlasesFromConnectedUser );
		httpsession.setAttribute( "atlasesIds" , atlasesIds );

	}
}
