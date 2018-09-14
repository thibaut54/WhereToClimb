package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AtlasManager;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.business.contract.RoleManager;
import org.thibaut.wheretoclimb.business.contract.UserManager;

@Component
public class ManagerFactoryImpl implements ManagerFactory {

	private UserManager userManager;
	private AtlasManager atlasManager;
	private RoleManager roleManager;

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
}
