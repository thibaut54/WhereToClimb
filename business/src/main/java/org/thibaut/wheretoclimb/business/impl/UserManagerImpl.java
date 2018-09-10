package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.UserManager;
import org.thibaut.wheretoclimb.model.beans.User;

import java.util.List;

@Component
public class UserManagerImpl extends AbstractManager implements UserManager {

	@Override
	@Autowired
	public List< User > getUsers( ) {

		System.out.println( "In USERMANAGER, getUsers() method" );

		List<User> users = getDaoFactory().getUserRepository().findAll();

		users.forEach( user -> System.out.println( user.toString() )  );

		return users;
	}

}
