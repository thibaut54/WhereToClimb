package org.thibaut.wheretoclimb.webapp.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thibaut.wheretoclimb.consumer.repository.UserRepository;
import org.thibaut.wheretoclimb.model.beans.User;

import java.util.List;

@RestController
public class UsersController {

	@Autowired
	private UserRepository userRepository;


	public UsersController( UserRepository userRepository ) {
		this.userRepository = userRepository;
	}

	@GetMapping("/all")
	public String hello(){
		return "Unsecured hello";
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/secured/users")
	public String securedHello(){
		return "Secured hello!";
	}
//	public List< User > getUsers(){
//		List< User > users = this.userRepository.findAll();
//		return users;
//	}

}
