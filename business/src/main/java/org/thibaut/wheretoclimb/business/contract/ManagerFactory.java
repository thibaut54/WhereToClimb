package org.thibaut.wheretoclimb.business.contract;

import org.springframework.beans.factory.annotation.Autowired;

public interface ManagerFactory {

	abstract UserManager getUserManager();

	void setUserManager( UserManager userManager );

	abstract AtlasManager getAtlasManager();

	void setAtlasManager( AtlasManager atlasManager );

	RoleManager getRoleManager( );

	void setRoleManager( RoleManager roleManager );

	PasswordManager getPasswordManager( );

	void setPasswordManager( PasswordManager passwordManager );

	AreaManager getAreaManager( );

	@Autowired
	void setAreaManager( AreaManager areaManager );
}
