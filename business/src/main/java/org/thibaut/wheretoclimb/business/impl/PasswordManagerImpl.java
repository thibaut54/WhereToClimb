package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.PasswordManager;

@Component
public class PasswordManagerImpl implements PasswordManager {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public String crypt( String password ){
		return bCryptPasswordEncoder.encode( password );
	}
}
