package org.thibaut.wheretoclimb.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thibaut.wheretoclimb.consumer.repository.UserRepository;
//import org.thibaut.wheretoclimb.model.beans.CustomUserDetails;
import org.thibaut.wheretoclimb.model.beans.Role;
import org.thibaut.wheretoclimb.model.beans.User;
import org.thibaut.wheretoclimb.consumer.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
		if (username.trim().isEmpty()) {
			throw new UsernameNotFoundException("username is empty");
		}

		User user = userService.findByUsernameOrEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("User " + username + " not found");
		}

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getGrantedAuthorities(user));
	}

	private List< GrantedAuthority > getGrantedAuthorities( User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Role role = user.getRole();
		authorities.add(new SimpleGrantedAuthority(role.getRole()));
		return authorities;
	}

//    @Autowired
//    private UserRepository userRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//
//        Optional< User > optionalUsers = userRepository.findByUserName(userName);
//
//        optionalUsers
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
//        return optionalUsers
//                .map( CustomUserDetails::new).get();
//
//    }


}
