//package org.thibaut.wheretoclimb.webapp.controller;
//
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
//import org.thibaut.wheretoclimb.business.contract.UserManager;
//import org.thibaut.wheretoclimb.consumer.repository.UserRepository;
//import org.thibaut.wheretoclimb.model.beans.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class UsersController {
//
////	@Autowired
//	private ManagerFactory managerFactory;
//
////	@Autowired
//	public UsersController( ManagerFactory managerFactory ) {
//		this.managerFactory = managerFactory;
//	}
//
////	@PreAuthorize("hasAnyRole('ADMIN')")
//	@GetMapping("/secured/users")
//	public ArrayList< User > getUsers(){
//		return ( ArrayList< User > ) this.managerFactory.getUserManager().getUsers();
//	}
//
//}
