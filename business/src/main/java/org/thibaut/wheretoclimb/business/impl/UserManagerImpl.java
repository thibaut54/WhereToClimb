package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.PasswordManager;
import org.thibaut.wheretoclimb.business.contract.UserManager;
import org.thibaut.wheretoclimb.consumer.repository.UserRepository;
import org.thibaut.wheretoclimb.model.entity.Role;
import org.thibaut.wheretoclimb.model.entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserManagerImpl extends AbstractManager implements UserManager {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordManager passwordManager;


	@Override
	public List< User > getUsers( ) {

		List<User> users = getDaoFactory().getUserRepository().findAll();

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
		getDaoFactory().getUserRepository().save( user );
		return user;
	}


	@Override
	public User findById( Integer id ){
		User user = null;
		try {
			user = getDaoFactory().getUserRepository().findById( id ).get();
		} catch (Exception e) {
			throw e;
		}
		return user;
	}

}
