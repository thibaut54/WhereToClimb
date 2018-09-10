//package org.thibaut.wheretoclimb.consumer.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.thibaut.wheretoclimb.consumer.contract.UserService;
//import org.thibaut.wheretoclimb.model.beans.UserRole;
//import org.thibaut.wheretoclimb.model.beans.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
////import org.thibaut.wheretoclimb.model.beans.CustomUserDetails;
//
//
//@Service("customUserDetailsService")
//public class CustomUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private UserService userService;
//
//	@Override
//	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
//		if (username.trim().isEmpty()) {
//			throw new UsernameNotFoundException("username is empty");
//		}
//
//		User user = userService.findByUsernameOrEmail(username);
//
//		if (user == null) {
//			throw new UsernameNotFoundException("User " + username + " not found");
//		}
//
//		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getGrantedAuthorities(user));
//	}
//
//	private List< GrantedAuthority > getGrantedAuthorities( User user) {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		UserRole role = user.getRoleOfUsers();
//		authorities.add(new SimpleGrantedAuthority(role.getRoleOfUsers()));
//		return authorities;
//	}
//
////    @Autowired
////    private UserRepository userRepository;
////
////
////    @Override
////    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
////
////        Optional< User > optionalUsers = userRepository.findByUserName(userName);
////
////        optionalUsers
////                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
////        return optionalUsers
////                .map( CustomUserDetails::new).get();
////
////    }
//
//
//}
