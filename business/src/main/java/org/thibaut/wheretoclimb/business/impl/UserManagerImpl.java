package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.UserManager;
import org.thibaut.wheretoclimb.consumer.repository.UserRepository;
import org.thibaut.wheretoclimb.model.beans.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Component
public class UserManagerImpl extends AbstractManager implements UserManager {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Autowired
	public List< User > getUsers( ) {

		System.out.println( "In USERMANAGER, getUsers() method" );

		List<User> users = getDaoFactory().getUserRepository().findAll();

		users.forEach( user -> System.out.println( user.toString() )  );

		return users;
	}

//	@Autowired --> pourquoi cette injection ne fonctionne pas ?
	@Override
	public User findByUserName( String username ){
		User user = null;
		try {
			user = userRepository.findByUserName(username);
		} catch (Exception e) {
			throw e;
		}
		return user;
	}


}
