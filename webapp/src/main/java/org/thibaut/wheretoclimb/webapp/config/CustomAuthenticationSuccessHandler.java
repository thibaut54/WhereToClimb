package org.thibaut.wheretoclimb.webapp.config;

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
import org.thibaut.wheretoclimb.webapp.controller.AbstractController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private ManagerFactory managerFactory;
//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
//	                                    HttpServletResponse httpServletResponse,
//	                                    Authentication authentication)
//			throws IOException, ServletException {
//		//do some logic here if you want something to be done whenever
//		//the user successfully logs in.
//
//		HttpSession session = httpServletRequest.getSession();
//		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		session.setAttribute("username", authUser.getUsername());
//		session.setAttribute("userConnected", this.managerFactory.getUserManager().findByUserName( authUser.getUsername() ) );
//		session.setAttribute("authorities", authentication.getAuthorities());
//
//		//set our response to OK status
//		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
//
//		//since we have created our custom success handler, its up to us to where
//		//we will redirect the user after successfully login
//		httpServletResponse.sendRedirect("/public/showAtlas" );
//	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    Authentication authentication)
			throws IOException, ServletException {

		String userName = "";
		HttpSession session = request.getSession();
		Collection< GrantedAuthority > authorities = null;
		if(authentication.getPrincipal() instanceof Principal ) {
			userName = ((Principal)authentication.getPrincipal()).getName();
//			session.setAttribute("userName",userName);
			session.setAttribute("role", "none");
		}else {
//			userName = ((User)authentication.getPrincipal()).getUsername();
			User userSpringSecu = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			session.setAttribute("userSpringSecu", userSpringSecu );
			session.setAttribute("userName", userSpringSecu.getUsername());
			session.setAttribute("role", String.valueOf( userSpringSecu.getAuthorities()));
			session.setAttribute( "userConnected" , managerFactory.getUserManager().findByUserName( userSpringSecu.getUsername() ) );

		}
//		session.setAttribute("userId", userName);
		response.sendRedirect("/public/showAtlas" );
	}
}
