//package org.thibaut.wheretoclimb.webapp.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.thibaut.wheretoclimb.consumer.repository.UserRepository;
//import org.thibaut.wheretoclimb.consumer.repository.UserRoleRepository;
//import org.thibaut.wheretoclimb.model.beans.User;
//import org.thibaut.wheretoclimb.model.beans.UserRole;
//
//import java.util.List;
//
//@Service("customUserDetailsService")
//public class CustomUserDetailsService implements UserDetailsService{
//
//	private final UserRepository userRepository;
//	private final UserRoleRepository userRoleRepository;
//
//	@Autowired
//    public CustomUserDetailsService( UserRepository userRepository, UserRoleRepository userRoleRepository ) {
//        this.userRepository = userRepository;
//        this.userRoleRepository = userRoleRepository;
//    }
//
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByUserName(username);
//		if(null == user){
//			throw new UsernameNotFoundException("No user present with username: "+username);
//		}else{
//			List< /*UserRole*/ String > userRoles = userRoleRepository.findRoleByUserName(username);
//			return new CustomUserDetails( user, userRoles );
//		}
//	}
//
//}
