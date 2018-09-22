package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.*;

@Component
public class ManagerFactoryImpl implements ManagerFactory {


	private UserManager userManager;
	private AtlasManager atlasManager;
	private RoleManager roleManager;
	private PasswordManager passwordManager;

	@Override
	public UserManager getUserManager( ) {
		return userManager;
	}

	@Override
	@Autowired
	public void setUserManager( UserManager userManager ) {
		this.userManager = userManager;
	}

	@Override
	public AtlasManager getAtlasManager( ) {
		return atlasManager;
	}

	@Override
	@Autowired
	public void setAtlasManager( AtlasManager atlasManager ) {
		this.atlasManager = atlasManager;
	}


	@Override
	public RoleManager getRoleManager( ) {
		return roleManager;
	}

	@Override
	@Autowired
	public void setRoleManager( RoleManager roleManager ) {
		this.roleManager = roleManager;
	}

	@Override
	public PasswordManager getPasswordManager( ) {
		return passwordManager;
	}

	@Override
	@Autowired
	public void setPasswordManager( PasswordManager passwordManager ) {
		this.passwordManager = passwordManager;
	}
}
