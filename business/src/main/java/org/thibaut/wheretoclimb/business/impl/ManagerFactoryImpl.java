package org.thibaut.wheretoclimb.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thibaut.wheretoclimb.business.contract.AtlasManager;
import org.thibaut.wheretoclimb.business.contract.ManagerFactory;
import org.thibaut.wheretoclimb.business.contract.UserManager;

@Component
public class ManagerFactoryImpl implements ManagerFactory {

//	@Autowired
	private UserManager userManager;
	private AtlasManager atlasManager;

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
}
