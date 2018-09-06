package org.thibaut.wheretoclimb.consumer.service;

import org.thibaut.wheretoclimb.model.beans.User;

public interface UserService {

	User findByUsernameOrEmail( String usernameOrEmail);
}
