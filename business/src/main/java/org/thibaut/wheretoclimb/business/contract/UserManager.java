package org.thibaut.wheretoclimb.business.contract;

import org.thibaut.wheretoclimb.model.beans.User;

import java.util.List;

public interface UserManager {

	public List< User > getUsers();

	//	@Autowired --> pourquoi cette injection ne fonctionne pas ?
	User findByUserName( String username );

	User findByEmail( String email );
}
