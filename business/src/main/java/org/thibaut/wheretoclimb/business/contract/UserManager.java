package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.entity.User;

import java.util.List;

public interface UserManager {

	public List< User > getUsers();

	User findByUserName( String username );

	User findByEmail( String email );

	User createUser( User user );
}
