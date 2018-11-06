package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.PasswordManager;
import org.thibaut.wheretoclimb.business.contract.UserManager;
import org.thibaut.wheretoclimb.consumer.repository.UserRepository;
import org.thibaut.wheretoclimb.model.entity.Role;
import org.thibaut.wheretoclimb.model.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserManagerImpl extends AbstractManager implements UserManager {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordManager passwordManager;

	@Override
	public List< User > getUsers( ) {

		System.out.println( "In USERMANAGER, getUsers() method" );

		List<User> users = getDaoFactory().getUserRepository().findAll();

		users.forEach( user -> System.out.println( user.toString() )  );

		return users;
	}


	@Override
	public User findByUserName( String username){
		User user = null;
		try {
			user = getDaoFactory().getUserRepository().findByUserName(username);
		} catch (Exception e) {
			throw e;
		}

		return user;
	}


	@Override
	public  User findByEmail( String email ){
		User user = null;
		try {
			user = getDaoFactory().getUserRepository().findByEmail(email);
		} catch (Exception e) {
			throw e;
		}
		return user;
	}


	@Override
	public User createUser( User user ) {

		String encrytedPassword = passwordManager.crypt(user.getPassword());

		user.setPassword( encrytedPassword );

		List< Role > roles = new ArrayList<>();

		roles.add( getDaoFactory().getRoleRepository().findByRoleLike( "%USER" ) );

		user.setRoles( roles );
		this.getDaoFactory().getUserRepository().save( user );
		return user;
	}


}
