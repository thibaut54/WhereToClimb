package org.thibaut.wheretoclimb.webapp.security;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.model.beans.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ManagerFactory managerFactory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user =  this.managerFactory.getUserManager().findByUserName( username );

		if (user == null) {
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
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