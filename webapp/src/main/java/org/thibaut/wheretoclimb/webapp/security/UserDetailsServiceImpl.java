package org.thibaut.wheretoclimb.webapp.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.entity.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ManagerFactory managerFactory;


	/**
	 * The authentication method uses the user email, since it is easier to remember for most users
	 * @param input
	 * @return a UserDetails object
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername( String input ) throws UsernameNotFoundException {

		User user = new User();

		if( input.contains( "@" )){
			user =  this.managerFactory.getUserManager().findByEmail( input );
		}
		else {
			user =  this.managerFactory.getUserManager().findByUserName( input );
		}


		if (user == null) {
			System.out.println( "User email not found! " + input );
			throw new UsernameNotFoundException( "User with email " + input + " was not found in the database" );
		}

		System.out.println("Found User: " + user);

		// [ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = this.managerFactory.getRoleManager().findRoleByUserName(user.getUserName());

		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				// ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}

		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUserName(), //
				user.getPassword(), grantList);

		return userDetails;
	}

}