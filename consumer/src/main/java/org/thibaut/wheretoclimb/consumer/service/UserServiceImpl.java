package org.thibaut.wheretoclimb.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thibaut.wheretoclimb.consumer.repository.UserRepository;
import org.thibaut.wheretoclimb.model.beans.User;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public User findByUsernameOrEmail( String usernameOrEmail ) {
		User user = null;
		try {
			user = userRepository.findByUsernameOrEmail(usernameOrEmail);
		} catch (Exception e) {
			throw e;
		}
		return user;
	}
}
